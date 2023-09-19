package com.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ing.entity.Article;
import com.ing.entity.Member;
import com.ing.entity.Scrap;
import com.ing.repository.ScrapRepository;
import com.ing.vo.ArticleScrapVO;

/**
 * Scrap 테이블 관련 Service
 */
@Service
public class ScrapService {
    
    @Autowired
    ScrapRepository scrapRepository;
  
    /**
     * 스크랩 정보 저장
     * 
     * @param scrap: Scrap 테이블에 저장하고자 하는 Entity (PK가 없어야 insert Id 체크 없이 INSERT 연산 수행)
     * @return 스크랩 저장 성공 여부 (save 메소드 실행 완료 후 반환되는 Entity에는 PK 값이 지정되는 점을 활용)
     */
    public Boolean insertScrap(Scrap scrap){       
        scrapRepository.save(scrap);
      
        return scrap.getScrapId() != null;
    }  
    
    /**
     * 스크랩 정보 삭제
     * @param memberId: 스크랩의 memberId 필드 값
     * @param articleId: 스크랩의 articleId 필드 값
     * @return 스크랩 삭제 성공 여부 (delete 메소드 완료 후 반환하는 삭제된 레코드 개수 활용)
     */
    @Transactional
    public Boolean deleteScrapByMemberAndArticle(Member member, Article article){       
        
        Long deletedRecord = scrapRepository.deleteScrapByMemberAndArticle(member, article);
        
        return deletedRecord > 0;
    } 
    
    public Page<ArticleScrapVO> findScrapList(Integer memberId, Pageable pageable){
        
        return scrapRepository.findScrapList(memberId, pageable);
    }   
    
}
