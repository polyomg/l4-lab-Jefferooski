package com.poly.lab8.Controller;

import com.poly.lab8.Entity.Account;
import com.poly.lab8.Service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    AccountService accountService;

    @Autowired
    HttpSession session;

    // Show login form
    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        // Debug: Check if database has any users
        try {
            // This is just for debugging - remove in production
            System.out.println("Login form loaded - checking database connection...");
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return "auth/login"; // corresponds to login.html
    }

    // task 4
    // Process login form
//    @PostMapping("/auth/login")
//    public String loginProcess(
//            Model model,
//            @RequestParam("username") String username,
//            @RequestParam("password") String password) {
//
//        Account user = accountService.findById(username);
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid username!");
//        } else if (!user.getPassword().equals(password)) {
//            model.addAttribute("message", "Invalid password!");
//        } else {
//            session.setAttribute("user", user);
//            model.addAttribute("message", "Login successfully!");
//        }
//
//        return "auth/login";
//    }

    // task 5
//    @PostMapping("/auth/login")
//    public String loginProcess(
//            Model model,
//            @RequestParam("username") String username,
//            @RequestParam("password") String password) {
//
//        Account user = accountService.findById(username);
//
//        if (user == null) {
//            model.addAttribute("message", "Invalid username!");
//        } else if (!user.getPassword().equals(password)) {
//            model.addAttribute("message", "Invalid password!");
//        } else {
//            session.setAttribute("user", user);
//            model.addAttribute("message", "Login successfully!");
//
//            // 👇 Redirect back to previously protected URI
//            String securityUri = (String) session.getAttribute("securityUri");
//            if (securityUri != null) {
//                return "redirect:" + securityUri;
//            }
//        }
//
//        return "auth/login";
//    }

    // Process login form submission
    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {

        // Find user from the database
        System.out.println("Attempting to find user: " + username);
        Account user = accountService.findById(username);
        System.out.println("User found: " + (user != null ? user.getUsername() : "null"));

        // Check if user exists and validate password
        if (user == null) {
            System.out.println("User not found in database for username: " + username);
            model.addAttribute("message", "Invalid username!");
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
        } else {
            // Successful login, set user in session
            session.setAttribute("user", user);
            model.addAttribute("message", "Login successfully!");

            // Redirect to appropriate page based on user role
            if (user.isAdmin()) {
                return "redirect:/admin/admin_home"; // Admin Dashboard
            } else {
                return "redirect:/user/user_home"; // Regular User Dashboard
            }
        }

        // If login fails, return to login page
        return "auth/login";
    }

    // Admin home page
    @GetMapping("/admin/admin_home")
    public String adminHome(Model model) {
        return "admin/admin_home";
    }

    // User home page  
    @GetMapping("/user/user_home")
    public String userHome(Model model) {
        return "user/user_home";
    }

    // Logout method to clear session and redirect to login
    @GetMapping("/auth/logout")
    public String logout() {
        session.invalidate(); // Invalidates the session
        return "redirect:/auth/login"; // Redirect back to login
    }
}
