package com.tenyun7.config;

import com.tenyun7.aspects.SignAspect;
import com.tenyun7.store.RedisStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@EnableConfigurationProperties({ZaidProperties.class})
@Import({AopAutoConfiguration.class})
public class ZaidAutoConfiguration {

    @Bean
    public SignAspect signAspect() {
        return new SignAspect();
    }

    @Configuration
    @ConditionalOnClass(value = StringRedisTemplate.class)
    static class RedisStoreConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public RedisStore redisStore(@Autowired ZaidProperties zaidProperties,
                                     @Autowired StringRedisTemplate stringRedisTemplate) {

            return new RedisStore(zaidProperties, stringRedisTemplate);
        }


    }

}
