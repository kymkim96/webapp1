<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
						<h5>연결 테스트</h5>
						<div>
							<a class="btn btn-success btn-sm" href="javascript:conntest()">연결 테스트</a>
							<script>
								const conntest = function() {
									$.ajax({
										url: "conntest",
										method: 'get',
										success: function(data) {
											$("#result1").html(data);
										},
									});
								};
							</script>
							<div id="result1"></div>
						</div>
					</div>
					
					<div class="sector">
						<h5>JSON 응답 받기</h5>
						<div>
							<a class="btn btn-success btn-sm" href="javascript:jsonResponse1()">객체 {} 받기</a>
							<script>
								const jsonResponse1 = function() {
									$.ajax({
										url: "jsonresponse1",
										method: 'get',
										success: function(data) {
											console.log(data);
											$("#name").html(data.name);
											$("#age").html(data.age);
											$("#carkind").html(data.car.kind);
											$("#carcolor").html(data.car.color);
											
											for (var h of data.hobby) {
												$("#hobby").append(h + ",");
											}
										},
									});
								};
							</script>
							<div id="result2">
								<div id="name"></div>
								<div id="age"></div>
								<div id="carkind"></div>
								<div id="carcolor"></div>
								<div id="hobby"></div>
							</div>
							
							<a class="btn btn-success btn-sm" href="javascript:jsonResponse2()">배열 {} 받기</a>
							<script>
								function jsonResponse2() {
									$.ajax({
										url: "jsonresponse2",
										method: 'get',
										success: function(data) {
											console.log(data);
											for (var i=0; i<data.length; i++) {
												$("#result3 tbody").append("<tr>");
												$("#result3 tbody").append("<td>" + data[i].bno + "</td>");
												$("#result3 tbody").append("<td>" + data[i].btitle + "</td>");
												$("#result3 tbody").append("<td>" + data[i].bwriter + "</td>");
												$("#result3 tbody").append("</tr>");
											}
										}
									});
								}
							</script>
							<div id="result3">
								<table class="table">
									<thead>
										<tr>
											<td>번호</td>
											<td>제목</td>
											<td>글쓴이</td>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					<div class="sector">
						<h5>PK로 검색하기</h5>
						<div>
							<a class="btn btn-success btn-sm" href="javascript:func1(100)">사번이 100번인 사원의 정보 가져오기</a>
							<a class="btn btn-success btn-sm" href="javascript:func1(101)">사번이 101번인 사원의 정보 가져오기</a>
							<div id="result4">
								사번: <span id="eno"></span> </br>
								이름: <span id="first"></span> </br>
								성: <span id="last"></span> </br>
							</div>
							<script>
								function func1(eid) {
									$.ajax({
										url: 'employee',
										method: 'get',
										data: {
											employee_id: eid,
										},
										success: function(data) {
											console.log(data);
											$("#eno").html(data.eno);
											$("#first").html(data.first);
											$("#last").html(data.last);
										}
									});
								}
							</script>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</body>
</html>

















