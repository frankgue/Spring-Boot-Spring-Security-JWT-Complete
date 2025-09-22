package com.gkfcsolution.springbootspringsecurityjwtcomplete.api.filter;

import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.service.CustomUserDetailsService;
import com.gkfcsolution.springbootspringsecurityjwtcomplete.api.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created on 2025 at 11:10
 * File: null.java
 * Project: Spring-Boot-Spring-Security-JWT-Complete
 *
 * @author Frank GUEKENG
 * @date 22/09/2025
 * @time 11:10
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

//    @Autowired
//    private JwtUtil jwtUtil;
    private JwtUtil jwtUtil;
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
    private UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // Vérifie si le header contient un Bearer token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Vérifie si le header commence par "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }
        // Si on a un username et que personne n'est encore authentifié
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // On met l'auth dans le contexte Spring
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
