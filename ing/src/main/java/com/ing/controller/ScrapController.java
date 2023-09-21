/**
 * Scrap 테이블 관련 Controller
 */

package com.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ing.entity.Scrap;
import com.ing.service.ArticleService;
import com.ing.service.MemberService;
import com.ing.service.ScrapService;
import com.ing.utils.ScrapUtils;

@Controller
public class ScrapController {
    
    @Autowired
    ScrapService scrapService;

    @Autowired
    MemberService memberService;

    @Autowired
    ArticleService articleService;
    
    @Autowired
    ScrapUtils scrapUtils;

    /**
     * 스크랩 추가 
     * 
     * @param articleId: 스크랩하려는 기사의 PK 값 (cf. memberId는 Spring Security의 ContextHolder를 통해 식별)
     * @return 스크랩 저장 여부
     * @throws NumberFormatException - memberId, articleId를 String에서 Integer로 변환하는 중에 발생할 수 있는 예외
     */
    @PostMapping(value = "/scrap")
    @ResponseBody
    public Boolean insertScrap(
            @RequestParam(value = "articleId" , required = false) Integer articleId) 
            throws NumberFormatException{
        
        return scrapService.insertScrap(scrapUtils.getScrapInstance(articleId));
    }

    /**
     * 스크랩 삭제 
     * 
     * @param articleId: 스크랩하려는 기사의 PK 값 (cf. memberId는 Spring Security의 ContextHolder를 통해 식별)
     * @return 스크랩 삭제 여부
     * @throws NumberFormatException - memberId, articleId를 String에서 Integer로 변환하는 중에 발생할 수 있는 예외
     */
    @DeleteMapping(value = "/scrap")
    @ResponseBody
    public Boolean deleteScrap(
            @RequestParam(value = "articleId" , required = false) Integer articleId)
                    throws NumberFormatException{
        
        Scrap scrap = scrapUtils.getScrapInstance(articleId);
        
        return scrapService.deleteScrapByMemberAndArticle(scrap.getMember(), scrap.getArticle());       
    }
}
