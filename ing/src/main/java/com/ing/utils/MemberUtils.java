package com.ing.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * 계정 관련 공통 기능 모음
 *
 */
public class MemberUtils {
    
    /**
     * 현재 사용자의 memberId 값 반환
     * @return 현재 사용자의 memberId
     */
    public static int getMemberId() {        
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());  
    } 
}
