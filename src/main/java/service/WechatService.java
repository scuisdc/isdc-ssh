package service;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-10.
 */
public interface WechatService {

    boolean checkSignature(String timestamp, String nonce, String signature);

    void saveOpenid(String openid);
}
