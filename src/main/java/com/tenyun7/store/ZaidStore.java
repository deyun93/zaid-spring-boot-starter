package com.tenyun7.store;

public interface ZaidStore {

    void putSecret(String appKey, String appSecret);

    String getAppSecret(String appSecret);

    void putSign(String appKey, long timestamp, String nonce, String signature);

    String getSign(String appKey, long timestamp, String nonce);


}
