package com.ing.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ing.entity.Scrap;

/**
 * 
 * 스크랩 관련 공통 기능 모음
 *
 */
public final class ScrapUtils {
    
    /**
     * memberId와 articleId 값을 가진 Scrap 객체 생성
     * @param articleId: 스크랩하려는 기사의 PK 값 (cf. memberId는 Spring Security의 ContextHolder 활용)
     * @return Scrap 객체
     */
    public static Scrap getScrapInstance(int articleId) {
        
        int memberId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        
        Scrap scrap = Scrap.builder()
                            .memberId(memberId)
                            .articleId(articleId)
                            .build();
        
        return scrap;
    }
}
