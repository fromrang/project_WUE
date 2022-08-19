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
<title>order</title>
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
							<li><a href="">상품 관리 &nbsp;&nbsp;&nbsp;</a>
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
	<form action='order' method='get' name="order">
		<main class="to_do_list">
			<article
				style="padding-left: 150px; padding-right: 100px; z-index: 1;">
				<h3>Order List</h3>
				<p>&nbsp;
				<h4>주문 상태</h4>
				<div
					style="color: #31383F; height: 50px; padding: 5px; font-size: 20px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color: pink;"><a
						href="order_catagory?result=0&sseq=${sseq}">상품 준비 중</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="order_catagory?result=1&sseq=${sseq}">주문 확인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="order_catagory?result=2&sseq=${sseq}">배송 시작</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="order_catagory?result=3&sseq=${sseq}">배송 완료</a></span>
				</div>
				<p>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 5%;"><b>주문번호</b></th>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 14%;"><b>주문날짜</b></th>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 15%;"><b>주문상태</b></th>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 14%;"><b>이메일</b></th>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 15%;"><b>연락처</b></th>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 25%;"><b>주문
								상품명</b></th>
						<th
							style="border-bottom: 1px solid #444444; padding: 10px; width: 12%;"><b>주문상품
								합계 </b></th>
					</tr>
					<c:choose>
						<c:when test="${order!='[]'}">
							<c:forEach var="i" begin="0" end="${order.size()-1}">
								<tr>

									<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].oseq}
									</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;

										<fmt:formatDate value="${order[i].mod_date}"
											pattern="yyyy-MM-dd" />
									</td>
									<c:choose>
										<c:when test="${order[i].result == '0'}">
											<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;상품
												준비중</td>
										</c:when>
										<c:when test="${order[i].result == '1'}">
											<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;주문
												확인</td>
										</c:when>
										<c:when test="${order[i].result == '2'}">
											<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;배송
												시작</td>
										</c:when>
										<c:when test="${order[i].result == '3'}">
											<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;배송
												완료</td>
										</c:when>
									</c:choose>

									<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;${order[i].email}</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;${order[i].phone}</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;${order[i].pname}
										<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="주문상세 조회"
										onclick="location.href='order_detail?oseq=${order[i].oseq}&sseq=${sseq}'">
									</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${sum[i]} 원</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><p>관련 내역이 현재없습니다.</p></td>	
								<td></td>						
							<tr>						
						</c:otherwise>
					</c:choose>

				</table>
			</article>
		</main>
	</form>
</body>
</html>