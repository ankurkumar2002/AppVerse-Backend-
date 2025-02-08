package com.example.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.auth_service.model.UserPrinciple;
import com.example.auth_service.model.Users;
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;

    @Autowired
    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        Users user = userService.findByEmail(email)
                .orElseGet(() -> userService.createUserFromOAuth2(oAuth2User));

        return new UserPrinciple(user); 
    }
}
