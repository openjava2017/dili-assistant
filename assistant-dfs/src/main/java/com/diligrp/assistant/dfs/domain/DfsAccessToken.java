package com.diligrp.assistant.dfs.domain;

import com.diligrp.assistant.dfs.exception.DfsServiceException;
import com.diligrp.assistant.shared.Constants;
import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.shared.codec.StringCodec;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;
import java.util.StringTokenizer;

public class DfsAccessToken implements Serializable {
    // 文件存储通道
    private int pipeline;
    // 文件仓库ID
    private String repositoryId;

    public DfsAccessToken(int pipeline, String repositoryId) {
        this.repositoryId = repositoryId;
        this.pipeline = pipeline;
    }

    public int getPipeline() {
        return pipeline;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public String toString(PrivateKey privateKey) {
        try {
            byte[] bytes = StringCodec.getEncoder().encode(repositoryId);
            ByteBuffer packet = ByteBuffer.allocate(bytes.length + Integer.BYTES);
            packet.putInt(pipeline);
            packet.put(bytes);

            Signature signature = Signature.getInstance(Constants.SIGN_ALGORITHM);
            signature.initSign(privateKey, new SecureRandom());
            signature.update(packet.array());
            byte[] sign = signature.sign();
            return String.format("%s.%s", Base64.getEncoder().encodeToString(packet.array()), Base64.getEncoder().encodeToString(sign));
        } catch (Exception ex) {
            throw new DfsServiceException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "accessToken sign failed");
        }
    }

    public static DfsAccessToken of(String authorization, PublicKey publicKey) {
        StringTokenizer tokenizer = new StringTokenizer(authorization, ".");
        if (tokenizer.countTokens() != 2) {
            throw new DfsServiceException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Invalid accessToken format");
        }
        byte[] data = Base64.getDecoder().decode(tokenizer.nextToken());
        byte[] sign = Base64.getDecoder().decode(tokenizer.nextToken());

        try {
            Signature signature = Signature.getInstance(Constants.SIGN_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data);
            boolean result = signature.verify(sign);
            if (!result) {
                throw new DfsServiceException(ErrorCode.UNAUTHORIZED_ACCESS_ERROR, ErrorCode.MESSAGE_ACCESS_DENIED);
            }

            ByteBuffer packet = ByteBuffer.wrap(data);
            int pipeline = packet.getInt();
            byte[] bytes = new byte[packet.remaining()];
            for (int i = 0; packet.hasRemaining(); i++) {
                bytes[i] = packet.get();
            }
            String repositoryId = StringCodec.getDecoder().decode(bytes);
            return new DfsAccessToken(pipeline, repositoryId);
        } catch (Exception ex) {
            throw new DfsServiceException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Invalid accessToken data");
        }
    }
}
