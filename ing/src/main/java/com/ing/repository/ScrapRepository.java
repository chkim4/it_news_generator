package com.ing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ing.entity.Article;
import com.ing.entity.Member;
import com.ing.entity.Scrap;
import com.ing.vo.ArticleScrapVO;

/**
 * Scrap 테이블 관련 JPaRepository
 */
@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    
    // 삭제한 레코드 개수 반환
    public Long deleteScrapByMemberAndArticle(Member member, Article article);
    
    /**
     * 사용자가 스크랩한 전체 기사 조회를 위해 Article과 Scrap Entity 조인
     * @param memberId: 요청한 사용자를 식별하는 PK
     * @param pageable: pagination 설정을 위한 객체
     * @return 사용자가 스크랩한 기사 목록
     */
    @Query(value = "Select a.articleId AS articleId, a.ord AS ord, a.summary AS summary, " + 
            "a.url AS url, s.member.memberId AS memberId " +
            "FROM scrap s " + 
            "LEFT JOIN FETCH article a " + 
            "ON s.article.articleId = a.articleId " + 
            "WHERE s.member.memberId = :memberId " + 
            "ORDER BY a.createdAt DESC, a.ord ASC"
            )
    @Transactional(readOnly = true)
    public Page<ArticleScrapVO> findScrapList(@Param(value = "memberId") Integer memberId, 
            Pageable pageable);

}