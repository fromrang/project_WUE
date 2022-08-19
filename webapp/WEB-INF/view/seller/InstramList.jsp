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
<content ="width=device-width, initial-scale=1.0"> <jsp:include
	page="header.jsp" />
<title>Insert title here</title>
<style>
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
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.r {
 	width: 50%;
	border: 1px solid Gray;
	position: relative;
	top: 110px;
	margin-top: 5px;	
	margin-bottom: 50px;
	box-shadow: 0 2px 0 2px rgba(0, 0, 0, 0.12);
	margin-left: 200px;
}
</style>
<!-- <script type="text/javascript">

let curPos = 0;
let postion = 0;
const IMAGE_WIDTH = 400;
const prevBtn = document.querySelector(".prev")
const nextBtn = document.querySelector(".next")
const images = document.querySelector(".images")
 
function prev(){
  if(curPos > 0){
    nextBtn.removeAttribute("disabled")
    postion += IMAGE_WIDTH;
    images.style.transform = `translateX(${postion}px)`;
    curPos = curPos - 1;
  }
  if(curPos == 0){
    prevBtn.setAttribute('disabled', 'true')
  }
}
function next(){
  if(curPos < 3){
    prevBtn.removeAttribute("disabled")
    postion -= IMAGE_WIDTH;
    images.style.transform = `translateX(${postion}px)`;
    curPos = curPos + 1;
  }
  if(curPos == 3){
    nextBtn.setAttribute('disabled', 'true')
  }
}
 
function init(){
  prevBtn.setAttribute('disabled', 'true')
  prevBtn.addEventListener("click", prev)
  nextBtn.addEventListener("click", next)
}
 
init();
	}
</script>
 -->
</head>
<body>
	<!-- <div style="height: -20px;"></div>
	Swiper
	<div class="swiper-container mySwiper">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<img src="/WUE/img/mainad.jpg" />
			</div>
			<div class="swiper-slide">
				<img src="/WUE/img/farm5.png" />
			</div>
		</div>
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
		<div class="swiper-pagination" style="top:1100px;"></div>
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
	</script> -->
	<div style="height: 100px;"></div>
	<div style="text-align: center">
		&nbsp;&nbsp; Seller's Story <br>&nbsp;&nbsp; 농부님들 이야기
	</div>
	<c:forEach var="i" begin="0" end="${posting.size()-1}">

		<div class="r" style="margin-left: 500px;">

			<div class="title_image"
				style="position: relative; left: 4px; top: 3px">

				<c:choose>

					<c:when test="${posting[i].url==''}">

						<a href="instagramProfile?email=${posting[i].email}"><img
							style="border-radius: 30px; height: 50px; width: 50px"
							src="/WUE/img/noimage.png" class="tiny_image" align="left">
						</a>

					</c:when>

					<c:otherwise>
						<a href="instagramProfile?email=${posting[i].email}"><img
							style="border-radius: 30px; height: 50px; width: 50px"
							src="/WUE/img/${posting[i].url}" class="tiny_image" align="left">
						</a>

					</c:otherwise>

				</c:choose>

			</div>

			<div class="userid_txt"
				style="font-family: arial; position: relative; left: 10px; top: 20px">

				<a href="instagramProfile?email=${posting[i].email}">${posting[i].email}</a>

			</div>

			<div id="container"
				style="position: relative; top: 10px; width: 500px; margin: 0 auto;">

				<div class="album" style="overflow: hidden;">


					<c:forEach var="cmt" items="${posting[i].postimages}">


						<div class="images" style="position: relative; top: 30px;">
							<img src="/WUE/img/${cmt}"
								style="border-radius: 20px; height: 400px; width: 450px; display: block; margin-left: auto; margin-right: auto; margin-top: 5px;">
						</div>
					</c:forEach>

				</div>
			</div>

			<div class="write" style="margin: 20px">
				${posting[i].context} <br />
				<p></p>
				<span style=""
					onclick="location.href='instagramSelectPost?email=${posting[i].email}&postnumber=${posting[i].postnumber}'">
					댓글 ${replycount1[i]}개 모두 보기</span>
			</div>
		</div>
		<div style="margin-top: 70px;"></div>
	</c:forEach>


	<!-- ================================================================= -->

	<c:forEach var="k" begin="0" end="${posting2.size()-1}">
		<div class="r" style="margin-left: 500px;">
			<div class="title_image"
				style="position: relative; left: 4px; top: 3px">
				<c:choose>

					<c:when test="${posting2[k].getFramList().get(0).url==''}">
						<a
							href="instagramProfile?email=${posting2[k].getFramList().get(0).email}"><img
							style="border-radius: 30px; height: 50px; width: 50px"
							src="/webShopping/image/noimage.png" class="tiny_image"
							align="left"> </a>

					</c:when>

					<c:otherwise>
						<a
							href="instagramProfile?email=${posting2[k].getFramList().get(0).email}"><img
							style="border-radius: 30px; height: 50px; width: 50px"
							src="/WUE/img/${posting2[k].getFramList().get(i).url}"
							class="tiny_image" align="left"> </a>

					</c:otherwise>
				</c:choose>
			</div>

			<div class="userid_txt"
				style="font-family: arial; position: relative; left: 10px; top: 20px">
				<a
					href="instagramProfile?email=${posting2[k].getFramList().get(0).email}">${posting2[k].getFramList().get(0).email}</a>
			</div>


			<div id="container"
				style="position: relative; top: 10px; width: 500px; margin: 0 auto;">

				<div class="album" style="overflow: hidden;">

					<c:forEach var="cmt"
						items="${posting2[k].getFramList().get(0).postimages}">


						<div class="images" style="position: relative; top: 30px;">
							<img src="/WUE/img/${cmt}"
								style="border-radius: 20px; height: 400px; width: 450px; display: block; margin-left: auto; margin-right: auto; margin-top: 5px;">
						</div>
					</c:forEach>


				</div>
			</div>

			<div class="write" style="margin: 20px">

				${posting2[k].getFramList().get(0).experience_context} <br />


				<table
					style="width: 80%; border-top: 1px solid #444444; border-collapse: collapse;">

					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>index</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>예약
								가능날짜</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>예약
								가능시간</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>예약
								금액(총합)</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>체험가능
								인원</b></th>
					</tr>
					<c:forEach var="i" begin="0"
						end="${posting2[k].getFramList().size()-1}">
						<tr>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${i+1}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${posting2[k].getFramList().get(i).experience_date}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${posting2[k].getFramList().get(i).experience_time}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${posting2[k].getFramList().get(i).experience_price}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${posting2[k].getFramList().get(i).experience_number}</td>
						</tr>
					</c:forEach>
					<tr>
					</tr>
				</table>
				<p>
					<span style="margin-top: 50px;"
						onclick="location.href='instagramSelectfarm?email=${posting2[k].getFramList().get(0).email}'">
						댓글 ${replycount2[k]}개 모두 보기</span>
			</div>
		</div>

	</c:forEach>
</body>
</html>