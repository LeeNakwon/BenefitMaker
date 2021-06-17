<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="ko">
<%
	String[][] coInfo = (String[][])request.getAttribute("coInfo");

	String quarter[] = coInfo[0];
	String sales[] = coInfo[1];
	String bp[] = coInfo[2];
	String npdt[] = coInfo[3]; 
	String bpRatio[] = coInfo[4]; 
	String niRatio[] = coInfo[5];
	String roe[] = coInfo[6];
	String debtRatio[] = coInfo[7]; 
	String quickRatio[] = coInfo[8];
	String reserveRatio[] = coInfo[9];
	String code = coInfo[10][0];
%>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>상장사 상세정보 3쪽</title>
  </head>
  <body>
        <div class="container-fluid">

            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">지난 분기 실적</th>
                        <th scope="col"><%=quarter[2] %></th>
                        <th scope="col"><%=quarter[3] %></th>
                    </tr>
                </thead>
                <tbody>
					<tr>
                        <th scope="row">매출액(억원)</th>
                        <td><%=sales[2] %></td>
                        <td><%=sales[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">영업이익(억원)</th>
                        <td><%=bp[2] %></td>
                        <td><%=bp[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row">당기순이익(억원)</th>
                        <td><%=npdt[2] %></td>
                        <td><%=npdt[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">영업이익률(%)</th>
                        <td><%=bpRatio[2] %></td>
                        <td><%=bpRatio[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row">순이익률(%)</th>
                        <td><%=niRatio[2] %></td>
                        <td><%=niRatio[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">ROE(%)</th>
                        <td><%=roe[2] %></td>
                        <td><%=roe[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row">부채비율(%)</th>
                        <td><%=debtRatio[2] %></td>
                        <td><%=debtRatio[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">당좌비율(%)</th>
                        <td><%=quickRatio[2] %></td>
                        <td><%=quickRatio[3] %></td>
                    </tr>
                    <tr>
                        <th scope="row">유보율(%)</th>
                        <td><%=reserveRatio[2] %></td>
                        <td><%=reserveRatio[3] %></td>
                    </tr>
                </tbody>
            </table>
            <span style="float:left">
            <form method="post" action="test">
				<input type="hidden" name="flag" value="DisplayCompanyInfo2">
				<input type="hidden" name="code" value=<%=code %>>
				<button type="submit" class="btn btn-primary">prev</button>
			</form>
            </span>
        </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>