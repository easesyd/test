package com.syd.controller;

import com.syd.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@RestController
@RequestMapping
public class TestController {
    @Resource
    private TestService testService;
    @GetMapping("/test")
    public String test() {
        testService.test();
        return "test";
    }
}
