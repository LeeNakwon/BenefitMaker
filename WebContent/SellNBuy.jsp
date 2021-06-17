<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.java.DTO.*"%>
<%@ page import="com.java.Order.*"%>
<%@ page import="java.text.NumberFormat"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<title>모의투자</title>
<%
	Invest invest = (Invest) request.getAttribute("invest");
	User user = invest.getUser();
	Company com = invest.getCompany();

	String ID, coName;
	long cap;
	int amt, val, price;
	String strCap, strAmt, strVal, strPrice;//콤마 찍어서 스트링형 전환한거

	ID = user.getID();
	coName = com.getName();

	cap = user.getCapitalCurrent();
	amt = invest.getAmt();
	val = invest.getVal();
	price = invest.getMarket();

	strCap = NumberFormat.getInstance().format(cap);
	strAmt = NumberFormat.getInstance().format(amt);
	strVal = NumberFormat.getInstance().format(val);
	strPrice = NumberFormat.getInstance().format(price);
%>
<script>var oldVal = ""; var oldVal1 = ""; var flag = "Buy"; var option = "Distinct";</script>
</head>
<body>
	<table class="table" style="border: hidden">
		<tbody>
			<tr>
				<td style="width: 20%">보유 자본금</td>
				<td style="width: 80%"><%=strCap%>원</td>
			</tr>
			<tr>
				<td style="width: 20%">선택하신 상장사 명</td>
				<td style="width: 80%"><%=coName%></td>
			</tr>
			<tr>
				<td style="width: 20%">보유한 주식 수</td>
				<td style="width: 80%"><%=strAmt%></td>
			</tr>
			<tr>
				<td style="width: 20%">현재의 주 당 가격</td>
				<td style="width: 80%"><%=strPrice%>원</td>
			</tr>
			<tr>
				<td style="width: 20%">보유한 주식 가격</td>
				<td style="width: 80%"><%=strVal%>원</td>
			</tr>
			<tr>
				<td style="width: 20%">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadio"
							id="isBuy" value="Buy" checked> <label class="form-check-label"
							for="exampleRadios"> 매입</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadio"
							id="isSell" value="Sell"> <label
							class="form-check-label" for="exampleRadios"> 매도</label>
					</div>
				</td>
				<td style="width: 80%"><div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadios"
							id="radio1" value="Distinct" checked> <label
							id="radio1lable" class="form-check-label" for="exampleRadios1">
							지정가</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadios"
							id="radio2" value="Market"> <label id="radio2lable"
							class="form-check-label" for="exampleRadios1"> 시장가</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="exampleRadios"
							id="radio3" value="OptMarket"> <label id="radio3lable"
							class="form-check-label" for="exampleRadios1"> 조건부 지정가</label>
					</div></td>
			</tr>
			<tr>
				<td id="isAmt" style="width: 20%">매매할 주식 수</td>
				<td style="width: 80%"><script>
					function onlyNumber(event) {
						event = event || window.event;
						var keyID = (event.which) ? event.which : event.keyCode;
						if ((keyID >= 48 && keyID <= 57)
								|| (keyID >= 96 && keyID <= 105) || keyID == 8
								|| keyID == 46 || keyID == 37 || keyID == 39)
							return;
						else
							return false;
					}
					function removeChar(event) {
						event = event || window.event;
						var keyID = (event.which) ? event.which : event.keyCode;
						if (keyID == 8 || keyID == 46 || keyID == 37
								|| keyID == 39)
							return;
						else
							event.target.value = event.target.value.replace(
									/[^0-9]/g, "");
					}
				</script> <input type="text" id="amount" onkeydown='return onlyNumber(event)'
					onkeyup='removeChar(event)'>
			</tr>
			<tr>
				<td style="width: 20%">주 당 가격</td>
				<td style="width: 80%"><script>
					function onlyNumber(event) {
						event = event || window.event;
						var keyID = (event.which) ? event.which : event.keyCode;
						if ((keyID >= 48 && keyID <= 57)
								|| (keyID >= 96 && keyID <= 105) || keyID == 8
								|| keyID == 46 || keyID == 37 || keyID == 39)
							return;
						else
							return false;
					}
					function removeChar(event) {
						event = event || window.event;
						var keyID = (event.which) ? event.which : event.keyCode;
						if (keyID == 8 || keyID == 46 || keyID == 37
								|| keyID == 39)
							return;
						else
							event.target.value = event.target.value.replace(
									/[^0-9]/g, "");
					}
				</script> <input type="text" id="price" onkeydown='return onlyNumber(event)'
					onkeyup='removeChar(event)'>
				</td>
			</tr>
			<tr>
				<td style="width: 20%">수수료</td>
				<td id="charge" style="width: 80%">0</td>
			</tr>
			<tr>
				<td id="isTotal" style="width: 20%">총 매매 금액</td>
				<td id="total" style="width: 80%">0</td>
			</tr>
			<tr>
				<td style="width: 20%">
					<button id="confirmBtn" type="button" class="btn btn-primary">확인</button>
				</td>
				<td style="width: 80%"></td>
			</tr>
			<tr style="border: hidden">
			</tr>
		</tbody>
	</table>
	<form action="Insert" method="post" encType="UTF-8">
		<input type="hidden" name="flag" value="goToSearch">
		<button id="submit" type="submit" class="btn btn-primary"></button>
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
		function back(){
			$('#submit').click();
		}
		//라디오버튼 클릭 감지
		$("#isSell").click(function(){
			flag = $("#isSell").val();
    	});
		$("#isBuy").click(function(){
			flag = $("#isBuy").val();
    	});
		$("#radio1").click(function(){
			option = $("#radio1").val();
			$("#price").attr("readonly",false);
    	});
		$("#radio2").click(function(){
			option = $("#radio2").val();
			$("#price").val(<%=price%>);
			$("#price").attr("readonly",true);
			setChargeNTotal(oldVal);
    	});
		$("#radio3").click(function(){
			option = $("#radio3").val();
			$("#price").attr("readonly",false);
    	});
		//확인 버튼 누를 경우.
		$('#confirmBtn').click(function(){
		if($('#amount').val().length==0){
			alert("매매할 주식 수를 입력하세요.");
			return;
		}
		var amount = $('#amount').val();
		var totalVar = $('#total').text();
		var orderType = "";
		var opt = "";
		
		if(flag == "Sell"){orderType = "매도";}
		else if(flag == "Buy"){orderType = "매입";}
		else{alert("OrderType Error!!!");return;}
		
		if(option == "Distinct"){opt = "지정가";}
		else if(option == "Market"){opt = "시장가";}
		else if(option == "OptMarket"){opt = "조건부 시장가";}
		else{alert("Option Error!!!");return;}
		
		var checkMsg = "매매 종류 : "+orderType+"\n"+"옵션 : "+opt+"\n"+"상장사명 : "+"<%=coName%>"+"\n"+"주 당 가격 : "+<%=price%>
			+"\n"+orderType+"할 주식 수 : "+amount+"\n"+"총액 : "+totalVar+"\n\n"+"주문 등록을 진행하시겠습니까?";
					
		var check = isValid(flag);
		var ot = flag+','+option;
		if(check == 1){//true일 때
			if(!confirm(checkMsg)){alert("주문 등록이 취소되었습니다."); return;}
			else{
				$.ajax({
					url: "<%=request.getContextPath()%>/Insert",
				    type:'POST',
				    data: {
						flag:'register',
						userID: '<%=ID%>',
						opt: ot,
						coName: '<%=coName%>',
						val: amount,
						price: '<%=price%>',
					},
					async : true,
					cache : false,
					success : function(data) {
						back();
					},// 요청 완료 시
					error : function(msg, error) {
						alert(error);
						back();
					}// 요청 실패.
					});
			}
		} 
		else if (check == 0) {//'에러'일 때
			alert("Flag Error!!!");
		} 
		else {
			if (check == 2) {//보유 주식 수 초과
				alert("보유한 주식 수를 초과했습니다.");
			} 
			else if(check == 3){//보유 자본금 초과
				alert("보유 자본금을 초과했습니다.");
			}
			else if(check == 4){//주식 수 입력 잘못됨
				alert("1이상의 숫자를 입력하세요.");
			}
		}
		})

		function isValid(flag) {
			var val = oldVal;
			if (flag == "Sell" && val>0) {
				if (val <= <%=amt%>) {
					return 1;
				} else {
					return 2;//보유 주식 수 초과
				}
			}
			else if (flag == "Buy" && val>0) {
				var num =<%=price%>;
				var total = (num * val) * 1.01;

				if (total <=<%=cap%>) {
					return 1;
				} else {
					return 3;//보유 자본금 초과
				}
			}
			else if(val<=0){
				return 4;//주식 수 입력 잘못됨
			}
			else {
				return 0;
			}
		}

		//매매할 주식 수 입력 시 자동으로 수수료,총액 계산
		$('#amount').on("change keyup paste", function() {
			var currentVal = $(this).val();
			if (currentVal == oldVal) {
				return;
			} else {
				oldVal = currentVal;
				setChargeNTotal(oldVal);
			}
		});
		
		//매매할 주 당 가격 입력 시 자동으로 수수료, 총액 계산
		$('#price').on("change keyup paste", function() {
			var currentVal = $(this).val();
			if (currentVal == oldVal1) {
				return;
			} else {
				oldVal1 = currentVal;
				if(oldVal != ""){
					setChargeNTotal(oldVal);
				}
			}
		});

		function setChargeNTotal(val) {//수수료, 총액 구하기

			var num = $('#price').val();
			var total = num * val;
			var charge = Math.ceil(total * 0.01);//총 매매액의 1%를 올림한다.

			total = numWithComma(total + charge);//수수료 포함.
			charge = numWithComma(charge);
			$('#charge').text(charge + "원");
			$('#total').text(total + "원");
		}

		function numWithComma(x) {//3자리 단위로 콤마 찍기
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
	</script>
</body>
</html>