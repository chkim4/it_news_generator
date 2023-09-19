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
	 <ul class="navbar-nav sidebar sidebar-dark accordion">
	     <%
	     LocalDate lastDate = request.getAttribute("lastDate") != null ? 
	             (LocalDate) request.getAttribute("lastDate") : LocalDate.now();
	     
	     for (int i=6; i >= 0; i--) {
	         String date = lastDate.plusDays(-1*i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString(); 
	     %>
	      <li class="nav-item">
	          <a class="nav-link" href="/news?date=<%=date%>">
	              <span class = "nav-text">
	                  <%=date%>
	              </span>
	          </a>
	      </li>
	     <%}%>
	 </ul>