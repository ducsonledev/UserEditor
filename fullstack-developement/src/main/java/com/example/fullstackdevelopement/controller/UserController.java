package com.example.fullstackdevelopement.controller;

import com.example.fullstackdevelopement.exception.UserNotFoundException;
import com.example.fullstackdevelopement.model.User;
import com.example.fullstackdevelopement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired // marks constructor to be autowired by spring injection
    private UserRepository userRepository;

    // CRUD
    @PostMapping("/user") // mask
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}") // HTTP GET specific user
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                   user.setUsername(newUser.getUsername());
                   user.setName(newUser.getName());
                   user.setEmail(newUser.getEmail());
                   return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));

    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id))
            throw new UserNotFoundException(id);

        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully.";

    }
}
