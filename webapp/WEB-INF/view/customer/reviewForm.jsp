<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	  .content {
	    display: none;
	    padding-bottom: 30px;
	  }
	  #faq-title {
	    font-size: 25px;
	  }
	  .faq-content {
	    border-bottom: 1px solid #e0e0e0;
	  }
	  .title {
	    font-size: 19px;
	    padding: 30px 0;
	    cursor: pointer;
	    border: none;
	    outline: none;
	    background: none;
	    width: 100%;
	    text-align: left;
	  }
	  .title:hover {
	    color: #2962ff;
	  }
	  [id$="-toggle"] {
	    margin-right: 15px;
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
<!-- <script type="text/javascript">
	function submitReviewAdd(odseq){
		form.action = "/WUE/customer/reviewAdd?odseq="+odseq;
		form.submit();
	}
</script> -->
</head>
<body>
<header>
<jsp:include page = "header.jsp"/>
</header>
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
<h3>작성 가능한 후기 상품</h3>
	<div>
		<table class = "cart_table">
			<tbody>
		<c:forEach var="order" items="${canbeReview}">
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
						<%-- <input type=button value="후기 작성하러 가기" onclick="submitReviewAdd(${order.odseq});"> --%>
						 <input type=button value="후기 작성하러가기" onclick="location.href='reviewAdd?odseq=${order.odseq}'">
					</div>
				</td>
			
			</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

<c:set var="size" value="${fn:length(reviews)}" />
<c:choose>
	<c:when test="${size==0}">
		<div>
		상품을 구매해보세요!
		</div>			
	</c:when>
	<c:otherwise>
		<h3>작성한 후기</h3>
			<div>
				<%-- <c:forEach var="review" items="${reviews}"> --%>
				
				<c:forEach var="i" begin="0" end="${size-1}">
					<div class="faq-content">
						<button class="title" id="que-${i}"><span id="que-${i}-toggle">+</span><span>${reviews[i].content}</span></button>
						<div class="content" id="ans-${i}">
							<table class="register" width=600>
			                  <tr>
			                     <td width=100>상품명 : </td>
			                     <td>
			                     	${reviews[i].pname}
			                     </td>
			                  </tr>
			                  <tr height="10">
			                  </tr>
			                  <tr>
			                     <td width=100></td>
			                     <td>
			                     	<c:forEach var="image" items= "${reviews[i].img_url}">
			                     		<img src="/WUE/img/rimages/${image}" style="width:200px; height:200px;">
			                     	</c:forEach>
			                     </td>
			                  </tr>
			                   <tr height="10">
			                  </tr>
			                  <tr>
			                  	<td width=100></td>
			                     <td colspan=2  width=100 >${reviews[i].content}</td>
			                  </tr>
			               </table>
						</div>
					</div>
				</c:forEach>
			</div>
</c:otherwise>
</c:choose>
</main>

<script>
  const items = document.querySelectorAll('.title');

  function openCloseAnswer() {
    const answerId = this.id.replace('que', 'ans');

   if(document.getElementById(answerId).style.display === 'block') {
      document.getElementById(answerId).style.display = 'none';
      document.getElementById(this.id + '-toggle').textContent = '+';
      return;
    }else {
      document.getElementById(answerId).style.display = 'block';
      document.getElementById(this.id + '-toggle').textContent = '-';
   } 
  }

  items.forEach(item => item.addEventListener('click', openCloseAnswer));
</script>
</body>
</html>