package com.example.events.service;

import com.example.events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.events.models.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User getByUsername(String username) {
        return userRepository.findByName(username).orElse(null);
    }

    public void creatNewUser(String username, String password) { //todo - check if user exists
        User user = new User();
        user.setName(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findById(Long creatorId) {
        return userRepository.findById(creatorId).orElse(null);
    }
}
