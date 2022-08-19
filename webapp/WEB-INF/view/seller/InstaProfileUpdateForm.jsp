
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.profile_image{
width: 100px;
height: 100px;
border-radius: 500px;
display: block; 
margin-left: auto;  margin-right: auto;
margin-top: 3px;
}
.filebox{
position: relative;
left: 180px;
margin-top: 10px;
margin-bottom:10px;
}

.click_image{
position:relative;
left:-80px;
background-color:#E2E2E2;
}

.btn btn-default{
position:relative;
margin-left: 30px;
}
.info{
border: 2px solid Gray; 
width: 50%;
position:relative; 
left:25%;
margin-top:10px;
}

.form-control{
width:300px;
height: 20px;
margin-top:3px;
margin-left: 50px;

}


.form-control2{
width:300px;
height: 40px;
margin-top:3px;
margin-left: 50px;
overflow: auto;

}


#property{
margin-left: 50px;
font-family: arial
}

</style>
<script type="text/javascript">
function readUrl(input) {
if(input.files&&input.files[0]){
	var reader=new FileReader();
	reader.onload=function(e){
		document.getElementById('preview').src=e.target.result;
	};
	 reader.readAsDataURL(input.files[0]);
}else {
    document.getElementById('preview').src = document.getElementById('frist').value;
}
}


</script>
<jsp:include page = "header.jsp"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<article style="margin-top: 110px;">
<form action="instagramProfileUpdate" id="form" name="form"
			method="post" enctype="multipart/form-data" >
		<!-- 	autocomplete="off" -->
<div class="contents" style="border: 2px solid Gray; width: 50%;position:relative; left:25%; ">
	<div class="img">
		<div class="title_image">
			<c:choose>
				<c:when test="${user.url == ''}">
					<img src="/WUE/img/noimage.png" id="preview" class="profile_image">
					<input type="hidden" id="frist" value="http://localhost:8080/webShopping/image/noimage.png">
				</c:when>
				<c:otherwise>
					<img src="/WUE/img/${user.url}"  id="preview" class="profile_image">
					<input type="hidden" id="frist" value="http://localhost:8080/webShopping/image/${user.url}">
					<input type="hidden" name="url" value="${user.url}">
				</c:otherwise>
			</c:choose>
		</div>
	</div>
		
	<div class="filebox">
			<!-- <label for="ex_file">프로필 사진 바꾸기</label> 
			<input type="file" id="ex_file" name="filename" required /> -->
			<input type="file" id="img" name="img" style="display:none;"
			onchange="readUrl(this)"/>
			<label for="img" class="click_image">프로필 사진 바꾸기</label>
			<button type="submit" class="btn btn-default"
			style="border:1px solid gray;background-color: white;border-radius:5px;">업로드</button>

	</div>
</div>
<div class="info">
	
		<div class="form-group">
			<label for="name" id="property">Email</label>
			<br>
			<input type="text" class="form-control" 
				name="seller_email"  value="${user.seller_email}" readonly/>
		</div>
		<%-- <div class="form-group">
			<label for="website" id="property">웹사이트(주말농장)</label>
			<br>
			<input type="text" class="form-control" id="website" 
				name="webSite" placeholder="웹사이트" value="${user.webSite}" />
		</div> --%>
		<div class="form-group">
			<label for="intro" id="property">소개(Comment)</label>
			<br>
			<textarea class="form-control2"  
				name="commnet">${user.comment}</textarea>
		</div>
		<button type="submit" class="btn btn-default" style="border:1px solid gray;background-color: white;border-radius:5px;
		margin-top: 4px; margin-bottom: 3px;margin-left:330px;">완료</button>
</div>
	</form>
	</article>
</body>
</html>