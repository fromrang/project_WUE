<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.item {
	/* border: 2px solid orange; */
	width: 200px;
	height: 100%;
	margin-left: 10px;
	margin-right: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}

.section {
	width: 100%;
	float: left;
	margin-left: 330px;
	top:100px;
	margin-top: 100px;
}

.list {
	display: inline-flex;
	margin-left: 50px;
}

.SellProduct {
	border: solid 1px #FFFFFF;
	border-radius: 10px;
	margin-left: 50px;
	width: 1000px;
	min-height: 260px;
	background: #ebebeb;
	padding: 10px;
	text-align: center;
	height: 100%;
	display: inline-flex;
	flex-wrap : wrap;
	margin-bottom: 50px;
	margin-top: 10px;
}

.productImage {
	/* border: 2px solid green; */
	width: 190px;
	height: 130px;
	margin-left: 10px;
	margin-top: 10px;
}

.imagelist {
	border: 2px solid purple;
	height: 100%;
	width: 230px;
	margin-left: 5px;
	margin-right: 10px;
	margin-top: 5px;
	margin-bottom: 10px;
}

.itemSource {
	font-size: 14px;
	text-align: left;
    
}

table {
	table-layout:fixed;
	width: 190px;
	height:90px;
}

</style>
<script type="text/javascript">
function checkDelte(){
	var input=confirm('한번 삭제하면 다시 재등록해야합니다.그래도 삭제하시겠습니까?');
	 if(input==true){
		return true;
	 }else{
		 return false;
	 }
	 return false;
}
	

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="section" style="">
		<div class="list">
			<div>
				<h2>판매중</h2>
			</div>
			<div>
				<hr
					style="width: 900px; float: left; margin-top: 40px; margin-left: 10px;">
			</div>
		</div>
		<div class="SellProduct">
				<c:forEach var="i" begin="0" end="${Sellimage.size()-1}">
			<div class="imagelist">
			<a href="ProuctUpdateForm?pseq=${Sellproduct[i].pseq}" >
				<label style="font-size: 18px;font-family: Inter;font-weight: Regular;margin-left: 130px;">수정</label>
				 </a>
			<a href="ProductListDelte?pseq=${Sellproduct[i].pseq}" onclick="return checkDelte();">
				<label style="font-size: 18px;font-family: Inter;font-weight: Regular;margin-left: 10px;">삭제</label>
				 </a>
				 
				<div class="productImage">
					<a> <img style="width: 100%; height: 100%;"
						src="/WUE/img/Simage/${Sellimage[i]}">
					</a>
				</div>
				<%--  <c:forEach items="${Sellproduct}" var="product"> --%>
				<div class="item">
					<table border="1">
						<tr>
							<td style="height: 20%;margin;">
								<p class="itemSource">이름
								<p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">${Sellproduct[i].name}</p>
							</td>
						</tr>
						<tr >
							<td style="height: 20%;margin:0%;">
								<p class="itemSource">원가</p>
							</td>
							<td style="height: 20%;">
								<p class="itemSource2">${Sellproduct[i].cost}원</p>
							</td>
						</tr>
						<tr>
							<td style="height: 20%;margin:0%;" >
								<p class="itemSource">판매가</p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">${Sellproduct[i].price}원</p>
							</td>
						</tr>
						<tr>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource">재고현황</p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">${Sellproduct[i].quantity}개</p>
							</td>
						</tr>
						<tr>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource">누적판매수량</p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">개</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
					</c:forEach> 
		</div>
		<!-- ========================================================= -->
		<div class="list">
			<div>
				<h2>준비중</h2>
			</div>
			<div>
				<hr
					style="width: 900px; float: left; margin-top: 40px; margin-left: 10px;">
			</div>
		</div>
		<c:choose>
		<c:when test="${readyproduct=='[]'}">
			<div class="SellProduct">
			<p style="font-size: 30px;text-align:center;">준비중인 상품이 없습니다.</p>
			</div>
		</c:when>
		<c:otherwise>
		<div class="SellProduct">
				<c:forEach var="i" begin="0" end="${Sellimage2.size()-1}">
			<div class="imagelist">
			<a href="ProuctUpdateForm?pseq=${readyproduct[i].pseq}" >
				<label style="font-size: 18px;font-family: Inter;font-weight: Regular;margin-left: 130px;">수정</label>
				 </a>
			<a href="ProductListDelte?pseq=${readyproduct[i].pseq}" onclick="return checkDelte();">
				<label style="font-size: 18px;font-family: Inter;font-weight: Regular;margin-left: 10px;">삭제</label>
				 </a>
				<div class="productImage">
					<a> <img style="width: 100%; height: 100%;"
						src="/WUE/img/Simage/${Sellimage2[i]}">
					</a>
				</div>
				<%--  <c:forEach items="${Sellproduct}" var="product"> --%>
				<div class="item">
					<table border="1">
						<tr>
							<td style="height: 20%;margin;">
								<p class="itemSource">이름
								<p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">${readyproduct[i].name}</p>
							</td>
						</tr>
						<tr >
							<td style="height: 20%;margin:0%;">
								<p class="itemSource">원가</p>
							</td>
							<td style="height: 20%;">
								<p class="itemSource2">${readyproduct[i].cost}원</p>
							</td>
						</tr>
						<tr>
							<td style="height: 20%;margin:0%;" >
								<p class="itemSource">판매가</p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">${readyproduct[i].price}원</p>
							</td>
						</tr>
						<tr>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource">재고현황</p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">${readyproduct[i].quantity}개</p>
							</td>
						</tr>
						<tr>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource">누적판매수량</p>
							</td>
							<td style="height: 20%;margin:0%;">
								<p class="itemSource2">개</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
					</c:forEach> 
		
		
		</div>
		</c:otherwise>
		
		</c:choose>
		
	</div>
		
		
		
</body>
</html>