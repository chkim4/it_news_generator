<!-- 
   기사 정보 출력 화면 (페이지 로딩 없이 출력하기 위해 제작)     
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jstl/core_rt" %>

<head>  
    <%@ include file = "../cdn/lib-pagination-api.jsp" %>
    <link rel="stylesheet" href="css/layout/pagination.css">
</head>

<div class="table-responsive">
    <table class="table table-bordered" id="dataTable">
        <thead>
            <tr>
                <th>No.</th>
                <th>요약</th>
                <th>기사 원본</th>
                <th>스크랩</th>
            </tr>
        </thead>
        <tbody id = "table-body">           
        </tbody>
    </table>
</div>

<script src="js/pagination-api.js"></script>
<script>
	$(document).ready(getTableData(<%=request.getAttribute("articles")%>)); 
</script>