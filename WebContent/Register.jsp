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
<title>회원가입</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<%
	String flag = (String) request.getAttribute("isRegistered");
	String description = (String) request.getAttribute("Description");
	if(flag == null){
		flag = "none";
		description = "none";
	}
%>
<body>
	<script>
		var flag = "<%=flag%>";//실행 여부
		var description = "<%=description%>";//출력할 메시지(성공 또는 실패)
		if(flag == "none"){
			
		}
		else{
			if (flag == "false") {
				alert(description);
			}
		}
	</script>
	<form class="px-4 py-3" action="member" method="post" encType="UTF-8">
		<input type="hidden" name="flag" value="registerOK">
		<div class="form-group">
			<label for="exampleDropdownFormEmail1">ID</label> <input type="text"
				class="form-control" id="exampleDropdownFormPassword1" placeholder=""
				name="id" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">비밀번호</label> <input
				type="password" class="form-control"
				id="pw1" placeholder="" name="pw" required>
		</div>
		<div class="form-group">
			<label for="exampleDropdownFormPassword1">비밀번호 확인</label> <input
				type="password" class="form-control"
				id="pw2" placeholder="" required>
		</div>
				<div class="form-group">
			<label for="exampleDropdownFormPassword1">닉네임</label> <input
				type="text" class="form-control"
				id="exampleDropdownFormPassword1" placeholder="" name="nickName" required>
		</div>
				<div class="form-group">
			<label for="exampleDropdownFormPassword1">생년월일</label> <input
				type="date" class="form-control"
				id="exampleDropdownFormPassword1" placeholder="" name="birthDay" required>
		</div>
				<div class="form-group">
			<label for="exampleDropdownFormPassword1">이메일</label> <input
				type="email" class="form-control"
				id="exampleDropdownFormPassword1" placeholder="" name="email" required>
		</div>
		<button type="button" class="btn btn-primary" id="button1">회원 가입</button>
		<button type="submit" id="submit"></button>
	</form>
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
		$('#button1').click(function(){
			var pw1 = $('#pw1').val();
			var pw2 = $('#pw2').val();
			console.log(pw1);
			console.log(pw2);
			if(pw1 == pw2){
				$('#submit').click();
			}
			else{
				alert('비밀번호가 일치하지 않습니다.');
			}
		})
	</script>
</body>
</html>