package com.laptrinhjavaweb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FirstAccessInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirstAccessInterceptor.class);

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Inside CustomUserDetailsServiceInterceptor preHandle");

        String username = request.getParameter("username"); // You might need to adjust this to get the username parameter from the request
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                // Do something with the user details if needed
            }
        }

        return true;
    }
}
