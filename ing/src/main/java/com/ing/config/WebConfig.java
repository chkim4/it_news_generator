package com.ing.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Java 기반의 Spring MVC 설정 커스터마이징 <br>
 * ex. 이미지, js, css, 영상 등 정적 자원 관련 설정
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
   
    @Value("${custom.video.location}")
    private String videoLocation; 
    
    /**
     * 정적 자원 설정
     * @param registry - 정적 자원을 다루는 핸들러
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
        
        registry.addResourceHandler("/video/**")
                .addResourceLocations(videoLocation);

    }
}