<%@ page contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>

<jsp:useBean id="table_Svc" scope="page" class="db.table.model.TableService" />

<!DOCTYPE html>
<html>
<head>
<title>DB Selector</title>

<%
// 		out.println((List)getServletContext().getAttribute("dbStr"));
%>

</head>
<body>
<h1>DB Administrator </h1>
<h2>Please Select Databases</h2>

<form action="${pageContext.request.contextPath}/DbSelect">
  <label for="myDB">Choose a db:</label>
  <select name="db" onchange="myFunction()">
  	<c:forEach var="db"  items="${dbStr}" >
	<option value="${db}">${db}</option>
   </c:forEach>
    
  </select>
  <br><br>
  <input type="hidden" name="action" value="dbSelect">
  <input type="Submit" value="Submit">
</form>

<HR>
<H2><span style="color:red;font-size:12pt" >${dbUrl}</span></H2>
<H2>Please Select Tables</H2>
<%
// List Tables if db url session retrieved. otherwise,
%>
	<table border="1">
<!-- 	<thead> -->
<%-- 	<c:forEach items="${table_Svc.tableHeader}" var="header_name"> --%>
<%-- 		<th>${header_name}</th> --%>
<%--  	</c:forEach> --%>
<!-- 	</thead> -->
	<tbody>
	<% 
	// foreach input default ()
	// foreach input parameter : dburl - TBD
	%>
		<c:forEach items="${table_Svc.allTables}" var="table">
		
		<tr>
		<td>${table}</td>
		
		</tr>
		
		</c:forEach>
	</tbody>
	</table>

<H2>Row Data</H2>
<%
// List data if the table is selected
%>

</body>
</html>