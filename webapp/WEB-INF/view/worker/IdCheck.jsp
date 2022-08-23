<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 확인</title>
</head>
<body>
	<b><font size="4" color="gray">ID 중복 확인</font></b>
	<br>
	<form name="IdCheck">
		<input type="text" name="id" value="${param.id}" id="worker" disabled>		
		<c:choose>
			<c:when test="${worker.id != null}">
				<p style="color:red">이미 사용 중인 아이디입니다.	<p>
				<input type ="hidden" name="result" value="N"/>		
				<input type="button" value="취소" onclick="window.close()">			
			</c:when>
			<c:when test="${worker.id == null}">
				<p style="color:red">사용 가능한 아이디입니다.<p>
				<input type ="hidden" name="result" value="Y"/>
				<input type="button" value="취소" onclick="window.close()">
		<!-- <input type="button" value="사용하기" onclick="sendCheckValue()"> -->
				<input type="button" value="사용하기" onclick="window.close()">
			</c:when>		
			<c:otherwise>				
				<input type ="hidden" name="result" value="N"/>
			</c:otherwise>			
		</c:choose>	
		
		
	</form>
</body>
</html>