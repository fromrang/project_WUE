<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"></script>
</head>
<body>
<jsp:include page = "header.jsp"/>

 
<form action='farmAdd' method="post" enctype="multipart/form-data">
      <main class="to_do_list">
         <div style="margin-top: 110px; margin-left: 630px;">

            <table class="register" border=1 align=center cellspacing=1
               width=600>
               <tr>
                  <td colspan=2 align=center height=30>주말 농장 등록</td>
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
                  <td><input type="time" name="experience_time" ></td>
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
                  <td align=center width=200 height=250>내용(소개)</td>
                  <td><div id="detail">
                        <textarea rows="" cols="" id="detail" name="experience_context" size=200
                           maxlength=250 style="width: 383px; height: 221px;"></textarea>
                     </div></td>
               </tr>
               <tr>
               <td align=center width=200 height=250>사진첨부</td>
             <td> 
				<div id="att_zone">
					<input type="file" class="fileall" id="btnAtt" name="productImage" multiple="multiple">
						
				</div>
			 </td>
			</tr>
               <tr>
                  <td colspan=2 height=2 background="dot.gif"></td>
               </tr>
               <tr>
                  <td colspan=2 align=center height=30><input type=submit
                     value=게시물등록> <input type=reset value=취소
                     onclick="location.href='board'"></td>
               </tr>
               <tr>
                  <td colspan=2 height=2 background="dot.gif"></td>
               </tr>
            </table>
   </div>
   </main>
   </form>
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
		    

		    
		    /*첨부된 이미리즐을 배열에 넣고 미리보기 */
		    imageLoader = function(file){
		      sel_files.push(file);
		      var reader = new FileReader();
		      reader.onload = function(ee){
		    	  var fileInput = document.getElementsByClassName("fileall");
		    	  
		        let img = document.createElement('img')
		        img.setAttribute('style', img_style)
		 
		        img.src = ee.target.result;
		        var x = document.createElement("input");
		    	 x.setAttribute('type', 'file')
		     	 x.setAttribute("name",file.name);
		    	 x.setAttribute("multiple",'multiple');
		    	 x.setAttribute("style",'display:none');
		        attZone.appendChild(makeDiv(img, file,x));
		      }
		      
		      reader.readAsDataURL(file);
		    }
		    
		    /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
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
		       /*   attZone.removeChild(x);  */
		      }
		      div.appendChild(img)
		      div.appendChild(btn)
		      div.appendChild(x);
		      return div
		    }
		  }
		)('att_zone', 'btnAtt')


</script>
</body>
</html>