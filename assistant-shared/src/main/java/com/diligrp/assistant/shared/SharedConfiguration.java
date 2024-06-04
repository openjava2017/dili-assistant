package com.diligrp.assistant.shared;

import com.diligrp.assistant.shared.exception.PlatformServiceException;
import com.diligrp.assistant.shared.security.RsaCipher;
import com.diligrp.assistant.shared.util.JsonUtils;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
@ComponentScan("com.diligrp.assistant.shared")
public class SharedConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    // Jackson DataBinding所有配置
    @Bean
    @ConditionalOnClass(JavaTimeModule.class)
    public Jackson2ObjectMapperBuilderCustomizer customizeJacksonConfig() {
        return JsonUtils::initObjectMapperBuilder;
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        // 不能使用lambda表达式，否则导致springboot启动问题
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                try {
                    return StringUtils.hasText(source) ? LocalDateTime.parse(source, DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT)) : null;
                } catch (Exception ex) {
                    throw new IllegalArgumentException(String.format("Error parse %s to LocalDateTime", source), ex);
                }
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        // 不能使用lambda表达式，否则导致springboot启动问题
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                try {
                    return StringUtils.hasText(source) ? LocalDate.parse(source, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)) : null;
                } catch (Exception ex) {
                    throw new IllegalArgumentException(String.format("Error parse %s to LocalDate", source), ex);
                }
            }
        };
    }

    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        // 不能使用lambda表达式，否则导致springboot启动问题
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                try {
                    return StringUtils.hasText(source) ? LocalTime.parse(source, DateTimeFormatter.ofPattern(Constants.TIME_FORMAT)) : null;
                } catch (Exception ex) {
                    throw new IllegalArgumentException(String.format("Error parse %s to LocalTime", source), ex);
                }
            }
        };
    }

    @Bean
    public Converter<String, Date> dateConverter() {
        // 不能使用lambda表达式，否则导致springboot启动问题
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                try {
                    return StringUtils.hasText(source) ? new SimpleDateFormat(Constants.DATE_TIME_FORMAT).parse(source) : null;
                } catch (Exception ex) {
                    throw new IllegalArgumentException(String.format("Error parse %s to Date", source), ex);
                }
            }
        };
    }

    @Component
    @ConfigurationPropertiesBinding
    public class PrivateKeyConverter implements Converter<String, PrivateKey> {
        @Override
        public PrivateKey convert(String source) {
            try {
                return RsaCipher.getPrivateKey(source);
            } catch (Exception ex) {
                throw new PlatformServiceException("privateKey configuration failed", ex);
            }
        }
    }

    @Component
    @ConfigurationPropertiesBinding
    public class PublicKeyConverter implements Converter<String, PublicKey> {
        @Override
        public PublicKey convert(String source) {
            try {
                return RsaCipher.getPublicKey(source);
            } catch (Exception ex) {
                throw new PlatformServiceException("publicKey configuration failed", ex);
            }
        }
    }
}