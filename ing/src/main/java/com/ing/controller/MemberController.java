package com.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ing.entity.Member;
import com.ing.service.MemberService;

/**
 * Member 테이블 관련 Controller
 */
@Controller
public class MemberController {

    @Autowired
    private PasswordEncoder pwEncoder;
    
    @Autowired
    private MemberService memberService;

    // 로그인 페이지로 이동
    @GetMapping(value = "/login")
    public String loginPage() {
       
        return "login";
    }

    // 인증 실패 페이지로 이동
    @GetMapping(value = "/auth-error")
    public String authErrorPage() {    
        
        return "auth-error";
    }

    // 회원 가입 페이지로 이동
    @GetMapping(value = "/register")
    public String registerPage() { 
        
        return "register";
    }
    
    // 회원 가입 로직 처리
    @PostMapping(value = "/register")
    public String register(@ModelAttribute Member member) {
        
        String pass = pwEncoder.encode(member.getPass());
        member.setPass(pass); 
        
        memberService.register(member);
        
        return "main";

    }
    
    // 메인 페이지로 이동
    @GetMapping(value = "/main")
    public String main() {
        return "main";
    }

}
