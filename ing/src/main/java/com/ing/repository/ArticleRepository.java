package com.ing.repository;

import java.time.LocalDate;
import java.util.List;

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

    
    @Query(value = "SELECT summary " + 
                   "FROM article " + 
                   "WHERE TRUNC(created_at) = to_date(:requestDate, 'YYYY-MM-DD') " + 
                   "ORDER BY ord", nativeQuery = true)
    public List<ArticleSummary> findAllByCreatedAtOrderByOrd(@Param(value = "requestDate") String date);
}
//@Query(value = "SELECT summary " + 
//        "FROM article " + 
//        "WHERE TRUNC(created_at) = to_date(:requestDate, 'YYYY-MM-DD') " + 
//        "ORDER BY ord", nativeQuery = true)
//public List<ArticleSummary> findAllByCreatedAtOrderByOrd(@Param(value = "requestDate") LocalDate date);
//}