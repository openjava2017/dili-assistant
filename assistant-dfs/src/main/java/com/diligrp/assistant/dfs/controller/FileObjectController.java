package com.diligrp.assistant.dfs.controller;

import com.diligrp.assistant.dfs.Constants;
import com.diligrp.assistant.dfs.DfsProperties;
import com.diligrp.assistant.dfs.domain.DfsAccessToken;
import com.diligrp.assistant.dfs.domain.DfsFile;
import com.diligrp.assistant.dfs.domain.FileMetadata;
import com.diligrp.assistant.dfs.exception.DfsServiceException;
import com.diligrp.assistant.dfs.service.FileRepositoryService;
import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.shared.domain.Message;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/dfs")
public class FileObjectController {

    @Resource
    private DfsProperties dfsProperties;

    @Resource
    private FileRepositoryService fileRepositoryService;

    @RequestMapping(value = "/file/upload.do")
    public Message<?> fileUpload(@RequestPart("file") MultipartFile file,
                              @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) throws IOException {
        DfsAccessToken accessToken = accessAuthorization(authorization);

        Optional<MediaType> optional = MediaTypeFactory.getMediaType(file.getOriginalFilename());
        MimeType mimeType = optional.orElse(MediaType.APPLICATION_OCTET_STREAM);
        FileMetadata metadata = new FileMetadata(mimeType.toString());

        DfsFile fileObject = new DfsFile(file.getOriginalFilename(), file.getInputStream(), metadata);
        String fileId = fileRepositoryService.uploadFile(accessToken, fileObject);
        return Message.success(fileId);
    }

    @RequestMapping(value = "/files/upload.do")
    public Message<?> fileUploads(@RequestPart(value = "files") MultipartFile[] files,
                              @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) throws IOException {
        DfsAccessToken accessToken = accessAuthorization(authorization);

        List<String> fileIds = new ArrayList<>();
        for (MultipartFile file : files) {
            Optional<MediaType> optional = MediaTypeFactory.getMediaType(file.getOriginalFilename());
            MimeType mimeType = optional.orElse(MediaType.APPLICATION_OCTET_STREAM);
            FileMetadata metadata = new FileMetadata(mimeType.toString());

            DfsFile fileObject = new DfsFile(file.getOriginalFilename(), file.getInputStream(), metadata);
            String fileId = fileRepositoryService.uploadFile(accessToken, fileObject);
            fileIds.add(fileId);
        }

        return Message.success(fileIds);
    }

    @RequestMapping(value = "/file/uploadByBase64.do")
    public Message<?> uploadByBase64(@RequestParam("file") String file, @RequestParam(value = "name") String name,
                                  @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        DfsAccessToken accessToken = accessAuthorization(authorization);

        Optional<MediaType> optional = MediaTypeFactory.getMediaType(name);
        MimeType mimeType = optional.orElse(MediaType.APPLICATION_OCTET_STREAM);
        FileMetadata metadata = new FileMetadata(mimeType.toString());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(file));
        DfsFile fileObject = new DfsFile(name, inputStream, metadata);
        String fileId = fileRepositoryService.uploadFile(accessToken, fileObject);
        return Message.success(fileId);
    }

    @RequestMapping(value = "/file/preview.do")
    public void preview(@RequestParam("fileId") String fileId, @RequestParam(name = "style", required = false) String style,
                        @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization, HttpServletResponse response) throws IOException {
        DfsAccessToken accessToken = accessAuthorization(authorization);
        DfsFile file = fileRepositoryService.downloadFile(accessToken, fileId, style);
        InputStream fileStream = file.getStream();
        response.setContentType(file.getMetadata().getMimeType());
        response.setContentLength(fileStream.available());
        try {
            IOUtils.copy(fileStream, response.getOutputStream());
        } finally {
            IOUtils.closeQuietly(fileStream);
        }
        response.flushBuffer();
    }

    @RequestMapping(value = "/file/download.do")
    public void download(@RequestParam("fileId") String fileId, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization,
                         HttpServletResponse response) throws IOException {
        DfsAccessToken accessToken = accessAuthorization(authorization);
        DfsFile file = fileRepositoryService.downloadFile(accessToken, fileId, null);
        InputStream fileStream = file.getStream();
        response.setContentType(file.getMetadata().getMimeType());
        response.setContentLength(fileStream.available());
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8));
        try {
            IOUtils.copy(fileStream, response.getOutputStream());
        } finally {
            IOUtils.closeQuietly(fileStream);
        }
        response.flushBuffer();
    }

    @RequestMapping(value = "/file/delete.do")
    public Message<?> deleteFile(@RequestParam("fileId") String fileId, @RequestHeader(Constants.HEADER_AUTHORIZATION) String authorization) {
        DfsAccessToken accessToken = accessAuthorization(authorization);
        fileRepositoryService.deleteFile(accessToken, fileId);
        return Message.success();
    }

    private DfsAccessToken accessAuthorization(String authorization) {
        if (authorization == null) {
            throw new DfsServiceException(ErrorCode.UNAUTHORIZED_ACCESS_ERROR, ErrorCode.MESSAGE_ACCESS_DENIED);
        }
        return DfsAccessToken.of(authorization, dfsProperties.getPublicKey());
    }
}
