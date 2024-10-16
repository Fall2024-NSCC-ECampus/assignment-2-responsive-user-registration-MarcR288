package com.example.demo.Services;

import com.example.demo.Model.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerNewUser(String username, String email, String password, String confirmPassword) {
        if (userRepository.findByUsername(username) != null) {
            return "Username already exists!";
        }
        if (userRepository.findByEmail(email) != null) {
            return "Email already exists!";
        }
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match!";
        }

        String hashedPassword = passwordEncoder.encode(password);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);
        return null; // Return null if registration is successful
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
