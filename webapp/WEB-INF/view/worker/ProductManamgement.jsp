<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<script type="text/javascript">
	setInterval("dpTime()", 1000);
	function dpTime() {
		var now = new Date();
		hours = now.getHours();
		minutes = now.getMinutes();
		seconds = now.getSeconds();

		if (hours > 12) {
			hours -= 12;
			ampm = "오후 ";
		} else {
			ampm = "오전 ";
		}
		if (hours < 10) {
			hours = "0" + hours;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (seconds < 10) {
			seconds = "0" + seconds;
		}
		document.getElementById("dpTime").innerHTML = ampm + hours + ":"
				+ minutes + ":" + seconds;
	}
</script>
<style type="text/css">
/* ul li태그에 리스트 스타일을 없앰 */
ul li {
	list-style: none;
}
/* a태그에 텍스트 밑줄을 없애고 색상을 #333 */
a {
	text-decoration: none;
	color: #333;
}
/* 글자크기를 16px 맑은 고딕 굵게하고 width넓이 200, 높이 30, 글자색은 검정색, 라인높이30px
menu박스 가운데정렬, 글자가운데 정렬 */
#menu2 {
	font: bold 16px "malgun gothic";
	width: 180px;
	color: black;
	line-height: 30px;
	margin: 0 auto;
	text-align: center;
}

/* menu태그 자식의 ul의 자식 li를 왼쪽정렬과 넓이 140설정 */
#menu2>ul>li {
	float: left;
	width: 120px;
	position: relative;
}

#menu2>ul>li>ul {
	width: 120px;
	display: none;
	position: absolute;
	font-size: 14px;
}

#menu2>ul>li:hover>ul {
	display: block;
}

#menu2>ul>li>ul>li:hover {
	background: pink;
	transition: ease 1s;
}

div {
	margin: 0;
	padding: 0;
}

.register {
	border: 4px outset pink;
}
</style>
<title>Member Management</title>
</head>
<body>
	<form action='reg' method='get'>
		<header class="header">
			<div class="header__img">
				<img src="/WUE/img/logoWorker.png" />
			</div>

			<div style="margin-right: 125px; float: right; margin-top: -90px;"><%=sf.format(nowTime)%></div>
			<div style="margin: 20px; float: right; margin-top: -90px;">
				<span id="dpTime">오후 01:44:40</span>
			</div>
			<div class="header__img" style="margin-top: -60px;">
				<i class="fas fa-user-cog fa-3x"></i>&nbsp;&nbsp;&nbsp;<span>${authInfo.name}
					님 로그인</span>
			</div>

		</header>
		<script src="https://kit.fontawesome.com/4a9dbb7224.js"
			crossorigin="anonymous"></script>
		<nav class="menu">
			<table>
				<tr>
					<th style="border: 4px outset pink;" onclick="location.href='list'">관리자
						정보</th>
				</tr>
				<tr style="height: 120px;">
					<th style="border: 4px outset pink;">
						<div id="menu2">
							<ul>
								<li><a href="management">회원 관리 &nbsp;&nbsp;&nbsp;</a>
									<ul>
										<li><a href="leave">탈퇴 관리 &nbsp;&nbsp;&nbsp;</a></li>
									</ul></li>
								<ul>
									<li>&nbsp;&nbsp;</li>
								</ul>
							</ul>
						</div>
					</th>
				</tr>
				<tr style="height: 140px;">
					<th style="border: 4px outset pink;">
						<div id="menu2">
							<ul>
								<li><a href="kind=1">상품 관리 &nbsp;&nbsp;&nbsp;</a>
									<ul>
										<li><a href="reg">상품 등록 &nbsp;&nbsp;&nbsp;</a></li>
										<li><a href="kind=1">상품 관리 &nbsp;&nbsp;&nbsp;</a></li>
									</ul></li>
								<ul>
									<li>&nbsp;&nbsp;</li>
									<li>&nbsp;&nbsp;</li>
									<li>&nbsp;&nbsp;</li>
									<li>&nbsp;&nbsp;</li>
								</ul>
							</ul>
						</div>
					</th>
				</tr>
				<tr>
					<th style="border: 4px outset pink;" onclick="location.href='event'">이벤트 관리</th>
				</tr>
				<tr style="height: 140px;">
					<th style="border: 4px outset pink;">
						<div id="menu2">
							<ul>
								<li><a href="board">게시판 관리 &nbsp;&nbsp;&nbsp;</a>
									<ul>
										<li><a href="board">게시판 등록 &nbsp;&nbsp;&nbsp;</a></li>
										<li><a href="qna">Q & A &nbsp;&nbsp;&nbsp;</a></li>
										<li><a href="notice">공지사항 &nbsp;&nbsp;&nbsp;</a></li>
									</ul></li>
								<ul>
									<li>&nbsp;&nbsp;</li>
									<li>&nbsp;&nbsp;</li>
								</ul>
							</ul>
						</div>
					</th>
				</tr>
				<tr>
					<th style="border: 4px outset pink;" onclick="location.href='seller_order'">주문내역 관리</th>
				</tr>
				<tr>
					<th style="border: 4px outset pink;">
					<div id="menu2">
						<ul>
							<li><a href="sales">매출 관리 &nbsp;&nbsp;&nbsp;</a>
								<ul>
									<li><a href="sales">이익 관리&nbsp;&nbsp;&nbsp;</a></li>
									<li><a href="expenditure">지출 관리 &nbsp;&nbsp;&nbsp;</a></li>
								</ul></li>
							<ul>
								<li>&nbsp;&nbsp;</li>
							</ul>
						</ul>
					</div>
				</th>
				</tr>
			</table>
		</nav>
		</form>

		<main class="to_do_list">
			<div style="margin-top: 300px; margin-left: 220px;">
				<form action="reg" method="post" enctype="multipart/form-data">
					<table class="register" border=1 align=center cellspacing=1
						width=600>
						<tr>
							<td colspan=2 align=center height=30>기본정보</td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
						<tr>
							<td align=center width=100>상품명</td>
							<td><input type=text id='name' name=name size=12
								maxlength=12></td>
						</tr>
						<tr>
							<td align=center>카테고리</td>
							<td><select id='kind' name=kind>
									<option value=1>카데고리</option>
									<option value=1>과일</option>
									<option value=2>채소</option>
									<option value=3>쌀/잡곡</option>
									<option value=4>견과류</option>
							</select></td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
						<tr>
							<td colspan=2 align=center height=30>판매정보</td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
						<tr>
							<td align=center width=100>판매가</td>
							<td><input type=text id='name' name=price size=12
								maxlength=12></td>
						</tr>
						<tr>		
							<td align=center width=100>세일 여부</td>
							<td><select id='sale' name=sale>
									<option value=1>세일 여부</option>
									<option value=y>Y</option>
									<option value=n>N</option>
							</select></td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
						<tr>
							<td colspan=2 align=center height=30>재고정보</td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
						<tr>
							<td align=center width=100>재고수량</td>
							<td><input type=text id='name' name=quantity size=12
								maxlength=12></td>
						</tr>
						<tr>
							<td colspan=2 align=center height=30>이미지 정보</td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
						<tr>
							<td align=center width=150 height=250>상품 이미지 등록</td>
							<td><div id="att_zone">
									<input type='file' id="btnAtt" name="productImage"
										multiple='multiple' size=12 maxlength=12>
								</div></td>
						</tr>


						<tr>
							<td colspan=2 align=center height=30><input type=submit
								value=상품등록> <input type=reset value=취소
								onclick="location.href='kind=1'"></td>
						</tr>
						<tr>
							<td colspan=2 height=2 background="dot.gif"></td>
						</tr>
					</table>
				</form>
			</div>
		</main>

		<!-- 사진 업로드 미리보기 -->
		<script type="text/javascript">
