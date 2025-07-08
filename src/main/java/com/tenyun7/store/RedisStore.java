package com.tenyun7.store;

import com.tenyun7.config.ZaidProperties;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisStore implements ZaidStore {

    private static final String PREFIX_SECRET = "com:tenyun7:sign:appKey:";
    private static final String PREFIX_SIGN = "com:tenyun7:sign:";
    private final StringRedisTemplate stringRedisTemplate;
    private final long expireTime;

    public RedisStore(ZaidProperties zaidProperties,
                      StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.expireTime = zaidProperties.getExpireTime() == null ? 300 : zaidProperties.getExpireTime();
    }

    @Override
    public void putSecret(String appKey, String appSecret) {
        stringRedisTemplate.opsForValue().set(PREFIX_SECRET + appKey, appSecret);
    }

    @Override
    public String getAppSecret(String appSecret) {
        return stringRedisTemplate.opsForValue().get(PREFIX_SECRET + appSecret);
    }

    @Override
    public void putSign(String appSecret, long timestamp, String nonce,
                        String signature) {

        String key = PREFIX_SIGN + appSecret + "_" + timestamp + "_" + nonce;
        stringRedisTemplate.opsForValue().set(key, signature, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public String getSign(String appKey, long timestamp, String nonce) {

        String key = PREFIX_SIGN + appKey + "_" + timestamp + "_" + nonce;
        return stringRedisTemplate.opsForValue().get(key);
    }

}
