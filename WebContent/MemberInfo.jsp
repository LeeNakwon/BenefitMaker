<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.java.DTO.User"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<%
	User user = (User) request.getAttribute("user");
	String ID, NickName, Birthday, Email;
	long Capital1st, CapitalCurrent;
	int RankPer, RankAmt;
	ID = user.getID();
	Capital1st = user.getCapital1st();
	CapitalCurrent = user.getCapitalCurrent();
	NickName = user.getNickName();
	RankPer = user.getRankPer();
	RankAmt = user.getRankAmt();
	Birthday = user.getBirthday();
	Email = user.getEmail();
%>
<body>
	<div class="container"
		style="display: inline-block; text-align: center;">
		<h2>회원 정보</h2>
		<table class="table" style="border: hidden">
			<tbody>
				<tr>
					<td style="width: 30%">ID</td>
					<td style="width: 80%"><%=ID%></td>
				</tr>
				<tr>
					<td style="width: 30%">월 초 자본금</td>
					<td style="width: 80%"><%=Capital1st%> 원</td>
				</tr>
				<tr>
					<td style="width: 30%">현재 자본금</td>
					<td style="width: 80%"><%=CapitalCurrent%> 원</td>
				</tr>
				<tr>
					<td style="width: 30%">닉네임</td>
					<td style="width: 80%"><%=NickName%></td>
				</tr>
				<tr>
					<td style="width: 30%">저번달 수익률 순위</td>
					<td style="width: 80%"><%=RankPer%> 위</td>
				</tr>
				<tr>
					<td style="width: 30%">저번달 수익금 순위</td>
					<td style="width: 80%"><%=RankAmt%> 위</td>
				</tr>
				<tr>
					<td style="width: 30%">생년월일</td>
					<td style="width: 80%"><%=Birthday%></td>
				</tr>
				<tr>
					<td style="width: 30%">이메일</td>
					<td style="width: 80%"><%=Email%></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td>
						<form method="post" action="member">
							<input type="hidden" name="flag" value="deleteYes"> <input
								type="button" id="delete" class="btn btn-primary" value="회원탈퇴">
							<button type="submit" id="submit"></button>
						</form>
					</td>
					<td>
						<form method="get" action="View">
							<button type="submit" class="btn btn-primary">모의 투자 정보</button>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form method="post" action="member">
							<input type="hidden" name="flag" value="update">
							<button type="submit" class="btn btn-primary">회원 정보 수정</button>
						</form>
					</td>
					<td></td>
				</tr>
			</tbody>
		</table>
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
		$('#submit').hide();
		$('#delete').click(function(){
			var isTrue = confirm('삭제하면 복구할 수 없습니다.\n삭제하시겠습니까?');
			if(isTrue == true){
				$('#submit').click();
			}
		});
	</script>
	<script>
	$(window).on("unload",function Close(){
		$.ajax({
			url: "<%=request.getContextPath()%>/member",
				type : 'POST',
				data : {
					flag : 'logout',
				},
				async : true,
				cache : false,
				success : function(data) {
				},// 요청 완료 시
				error : function(msg, error) {
					alert(error);
				}// 요청 실패.
			});
		})
	</script>
</body>
</html>