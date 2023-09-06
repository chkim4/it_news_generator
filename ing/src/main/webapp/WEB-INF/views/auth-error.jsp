<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증 오류 페이지</title>
</head>
<body>
  <div class="error">
    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
  </div>
</body>
</html>