package com.gkfcsolution.springbootspringsecurityjwtcomplete.api.service;

import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.entity.User;
import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created on 2025 at 19:34
 * File: null.java
 * Project: Spring-Boot-Spring-Security-JWT-Complete
 *
 * @author Frank GUEKENG
 * @date 21/09/2025
 * @time 19:34
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not exist with name :" + username));
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user);
        return  userDetails;
    }
}
