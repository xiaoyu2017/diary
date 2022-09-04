package cn.fishland.diary.config;

import cn.fishland.diary.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    private static final List<String> EXCLUDE_PATH = Arrays.asList("/", "css/**", "database/**", "easyui/**", "js/**",
            "img/**", "/article/**", "fonts/**", "self/**", "/login", "/register", "/v1/login", "/index", "/v1/img/**",
            "/page/**", "/code/image");

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                //拦截的路径
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);
    }
}
