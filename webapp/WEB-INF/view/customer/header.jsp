<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
        div {
			margin:0; 
			padding:0;
			}
    </style>
</head>
<body>
<div style="background-color: #FEDD89; color: #31383F; height: 100px; padding: 5px;">
	<a href="/WUE/customer/main"><img src="/WUE/img/shopLogo.png" style="width:100px; height:100px;"></a>
	<a href="/WUE/customer/main/kind=1">Fruit</a>
	<a href="/WUE/customer/main/kind=2">Vegetable</a>
	<a href="/WUE/customer/main/kind=3">Rice/Mixed grains</a>
	<a href="/WUE/customer/main/kind=4">Nuts</a>
	
	<c:choose>
		<c:when test="${empty sessionScope.authInfo}">
			<a href="/WUE/customer/login">로그인</a>
			<a href="/WUE/customer/Join">회원가입</a>
		</c:when>
		<c:otherwise>
			${sessionScope.authInfo.name} 님 반갑습니다.
			<a href="/WUE/customer/cart/list">장바구니</a>
			<a href="/WUE/customer/likeList">좋아요</a>
			<a href="/WUE/customer/mypage">마이페이지</a>
			<!-- <a href="/WUE/customer/logout">로그아웃</a> -->
		</c:otherwise>
	</c:choose>
	
</div>
</body>
</html>