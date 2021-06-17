<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="ko">
<%
	String[] coInfo = (String[])request.getAttribute("coInfo"); 
	String high = coInfo[0];
	String low = coInfo[1];
	String high52 = coInfo[2];
	String low52 = coInfo[3];
	String foreigner = coInfo[4];
	String total = coInfo[5];
	String eps = coInfo[6];
	String bps = coInfo[7]; 
	String code = coInfo[8];
	String name = coInfo[9];
%>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>상장사 상세정보</title>
  </head>
  <body>
        <div class="container-fluid">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col" style="width: 30%">투자정보</th>
                        <th scope="col"><%=name %>(<%=code%>)</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">상한가</th>
                        <td><%=high %>(원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">하한가</th>
                        <td><%=low %>(원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">52주 최고</th>
                        <td><%=high52 %>(원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">52주 최저</th>
                        <td><%=low52 %>(원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">외국인소진율</th>
                        <td><%=foreigner %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">시가총액</th>
                        <td><%=total %>억(원)</td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">PER(/EPS)</th>
                        <td><%=eps %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">PBR(/BPS)</th>
                        <td><%=bps %></td>
                    </tr>
                </tbody>
            </table>
			<center>
			<form method="post" action="test">
				<input type="hidden" name="flag" value="DisplayCompanyInfo2">
				<input type="hidden" name="code" value=<%=code %>>
				<button type="submit" class="btn btn-primary">지난 분기 실적 보기</button>
			</form>
			</center>
        </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>