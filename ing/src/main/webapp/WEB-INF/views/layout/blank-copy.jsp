<!-- 
     header, nav, footer를 포함하는 빈 페이지
          복사하여 사용하기 위함

 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>test</title>
</head>
<body id = "page-top">
    <%@ include file = "layout/header.jsp" %>
    
    <div id="wrapper">
        <%@ include file = "layout/nav.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">      
                <h1 class="h3 mb-4 text-gray-800">Blank Page</h1>
            </div>
        </div>
    </div>
    
    <%@ include file = "layout/footer.jsp" %>
    
</body>
</html>