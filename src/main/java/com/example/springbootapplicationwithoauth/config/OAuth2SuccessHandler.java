package com.example.springbootapplicationwithoauth.config;


import com.example.springbootapplicationwithoauth.entity.User;
import com.example.springbootapplicationwithoauth.service.UserService;
import com.example.springbootapplicationwithoauth.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    public OAuth2SuccessHandler(JwtUtil jwtUtil, UserService userService)
    {
        this.jwtUtil=jwtUtil;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        // 🔥 Save or fetch user
        User user = userService.getOrCreateUser(name, email);

        String token = jwtUtil.generateToken(email);

        // 🔥 TEMP: Just print (we’ll replace with JWT)
        System.out.println("Logged in user email: " + email);



        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                String.format("{\"token\":\"%s\"}", token)
        );

    }
}