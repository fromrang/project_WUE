<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
<style type="text/css">
.txt {
	display: inline-table;
	position: relative;
	left: 90px;
	top: -60px;
	margin: 10px;
}
.btn-default{
text-decoration: none;
color: gray;
position:relative;
left: 50px;
}

.contents {
	bottom: 6rem;
}
.title_ft{
text-decoration: none;
}
</style>
<jsp:include page="header.jsp" />
</head>
<body>
<body>
	<div class="contents"
		style="border: 2px solid Gray; width: 50%; height: 300px; position: relative; left: 450px; top: 120px;">
		<div class="nav">
			<span class="title"
				style="position: absolute; left: 50%; transform: translate(-50%)">
				<a href="/main/user/${user.seller_email}" class="title_ft"
				style="margin: 5px;">${user.seller_email}님의 WUE</a>
			</span>
		</div>

		<div class="profile" style="position: relative; height: 150%;">
			<div class="title_image">
				<c:choose>
					<c:when test="${user.url == ''}">
						<img src="/WUE/img/noimage.png" class="profile_image"
							style="border-radius: 500px; width: 100px; height: 100px; margin: 20px; position: relative; left: 70px; top: 20px;">
					</c:when>
					<c:otherwise>
						<img src="/WUE/img/${user.url}" class="profile_image"
							style="border-radius: 500px; width: 100px; height: 100px; margin: 20px; position: relative; left: 70px; top: 20px;">
					</c:otherwise>
				</c:choose>
				<div class="txt">
				<c:choose>
				<c:when test="${fram=='[]'}">
					<span style="position: relative; left: 40%">${userPostNumber.size()}</span><br>
					<span>게시물</span>
					</c:when>
					<c:otherwise>
					<span style="position: relative; left: 40%">${userPostNumber.size()+1}</span><br>
					<span>게시물</span>
					</c:otherwise>
					</c:choose>
				</div>

				<div class="txt">
					<span style="position: relative; left: 40%">0</span><br>
					<span>팔로워</span>
				</div>

				<div class="txt">
					<span style="position: relative; left: 40%">0</span><br>
					<span>팔로잉</span>
				</div>
			</div>

			  <div class="comment" style="border: 2px solid white-space; width:400px;height:25%;position:relative;left:70px;top:-30px; 
			  overflow: hidden;">
			  <p style="color: gray ;margin-top: 30px;" > ${user.comment}</p>
			
			  </div>
			<c:choose>
					<c:when test="${user.seller_email==authInfo.email}">
			<div class="fix"
				style="border:1px solid gray ;width:200px; position: absolute; top: 60%; left: 20%; transform: translate(-50%)">
				<a href="instagramProfileUpdate?seller_email=${user.seller_email}" class="btn btn-default">프로필 수정</a>
			</div>
			<c:choose>
			<c:when test="${fram=='[]'}">
			<div class="postInsert"
				style="border:1px solid gray ;width:200px; position: absolute; top: 60%; left: 50%; transform: translate(-50%);">
				<a href="farmfist" class="btn btn-default">주말 농장 등록</a>
			</div>
			</c:when>
			<c:otherwise>
				<div class="postInsert"
				style="border:1px solid gray ;width:200px; position: absolute; top: 60%; left: 50%; transform: translate(-50%);">
				<a href="farmForm" style="text-decoration: none;color: gray;">    주말 농장 추가등록</a>
				|<a href="farmUpdateForm" style="text-decoration: none;color: gray;" >  수정</a>
			</div>
			
			</c:otherwise>
			</c:choose>
			<div class="postInsert"
				style="border:1px solid gray ;width:200px; position: absolute; top: 60%; left: 80%; transform: translate(-50%); ">
				<a href="PostAdd" class="btn btn-default">게시물 등록</a>
			</div>
			
					</c:when>
			</c:choose>
		</div>
	</div>

	<c:choose>
		<c:when test="${userPostNumber.size()==0}">
			<div id="bestProduct"
				style="border: 2px solid Gray; margin: 50px; width: 50%; position: relative; left: 400px;top:130px;">
				<span
					style="position: absolute; left: 50%; transform: translate(-50%)">${user.seller_email}님의
					게시물 </span>
				<div id="item" style="margin-top: 25px; margin-left: 10px;">
					<span >	게시물이 현재 없습니다. </span>
				</div>
			</div>
		</c:when>
		<c:otherwise>		
		<div id="bestProduct"
			style="border: 2px solid Gray; margin: 50px; width: 50%; position: relative; left: 400px;top:130px;">
			<span
				style="position: absolute; left: 50%; transform: translate(-50%)">${user.seller_email}님의
				게시물 </span>
			<div id="item">
				<c:choose>
			   <c:when test="${framimage!='[]'}">
					<a href="instagramSelectfarm?email=${user.seller_email}"> <img
						src="/WUE/img/${framimage[0]}" 
						style="width: 200px; height: 200px; display: run-in; margin-top: 25px; margin-left: 10px;" /></a>
				<span style="position:absolute;border:2px solid gray;top:26px;left:11px;color:blue;background-color:white ">주말농장</span>
				</c:when>
				</c:choose>
				<c:forEach var="i" begin="0" end="${userPostNumber.size()-1}">
					<a href="instagramSelectPost?postnumber=${userPostNumber[i]}&email=${user.seller_email}"> <img
						src="/WUE/img/${imageFrist[i]}"
						style="width: 200px; height: 200px; display: run-in; margin-top: 25px; margin-left: 10px;" /></a>
				</c:forEach>
				
			</div>
		</div>
		</c:otherwise>
		</c:choose>
</body>
</html>