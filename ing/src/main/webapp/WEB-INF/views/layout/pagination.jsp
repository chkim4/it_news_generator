 <!-- 화면 최하단 페이지 이동 버튼 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 
    defaultUrl: 페이지 클릭 시 이동할 URL 중 page 쿼리스트링만 제외
        ex. "/news?date=2017-01-13&page=7"로 이동하고자 하는 경우
            defaultUrl = "/news?date=2017-01-13"으로 할당해야 함
    
      나머지는 NewsUtils.java 파일 내 getPaginationData 메소드에 정리되어 있으므로 참고
 -->

<head>
    <link rel="stylesheet" href="css/layout/pagination.css">
</head>

<%
	int firstPage = (Integer) request.getAttribute("firstPage");
	int currentPage = (Integer) request.getAttribute("currentPage");
	int lastPage = (Integer) request.getAttribute("lastPage");
	String defaultUrl = request.getParameter("defaultUrl");
	Boolean isNextSetExists = (Boolean) request.getAttribute("isNextSetExists");
%>

 <!-- 페이지 이동 버튼 -->
<nav>
  <ul class="pagination">
  <!-- 이전 페이지 세트 이동 버튼. (11 ~ 20) -> (1 ~ 10) 
                 현재 페이지가 첫 세트에 있다면 (1 ~ 10 중 하나) 비활성화   -->
 <%
   if (firstPage > 0){
       String previousPageURL = defaultUrl + "&page=" + (firstPage-1); %>
         
     <li class="page-item">
         <a class="page-link" href="<%=previousPageURL%>" aria-label="Previous">
           <span aria-hidden="true">&laquo;</span>
           <span class="sr-only">Previous</span>
         </a>
     </li>              
 <% }%> 
 
 <!-- 현재 페이지 세트 내 페이지 출력  -->
 <%                        
    for(int p=firstPage; p<lastPage; p++) {
        String currentPageURL = defaultUrl + "&page=" + p; %>
        <li class="page-item"><a class="page-link" href="<%=currentPageURL%>"><%=p+1%></a></li>
 <%}%>
 
  <!-- 다음 페이지 세트 이동 버튼. (1 ~ 10) -> (11 ~ 20) 
                         현재 페이지가 마지막 세트에 있다면 비활성화   -->
 <%                     
     if(isNextSetExists) {
         String nextPageURL = defaultUrl + "&page=" + lastPage;
 %>
       <li class="page-item">
          <a class="page-link" href="<%=nextPageURL%>" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
              <span class="sr-only">Next</span>
          </a>
       </li>                    
 <%}%>                       
  </ul>
</nav> 