( /* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */
		  imageView = function imageView(att_zone, btn){

		    var attZone = document.getElementById(att_zone);
		    var btnAtt = document.getElementById(btn)
		    var sel_files = [];
		    
		    // 이미지와 체크 박스를 감싸고 있는 div 속성
		    var div_style = 'display:inline-block;position:relative;'
		                  + 'width:150px;height:120px;margin:5px;border:1px solid #00f;z-index:1';
		    // 미리보기 이미지 속성
		    var img_style = 'width:100%;height:100%;z-index:none';
		    // 이미지안에 표시되는 체크박스의 속성
		    var chk_style = 'width:30px;height:30px;position:absolute;font-size:24px;'
		                  + 'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:#f00';
		  
		    btnAtt.onchange = function(e){
		      var files = e.target.files;
		      var fileArr = Array.prototype.slice.call(files)
		      for(f of fileArr){
		        imageLoader(f);
		      }
		    }  
		    
		  
		    // 탐색기에서 드래그앤 드롭 사용
		    attZone.addEventListener('dragenter', function(e){
		      e.preventDefault();
		      e.stopPropagation();
		    }, false)
		    
		    attZone.addEventListener('dragover', function(e){
		      e.preventDefault();
		      e.stopPropagation();
		      
		    }, false)
		  
		    attZone.addEventListener('drop', function(e){
		      var files = {};
		      e.preventDefault();
		      e.stopPropagation();
		      var dt = e.dataTransfer;
		      files = dt.files;
		      for(f of files){
		        imageLoader(f);
		      }
		      
		    }, false)
		    

		    
		    /*첨부된 이미지를 배열에 넣고 미리보기 */
		    imageLoader = function(file){
		      sel_files.push(file);
		      var reader = new FileReader();
		      reader.onload = function(ee){
		        let img = document.createElement('img')
		        img.setAttribute('style', img_style)
		 
		        img.src = ee.target.result;
		        //이미지 여러장 넣기
		        var x = document.createElement("input");
		    	 x.setAttribute('type', 'file')
		     	 x.setAttribute("name",file.name);
		    	 x.setAttribute("style",'display:none');
		    	 //makeDiv에 x를 추가
		        attZone.appendChild(makeDiv(img, file,x));
		      }
		      
		      reader.readAsDataURL(file);
		    }
		    
		    /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
		   //makeDiv에 x를 추가
		    makeDiv = function(img, file,x){
		      var div = document.createElement('div')
		      div.setAttribute('style', div_style)
		      
		      var btn = document.createElement('input')
		      btn.setAttribute('type', 'button')
		      btn.setAttribute('value', 'x')
		      btn.setAttribute('delFile', file.name);
		      btn.setAttribute('style', chk_style);
		      btn.onclick = function(ev){
		        var ele = ev.srcElement;
		        var delFile = ele.getAttribute('delFile');
		        
		        for(var i=0 ;i<sel_files.length; i++){
		          if(delFile== sel_files[i].name){
		            sel_files.splice(i, 1);      
		          }
		        }
		        
		        dt = new DataTransfer();
		        for(f in sel_files) {
		          var file = sel_files[f];
		          dt.items.add(file);
		        }
		        btnAtt.files = dt.files;
		        var p = ele.parentNode;
		        attZone.removeChild(p)
		      }
		      div.appendChild(img)
		      div.appendChild(btn)
		      div.appendChild(x)
		      return div
		    }
		  }
		)('att_zone', 'btnAtt')
	</script>
</body>
</html>