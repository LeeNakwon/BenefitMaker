<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>로그인</title>
</head>
<%
	String flag = (String)request.getAttribute("isLogin");
	String description = (String)request.getAttribute("Description");
%>
<body>
	<script>
		var flag = "<%=flag %>";
		var description = "<%=description%>";
		if(flag == "false"){
			alert(description);
		}
	</script>
	<form class="px-4 py-3" action="member" method="post" encType="UTF-8">
		<input type="hidden" name="flag" value="login">
		<div class="form-group">
			<label for="exampleDropdownFormEmail1">ID</label> <input type="text"
				class="form-control" id="exampleDropdownFormEmail1"
				placeholder="" name="ID" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">PW</label> <input
				type="password" class="form-control"
				id="exampleDropdownFormPassword1" placeholder="" name="PW" required>
		</div>
		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
	<div class="dropdown-divider"></div>
	<a class="dropdown-item" href="http://localhost:8080/BenefitMaker/Register.jsp">회원가입</a>
	<a class="dropdown-item" href="http://localhost:8080/BenefitMaker/FindIDPW.jsp">ID/PW 찾기</a>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>