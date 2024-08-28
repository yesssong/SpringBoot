<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  #box{
     width: 600px;
     margin: auto;
     margin-top: 100px;
  }
  
  textarea {
	  width: 100%; /* 부모의 100% */
	  min-height: 100px;
	  resize: none;
  }
</style>

<script type="text/javascript">
  
  function send(f){ // f = this.form
	 
	  let name    = f.name.value.trim();
      let content = f.content.value.trim();
      let pwd     = f.pwd.value.trim();
      
      if(name==''){
    	  alert('이름을 입력하세요!');
    	  f.name.value='';
    	  f.name.focus();
    	  return;
      }
      
      if(content==''){
    	  alert('내용을 입력하세요!');
    	  f.content.value='';
    	  f.content.focus();
    	  return;
      }
      
      if(pwd==''){
    	  alert('비밀번호를 입력하세요!');
    	  f.pwd.value='';
    	  f.pwd.focus();
    	  return;
      }
      
      
      //수정확인
      if(confirm("정말 수정하시겠습니까?")==false) return;
            
      
      f.action = "modify.do";//  VisitModifyAction
      f.submit(); //전송     
	  
	  
  }

</script>


</head>
<body>
<form>
    <input type="hidden"  name="idx"  value="${ vo.idx }">
	<div id="box">
		<div class="panel panel-primary">
			<div class="panel-heading"><h4>방명록 수정하기</h4></div>
			<div class="panel-body">
			   <table class="table">
			       <tr>
			          <th width="20%"><label>작성자</label></th>
			          <td><input class="form-control" name="name"  value="${ vo.name }"></td>
			       </tr>
			       <tr>
			          <th><label>내용</label></th>
			          <td>
			             <textarea class="form-control" name="content" >${ vo.content }</textarea>
			          </td>
			       </tr>
			       <tr>
			          <th><label>비밀번호</label></th>
			          <td><input class="form-control" type="password" name="pwd" value="${ vo.pwd }"></td>
			       </tr>
			       
			       <tr>
			          <td colspan="2" align="center">
			             <input class="btn btn-primary" type="button" value="수정하기" 
			                    onclick="send(this.form);">
			             <input class="btn btn-success" type="button" value="목록보기" 
			                    onclick="location.href='list.do'">
			          </td>
			       </tr>
			   </table>
			</div>
		</div>
	</div>
</form>
</body>
</html>