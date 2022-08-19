<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<script type="text/javascript" src="./resources/js/validation.js"></script>
<style type="text/css">
.container{
position:relative;
left:0%;
width:50%; 
top:100px;
}
#att_zone:empty:before{
	content : attr(data-placeholder);
	}
	#att_zone{
	width: 70%;
	height:100%;
	min-height:150px;
	border:1px dotted #00f;
	}

</style>
<script type="text/javascript">

function checkAdd(){
	var input=confirm('한번 등록하면 변경이 아닌 삭제만 가능합니다. 등록하시겠습니까?');
	 if(input==true){
		return true;
	 }else{
		 return false;
	 }
	 return false;
}
	

</script>
<jsp:include page = "header.jsp"/>
<title>상품 등록</title>
</head>
<body>
<!-- 	<div class="jumbotron">
		<div class="container">
				상품 등록
		</div>
	</div>
	 -->
	<div class="container" >
		<div>
		<!-- onsubmit="return checkAdd()" -->
			<!-- <a href="logout.jsp">logout</a> -->
			<h3 style="margin-top:20px;">상품 등록</h3>
			<hr style="width: 600px;">
		</div>
		
		<form name="newProduct" action="ProductAdd" class="form-horizontal" method="post" 
		enctype="multipart/form-data"
		>
		<!-- enctype="multipart/form-data" -->
		<%-- <input type="hidden" name="sseq" value="${authInfo.sseq}"> --%>
			<div class="form-group row">
				<label class="col-sm-2">상품 종류</label>
				<div class="com-sm-3">
				<select name="kind">					  
				<option value="종류 선택">종류 선택</option>
				<option value='1'>1.과일</option>
				<option value='2'>2.채소</option>
				<option value='3'>3.쌀/잡곡</option>
				<option value='4'>4.견과류</option>
			</select>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품 이름</label>
				<div class="com-sm-3">
					<input type="text" id="name" name="name" >
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">원가</label>
				<div class="com-sm-3">
					<input type="text" id="unitPrice" name="cost" class="form-control">
				</div>
			</div>
			
				<div class="form-group row">
				<label class="col-sm-2">판매가</label>
				<div class="com-sm-3">
					<input type="text" id="unitPrice" name="price" class="form-control">
				</div>
			</div>
			
				<div class="form-group row">
				<label class="col-sm-2">세일가</label>
				<div class="com-sm-3">
					<input type="text" id="unitPrice" name="sale_price" class="form-control">
				</div>
			</div>
			
	
			<div class="form-group row">
				<label class="col-sm-2">재고 수</label>
				<div class="com-sm-3">
					<input type="text" id="unitsInStock" name="quantity" class="form-control">
				</div>
			</div>
			
			<!-- <div class="form-group row">
				<label class="col-sm-2" >세일여부</label>
				<div class="com-sm-5">
					<input type="radio" name="sale" value="y"> Yes
					<input type="radio" name="sale" value="n"> No
				</div>
			</div> -->
			
			<div class="form-group row" id="image_previews" >
				<label class="col-sm-2">상품 이미지</label>
				<div id="att_zone">
					<input type="file" class="fileall" id="btnAtt" name="productImage" multiple="multiple">
						
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록">
				</div>
			</div>
			
		</form>
	</div>
	
	
	
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