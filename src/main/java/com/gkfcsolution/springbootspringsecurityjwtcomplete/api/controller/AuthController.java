package com.gkfcsolution.springbootspringsecurityjwtcomplete.api.controller;

import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.entity.User;
import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.entity.dto.AuthRequest;
import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.service.CustomUserDetails;
import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2025 at 23:08
 * File: null.java
 * Project: Spring-Boot-Spring-Security-JWT-Complete
 *
 * @author Frank GUEKENG
 * @date 21/09/2025
 * @time 23:08
 */
@RestController
@RequestMapping("/api/v1/token")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username/password");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails();
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        customUserDetails.setUser(user);
        return jwtUtil.generateToken(customUserDetails);
    }
}
