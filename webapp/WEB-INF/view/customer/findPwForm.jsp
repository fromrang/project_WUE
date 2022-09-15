<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
<form action="/WUE/customer/findPw" method="post" >
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
		<div style="height:10px;">
			<span class="message">
			이메일
			</span>
		</div><p>
		<input class="field" type="text" name= "email" size = "4" >
		<input type="submit" class="login-btn" value="임시 비밀번호 발급받기" size = "100">
		<div style="height:10px;">
			<span class="message">
			${password} 
			${message}
			</span>
		</div><p>
	</fieldset>

</form>
</body>
</html>