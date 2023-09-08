package com.ing.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.entity.Article;
import com.ing.repository.ArticleRepository;

/**
 * Article 테이블 관련 Service
 */
@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;
    
     
    public List<Article> findAllByCreatedAt(LocalDate date) {
        
        return articleRepository.findAllByCreatedAt(date);
    } 
    
       
}
