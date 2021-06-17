<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.java.DTO.Bookmark"%>
<%@ page import="com.java.DTO.DayStockInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="com.java.DTO.DayStockInfoJSP"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<%
	DayStockInfoJSP info = (DayStockInfoJSP) request.getAttribute("StockInfo");
%>
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

<title>관심종목</title>
</head>
<%
	ArrayList<String> bookmark = (ArrayList<String>) request.getAttribute("bookmark");
%>
<body>
	<div>
		<p class="text-center">
			<strong>관심 종목</strong>
		</p>
	</div>
	<table class="table table-striped"
		style="table-layout: fixed border: hidden">
		<thead>
			<tr>
				<td scope="col">#</td>
				<td scope="col">상장사 명</td>
				<td scope="col">-</td>
			</tr>
		</thead>
		<tbody>
			<tr id="tr0">
				<td id="0">1</td>
				<td id="0_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit0" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit01" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr1">
				<td id="1">2</td>
				<td id="1_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit1" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit11" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr2">
				<td id="2">3</td>
				<td id="2_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit2" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit21" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr3">
				<td id="3">4</td>
				<td id="3_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit3" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit31" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr4">
				<td id="4">5</td>
				<td id="4_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit4" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit41" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr5">
				<td id="5">6</td>
				<td id="5_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit5" class="btn btn-primary">모의
							투자</button>
					</form>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit51" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr6">
				<td id="6">7</td>
				<td id="6_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit6" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit61" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr7">
				<td id="7">8</td>
				<td id="7_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit7" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit71" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr8">
				<td id="8">9</td>
				<td id="8_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit8" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit81" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr id="tr9">
				<td id="9">10</td>
				<td id="9_bookmark"></td>
				<td>
					<form action="Insert" method="post">
						<input type="hidden" name="flag" value="invest"> <input
							type="hidden" name="CoName" id="CoName" value="">
						<button type="submit" id="submit9" class="btn btn-primary">모의
							투자</button>
					</form>
				</td>
				<td>
					<form action="Stock" method="get" encType="UTF-8">
						<input type="hidden" name="flag" value="live"> 
						<input type="hidden" name="CoName" id="name" value="">
						<button type="submit" id="submit91" class="btn btn-primary">상세정보</button>
					</form>
				</td>
			</tr>
			<tr>
				<td><input type="button" class="btn btn-primary" id="backward"
					value="이전" onclick="backward()"></td>
				<td></td>
				<td><input type="button" class="btn btn-primary" id="forward"
					value="다음" onclick="forward()"></td>
			</tr>
		</tbody>
	</table>
	<div class="dropdown-divider"></div>
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
	<script>
		$('#submit').hide();
		var counter = -10;
		var bookmark = new Array();
		var size =
	<%=bookmark.size()%>
		;
		$('#forward').hide();
		$('#backward').hide();
		<c:forEach var="value" items="${bookmark}">
		bookmark.push("${value}")
		</c:forEach>
		for (var i = 0; i < 10; i++) {
			$('#submit' + i).hide();
			$('#submit' + i + '1').hide();
		}

		function setTd(i, point) {
			$('#' + i + '_bookmark').text(bookmark[point]);
			$('#submit' + i).show();
			$('#submit' + i + '1').show();
		}

		function setNo(count, limit) {
			for (var i = 0; i < limit; i++) {
				var point = i + count + 1;
				$('#' + i).text(point);
			}
			for (var i = limit; i < 10; i++) {
				$('#' + i).text('');
			}
		}

		$('#submit0').click(function() {
			$('#CoName').val(bookmark[counter + 0]);
		})
		$('#submit01').click(function() {
			$('#name').val(bookmark[counter + 0]);
		})
		$('#submit1').click(function() {
			$('#CoName').val(bookmark[counter + 1]);
		})
		$('#submit11').click(function() {
			$('#name').val(bookmark[counter + 1]);
		})
		$('#submit2').click(function() {
			$('#CoName').val(bookmark[counter + 2]);
		})
		$('#submit21').click(function() {
			$('#name').val(bookmark[counter + 2]);
		})
		$('#submit3').click(function() {
			$('#CoName').val(bookmark[counter + 3]);
		})
		$('#submit31').click(function() {
			$('#name').val(bookmark[counter + 3]);
		})
		$('#submit4').click(function() {
			$('#CoName').val(bookmark[counter + 4]);
		})
		$('#submit41').click(function() {
			$('#name').val(bookmark[counter + 4]);
		})
		$('#submit5').click(function() {
			$('#CoName').val(bookmark[counter + 5]);
		})
		$('#submit51').click(function() {
			$('#name').val(bookmark[counter + 5]);
		})
		$('#submit6').click(function() {
			$('#CoName').val(bookmark[counter + 6]);
		})
		$('#submit61').click(function() {
			$('#name').val(bookmark[counter + 6]);
		})
		$('#submit7').click(function() {
			$('#CoName').val(bookmark[counter + 7]);
		})
		$('#submit71').click(function() {
			$('#name').val(bookmark[counter + 7]);
		})
		$('#submit8').click(function() {
			$('#CoName').val(bookmark[counter + 8]);
		})
		$('#submit81').click(function() {
			$('#name').val(bookmark[counter + 8]);
		})
		$('#submit9').click(function() {
			$('#CoName').val(bookmark[counter + 9]);
		})
		$('#submit91').click(function() {
			$('#name').val(bookmark[counter + 9]);
		})

		if (size < 10) {
			for (var i = 0; i < size; i++) {
				setTd(i, i);
			}
			counter += 10;
			setNo(counter, size);
		} else {
			for (var i = 0; i < 10; i++) {
				setTd(i, i);
				$('#forward').show();
			}
			counter += 10;
			setNo(counter, 10);
		}

		function forward() {
			counter += 10;
			var leftover = size - counter;
			if (leftover < 10) {
				for (var i = 0; i < leftover; i++) {
					var point = i + counter;
					setTd(i, point);
				}
				for (var i = leftover; i < 10; i++) {
					var point = i + leftover;
					$('#' + i + '_bookmark').text('');
				}
				$('#forward').hide();
				$('#backward').show();
				setNo(counter, leftover);
			} else {
				for (var i = 0; i < 10; i++) {
					var point = i + counter;
					setTd(i, point);
				}
				$('#forward').show();
				$('#backward').show();
				setNo(counter, 10);
			}
		}

		function backward() {
			counter -= 10;
			for (var i = 0; i < 10; i++) {
				var point = i + counter;
				setTd(i, point);
			}
			if (counter == 0) {
				$('#forward').show();
				$('#backward').hide();
			} else {
				$('#forward').show();
				$('#backward').show();
			}
			setNo(counter, 10);
		}
	</script>
</body>
</html>