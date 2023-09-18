package com.syd.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

    @Test
    public void test() {
        testController.test();
    }
}
