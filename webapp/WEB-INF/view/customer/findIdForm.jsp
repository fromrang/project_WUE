<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
	margin:0;
	padding:0;
	background-color: #FEDD89; 
	color: #31383F;
}
.login-btn{
	border: none;
	width:305px;
	height:30px;
	border-radius: 5px;
	background-color: #FEDD89;
	color: #12467a;
	cursor:pointer;
	}
.check-btn{
	border: none;
	width:305px;
	height:50px;
	border-radius: 5px;
	background-color: #FEDD89;
	color: #12467a;
	cursor:pointer;
	}
</style>
</head>
<body>
<form action="/WUE/customer/findId" method="post">
	<!-- <div style="background-color: #FEDD89; color: #31383F; height: 100px; padding: 5px;"> -->
	<!-- <div style="background-color: #FEDD89; color: #31383F;"> -->
    	<img src="/WUE/img/simbol.png" style="width: 100px; height: 100px; margin-left: 210px; margin-top: 70px">
	<!-- </div> -->
	<div style="margin: 100px"></div>
	<fieldset style="margin: auto; width: 300px; height:230px; background-color: white; ">
		<c:choose>
			<c:when test="${email != null}">
				<div style="height:100px; margin-top: 30px; text-align: center;">
					<span class="message" style="text-align: center;">회원님의 아이디는</span><br><br>
					<span class="message" style="text-align: center; color:blue">${email}</span><br><br>
					<span class="message" style="text-align: center;">입니다.</span>
						
				</div><p>
				<input type="button" class="check-btn" value="확인" size = "100" onclick="self.close();">
			</c:when>
			<c:otherwise>
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
				<input class="field" type="text" name= "phone1" size = "4" > -
				<input class="field" type="text" name= "phone2" size = "4" > -
				<input class="field" type="text" name= "phone3" size = "4" >
				
				<p></p>
				<input type="submit" class="login-btn" value="아이디 찾기" size = "100">
				<div style="height:10px;">
					<span class="message">
					<em style="color:red;">${message}</em> 
					</span>
				</div><p>
			</c:otherwise>
		</c:choose>
	</fieldset>
		
		

		
	

</form>
</body>
</html>