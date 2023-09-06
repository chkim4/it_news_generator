package com.ing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정
 */
@Configuration
public class CustomSpringSecurityConfig {
    
    /**
     * 사용자의 요청 시 가장 먼저 거치는 필터인 SecurityFilterChain 정의
     * @param http 인증 및 비인증 대상, 인증 후 이동할 페이지 등을 설정
     * @return 설정이 끝난 HTTPSecurity 객체
     * @throws Exception - 발생할 수 있는 에러 처리
     */ 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers( "/login", "/register", "/login-proc").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-proc")
                .usernameParameter("email")
                .passwordParameter("pass")
                .defaultSuccessUrl("/main", true)
                .failureUrl("/auth-error")
                .permitAll()
            .and()
                .logout()
            .and()
                .cors().disable()        
                .csrf().disable()        
                .headers().frameOptions().disable();
                    
        return http.build();
    } 
    
    // 정적 자원에 대한 인증 제거 
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**" , "/img/**");
    }
        
    /* 
     * AuthenticationManager 등록
     * 현재 인증을 수행하는 Authentication Provider: CustomAuthenticationProvider
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {       
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    // 암호화 방식 정의
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}