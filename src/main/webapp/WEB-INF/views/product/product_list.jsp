<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap 3.x -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
   img{
      width: 100px;
      height: 80px;
   }
</style>

</head>
<body>
   <table class="table">
      <tr>
         <th>번호</th>
         <th>이미지</th>
         <th width="50%">상품명</th>
         <th>최저가(원)</th>
         <th>최고가(원)</th>
         <th>판매몰</th>
      </tr>
      
      <!--   for(ProductVo vo : list )동일 -->
      <c:forEach var="vo"  items="${ list }" varStatus="i">
         <tr>
            <td>${ i.count }</td>
            <td>
               <img src="${ vo.image }">
            </td>
            <td>
               <a href="${ vo.link }" >${ vo.title }</a>
            </td>
            <td><fmt:formatNumber value="${ vo.lprice }"/></td>
            <td><fmt:formatNumber value="${ vo.hprice }"/></td>
            <td>${ vo.mallName }</td>
         </tr>
      </c:forEach>
      
   </table>
</body>
</html>