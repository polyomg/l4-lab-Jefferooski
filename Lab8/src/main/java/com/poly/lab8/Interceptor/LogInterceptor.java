package com.poly.lab8.Interceptor;

import com.poly.lab8.Entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Account user = (Account) request.getSession().getAttribute("user");

        if (user != null) {
            System.out.println(
                    "[ACCESS LOG] " + request.getRequestURI() +
                            " | Time: " + new Date() +
                            " | User: " + user.getFullname()
            );
        }

        return true;
    }
}
