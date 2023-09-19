package com.ing.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.service.ArticleService;
import com.ing.service.VideoService;
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
     * @throws JsonProcessingException - 조회한 기사들(List)을 jsp에 송신하기 위해 JSON String으로 바꿔야 함. 그 과정에서 발생하는 예외
     */
    @GetMapping(value = "/news")
    public String main(
            @RequestParam(value = "date" , required = false) String date,
            Model model, @PageableDefault(size = NewsUtils.SUMMARY_PAGE_SIZE) Pageable pageable) throws DateTimeParseException, JsonProcessingException { 
        
        Boolean isToday = false;
        LocalDate requestDate = null; 
        String view = "news-no";
        String defaultUrl = "";
        int memberId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        
        if (date == null) {
            isToday = true;
            requestDate = LocalDate.now();
        }
        else {
            requestDate = LocalDate.parse(date);
            isToday = LocalDate.now().isEqual(requestDate);       
        }
        
        // 뉴스 영상 페이지와 뉴스 요약 페이지 내 공통 요소
        String requestDateStr = requestDate.toString(); 
            
        // 뉴스 영상 페이지 (오늘 뉴스)
        if(isToday) {
           String location = videoService.findLocationByCreatedAt(requestDate.toString());
           
           // 오늘 뉴스가 생성되었을 때만 뉴스 영상 페이지 조회
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
        } 
        return view;
    } 
        
    /**
     * 뉴스 영상 페이지 영상 하단에 있는 기사 요약 정보를 페이지 리로딩 없이 조회하기 위한 메소드
     * 
     * @param date: 클라이언트가 보낸 날짜 정보. 클라이언트가 23:59:59에 조회하는 등 오늘 날짜가 변경되면서 발생할 수 있는 문제를 예방하기 위함 
     * @param requestPage: 클라이언트가 요청한 페이지
     * @param pageable: '@GetMapping(value = "/news")'의 핸들러 메소드 참고
     * @return 클라이언트가 요청한 조건에 알맞은 기사와 페이지네이션 정보
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
        int memberId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
              
        // NewsUtils.getPaginationData 참고
        String defaultUrl = "/news-today-api?date="+ requestDateStr;
        
        // 클라이언트가 요청한 기사 및 페이지네이션 정보
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
            
        Page<ArticleScrapVO> page = articleService.findArticleWithScrap(requestDateStr, memberId, pageable);
        List<ArticleScrapVO> articles = page.getContent();
          
        // JavaScript에서는 Java의 객체를 사용할 수 없으므로 출력할 기사들을 JSON String 형태로 전환
        ObjectMapper mapper = new ObjectMapper();
        String articlesJson = mapper.writeValueAsString(articles);
                
        resultMap.putAll(NewsUtils.getPaginationData(page, NewsUtils.DEFAULT_PAGE_UNIT, defaultUrl));
        resultMap.put("articles", articlesJson);
        
        // 클라이언트에 저장 후, 클라이언트가 요청할 때 참고할 정보
        resultMap.put("date", requestDateStr);

        return resultMap;    
    }
}
