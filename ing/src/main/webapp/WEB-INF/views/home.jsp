<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>테스트용 페이지</title>
    <%@ include file = "cdn/lib-common.jsp" %>
    
</head>
<body>
    <h1>WELCOME BACK TO SPRING BOOT!!!!!</h1>
	<button onclick="test()">axios 테스트</button>

<script>
    function test() {
        axios.get('http://localhost:8081/home')
        .then(function (response) {
            alert("Axios Completed");
        })
        .catch(function (e) {
            alert(e);
        });
    }
</script>	
</body>
</html>