package com.diligrp.assistant.product;

import com.diligrp.assistant.shared.mybatis.MybatisMapperSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.diligrp.assistant.product")
@MapperScan(basePackages =  {"com.diligrp.assistant.product.dao"}, markerInterface = MybatisMapperSupport.class)
public class ProductConfiguration {
}