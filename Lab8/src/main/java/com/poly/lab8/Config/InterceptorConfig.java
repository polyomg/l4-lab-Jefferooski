package com.poly.lab8.Config;

import com.poly.lab8.Interceptor.AuthInterceptor;
import com.poly.lab8.Interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**"
                )
                .excludePathPatterns("/admin/admin_home");
        // AuthInterceptor: Enforces login/role
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**"
                )
                .excludePathPatterns("/admin/admin_home");
    }
}
