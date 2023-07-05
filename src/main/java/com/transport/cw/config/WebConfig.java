package com.transport.cw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${resource.path.locations}")
    private String resourcePath;
    @Value("${resource.path.handler}")
    private String uploacPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations(uploacPath);
        registry.addResourceHandler("/images/**").addResourceLocations("file:src/main/resources/static/images/");
    }
}
