<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
<title>Insert title here</title>
	<script type="text/javascript">
	
		<c:if test="${member != null && member =='notid'}" var="result">
			alert('회원정보가 없거나 비밀번호가 틀렸습니다.');
		</c:if>

	</script>
</head>
<body>

<form action="/WUE/customer/login" method="post" >
<fieldset style="margin: auto; width: 220px; margin-top: 250px">
	<legend>로그인</legend>
	EAMIL  <input type = "text" name="email" value= "${member.email}">${notMember}<p>
	PW  <input type = "password" name= "pw"><p>
	<input type="submit" value="로그인" onload="loginCheck();">
	<input type="reset" value="취소"><br>
	<input type='button' value= '회원가입' onclick= 'location.href= "/webapp/MemberAddSV"' />
</fieldset>
</form>
</body>
</html>