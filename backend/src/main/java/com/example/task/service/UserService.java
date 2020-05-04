package com.example.task.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails getUserByUsername(String username);
}
