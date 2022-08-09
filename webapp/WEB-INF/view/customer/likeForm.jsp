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
	function clickBtn() {
	   form.action = "/WUE/customer/like/delete"; 
	   form.submit(); 
	}
</script>
</head>
<body>
	<form action="" name='form'>
	<span>좋아요 누른 상품</span>
	<ul class="cunit_thmb_lst">
	<c:forEach var="like" items="${likeList}">
		
		<li class="cunit_t232">
				<div class = "cunit_prod">
					<input type="checkbox" name="likeid" value="${like.pseq}">
					<img src="/WUE/img/Simage/${like.url}" style="width:100px; height:100px;">
				</div>
			<div class = "cunit_info">
				<div class = "title">
					<a href="/WUE/customer/pseq=${like.pseq}"><span>${like.pname}</span></a>
				</div>
				<div>
					<c:choose>
						<c:when test="${like.sale eq 'y'}">
						<div class="cunit_price">
							<div class="before_sale_price">
								<em class = "price">${like.price}</em>
								<span>원</span>	
							</div>
							
							<div class = "product_price">
								<em class = "price">${like.sale_price}</em>
								<span>원</span>									
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<div class="cunit_price">
							<div class = "product_price">
								<em>${like.price}</em>
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
	<input type="button" value="선택 상품 삭제하기" onclick="clickBtn()"/>
	</form>
</body>
</html>