<!-- 뉴스 요약 페이지, 뉴스 영상 페이지 하단 출처 등에서 사용하는 뉴스 모음 테이블 (페이지 전환 시 리로드 O)-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jstl/core_rt" %>

<head>  
    <link rel="stylesheet" href="css/layout/news-table.css">
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
        <tbody>
            <c:forEach var= "article" items = "${articles}" varStatus= "status">
             <tr id = '${"article"}${status.index}'>
                 <td>${status.count}</td>
                 <td>${article.summary}</td>
                 <td><input type = "button" class = "btn" value = "기사 원본" onclick="window.open('${article.url}');"></td>
                 <td><input type = "button" class = "btn delete-btn" value = "삭제" onclick="deleteScrap('${article.articleId}', 'article${status.index}')"></td>
             </tr>      
            </c:forEach> 
        </tbody>
    </table>
</div>
<script src="js/mypage-table.js"></script>

