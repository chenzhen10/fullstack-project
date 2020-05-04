package com.example.task.controller;

import com.example.task.model.type.Role;
import com.example.task.service.UserService;
import com.example.task.service.jwt.JwtTokenGenerator;
import com.example.task.ui.JwtResponse;
import com.example.task.ui.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/authentication", produces = "application/json; utf-8")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody UserDto userDto){
        authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        UserDetails userByUsername = userService.getUserByUsername(userDto.getUsername());
        String token = jwtTokenGenerator.generateToken(userByUsername);
        Role role = Role.valueOf(userByUsername.getAuthorities().iterator().next().toString());
        return ResponseEntity.ok(new JwtResponse(token, role));
    }

    private void authenticate(Authentication authentication){
        authenticationManager.authenticate(authentication);
    }

}
