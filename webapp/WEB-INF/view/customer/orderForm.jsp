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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	            
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("detailAddress").focus();
	        }
	    }).open();
	}
	
	function changeamount(total){
		// 사용할 포인트
		let point = Number(document.getElementById('point').value);
		if(point == 0){
			document.getElementById('point').value = 0;
			point = 0;
			return;
		}
		// 가격
		let amount = total;
		// 가용
		let exist_point = ${customer.point};
		if(point < 0) {
			alert("0 보다 작은 포인트는 사용할 수 없습니다.");
			document.getElementById('point').value = 0;
			point =  0;
			return;
		}
		if(point > ${customer.point}){
			alert("사용 가능한 포인트를 초과하였습니다.")
			document.getElementById('point').value = ${customer.point};
			point =  ${customer.point}
		}
		if(point <= amount){
			amount = amount-point;
			exist_point = exist_point - point;
			document.getElementById("total").innerText = amount;
			document.getElementById("exist_point").innerText = exist_point;
		}else{
			alert("주문금액을 초과할수 없습니다.");
		}	
		
	}
	function submitForm(){
		if(${customer.address==null} && document.getElementById("postcode").value == ""){
			alert("우편번호를 입력해주세요.");
		}else{
			form.action = "/WUE/customer/payment";
			form.submit();
		}
	}
</script>
</head>
<body>

<c:set var="size" value="${fn:length(products)}" />
<form action="/WUE/customer/payment" method = "post" name = "form">
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
						${sessionScope.cAuthInfo.name}
						${sessionScope.cAuthInfo.phone}
					</div>
					<c:choose>
						<c:when test="${customer.address == null}">
							<div class="address_area" id="address_area">
								<input type="text" id="postcode" name = "postcode" value = "" placeholder="우편번호">
								<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
								<input type="text" id="address" name = "address" placeholder="주소"><br>
								<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
							</div>
						</c:when>
						<c:otherwise>
							<div class="address_area" id="address_area">
								${customer.address}
								${customer.zip_code}
							</div>
						</c:otherwise>
					</c:choose>
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
						${sessionScope.cAuthInfo.phone}
					</div>
				</div><br><br>
				<div class="sub_dt">
					<span> 주문자명/이메일</span>
				</div>
				<div class="sub_dd">
					<div>
						${sessionScope.cAuthInfo.name}/
						${sessionScope.cAuthInfo.email}
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
												<input type="hidden" value="${products[i].sale_price * products[i].quantity}" name="orders[${i}].payment">
												<c:set var="total" value = "${total + products[i].sale_price * products[i].quantity}"/>
          									
											</div>
										</div>
									</c:when>
									<c:otherwise>
									<div class="cunit_price">
										<div class = "product_price">
											<em>${products[i].price * products[i].quantity}</em>
											<span>원</span>
											<input type="hidden" value="${products[i].price * products[i].quantity}" name="orders[${i}].payment">
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
				<input type="hidden" value="${products[i].url}" name="orders[${i}].url">
				<input type="hidden" value="${products[i].name}" name="orders[${i}].pname">
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="payment_info_area">
		<div class="point_use_area">
			<div class="exist_point">
				가용 포인트 : 
				<span id= "exist_point">${customer.point}</span>
				<em> 점 </em>
			</div>
			<div class="use_point">
				사용할 포인트 <input type="number" value=0 name="point" id="point" pattern="[0-9]+" onkeydown="javascript: return event.keyCode == 69 ? false : true" onchange="changeamount(${total})">점
			</div>
		</div>
		<div class="order_price">
			<div>
				<em> 결제 예정 금액 </em>	
			</div>
			<div>
				<span id= "total">${total}</span>
				<em> 원 </em>
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
	<input type = 'hidden' value=${total} name='amount'>
	<input type="button" value="결제하기" onclick="submitForm()" onkeydown="javascript: return event.keyCode == 13 ? false : true">
	</div>
</div>
</form>
</body>
</html>