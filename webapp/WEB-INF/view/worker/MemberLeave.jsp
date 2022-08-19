<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript">
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
<title>Member Leave</title>
</head>
<body>
	<form action='management' method='get'>
		<header class="header">
			<div class="header__img">
				<img src="/WUE/img/logoWorker.png" />
			</div>

			<div style="margin-right: 125px; float: right; margin-top: -85px;"><%=sf.format(nowTime)%></div>
			<div style="margin: 20px; float: right; margin-top: -85px;">
				<span id="dpTime">오후 01:44:40</span>
			</div>
			<div class="header__img" style="margin-top: -50px;">
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
				<tr>
					<th style="border: 4px outset pink;">상품 관리</th>
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

		<main class="to_do_list">
			<article style="padding-left: 50px;">
				<h3>Customer List</h3>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Name</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Email</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Password</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Phone</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>zip_code</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Address</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Grade</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Status</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>reg_date</b></th>
					</tr>
					<tr></tr>
					<c:forEach items="${customer}" var="customer">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><a
								href="update?email=${customer.email}">${customer.email}</a></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.pw}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.phone}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.zip_code}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.address}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.grade}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${customer.status}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">
								<fmt:formatDate pattern="yyyy-MM-dd"
									value="${customer.reg_date}" /> <input
								style="float: right; margin: 5px;" type="button" value="탈퇴"
								onclick="location.href='delete?email=${customer.email}'">
							</td>
						</tr>
					</c:forEach>
				</table>
			</article>
		</main>


		<main class="visit" style="padding: 60px 0;">
			<article style="padding-left: 50px;">
				<h3>Seller List</h3>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Name</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Email</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Password</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Phone</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>zip_num</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Address</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>Status</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>reg_date</b></th>
					</tr>
					<tr></tr>
					<c:forEach items="${seller}" var="seller">
						<tr align="center">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller.name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><a
								href="update?email=${seller.email}">${seller.email}</a></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller.pw}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller.phone}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller.zip_num}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller.address}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${seller.status}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">
								<fmt:formatDate pattern="yyyy-MM-dd" value="${seller.reg_date}" />
								<input style="float: right; margin: 5px;" type="button"
								value="탈퇴"
								onclick="location.href='delete?email=${seller.email}'">
							</td>
						</tr>
					</c:forEach>
				</table>
			</article>
		</main>
	</form>
</body>
</html>