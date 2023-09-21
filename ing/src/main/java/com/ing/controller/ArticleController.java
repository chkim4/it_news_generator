package com.ing.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.service.ArticleService;
import com.ing.service.VideoService;
import com.ing.utils.MemberUtils;
import com.ing.utils.NewsUtils;
import com.ing.vo.ArticleScrapVO;

/**
 * Article 테이블 관련 Controller
 */
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;
    
    @Autowired
    VideoService videoService;
    
    // 내비게이션에 표시할 기사 개수
    private static int NAV_ARTICLES = 7;
    
    /** 
     * <pre>
     * 메인 페이지 호출 메소드
     *   0. 날짯값: 쿼리스트링을 통해 할당한 값 (ex. 'news?date=2017-01-13'에서 '2017-01-13'이 날짯값)
     *   1. 오늘 날짜 혹은 날짯값을 부여하지 않고 뉴스 조회 시: 오늘 뉴스 페이지 호출
     *   2. 날짯값을 부여했으나 오늘 날짜가 아닐 경우: 뉴스 요약 페이지 호출
     *   3. 해당 날짜에 뉴스가 없을 경우: 뉴스 없음 페이지 호출
     * </pre>
     * 
     * @param date: 사용자가 요청한 날짯값
     * @param base: 사용자가 이전에 요청한 날짯값. 이번 요청으로 내비게이션에 있는 날짜 목록을 갱신할지 판단하는 데 사용. 
     *              없을 시 내비게이션에 date와 date의 (NAV_ARTICLES-1)일 전까지의 날짜 출력
     * @param model: 데이터 전송을 위한 객체
     * @param pageable: pagination 설정을 위한 객체
     * @return 위 조건에 따라 이동할 페이지 이름
     * @throws DateTimeParseException - 사용자가 잘못된 날짯값 송신 시 발생하는 예외 (ex. 'news?date=asdf')
     * @throws JsonProcessingException - 조회한 기사들(List)을 jsp에 송신하기 위해 JSON String으로 바꿔야 함. 그 과정에서 발생하는 예외
     */
    @GetMapping(value = "/news")
    public String main(
            @RequestParam(value = "date" , required = false) String date,
            @RequestParam(value = "base" , required = false) String base,
            Model model, @PageableDefault(size = NewsUtils.SUMMARY_PAGE_SIZE) Pageable pageable) throws DateTimeParseException, JsonProcessingException { 
        
        Boolean isToday = false;
        LocalDate requestDate = null; 
        String view = "news-no";
        String defaultUrl = "";
        int memberId = MemberUtils.getMemberId();
        
        LocalDate today = LocalDate.now();
        
        if (date == null) {
            isToday = true;
            requestDate = today;
        }
        else {
            requestDate = LocalDate.parse(date);
            isToday = today.isEqual(requestDate);       
        }
        
        /*
         *  오늘 뉴스 페이지와 뉴스 요약 페이지 내 공통 요소
         *  NAV_ARTICLES: 내비게이션에 표시할 기사 개수
         *  requestDateStr: 화면에 출력할 뉴스 날짜. 내비게이션에서 선택한 날짜를 확인할 때도 사용
         */
        String requestDateStr = requestDate.toString(); 
        model.addAttribute("NAV_ARTICLES", NAV_ARTICLES);
        model.addAttribute("requestDateStr", requestDateStr);
        
        // 오늘 뉴스 페이지
        if(isToday) {
           String location = videoService.findLocationByCreatedAt(requestDate.toString());
           
           // 영상이 생성되었을 때만 출처 기사 목록 조회
           if(location != null) {
               view = "news-today";
               
               defaultUrl = "/news-today-api";
               
               // 한 페이지 당 출력하는 기사 개수를 VIDEO_PAGE_SIZE 개로 조정하기 위함
               PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), NewsUtils.VIDEO_PAGE_SIZE);               
               Page<ArticleScrapVO> page = articleService.findArticleWithScrap(requestDateStr, memberId, pageRequest);
               List<ArticleScrapVO> articles = page.getContent();
          
               if (!articles.isEmpty()) {           

                   ObjectMapper mapper = new ObjectMapper();                   
                   String articlesJson = mapper.writeValueAsString(articles);

                   model.addAttribute("articles", articlesJson);
                   model.addAllAttributes(NewsUtils.getPaginationData(page, NewsUtils.DEFAULT_PAGE_UNIT, defaultUrl));
               } 
               
               // 영상은 있으나 기사가 전부 삭제되었을 경우를 대비해서 location 추가만 따로 배치
               model.addAttribute("location", location);
               model.addAttribute("date", requestDateStr);
           }
        }
        
        // 뉴스 요약 페이지
        else { 
          
            defaultUrl = "/news?date="+ requestDateStr; 
            
            Page<ArticleScrapVO> page = articleService.findArticleWithScrap(requestDateStr, memberId, pageable);
            List<ArticleScrapVO> articles = page.getContent();
                              
            // 해당 일자에 기사가 있을 경우 뉴스 요약 페이지 (news-summary) 로 이동
            if (!articles.isEmpty()) {                
                model.addAttribute("articles", articles);
                model.addAttribute("date", requestDateStr);
                model.addAllAttributes(NewsUtils.getPaginationData(page, NewsUtils.DEFAULT_PAGE_UNIT, defaultUrl));
                
                view = "news-summary";
            
            }
            
            /* 
             * 내비게이션 내 날짯값을 갱신할지 판단  (layout/nav.jsp 참고)
             * 갱신 조건
             *   1. 사용자가 url 쿼리스트링에서 'base' 키를 지웠을 때
             *   2. 다음 주 뉴스 조회 버튼 누를 때: diffFromBase > 0
             *   3. 이전 주 뉴스 조회 버튼 누를 때: diffFromBase <= -1*NAV_ARTICLES
             * 
             * cf. 오늘 뉴스 요청 시에는 nav.jsp에 lastDate를 보내지 않음 -> nav.jsp에서 lastDate를 오늘로 설정
             */                    
            if (base != null) {
                LocalDate baseDate =  LocalDate.parse(base);
                
                long diffFromBase = baseDate.until(requestDate, ChronoUnit.DAYS);
                
                if(diffFromBase > 0 || diffFromBase <= -1*NAV_ARTICLES) {
                     model.addAttribute("lastDate", requestDate);
                 }
                 else {
                     model.addAttribute("lastDate", baseDate);
                }               
            } 
            
            // 사용자가 url 쿼리스트링에서 'base' 키를 지웠을 때
            else {
                model.addAttribute("lastDate", requestDate);
            }   

        } // 뉴스 요약 페이지 끝  
        
        return view;
    } 
        
    /**
     * 오늘 뉴스 페이지 영상 하단에 있는 기사 요약 정보를 페이지 리로딩 없이 조회하기 위한 메소드
     * 
     * @param date: 사용자가 보낸 날짜 정보. 사용자가 23:59:59에 조회하는 등 오늘 날짜가 변경되면서 발생할 수 있는 문제를 예방하기 위함 
     * @param requestPage: 사용자가 요청한 페이지
     * @param pageable: '@GetMapping(value = "/news")'의 핸들러 메소드 참고
     * @return 사용자가 요청한 조건에 알맞은 기사와 페이지네이션 정보
     * @throws DateTimeParseException - '@GetMapping(value = "/news")'의 핸들러 메소드 참고
     * @throws JsonProcessingException - '@GetMapping(value = "/news")'의 핸들러 메소드 참고
     */
    @GetMapping(value = "/news-today-api")
    @ResponseBody
    public HashMap<String, Object> getNextArticles(
            @RequestParam(value = "date" , required = false) String date,
            @RequestParam(value = "page" , required = false) Integer requestPage,
            @PageableDefault(size = NewsUtils.VIDEO_PAGE_SIZE) Pageable pageable) throws DateTimeParseException, JsonProcessingException { 
    
        LocalDate requestDate = LocalDate.parse(date); 
        String requestDateStr = requestDate.toString(); 
        int memberId = MemberUtils.getMemberId();
              
        // NewsUtils.getPaginationData 참고
        String defaultUrl = "/news-today-api?date="+ requestDateStr;
        
        // 사용자가 요청한 기사 및 페이지네이션 정보
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
            
        Page<ArticleScrapVO> page = articleService.findArticleWithScrap(requestDateStr, memberId, pageable);
        List<ArticleScrapVO> articles = page.getContent();
          
        // JavaScript에서는 Java의 객체를 사용할 수 없으므로 출력할 기사들을 JSON String 형태로 전환
        ObjectMapper mapper = new ObjectMapper();
        String articlesJson = mapper.writeValueAsString(articles);
                
        resultMap.putAll(NewsUtils.getPaginationData(page, NewsUtils.DEFAULT_PAGE_UNIT, defaultUrl));
        resultMap.put("articles", articlesJson);
        
        // pagination-api.jsp 내 페이지 이동 버튼에서 사용할 날짯값 전송
        resultMap.put("date", requestDateStr);

        return resultMap;    
    }
}
