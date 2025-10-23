package com.poly.lab8.Interceptor;

import com.poly.lab8.Entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);

        Account user = (Account) session.getAttribute("user");

        // Not logged in
        if (user == null) {
            session.setAttribute("message", "Please log in to access this page!");
            response.sendRedirect("/auth/login");
            return false;
        }

        // Trying to access admin pages without admin role
        if (uri.startsWith("/admin") && !isAdmin(user)) {
            session.setAttribute("message", "You must be an admin to access this page!");
            response.sendRedirect("/auth/login");
            return false;
        }

        return true;
    }

    private boolean isAdmin(Account user) {
        // You may have a proper role system; here, we just simulate it.
        // Assume you add a `boolean isAdmin` or a `role` field to Account.
        return user.getUsername().equalsIgnoreCase("admin"); // Simplified check
    }
}