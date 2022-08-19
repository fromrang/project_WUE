<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 전체영역에서 여백을 없애줌 */
* {
	margin: 0;
	padding: 0;
}
/* ul li태그에 리스트 스타일을 없앰 */
ul li {
	list-style: none;
	text-align: center;
}
/* a태그에 텍스트 밑줄을 없애고 색상을 #333 */
a {
	text-decoration: none;
	color: #333;
}
/* 글자크기를 16px 맑은 고딕 굵게하고 width넓이 700, 높이 50만큼 배경색은 #ccc, 글자색은 검정색, 라인높이50px
menu박스 가운데정렬, 글자가운데 정렬 */
#menu {
	font: bold 16px "malgun gothic";
	width: 700px;
	height: 50px;
	color: black;
	line-height: 50px;
	margin: 0 auto;
	text-align: center;
}

/* menu태그 자식의 ul의 자식 li를 왼쪽정렬과 넓이 140설정 */
#menu>ul>li {
	float: left;
	width: 140px;
	position: relative;
}

div {
	margin: 0;
	padding: 0;
}

a {
	text-decoration-line: none;
	color: #12467a;
}

.search {
	position: absolute;
	text-align: center;
	margin-left: 600px;
	top: 15px;
	margin-top: 15px;
	padding: 0;
	list-style: none;
	border-radius: 7px;
	box-shadow: 0 2px 2px 2px rgba(0, 0, 0, 0.12);
	padding: 0;
}

.search input {
	width: 270px;
	height: 5px;
	text-align: center;
	border: 1px solid rgba(0, 0, 0, 0.2);
	border-radius: 7px;
	padding: 15px 0 15px 0;
	font-size: 15px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.2);
	transition: border-color 1s ease-in-out;
}

button {
	border: none;
	background: none;
	cursor: pointer;
}

#menu>ul>li>ul {
	width: 130px;
	display: none;
	position: absolute;
	font-size: 14px;
	background: skyblue;
}

#menu>ul>li:hover>ul {
	display: block;
}

#menu>ul>li>ul>li:hover {
	background: pink;
	transition: ease 1s;
}
</style>
</head>
<link rel="stylesheet" href="/middle/css/import.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<body>
	<div
		style="background-color: #99ccff; color: #31383F; height: 135px; padding: 5px;">
		<a href="javascript:history.back();"><img src="/WUE/img/simbol1.png"
			style="margin-left: 20px; padding-top: 10px; width: 105px; height: 105px;"></a>
			
			
			<c:choose>
					<c:when test="${authInfo.email!=null}">
		<div id="menu" style="margin-left: 600px; margin-top: -30px; font-size: 22px;">
				<ul>
					<li><a href="ProuctList">상품 관리&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<ul>
							<li><a href="ProductAdd">상품 등록&nbsp;&nbsp;&nbsp;</a></li>
							<li><a href="ProuctList">상품 내역&nbsp;&nbsp;&nbsp;</a></li>
						</ul></li>
				</ul>
				<ul>
					<li><a style="margin-left: 20px;" href="SOrderList"> 주문관리&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<ul>
							<li><a style="margin-left: 20px;" href="SOrderList"> 주문관리&nbsp;&nbsp;&nbsp;&nbsp;</a></li>										
						</ul></li>
				</ul>
				<ul>
					<li><a  style="padding-left: 20px;" href="Snotice">게시판&nbsp;&nbsp;&nbsp;&nbsp;</a>
						<ul>
							<li><a  style="padding-left: 20px;" href="Snotice">게시판&nbsp;&nbsp;&nbsp;&nbsp;</a></li>					
						</ul></li>				
				</ul>	
				
				<ul>
					<li><a style="" href="myinstagramProfile">인스타그램</a>
						<ul>
							<li><a href="myinstagramProfile">My
									WUE&nbsp;&nbsp;&nbsp;</a></li>
							<li><a href="instagramAll"> WUE LIST&nbsp;&nbsp;&nbsp;</a></li>
						</ul></li>
				</ul>
				<ul>
					<li><a style="padding-left: 30px;" href="myinstagramProfile">마이페이지</a>
						<ul>
							<li><a href="SMain">My
									PAGE&nbsp;&nbsp;&nbsp;</a></li>
							<li ><a href="SUpdate">회원 정보 수정 &nbsp;&nbsp;&nbsp;</a></li>
						</ul></li>
				</ul>
		</div>
		</c:when>
		</c:choose>
		<div style="margin-top: -55px; margin-left: 1450px; padding-left: 100px;">
			<c:choose>
				<c:when test="${authInfo==null&&cAuthInfo==null}">
					<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span>${authInfo.name}
						<a href="Slogin"> 로그인</a>
					</span>

				</c:when>
				<c:when test="${authInfo!=null}">
					<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span>${authInfo.name}
						님 로그인 중</span>

					<span><a style="color: blue;" href="Slogout">&nbsp;&nbsp;&nbsp;로그아웃</a>
					</span>
				</c:when>
			</c:choose>
		</div>

	</div>
	<script src="https://kit.fontawesome.com/4a9dbb7224.js"
		crossorigin="anonymous"></script>
</body>
</html>