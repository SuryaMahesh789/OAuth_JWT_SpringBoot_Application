package com.example.springbootapplicationwithoauth.config;

import com.example.springbootapplicationwithoauth.security.JwtFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig
{

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private final JwtFilter jwtFilter;

    public SecurityConfig(OAuth2SuccessHandler oAuth2SuccessHandler, JwtFilter jwtFilter) {
        this.oAuth2SuccessHandler = oAuth2SuccessHandler;
        this.jwtFilter=jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/","/error")
                        .permitAll()
                        .anyRequest()
                        .authenticated())

                .oauth2Login(oauth -> oauth
                        .successHandler(oAuth2SuccessHandler)

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
