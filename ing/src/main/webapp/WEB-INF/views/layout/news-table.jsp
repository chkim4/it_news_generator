<!-- 
    뉴스 요약 페이지에서 사용하는 기사 목록 테이블 (Scriptlet으로 생성)
-->

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
            <c:forEach var= "article" items = "${articles}">
             <tr>
                 <td>${article.ord}</td>
                 <td>${article.summary}</td>
                 <td><input type = "button" class = "btn" value = "기사 원본" onclick="window.open('${article.url}');"></td>
                 <td>
                 <c:choose>
	               <c:when test = "${article.memberId == null}">
                     <input type = "button" class = "btn" value = "스크랩" onclick="insertScrap('${article.articleId}', this)">	                 
	               </c:when>                    
                   
                   <c:otherwise>
                     <input type = "button" class = "btn delete-btn" value = "삭제" onclick="deleteScrap('${article.articleId}', this)">                   
                   </c:otherwise>               
                 </c:choose>               
                 </td>
             </tr>      
            </c:forEach> 
        </tbody>
    </table>
</div>
<script src="js/common-scrap.js"></script>

