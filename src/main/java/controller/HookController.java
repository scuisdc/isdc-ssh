package controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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

    @RequestMapping("/ssh/push")
    public void onSSHPush() throws IOException, InterruptedException {
        System.out.println("backend push event received...updating project...");
        Process pr = Runtime.getRuntime().exec("/usr/bin/update-backend-from-github");
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }
    }

    @RequestMapping("/ng/push")
    public void onNGPush() throws IOException, InterruptedException {
        System.out.println("frontend push event received...updating project...");
        Process pr = Runtime.getRuntime().exec("/usr/bin/update-frontend-from-github");
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = buf.readLine()) != null) {
            System.out.println(line);
        }
    }
}
