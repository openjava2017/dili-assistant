package com.diligrp.assistant.boot;

import com.diligrp.assistant.data.DataConfiguration;
import com.diligrp.assistant.dfs.DfsConfiguration;
import com.diligrp.assistant.logging.LoggingConfiguration;
import com.diligrp.assistant.product.ProductConfiguration;
import com.diligrp.assistant.shared.SharedConfiguration;
import com.diligrp.assistant.sms.SmsConfiguration;
import com.diligrp.assistant.uid.UidConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({BootConfiguration.class, SharedConfiguration.class, DfsConfiguration.class, SmsConfiguration.class,
        DataConfiguration.class, ProductConfiguration.class, LoggingConfiguration.class, UidConfiguration.class})
@EnableDiscoveryClient
public class AssistantServiceBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(AssistantServiceBootstrap.class, args);
    }
}