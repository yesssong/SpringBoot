<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h3>업로드 결과</h3>
    제목:${title}<br>
    <!-- List 출력 -->
     <c:forEach var="filename" items="${filename_list}">
         <img src="images/${filename}" width="200">
     </c:forEach>

    <br>
    <br>
    
    <a href="input3.html">다시하기</a>
</body>
</html>