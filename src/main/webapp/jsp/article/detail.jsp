<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
</head>
<body style= "text-align: center;">
		
<style type="text/css">
	a:hover {
	background-color: gray;
	color: red;
}
</style>

	<h1>게시물 상세보기</h1>
	<div>
	번호 : 
	<%=articleRow.get("id")%>
	</div>
	<div>
	작성날짜 : 
	<%=articleRow.get("regDate") %>
	</div>
	<div>
	제목 :
	<%=articleRow.get("title") %>
	</div>
	<div>
	내용 : 
	<%=articleRow.get("body") %>
	<div><a style = "text-decoration: none;" href= "modify?id=<%=articleRow.get("id")%>">수정</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a style = "text-decoration: none;" href= "dodelete?id=<%=articleRow.get("id")%>">삭제</a></div>
	</div>
	
	<div>
		<a style = "text-decoration: none;" style="color: green" href="list">리스트로 돌아가기</a>
	</div>


</body>
</html>