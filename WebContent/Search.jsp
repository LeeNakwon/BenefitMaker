<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.java.DTO.Company"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<title>로그인</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<%
	ArrayList<String> names = (ArrayList<String>) request.getAttribute("names");
	String error = (String)request.getAttribute("error");
	System.out.println(error);
%>

<body>
	<div>
		<p class="text-center"><strong>주가 정보 조회</strong></p>
	</div>
	<div class="container">
		<form class="form-inline" action="Stock" method="get" encType="UTF-8">
			<input id="search_bar" style="width: 85%" class="form-control"
			type="text" placeholder="상장사 이름" aria-label="Search">
			<input type="hidden" name="flag" value="live"> <input
				type="hidden" name="CoName" id="name" value="삼성바이오로직스">
			<button type="submit" onclick="button_click();"
				class="btn btn-primary">주가 정보 조회</button>
		</form>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script>
		function button_click() {
			var name = $('#search_bar').val();
			$('#name').val(name);
		}
	</script>
	<script>
		var error = '<%=error%>';
		if(error == 'true'){
			alert('잘못된 상장사 이름입니다.');
		}
	</script>
	<script>
		$(function() {
			var Strings = new Array();
			<c:forEach var="name" items="${names}">
			Strings.push("${name}")
			</c:forEach>
			$('#search_bar').autocomplete({
				source : Strings
			});
		})
	</script>
</body>
</html>