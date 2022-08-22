<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
        ul {
    		list-style: none;
		}
		.before_sale_price .price {
    		color: #777;
		    font-size: 14px;
		    letter-spacing: -0.5px;
		    text-decoration: line-through;
		    vertical-align: middle;
		}
		.cunit_thmb_lst > li {
		    display: inline-block;
		    vertical-align: top;
		    padding-top: 30px;
		    width: 232px;
		    margin-right: 30px;
		    padding-bottom: 24px;
		}
	  	main{
			margin-top: 160px;
		}
		header {
		  position: fixed;
		  top: 0;
		  left: 0;
		  right: 0;
		  justify-content: space-between;
		  align-items: center;
		  z-index: 1;
		}				

</style>
<script type="text/javascript">
//이름 / 값 / 저장 시킬 시간
/* 	function SetCookie(cookie_name, value, hours) {
	    if (hours) {
	        var date = new Date();
	        date.setTime(date.getTime() + (hours * 60 * 60 * 1000));
	        var expires = "; expires=" + date.toGMTString();
	    } else {
	        var expires = "";
	    }
	    document.cookie = cookie_name+ "=" + value + expires + "; path=/";
	} */
	
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
<jsp:include page = "header.jsp"/>
</header>
<main>
<form action="" name="form">
<div class="product_List" id ="area_itemlist">
		<ul class="cunit_thmb_lst">
			<c:forEach var="product" items="${products}">
				<li class="cunit_t232">
					<div class = "cunit_prod">
						<img src="/WUE/img/Simage/${product.url}" style="width:100px; height:100px;">
					</div>
					<c:choose>
						<c:when test="${product.like eq 'Exist'}">
							<div style="margin-left: 150px;">
								<button value="좋아요 취소" onclick="disLike(${products[i].pseq})">
									<i class="fas fa-heart fa-xl" onclick="disLike(${product.pseq})"></i>
								</button>
							</div>
						</c:when>
						<c:otherwise>
							<div style="margin-left: 150px;">
								<button value="좋아요" onclick="insertLike(${products[i].pseq})">
									<img src="/WUE/img/pngegg.png" style="width: 18px; height: 18px;">
								</button>
							</div>
						</c:otherwise>
					</c:choose>
					
					<div class = "cunit_info">
						<div class = "title">
							<c:choose>
								<c:when test="${product.quantity == 0}">
									<span>
										${product.name}
									</span><p>
									<span>
										품절
									</span>
								</c:when>
								<c:otherwise>
									<a href="/WUE/customer/pseq=${product.pseq}">${product.name}</a>								
								</c:otherwise>
							</c:choose>
						</div>
						<div>
							<c:choose>
								<c:when test="${product.sale eq 'y'}">
								<div class="cunit_price">
									<div class="before_sale_price">
										<em class = "price">${product.price}</em>
										<span>원</span>	
									</div>
									
									<div class = "product_price">
										<em class = "price">${product.sale_price}</em>
										<span>원</span>									
									</div>
								</div>
								</c:when>
								<c:otherwise>
								<div class="cunit_price">
									<div class = "product_price">
										<em>${product.price}</em>
										<span>원</span>									
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
</form>
</main>
</body>
</html>