package com.syd;

import com.syd.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author syd
 * @Description：
 * @created 2023/9/15
 */
@SpringBootApplication
public class TestApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
        TestController controller = context.getBean(TestController.class);
        controller.test();
    }
}
