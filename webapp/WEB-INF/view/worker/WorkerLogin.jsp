<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
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
<title>Worker Login Page</title>
</head>
<body>
	<header class="header">
		<!-- <div class="header" style="background-color: #FEDD89; color: #31383F; height: 100px; padding: 5px;"> -->
		<div class="header__img">
			<img src="/WUE/img/logoWorker.png" />
			<!-- <img src="img/simbol.png" style="width:100px; height:100px;"/>	 -->
		</div>
		<div style="margin-right: 125px; float: right; margin-top: -85px;"><%=sf.format(nowTime)%></div>
		<div style="margin: 20px; float: right; margin-top: -85px;"><span id="dpTime">오후 00:00:00</span></div>		
		<div class="header__img" style="margin-top: -50px;">
			<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span> 관리자 모드 </span>
		</div>
	</header>
	<script src="https://kit.fontawesome.com/4a9dbb7224.js"
		crossorigin="anonymous"></script>

	<nav class="menu" style="margin-top:40px;">
		<table>
			<tr>
				<th style="border: 4px outset pink;">관리자 정보</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">회원 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">상품 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">이벤트 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">게시판 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">주문내역 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">매출 관리</th>
			</tr>
		</table>
	</nav>
	<form action='/WUE/worker/login' method='post'>
		<div class="login">
			<fieldset style="margin: auto; width: 250px; border: solid pink;">
				<legend>로그인</legend>
				ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="text" name="id" value="${member.id}">${emptyId}
				<p>
					Password&nbsp;<input type="password" name="pw">
				<p>
				<div class="button">
					<input style="border: 1.5px solid pink; background-color: #FFF0F5;"
						type="submit" value="로그인"> <input
						style="border: 1.5px solid pink; background-color: #FFF0F5;"
						type="reset" value="취소"> <input
						style="border: 1.5px solid pink; background-color: #FFF0F5;"
						type="button" value="회원가입" onclick="location.href='add'">
				</div>
			</fieldset>
		</div>
	</form>
</body>
</html>