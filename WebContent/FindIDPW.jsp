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
<title>ID/PW 찾기</title>
</head>
<%
	String flag = (String) request.getAttribute("isFind");
	String description = (String) request.getAttribute("Description");
	if(flag == null){
		flag = "none";
	}
%>
<body>
	<script>
		var flag = "<%=flag%>";
		var description = "<%=description%>";
		if(flag == "none"){}
		else{
			if (flag == "true") {
				alert(description);
			}
		}
	</script>
	<form class="px-4 py-3" action="member" method="post" encType="UTF-8">
		<input type="hidden" name="flag" value="findID">
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">이메일</label> 
			<input type="email" class="form-control" id="exampleDropdownFormPassword1" placeholder="" name="email" required>
		</div>
		<button type="submit" class="btn btn-primary">id 찾기</button>
	</form>
	<form class="px-4 py-3" action="member" method="post" encType="UTF-8">
		<input type="hidden" name="flag" value="findPW">
		<div class="form-group">
			<label for="exampleDropdownFormEmail1">생년월일</label> 
			<input type="date"
				class="form-control" id="exampleDropdownFormEmail1" placeholder=""
				name="birthDay" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">이메일</label> 
			<input
				type="email" class="form-control"
				id="exampleDropdownFormPassword1" placeholder="" name="email" required>
		</div>
		<button type="submit" class="btn btn-primary">pw 찾기</button>
	</form>
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