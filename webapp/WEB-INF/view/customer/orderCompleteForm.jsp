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
.complete_message{
	width: 600px;
	margin: 20px auto 80px auto;
	
}

.complete_message_title{
	text-align: center;
}
.image_unit_item{
	padding: 20px 100px 20px 150px;
	float:left;
}
.name_item{
	padding: 20px 50px 20px 50px;
	float:left;
}
.cunit_price{
	padding: 20px 100px 20px 120px;
}

.order_list{
	padding: 20px 150px 20px 200px;
	margin: 0 auto;
}
.complete_area{
	margin: 200px;
	with: 900px;
}
.back_main{
	margin-top:50px;
	margin-left:620px;
}
</style>
</head>
<body>
<div class="complete_area">
	<div class="complete_message">
		<h2 class="complete_message_title">주문이 완료되었습니다.</h2>
	</div>
	<div class="order_list">
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
						<td class="area_name_item">
							<div class="name_item">
								<em>${order.pname}</em>
							</div>
						</td>
						<td class="price_item_quantity">
							<div class="cunit_price">
								<em class = "price">${order.quantity * order.payment}</em>
								<span>원</span><p>
								<em>${order.quantity}</em>
								<span>개</span>		
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="back_main">
		<button type=button onClick="location.href='/WUE/customer/main'">
			<span style="font-size: 20px; color: #777;">메인 페이지로 돌아가기</span>
		</button>
	</div>

</div>
</body>
</html>