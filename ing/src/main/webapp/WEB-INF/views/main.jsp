<!-- 
    로그인 혹은 회원 가입 후 띄울 페이지.
    현재는 테스트 용도로만 쓰임.

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
                <h1 class="h3 mb-4 text-gray-800">Main Page</h1>
            </div>
        </div>
    </div>
    
    <%@ include file = "layout/footer.jsp" %>
    
</body>
</html>