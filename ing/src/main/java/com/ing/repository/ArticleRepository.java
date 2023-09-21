package com.ing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ing.entity.Article;
import com.ing.vo.ArticleScrapVO;


/**
 * Article 테이블 관련 JPaRepository
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    
    
    
      /**
       * 사용자가 요청한 날짜의 기사 목록과 기사별 스크랩 여부 확인을 위해 Article과 Scrap Entity 조인
       * @param date: 사용자가 요청한 날짜
       * @param memberId: 요청한 사용자를 식별하는 PK
       * @param pageable: pagination 설정을 위한 객체
       * @return 사용자가 요청한 날짜의 기사 목록과 사용자가 기사별 스크랩을 했는지 식별하는 정보 <br>
       *        (memberId가 null -> 스크랩X / memberId가 null이 아님 -> 스크랩O)
       */
      @Query(value = "Select  a.articleId AS articleId, a.ord AS ord, a.summary AS summary, " + 
                     "a.url AS url, s.member.memberId AS memberId " +
                     "FROM article a "+ 
                     "LEFT JOIN FETCH scrap s " + 
                     "ON a = s.article AND s.member.memberId = :memberId " +
                     "WHERE TRUNC(a.createdAt) = to_date(:requestDate, 'YYYY-MM-DD') " + 
                     "ORDER BY ord" 
      )
    @Transactional(readOnly = true)
    public Page<ArticleScrapVO> findArticleWithScrap(@Param(value = "requestDate") String date, 
                                                     @Param(value = "memberId") Integer memberId, 
                                                     Pageable pageable);


    public Article findByArticleId(Integer articleId);

}