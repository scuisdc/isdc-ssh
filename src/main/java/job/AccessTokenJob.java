package job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import support.TokenAuthenticationService;
import weixin.popular.api.TokenAPI;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-8-6.
 */
@EnableScheduling
@Component
public class AccessTokenJob {

    public static String access_token = "";

    private String appId;

    private String appSecret;

    public AccessTokenJob(@Value("${weixin.appId:appid}") String appId, @Value("${weixin.appSecret:appsecret}") String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
        refreshToken();
        TokenAuthenticationService.setSECRET(appSecret);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshToken() {
        access_token = TokenAPI.token(appId, appSecret).getAccess_token();
    }
}
