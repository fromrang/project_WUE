<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#wrap{
	width:490px;
	text-align: center;
	margin: 0 auto 0 auto;
}
#chk{
	text-align: center;
}

</style>
<script type="text/javascript">
function useEmail() {
	var email= idcheck.email.value;
	if(email.includes("@")==true){
		str=email.split(/[@]/);
		/* alert(str[0]); */
		window.opener.document.getElementById("email").value=str[0];
		window.opener.document.getElementById("email").readOnly=true;
		const el=window.opener.document.getElementById("email2");
		const len=el.options.length;
		for(let i=0;i<len;i++){
			if(el.options[i].value==str[1]){
				el.options[i].selected=true;
			}
		}
		self.close();
	}else{
		window.opener.document.getElementById("email").value=email;
		self.close();
	}
}

function idCheck(){
	var email= idcheck.email.value;
	if(email.includes("@")==true){
		str=email.split(/[@]/);
		if(str[1]!='naver.com'&&str[1]!='daum.net'&&str[1]!='nate.com'
			&&str[1]!='gmail.com')
         alert("이메일 형식이 틀립니다.다시 입력해주세요");                
		email.focus();
         return false;
         }
	return true;
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrap">
		<br>
		<b><font size="4" color="gray">아이디 중복체크</font></b>
		<hr size="1" width="350">
		<br>
		<div id="chk">
			<form action="Idcheck" method="get" name="idcheck" onsubmit="idCheck()">
				<input type="text" name="email" value="${param.email}">
					<input type="submit" value="다시 중복확인">
					<input type="hidden" name="email2" value="${param.email2}">
			</form>
			<div id="msg">
			<br>
			  <div>
				<c:choose>
					<c:when test="${customer.name==null}">
						<p style="color: gray;">사용가능한 아이디 입니다</p>
						<input id="cancelBtn" type="button" value="취소">
						<input id="useBtn" type="button" value="사용하기" onclick="useEmail()">
					</c:when>
					<c:otherwise>
					<p style="color: gray;">이미 사용중인 아이디 입니다.
					<input id="cancelBtn" type="button" value="취소">
					</p>
					</c:otherwise>
				</c:choose>
			  </div>
			
			</div>
		</div>
	</div>
</body>
</html>