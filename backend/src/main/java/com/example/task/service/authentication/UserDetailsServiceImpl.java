package com.example.task.service.authentication;

import com.example.task.model.UserModel;
import com.example.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("This username " + s + " was not found "));

        return new User(userModel.getUsername(), userModel.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(userModel.getRole().name())));
    }
}
