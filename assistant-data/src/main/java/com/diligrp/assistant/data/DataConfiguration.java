package com.diligrp.assistant.data;

import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.diligrp.assistant.data")
@MapperScan(basePackages =  {"com.diligrp.assistant.data.dao"}, markerInterface = MybatisMapperSupport.class)
public class DataConfiguration {
}
