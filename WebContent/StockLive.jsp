<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<%@ page import="com.java.DTO.DayStockInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.java.DTO.DayStockInfoJSP"%>
<%@ page import="com.java.DTO.BookmarkList" %>
<html lang="ko">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>로그인</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%
	DayStockInfoJSP info = (DayStockInfoJSP) request.getAttribute("StockInfo");
	String predict = (String)request.getAttribute("predict");
%>
</head>

<body>
	<div>
		<p class="text-center">
			<Strong><%=info.getName()%></Strong>&nbsp&nbsp(<%=info.getCode()%>)
			<img src="https://cdn.onlinewebfonts.com/svg/img_520926.png"
			style="height: 24px; width: 24px;" alt="" id="insertBookmark"
			onclick="imgToggle()">
			<img src="https://www.iconattitude.com/icons/open_icon_library/actions/png/32/bookmark-2.png"
			style="height: 24px; width: 24px;" alt="" id="deleteBookmark"
			onclick="imgToggle()">
		</p>
		<div class="chart">
			<table class="table">
				<thead></thead>
				<tbody>
					<tr>
						<td style="color:#187785" bgcolor="#e8ebee"><strong>선차트</strong></td>
						<td id="area_day" bgcolor="#f7faf5"><strong>1일</strong></td>
						<td id="area_week" bgcolor="#e8ebee"><strong>1주일</strong></td>
						<td id="area_month3" bgcolor="#f7faf5"><strong>3개월</strong></td>
						<td id="area_year" bgcolor="#e8ebee"><strong>1년</strong></td>
						<td id="area_year3" bgcolor="#f7faf5"><strong>3년</strong></td>
						<td id="area_year5" bgcolor="#e8ebee"><strong>5년</strong></td>
						<td id="area_year10" bgcolor="#f7faf5"><strong>10년</strong></td>
					</tr>
					<tr>
						<td style="color:#187785" bgcolor="#e8ebee"><strong>봉차트</strong></td>
						<td id="candle_day" bgcolor="#f7faf5"><strong>일봉</strong></td>
						<td id="candle_week" bgcolor="#e8ebee"><strong>주봉</strong></td>
						<td id="candle_month" bgcolor="#f7faf5"><strong>월봉</strong></td>
					</tr>
				</tbody>
			</table>
			<img src="<%="https://ssl.pstatic.net/imgfinance/chart/item/area/day/"+ info.getCode() + ".png" %>" width="100%" id="chart"></img>
		</div>
		<table class="table table-striped"
			style="table-layout: fixed border: hidden">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td>현재가<br>
						<p id="liveprice"><%=info.getPercent()%></p>
					</td>
					<td>AI 예측 종가<br>
						<p id="predict"><%=predict %></p>
					</td>
					<td>전일<br>
						<p id="yesterday"><%=info.getYesterday()%></p>
					</td>
					<td>고가<br> <span id="high"><%=info.getHigh()%></span>&nbsp;<span
						id="color" style="color:#BDBDBD">(상한가 <span id="highlimit"><%=info.getHighLimit()%></span>)
					</span>
					</td>
					<td>거래량<br>
						<p id="volume"><%=info.getVolume()%></p>
					</td>
				</tr>
				<tr>
					<td>전일대비<br> <span id="und"><strong><%=info.getUnD()%></strong></span> <span
						id="percent"><%=info.getLivePrice()%></span><br> <span
						id="diff"><%=info.getDiffAmount()%></span>
					</td>
					<td>시가<br>
						<p id="start"><%=info.getStart()%></p>
					</td>
					<td>저가<br> <span id="low"><%=info.getLow()%></span>&nbsp;<span id="color" style="color:#BDBDBD">(하한가
						<span id="lowlimit"><%=info.getLowLimit()%></span>)</span>
					</td>
					<td>거래대금<br> <span id="tradeamt"><%=info.getTradeAmt()%></span>&nbsp;백만
					</td>
				</tr>
			</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<form action="test" method="post" colspan="2">
							<input type="hidden" name="flag" value="DisplayCompanyInfo1">
							<input type="hidden" name="code" value="<%=info.getCode()%>">
							<button type="submit" class="btn btn-primary">상세정보</button>
						</form>
					</td>
					<td>
						<form action="Insert" method="post">
							<input type="hidden" name="flag" value="invest">
							<input type="hidden" name="CoName" value="<%=info.getName()%>">
							<button type="submit" class="btn btn-primary">모의 투자</button>
						</form>
					</td>
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
		var cnt = 0;
		var isExist = <%=(boolean)request.getAttribute("isExist")%>
		if(isExist == true) {
			$('#insertBookmark').hide();
			cnt = 1;
		}
		else if(isExist == false) {
			$('#deleteBookmark').hide();
			cnt = 0;
		}
		function imgToggle() {
			if(cnt == 0) {
				$.ajax({
					url: "<%=request.getContextPath()%>/bm",
					type: 'POST',
					data: {
						flag: 'insertBookmark',
						Code: '<%=info.getCode()%>',
					},
					async : true,
					cache : false,
					success : function(data) {
						$('#insertBookmark').hide();
						$('#deleteBookmark').show();
					},// 요청 완료 시
					error : function(msg, error) {
						alert(error);
					}// 요청 실패.
				});
				cnt = 1;
			} else {
				$.ajax({
					url: "<%=request.getContextPath()%>/bm",
					type: 'POST',
					data: {
						flag: 'deleteBookmark',
						Code: '<%=info.getCode()%>',
					},
					async : true,
					cache : false,
					success : function(data) {
						$('#deleteBookmark').hide();
						$('#insertBookmark').show();
					},// 요청 완료 시
					error : function(msg, error) {
						alert(error);
					}// 요청 실패.
				});
				cnt = 0;
			}
		}
	</script>
	<script>
		$('#area_day').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/day/"+ info.getCode() + ".png" %>");
		})
		$('#area_week').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/week/"+ info.getCode() + ".png" %>");
		})
		$('#area_month3').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/month3/"+ info.getCode() + ".png" %>");
		})
		$('#area_year').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/year/"+ info.getCode() + ".png" %>");
		})
		$('#area_year3').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/year3/"+ info.getCode() + ".png" %>");
		})
		$('#area_year5').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/year5/"+ info.getCode() + ".png" %>");
		})
		$('#area_year10').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/area/year10/"+ info.getCode() + ".png" %>");
		})
		$('#candle_day').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/candle/day/"+ info.getCode() + ".png" %>");
		})
		$('#candle_week').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/candle/week/"+ info.getCode() + ".png" %>");
		})
		$('#candle_month').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgfinance/chart/item/candle/month/"+ info.getCode() + ".png" %>");
		})
	</script>
	
	<script>
		function Color(){
			var und = '<%=info.getUnD()%>'
			if(und == '▲'){
				$('#und').css("color","red");
				$('#liveprice').css("color","red");
				$('#percent').css("color","red");
				$('#diff').css("color","red");
				$('#start').css("color","red");
				$('#high').css("color","red");
				$('#low').css("color","red");
				$('#color').css("color","#BDBDBD");
			}
			else if(und == '▼'){
				$('#und').css("color","blue");
				$('#liveprice').css("color","blue");
				$('#percent').css("color","blue");
				$('#diff').css("color","blue");
				$('#start').css("color","blue");
				$('#high').css("color","blue");
				$('#low').css("color","blue");
			}
			else{
				$('#und').css("color","black");
				$('#liveprice').css("color","black");
				$('#percent').css("color","black");
				$('#diff').css("color","black");
				$('#start').css("color","black");
				$('#high').css("color","black");
				$('#low').css("color","black");
			}
		}
		</script>
	<script>
	function action(){
		$.ajax({
			url: "<%=request.getContextPath()%>/Stock",
		    type:'GET',
		    data: {
		        flag:'live',
		        ajax:'true',
		        CoName:'<%=info.getName()%>',
				},
				async : true,
				cache : false,
				success : function(data) {
					var as = eval(data);
					$('#und').text(as[2]);
					$('#percent').text(as[4]);
					$('#liveprice').text(as[3]);
					$('#diff').text(as[5]);
					$('#yesterday').text(as[6]);
					$('#high').text(as[7]);
					$('#highlimit').text(as[8]);
					$('#volume').text(as[9]);
					$('#start').text(as[10]);
					$('#low').text(as[11]);
					$('#lowlimit').text(as[12]);
					$('#tradeamt').text(as[13]);
					Color();
				},// 요청 완료 시
				error : function(msg, error) {
					alert(error);
				}// 요청 실패.
			});
		}
		setInterval("action()", 5000);
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