package com.example.API_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody String token){
        boolean isValid = jwtUtil.validateToken(token);
        return ResponseEntity.ok(isValid);
    }
}
