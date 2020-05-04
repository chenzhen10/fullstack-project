package com.example.task.ui;

import com.example.task.model.type.Role;
import lombok.Data;

@Data
public class JwtResponse {

    private final String token;
    private final Role role;
}
