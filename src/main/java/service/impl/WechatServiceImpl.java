package service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.WechatService;
import weixin.popular.util.SignatureUtil;

import javax.transaction.Transactional;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-10.
 */
@Service("WechatService")
@Transactional
public class WechatServiceImpl implements WechatService {

    private final String token;

    public WechatServiceImpl(@Value("${weixin.messageToken:token}") String token) {
        this.token = token;
    }

    @Override
    public boolean checkSignature(String timestamp, String nonce, String signature) {
        return signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce));
    }
}
