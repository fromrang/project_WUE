<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
/* ul li태그에 리스트 스타일을 없앰 */
ul li {
   list-style: none;
}
/* a태그에 텍스트 밑줄을 없애고 색상을 #333 */
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
<title>Work Main Page</title>
</head>
<body>
	<header class="header">
		<div class="header__img">
			<img src="/WUE/img/logoWorker.png" />
		</div>
		
			<div style="margin-right: 125px; float: right; margin-top: -85px;"><%=sf.format(nowTime)%></div>
			<div style="margin: 20px; float: right; margin-top: -85px;">
				<span id="dpTime">오후 01:44:40</span>
			</div>
			<div class="header__img" style="margin-top: -50px;">
				<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span>${authInfo.name} 님 로그인</span>
			</div>
		
	</header>
	<script src="https://kit.fontawesome.com/4a9dbb7224.js"
		crossorigin="anonymous"></script>
	<form action='update' method='post'>
		<nav class="menu" style="top:15px;">
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
					<th style="border: 4px outset pink;" onclick="location.href='seller_order'">주문내역 관리</th>
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
		      <main class="update" style="margin-left:800px; margin-top:250px;">
		      
         <article>
            <table
               style="border: 1px solid black; background-color: #FFF0F5; border-color: #FFF0F5;">
               <tr>
                  <th>Name</th>
                  <td><input type="text" name="name" value="${worker.name}"
                     readonly /></td>
               </tr>
               <tr>
                  <th>ID</th>
                  <td><input type="text" name="id" value="${worker.id}"
                     readonly /></td>
               </tr>
               <tr>
                  <th>PW</th>
                  <td><input type="text" name="pw" value="${worker.pw}" /></td>
               </tr>
               <tr>
                  <th>Email</th>
                  <td><input type="text" name="email" value="${worker.email}"
                     readonly /></td>
               </tr>
               <tr>
                  <th>Phone</th>
                  <td><input type="text" name="phone" value="${worker.phone}" /></td>
            </table>
            <div style="align-item: center; margin: 10px 0 0 50px;">
               <input style="background-color: #FFF0F5; border-color: #FFF0F5;"
                  type="submit" value="회원정보 수정" /> <input
                  style="background-color: #FFF0F5; border-color: #FFF0F5;"
                  type="reset" value="취소" onclick="location.href='list'" />
            </div>
         </article>
      </main>
	</form>
</body>
</html>