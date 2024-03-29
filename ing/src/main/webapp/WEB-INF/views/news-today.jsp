<!-- 
오늘 뉴스 페이지: 오늘 뉴스 영상과 출처 기사 목록을 출력하는 페이지 (페이지 전환 시 리로드 X)
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>뉴스 영상 페이지</title>
	<%@ include file = "./cdn/lib-axios.jsp" %>
	<link rel="stylesheet" href="css/news-today.css">
</head>
<body id = "page-top">
    <%@ include file = "layout/header.jsp" %>
    <div id="wrapper">
        <%@ include file = "layout/nav.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">      
                <h1 class="h3 mb-4 text-gray-800">오늘 뉴스 영상</h1>
                <div class = "video-wrapper">
                  <video controls preload = "auto">
	                   <source src="${location}" type = "video/mp4">
	              </video>
	            </div>
	            <div class = "source-wrapper">
                    <!-- 기사 요약 테이블 -->
                    <div id = "table-wrapper">
                        <%@ include file = "layout/news-table-api.jsp" %>
                    </div>  
                    <br/>
                     <!-- 화면 최하단 페이지 이동 버튼 -->                    
                    <%@ include file = "layout/pagination-api.jsp" %>                   
                </div>
	        </div>
        </div>
    </div>
    
    <%@ include file = "layout/footer.jsp" %>
    
</body>
</html>