package com.ing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ing.entity.Member;
import com.ing.service.MemberService;
import com.ing.service.ScrapService;
import com.ing.utils.NewsUtils;
import com.ing.vo.ArticleScrapVO;

/**
 * Member 테이블 관련 Controller
 */
@Controller
public class MemberController {

    @Autowired
    private PasswordEncoder pwEncoder;
    
    @Autowired
    private MemberService memberService; 
    
    @Autowired
    ScrapService scrapService;

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

    // 마이 페이지로 이동
    @GetMapping(value = "/mypage")
    public String mypage(Model model, @PageableDefault(size = NewsUtils.SUMMARY_PAGE_SIZE) Pageable pageable) {
        
        String defaultUrl = "/mypage";
 
        int memberId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        
        Page<ArticleScrapVO> page = scrapService.findScrapList(memberId, pageable);
        
        List<ArticleScrapVO> articles = page.getContent();
        
        model.addAllAttributes(NewsUtils.getPaginationData(page, NewsUtils.DEFAULT_PAGE_UNIT, defaultUrl));  
        model.addAttribute("articles", articles);
         
        return "mypage";
    } 
    

}
