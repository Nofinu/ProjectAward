package com.example.nul_back.dao;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.CharStreams;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Aspect
public class TokenCheck {
    @Before("execution(* com.example.nul_back.controller.*.*(..))")
    public void testToken() {
        RestClient restClient = RestClient.create();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = servletRequest.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        String result = restClient.get().uri("https://id.twitch.tv/oauth2/validate").header("authorization","OAuth "+token).retrieve().body(String.class);
        servletRequest.setAttribute("twitchResponse", result);
    }

}
