<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
	
		<c:if test="${member != null && member =='notid'}" var="result">
			alert('회원정보가 없거나 비밀번호가 틀렸습니다.');
		</c:if>

	</script>
</head>
<body >
<form action="/webapp/member/add" method="post" style="margin: auto; width: 220px; margin-top: 250px">
<h1>회원 가입</h1>
		<fieldset>
			<p>이메일 : <input type= 'text' name = 'email' size = '30' maxlength='50' value="${member.email}"/></p>${existEmail}
			<p>비밀번호 : <input type= 'password' name = 'pwd' size = '30' maxlength='50' /></p>
			<!-- <p>비밀번호 확인 : <input type= 'password' name = 'pwck' size = '30' maxlength='50' /></p> -->
			<p>이름 : <input type= 'text' name = 'mname' size = '30' maxlength='50' value="${member.mname}"/></p>
			<input type='submit' value= '가입하기'/>
			<input type='reset' value= '취소' onclick= 'location.href= "list"' />
		</fieldset>
</form>
</body>
</html>