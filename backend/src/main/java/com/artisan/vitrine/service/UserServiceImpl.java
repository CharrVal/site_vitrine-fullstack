package com.artisan.vitrine.service;

import com.artisan.vitrine.entity.User;
import com.artisan.vitrine.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User updateUser = userRepository.findUserById(id);
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setRole(user.getRole());

        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }
}
