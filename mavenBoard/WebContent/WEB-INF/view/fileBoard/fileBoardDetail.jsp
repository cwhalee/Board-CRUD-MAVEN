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
		<h1>자료실</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./upload.ino">리스트로</a>
	</div>
	<hr style="width: 600px">

	<form action="./fileBoardUpdatePro.ino" method="post" enctype="multipart/form-data">
		<div style="float: left;">
			<div style="width: 150px; float: left;" >이름 : </div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="name" value="${fileBoardDto.name }" readonly/></div>
		</div>
		<div style="float: left;">
			<div style="width: 150px; float: left;">제목 :</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="title"  value="${fileBoardDto.title }" /></div>
		</div>
		<div style="float: left;">
			<div style="width: 150px; float: left;">작성날자</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="regdate"  value="${fileBoardDto.regdate }" readonly/></div>
		</div>
		<div style="float: left;">
			<div style="width: 150px; float: left;">내용 :</div>
			<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65" >${fileBoardDto.content }</textarea></div>
		</div>
		
		<div style="float: left;">
		<c:choose>
		<c:when test="${not empty multiFiles}"> 
  			<c:forEach items="${multiFiles}" var="file">
       			<div ><a style="float: left;" href="filedownload.ino?nfilename=${file.nfilename}&filename=${file.filename}">${file.filename} 다운로드</a> 
       			& <a style="right" href="fileremove.ino?nfilename=${file.nfilename}&num=${param.num}">${file.filename} 파일 삭제</a></div>
			</c:forEach>
  		</c:when>
  		<c:otherwise>
      		<div><input multiple="multiple" type="file" name="file" /></div>
      	</c:otherwise> 
   		</c:choose>
   		</div>
   		
		<div align="right">
			<input type="submit" value="수정">
			<input type="button" value="삭제" onclick="deleteBtn();">
			<input type="button" value="취소" onclick="location.href='./upload.ino';">
			<input type="hidden" value="${param.num}" name="num">
			&nbsp;&nbsp;&nbsp;
		</div>
	</form>
	
	
	<script>
		function deleteBtn() {
		    var result = confirm("삭제 하시겠습니까?");
		    var num = $("input[name=num]").val();
		    console.log(num);
		    if (result) {
		    	location.href="fileBoardDeletePro.ino?num="+num	   
		    } else {
		   
		    } 
		}		
	</script>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>