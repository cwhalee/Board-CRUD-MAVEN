<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>�����Խ���</h1>
	</div>
	<div style="width:650px;" align="right">
		<a href="./main.ino">����Ʈ��</a>
	</div>
	<hr style="width: 600px">
	
	<form action="./freeBoardInsertPro.ino" id="boardform">
		<div style="float: left;">
			<div style="width: 150px; float: left;">�̸� :</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="name"/></div>
		</div>
			
		<div style="float: left;">	
			<div style="width: 150px; float: left;">���� :</div>
			<div style="width: 500px; float: left;" align="left"><input type="text" name="title"/></div>
		</div>
		
		<div style="float: left;">
			<div style="width: 150px; float: left;">���� :</div>
			<div style="width: 500px; float: left;" align="left"><textarea name="content" rows="25" cols="65"  ></textarea></div>
		</div>
			
		<div align="right">
		<input type="submit" value="�۾���">
		<input type="button" value="�ٽþ���" onclick="Reset();">
		<input type="button" value="���" onclick="Confirm();">
		&nbsp;&nbsp;&nbsp;
		</div>
	
	</form>
	
	<script>
		function Confirm() {
		    var result = confirm("��� �Ͻðڽ��ϱ�?");
		    if (result) {
		      
		        history.go(-1); 
		    } else {
		   
		    }
		}
	
		 function Reset(){
		        $('#boardform').load(window.location.href+' #boardform');
//		        $('#targetDiv').load(location.href+' #targetDiv');
		    }
	</script>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>