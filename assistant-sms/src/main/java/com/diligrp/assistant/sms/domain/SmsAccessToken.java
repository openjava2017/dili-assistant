package com.diligrp.assistant.sms.domain;

import com.diligrp.assistant.shared.Constants;
import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.shared.codec.StringCodec;
import com.diligrp.assistant.sms.exception.SmsServiceException;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;
import java.util.StringTokenizer;

public class SmsAccessToken implements Serializable {

    private String token;

    public SmsAccessToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String toString(PrivateKey privateKey) {
        try {
            byte[] bytes = StringCodec.getEncoder().encode(token);
            Signature signature = Signature.getInstance(Constants.SIGN_ALGORITHM);
            signature.initSign(privateKey, new SecureRandom());
            signature.update(bytes);
            byte[] sign = signature.sign();
            return String.format("%s.%s", Base64.getEncoder().encodeToString(bytes), Base64.getEncoder().encodeToString(sign));
        } catch (Exception ex) {
            throw new SmsServiceException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "accessToken sign failed");
        }
    }

    public static SmsAccessToken of(String authorization, PublicKey publicKey) {
        StringTokenizer tokenizer = new StringTokenizer(authorization, ".");
        if (tokenizer.countTokens() != 2) {
            throw new SmsServiceException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Invalid accessToken format");
        }
        byte[] data = Base64.getDecoder().decode(tokenizer.nextToken());
        byte[] sign = Base64.getDecoder().decode(tokenizer.nextToken());

        try {
            Signature signature = Signature.getInstance(Constants.SIGN_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data);
            boolean result = signature.verify(sign);
            if (!result) {
                throw new SmsServiceException(ErrorCode.UNAUTHORIZED_ACCESS_ERROR, ErrorCode.MESSAGE_ACCESS_DENIED);
            }

            String token = StringCodec.getDecoder().decode(data);
            return new SmsAccessToken(token);
        } catch (Exception ex) {
            throw new SmsServiceException(ErrorCode.ILLEGAL_ARGUMENT_ERROR, "Invalid accessToken data");
        }
    }
}
