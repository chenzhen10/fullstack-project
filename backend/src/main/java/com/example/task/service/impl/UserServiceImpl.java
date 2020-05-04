package com.example.task.service.impl;

import com.example.task.service.UserService;
import com.example.task.service.authentication.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public UserDetails getUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }
}
