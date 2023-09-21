<!-- 
    오늘 뉴스 페이지 최하단에 있는 페이지 이동 버튼 (누를 시 새로운 데이터 요청. 페이지 전환 시 리로드 X. JavaScript로 생성)     
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>  
    <link rel="stylesheet" href="css/layout/pagination.css">
</head>

<!-- 페이지 이동 버튼 -->
<nav>
  <ul id = "pagination" class="pagination"> 
  </ul>
</nav>
 
<script src="js/pagination-api.js"></script>
<script> 

// firstPage와 lastPage를 포함한 페이지 세트 출력.
document.addEventListener("DOMContentLoaded", () => {
  
    const firstPage = Number("${firstPage}");
    const lastPage = Number("${lastPage}");
    const defaultUrl = "${defaultUrl}";
    const isNextSetExists = "${isNextSetExists}" === "true";
    const date = "${date}";
    
    getCurrentSet(firstPage, lastPage, defaultUrl, isNextSetExists, date);
});

</script>