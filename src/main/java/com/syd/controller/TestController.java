package com.syd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@RestController
@RequestMapping
public class TestController {
    @GetMapping("/test")
    public String test() {
        System.out.println("Hello World!");
        return "test";
    }
}
