<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="/WUE/css/import.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
ul li {
	list-style: none;
}

a {
	text-decoration: none;
	color: #333;
}

#menu2 {
	font: bold 16px "malgun gothic";
	width: 180px;
	color: black;
	line-height: 30px;
	margin: 0 auto;
	text-align: center;
}

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

.profile_image img {
	width: 250px;
	height: 250px;
	top: 100px;
	margin-left: 555px;
	border-radius: 50%;
}

.product_add {
	margin-left: 585px;
}

.profile {
	margin-top: -95px;
	margin-left: 810px;
	font-size: 20px;
}

.adjust {
	margin-top: 40px;
	margin-left: 808px;
	font-size: 20px;
}

.product_count {
	margin-top: 40px;
	margin-left: 350px;
	font-size: 20px;
}

.order_list {
	margin-top: 40px;
	margin-left: 350px;
	font-size: 20px;
}

.market_price {
	margin-left: 80px;
	margin-top: 50px;
	font-size: 20px;
}

.ranking {
	margin-left: 850px;
	margin-top: -540px;
	font-size: 20px;
}
</style>
<jsp:include page="header.jsp" />
</head>
<body>
	<article>
		<main class="to_do_list">
			<div class="profile_image">
				<a href="instagramProfile?email=${authInfo.email}"> <img
					src="/WUE/img/${user.url}" id="preview" class="image">
				</a> <input type="hidden" id="frist"
					value="http://localhost:8080/webShopping/image/${user.url}">
				<input type="hidden" name="url" value="${user.url}">
			</div>
			<div style="height: 15px;"></div>
			<strong><span style="margin-left: 650px;">${authInfo.name}</span></strong>
			<div style="height: 15px;"></div>
			<div class="product_add">
				<a href="ProductAdd"><input
					style="width: 180px; font-size: 20px; background-color: white; border-radius: 20px; border: solid 3px #99ccff;"
					type="button" value="상품 등록하기"></a>
			</div>
			<div style="height: 60px;"></div>
			<div class="product_count">
				<a href="ProuctList">상품 등록 수</a>&nbsp;&nbsp;&nbsp; <strong>${count}</strong>
				건
			</div>
			<div class="order_list">
				<a href="SOrderList">주문 현황 수</a>&nbsp;&nbsp;&nbsp; <strong>${count2.size()}</strong>
				건
			</div>
			<div class="profile">
				<i class="fab fa-instagram fa-2xl"></i> <a href="myinstagramProfile">
					&nbsp;&nbsp;인스타그램</a>
			</div>
			<div class="adjust">
				<i class="fas fa-user-circle fa-2xl"></i><a href="SUpdate">&nbsp;
					프로필 수정</a>
			</div>
			<div style="height: 100px;"></div>
			<div class="market_price">
				<i class="fas fa-chart-line fa-2xl"></i>&nbsp;&nbsp; 판매량
				<p>							
				<img style="margin-top:60px; width:550px; height:400px;" src="/WUE/img/market_price.jfif">
			</div>
			<div style="height: 60px;"></div>
			<div class="ranking">
				<i class="fas fa-medal fa-2xl"></i>&nbsp;&nbsp; 실시간 검색 순위
				<p>
				<img style="margin-top:60px;" src="/WUE/img/ranking.png">
			</div>
		</main>
	</article>
	<script src="https://kit.fontawesome.com/4a9dbb7224.js"
		crossorigin="anonymous"></script>
</body>
</html>


