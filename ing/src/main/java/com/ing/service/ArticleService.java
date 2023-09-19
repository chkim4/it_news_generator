package com.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ing.entity.Article;
import com.ing.repository.ArticleRepository;
import com.ing.vo.ArticleScrapVO;

/**
 * Article 테이블 관련 Service
 */
@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;
    
     
    public Page<ArticleScrapVO>findArticleWithScrap(String date, Integer memberId, Pageable pageable) {
        
        return articleRepository.findArticleWithScrap(date, memberId, pageable);
    }  
    
    public Article findByArticleId(int articleId) {
        return articleRepository.findByArticleId(articleId);
    }
}
