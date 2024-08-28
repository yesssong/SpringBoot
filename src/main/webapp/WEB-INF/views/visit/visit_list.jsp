<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap 3.x -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- DatePicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>


<!-- SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style type="text/css">
  #box{
     width: 600px;
     height: auto;
     margin: auto;
     margin-top: 50px;
  }
  
  #title{
     text-align: center;
     font-size: 26px;
     font-weight: bold;
     color: #3366ff;
     text-shadow: 1px 1px 2px black;
  }
  
  .content{
     border: 1px solid #cccccc;
     min-height: 60px;
     padding: 5px;
     box-shadow: -1px -1px 1px gray;
  }
  
  .regdate{
     padding: 5px;
  }
  
  .pwd{
     padding: 5px;
  }
  
  #search_box{
  	 margin-bottom: 10px; 
  	 margin-top: 35px;
  }
  #myDatePicker{
     display: none;
  }
</style>

<script type="text/javascript">

  function del(f){

	  const c_pwd = f.c_pwd.value.trim();  //입력비번
	  const idx   = f.idx.value;           //게시물번호
	  
	  if(c_pwd==''){
		  //alert('삭제할 게시물 비밀번호를 입력하세요!');
		  
			Swal.fire({
				title : "삭제",
				html : "<font color=red><h4>삭제할 비밀번호를 입력하세요</h4></font>",
				icon : "info"
			});
			
			f.c_pwd.value = '';
			f.c_pwd.focus();
			return;
		}

		//Ajax로 비밀번호가 맞는지 여부 확인
		$.ajax({
			url : "check_pwd.do", // VisitCheckPwdAction
			data : {
				"idx" : idx,
				"c_pwd" : c_pwd
			}, // parameter => check_pwd.do?idx=3&c_pwd=1234
			dataType : "json", // 결과값의 Type
			success : function(res_data) {
				// res_data = {"result": true} or {"result": false}

				if (res_data.result == false) {
					alert('비밀번호가 틀립니다!');
					return;
				}

//--------------------------------------------------------------------------				
				Swal.fire({
					  title: "정말 삭제하시겠습니까?",
					  text: "삭제버튼을 누르면 삭제됩니다",
					  icon: "warning",
					  showCancelButton: true,
					  confirmButtonColor: "#3085d6",
					  cancelButtonColor: "#d33",
					  confirmButtonText: "삭제",
					  cancelButtonText:"취소"
					}).then((result) => {
					  if (result.isConfirmed) {
						//삭제
						location.href = "delete.do?idx=" + idx;
					  }
					});
//---------------------------------------------------------------------------				
				

			},
			error : function(error) {
				alert(error.responseText);
			}
		});

	}//end-del

	function modify_form(f) {

		const c_pwd = f.c_pwd.value.trim(); //입력비번
		const idx = f.idx.value; //게시물번호

		if (c_pwd == '') {
			alert('수정할 게시물 비밀번호를 입력하세요!');
			f.c_pwd.value = '';
			f.c_pwd.focus();
			return;
		}

		//Ajax로 비밀번호가 맞는지 여부 확인
		$.ajax({
			url : "check_pwd.do", // VisitCheckPwdAction
			data : {
				"idx" : idx,
				"c_pwd" : c_pwd
			}, // parameter => check_pwd.do?idx=3&c_pwd=1234
			dataType : "json", // 결과값의 Type
			success : function(res_data) {
				// res_data = {"result": true} or {"result": false}

				if (res_data.result == false) {
					alert('비밀번호가 틀립니다!');
					return;
				}

				// 자바스크립트의 페이지호출기능을 이용해서 수정할 idx만 넘긴다 
				location.href = "modify_form.do?idx=" + idx;

			},
			error : function(error) {
				alert(error.responseText);
			}
		});

	}//end-modify_form
	
	
	
	//검색기능
	function find(f){
		
		let search      = $("#search").val();
		let search_text = "";
		
		if(search=='regdate')
            search_text = $("#myDatePicker").val().trim();    
		else
			search_text = $("#search_text").val().trim();
		
		//전체검색이 아닌데 검색어가 비어있으면
		if(search !='all' &&  search_text==''){
			alert("검색어를 입력하세요");
			
			if(search=='regdate'){
				$("#myDatePicker").val("");
				$("#myDatePicker").focus();
			}else{
				$("#search_text").val("");
				$("#search_text").focus();
			}
			
			
			return;
		}
		
		
		
		f.action="list.do";//생략되면 자신에게 전송
		f.submit();
		
	}
