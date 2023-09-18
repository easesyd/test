package com.syd;

import com.syd.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

/**
 * @author syd
 * @Description：
 * @created 2023/9/15
 */
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
        TestController controller = context.getBean(TestController.class);
        controller.test();
    }
}
