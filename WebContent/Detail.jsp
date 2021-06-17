<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.java.DTO.Order"%>
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
	Order order = (Order) request.getAttribute("result");
	String[] types = order.getOrderType().split(",");
	if(types[0].equals("Sell")){
		types[0] = "매입 주문";
	}
	else{
		types[0] = "매도 주문";
	}
	if(types[1].equals("Distinct")){
		types[1] = "지정가 ";
	}
	else if(types[1].equals("Market")){
		types[1] = "시장가 ";
	}
	else{
		types[1] = "조건부 지정가 ";
	}
	boolean isDeletable = (boolean) request.getAttribute("isDeletable");
	String isConcluded = null;
	if(order.getIsConcluded() == 0){
		isConcluded = "진행중";
	}
	else{
		isConcluded = "완료";
	}
%>

<body>
	<div>
		<p class="text-center">
			<strong>주문 상세 정보</strong>
		</p>
	</div>
	<table class="table table-striped"
		style="table-layout: fixed border: hidden">
		<tbody>
			<tr>
				<td scope="col"><strong>주문 등록 시간</strong></td>
				<td><%=order.getDateTime() %></td>
			</tr>
			<tr>
				<td scope="col"><strong>주문 종류</strong></td>
				<td><%=types[1]%><%=types[0]%></td>
			</tr>
			<tr>
				<td scope="col"><strong>상장사 명</strong></td>
				<td><%=order.getCoName()%></td>
			</tr>
			<tr>
				<td scope="col"><strong>주문 수량</strong></td>
				<td><%=order.getOrderedVolume()%></td>
			</tr>
			<tr>
				<td scope="col"><strong>주문 잔량</strong></td>
				<td><%=order.getRemainVolume()%></td>
			</tr>
			<tr>
				<td scope="col"><strong>체결 여부</strong></td>
				<td><%=isConcluded%></td>
			</tr>
			<tr>
				<td scope="col"><strong>매매 가격</strong></td>
				<td><%=order.getPrice()%></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" class="btn btn-primary" value="주문 취소" id="delete">
				</td>
			</tr>
		</tbody>
	</table>
	<form method="get" action="View">
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
		var isDeletable = '<%=isDeletable%>';
		
		if(isDeletable == 'false'){
			$('#delete').hide();
		}
	</script>

	<script>
		$('#submit').hide();
		function back(){
			$('#submit').click();
		}
		$('#delete').click(function(){
			$.ajax({
				url: "<%=request.getContextPath()%>/DeleteOrder",
			    type:'POST',
			    data: {
			    	OrderID:'<%=order.getOrderID()%>'
					},
					async : false,
					cache : false,
					success : function(data) {
						var as = eval(data);
						if(as[0] == "true"){
							alert("주문 취소 성공!");
							back();
						}
						else{
							alert("오류 발생! 관리자에게 문의하세요.")
							back();
						}
					},// 요청 완료 시
					error : function(msg, error) {
						alert(error);
					}// 요청 실패.
				});
		})
	</script>

	<script>
		$(window).on("unload", function Close() {
			$.ajax({
				url : "<%=request.getContextPath()%>/member",
		    type:'POST',
		    data: {
		        flag:'logout',
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