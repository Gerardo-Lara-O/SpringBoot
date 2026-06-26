package com.gerardo.curso.springboot.app.interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ✅ Incluyendo las rutas que quiero
        // registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar", "/app/foo");
        
        // ❌ Excluyendo las rutas
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar", "/app/foo");
    }

}
