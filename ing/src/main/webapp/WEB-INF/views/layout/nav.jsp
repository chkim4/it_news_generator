<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>


<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <title>navigation</title>
    
    <link rel="stylesheet" href="css/layout/nav.css">
  
</head>
<%   
    
    // 최하단에 표시할 날짜. 서버에서 데이터를 보내지 않았을 경우, 오늘 날짜가 최하단에 위치
    LocalDate lastDate = request.getAttribute("lastDate") != null ? 
        (LocalDate) request.getAttribute("lastDate") : LocalDate.now();

    // 표시할 기사 개수. 서버에서 보내도록 설정했으나 혹시 몰라서 null에 대한 처리를 수행함
    Integer NAV_ARTICLES = request.getAttribute("NAV_ARTICLES") != null ? 
            (Integer) request.getAttribute("NAV_ARTICLES") : 7;
       
    Boolean isSelectDone = false;
  
 %>
	 <ul class="navbar-nav sidebar sidebar-dark accordion">
	   <li class="nav-item">
	     <!-- base: 이번 요청으로 내비게이션 내 날짯값을 갱신할지 판단할 때 사용. ArticleController 참고 -->
	     <a class="nav-link" href = "/news?date=<%=lastDate.plusDays(-1*NAV_ARTICLES)%>&base=<%=lastDate%>">
           <span class = "nav-text">
             <img class="week-move-icon" src = "/img/arrow-up-circle.svg">
           </span>
         </a>        
       </li>
    <%
    for (int i= (NAV_ARTICLES-1) ; i >= 0; i--) {
        String date = lastDate.plusDays(-1*i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(); 
        
        String linkClassName = "nav-link"; 
        String textClassName = "nav-text";
        Boolean isSelected = false;
        
        if (!isSelectDone){
            isSelected = date.equals((String) request.getAttribute("requestDateStr"));
            
            if(isSelected){
                linkClassName = "nav-link white"; 
                textClassName = "nav-text theme"; 
                isSelectDone = true;
            }
        }
       
    %>
	      <li class="nav-item">
	          <a class = "<%=linkClassName%>" href="/news?date=<%=date%>&base=<%=lastDate%>">
	              <span class = "<%=textClassName%>">
	                  <%=date%>
	              </span>
	          </a>
	      </li>
	 <%}%>
       <li class="nav-item">
         <a class="nav-link" href = "/news?date=<%=lastDate.plusDays(NAV_ARTICLES)%>&base=<%=lastDate%>">
           <span class = "nav-text">
             <img class="week-move-icon" src = "/img/arrow-down-circle.svg">
           </span>
         </a>        
       </li>
	 </ul>