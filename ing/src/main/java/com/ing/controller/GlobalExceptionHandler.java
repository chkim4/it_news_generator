package com.ing.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 예외 처리를 위한 Controller
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
   /**
    * 모든 Controller에서 발생한 모든 예외를 한꺼번에 처리하기 위한 메소드 <br>
    * model.addAttribute("msg", 출력할 문구); 형태를 통해 화면에 띄울 문구 지정 가능 <br>
    * 기본 문구: error-component.jsp 파일 참고
    * @param exception Controller로부터 받아온 예외 객체. 추후 로깅 등을 위해 받아옴
    * @return 예외 처리 페이지
    */
    @ExceptionHandler({Exception.class})
    public String exceptionHandler(Exception exception) {
        return "redirect:/error";
    }
}