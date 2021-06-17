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
<title>회원 정보 수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<%
	String flag = (String) request.getAttribute("isUpdated");
	String description = (String) request.getAttribute("Description");
	String id = (String) request.getAttribute("id");
	String pw = (String) request.getAttribute("pw");
	String nickName = (String) request.getAttribute("nickName");
	String birth = (String) request.getAttribute("birth");
	String email = (String) request.getAttribute("email");
%>
<body>
	<script>
		var flag = "<%=flag%>";
		var description = "<%=description%>";
		if (flag == "false") {
			alert(description);
		}
	</script>
	<form class="px-4 py-3" action="member" method="post" encType="UTF-8">
		<input type="hidden" name="flag" value="updateOk">
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">기존 비밀번호</label> <input
				type="password" class="form-control"
				id="exampleDropdownFormPassword1" placeholder="" name="check"
				required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">변경할 비밀번호</label> <input
				type="password" class="form-control"
				id="pw1" placeholder="" value=<%=pw%>
				name="pw1" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">비밀번호 확인</label> <input
				type="password" class="form-control"
				id="pw2" placeholder="" value=<%=pw%>
				 required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">변경할 닉네임</label> <input
				type="text" class="form-control" id="exampleDropdownFormPassword1"
				placeholder="" value=<%=nickName%> name="nickname" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">변경할 생년월일</label> <input
				type="date" class="form-control" id="exampleDropdownFormPassword1"
				placeholder="" value=<%=birth%> name="birth" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">변경할 이메일</label> <input
				type="email" class="form-control" id="exampleDropdownFormPassword1"
				placeholder="" value=<%=email%> name="email" required>
		</div>
		<input type="button" id="button" class="btn btn-primary" value="수정하기">
		<button type="submit" id ="submit"></button>
	</form>
	<div class="dropdown-divider"></div>


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
		$('#submit').hide();
		$('#button').click(function(){
			var pw1 = $('#pw1').text();
			var pw2 = $('#pw2').text();
			if(pw1 == pw2){
				$('#submit').click();
			}
			else{
				alert('변경할 비밀번호가 일치하지 않습니다.');
			}
		});
	</script>
</body>
</html>