<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
<title>Insert title here</title>
<style type="text/css">
	.cart_list {
		margin: 50px;
	}
	.cart_list_up{
		margin: 10px;
		padding	: 30px 10px;
		font-size: 30px;
	}
	.codr_unit{
		padding	: 30px 10px;
	}
	table {
	    display: table;
	    border-collapse: separate;
	    box-sizing: border-box;
	    text-indent: initial;
	    border-spacing: 2px;
	    border-color: grey;
	    width: 80%;
	}
	tbody{
		display: table-row-group;
		vertical-align: middle;
		margin: 0;
		padding: 0;
		border: 0;
	}
	.area_image_item{
		position: relative;
		padding: 20px 0;
		vertical-align: top;
	}
	.image_unit_item{
		position: relative;
		vertical-align: top;
	}
	.blind {
	    overflow: hidden;
	    position: absolute;
	    width: 1px;
	    height: 1px;
	    margin: -1px;
	    padding: 0;
	    border: 0;
	    line-height: 0;
	    white-space: normal;
	    word-wrap: break-word;
	    word-break: break-all;
	    clip: rect(0, 0, 0, 0);
	}
	.image_chk{
		position: relative;
	    min-width: 15px;
	    display: inline-block;
	    min-height: 15px;
	    word-wrap: normal;
	    word-break: break-all;
	    vertical-align: top;
	    line-height: 16px;
	}
	.name_item {
	    position: relative;
	    min-height: 52px;
	    padding: 20px;
	    vertical-align: top;
	}
	.price_item {
	    padding: 20px 5px 20px 20px;
	    font-size: 0;
	}

	.codr_amount {
	    display: block;
	    position: relative;
	    width: 40px;
	    padding: 0 30px;
	    margin: 0;
	    
	}
	#quantity {
	    display: block;
	    width: 40px;
	    height: 28px;
	    border: 0;
	    background: none;
	    font-size: 14px;
	    line-height: 28px;
	    color: #777;
	    text-align: center;
	}
	
	.menu {
		position: fixed;
		top: 0;
		width: 20%;
		height: 100%;
		justify-content: flex-start;
		box-sizing: border-box;
	}
		
	.menu table {
		position: relative;
		margin-top: 113px;
		width: 80%;
		height: 85%;
		font-family: Arial, sans-serif;
	}
	.main_area{
		position: relative;
		margin-top: 200px;
		margin-left: 300px;	
	}
</style>
<script type="text/javascript">
	function cancelBtn(odseq, oseq, payment){
		form.action = "/WUE/customer/order/delete/odseq="+odseq+"&oseq="+oseq+"&payment="+payment;
		form.submit();
	}
</script>
</head>
<body>
<form action="" id="form">
<nav class="menu">
<table>
<tr>
<th onclick="location.href='mypage'">주문/배송 조회</th>
</tr>
<tr>
<th onclick="location.href='orderList'">구매 내역</th>
</tr>
<tr>
<th onclick="location.href='likeList'">좋아요</th>
</tr>
<tr>
<th onclick="location.href='review'">마이 리뷰</th>
</tr>
<tr>
<th onclick="location.href='change'">회원정보 변경</th>
</tr>
</table>
</nav>
<main class="main_area">
	<article style="padding-left: 50px;">
	<div>
		<h1>
			${sessionScope.authInfo.name}님
		</h1>
		<h2>
			point : ${customer.point}점	
		</h2>
	</div>
	<h3>
		주문/배송 조회
	</h3>
	<div class="codr_unit"> 
		<table class = "cart_table">
			<tbody>
				<c:forEach var="order" items="${orders}">
					<tr class="pay_item_area" id="a">
						<td class="area_image_item">
							<div class = "image_unit_item">
								<span class="image_item">
									<img src="/WUE/img/Simage/${order.url}">
								</span>	
							</div>
						</td>	
						<td class="name_item">
							<a href="/WUE/customer/pseq=${order.pseq}">${order.pname}</a></td>	
						<td class="price_item_quantity">
							<div class="cunit_price">
								<div class = "product_price">
									<span class="paymeny">
										<em>${order.payment}</em>
										<span>원</span>
									</span>
								</div>
							</div>
							<div class="codr_unit_amount">
								<em>${order.quantity}</em>
								<span>개</span>		
							</div>
						</td>
						<td class="delivery_Status_area">
							<div class="delivery_Status">
								<c:choose>
									<c:when test="${order.result == 0}">
										<em>주문 확인 중</em>
									</c:when>
									<c:when test="${order.result == 1}">
										<em>상품 준비 중</em>
									</c:when>
									<c:when test="${order.result == 2}">
										<em>배송 시작</em><p>
										<%-- <span>${order.mod_date}</span> --%>
										<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${order.mod_date}" />
	
									</c:when>
								</c:choose>
							</div>
						</td>
						<td class="order_delete_area">
							<div class="order_delete">
								<c:choose>
									<c:when test="${order.result == 0}">
										<div>
											<%-- <input type="hidden" value="${order.odseq}" name="odseq">
											<input type="hidden" value="${order.oseq}" name="oseq">
											<input type="hidden" value="${order.payment}" name="payment"> --%>
											<input type="button" value="주문 취소" onclick="cancelBtn(${order.odseq}, ${order.oseq}, ${order.payment});">
										</div>
									</c:when>
									<c:otherwise>
										<em>주문 취소 불가능</em>
									</c:otherwise>
								</c:choose>
							</div>
						</td>
						
						
					</tr>
	
				</c:forEach>
			</tbody>
		</table>
	</div>	
</article>
</main>
</form>
</body>
</html>