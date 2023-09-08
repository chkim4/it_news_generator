package com.ing.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 예외 처리를 위한 Controller
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
       
   /**
    * 모든 Controller에서 발생한 모든 예외를 한꺼번에 처리하는 메소드 <br>
    * @param exception Controller로부터 받아온 예외 객체
    * @return 예외 처리 페이지
    */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception) {
     
        return "redirect:/error";
    }
}