package com.minigame.apiminigame.Service;

import com.minigame.apiminigame.Model.UserAuthModel;
import com.minigame.apiminigame.Repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //Collections use for Sorting, Searching, Shuffling, Reversing, Unmodifiable Collections, Singleton Collections
    public UserAuthModel registerUser (UserAuthModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Collections.singleton("ROLE_USER")); // Default role single role
//        user.setRoles(new HashSet<>(Set.of("ROLE_USER")));
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER"); // Default role
        user.setRoles(roles);
        return userAuthRepository.save(user);
    }

    public Optional<UserAuthModel> findByUsername(String username) {
        return userAuthRepository.findByUsername(username);
    }
}
