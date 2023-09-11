package com.ing.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ing.entity.ArticleSummary;
import com.ing.service.ArticleService;

import com.ing.utils.NewsUtils;

/**
 * Article 테이블 관련 Controller
 * 
 */
@Controller
public class ArticleController {
    
    // 뉴스 요약 페이지에서 한 페이지에 출력할 기사의 개수 
    private static final int PAGE_SIZE = 2;
    
    // 뉴스 요약 페이지에서 페이지 하단에 표시할 페이지 개수  (ex. 10일 경우 하단 페이지 하단: 1~10, 11 ~ 20,...) 
    private static final int PAGE_UNIT = 10;
    
    
    @Autowired
    ArticleService articleService;
    

    /** 
     * <pre>
     * 메인 페이지 호출 메소드
     *   0. 날짯값: 쿼리스트링을 통해 할당한 값 (ex. 'news?date=2017-01-13'에서 '2017-01-13'이 날짯값)
     *   1. 오늘 날짜 혹은 날짯값을 부여하지 않고 뉴스 조회 시: (오늘) 뉴스 영상 페이지 호출
     *   2. 날짯값을 부여했으나 오늘 날짜가 아닐 경우: 뉴스 요약 페이지 호출
     *   3. 해당 날짜에 뉴스가 없을 경우: 뉴스 없음 페이지 호출
     * </pre>
     * 
     * @param date: 사용자가 요청한 날짯값
     * @param model: 데이터 전송을 위한 객체
     * @param pageable: pagination 설정을 위한 객체
     * @return 위 조건에 따라 이동할 페이지 이름
     * @throws DateTimeParseException - 사용자가 잘못된 날짯값 송신 시 발생하는 예외 (ex. 'news?date=asdf')
     */
    @GetMapping(value = "/news")
    public String test(
            @RequestParam(value = "date" , required = false) String date,
            Model model, @PageableDefault(size = PAGE_SIZE) Pageable pageable) throws DateTimeParseException { 
        
        Boolean isToday = false;
        LocalDate requestDate = null; 
        String view = "news-no";
            
        if (date == null) {
            isToday = true;
            requestDate = LocalDate.now();
        }
        else {
            requestDate = LocalDate.parse(date);
            isToday = LocalDate.now().isEqual(requestDate);       
        }
        
        // 테스트를 위해 넣은 코드. 테스트 후 삭제 요망
        isToday = false;
    
        // 오늘 영상 조회하는 기능 구현해야 함.
        if(isToday) {
           view = "main";
        }
        
        else { 
            
            String requestDateStr = requestDate.toString();            
            
            Page<ArticleSummary> page = articleService.findAllByCreatedAtOrderByOrd(requestDateStr, pageable);
            List<ArticleSummary> articles = page.getContent();                 
  
            if (!articles.isEmpty()) {                
                
                model.addAttribute("articles", articles);
                model.addAllAttributes(NewsUtils.getPaginationData(page, PAGE_UNIT));
                
                view = "news-summary";
            }
        } 
        return view;
    } 
}
