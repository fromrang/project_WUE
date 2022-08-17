<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	td{font-size:9pt}
	.join-btn{
		border: none;
		width:305px;
		height:30px;
		border-radius: 5px;
		background-color: #FEDD89;
		color: #12467a;
		cursor:pointer;
	}
</style>
<script type="text/javascript">
	
	function idCheck() {
/* 		if(document.frmmember.email2.value == "이메일 선택"){
	          alert("이메일을 선택하여 주세요.");
	    }else{	  
			window.open('Idcheck?email='+frmmember.email.value+'&email2='+frmmember.email2.value,'popup','width=500,height=300');
		} */
		
		window.open('Idcheck?email='+frmmember.email.value+'&email2='+frmmember.email2.value,'popup','width=500,height=300');
	}
	
	 function nullCheck(){
		 
		 var name = document.getElementById('name');
		 if(name.value == ""){
	          alert("이름을 입력해 주세요!!!");                
	          name.focus();
	          return false;
	          }
	          
		 var email=document.getElementById('email');
		 if(email.value==""){
			 alert('이메일을 입력해주세요!');
			 return false;
		 }
		 
		if(document.frmmember.email2.value == "이메일 선택"){
	          alert("이메일을 선택하여 주세요.");
	          document.frmmember.email2.focus();
	          return false;
	       }
		 
	          
		 var pw = document.getElementById('pw');
		 if(pw.value==""){
				alert('비밀번호를 입력해주세요~');
				pw.focus();
				return false;
		 }
		 
		 var pwMore=document.getElementById('pw2');
		 if(pwMore.value==""){
			 alert('비밀번호를 한번더 입력해주세요');
			 pwMore.focus();
			 return false;
		 }
		 
		 if(pwMore.value!=pw.value){
			 alert('동일한 비밀번호가 아닙니다.');
			 return false;
		 }
		
		 
		if(document.frmmember.phone.value == "휴대전화 선택"){
	          alert("휴대번호를 선택하여 주세요.");
	          document.frmmember.phone.focus();
	          return false;
	       }
		 
		 
		 var tel2=document.getElementById('phone2');
		 var tel3=document.getElementById('phone3');
		 if(tel2.value==""||tel3.value==""){
			 alert('전화번호를 입력해주세요!');
			 return false;
		 }
		 
		 return true;
	 }
		
		// name=frm1  
		 
</script>
</head>
<body>
	<form name="frmmember" action="Join" onsubmit="return nullCheck();" method="post" >
		<!-- true가 되어야 전송이 가능한 것, 투르가 아니면 전송을 못함 -->
		<h1 style="text-align:center; margin-top: 80px; color: #12467a;">회원 가입</h1>
		<fieldset style="margin: auto; width: 500px; height:180px; padding: 30px">
			<table border="0" align=center cellspacing=1 bordercolor=white bordercolorlight=white bgcolor=white >
				<tr>
				   <td colspan=1 align=center style="background-color: #FEDD89;">이름</td>
				   <td>
				   	<input type=text id='name' name='name' size=20 maxlength=20 pattern="^[가-힣]*{2,10}">&nbsp;(한글 2자~10자로 입력해주세요)
				   </td>
				</tr>
				<tr>
				   <td colspan=1 align=center style="background-color: #FEDD89;">E-mail주소 </td>
				   <td>
					   <input type=text id='email' name=email size=12 maxlength=12 onclick="idCheck()" readonly>&nbsp;@&nbsp;
					   <select id='email2' name=email2 >
							<option value="이메일 선택">이메일 선택</option>
						    <option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="daum.net">daum.net</option>
							<option value="nate.com">nate.com</option>
					   </select>	
					 <img style = "width:3%;vertical-align:middle;" id='dc' src="/webShopping/image/search.png" onclick="idCheck()">
				   </td>
				</tr>
			
			<tr>
			   <td align=center style="background-color: #FEDD89;">비밀번호</td>
			   <td> 
			      <input type=password id='pw' name=pw size=12 maxlength=20
			      pattern="[a-zA-Z0-9]{6,20}">&nbsp;(영어 및 숫자만 허용,6글자~20글자)</td>
			</tr>
			<tr>
			   <td align=center style="background-color: #FEDD89;">비밀번호 확인</td>
			   <td><input type=password id='pw2' name=pw2 size=12 maxlength=20
			    pattern="[a-zA-Z0-9]{6,20}">&nbsp;(영어 및 숫자만 허용,6글자~20글자)</td>
			</tr>
			
			<tr><td colspan=2 height=2 background="dot.gif"></td></tr>
			
			
			<tr><td colspan=2 height=2 background="dot.gif"></td></tr>
			<tr>
			   <td align=center style="background-color: #FEDD89;">휴대폰번호</td>
			   <td>
			<select id='phone' name=phone>
			      <option value="휴대전화 선택">휴대전화 선택</option>
							<option value=010>010</option>
							<option value=011>011</option>
							<option value=017>017</option>
						</select>-
			      <input type=text id='phone2' name=phone2 size=4 maxlength=4>&nbsp;-&nbsp;
			      <input type=text id='phone3' name=phone3 size=4 maxlength=4></td>
			</tr>
			<tr>
			   <td colspan=2 bgcolor=white align=center height=30>
			   </td>
			</tr>
			<tr>
			   <td colspan=2 bgcolor=white align=center height=30>   
			      <!-- <input type=submit value=가입하기 > -->
			      <input type="submit" class="join-btn" value="가입하기" size = "100">
			      
					<input type=reset value=다시입력> 
			      
			   </td>
			</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
