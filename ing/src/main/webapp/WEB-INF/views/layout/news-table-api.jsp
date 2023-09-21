<!-- 
    오늘 뉴스 페이지에서 사용하는 기사 목록 테이블 (JavaScript로 생성)
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jstl/core_rt" %>

<head>  
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

<script src="js/common-scrap.js"></script>
<script src="js/pagination-api.js" data-insertScrap = insertScrap data-deleteScrap = deleteScrap></script>
<script>
	$(document).ready(getTableData(<%=request.getAttribute("articles")%>)); 
</script>