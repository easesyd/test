package com.syd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syd.service.TestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private RestTemplate restTemplate;

    @Override
    public void test() {
        String USER_ID = System.getenv("USER_ID");
        String START_DATE = System.getenv("START_DATE");
        String BIRTHDAY = System.getenv("BIRTHDAY");
        String CITY = System.getenv("CITY");
        String accessToken = JSON.parseObject(getAccessToken()).getString("access_token");
        System.out.println(accessToken);
        sendMessage(accessToken);
    }

    public void sendMessage(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
//        String TEMPLATE_ID = System.getenv("TEMPLATE_ID");
        String TEMPLATE_ID = "-Zd6DD96Yqc5ASdRzjJB5V-fU3tulVsj208zGx3Uur0";
        JSONObject object = new JSONObject();
        object.put("touser", "oaM8s6xPcWrT-npsnga5UVuQHx-s");
        object.put("template_id", TEMPLATE_ID);
        JSONObject data = new JSONObject();
        JSONObject dateObject = new JSONObject();
        dateObject.put("value", LocalDateTime.now().toString());
        dateObject.put("color", "#173177");
        data.put("date", dateObject);
        object.put("data", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = object.toString();
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String result = restTemplate.postForObject(url, entity, String.class);
        System.out.println(result);
    }

    public String getAccessToken() {
//        String APP_ID = System.getenv("APP_ID");
//        String APP_SECRET = System.getenv("APP_SECRET");
        String APP_ID = "wx72e3b32d6ed5ee77";
        String APP_SECRET = "1ad43532df49989264e3f722c59fd847";
        Map<String, String> param = new HashMap<>();
        param.put("appid", APP_ID);
        param.put("secret", APP_SECRET);
        return restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}", String.class, param);
    }
}
