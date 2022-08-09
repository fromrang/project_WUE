<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>
<link rel="stylesheet" 
href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
crossorigin="anonymous">
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
	<input type='button' class="btn btn-info" value= '회원가입' onclick= 'location.href= "/webapp/MemberAddSV"' />
</fieldset>
</form>
</body>
</html>