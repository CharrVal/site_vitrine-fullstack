package com.artisan.vitrine.controller;

import com.artisan.vitrine.entity.User;
import com.artisan.vitrine.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000/")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findUserById(id);
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userRepository.findUserById(id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRole(user.getRole());
        return userRepository.save(updatedUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteUserById(id);
    }
}
