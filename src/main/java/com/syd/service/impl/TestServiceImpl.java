package com.syd.service.impl;

import com.syd.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        String APP_ID = System.getProperty("secrets.APP_ID");
        String APP_SECRET = System.getProperty("secrets.APP_SECRET");
        String TEMPLATE_ID = System.getProperty("secrets.TEMPLATE_ID");
        String USER_ID = System.getProperty("secrets.USER_ID");
        String START_DATE = System.getProperty("secrets.START_DATE");
        String BIRTHDAY = System.getProperty("secrets.BIRTHDAY");
        String CITY = System.getProperty("secrets.CITY");
        System.out.println(APP_ID);
        System.out.println(APP_SECRET);
        System.out.println(TEMPLATE_ID);
        System.out.println(USER_ID);
        System.out.println(START_DATE);
        System.out.println(BIRTHDAY);
        System.out.println(CITY);
        String env = System.getenv("secrets.APP_ID");
        System.out.println("env: " + env);
        System.out.println("Hello World!");
    }
}
