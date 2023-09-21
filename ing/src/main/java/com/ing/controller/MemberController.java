package com.ing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.ing.utils.MemberUtils;
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
    public String register(@ModelAttribute Member member, Model model) throws Exception{
        
        System.out.println("회원 가입 시작");
        
        String pass = pwEncoder.encode(member.getPass());
        member.setPass(pass); 
        
        String view = "auth-error";
  
        if (!memberService.register(member)) {
            model.addAttribute("msg", "회원 가입에 실패했습니다. <br> 아래 링크를 통해 로그인 페이지 -> 회원가입 페이지로 이동하여 다시 시도해주십시오.");
        }
        else {
            view = "redirect:/news";
            
            // SpringContextHolder에 회원 가입을 완료한 사용자 정보 추가
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(member.getMemberId(), member.getPass());    
            Authentication authentication = authToken;
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        return view;
    }
    
    /* 마이 페이지로 이동
     * 스크랩한 기사 조회 포함
     */
    @GetMapping(value = "/mypage")
    public String mypage(Model model, @PageableDefault(size = 10) Pageable pageable) throws Exception{
        
        String defaultUrl = "/mypage";
 
        int memberId = MemberUtils.getMemberId();
        
        Page<ArticleScrapVO> page = scrapService.findScrapList(memberId, pageable);
        
        List<ArticleScrapVO> articles = page.getContent();   
        
        model.addAllAttributes(NewsUtils.getPaginationData(page, NewsUtils.DEFAULT_PAGE_UNIT, defaultUrl));  
        model.addAttribute("articles", articles);
         
        return "mypage";
    } 
    
    // 로그아웃. CSRF 공격을 예방하기 위해 POST로 지정. 자세한 건 Spring Security의 HttpSecurity.logoutUrl 참고 
    @PostMapping(value = "/logout")
    public String logout() {
       
        return "redirect:/login";
    }

}