</script>

<!-- ------- [초기화]------ -->

<script type="text/javascript">

  $(document).ready(function(){
	  
	  //전체검색이 아니면..
	  	  
	  if("${ (not empty param.search)  }" =="true"){
		  
	      $("#search").val("${ param.search }");
	      
	  }
	  
	  //전체보기면 검색어 지우기
	  if("${ param.search eq 'all'}" == "true"){
		  $("#search_text").val("");
	  }
	
	  
	  if("${ param.search eq 'regdate'}" == "true"){
		 
		  $("#search_text").hide();	 
		  $("#search_text").attr("disabled",true);	 
		  	  
		  $("#myDatePicker").show();
		  $("#myDatePicker").attr("disabled",false);
		  
	  }else{
		  
		  $("#search_text").show();	  
		  $("#search_text").attr("disabled",false);
		  
		  $("#myDatePicker").hide();
		  $("#myDatePicker").attr("disabled",true);
	  }
	  
	  
	  
	  $("#search").change(function(){
		
		  if($(this).val()=="regdate"){
			  
			  $("#search_text").hide();	 
			  $("#search_text").attr("disabled",true);	 
			  	  
			  $("#myDatePicker").show();
			  $("#myDatePicker").val("");
			  $("#myDatePicker").attr("disabled",false);
			  			  
		  }else{
			  
			  $("#search_text").show();
			  $("#search_text").val("");
			  $("#search_text").attr("disabled",false);
			  
			  $("#myDatePicker").hide();
			  $("#myDatePicker").attr("disabled",true);
			  
		  }
		  
	  });
	  
	  
	  //날짜 DatePicker
	  $("#myDatePicker").datepicker({
		  showButtonPanel: true,
		  currentText: '오늘날짜',
		  closeText:'닫기',
		  dateFormat:'yy-mm-dd'
	  });
	  
  });

</script>





</head>
<body>
<div id="box">
   <h1 id="title">::::방명록::::</h1>
 
   <!-- 검색메뉴 -->
   <div id="search_box" class="row">
     <form class="form-inline">  
     <div class="col-sm-9" >
	       <select id="search" name="search" class="form-control">
	           <option value="all">전체보기</option>
	           <option value="name">이름</option>
	           <option value="content">내용</option>
	           <option value="name_content">이름+내용</option>
	           <option value="regdate">작성날짜(YYYY-MM-DD)</option>
	       </select>
	       
	       
	       <input class="form-control" id="search_text" size="15"  name="search_text"  value="${ param.search_text }">
	       
	       <input class="form-control" id="myDatePicker" size="15" name="search_text"  value="${ param.search_text }"  disabled="disabled">
	       
	       
	       <input class="btn btn-primary"  type="button"  value="검색"  onclick="find(this.form);">
	  </div>
	  <div class="col-sm-3" style="text-align:right;">    
	       <input type="button" class="btn btn-primary" 
	                    onclick="location.href='insert_form.do'" value="글올리기">
	  </div>                  
     </form>  
   </div>
   
   
   
   
   <!-- 방명록 내용 -->
   <!-- for(VisitVo vo : list ) 동일함 -->
   <c:forEach var="vo"  items="${ list }">
		<div class="panel panel-primary">
			<div class="panel-heading"><h4><b>${ vo.name }</b>님의 글</h4></div>
			<div class="panel-body">
			   <div class="content">
			       ${ vo.content }
			   </div>
			   <div class="regdate">
			      <label>작성일자 : </label> ${ fn:substring(vo.regdate,0,16) }(${ vo.ip })
			   </div>
			   <div class="pwd">
			      <form class="form-inline">
			          <!--  delete from visit where idx=2 -->
				      <input type="hidden"  name="idx"  value="${ vo.idx }">
				      <label>비밀번호(${ vo.pwd }) : </label><input class="form-control" type="password" name="c_pwd">
				                        <input class="btn btn-success" type="button" value="수정"
				                               onclick="modify_form(this.form);"> 
				                        <input class="btn btn-danger"  type="button" value="삭제"
				                               onclick="del(this.form);">
			      </form>                   
			   </div>
			</div>
		</div>
	</c:forEach>
   
   
</div>
</body>
</html>