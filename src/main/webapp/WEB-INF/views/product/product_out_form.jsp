<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function send_output(f){
	var name=f.name.value;
	var cnt =f.cnt.value;
	
	if(name==''){
		alert('출고상품명을 입력하세요');
		f.name.focus();
		return;
	}
	
	var number_filter=/^[0-9]+$/;
	if(number_filter.test(cnt)==false){
		alert('수량은 숫자만 입력하세요');
		f.cnt.focus();
		return;
	}
	
	f.action="insert_out.do";	// ProductController 가서 출고 작업
	f.submit();
	
}

</script>

</head>
<body>
<form>
	<table>
	   <caption>::::출고등록::::</caption>
	   <tr>
	   	  <th>상품명</th>
	   	  <td><input name="name"></td>
	   </tr>
	   <tr>
	   	  <th>수량</th>
	   	  <td><input name="cnt"></td>
	   </tr>
	   <tr>
	   	  <td colspan="2" align="center">
	   	      <input type="button" value="출고" 
	   	             onclick="send_output(this.form);">
	   	      <input type="reset"  value="취소">
	   	  </td>
	   </tr>
	   
	</table>
</form>

</body>
</html>