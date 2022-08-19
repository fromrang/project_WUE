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

function f(){
	for (var i=0; i<3; i++) {
		alert('a');
		alert(Number(order.productPrice[i].value));
			/* alert(order.productPrice[i].value); */
		}
}

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page = "header.jsp"/>
	<form action='SOrderResultUpate' method='get' name="order">
		<main class="to_do_list">
			<article
				style="padding-left: 150px; padding-right: 100px; z-index: 1;margin-top: 100px;">
				<h3>Order List</h3>
				<p>&nbsp;
				<h4>주문 상태</h4>
				<div
					style="color: #31383F; height: 50px; padding: 5px; font-size: 20px;">
					&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color: pink;">
						<a href="SOrderList">전체</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="SOrderListResult?result=0">상품 준비 중</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="SOrderListResult?result=1">주문 확인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="SOrderListResult?result=2">배송 시작</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="SOrderListResult?result=3">배송 완료</a>
					</span>
				</div>
				<p>
				<table
					style="width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
					<tr>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:5% "><b>주문번호</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:10%"><b>주문날짜</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:10%"><b>주문상태</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:10%;"><b>배송상태<br>
								변경일</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:5%;"><b>이메일</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:7%"><b>주문자
								명</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px;width:13%"><b>연락처</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:20%"><b>주소</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:15%"><b>주문
								상품명</b></th>
						<th style="border-bottom: 1px solid #444444; padding: 10px; width:5%"><b>총금액<br>
						</b></th>
					</tr>
					<tr></tr>

					<c:choose>
						<c:when test="${order!='[]'}">
							<c:forEach var="i" begin="0" end="${order.size()-1}">
								<tr>

									<td style="border-bottom: 1px solid #444444; padding: 10px;width:5%">${order[i].oseq}
									</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:10%">
									
										<fmt:formatDate value="${order[i].reg_date}"
											pattern="yyyy-MM-dd" />
									</td>
									
									
								       <c:choose>
								<c:when test="${order[i].result == '0'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:10%">상품 준비중
									</td>
								</c:when>
								<c:when test="${order[i].result == '1'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:10%">주문
										확인 
									</td>
								</c:when>
								<c:when test="${order[i].result == '2'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:10%">배송
										시작 
									</td>
								</c:when>
								<c:when test="${order[i].result == '3'}">
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:10%">배송
										완료 
									</td>
								</c:when>
							</c:choose>
							
									<td style="border-bottom: 1px solid #444444; padding: 10px;">
										<fmt:formatDate value="${order[i].mod_date}"
											pattern="yyyy-MM-dd" />
									</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:5%">${order[i].email}</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px; width:7%">${order[i].name}</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:13%">${order[i].phone}</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px; width:20%">${order[i].zip_code}<br>
										${order[i].address}
									</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:15%">${order[i].pname}
										<input type="button" value="주문상세 조회" onclick="location.href='SOrderDetail?oseq=${order[i].oseq}'">
									</td>
									<td style="border-bottom: 1px solid #444444; padding: 10px;width:5%"  onclick="f()">${sum[i]}
								   <c:set var= "productPrice" value = "${sum[i]}"/>
								  <input type = "hidden" name ="productPrice" value="${sum[i]}"/>  
									 </td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="10">
									<p>관련 내역이 현재없습니다.</p>
								</td>
							<tr>
						</c:otherwise>
					</c:choose>
				</table>
			</article>
		</main>
	</form>
</body>
</html>