package com.diligrp.assistant.sms;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SmsProperties {
    // 私钥
    private PrivateKey privateKey;
    // 公钥
    private PublicKey publicKey;
    // OSS配置
    private SmsChinese smschinese;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public SmsChinese getSmschinese() {
        return smschinese;
    }

    public void setSmschinese(SmsChinese smschinese) {
        this.smschinese = smschinese;
    }

    public static class SmsChinese {
        private String uri;
        private String uid;
        private String secretKey;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}