package com.minigame.apiminigame.Controller.User;
import com.minigame.apiminigame.Model.User;
import com.minigame.apiminigame.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userRequest) {
        User user = new User();
        if (userRequest.getPassword() == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        else if (userRequest.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must not be less than 6 characters");
        }else {
            user =  userService.saveUser(userRequest);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable long id ,@RequestBody User userRequest) {
        User user = userService.updateUser(id, userRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if(userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
        }
        else {
            throw new IllegalArgumentException("Id must not be null");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
