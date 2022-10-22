package com.example.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods(
			HttpMethod.GET.name(),
    		HttpMethod.HEAD.name(),
    		HttpMethod.POST.name(),
    		HttpMethod.PUT.name(),
    		HttpMethod.DELETE.name())
		.allowedOrigins("http://127.0.0.1:8081", "http://localhost:8081");
	}
}
