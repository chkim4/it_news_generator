<!-- 
    마이 페이지
    스크랩한 기사 출력 등
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이 페이지</title>
	<%@ include file = "./cdn/lib-axios.jsp" %>
	<link rel="stylesheet" href="css/news-summary.css">
</head>
<body id = "page-top">
    <%@ include file = "layout/header.jsp" %>

    <div id="wrapper">
        <%@ include file = "layout/nav.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">      
                <h1 class="h3 mb-4 text-gray-800">스크랩한 기사 목록</h1>
                <!-- 기사 요약 테이블 -->           
                <%@ include file = "layout/mypage-table.jsp" %>
                
                <!-- 화면 최하단 페이지 이동 버튼 -->
                <%@ include file = "layout/mypage-pagination.jsp" %>
             </div>
            </div>
        </div>
        <%@ include file = "layout/footer.jsp" %>    
</body>
</html>