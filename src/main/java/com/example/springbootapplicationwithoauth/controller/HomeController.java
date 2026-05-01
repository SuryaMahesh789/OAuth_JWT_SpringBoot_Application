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
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2User oauthUser) {
            return oauthUser.getAttributes(); // OAuth flow
        }

        // JWT flow
        return Map.of("email", principal.toString());
    }

    @GetMapping("/")
    public String root() {
        return "OAuth Demo Running";
    }
}
