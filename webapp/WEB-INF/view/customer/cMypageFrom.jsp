<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
<title>Insert title here</title>
</head>
<body>
<div>
<h1>
${sessionScope.authInfo.name}님
</h1>
<h2>
point : ${sessionScope.authInfo.point}점	
</h2>
</div>
<h3>
주문/배송 조회
</h3>
<c:forEach var="order" items="${orders}">
	<div>
		
		<span>${order.pname}</span>
	</div>
</c:forEach>
구매내역
마이리뷰
상품 QnA

회원 정보 변경
비밀번호 변경	
회원탈퇴

고객센터
</body>
</html>