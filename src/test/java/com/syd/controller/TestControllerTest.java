package com.syd.controller;

import com.syd.TestApplication;
import com.syd.service.TestService;
import com.syd.service.impl.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestControllerTest {
    @Resource
    private TestController testController;
    @Resource
    private TestServiceImpl testService;

    @Test
    public void test() {
        System.out.println("TEST METHOD");
        String accessToken = testService.getAccessToken();

        /*Map<String, String> getenv = System.getenv();
        System.out.println(getenv);
        Properties properties = System.getProperties();
        System.out.println("=====================================");
        System.out.println(properties);
        Enumeration<?> enumeration = properties.propertyNames();
        testController.test();*/
    }

}
