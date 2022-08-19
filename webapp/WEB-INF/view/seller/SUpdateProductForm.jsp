<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<script type="text/javascript" src="./resources/js/validation.js"></script>
<style type="text/css">
.container{
position:relative;
left:9%;
width:50%; 
}
#att_zone:empty:before{
	content : attr(data-placeholder);
	}
	#att_zone{
	width: 70%;
	height:100%;
	min-height:150px;
	border:1px dotted #00f;
	}

</style>
<script type="text/javascript">

function checkAdd(){
	var input=confirm('변경하시겠습니까?');
	 if(input==true){
		return true;
	 }else{
		 return false;
	 }
	 return false;
}
window.onload=function(){
 var str=newProduct.kind2.value;
const el=document.getElementById("kind");
const len=el.options.length;
for(let i=0;i<len;i++){
	if(el.options[i].value==str){
	el.options[i].selected=true;
	}
}
	
}

	

</script>
<jsp:include page = "header.jsp"/>
<title>상품 수정</title>
</head>
<body>
<!-- 	<div class="jumbotron">
		<div class="container">
				상품 등록
		</div>
	</div>
	 -->
	<div class="container" onsubmit="return checkAdd()">
		<div>
			<!-- <a href="logout.jsp">logout</a> -->
			<h3 style="margin-top:120px;">상품 수정</h3>
			<hr style="width: 600px;">
		</div>
		
		<form name="newProduct" action="ProuctUpdate" class="form-horizontal" method="post" 
		>
		<input type="hidden" name="pseq" value="${pseq}">
		<input type="hidden" name="kind2" value="${product.kind}">
			<div class="form-group row">
				<label class="col-sm-2">상품 종류</label>
				<div class="com-sm-3">
				<select name="kind" id="kind">					  
				<option value="종류 선택">종류 선택</option>
				<option value='1'>1.과일</option>
				<option value='2'>2.채소</option>
				<option value='3'>3.쌀/잡곡</option>
				<option value='4'>4.견과류</option>
			</select>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품 이름</label>
				<div class="com-sm-3">
					<input type="text" id="name" name="name" class="form-control" value="${product.name}">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">원가</label>
				<div class="com-sm-3">
					<input type="text" id="unitPrice" name="cost" class="form-control" value="${product.cost}">
				</div>
			</div>
			
				<div class="form-group row">
				<label class="col-sm-2">판매가</label>
				<div class="com-sm-3">
					<input type="text" id="unitPrice" name="price" class="form-control"
					value="${product.price}">
				</div>
			</div>
			
				<div class="form-group row">
				<label class="col-sm-2">세일가</label>
				<div class="com-sm-3">
					<input type="text" id="unitPrice" name="sale_price" class="form-control"
					value="${product.sale_price}">
				</div>
			</div>
			
	
			<div class="form-group row">
				<label class="col-sm-2">재고 수</label>
				<div class="com-sm-3">
					<input type="text" id="unitsInStock" name="quantity" class="form-control"
					value="${product.quantity}">
				</div>
			</div>
			
			<!-- <div class="form-group row">
				<label class="col-sm-2" >세일여부</label>
				<div class="com-sm-5">
					<input type="radio" name="sale" value="y"> Yes
					<input type="radio" name="sale" value="n"> No
				</div>
			</div> -->
			
			
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="수정">
				</div>
			</div>
			
		</form>
	</div>
	
	
	
	

</body>
</html>