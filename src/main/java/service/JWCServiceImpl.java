package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ScoreRequestDAO;
import entity.ScoreRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-7-25.
 */
@Service("JWCService")
@Transactional
@EnableAsync
public class JWCServiceImpl implements JWCService {

    private final ScoreRequestDAO scoreRequestDAO;

    @Autowired
    public JWCServiceImpl(ScoreRequestDAO scoreRequestDAO) {
        this.scoreRequestDAO = scoreRequestDAO;
    }

    @Override
    public void addRequest(ScoreRequest request) {
        scoreRequestDAO.addRequest(request);
    }

    @Async
    @Override
    public void getScore(ScoreRequest request) {
        try {
            String url = "http://202.115.47.141/loginAction.do";
            HttpContext localContext = new BasicHttpContext();
            CookieStore cookieStore = new BasicCookieStore();
            localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", "Mozilla/5.0");
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("zjh", request.getZjh()));
            urlParameters.add(new BasicNameValuePair("mm", request.getMm()));
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "gb2312"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            if (result.toString().contains("密码不正确")) {
                request.setSuccess(false);
                request.setResult("您的密码不正确，请您重新输入！");
            } else {
                HttpGet get = new HttpGet("http://202.115.47.141/bxqcjcxAction.do");
                response = client.execute(get);
                rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "gb2312"));
                result = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                request.setResult(parseScorePage(result.toString()));
                request.setSuccess(true);
            }
        } catch (Exception e) {
            request.setSuccess(false);
            request.setResult("连接出错");
            e.printStackTrace();
        }
        request.setComplete(true);
        scoreRequestDAO.updateRequest(request);
    }

    private String parseScorePage(String page) throws JsonProcessingException {
        String[] arr = new String[]{"kch", "kxh", "nameCN", "nameEN", "credit", "type", "score"};
        Pattern patternTable = Pattern.compile(
                "<table cellpadding=\"0\" width=\"100%\" class=\"displayTag\" cellspacing=\"1\" border=\"0\" id=\"user\">(.*)</TABLE>");
        Pattern patternTr = Pattern.compile("<tr class=\"odd\" onMouseOut=\"this.className='even';\" onMouseOver=\"this.className='evenfocus';\">(.*?)</tr>");
        Pattern patternTd = Pattern.compile("<td align=\"center\">(.*?)</td>");
        List<Map<String, String>> list = new ArrayList<>();
        Matcher matcherTable = patternTable.matcher(page);
        if (matcherTable.find()) {
            String table = matcherTable.group();
            Matcher matcherTr = patternTr.matcher(table);
            while (matcherTr.find()) {
                Map<String, String> map = new HashMap<>();
                String tr = matcherTr.group();
                Matcher matcherTd = patternTd.matcher(tr);
                for (int i = 0; matcherTd.find(); i++) {
                    String text = matcherTd.group();
                    text = text.substring(19, text.length() - 5);
                    map.put(arr[i], text.trim());
                }
                list.add(map);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(list);
    }

    @Override
    public ScoreRequest findRequest(String zjh, String mm, Date date) {
        return scoreRequestDAO.findRequest(zjh, mm, date);
    }
}
