<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
<title>Insert title here</title>
<style>
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
</style>
<script type="text/javascript">
	function getImgAssoItem(image){
		document.getElementById("mainImg").src = "/WUE/img/Simage/"+image;
		
	}
	
	function cartInsertAction(){
		form.action="cart/insert";
		form.submit();
		
	}
	function orderInsertAction(){
		form.action="order";
		form.submit();
	}
	function count(type)  {
		  // 결과를 표시할 element
		  const resultElement = document.getElementById('quantity');
		  const countElement = document.getElementById('count');
		  // 현재 화면에 표시된 값
		  /* let number = resultElement.innerText; */
		  let number = resultElement.value;
		  
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
		  if(number == ${product.quantity}+1){
			  alert("현재 재고는 ${product.quantity} 입니다.");
			  number = ${product.quantity};
		  }	  
		  // 결과 출력
		  resultElement.value = number;
		  countElement.value=number;
	}
</script>

</head>
<body>
<form id="form">
<img src="/WUE/img/Simage/${images[0]}" style="width:400px; height:400px;" id="mainImg">
${product.name}
<input type = "hidden" value=${product.pseq} name='pseq'>
<div>
	<input type='button'
	       onclick='count("minus")'
	       value='-'/>
	<input type = "text" value=1 id='quantity' name='quantity' onchange='count("text")' class="quantity_input">
	<input type='button'
	       onclick='count("plus")'
	       value='+'/>
	
</div>
<ul class="lst_thmb">
	<c:forEach var="image" items="${images}">
	
		<li class="active">
			<a href="javascript:void(0);" onclick="getImgAssoItem('${image}');" class="clickable"><img src="/WUE/img/Simage/${image}" width="84" height="84" class="zoom_thumb"><span class="bd"></span></a>
		</li>
			
	</c:forEach>
</ul>
<input type="button" value="장바구니 추가" onclick='cartInsertAction();'/>
<input type="button" value="주문하기" onclick='orderInsertAction();'/> 

<c:set var="size" value="${fn:length(reviews)}" />
<h3>후기 보기</h3>
	<c:choose>
		<c:when test="${size == 0}">
			<span>후기가 없습니다.</span>
		</c:when>
		<c:otherwise>
			<div>
				<%-- <c:forEach var="review" items="${reviews}"> --%>
				<c:forEach var="i" begin="0" end="${size-1}">
					<div class="faq-content">
						<button class="title" id="que-${i}" type="button"><span id="que-${i}-toggle">+</span><span>${reviews[i].content}</span></button>
						<div class="content" id="ans-${i}">
							<table class="register" width=600>
			                  <tr>
			                     <td width=100>작성 날짜 : </td>
			                     <td>
			                     	<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${reviews[i].reg_date}" />
			                     	
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
	
</form>
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

