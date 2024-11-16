package com.example.demo.Services;

import com.example.demo.Model.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(String username, String email, String password) {

        username = username.trim();
        email = email.toLowerCase().trim();

        if (userRepository.existsByUsername(username)) {
            System.out.println("User with username " + username + " already exists");
            throw new IllegalArgumentException("User with username " + username + " already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Consider hashing the password

        userRepository.save(user);


        System.out.println("New user created " + user.getUsername());
    }

}
