package com.dnui.forest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /images/** 映射到磁盘上的 /data/uploads/ 文件夹
        registry.addResourceHandler("/data/**")
                .addResourceLocations("file:D:\\JavaWeb\\end\\forest\\src\\main\\resources\\static\\data\\");
    }
}
