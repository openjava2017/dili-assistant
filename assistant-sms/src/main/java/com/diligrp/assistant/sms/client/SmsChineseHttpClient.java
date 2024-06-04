package com.diligrp.assistant.sms.client;

import com.diligrp.assistant.shared.ErrorCode;
import com.diligrp.assistant.shared.service.ServiceEndpointSupport;
import com.diligrp.assistant.sms.exception.SmsServiceException;

public class SmsChineseHttpClient extends ServiceEndpointSupport {
    /**
     * 接口地址
     */
    private String uri;

    /**
     * 用户名
     */
    private String uid;

    /**
     * 短信密钥
     */
    private String secretKey;

    public SmsChineseHttpClient(String uri, String uid, String secretKey) {
        this.uri = uri;
        this.uid = uid;
        this.secretKey = secretKey;
    }

    public String sendSmsMessage(String telephone, String message) {
        HttpParam[] params = new HttpParam[4];
        params[0] = HttpParam.create("Uid", uid);
        params[1] = HttpParam.create("Key", secretKey);
        params[2] = HttpParam.create("smsMob", telephone);
        params[3] = HttpParam.create("smsText", message);
        HttpResult result = send(uri, params);
        if (result.statusCode != 200) {
            throw new SmsServiceException(ErrorCode.SERVICE_ACCESS_ERROR, "调用网建短信服务失败");
        }

        System.out.println(result.responseText);
        return null;
    }
}
