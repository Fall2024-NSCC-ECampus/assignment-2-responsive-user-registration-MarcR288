package com.example.demo.Controllers;

import com.example.demo.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "UserRegistration.html";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "UserLogin.html";
    }

    @GetMapping("/login/{username}")
    @ResponseBody
    public String showUserPage(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        return "Welcome " + username + "!";
    }

    @PostMapping("/login")
    public String handleUserLogin(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            return "redirect:/login/" + username; // Redirect to user-specific page
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "UserLogin"; // Return to the login page with an error
        }
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model) {

        // Call the UserService to register the user
        String errorMessage = userService.registerNewUser(username, email, password, confirmPassword);

        if (errorMessage != null) {
            System.out.println("Error: " + errorMessage);
            model.addAttribute("error", errorMessage);
            return "UserRegistration"; // Return to the registration page with an error
        }

        model.addAttribute("success", "Registration successful! You can now log in.");
        return "redirect:/login"; // Redirect to login page after successful registration
    }
}
