package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainController {

    @Autowired
    Facebook facebook;
    @Autowired
    ConnectionRepository connectionRepository;


    @GetMapping("/")
    @ResponseBody
    public String index(){
        if(connectionRepository.findPrimaryConnection(Facebook.class) == null){
            return "Nie zalogowano";
        }
        return facebook.feedOperations().getFeed().stream().map(s -> s.getMessage()).collect(Collectors.joining(","));
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
