/**
 * jsp 파일 동작 확인 등 테스트를 위한 Controller
 */

package com.ing.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ing.entity.Article;
import com.ing.service.ArticleService;

/**
 * Article 테이블 관련 Controller
 */
@Controller
public class ArticleController {
    
    @Autowired
    ArticleService articleService;
    
    // 메인 페이지 (로그인 시: 오늘 뉴스 페이지 / 없을 시 뉴스 없음 페이지
    @GetMapping(value = "/news")
    public String test(
            @RequestParam(value = "date" , required = false) String date,
            Model model) throws DateTimeParseException { 
        
        System.out.println("news");
        
        Boolean isToday = false;
        LocalDate requestDate = null; 
        String view = "news-no";
        
        /*
         * date 값 후보 및 후보 별 대처
         *  1. null: 로그인이나 회원 가입 후 처음으로 접속할 때 -> 오늘 뉴스 영상 페이지로 이동
         *  2. 올바른 값 (ex.'2017-01-13'): 해당 날짜의 뉴스 페이지로 이동 (해당 날짜가 오늘인 지 확인)
         *  3. 그 외: error.jsp로 이동  
         */
        
        if (date == null) {
            isToday = true;
            requestDate = LocalDate.now();
        }
        else {
            
            try {
                requestDate = LocalDate.parse(date);
                isToday = LocalDate.now().isEqual(requestDate);
            }
            catch(DateTimeParseException e) {                
                throw new DateTimeParseException("잘못된 날짜 값을 입력하였습니다.", e.getParsedString(), e.getErrorIndex());
            }
        }
    
        // 오늘 영상 조회하는 기능 구현해야 함.
        if(isToday) {
           view = "main";
        }
        
        else { 
            List<Article> articles = articleService.findAllByCreatedAt(requestDate);
                
            if (!articles.isEmpty()) {
                view = "news?date=" + requestDate.toString();
            }
        }
        return view;
    } 
}
