<!-- 뉴스 요약 페이지: 오늘 날짜가 아닌 뉴스를 요약 형태로 출력 (페이지 전환 시 리로드 O)-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>뉴스 요약 페이지</title>
	<link rel="stylesheet" href="css/news-summary.css">
</head>
<body id = "page-top">
    <%@ include file = "layout/header.jsp" %>

    <div id="wrapper">
        <%@ include file = "layout/nav.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">      
                <h1 class="h3 mb-4 text-gray-800">뉴스 요약</h1>
                <!-- 기사 요약 테이블 -->           
                <%@ include file = "layout/news-table.jsp" %>
                
                <!-- 화면 최하단 페이지 이동 버튼 -->
                <%@ include file = "layout/pagination.jsp" %>
             </div>
            </div>
        </div>
        <%@ include file = "layout/footer.jsp" %>    
</body>
</html>