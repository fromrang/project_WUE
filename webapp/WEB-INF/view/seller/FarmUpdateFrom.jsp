<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function delteFramAll() {
	
document.form.action="deltefarmAll";
document.form.submit();
	
}

function updateFarm() {
	
	document.form.action="farmUpdate";
	document.form.submit();
		
	}
	
function back() {
	
	document.form.action="myinstagramProfile";
	document.form.submit();
		
	}

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page = "header.jsp"/>
	<form action='farmUpdate' method="get" name="form">
		<main class="to_do_list">
			<div style="margin-top: 110px; margin-left: 630px;">

				<table class="register" border=1 align=center cellspacing=1
					width=600>
					<tr>
						<td colspan=5 align=center height=30>주말 농장 변경</td>
					</tr>
					<tr>
						<td colspan=5 height=2 background="dot.gif"></td>
					</tr>
					<tr>
						<td colspan=5 align=center height=30>예약 일자 변경</td>
					</tr>
					<tr>
						<td align=center width=120>예약일</td>
						<td align=center width=120>예약시간</td>
						<td align=center width=120>예약가격</td>
						<td colspan=2 align=center width=240>예약가능 인원</td>
						</tr>
						<c:forEach var="i" begin="0"
							end="${posting2[0].getFramList().size()-1}">
					<tr>
							
							<td width=150 align=center><input type="date" name="experience_date"
								value="${posting2[0].getFramList().get(i).experience_date }">
							<input type="hidden" name="fseq" value="${posting2[0].getFramList().get(i).fseq}">
							<c:set value="${posting2[0].getFramList().get(i).fseq}" var="fseq"></c:set>
							</td>


							<td width=150 align=center><input type="time" name="experience_time"
								value="${posting2[0].getFramList().get(i).experience_time}">
							</td>


							<td width=150 align=center><input type=text id='name' name='experience_price'
								size=12 maxlength=10
								value="${posting2[0].getFramList().get(i).experience_price}">
							</td>


							<td width=150 align=center><input type=text id='title' name='experience_number'
								size=10 maxlength=30
								value="${posting2[0].getFramList().get(i).experience_number}">명
							</td>
							<td>
							<c:choose>
							<c:when test="${posting2[0].getFramList().get(i).fseq!=posting2[0].getFramList().get(0).fseq}">
							<input type="button" value="삭제" onclick="location.href='deltefarm?fseq=${posting2[0].getFramList().get(i).fseq}'">
							</c:when>
							<c:otherwise>
							<input type="button" value="수정" onclick=" updateFarm()">
							</c:otherwise>
							</c:choose>
							</td>
					</tr>
						</c:forEach>
					
					<tr>
						<td align=center width=200 height=250 >내용(소개) 변경</td>
						<td colspan=5><div id="detail">
								<textarea rows="" cols="" id="detail" name="experience_context"
									size=200 maxlength=250 style="width: 383px; height: 221px;">${posting2[0].getFramList().get(0).experience_context}
                     </textarea>
							</div></td>
					</tr>
					<tr>
                  <td colspan=5 align=center height=30><input type=submit
                     value=게시물수정>
                     <input type="button" onclick="delteFramAll()" value="전체 삭제">
                      <input type="button" value=취소
                     onclick="back()"></td>
               </tr>
               <tr>
                  <td colspan=5 height=2 background="dot.gif"></td>
               </tr>
				</table>
			</div>
		</main>
	</form>

</body>
</html>