package com.minigame.apiminigame.Model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_userauth")
public class UserAuthModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) // Ensure username is not null and unique
    private String username;

    @Column(nullable = false) // Ensure password is not null
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @Override
    public String toString() {
        return "UserAuthModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public UserAuthModel() {
    }

    public UserAuthModel(Long id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
