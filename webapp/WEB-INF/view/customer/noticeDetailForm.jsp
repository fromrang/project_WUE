<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page = "header.jsp"/>
<title>Insert title here</title>
<style type="text/css">
        ul {
    		list-style: none;
    		font-size: 12px;
		}
		.notice_list {
		    display: block;
		    list-style-type: disc;
		    margin-block-start: 1em;
		    margin-block-end: 1em;
		    margin-inline-start: 0px;
		    margin-inline-end: 0px;
		    padding-inline-start: 20px;
		}
		.notice_item{
			display: inline-block;
		    overflow: hidden;
		    max-width: 100%;
		    font-size: 12px;
		    line-height: 12px;
		    color: #666;
		    vertical-align: top;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		}
		li {
		    display: list-item;
		    text-align: -webkit-match-parent;
		}
		a {
		    text-decoration: none;
		    cursor: pointer;
		    color: #666;
		}
		.notice_title {
		    margin-bottom: 11px;
		    padding: 8px 0;
		    border-bottom: 2px solid #222;
		}
		.notice_area {
		    position: relative;
		    float: left;
		    width: 350px;
		    margin-left: 93px;
		    height: 150px;
		}
		.QnA_area{
		    position: relative;
		    float: left;
		    width: 350px;
		    margin-left: 130px;
		    height: 150px;
		}
		.notice_all_area{
			overflow: hidden;
		    width: 1280px;
		    margin: 0 auto;
		    padding: 40px 0;
		}
		.detail_title{
			padding: 10px 100px;
		}
		.detail_content{
			margin: 20px 100px; 
			padding: 20px 20px;
			background: #f5f5f5;
		}
		.detail_area{
			margin: 20px; 
		}
		
</style>
</head>
<body>
<div class="notice_all_area">
	<div class="notice_area" >
		<div class="notice_title" >
			<span>공지사항</span>
		</div>
		<div class="notice_content">
			<ul class="notice_list">
				<c:forEach var="notice" items="${noticeList}">
					<li class="notice_item">
						<a class="notice_click" href="notice?wseq=${notice.wseq}">${notice.title}</a>						
					</li><p>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="QnA_area" >
		<div class="notice_title">
			<span>자주하는 질문</span>
		</div>
		<div class="QnA_content">
			<ul class="notice_list">
				<c:forEach var="qna" items="${qnaList}">
					<li class="notice_item">
						<a class="notice_click" href="notice?wseq=${qna.wseq}">${qna.title}</a>						
					</li><p>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
<div class="detail_area">
	<div class="detail_title">
		<span>${wnotice.title}</span>
	</div>
	<div class="detail_content">
		<span>${wnotice.detail}</span>
	</div>
</div>
</body>
</html>