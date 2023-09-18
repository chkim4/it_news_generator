package com.ing.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ing.entity.Scrap;
import com.ing.service.ArticleService;
import com.ing.service.MemberService;

/**
 * 
 * 스크랩 관련 공통 기능 모음
 *
 */
@Component
public final class ScrapUtils {
    
    @Autowired
    MemberService memberService;

    @Autowired
    ArticleService articleService;

    
    /**
     * memberId와 articleId 값을 가진 Scrap 객체 생성
     * @param articleId: 스크랩하려는 기사의 PK 값 (cf. memberId는 Spring Security의 ContextHolder 활용)
     * @return Scrap 객체
     */
    public Scrap getScrapInstance(int articleId) {
        
        int memberId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        
        Scrap scrap = Scrap.builder()
                            .member(memberService.findByMemberId(memberId))
                            .article(articleService.findByArticleId(articleId))
                            .build();
        
        return scrap;
    }
}
