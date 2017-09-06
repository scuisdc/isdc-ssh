package controller;


import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;


/**
 * HookController.java
 * Created on 2017/8/30 12:02
 *
 * @author Amao
 * @version 1.0.1
 */
@RestController
@RequestMapping("/github")
public class HookController {

    @Value("${github.webHookSecret}")
    private String webHookSecret;

    @RequestMapping("/ssh/push")
    public ResponseEntity<Object> onSSHPush(@RequestHeader("X-Hub-Signature") String signature,
                                            @RequestBody String payload, @RequestHeader("User-Agent") String userAgent) throws IOException, InterruptedException {
        String computed = String.format("sha1=%s", HmacUtils.hmacSha1Hex(webHookSecret, payload));
        if (!MessageDigest.isEqual(signature.getBytes(), computed.getBytes())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        System.out.println("backend push event received...updating project...");
        Process pr = Runtime.getRuntime().exec("/usr/bin/update-backend-from-github");
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/ng/push")
    public ResponseEntity<Object> onNGPush(@RequestHeader("X-Hub-Signature") String signature,
                                           @RequestBody String payload, @RequestHeader("User-Agent") String userAgent) throws IOException, InterruptedException {
        String computed = String.format("sha1=%s", HmacUtils.hmacSha1Hex(webHookSecret, payload));
        if (!MessageDigest.isEqual(signature.getBytes(), computed.getBytes())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        System.out.println("frontend push event received...updating project...");
        Process pr = Runtime.getRuntime().exec("/usr/bin/update-frontend-from-github");
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
