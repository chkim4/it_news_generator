package com.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.entity.ArticleSummary;
import com.ing.repository.ArticleRepository;

/**
 * Article 테이블 관련 Service
 */
@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;
    
     
    public Page<ArticleSummary> findAllByCreatedAtOrderByOrd(String date, Pageable pagable) {
        
        return articleRepository.findAllByCreatedAtOrderByOrd(date, pagable);
    } 
    
       
}
