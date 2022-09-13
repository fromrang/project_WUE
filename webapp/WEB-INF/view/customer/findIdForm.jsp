<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/WUE/customer/findId" method="post" >
	<!-- <h1 style="text-align:center; margin-top: 80px; color: #12467a;">아이디 찾기</h1> -->
	<fieldset style="margin: auto; width: 300px; height:230px;">
		<p></p>
		<div style="height:10px;">
			<span class="message">
			이름
			</span>
		</div><p>
		<input class="field" type="text" name="name" size = "30" autofocus><p>
		<div style="height:10px;">
			<span class="message">
			전화번호
			</span>
		</div><p>
		<input class="field" type="text" name= "phone1" size = "4" >
		<input class="field" type="text" name= "phone2" size = "4" >
		<input class="field" type="text" name= "phone3" size = "4" >
		
		<p>
		<input type="submit" class="login-btn" value="아이디 찾기" size = "100">
		<div style="height:10px;">
			<span class="message">
			${message} 
			${email}
			</span>
		</div><p>
		
	</fieldset>

</form>
</body>
</html>