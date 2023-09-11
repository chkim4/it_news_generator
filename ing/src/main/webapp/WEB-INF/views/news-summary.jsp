<!-- 뉴스 요약 페이지: 오늘 날짜가 아닌 뉴스를 요약 형태로 출력 -->

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
                <h1 class="h3 mb-4 text-gray-800">기사 목록</h1>
                <!--  테이블 시작 -->           
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>요약</th>
                                <th>기사 원본</th>
                                <th>스크랩</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var= "article" items = "${articles}">
                             <tr>
                                 <td>${article.ord}</td>
                                 <td>${article.summary}</td>
                                 <td><input type = "button" class = "btn" value = "기사 원본" onclick="window.open('${article.url}');"></td>
                                 <td><input type = "button" class = "btn" value = "스크랩"></td>
                             </tr>      
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                <!-- 테이블 끝 -->
                
                <%
                    String defaultUrl = "/news?date="+ request.getParameter("date");
                %>                
                <!-- 화면 최하단 페이지 이동 버튼 -->
                <jsp:include page = "layout/pagination.jsp">
                    <jsp:param name = "defaultUrl" value = "<%=defaultUrl%>"/>       
                </jsp:include>
             </div>
            </div>
        </div>
        <%@ include file = "layout/footer.jsp" %>    
</body>
</html>