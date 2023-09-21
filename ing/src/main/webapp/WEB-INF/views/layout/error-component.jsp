<!-- 
     오류 발생 시 화면에 출력하는 공통 요소 ( 'X' 아이콘과 문구) 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
    <link rel="stylesheet" href="css/layout/error-component.css">
</head>


<div class="text-center"> 
    <img class="error-img" src="img/x.png" alt = "에러 아이콘" />
</div>
<div class = "error-msg-div">

   <%
       String msg = request.getParameter("msg");
       
       if (msg == null) {
           msg = "죄송합니다. <br> 문제가 발생했습니다.";
       }
   %>

  <%=msg%>      
</div>