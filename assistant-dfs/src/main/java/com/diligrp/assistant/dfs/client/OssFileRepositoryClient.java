package com.diligrp.assistant.dfs.client;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.*;
import com.diligrp.assistant.dfs.Constants;
import com.diligrp.assistant.dfs.domain.DfsFile;
import com.diligrp.assistant.dfs.domain.FileMetadata;
import com.diligrp.assistant.dfs.exception.DfsServiceException;
import com.diligrp.assistant.shared.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OssFileRepositoryClient implements FileRepositoryClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(OssFileRepositoryClient.class);

    private OSS client;

    public OssFileRepositoryClient(String uri, String accessKeyId, String accessKeySecret) {
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(200);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(10000);
        // 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(10000);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(15000);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(30000);
        // 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(5);
        client = new OSSClientBuilder().build(uri, credentialsProvider);
    }

    @Override
    public String createFileRepository(String repositoryId) {
        CreateBucketRequest request = new CreateBucketRequest(repositoryId);
        try {
            Bucket bucket = client.createBucket(request);
            return bucket.getName();
        } catch (OSSException oex) {
            LOGGER.error("Failed to create oss bucket", oex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "创建存储空间失败: " + oex.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error("Failed to create oss bucket", ex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "创建存储空间失败: " + ex.getMessage());
        }
    }

    @Override
    public boolean deleteFileRepository(String repositoryId) {
        try {
            client.deleteBucket(repositoryId);
            return true;
        } catch (OSSException oex) {
            LOGGER.error("Failed to delete oss bucket", oex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件仓库删除失败: " + oex.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error("Failed to delete oss bucket", ex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件仓库删除失败: " + ex.getMessage());
        }
    }

    @Override
    public void putFile(String repositoryId, DfsFile file) {
        FileMetadata option = file.getMetadata();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(option.getMimeType());
        metadata.addUserMetadata(Constants.FILE_METADATA_NAME, file.getName());
        PutObjectRequest request = new PutObjectRequest(repositoryId, file.getId(), file.getStream(), metadata);

        try {
            client.putObject(request);
        } catch (OSSException oex) {
            LOGGER.error("Failed to put file into bucket", oex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件上传失败: " + oex.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error("Failed to put file into bucket", ex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件上传失败: " + ex.getMessage());
        }
    }

    @Override
    public DfsFile getFile(String repositoryId, String fileId, String style) {
        GetObjectRequest request = new GetObjectRequest(repositoryId, fileId);
        request.setProcess(style);
        OSSObject ossObject;

        try {
            ossObject = client.getObject(request);
            ObjectMetadata metadata = ossObject.getObjectMetadata();
            String name = metadata.getUserMetadata().get(Constants.FILE_METADATA_NAME);
            FileMetadata fileMetadata = new FileMetadata(metadata.getContentType());
            return new DfsFile(fileId, name, ossObject.getObjectContent(), fileMetadata);
        } catch (OSSException oex) {
            LOGGER.error("Failed to get file from bucket", oex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件下载失败: " + oex.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error("Failed to get file from bucket", ex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件下载失败: " + ex.getMessage());
        }
    }

    @Override
    public boolean deleteFile(String repositoryId, String fileId) {
        try {
            client.deleteObject(repositoryId, fileId);
            return true;
        } catch (OSSException oex) {
            LOGGER.error("Failed to delete file from bucket", oex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件删除失败: " + oex.getErrorMessage());
        } catch (Exception ex) {
            LOGGER.error("Failed to delete file from bucket", ex);
            throw new DfsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "文件删除失败: " + ex.getMessage());
        }
    }

    @Override
    public void destroy() {
        client.shutdown();
    }
}
