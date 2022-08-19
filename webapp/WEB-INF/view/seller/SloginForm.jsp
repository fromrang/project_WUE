<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.field{
	width:300px;
	height:30px;
	}
.login-btn{
	border: none;
	width:305px;
	height:30px;
	border-radius: 5px;
	background-color: #99ccff;
	color: #12467a;
	cursor:pointer;
	}
.message{
	font-size: 12px;
	color: red;
	}
.signup-btn{
	padding: 0;
	border: none;
	background: none;
	cursor:pointer;
	color: #12467a;
	}
fieldset{
	border:5;
}
	  	main{
			margin-top: 160px;
		}
		header {
		  position: fixed;
		  top: 0;
		  left: 0;
		  right: 0;
		  justify-content: space-between;
		  align-items: center;
		  z-index: 1;
		}

</style>

</head>
<body>
<header>
<jsp:include page = "header.jsp"/>
</header>
<main>
<form action="Slogin" method="post" >
	<h1 style="text-align:center; margin-top: 80px; color: #12467a;">LOGIN</h1>
	<fieldset style="margin: auto; width: 300px; height:230px;">
		<p></p>
		<input class="field" type="text" name="email" value= "${member.email}"  placeholder="EMAIL" size = "30" autofocus><p>
		<input class="field" type="password" name= "password" placeholder="PASSWORD" size = "30" >
		<div style="height:10px;">
			<span class="message">
			${notMember}
			</span>
		</div><p style="margin-top: 20px;">
		<input type="submit" class="login-btn" value="로그인" onload="loginCheck();" size = "100">
		<p>
		<input type='button' class="signup-btn" value= '아직 회원이 아니신가요?' onclick= 'location.href= "SJoin"' />
	</fieldset>
</form>
</main>
</body>
</html>