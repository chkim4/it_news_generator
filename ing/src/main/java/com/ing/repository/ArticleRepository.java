package com.ing.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.Article;

/**
 * Article 테이블 관련 JPaRepository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public List<Article> findAllByCreatedAt(LocalDate date);
}