<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
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
	<form action="" name='form'>
	<span>best 상품</span>
	<ul class="cunit_thmb_lst">
	<c:forEach var="product" items="${products}">
		<li class="cunit_t232">
				<div class = "cunit_prod">
					<img src="/WUE/img/Simage/${product.url}" style="width:100px; height:100px;">
				</div>
				<c:choose>
					<c:when test="${product.like eq 'Exist'}">
						<div>
							<input type="button" value="좋아요 취소" onclick="disLike(${product.pseq})">
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<input type="button" value="좋아요" onclick="insertLike(${product.pseq})">
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
	</form>
</body>
</html>