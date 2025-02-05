package com.example.auth_service.service;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.auth_service.model.UserPrinciple;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

                UserPrinciple user = (UserPrinciple) authentication.getPrincipal();
                String token = jwtService.generateToken(user,false);


                response.sendRedirect(token);
            


    }
    
}
