<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		.page-item{list-style: none; float: left; padding: 6px;}
	</style>
</head>
<body>
	<div>
		<h1>자료실</h1>
	</div>
	<div style="width:650px;" align="right">
		<form action="./upload.ino" id="frm">
			<select name="searchType" id="searchType">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="keyword" id="keyword" value="${pagination.keyword}">
			<button type="submit" id="btn">검색</button>
		</form>
		
		<a href="./fileBoardInsert.ino">글쓰기</a>
	</div>
	<hr style="width: 600px">
	
	<c:forEach items="${fileBoardList }" var="dto">
			<div style="width: 50px; float: left;">${dto.num }</div>	
			<div style="width: 300px; float: left;"><a href="./fileBoardDetail.ino?num=${dto.num }">${dto.title }</a></div>
			<div style="width: 150px; float: left;">${dto.name }</div>
			<div style="width: 150px; float: left;">${dto.regdate }</div> 
		<hr style="width: 600px">
	</c:forEach>
	
	<div>
		<nav aria-label="Page navigation example" style="margin: auto;">
			<ul class="pagination justify-content-center">
				<c:if test="${pagination.prev}">
					<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.currPageNo}', '${pagination.range}', '${pagination.pageSize}')">이전</a></li>
				</c:if>
				
				<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
					<li class="page-item <c:out value="${pagination.currPageNo == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}')"> ${idx} </a></li>
				</c:forEach>
				
				<c:if test="${pagination.next}">
					<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.currPageNo}', '${pagination.range}', '${pagination.pageSize}')" >다음</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
    
	<script>    
	//이전 버튼
	function fn_prev(currPageNo, range, pageSize) {
	
		var currPageNo = (range - 1) * pageSize;
		var range = range - 1;
	
		var url = "./upload.ino";
		url = url + "?currPageNo=" + currPageNo;
		url = url + "&range=" + range;
		url = url + "&searchType=" + $("#searchType").val();
		url = url + "&keyword=" + $("#keyword").val();
		location.href = url;
	
	}
	//페이지 번호
	function fn_pagination(currPageNo, range) {
	
		var url = "./upload.ino";
		url = url + "?currPageNo=" + currPageNo;
		url = url + "&range=" + range;
		url = url + "&searchType=" + $("#searchType").val();
		url = url + "&keyword=" + $("#keyword").val();
		console.log($("#keyword").val());
		location.href = url;	
	
	}
	//다음 버튼
	function fn_next(currPageNo, range, pageSize) {
	
		var currPageNo = (range * pageSize) + 1;
		var range = parseInt(range) + 1;	
	
		var url = "./upload.ino";
		url = url + "?currPageNo=" + currPageNo;
		url = url + "&range=" + range;
		url = url + "&searchType=" + $("#searchType").val();
		url = url + "&keyword=" + $("#keyword").val();
		location.href = url;
	}
	
	</script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>
</html>