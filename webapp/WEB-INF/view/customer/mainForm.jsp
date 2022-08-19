<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
html, body {
	height: 100%;
}

.swiper-container {
	width: 100%;
	height: 100%;
}

.swiper-slide {	
	text-align: center;
	font-size: 18px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.swiper-slide img {
	display: block;
	width: 120%; 
	height: 100%;
	object-fit: cover;
}

.best ul li {
	padding-left: 22px;
}

.img {
	width: 100%;
	vertical-align: middle;
	align-items: center;
}

.tit_main {
	height: 52px;
	color: #333;
	font-family: "ProximaNova-Thin";
	font-size: 48px;
	text-transform: uppercase;
	text-align: center;
	margin-bottom: 15px;
}

h2 {
	display: block;
	font-size: 1.5em;
	margin-block-start: 0.83em;
	margin-block-end: 0.83em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	font-weight: normal;
}

.recommend_detail {
	margin-left: 30%;
	/* margin-top: -615px; */
}

.before_sale_price .price {
	color: #777;
	font-size: 14px;
	letter-spacing: -0.5px;
	text-decoration: line-through;
	vertical-align: middle;
}

.cunit_thmb_lst>li {
	display: inline-block;
	vertical-align: top;
	padding-top: 30px;
	width: 300px; 
	margin-right: 30px;
	padding-bottom: 24px;
}

ul {
	list-style: none;
	font-size: 12px;
}

.before_sale_price .price {
	color: #777;
	font-size: 14px;
	letter-spacing: -0.5px;
	text-decoration: line-through;
	vertical-align: middle;
}


.notice_list {
	display: block;
	list-style-type: disc;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	padding-inline-start: 20px;
}

.notice_item {
	display: inline-block;
	overflow: hidden;
	max-width: 100%;
	font-size: 12px;
	line-height: 12px;
	color: #666;
	vertical-align: top;
	text-overflow: ellipsis;
	white-space: nowrap;
}

li {
	display: list-item;
	text-align: -webkit-match-parent;
}

a {
	text-decoration: none;
	cursor: pointer;
	color: #666;
}
.best_p_list{
	/* width: 1800px; */
	margin: 20px;
	padding: 20px;
	text-align: center;
}
.best_p_list > ul{
	padding: 50px;
}
.recommend_list_area{
	margin:20px;
	padding: 0px;
}
.notice_title {
	margin-bottom: 11px;
	padding: 8px 0;
	border-bottom: 2px solid #222;
}

.notice_area {
	position: relative;
	float: left;
	width: 30%;
	margin-left: 100px;
	height: 150px;
}

.QnA_area {
	position: relative;
	float: left;
	width: 30%;
	margin-left: 400px;
	height: 150px;
}

.notice_all_area {
	overflow: hidden;
	margin: 0 auto;
	padding: 30px;
	background-color: #e9e9e9;
}

.main_page_url {
	overflow: hidden;
	margin: 0 auto;
	padding: 30px 0px 30px 30px;
	background-color: #e9e9e9;
	text-align: center;
	font-size: 50px;
	font-weight: bold;
	color: #222
}

.promotion {
	width: 1160px;
	padding-top: 225px;
	/* padding-left: 115px; */
	margin: 0 auto;
}

.sub {
	font-size: 54px;
	font-weight: 100;
	/* padding-left: 5px; */
	color: #222;
	width: 700px;
	/* height: 28px; */
	overflow: hidden;
	line-height: 80px;
	/* letter-spacing: -1.2px; */
}

.title1 {
	font-size: 54px;
	font-weight: 700;
	line-height: 100%;
	color: #222;
	width: 700px;
	line-height: 80px;
	overflow: hidden;
	margin-top: -8px;
}

.title2 {
	font-size: 24px;
	font-weight: 400;
	color: #759510;
	width: 700px;
	/* letter-spacing: -2.7px; */
	line-height: 33px;
	overflow: hidden;
	padding-left: 5px;
	margin-top: 20px;
}

header {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	justify-content: space-between;
	align-items: center;
	z-index:10;
}

body {
	margin: 0px
}
</style>
<script type="text/javascript">
   function insertLike(pseq){
      form.action = "/WUE/customer/like="+pseq;
      form.submit();
   }
   
   function disLike(pseq){
      form.action= "/WUE/customer/dislike="+pseq;
      form.submit();
   }
</script>
</head>
<body>

	<header>
		<jsp:include page="header.jsp" />
	</header>
	
	<div style="height: 110px;"></div>
	
	<form action="" name='form'>
		<!-- Swiper -->
		<div class="swiper-container mySwiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="/WUE/img/add1.PNG" />
				</div>
				<div class="swiper-slide">
					<img src="/WUE/img/welcomecustomer.png" /><!-- 광고 이미지 -->
				</div>
			</div>
 			<div class="swiper-button-next" style="z-index:5;"></div>
			<div class="swiper-button-prev" style="
			z-index:5;"></div>
			<div class="swiper-pagination" style="top:650px; z-index:5;" ></div>
		</div>
		<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
		<script>
		var swiper = new Swiper(".mySwiper", {
			spaceBetween : 30,
			centeredSlides : true,
			autoplay : {
				delay : 2500,
				disableOnInteraction : false,
			},
			pagination : {
				el : ".swiper-pagination",
				clickable : true,
			},
			navigation : {
				nextEl : ".swiper-button-next",
				prevEl : ".swiper-button-prev",
			},
		});
	</script>

<!-- 		<div class="content slick-slide"
			style="background-image: url(/WUE/img/mainad.jpg); width: 100%; height: 600px;"
			data-slick-index="6" aria-hidden="true" tabindex="-1">
			<div class="promotion">
				<div class="sub">핑크빛~ 붉은빛~</div>
				<div class="title1">취향저격 복숭아 찾기</div>
				<div class="title2" style="color: #917254">당신의 복숭아 취향은 무엇인가요?</div>
				<div class="comment"></div>
			</div>
		</div> -->
	<div style="width:1920px; align:center;">	
		<div style="height: 200px;"></div>

		<div class="best">
			<h2 class="tit_main">Best Product</h2>
			<!-- <div style="height: 40px;"></div> -->
			<div class = "best_p_list">
			<ul class="cunit_thmb_lst">
				<c:forEach var="product" items="${products}">
					<li class="cunit_t232">
						<div class="cunit_prod">
							<c:choose>
								<c:when test="${product.quantity == 0}">
									<img src="/WUE/img/Simage/${product.url}" style="width: 250px; height: 250px;">
								</c:when>
								<c:otherwise>
									<a href="/WUE/customer/pseq=${product.pseq}">
										<img src="/WUE/img/Simage/${product.url}" style="width: 250px; height: 250px;">
									</a>
								</c:otherwise>
								</c:choose>
						
						</div> 
						<c:choose>
							<c:when test="${product.like eq 'Exist'}">
								<div style="margin-left: 200px;">
									<!-- //좋아요 한 상품 -->
									<i class="fas fa-heart fa-xl"
										onclick="disLike(${product.pseq})"></i>
									<%-- 									<input type="button" value="좋아요 취소"
										onclick="disLike(${product.pseq})"> --%>
								</div>
							</c:when>
							<c:otherwise>
								<div style="margin-left: 200px;">
									<!-- //좋아요하지 않은 상품 -->
									<%-- <i class="fas fa-heart-broken fa-xl" onclick="insertLike(${product.pseq})"></i> --%>
									<%-- <input type="button" value="좋아요" onclick="insertLike(${product.pseq})"> --%>
									<button onclick="insertLike(${product.pseq})">
										<img src="/WUE/img/pngegg.png"
											style="width: 18px; height: 18px;">
									</button>
								</div>
							</c:otherwise>
						</c:choose>
						<div style="height: 10px;"></div>
						<div class="cunit_info">
							<div class="title">
								<Strong> 
									<c:choose>
										<c:when test="${product.quantity == 0}">
											<span> ${product.name} </span>
											<p>
												<span> 품절 </span>
										</c:when>
										<c:otherwise>
											<a style="padding-left: 30px; font-size: 20px;"
												href="/WUE/customer/pseq=${product.pseq}">${product.name}</a>
										</c:otherwise>
									</c:choose>
								</Strong>
							</div>
							<div style="padding-left: 30px; font-size: 15px;">
								<c:choose>
									<c:when test="${product.sale eq 'y'}">
										<div class="cunit_price">
											<div class="before_sale_price">
												<em class="price">${product.price}</em> <span>원</span>
											</div>

											<div class="product_price">
												<em class="price">${product.sale_price}</em> <span>원</span>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="cunit_price">
											<div class="product_price">
												<em>${product.price}</em> <span>원</span>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
			</div>
		</div>

		<div style="height: 150px;"></div>

		<div class="recommend">
			<h2 class="tit_main">
				Recommend Product<a href="/Beauty" class="plus"></a>
			</h2>
			<div style="height: 60px;"></div> 
			<div class="recommend_list_area">
				<!-- 광고 부분 -->
				<div class="ad" style="float: left;">
					<img style="width: 100%; margin-top:20px; margin-left:10px; margin-right:250px; padding-left: 40px" src="/WUE/img/mainadd2.jpg">
				</div>
				<!-- 추천 상품 부분 -->
				<div class="recommend_detail">
					<ul class="cunit_thmb_lst" style="margin: 0px; padding: 10px; text-align: center;">
						<c:forEach var="Rproduct" items="${Rproducts}">
							<li class="cunit_t232" style="padding: 10px 20px 40px 10px; width:300px;">
								<div class="cunit_prod">
									<c:choose>
										<c:when test="${Rproduct.quantity == 0}">
											<img src="/WUE/img/Simage/${Rproduct.url}" style="width: 250px; height: 250px;">
										</c:when>
										<c:otherwise>
											<a style="font-size: 12px;" href="/WUE/customer/pseq=${Rproduct.pseq}">
												<img src="/WUE/img/Simage/${Rproduct.url}" style="width: 250px; height: 250px;">
											</a>
										</c:otherwise>
									</c:choose>
									
								</div> 
								<c:choose>
									<c:when test="${Rproduct.like eq 'Exist'}">
										<div style="margin-left: 180px;">
											<!-- //좋아요한 상품 -->
											<i class="fas fa-heart fa-xl"
												onclick="disLike(${Rproduct.pseq})"></i>
											<%-- <input type="button" value="좋아요 취소"
												onclick="disLike(${Rproduct.pseq})"> --%>
										</div>
									</c:when>
									<c:otherwise>
										<div style="margin-left: 180px;">
											<button onclick="insertLike(${Rproduct.pseq})">
												<img src="/WUE/img/pngegg.png"
													style="width: 18px; height: 18px;">
											</button>
										</div>
									</c:otherwise>
								</c:choose>
								<div style="height: 10px;"></div>
								<div class="cunit_info">
									<div class="title">
										<Strong>
											<c:choose>
												<c:when test="${Rproduct.quantity == 0}">
													<span> ${Rproduct.name} </span>
													<p>
														<span> 품절 </span>
												</c:when>
												<c:otherwise>
													<a style="font-size: 12px;"
														href="/WUE/customer/pseq=${Rproduct.pseq}">${Rproduct.name}</a>
												</c:otherwise>
											</c:choose>
										</Strong>
									</div>
									<div style="font-size: 12px;">
										<c:choose>
											<c:when test="${Rproduct.sale eq 'y'}">
												<div class="cunit_price">
													<div class="before_sale_price">
														<em class="price">${Rproduct.price}</em> <span>원</span>
													</div>
	
													<div class="product_price">
														<em class="price">${Rproduct.sale_price}</em> <span>원</span>
													</div>
												</div>
											</c:when>
											<c:otherwise>
												<div class="cunit_price">
													<div class="product_price">
														<em>${Rproduct.price}</em> <span>원</span>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div style="height: 200px;"></div>
		<div class="notice_all_area">
			<div class="notice_area">
				<div class="notice_title">
					<span>공지사항</span>
				</div>
				<div class="notice_content">
					<ul class="notice_list">
						<c:forEach var="notice" items="${noticeList}">
							<li class="notice_item"><a class="notice_click"
								href="notice?wseq=${notice.wseq}">${notice.title}</a></li>
							<p>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="QnA_area">
				<div class="notice_title">
					<span>자주하는 질문</span>
				</div>
				<div class="QnA_content">
					<ul class="notice_list">
						<c:forEach var="qna" items="${qnaList}">
							<li class="notice_item"><a class="notice_click"
								href="notice?wseq=${qna.wseq}">${qna.title}</a></li>
							<p>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		<div class="main_page_url">
			<a href="/WUE/index"> What You Eat </a>


		</div>
	</div>
	</form>
	

</body>
</html>