<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	.cart_insert_message{
		text-align: center;
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
	main{
		position: relative;
		margin-top: 200px;
	}
	body{
		margin : 0px
	}
	
</style>
<script type="text/javascript">
	function count(type, cart)  {
		//var i = Number(i);
		/*
		console.log(i);
		  // 결과를 표시할 element
		   
		  const resultElement = document.getElementById('quantity_'+i);
		  
		  // 현재 화면에 표시된 값
		  // let number = resultElement.innerText; 
		  let number = resultElement.value;
		  let quantity = ${cartList[0].total_quantity};
		  
		  // 더하기/빼기
		  if(type === 'plus') {
		    number = parseInt(number) + 1;
		  }else if(type === 'minus')  {
			  if(number == 1){
				  number = 1;
			  }else{				  
			    number = parseInt(number) - 1;
			  }
		  }else if(type == 'text'){
			  if(number <= 0){
				  number =1;
			  }else{
				  resultElement.value = number;
			  }
		  }
		  
		  if(number >= 11){
			  alert("최대 구매 수량은 10개 입니다.");
			  number = 10;
		  }
		  if(number == ${cartList[i].total_quantity}+1){
			  alert("현재 재고는 quantity 입니다.");
			  number = ${cartList[i].total_quantity};
		  }	  
		  // 결과 출력
		  resultElement.value = number;
		  */
	}
	
	function plusPrice(event){
		var obj_length = form.productPrice.length;
		
		/* 체크 박스 하나일때 */
 		if(typeof obj_length == 'undefined'){
 			var newTotalPrice = 0;
			if (form.cartid.checked == true) {
			    newTotalPrice += Number(form.productPrice.value);
			}
			
			form.totalPrice.value=newTotalPrice;
		}else{ 
			var newTotalPrice = 0;
			for (var i=0; i<obj_length; i++) {
				if (form.cartid[i].checked == true) {
				    newTotalPrice += Number(form.productPrice[i].value);
				}
			}
			form.totalPrice.value=newTotalPrice;
		}
      
     }  
	       
	function clickBtn() {
	   form.action = "/WUE/customer/cart/delete"; 
	   form.submit(); 
	}

	function selectAll(selectAll,event)  {
		const checkboxes 
		     = document.getElementsByName('cartid');
		
		checkboxes.forEach((checkbox) => {
		  checkbox.checked = selectAll.checked;2
		})
		plusPrice(event);
	} 
	
	function orderBtn(){
		
 		const query = 'input[name="cartid"]:checked';
		const selectedElements = document.querySelectorAll(query);
		const selectedElementsCnt = selectedElements.length;

		var total = form.totalPrice.value;
		if(total == 0){
			alert("상품 선택 후 주문해주세요");
		}else if(selectedElementsCnt == 0){
			form.cart.checked = true;
			const checkboxes = document.getElementsByName('cartid');
		
			checkboxes.forEach((checkbox) => {
			  checkbox.checked =true;
			})
			form.action = "/WUE/customer/order";
			form.submit();
		}else{
			form.action = "/WUE/customer/order";
			form.submit();
		}
	}

</script>
</head>
<body>
<header>
<jsp:include page = "header.jsp"/>
</header>
<main>
<form action="/WUE/customer/order" id="form" method='post'>
	<c:set var="size" value="${fn:length(cartList)}" />
	<c:choose>
		<c:when test="${size==0}">
			<div class="cart_insert_message">
				<h2>
					상품을 장바구니에 추가해보세요.
				</h2>
			</div>			
		</c:when>
		<c:otherwise>
			<div class="cart_list">
				
				<div class="cart_list_up">
						<input type="checkbox" name="cart" id="cart" onclick="selectAll(this,event);" >
					
					<span>상품 갯수: ${size}</span>
				</div>
			
			<div class="codr_unit"> 
				<table class = "cart_table">
					<tbody>
						<c:set var="total" value="0"/>
						<c:forEach var="i" begin="0" end="${size-1}">
							<tr class="pay_item_area" id="a">
								<td class="area_image_item">
									<div class = "image_unit_item">
										<span class="image_chk">
											<c:choose>
												<c:when test="${cartList[i].total_quantity == 0}">
													<span></span>	
												</c:when>
												<c:otherwise>
													<input type="checkbox" id = "chk_order_${cartList[i].cseq}" name="cartid" value="${cartList[i].cseq}" onclick="plusPrice(event)" >
												</c:otherwise>	
											</c:choose>	
																		
											<label for = "chk_order_${cartList[i].cseq}">
												<span class = "blind">
													${cartList[i].name}
												</span>
												<span class="blind">
													상품 선택
												</span>
											</label>
										</span>
										<span class="image_item">
											<img src="/WUE/img/Simage/${imagebyProduct[i]}">
										</span>
										
									</div>
								</td>	
								<td class="name_item">
									<a href="/WUE/customer/pseq=${cartList[i].pseq}">${cartList[i].name}</a></td>	
								<%-- <td class = "quantity_item">${cartList[i].quantity}</td> --%>
								<td class="price_item_quantity">
									<div class="cunit_price">
										<div class = "product_price">
											<c:choose>
											<c:when test="${cartList[i].total_quantity == 0}">
												<span></span>	
											</c:when>
											<c:when test="${cartList[i].sale eq 'y'}">
												<em class = "price">${cartList[i].sale_price}</em>
												<input type = "hidden" name ="productPrice" value="${cartList[i].quantity*cartList[i].sale_price}"/>         
			                					<c:set var= "productPrice" value = "${cartList[i].quantity * cartList[i].sale_price}"/>
			               						<c:set var="total" value = "${total + cartList[i].quantity * cartList[i].sale_price}"/> 
												<span>원</span>					
											</c:when>
											<c:otherwise>
												<em>${cartList[i].price * cartList[i].quantity}</em>
												<input type = "hidden" name ="productPrice" value="${cartList[i].quantity*cartList[i].price}"/>         
			                					<c:set var= "productPrice" value = "${cartList[i].quantity * cartList[i].price}"/>
			                					<c:set var="total" value = "${total + cartList[i].quantity * cartList[i].price}"/> 
												<span>원</span>				
										
											</c:otherwise>
											</c:choose>		
										</div>
									</div>
									<div class="codr_unit_amount">
										<c:choose>
											<c:when test="${cartList[i].total_quantity == 0}">
												<span>품절</span>	
											</c:when>
											<c:otherwise>
												<em>${cartList[i].quantity}</em>
												<span>개</span>
											</c:otherwise>	
										</c:choose>	
									</div>
								</td>
							</tr>
			
						</c:forEach>
					</tbody>
				</table>
			</div>	
			</div>
				<div>
					<b>총액  </b><input type="text" name="totalPrice" id = "totalPrice" value="${total}" disabled/>
					<input type="button" value="주문하기" onclick="orderBtn();">
					<input type="button" value="삭제" onclick="clickBtn();">
				</div>
			</c:otherwise>
		</c:choose>
	</form>
</main>
</body>
</html>