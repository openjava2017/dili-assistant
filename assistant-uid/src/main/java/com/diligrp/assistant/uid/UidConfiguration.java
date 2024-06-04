package com.diligrp.assistant.uid;

import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.diligrp.assistant.uid")
@MapperScan(basePackages =  {"com.diligrp.assistant.uid.dao"}, markerInterface = MybatisMapperSupport.class)
public class UidConfiguration {
}
