package com.minigame.apiminigame.Service;

import com.minigame.apiminigame.Model.UserAuthModel;
import com.minigame.apiminigame.Repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthModel user  = userAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        // Convert roles to GrantedAuthorities
        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
//                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        // Return UserDetails with username, password, and authorities
        return new User(user.getUsername(), user.getPassword(), authorities);
    }


}
