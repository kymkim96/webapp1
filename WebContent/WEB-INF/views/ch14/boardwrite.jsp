<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/main.css">
	</head>
	<body>	
		<div class="wrap">
			<%-- 공통 헤더 --%>
			<%-- <jsp:include page="/WEB-INF/views/include/header.jsp"/> --%>
			<%@ include file="/WEB-INF/views/include/header.jsp" %>
			
			<%-- 내용 --%>
			<div class="mainCenter">
				<%-- 공통 메뉴 --%>
				<jsp:include page="/WEB-INF/views/include/menu.jsp"/>
				
				<div class="content">
					<div class="sector">
						<div class="alert alert-primary" role="alert">
							글쓰기
						</div>
						<form name="boardwriteform" enctype="multipart/form-data" action="boardwrite" method="post">
						  <div class="form-group">
							  <label for="btitle">제목</label>
							  <input type="text" class="form-control" id="btitle" name="btitle">
							  <small class="form-text text-muted">필수 입력 사항입니다.</small>
						  </div>
						  <div class="form-group">
						      <label for="bcontent">내용</label>
						      <textarea class="form-control" id="bcontent" name="bcontent" rows="5" cols="50"></textarea>
						  </div>
						  <div class="form-group">
							  <label for="battach">첨부</label>
							  <input type="file" id="battach" name="battach">
						  </div>
						  <button class="btn btn-primary">저장</button>
						  <a class="btn btn-primary" href="boardlist2">취소</a>
						</form>
					</div>
				</div>
			</div>
			
		</div>
	</body>
</html>

















