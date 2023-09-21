package com.ing.vo;

/**
 * <pre>
 * Article과 Scrap Entity의 조인 결과를 저장할 때 사용하는 클래스. 
 * 다음 두 목적으로 사용함
 *   1. 특정 날짜의 기사 목록을 요청한 사용자가 기사별로 스크랩했는 지 확인 
 *   2. 사용자가 스크랩한 기사 목록 조회 
 * </pre>
 */
public interface ArticleScrapVO {
    
    public Integer getArticleId();
    public Integer getOrd();
    public String getSummary(); 
    public String getUrl(); 
    public Integer getMemberId();
    
}
