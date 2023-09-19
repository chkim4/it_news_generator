///**
// * jsp 파일 동작 확인 등 테스트를 위한 Controller
// */
//
//package com.ing.controller;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.ing.service.ArticleService;
//import com.ing.vo.ArticleScrapVO_Class;
//
//@Controller
//public class HomeController {
//
//    @Autowired
//    ArticleService articleService;
//    
//   
//    @GetMapping(value = "/test")
//    public String test(@PageableDefault(size = 10) Pageable pageable) {
//        
//        List<ArticleScrapVO_Class> result= articleService.findArticleWithScrap(LocalDate.of(2023,9,10).toString(), 9, pageable).getContent();
//        
//        System.out.println("size: " + result.size()); 
//        System.out.println("get(0).getArticleId(): " + result.get(0).getArticleId()); 
//        System.out.println("get(0).getMemberId()): " + result.get(0).getMemberId()); //이거 null 여부로 스크랩 여부 확인 가능
//        
//        return "home";
//    }
//
//    @GetMapping(value = "/home")
//    public String home() {
//        System.out.println("home"); 
//        return "home";
//    }
//    
//    @GetMapping(value = "/blank")
//    public String blank() {
//        System.out.println("blank"); 
//        return "layout/blank";
//    }
//
//    @GetMapping(value = "/header")
//    public String header() {
//        System.out.println("header");
//        return "layout/header";
//    }
//
//    @GetMapping(value = "/nav")
//    public String nav() {
//        System.out.println("nav");
//        
//        return "layout/nav";
//    }
//
//    @GetMapping(value = "/footer")
//    public String footer() {
//        System.out.println("footer");
//                
//        return "layout/footer";
//    }
//
//}
