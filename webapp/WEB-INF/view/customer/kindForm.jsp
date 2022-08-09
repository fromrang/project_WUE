<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<form action="" name="form">
<c:set var="size" value="${fn:length(products)}" />

<div class="product_List" id ="area_itemlist">
		<ul class="cunit_thmb_lst">
			<%-- <c:forEach var="product" items="${products}"> --%>
			<c:forEach var="i" begin="0" end="${size-1}">
				<li class="cunit_t232">
					<div class = "cunit_prod">
						<img src="/WUE/img/Simage/${imagebyProduct[i]}" style="width:100px; height:100px;">
						<span>${product[i].like}</span>
					</div>
					<c:choose>
						<c:when test="${products[i].like eq 'Exist'}">
							<div>
								<input type="button" value="좋아요 취소" onclick="disLike(${products[i].pseq})">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<input type="button" value="좋아요" onclick="insertLike(${products[i].pseq})">
							</div>
						</c:otherwise>
					</c:choose>
					
					<div class = "cunit_info">
						<div class = "title">
							<a href="/WUE/customer/pseq=${products[i].pseq}">${products[i].name}</a>
						</div>
						<div>
							<c:choose>
								<c:when test="${products[i].sale eq 'y'}">
								<div class="cunit_price">
									<div class="before_sale_price">
										<em class = "price">${products[i].price}</em>
										<span>원</span>	
									</div>
									
									<div class = "product_price">
										<em class = "price">${products[i].sale_price}</em>
										<span>원</span>									
									</div>
								</div>
								</c:when>
								<c:otherwise>
								<div class="cunit_price">
									<div class = "product_price">
										<em>${products[i].price}</em>
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
</body>
</html>