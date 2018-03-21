package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    @GetMapping("/cos")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String cos(){
        return "index";
    }

    @GetMapping("/content")
    @ResponseBody
    public String content(){
        return "tajny content";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
