<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.java.DTO.*"%>
<%@ page import="java.util.*"%>
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

<title>로그인</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<%
	ArrayList<User> list = com.java.DTO.UserList.getList();
	User user;
	int ranking = 3;//순위권을 3위까지로 지정
	
	if(list.size() < ranking){ranking = list.size();}//순위권보다 회원 수가 적다면 순위권을 조정.
	
	String[] nickNamePer = new String[ranking];//수익률 순위권 닉네임
	String[] nickNameAmt = new String[ranking];//수익금 순위권 닉네임
	double[] per = new double[ranking];//수익률 
	long[] amt = new long[ranking];//수익금
	String[] strAmt = new String[ranking];//수익금 출력양식
	
	for(int i = 0;i<ranking;i++){
		nickNamePer[i] = ""; nickNameAmt[i] = ""; per[i] = 0; amt[i] = 0;
	}
	
	int countPer = 0;
	int countAmt = 0;
	double round = 0;//반올림을 위한 변수
	
	for(int i = 0;i < list.size();i++){
		user = list.get(i);
		
		if(user.getIsActive() == 1){//유효한 회원정보에 대해서
			if(countPer >= ranking && countAmt >= ranking){//전체를 순회하기 전에 순위권 회원을 모두 찾았다면 빠져나온다.
				break;
			}

			
			if(user.getRankAmt() <= ranking && nickNameAmt[user.getRankAmt()-1] == ""){//순위권일 때, 순위 중복이 아닐 경우
				nickNameAmt[user.getRankAmt()-1] = user.getNickName();
				amt[user.getRankAmt()-1] = user.getAmt();
				countAmt++;
			}
			if(user.getRankPer() <= ranking && nickNamePer[user.getRankPer()-1] == ""){//마찬가지로 순위권이며, 순위 중복이 아닐 경우 
				nickNamePer[user.getRankPer()-1] = user.getNickName();
				
				round = (double)Math.round(user.getPer()*100*1000)/1000;//소수점 아래 2번째 자리에서 반올림
				per[user.getRankPer()-1] = round;
				countPer++;
			}
		}
	}
	
	for(int i = 0;i<ranking;i++){
		strAmt[i] = NumberFormat.getInstance().format(amt[i]);
	}
	
%>
</head>
<body>
	
	
	<div class="container-fluid">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col" style="width: 20%"><div class="dropdown">
		<a class="btn btn-secondary dropdown-toggle" href="#" role="button"
			id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> 익절 메이커 </a>


		<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
			<center>
				<form action="member" method="post" encType="UTF-8">
					<a class="dropdown-item" href="#"> <input type="hidden"
						name="flag" value="logout">
						<button type="submit" class="btn btn-light"
							style="background: white; border: none;">로그아웃</button>
					</a>
				</form>
				<form action="member" method="post" encType="UTF-8">
					<a class="dropdown-item" href="#"> <input type="hidden"
						name="flag" value="viewinfo">
						<button type="submit" class="btn btn-light"
							style="background: white; border: none;">회원 정보 조회</button>
					</a>
				</form>
				<form action="Search" method="get" encType="UTF-8">
					<a class="dropdown-item" href="#"> <input type="hidden"
						name="flag" value="search">
						<button type="submit" class="btn btn-light"
							style="background: white; border: none;"
							onclick="location.href='Search.jsp'">주가 정보 조회</button>
					</a>
				</form>
				<form action="bm" method="post" encType="UTF-8">
					<a class="dropdown-item" href="#"> <input type="hidden"
						name="flag" value="inquireBookmark">
						<button type="submit" class="btn btn-light"
							style="background: white; border: none;">관심종목 조회</button>
					</a>
				</form>
			</center>
		</div>
	</div></th>
                        <th scope="col">수익률</th>
                        <th scope="col">수익금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">1위</th>
                        <td><%=nickNamePer[0] %> (<%=per[0] %>%)</td>
                        <td><%=nickNameAmt[0] %> (<%=strAmt[0] %>원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">2위</th>
                        <td><%=nickNamePer[1] %> (<%=per[1] %>%)</td>
                        <td><%=nickNameAmt[1] %> (<%=strAmt[1] %>원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">3위</th>
                        <td><%=nickNamePer[2] %> (<%=per[2] %>%)</td>
                        <td><%=nickNameAmt[2] %> (<%=strAmt[2] %>원)</td>
                    </tr>
                </tbody>
            </table>
        </div>
	
	<p class="text-center">
			<Strong>코스피</Strong>
	</p>

		<div class="chart">
			<table class="table">
				<thead></thead>
				<tbody>
					<tr>
						<td style="width: 10%" bgcolor="#f7faf5" id="area_day"><strong>1일</strong></td>
						<td style="width: 10%" bgcolor="#e8ebee" id="area_month3"><strong>3개월</strong></td>
						<td style="width: 10%" bgcolor="#f7faf5" id="area_year"><strong>1년</strong></td>
						<td style="width: 10%" bgcolor="#e8ebee" id="area_year3"><strong>3년</strong></td>
					</tr>
				</tbody>
			</table>
			<center>
				<img src="<%="https://ssl.pstatic.net/imgstock/chart3/day/KOSPI.png" %>" width="40%" id="chart"></img>
			</center>
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
		$('#area_day').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgstock/chart3/day/KOSPI.png"%>");
		})
		$('#area_month3').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgstock/chart3/day90/KOSPI.png"%>");
		})
		$('#area_year').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgstock/chart3/day365/KOSPI.png" %>");
		})
		$('#area_year3').click(function(){
			$('#chart').attr("src",	"<%="https://ssl.pstatic.net/imgstock/chart3/day1095/KOSPI.png" %>");
		})
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