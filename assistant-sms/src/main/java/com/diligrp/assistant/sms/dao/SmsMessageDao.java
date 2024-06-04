package com.diligrp.assistant.sms.dao;

import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import com.diligrp.assistant.sms.model.SmsMessageDo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("smsMessageDao")
public interface SmsMessageDao extends MybatisMapperSupport {
    void insertSmsMessage(SmsMessageDo smsMessage);

    Optional<SmsMessageDo> findSmsMessageById(String messageId);

    int updateSmsMessageState(SmsMessageDo smsMessage);
}
