package com.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.Scrap;

/**
 * Scrap 테이블 관련 JPaRepository
 */
@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    
    // 삭제한 레코드 개수 반환
    public Long deleteByMemberIdAndArticleId(int memberId, int articleId);

}