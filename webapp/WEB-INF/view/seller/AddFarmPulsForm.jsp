<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page = "header.jsp"/>
<form action='farmPuls' method="get" >
   		<main class="to_do_list">
         <div style="margin-top: 110px; margin-left: 630px;">

            <table class="register" border=1 align=center cellspacing=1
               width=600>
               <tr>
                  <td colspan=2 align=center height=30>주말 농장 추가 등록</td>
               </tr>
               <tr>
                  <td colspan=2 height=2 background="dot.gif"></td>
               </tr>
               <tr>
                  <td align=center width=200>예약일</td>
                  <td> <input type="date" name="experience_date"></td>
               </tr>
               <tr>
                  <td align=center width=200>예약시간</td>
                  <td><input type="time" name="experience_time"></td>
               </tr>
               <tr>
                  <td align=center width=200>예약가격</td>
                  <td><input type=text id='name' name='experience_price' size=12 maxlength=10></td>
               </tr>
               <tr>
                  <td align=center width=200>예약가능 인원</td>
                  <td><input type=text id='title' name='experience_number' size=12
                     maxlength=30>명</td>
               </tr>
               <tr>
                  <td colspan=2 height=2 background="dot.gif"></td>
               </tr>
               <tr>
                  <td colspan=2 align=center height=30><input type=submit
                     value=게시물등록> <input type=reset value=취소
                     onclick="location.href='myinstagramProfile'"></td>
               </tr>
               <tr>
                  <td colspan=2 height=2 background="dot.gif"></td>
               </tr>
               </table>
               </div>
               </main>
               </form>
</body>
</html>