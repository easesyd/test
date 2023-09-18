package com.syd.service.impl;

import com.syd.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
        String TEMPLATE_ID = System.getenv("TEMPLATE_ID");
        String USER_ID = System.getenv("USER_ID");
        String START_DATE = System.getenv("START_DATE");
        String BIRTHDAY = System.getenv("BIRTHDAY");
        String CITY = System.getenv("CITY");
        System.out.println(TEMPLATE_ID);
        System.out.println(USER_ID);
        System.out.println(START_DATE);
        System.out.println(BIRTHDAY);
        System.out.println(CITY);
        System.out.println("==========================");
        Map<String, String> getenv = System.getenv();
        System.out.println(getenv);
        String accessToken = getAccessToken();
        System.out.println(accessToken);
    }

    public String getAccessToken() {
        String APP_ID = System.getenv("APP_ID");
        String APP_SECRET = System.getenv("APP_SECRET");
        Map<String, String> param = new HashMap<>();
        param.put("appid", APP_ID);
        param.put("secret", APP_SECRET);
        String result = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential", String.class, param);
        System.out.println(result);
        return result;
    }
}
