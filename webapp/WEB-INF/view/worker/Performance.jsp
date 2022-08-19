<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="/WUE/css/import.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Performance</title>
<script type="text/javascript">
	//이름 / 값 / 저장 시킬 시간
	function SetCookie(cookie_name, value, hours) {
		if (hours) {
			var date = new Date();
			date.setTime(date.getTime() + (hours * 60 * 60 * 1000));
			var expires = "; expires=" + date.toGMTString();
		} else {
			var expires = "";
		}
		document.cookie = cookie_name + "=" + value + expires + "; path=/";
	}

	setInterval("dpTime()", 1000);
	function dpTime() {
		var now = new Date();
		hours = now.getHours();
		minutes = now.getMinutes();
		seconds = now.getSeconds();

		if (hours > 12) {
			hours -= 12;
			ampm = "오후 ";
		} else {
			ampm = "오전 ";
		}
		if (hours < 10) {
			hours = "0" + hours;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (seconds < 10) {
			seconds = "0" + seconds;
		}
		document.getElementById("dpTime").innerHTML = ampm + hours + ":"
				+ minutes + ":" + seconds;
	}
</script>
<style type="text/css">
ul li {
	list-style: none;
}

a {
	text-decoration: none;
	color: #333;
}
/* 글자크기를 16px 맑은 고딕 굵게하고 width넓이 200, 높이 30, 글자색은 검정색, 라인높이30px
menu박스 가운데정렬, 글자가운데 정렬 */
#menu2 {
	font: bold 16px "malgun gothic";
	width: 180px;
	color: black;
	line-height: 30px;
	margin: 0 auto;
	text-align: center;
}

/* menu태그 자식의 ul의 자식 li를 왼쪽정렬과 넓이 140설정 */
#menu2>ul>li {
	float: left;
	width: 120px;
	position: relative;
}

#menu2>ul>li>ul {
	width: 120px;
	display: none;
	position: absolute;
	font-size: 14px;
}

#menu2>ul>li:hover>ul {
	display: block;
}

