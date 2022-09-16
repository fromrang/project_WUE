<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@2.4.1/dist/email.min.js"> </script> 
<script type="text/javascript"> 
	(function(){ 
    	emailjs.init("YOUR_USER_ID"); 
     })(); 
	
	var templateParams = {
		    name: 'James',
		    notes: 'Check this out!'
		};
	 
	emailjs.send('YOUR_SERVICE_ID', 'YOUR_TEMPLATE_ID', templateParams)
	    .then(function(response) {
	       console.log('SUCCESS!', response.status, response.text);
	    }, function(error) {
	       console.log('FAILED...', error);
	       
	    });
</script> -->
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
<form action="/WUE/customer/findPw" method="post" >
	<img src="/WUE/img/simbol.png" style="width: 100px; height: 100px; margin-left: 210px; margin-top: 70px">
	<!-- <h1 style="text-align:center; margin-top: 80px; color: #12467a;">아이디 찾기</h1> -->
	<div style="margin: 60px"></div>
	<fieldset style="margin: auto; width: 300px; height:280px;  background-color: white; ">
	<c:choose>
			<c:when test="${password != null}">
				<div style="height:100px; margin-top: 30px; text-align: center;">
					<span class="message" style="text-align: center;">임시 비밀번호는</span><br><br>
					<span class="message" style="text-align: center; color:blue">${password}</span><br><br>
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
				<input class="field" type="text" name= "phone1" size = "4" >-
				<input class="field" type="text" name= "phone2" size = "4" >-
				<input class="field" type="text" name= "phone3" size = "4" >
				<p>
				<div style="height:10px;">
					<span class="message">
					이메일
					</span>
				</div><p>
				<input class="field" type="text" name= "email" size = "30" ><p></p>
				<input type="submit" class="login-btn" value="임시 비밀번호 발급받기" size = "100">
				<p></p>
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