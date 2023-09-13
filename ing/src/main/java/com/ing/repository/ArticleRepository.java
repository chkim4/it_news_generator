package com.ing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ing.entity.Article;
import com.ing.entity.ArticleSummary;

/**
 * Article 테이블 관련 JPaRepository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    
    @Query(value = "SELECT article_id, summary, url, ord " + 
                   "FROM article " + 
                   "WHERE TRUNC(created_at) = to_date(:requestDate, 'YYYY-MM-DD') " + 
                   "ORDER BY ord", 
           countQuery = "SELECT article_id, summary, url, ord " + 
                        "FROM article " + 
                        "WHERE TRUNC(created_at) = to_date(:requestDate, 'YYYY-MM-DD')",         
           nativeQuery = true)
    public Page<ArticleSummary> findAllByCreatedAtOrderByOrd(@Param(value = "requestDate") String date,  Pageable pageable);

}