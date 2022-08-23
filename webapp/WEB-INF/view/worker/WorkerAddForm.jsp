<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>	
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="/WUE/css/import.css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Worker Login Page</title>
<style>
td {
	font-size: 9pt
}
</style>
	<script type="text/javascript">
	function nullCheck() {
		var id = document.getElementById('id');
		var pw = form.pw.value;
		var name = form.name.value;
		var email = form.email.value;
		var phone = form.phone.value;
		
		if (id.value == "") {
			alert("아이디를 입력해 주세요.");
			id.focus();
			return false;
		} else if (pw.value == "" || pw2.value == "") {
			alert("비밀번호를 입력해 주세요.");
			pw.focus();
			return false;
		} else if (name.value == "") {
			alert("이름을 입력해 주세요.");
			name.focus();
			return false;
		} else if (email.value == "" || email2.value == "") {
			alert("이메일을 입력해 주세요.");
			email.focus();
			return false;
		} else if (phone.value == "" || phone2.value == ""
				|| phone3.value == "") {
			alert("휴대폰번호 입력해 주세요.");
			phone.focus();
			return false;
		} else if(form.idDuplication.value != "idCheck"){
			
		}
		return false;
		return true;
	}

	function idCheck() {
		var id = form.id.value.trim();
		window.open('idcheck?id=' + id, 'popup', "width=500,height=200");

	}
</script>
</head>
<body>
	<header class="header">
		<div class="header__img">
			<img src="/WUE/img/logoWorker.png" />
		</div>
		<div style="margin-right: 125px; float: right; margin-top: -85px;"><%=sf.format(nowTime)%></div>
		<div style="margin: 20px; float: right; margin-top: -85px;"><span id="dpTime">오후 00:00:00</span></div>		
		<div class="header__img" style="margin-top: -50px;">
			<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span> 관리자 모드 </span>
		</div>
	</header>
	<script src="https://kit.fontawesome.com/4a9dbb7224.js"
		crossorigin="anonymous"></script>

	<nav class="menu">
		<table>
			<tr>
				<th style="border: 4px outset pink;">관리자 정보</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">회원 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">상품 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">이벤트 관리</th>
			</tr>
			<tr style="height: 140px;">
					<th style="border: 4px outset pink;">게시판 관리</th>
				</tr>
			<tr>
				<th style="border: 4px outset pink;">주문내역 관리</th>
			</tr>
			<tr>
				<th style="border: 4px outset pink;">매출 관리</th>
			</tr>
		</table>
	</nav>
	<form name=form action='add' method='get' onsubmit="return nullCheck();">
	<div style="margin-top: 300px; margin-left: 220px;">
		<table border=0 align=center cellspacing=1 bordercolor=white
			bordercolorlight=DarkRed bgcolor=Linen width=600>
			<tr>
				<td colspan=2 bgcolor=MistyRose align=center height=30>회원가입
			</td>
			</tr>	
			<tr>
				<td colspan=2 height=2 background="dot.gif"></td>
			</tr>
			<tr>
				<td bgcolor=MistyRose align=center width=100>아이디</td>
				<td><input type=text id='id' name=id required="required" size=12 maxlength=12>
					<img style="width: 3%; vertical-align: middle;" id='dc'
					src="/middle/img/search.png" value="증복확인" onclick="idCheck()"> (영어 및 숫자만 허용, 4자 ~
					12자 이내)</td>
					
			</tr>

			<tr>
				<td bgcolor=MistyRose align=center>비밀번호</td>
				<td><input type=password id='pw' name=pw pattern="^[a-z0-9_-]{6,20}$" title="영어 + 숫자, 6자~ 20자로 입력해주세요" required="required" size=12 maxlength=12>&nbsp;(영어 + 숫자, 6자~ 20자로 입력해주세요)</td>
			</tr>
			<tr>
				<td bgcolor=MistyRose align=center>비밀번호 확인</td>
				<td><input type=password id='pw2' name=pw2 size=12 maxlength=12>&nbsp;(영어 + 숫자, 6자~ 20자로 입력해주세요</td>
			</tr>
			<tr>
				<td bgcolor=MistyRose align=center>이름</td>
				<td><input type=text id='name' name=name pattern="^[가-힣]{2,10}$" title="한글
					2자~10자로 입력해주세요" required="required" size=12 maxlength=20>&nbsp;(한글
					2자~10자로 입력해주세요)</td>
			</tr>
			<tr>
				<td colspan=2 height=2 background="dot.gif"></td>
			</tr>

			<tr>
				<td bgcolor=MistyRose align=center>E-mail주소</td>
				<td><input type=text id='email' name=email size=12 maxlength=12>&nbsp;@&nbsp;		
					<select id='email2' name=email2>
						<option value=naver.com>naver.com</option>
						<option value=gmail.com>gmail.com</option>
						<option value=daum.com>daum.net</option>
						<option value=nate.com>nate.com</option>
					</select>	
				</td>
			</tr>
			<tr>
				<td colspan=2 height=2 background="dot.gif"></td>
			</tr>
			<tr>
				<td bgcolor=MistyRose align=center>휴대폰번호</td>
				<td><select id='phone' name=phone>
						<option value=010>010</option>
						<option value=02>011</option>
						<option value=070>070</option>
				</select>- <input type=text id='phone2' name=phone2 pattern="[0-9]{3,4}" title="숫자 3~4자리를 입력해주세요" size=4 maxlength=4>&nbsp;-&nbsp;
					<input type=text id='phone3' name=phone3 pattern="[0-9]{4}" title="숫자 4자리를 입력해주세요"size=4 maxlength=4></td>
			</tr>
			<tr>
				<td colspan=2 height=2 background="dot.gif"></td>
			</tr>
			<tr>
				<td colspan=2 bgcolor=MistyRose align=center height=30><input
					type=submit value=가입하기> <input type=reset value=다시입력>
				</td>
			</tr>
			<tr>
				<td colspan=2 height=2 background="dot.gif"></td>
			</tr>
			</center>
		</table>
	</div>	
	</form>
</body>
</html>