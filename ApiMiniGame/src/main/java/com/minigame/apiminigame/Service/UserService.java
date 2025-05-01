package com.minigame.apiminigame.Service;

import com.minigame.apiminigame.Model.User;
import com.minigame.apiminigame.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id ,User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User updatedUser = userOptional.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            return userRepository.save(updatedUser);
        }else{
            throw new RuntimeException("User  not found with id: " + id);
        }
    }

    // Method to delete a user by ID
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}