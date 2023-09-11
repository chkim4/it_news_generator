package com.ing.utils;

import java.util.HashMap;

import org.springframework.data.domain.Page;

/**
 * 
 * 뉴스 관련 공통 기능 모음
 *
 */
public final class NewsUtils {

    /**
     * <pre>
     * Pagination에 필요한 데이터를 HashMap으로 반환 <br>
     * 1. 세트: 페이지 하단에 적힌 페이지 이동 버튼의 모음
     *    ex. 한 화면에 10개 페이지 이동 버튼 출력 시: 1~10 페이지 이동 버튼들이 한 세트, 11~20 페이지 이동 버튼이 다른 세트 
     * 
     * 2. Spring Pagination은 0페이지부터 시작. 그런데 화면 하단에는 1페이지부터 기재하므로 둘의 차이가 있음.
     *    또한 여기 기재된 모든 페이지는 Spring Pagination 값을 적용        
     *       
     * 3. 아래는 반환하는 HashMap의 key: value (key: String / value: Object)
     *    firstPage: 현재 화면의 세트 내 첫 페이지 (int)
     *        ex. 현재 페이지: 3, 세트: 1~10페이지일 경우 1에 해당. Spring Pagination 기준이므로 실제로는 0
     *      
     *    currentPage: 사용자가 요청한 페이지 (int)
     *        ex. 사용자가  3 페이지를 요청할 때 3에 해당. Spring Pagination 기준이므로 실제로는 2
     *  
     *    lastPage: 현재 세트 내 마지막 페이지 (int)
     *        ex. 현재 페이지: 3, 세트: 1~10페이지일 경우 10에 해당. Spring Pagination 기준이므로 실제로는 9
     *      
     *    isNextSetExists: 현재 세트 기준으로 다음 세트 존재 여부 (Boolean)
     *        화면에 다음 세트로 이동하는 버튼 출력 여부 판단 시 활용
     * </pre>
     * @param <T>
     * 
     * @param page: pagination에 필요한 데이터를 추출하고자 하는 page 객체 
     * @param pageUnit: 한 페이지에 출력할 기사 개수
     * @return pagination에 필요한 데이터를 저장한 HashMap
     */
    public static <T> HashMap<String, Object> getPaginationData(Page<T> page, int pageUnit){
        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();
        Boolean isNextSetExists = true;
                        
        int lastPage = (currentPage + pageUnit)/pageUnit*pageUnit;
        
        if (lastPage >= totalPages){
           lastPage = totalPages;
           isNextSetExists = false;                  
        }
       
       int firstPage = currentPage/pageUnit*pageUnit;
       
       HashMap<String, Object> resultMap = new HashMap<String, Object>();
       
       resultMap.put("firstPage", firstPage);
       resultMap.put("currentPage", page.getNumber());
       resultMap.put("lastPage", lastPage);
       resultMap.put("isNextSetExists", isNextSetExists);
       resultMap.put("totalPages", page.getTotalPages());
         
       return resultMap;
    }
}
