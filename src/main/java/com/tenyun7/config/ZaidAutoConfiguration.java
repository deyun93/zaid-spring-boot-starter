package com.tenyun7.config;

import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AopAutoConfiguration.class})
public class ZaidAutoConfiguration {
}
