<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<div>
		<h1>자유게시판</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./main.ino">리스트로</a>
	</div>
	<hr style="width: 600px">
	
	<form action="./freeBoardInsertPro.ino">
		<div style="float: left;">
			<div style="width: 150px; float: left;">이름 :</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="name" value="${freeBoardDto.name }" /></div>
		</div>
		
		<div style="float: left;">
			<div style="width: 150px; float: left;">제목 :</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${freeBoardDto.title }" readonly/></div>
		</div>
		
		<div style="float: left;">
			<div style="width: 150px; float: left;">작성날자</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${freeBoardDto.regdate }" readonly/></div>
		</div>
		
		<div style="float: left;">
			<div style="width: 150px; float: left;">내용 :</div>
			<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65" >${freeBoardDto.content }</textarea></div>
		</div>
		
		<div align="right">
		<input type="button" value="수정" onclick="updateBtn();">
		<input type="button" value="삭제" onclick="deleteBtn();">
		<input type="button" value="취소" onclick="location.href='./main.ino';">
		<input type="hidden" value="${param.num}" name="bno">
		&nbsp;&nbsp;&nbsp;
		</div>
	
	</form>
	
	
	<script>
		function deleteBtn() {
		    var result = confirm("삭제 하시겠습니까?");
		    var bno = $("input[name=bno]").val();
		    console.log(bno);
		    if (result) {
		    	location.href="freeBoardDeletePro.ino?num="+bno		   
		    } else {
		   
		    } 
		}
		
		function updateBtn() {		 
		    var bno = $("input[name=bno]").val();
		    var bcon = $("textarea[name=content]").val();
		    console.log(bno);
		    console.log(bcon);
		   
		    location.href="freeBoardUpdatePro.ino?num="+bno+"&content="+bcon		   
		}
		
	</script>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>