<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    
</head>
<body>
    <h1>WELCOME BACK TO SPRING BOOT!!!!!</h1>
	<button text="click" onclick="test()"></button>

	
	
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