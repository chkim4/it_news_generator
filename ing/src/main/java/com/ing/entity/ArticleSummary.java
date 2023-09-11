package com.ing.entity;


/**
 * 뉴스 요약 페이지에서 사용할 Article 테이블 내 필드 일부
 */

public interface ArticleSummary {
    public Integer getArticleId();
    public Integer getOrd();
    public String getSummary(); 
    public String getUrl(); 
}
