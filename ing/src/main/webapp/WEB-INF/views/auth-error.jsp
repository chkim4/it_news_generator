<!-- 
    인증이 되지 않은 상태에서 에러 발생 시 출력하는 화면 (헤더와 내비게이션이 없음)
    
    만약 Spring Security에서 보낸 에러 문구를 확인하고 싶으면 아래 코드를 추가하자
    최상단: <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <body> 태그 내: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
-->



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>인증 오류 페이지</title>
    <link rel="stylesheet" href="css/layout/error-component.css">

</head>
<body>
  <head>
    
</head>

<body id = "page-top">  
<% 
    String msg = (String) request.getAttribute("msg");
    
    if (msg == null) {
        msg = "죄송합니다. <br> 인증 과정에 문제가 발생했습니다.";
    }
%>

    <div id="wrapper">
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <jsp:include page = "layout/error-component.jsp">
                    <jsp:param name="msg" value= "<%= msg %>" />
                </jsp:include>
            <div class = "error-msg-div">
                <a href = "/login">로그인 페이지로 이동</a>
            </div>
            </div>
        </div>
    </div> 
</body>
</html>