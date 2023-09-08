<!-- 
     화면에 띄울 뉴스가 없을 때 출력하는 페이지
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
                <%@ include file = "layout/error-component.jsp" %>
            </div>
        </div>
    </div> 
    <%@ include file = "layout/footer.jsp" %>
  
</body>
</html>