package com.poly.lab5.Controller;

import com.poly.lab5.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1() {
        return "/account/login";
    }
    @PostMapping("/account/login")
    public String login2() {
        // Read parameters from request
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        // Check login credentials
        if ("poly".equals(username) && "123".equals(password)) {
            // Successful login
            sessionService.set("username", username);

            if (remember) {
                // Remember user for 10 days
                cookieService.add("user", username, 24 * 10);  // 10 days * 24 hours
            } else {
                // Remove remembered user cookie
                cookieService.remove("user");
            }

            // Redirect or forward to home page or dashboard after login
            return "redirect:/item/index";  // or your desired page
        } else {
            // Login failed: add an error attribute and show login page again
            return "/account/login";
        }
    }
    @RequestMapping("/account/logout")
    public String logout() {
        sessionService.remove("username");  // Clear session login info
        return "redirect:/account/login";   // Redirect to login page
    }
}
