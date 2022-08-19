<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

		 function nullCheck(){
			 
			 var name = document.getElementById('name');
			 if(name.value == ""){
		          alert("이름을 입력해 주세요!!!");                
		          name.focus();
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
			 
			var tel1=document.getElementById('phone1');
			 var tel2=document.getElementById('phone2');
			 var tel3=document.getElementById('phone3');
			 if(tel1.value==""||tel2.value==""||tel3.value==""){
				 alert('전화번호를 입력해주세요!');
				 return false;
			 }
			 
			 return true;
		 }
		
		// name=frm1  
		 
		function checkDrop() {
			var input=confirm('한번 등록하면 변경이 아닌 삭제만 가능합니다. 등록하시겠습니까?');
			 if(input==true){
				 document.frmmember.action="SDelete";
		         document.frmmember.submit();
			 }else{
				 
			 }
			 
		}
	</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />

<form name="frmmember"action="SUpdate"  method="post" 	onsubmit="return nullCheck();">
	<!-- true가 되어야 전송이 가능한 것, 투르가 아니면 전송을 못함 -->
	<article style="margin-top: 100px;">
		<h1 style=" position:relative; left: 30%;">회원 정보수정</h1>
		<fieldset style="width: 600px; position:relative; left: 30%;" >
	<table border="0" align=center cellspacing=1 bordercolor=white bordercolorlight=white bgcolor=white >
<tr><td colspan=2 height=2 background="dot.gif"></td></tr>

<tr><td colspan=2 height=2 background="dot.gif"></td></tr>
<tr>
   <td colspan=1 bgcolor=MistyRose align=center>E-mail주소 </td>
   <td>
      <input type=text id='email' name=email size=12 maxlength=12 readonly="readonly" value="${user.email}" style="width: 150px;">(이메일은 수정 불가능합니다.)
</tr>
<tr>
   <td colspan=1 bgcolor=MistyRose align=center >이름</td>
   <td><input type=text id='name' name='name' size=12 maxlength=20
   value="${user.name}"
   pattern="^[가-힣]*{2,10}" >&nbsp;(한글 2자~10자로 입력해주세요)</td>
</tr>

<tr>
   <td bgcolor=MistyRose align=center>비밀번호 재설정</td>
   <td> 
      <input type=password id='pw' name=pw size=12 maxlength=20  
      pattern="[a-zA-Z0-9]{6,20}">&nbsp;(영어 및 숫자만 허용,6글자~20글자)</td>
</tr>
<tr>
   <td bgcolor=MistyRose align=center>비밀번호 확인</td>
   <td><input type=text id='pw2' name=pw2 size=12 maxlength=20
    pattern="[a-zA-Z0-9]{6,20}">&nbsp;(영어 및 숫자만 허용,6글자~20글자)</td>
</tr>

<tr><td colspan=2 height=2 background="dot.gif"></td></tr>


<tr><td colspan=2 height=2 background="dot.gif"></td></tr>
<tr>
   <td bgcolor=MistyRose align=center>휴대폰번호</td>
   <td>
        <input type=text id='phone1' name=phone1 size=4 maxlength=3
        value="${tel[0]}" pattern="[0-9]{3}">&nbsp;-&nbsp;
      <input type=text id='phone2' name=phone2 size=4 maxlength=4
      value="${tel[1]}"  pattern="[0-9]{3,4}">&nbsp;-&nbsp;
      <input type=text id='phone3' name=phone3 size=4 maxlength=4
     value="${tel[2]}"  pattern="[0-9]{4}">
      </td>
</tr>
<tr><td colspan=2 height=2 background="dot.gif"></td></tr>
<tr>
	<td bgcolor=MistyRose align=center>우편번호</td>
	<td>
	<input type=text name=zip_num size=3 maxlength=3 id="post" value="${user.zip_num}" style="width: 100px;">
	<img style = "width:3%;vertical-align:middle;" id='dc' src="/webShopping/image/search.png" onclick="sample4_execDaumPostcode()"></td>
</tr>
<tr>
	<td bgcolor=MistyRose align=center>주소</td>
	<td><input type=text name=address value="${user.address}" size=40 maxlength=50 id="add" style="width: 300px;">
	<span id="guide" style="color:#999;display:none"></span>
	</td>
</tr>

<tr>
   <td colspan=2 bgcolor=white align=center height=30>   
      <input type=submit value='수정하기'>
       <input type="button" value='취소' onclick='location.href= "SMain"' />
        <input type="button" value='탈퇴하기' onclick=' checkDrop()' />  
   </td>
</tr>
</table>
</fieldset>
</article>
</form>

<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
var element_layer = document.getElementById('layer');

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('post').value = data.zonecode;
            document.getElementById("add").value = roadAddr;
            document.getElementById("add").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
         /*    if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }
 */
            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
</script>
</body>
</html>