<!-- 
     에러 페이지
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>에러 페이지</title>
</head>
<body id = "page-top">
    <%@ include file = "layout/header.jsp" %>
    
    <div id="wrapper">
        <%@ include file = "layout/nav.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <%@ include file = "layout/error-component.jsp" %>
                <div class = "error-msg-div">
                    <a href = "/news">오늘 뉴스로 이동</a>
                </div>
            </div>
        </div>
    </div> 
    <br/>
    
    <%@ include file = "layout/footer.jsp" %>
    
  
</body>
</html>