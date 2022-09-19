<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function javascript(){
	 
	    //팝업창에서 부모창을 다른페이지로 이동합니다.
	    window.opener.location.href="/WUE/customer/cart/list";
		self.close();
	}
</script>
<style type="text/css">
.cart_btn{
	margin-left:40px;
	margin-top: 30px;
	border: none;
	width:150px;
	height:50px;
	border-radius: 5px;
	background-color: #FEDD89;
	color: #12467a;
	cursor:pointer;

}
.ctn_btn{
	margin-top: 30px;
	border: none;
	width:150px;
	height:50px;
	border-radius: 5px;
	background-color: #FEDD89;
	color: #12467a;
	cursor:pointer;

}
.alarm{	
	margin-top: 5px;
	color: #777;
	font-size: 10px;
	letter-spacing: -0.5px;
	
}
</style>
</head>
<body>
<div style='text-align: center; margin-top: 40px;'>
	<span>장바구니에 담기 완료!</span>
</div>
<input class='cart_btn' type='button' onclick='javascript();' value='장바구니로 가기'>
<input class='ctn_btn' type='button' onclick='self.close();' value='쇼핑 계속 하기'>
<div>
	<span class='alarm'>
		* 만약 장바구니에 담긴 수량이 해당 제품 재고 혹은 최대 구매 수량을 넘길 시에는 장바구니 수량이 변경되지 않습니다.
	</span>
</div>
</body>
</html>