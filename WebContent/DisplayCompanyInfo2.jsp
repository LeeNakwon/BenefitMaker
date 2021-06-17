<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@page import="java.util.ArrayList" %><!doctype html>
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
	String name = coInfo[11][0];
%>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>상장사 상세정보</title>
  </head>
  <body>
        <div class="container-fluid">

            <table class="table" style="table-layout:fixed">
                <thead class="thead-light">
                    <tr>
                        <th scope="col"><%=name %>(<%=code %>)</th>
                        <th id="0" scope="col"><%=quarter[0] %></th>
                        <th id="1" scope="col"><%=quarter[1] %></th>
                    </tr>
                </thead>
                <tbody>
					<tr>
                        <th scope="row">매출액(억원)</th>
                        <td id="2" ><%=sales[0] %></td>
                        <td id="3"><%=sales[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">영업이익(억원)</th>
                        <td id="4"><%=bp[0] %></td>
                        <td id="5"><%=bp[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row">당기순이익(억원)</th>
                        <td id="6"><%=npdt[0] %></td>
                        <td id="7"><%=npdt[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">영업이익률(%)</th>
                        <td id="8"><%=bpRatio[0] %></td>
                        <td id="9"><%=bpRatio[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row">순이익률(%)</th>
                        <td id="10"><%=niRatio[0] %></td>
                        <td id="11"><%=niRatio[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">ROE(%)</th>
                        <td id="12"><%=roe[0] %></td>
                        <td id="13"><%=roe[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row">부채비율(%)</th>
                        <td id="14"><%=debtRatio[0] %></td>
                        <td id="15"><%=debtRatio[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row" bgcolor="#e9ecef">당좌비율(%)</th>
                        <td id="16"><%=quickRatio[0] %></td>
                        <td id="17"><%=quickRatio[1] %></td>
                    </tr>
                    <tr>
                        <th scope="row">유보율(%)</th>
                        <td id="18"><%=reserveRatio[0] %></td>
                        <td id="19"><%=reserveRatio[1] %></td>
                    </tr>
                </tbody>
            </table>
            <span style="float:left">
				<input type="button" class="btn btn-primary" id="backward" value="이전" onclick="backward()">
			</form>
            </span>
			<span style="float:right">
			<input type="button" class="btn btn-primary" id="forward" value="다음" onclick="forward()">
			</span>
        </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  	<script>
  	$('#backward').hide();
  	function backward(){
		$('#0').text('<%=quarter[0] %>');
		$('#1').text('<%=quarter[1] %>');
		$('#2').text('<%=sales[0] %>');
		$('#3').text('<%=sales[1] %>');
		$('#4').text('<%=bp[0] %>');
		$('#5').text('<%=bp[1] %>');
		$('#6').text('<%=npdt[0] %>');
		$('#7').text('<%=npdt[1] %>');
		$('#8').text('<%=bpRatio[0] %>');
		$('#9').text('<%=bpRatio[1] %>');
		$('#10').text('<%=niRatio[0] %>');
		$('#11').text('<%=niRatio[1] %>');
		$('#12').text('<%=roe[0] %>');
		$('#13').text('<%=roe[1] %>');
		$('#14').text('<%=debtRatio[0] %>');
		$('#15').text('<%=debtRatio[1] %>');
		$('#16').text('<%=quickRatio[0] %>');
		$('#17').text('<%=quickRatio[1] %>');
		$('#18').text('<%=reserveRatio[0] %>');
		$('#19').text('<%=reserveRatio[1] %>');
		
		$('#forward').show();
		$('#backward').hide();
	}
	
	function forward(){
		$('#0').text('<%=quarter[2] %>');
		$('#1').text('<%=quarter[3] %>');
		$('#2').text('<%=sales[2] %>');
		$('#3').text('<%=sales[3] %>');
		$('#4').text('<%=bp[2] %>');
		$('#5').text('<%=bp[3] %>');
		$('#6').text('<%=npdt[2] %>');
		$('#7').text('<%=npdt[3] %>');
		$('#8').text('<%=bpRatio[2] %>');
		$('#9').text('<%=bpRatio[3] %>');
		$('#10').text('<%=niRatio[2] %>');
		$('#11').text('<%=niRatio[3] %>');
		$('#12').text('<%=roe[2] %>');
		$('#13').text('<%=roe[3] %>');
		$('#14').text('<%=debtRatio[2] %>');
		$('#15').text('<%=debtRatio[3] %>');
		$('#16').text('<%=quickRatio[2] %>');
		$('#17').text('<%=quickRatio[3] %>');
		$('#18').text('<%=reserveRatio[2] %>');
		$('#19').text('<%=reserveRatio[3] %>');
		
		$('#forward').hide();
		$('#backward').show();
	}
  	</script>
  </body>
</html>