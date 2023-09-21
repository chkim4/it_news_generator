<!-- 
    화면 상단 헤더
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
    <title>헤더</title>
    
    <%@ include file = "../cdn/lib-common.jsp" %>
    <link rel="stylesheet" href="css/layout/header.css">

</head>
	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow" style="height: 10vh;">
	   
	   <!-- 로고 -->
        <a class = "logo-wrapper"  href = "/news">
            <img class="logo" src = "img/logo-black.png" alt = "logo"/>
        </a>
	
	    <!-- 우측 요소들 -->
	    <ul class="navbar-nav ml-auto">		 
	        <!-- 사용자 정보 드롭다운 -->
	        <li class="nav-item dropdown no-arrow">
	            <!-- 사용자 정보 보기 드롭 다운 메뉴 -->
	            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
	                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                <span class="mr-2 d-none d-lg-inline text-gray-600 large">계정</span>
	            </a>
	            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
	                aria-labelledby="userDropdown">
	                <a class="dropdown-item" href="/mypage">
	                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-600"></i>
	                                                 마이페이지
	                </a>
	                <div class="dropdown-divider"></div>
	                <a class="dropdown-item" onclick="javascript:document.getElementById('logout-form').submit();" data-toggle="modal" data-target="#logoutModal">
	                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-600"></i>
	                                                로그아웃
	                </a>
	                <form id = "logout-form" action = "/logout" method = "POST"></form>
	            </div>
	        </li>
	    </ul>
	</nav>
</html>