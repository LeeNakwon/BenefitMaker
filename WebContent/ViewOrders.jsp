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
	ArrayList<String> dates = (ArrayList<String>) request.getAttribute("dates");
	ArrayList<String> names = (ArrayList<String>) request.getAttribute("names");
	ArrayList<String> ids = (ArrayList<String>) request.getAttribute("ids");
%>

<body>
	<div>
		<p class="text-center">
			<strong>주문 목록</strong>
		</p>
	</div>
	<table class="table table-striped" style="table-layout: fixed border: hidden">
		<thead>
			<tr>
				<td scope="col">#</td>
				<td scope="col">날짜</td>
				<td scope="col">상장사 명</td>
			</tr>
		</thead>
		<tbody>
			<tr id="tr0">
				<td id="0"></td>
				<td id="0_date"></td>
				<td id="0_order"></td>
			</tr>
			<tr id="tr1">
				<td id="1">2</td>
				<td id="1_date"></td>
				<td id="1_order"></td>
			</tr>
			<tr id="tr2">
				<td id="2">3</td>
				<td id="2_date"></td>
				<td id="2_order"></td>
			</tr>
			<tr id="tr3">
				<td id="3">4</td>
				<td id="3_date"></td>
				<td id="3_order"></td>
			</tr>
			<tr id="tr4">
				<td id="4">5</td>
				<td id="4_date"></td>
				<td id="4_order"></td>
			</tr>
			<tr id="tr5">
				<td id="5">6</td>
				<td id="5_date"></td>
				<td id="5_order"></td>
			</tr>
			<tr id="tr6">
				<td id="6">7</td>
				<td id="6_date"></td>
				<td id="6_order"></td>
			</tr>
			<tr id="tr7">
				<td id="7">8</td>
				<td id="7_date"></td>
				<td id="7_order"></td>
			</tr>
			<tr id="tr8">
				<td id="8">9</td>
				<td id="8_date"></td>
				<td id="8_order"></td>
			</tr>
			<tr id="tr9">
				<td id="9">10</td>
				<td id="9_date"></td>
				<td id="9_order"></td>
			</tr>
			<tr>
				<td>
					<input type="button" class="btn btn-primary" id="backward" value="이전" onclick="backward()">
				</td>
				<td></td>
				<td>
					<input type="button" class="btn btn-primary" id="forward" value="다음" onclick="forward()">
				</td>
			</tr>
		</tbody>
	</table>
	<form action = "ViewDetail" method="post">
		<input type="hidden" name="OrderID" id="OrderID" value="">
		<input type="submit" id = "submit">
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
		<% int test = 1;%>
		var counter = -10;
		var dates = new Array();
		var names = new Array();
		var OID = new Array();
		var size = <%=dates.size()%>
		$('#forward').hide();
		$('#backward').hide();
		<c:forEach var="value" items="${dates}">
			dates.push("${value}")
		</c:forEach>
		<c:forEach var="value" items="${names}">
			names.push("${value}")
		</c:forEach>
		<c:forEach var="value" items="${ids}">
			OID.push("${value}")
		</c:forEach>
		
		function setTd(i, point){
			$('#' + i + '_date').text(dates[point]);
			$('#' + i + '_order').text(names[point]);
		}
		
		function setNo(count, limit){
			for(var i =0; i < limit; i++){
				var point = i + count + 1;
				$('#' + i).text(point);
			}
			for(var i = limit; i < 10; i++){
				$('#' + i ).text('');
			}
		}
		
		if(size < 10){
			for(var i =0; i < size; i++){
				setTd(i,i);
			}
			counter += 10;
			setNo(counter, size);
		}
		else{
			for(var i =0; i < 10; i++){
				setTd(i,i);
				$('#forward').show();
			}
			counter += 10;
			setNo(counter, 10);
		}
		
		function forward(){
			counter += 10;
			var leftover = size - counter;
			if(leftover < 10){
				for(var i=0; i < leftover; i++){
					var point = i + counter;
					setTd(i,point);
				}
				for(var i = leftover; i < 10; i++){
					var point = i + leftover;
					$('#' + i + '_date').text('');
					$('#' + i + '_order').text('');
				}
				$('#forward').hide();
				$('#backward').show();
				setNo(counter, leftover);
			}
			else{
				for(var i=0; i < 10; i++){
					var point = i + counter;
					setTd(i, point);
				}
				$('#forward').show();
				$('#backward').show();
				setNo(counter, 10);
			}
		}
		
		function backward(){
			counter -= 10;
			for(var i =0; i < 10; i++){
				var point = i + counter;
				setTd(i, point);
			}
			if(counter == 0){
				$('#forward').show();
				$('#backward').hide();
			}
			else{
				$('#forward').show();
				$('#backward').show();
			}
			setNo(counter, 10);
		}
		
		$('#tr0').click(function(){
			var position = counter + 0;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr1').click(function(){
			var position = counter + 1;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr2').click(function(){
			var position = counter + 2;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr3').click(function(){
			var position = counter + 3;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr4').click(function(){
			var position = counter + 4;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr5').click(function(){
			var position = counter + 5;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr6').click(function(){
			var position = counter + 6;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr7').click(function(){
			var position = counter + 7;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr8').click(function(){
			var position = counter + 8;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
		$('#tr9').click(function(){
			var position = counter + 9;
			$('#OrderID').val(OID[position]);
			$('#submit').click();
		})
	</script>
	<script>
	$(window).on("unload",function Close(){
		$.ajax({
			url: "<%=request.getContextPath()%>/member",
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