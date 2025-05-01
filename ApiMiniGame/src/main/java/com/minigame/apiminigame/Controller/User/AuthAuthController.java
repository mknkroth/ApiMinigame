package com.minigame.apiminigame.Controller.User;

import com.minigame.apiminigame.Model.UserAuthModel;
import com.minigame.apiminigame.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthAuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserAuthModel> register(@RequestBody UserAuthModel user) {
        UserAuthModel newUser  = authService.registerUser (user);
        return ResponseEntity.ok(newUser );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthModel user) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Optional<UserAuthModel> optionalUser = authService.findByUsername(user.getUsername());
            return ResponseEntity.ok(optionalUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
