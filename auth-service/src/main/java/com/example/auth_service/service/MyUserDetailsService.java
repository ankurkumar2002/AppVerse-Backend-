package com.example.auth_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.auth_service.model.UserPrinciple;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.UserRepo;



@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User mot found");
            throw new UsernameNotFoundException(username);
        }

        return new UserPrinciple(user);
    }
}
