<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Order List</title>
</head>
<body>
<jsp:include page = "header.jsp"/>
	<article style="padding-left: 150px; padding-right: 100px; z-index: 1;margin-top: 100px;">
	<form action="SOrderResultUpate " method="get">
		
		<h3>주문자 정보</h3>
		<table border=0 align=right cellspacing=1 bordercolor=white
			style="margin-top: 50px; position: relative; width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
			<!-- <tr>
               <td colspan=8 height=2></td>
            </tr> -->
			<tr>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문일자</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문번호</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문자</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>주문총액</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>처리상태(배송상태)</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>처리상태
						변경</b></th>
			</tr>
			<tr align="center">
				<td style="border-bottom: 1px solid #444444; padding: 10px;"><fmt:formatDate
						value="${order[0].reg_date}" pattern="yyyy-MM-dd" /></td>
				<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[0].oseq}
				<c:forEach items="${order}" var="order">
				<input type="hidden" name="odseq" value="${order.odseq}">
				</c:forEach>
				</td>
				<td style="border-bottom: 1px solid #444444; padding: 10px;">${order[0].name}</td>
				<td style="border-bottom: 1px solid #444444; padding: 10px;">${sum}원</td>
				<c:choose>
					<c:when test="${order[0].result == '0'}">
						<td style="border-bottom: 1px solid #444444; padding: 10px;">상품준비 중 </td>
					</c:when>
					<c:when test="${order[0].result == '1'}">
						<td style="border-bottom: 1px solid #444444; padding: 10px;">주문
							확인
					</c:when>
					<c:when test="${order[0].result == '2'}">
						<td style="border-bottom: 1px solid #444444; padding: 10px;">배송 시작  </td>
					</c:when>
					<c:when test="${order[0].result == '3'}">
						<td style="border-bottom: 1px solid #444444; padding: 10px;">배송
							완료</td>
					</c:when>
				</c:choose>
				<td style="border-bottom: 1px solid #444444; padding: 10px;"><select
					id='select' name=result>
						<option value="-1">배송 상태 변경</option>
						<option value="0">1.상품 준비 중</option>
						<option value="1">2.주문 확인</option>
						<option value="2">3.배송 시작</option>
						<option value="3">4.배송 완료</option>
				</select> <input type="submit" value="선택"></td>
			</tr>
			<!--  <tr>
               <td colspan=8 height=2></td>
            </tr> -->
			<tr>
			</tr>
		</table>
		 
			</form>
		<p></p>
		<p></p>
		<p></p>
		<p></p>

		<!--  =========================================================== -->

		<h3 style="margin-top: 200px;">주문상세정보</h3>
		<table align=right cellspacing=1 bordercolor=white
			style="position: relative; width: 100%; border-top: 1px solid #444444; border-collapse: collapse;">
			<!-- <tr>
               <td colspan=8 height=2></td>
            </tr> -->
			<tr>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>상품명</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>상품별
						번호</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>수량</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>가격</b></th>
				<th style="border-bottom: 1px solid #444444; padding: 10px;"><b>상품별
						가격</b></th>

			</tr>

			<c:set var="total" value="0" />
			<c:forEach items="${order}" var="order">
				<tr align="center">
					<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.pname}</td>
					<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.odseq}</td>
					<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.quantity}개</td>
					<c:choose>
						<c:when test="${order.sale == 'y'}">
							<input type="hidden" name="price">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.sale_price}</td>
						</c:when>
						<c:when test="${order.sale == 'n'}">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.price}</td>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${order.sale == 'y'}">
							<input type="hidden" name="price">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.quantity * order.sale_price}원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
						</c:when>
						<c:when test="${order.sale == 'n'}">
							<td style="border-bottom: 1px solid #444444; padding: 10px;">${order.quantity * order.price}원</td>
							<td style="border-bottom: 1px solid #444444; padding: 10px;"></td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
			<tr>
               <td colspan=8 height=2></td>
            </tr> 
			<!-- <tr>

				<td align=right><input type="text" value="주문 처리가 완료되었습니다."
					disabled style="width: 185px;"></td>
			</tr> -->
		</table>
	</article>
</body>


</html>