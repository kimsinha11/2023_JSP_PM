<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 리스트 </title>
</head>
<body>
<h1>게시물 리스트</h1>

<ul>
<%for(int i = articleRows.size()-1; i>=0; i--) {%>
<li>번호 : <%=articleRows.get(i).get("id")%>번 <br /> 작성 날짜 : <%=articleRows.get(i).get("regDate")%> <br /> 제목 :  <%=articleRows.get(i).get("title")%> <br /> 내용 : <%=articleRows.get(i).get("body")%></li>
<%} %>
</ul>
</body>
</html>
