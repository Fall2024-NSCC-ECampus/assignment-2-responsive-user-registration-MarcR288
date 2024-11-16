package com.example.demo.Controllers;

import com.example.demo.Model.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", new User());
        return "UserRegistration";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "UserLogin";
    }

    @GetMapping("/login/{username}")
    public String showUserPage(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        return "userPage"; // This will point to the userPage.html template
    }

    @PostMapping("/login")
    public String handleUserLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        // Validate and sanitize inputs
        username = username.trim();

        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Username and password cannot be empty");
            System.out.println("Username and password cannot be empty");
            return "UserLogin"; // Return to the login page with an error
        }

        return "UserLogin"; // Spring Security will handle the login logic
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {

        userService.registerUser(username, email, password);

        return "redirect:/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        return "redirect:/login"; // Redirect to the login page
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
}
