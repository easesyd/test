package com.syd.service.impl;

import com.syd.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        String APP_ID = System.getenv("APP_ID");
        String APP_SECRET = System.getenv("APP_SECRET");
        String TEMPLATE_ID = System.getenv("TEMPLATE_ID");
        String USER_ID = System.getenv("USER_ID");
        String START_DATE = System.getenv("START_DATE");
        String BIRTHDAY = System.getenv("BIRTHDAY");
        String CITY = System.getenv("CITY");
        System.out.println(APP_ID);
        System.out.println(APP_SECRET);
        System.out.println(TEMPLATE_ID);
        System.out.println(USER_ID);
        System.out.println(START_DATE);
        System.out.println(BIRTHDAY);
        System.out.println(CITY);
        System.out.println("==========================");
        Map<String, String> getenv = System.getenv();
        System.out.println(getenv);
    }
}
