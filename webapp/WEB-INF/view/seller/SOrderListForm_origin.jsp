<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
window.onload=function(){
var array=order.f.value;
alert(array);
/* var newTotalPrice = 0; */
 for (var i=0; i<3; i++) {
       alert(array);
            /* newTotalPrice += Number(form.productPrice[i].value); */
        }
    }
	
}


</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page = "header.jsp"/>
	<form action='order' method='get' name="order">
		<main class="to_do_list">
			<article
				style="padding-left: 150px; padding-right: 100px; z-index: 1;">
				<h3>Order List</h3>
				<p>&nbsp;
				<h4>주문 상태</h4>
				<div
					style="color: #31383F; height: 50px; padding: 5px; font-size: 20px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color: pink;"><a
						href="/middle/worker/kind=1">상품 준비 중</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/middle/worker/kind=2">주문 확인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/middle/worker/kind=3">배송 시작</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/middle/worker/kind=4">배송 완료</a></span>
				</div>
				<p>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문번호</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문날짜</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문상태</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>배송상태
								변경일</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>이메일</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>연락처</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주소</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문
								상품명</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문상품
								수량</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문가격
								가격</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>총금액<br>
						</b></th>
					</tr>
					<tr></tr>
					<tr>
						<c:forEach var="i" begin="0" end="${order.size()}">
						<input type="hidden" name="f" value="${sum}">
						<c:choose>
						<c:when test="i==0">
								<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].oseq}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">
									<fmt:formatDate value="${order[i].reg_date}" pattern="yyyy-MM-dd"/>
								</td>
							<c:choose>
								<c:when test="${order[i].result == '0'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
										준비중 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
								<c:when test="${order[i].result == '1'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">주문
										확인 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
								<c:when test="${order[i].result == '2'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
										중 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
								<c:when test="${order[i].result == '3'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
										확인 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${order[i].oseq != order[i-1].oseq}">
								<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].oseq}  ${orderlist.order[i].oseq}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate value="${order[i].reg_date}" pattern="yyyy-MM-dd"/>
								</td>
							<c:choose>
								<c:when test="${order[i].result == '0'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
										준비중 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
								<c:when test="${order[i].result == '1'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">주문
										확인 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
								<c:when test="${order[i].result == '2'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
										중 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
								<c:when test="${order[i].result == '3'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
										확인 <input type="button" value="배송상태 변경">
									</td>
								</c:when>
							</c:choose>
								</c:when>
								<c:otherwise>
								<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
								<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
								<td style="border-bottom: 1px solid #444444; padding: 10px; "></td>
								</c:otherwise>
							</c:choose>
							</c:otherwise>
							</c:choose>
						
								<%-- <fmt:parseDate var="date1" value="${order[i].mod_date}" pattern="yyyy-MM-dd"/> --%>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">
							<fmt:formatDate value="${order[i].mod_date}" pattern="yyyy-MM-dd"/> </td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].email}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].phone}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].zip_code}<br>
								${order[i].address}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].name}</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].quantity}</td>
							
					
							<c:choose>
								<c:when test="${order[i].sale == 'y'}">
									<input type="hidden" name="price" >
									<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].sale_price}</td>
								</c:when>
								<c:when test="${order[i].sale == 'n'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].price}</td>
								</c:when>
							</c:choose>
						   <td style="border-bottom: 1px solid #444444; padding: 10px;">${order[i].payment}</td>
							<tr>
							<tr>
							<%-- <c:choose>
								<c:when test="${order[i].oseq == order[i-1].oseq}" > --%>
								<c:if test="${order[i].oseq != order[i+1].oseq}">
								<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><br>
									<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"><br>
								<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;">총합(total)<br>
						    <td style="border-bottom: 1px solid #444444; padding: 10px;"><p id="totalprice">
								</td>
							  </c:if>
								<%-- </c:when>
							</c:choose> --%>
							</tr>
						</c:forEach>
				</table>
			</article>
		</main>
	</form>
</body>
</html>