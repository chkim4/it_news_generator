package com.ing.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 예외 처리를 위한 Controller
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
       
   /**
    * 모든 Controller에서 발생한 모든 예외를 한꺼번에 처리하는 메소드 <br>
    * 인증을 마쳤을 경우: error.jsp로 이동 (헤더, 내비게이션 포함)
    * 인증이 안 되었을 경우: auth-error.jsp로 이동 (헤더, 내비게이션 없음)
    * @param e Controller로부터 받아온 예외 객체
    * @return 예외 처리 페이지
    */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        System.out.println(e.getMessage());
        
        String view = "redirect:/auth-error";
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(!(auth instanceof AnonymousAuthenticationToken)){
            view = "redirect:/error";
        }
        return view;
    }
}