#menu2>ul>li>ul>li:hover {
	background: pink;
	transition: ease 1s;
}
</style>
</head>
<body>

	<header class="header" style="z-index: 2;">
		<div class="header__img">
			<img src="/WUE/img/logoWorker.png" />
		</div>

		<div style="margin-right: 125px; float: right; margin-top: -90px;"><%=sf.format(nowTime)%></div>
		<div style="margin: 20px; float: right; margin-top: -90px;">
			<span id="dpTime">오후 01:44:40</span>
		</div>
		<div class="header__img" style="margin-top: -60px;">
			<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span>${authInfo.name}
				님 로그인</span>
		</div>

	</header>
	<script src="https://kit.fontawesome.com/4a9dbb7224.js"
		crossorigin="anonymous"></script>
	<nav class="menu">
		<table>
			<tr>
				<th style="border: 4px outset pink;" onclick="location.href='list'">관리자
					정보</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;"
					onclick="location.href='management'">회원 관리</th>
			</tr>
			<tr style="height: 140px;">
				<th style="border: 4px outset pink;">
					<div id="menu2">
						<ul>
							<li><a href="kind=1">상품 관리 &nbsp;&nbsp;&nbsp;</a>
								<ul>
									<li><a href="reg">상품 등록 &nbsp;&nbsp;&nbsp;</a></li>
									<li><a href="kind=1">상품 관리 &nbsp;&nbsp;&nbsp;</a></li>
								</ul></li>
							<ul>
								<li>&nbsp;&nbsp;</li>
								<li>&nbsp;&nbsp;</li>
							</ul>
						</ul>
					</div>
				</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;" onclick="location.href='event'">이벤트 관리</th>
			</tr>
			<tr style="height: 140px;">
				<th style="border: 4px outset pink;">
					<div id="menu2">
						<ul>
							<li><a href="board">게시판 관리 &nbsp;&nbsp;&nbsp;</a>
								<ul>
									<li><a href="board">게시판 등록 &nbsp;&nbsp;&nbsp;</a></li>
									<li><a href="qna">Q & A &nbsp;&nbsp;&nbsp;</a></li>
									<li><a href="notice">공지사항 &nbsp;&nbsp;&nbsp;</a></li>
								</ul></li>
							<ul>
								<li>&nbsp;&nbsp;</li>
								<li>&nbsp;&nbsp;</li>
								<li>&nbsp;&nbsp;</li>
								<li>&nbsp;&nbsp;</li>
							</ul>
						</ul>
					</div>
				</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;"
					onclick="location.href='seller_order'">주문내역 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">
					<div id="menu2">
						<ul>
							<li><a href="sales">매출 관리 &nbsp;&nbsp;&nbsp;</a>
								<ul>
									<li><a href="sales">이익 관리&nbsp;&nbsp;&nbsp;</a></li>
									<li><a href="expenditure">지출 관리 &nbsp;&nbsp;&nbsp;</a></li>
								</ul></li>
							<ul>
								<li>&nbsp;&nbsp;</li>
							</ul>
						</ul>
					</div>
				</th>
			</tr>
		</table>
	</nav>
	<form action='sales' method='post'>
		<main class="to_do_list">
			<article
				style="padding-left: 150px; padding-right: 100px; z-index: 1;">
				<h3>Statistics</h3>
				<p>&nbsp;
				<h4>일별 결제 금액</h4>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
				<div style="width: 900px; height: 480px;">
					<!--차트가 그려질 부분-->
					<canvas id="myChart"></canvas>
				</div>
				<script type="text/javascript">
					var context = document.getElementById('myChart')
							.getContext('2d');
					var myChart = new Chart(context, {
						type : 'line', // 차트의 형태
						data : { // 차트에 들어갈 데이터
							labels : [
							//x 축
							'8.11', '8.12', '8.13', '8.14', '8.15', '8.16',
									'8.17' ],
							datasets : [ { //데이터
								label : '일별 결제 금액', //차트 제목
								fill : false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
								data : [ 100, 520, 430, 500, 230, 620, 698 //x축 label에 대응되는 데이터 값
								],
								backgroundColor : [
								//색상
								'rgba(255, 99, 132, 0.2)',
										'rgba(54, 162, 235, 0.2)',
										'rgba(255, 206, 86, 0.2)',
										'rgba(75, 192, 192, 0.2)',
										'rgba(153, 102, 255, 0.2)',
										'rgba(255, 159, 64, 0.2)' ],
								borderColor : [
								//경계선 색상
								'rgba(255, 99, 132, 1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(75, 192, 192, 1)',
										'rgba(153, 102, 255, 1)',
										'rgba(255, 159, 64, 1)' ],
								borderWidth : 1
							} ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							}
						}
					});
				</script>
				<div style="height: 30px"></div>
				<h3>회원별 매출 추이</h3>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Name</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Email</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Payments</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Grade</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Date</b></th>
					</tr>
					<tr></tr>
					<c:forEach var="i" begin="0" end="${sales.size()-1}">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${sales[i].name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${sales[i].email}
							</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${salesbyname[i]}원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><a
								href="promote?email=${sales[i].email}">${sales[i].grade}</a></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${sales[i].reg_date}" /><br></td>
						</tr>
					</c:forEach>
				</table>
				<div style="height: 80px"></div>
				<h4>판매자별 수수료</h4>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
				<div style="width: 900px; height: 480px;">
					<!--차트가 그려질 부분-->
					<canvas id="Chart2"></canvas>
				</div>
				<script type="text/javascript">
					var context = document.getElementById('Chart2').getContext(
							'2d');
					var Chart2 = new Chart(context, {
						type : 'bar', // 차트의 형태
						data : { // 차트에 들어갈 데이터
							labels : [
							//x 축
							'박신혜', '이은영', '김종희', '마예나', '김지욱', '공유' ],
							datasets : [ { //데이터
								label : '수수료 금액', //차트 제목
								fill : false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
								data : [ 807, 457, 358, 529, 643, 2580 //x축 label에 대응되는 데이터 값
								],
								backgroundColor : [
								//색상
								'rgba(255, 99, 132, 0.2)',
										'rgba(54, 162, 235, 0.2)',
										'rgba(255, 206, 86, 0.2)',
										'rgba(75, 192, 192, 0.2)',
										'rgba(153, 102, 255, 0.2)',
										'rgba(255, 159, 64, 0.2)' ],
								borderColor : [
								//경계선 색상
								'rgba(255, 99, 132, 1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(75, 192, 192, 1)',
										'rgba(153, 102, 255, 1)',
										'rgba(255, 159, 64, 1)' ],
								borderWidth : 1
							} ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							}
						}
					});
				</script>
				<div style="height: 30px"></div>
				<h3>판매자별 수수료</h3>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Name</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Email</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Commission</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Month</b></th>
						<!-- <th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Phone</b></th> -->
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Date</b></th>
					</tr>
					<tr></tr>
					<c:forEach items="${seller1Sales}" var="seller1Sales">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller1Sales.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller1Sales.email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller1Sales.money}
								원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller1Sales.month}</td>
							<%-- <td style="border-bottom: 1px solid #444444; padding: 10px;">${sellerSales.phone}</td> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${seller1Sales.reg_date}" /><br></td>
						</tr>
					</c:forEach>
					<c:forEach items="${seller2Sales}" var="seller2Sales">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller2Sales.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller2Sales.email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller2Sales.money}
								원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller2Sales.month}</td>
							<%-- <td style="border-bottom: 1px solid #444444; padding: 10px;">${sellerSales.phone}</td> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${seller2Sales.reg_date}" /><br></td>
						</tr>
					</c:forEach>
					<c:forEach items="${seller3Sales}" var="seller3Sales">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller3Sales.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller3Sales.email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller3Sales.money}
								원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller3Sales.month}</td>
							<%-- <td style="border-bottom: 1px solid #444444; padding: 10px;">${sellerSales.phone}</td> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${seller3Sales.reg_date}" /><br></td>
						</tr>
					</c:forEach>
					<c:forEach items="${seller4Sales}" var="seller4Sales">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller4Sales.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller4Sales.email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller4Sales.money}
								원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller4Sales.month}</td>
							<%-- <td style="border-bottom: 1px solid #444444; padding: 10px;">${sellerSales.phone}</td> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${seller4Sales.reg_date}" /><br></td>
						</tr>
					</c:forEach>
					<c:forEach items="${seller5Sales}" var="seller5Sales">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller5Sales.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller5Sales.email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller5Sales.money}
								원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller5Sales.month}</td>
							<%-- <td style="border-bottom: 1px solid #444444; padding: 10px;">${sellerSales.phone}</td> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${seller5Sales.reg_date}" /><br></td>
						</tr>
					</c:forEach>
					<c:forEach items="${seller6Sales}" var="seller6Sales">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller6Sales.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller6Sales.email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller6Sales.money}
								원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller6Sales.month}</td>
							<%-- <td style="border-bottom: 1px solid #444444; padding: 10px;">${sellerSales.phone}</td> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
									pattern="yyyy-MM-dd" value="${seller6Sales.reg_date}" /><br></td>
						</tr>
					</c:forEach>
				</table>
				<div style="height:100px;"></div>
			</article>
		</main>
	</form>

</body>
</html>