<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/WUE/css/order.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="size" value="${fn:length(products)}" />
<form action="/WUE/customer/payment" method = "get">
	<div class="order_head">
		<h2 class="head_text_h2">
			<span class="head_text">결제하기</span>
		</h2>
	</div>
	<div class="authinfo_area">
		<div class="order_title">
		받는 분 정보
		</div>
		<div class="order_title_sub">
			<div class="sub_dl" role="presentation">
				<div class="sub_dt">
					<span> 배송지 정보</span>
				</div>
				<div class="sub_dd">
					<div>
						${sessionScope.authInfo.name}
						${sessionScope.authInfo.phone}
					</div>
					<div>
						${sessionScope.authInfo.address}
						${sessionScope.authInfo.zip_code}
					</div>
				</div>
			</div>
		</div>
		<div class="order_title_sub2">
			<div class="sub_dl" role="presentation">
				<div class="sub_dt">
					<span> 휴대폰 정보 </span>
				</div>
				<div class="sub_dd">
					<div>
						${sessionScope.authInfo.phone}
					</div>
				</div><br><br>
				<div class="sub_dt">
					<span> 주문자명/이메일</span>
				</div>
				<div class="sub_dd">
					<div>
						${sessionScope.authInfo.name}/
						${sessionScope.authInfo.email}
					</div>
				</div>
			</div>
		</div>
	</div>
<div>
	<div class="product_order_area">
		<div class="totla_count">
			<span> 주문 상품 ${size}개 </span>
		</div>
	<div class="codr_unit">
		<table class = "cart_table">
			<tbody>
				<c:set var="total" value="0" />
				<c:forEach var="i" begin="0" end="${size-1}">
				<tr class="pay_item_area" id="a">
					<td class="area_image_item">
						<div class = "image_unit_item">
							<span class="image_item">
								<img src="/WUE/img/Simage/${products[i].url}">
							</span>
						</div>
					</td>	
					<td class="name_item">${products[i].name}</td>
					<td class="price_item_quantity">
						<div class="cunit_price">
							<div class = "product_price">
								<c:choose>
									<c:when test="${products[i].sale eq 'y'}">
										<div class="cunit_price">
											<div class="before_sale_price">
												<em class = "price">${products[i].price * products[i].quantity}</em>
												<span>원</span>	
											</div>
											
											<div class = "product_price">
												<em class = "price">${products[i].sale_price * products[i].quantity}</em>
												<span>원</span>
												<c:set var="total" value = "${total + products[i].sale_price * products[i].quantity}"/>
          									
											</div>
										</div>
									</c:when>
									<c:otherwise>
									<div class="cunit_price">
										<div class = "product_price">
											<em>${products[i].price * products[i].quantity}</em>
											<span>원</span>
											<c:set var="total" value = "${total + products[i].price * products[i].quantity}"/>									
										</div>
									</div>
									</c:otherwise>
								</c:choose>	
							</div>
						</div>
						<div class="codr_unit_amount">
							<em>${products[i].quantity}</em>
							<span>개</span>		
						</div>
					</td>
				</tr>
				<input type="hidden" value="${products[i].pseq}" name="orders[${i}].pseq">
				<input type="hidden" value="${products[i].quantity}" name="orders[${i}].quantity">
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="payment_info_area">
		<div class="order_price">
			<div>
				<em> 결제 예정 금액 </em>	
			</div>
			<div>
				<span>${total}</span>
				<em> 원 </em>
			</div>
		</div>
		<div class="point_use_area">
			<div class="exist_point">
				가용 포인트 : ${sessionScope.authInfo.point}점
			</div>
			<div class="use_point">
				사용할 포인트 <input type="text" value=0 name="point">점
			</div>
		</div>
		<div class="payment_method_area">
			<div>
				<em> 결제 방법 </em>	
			</div>
			<div>
				결제 방법(신용카드, 카카오페이, 실시간 계좌이체)
				카드 종류
			</div>
		</div>
		
		
	</div>

	<input type="submit" value="결제하기">
	</div>
</div>
</form>
</body>
</html>