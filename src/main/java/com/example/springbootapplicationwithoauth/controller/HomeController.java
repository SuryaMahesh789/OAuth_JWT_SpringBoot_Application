package com.example.springbootapplicationwithoauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController
{
    @GetMapping("/home")
    public Map<String,Object> home(Authentication authentication)
    {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        return oAuth2User.getAttributes();
    }

    @GetMapping("/")
    public String root() {
        return "OAuth Demo Running";
    }
}
