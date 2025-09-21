package com.gkfcsolution.springbootspringsecurityjwtcomplete.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2025 at 20:54
 * File: null.java
 * Project: Spring-Boot-Spring-Security-JWT-Complete
 *
 * @author Frank GUEKENG
 * @date 21/09/2025
 * @time 20:54
 */
@RestController
@RequestMapping("/api/v1/rest")
public class WelcomeController {

    @GetMapping("/hello")
    public String welcome(){
        return "Welcome";
    }
}
