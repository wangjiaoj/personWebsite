<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>uploadFile</title>
  <script src="resources/js/lib/jquery-2.1.4/jquery.js"></script>
   <script src="resources/js/lib/ajaxfileupload.js"></script> 
  <!-- include libraries BS3 -->
  <link rel="stylesheet" href="resources/css/bootstrap.css" />
  <script type="text/javascript" src="resources/js/bootstrap.js"></script>

  <!-- include summernote -->
  <link rel="stylesheet" href="resources/js/lib/summernote/summernote.css">
  <script type="text/javascript" src="resources/js/lib/summernote/summernote.js"></script>
  <script type="text/javascript" src="resources/js/lib/summernote/summernote-zh-CN.js"></script>
</head>
<body>
 <div id="summernote"></div>  
   <input type="submit" id="submitsummernote" value="提交">
  
<div>
<img src=""/>
</div>

<script>
$(function(){
   $("#summernote").summernote({
           height: 200,
           lang: 'zh-CN',
           toolbar: [
            //[groupname, [button list]]
            ['style', ['bold', 'italic', 'underline', 'clear']],
            ['font', ['strikethrough']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert',['link','picture']]
            ] 
             
      });
       $(".note-insert").on("click",function(){
         if(!($("#insertImageDiv").hasClass("form-group"))){
            $(".note-image-input").parent().attr("id","insertImageDiv");
            $("#insertImageDiv").css("position","relative")
            $("#insertImageDiv").empty().append($('<span class="btn btn-primary">选择文件</span>'+
           '<input type="file" name="file" id="uploadfile" style="position: absolute;top: 0px;opacity: 0;">'+
            '<input class="btn btn btn-primary" type="submit" value="提交" id="submitfile" style="float: right;">'));
           $("#submitfile").off("click");
           $("#submitfile").on("click",function(){
              sendFile() ;
           });
         }
       
       });
             
     function sendFile() {             
        //$("#summernote").summernote('insertImage',"http://localhost:8080/springmvc-hibernate-test/resources/upload/14625042804136cd844catw1eo1bllexxyj20q70enn1l.jpg")                  
         $.ajaxFileUpload({  
           url: "http://localhost:8080/personWebsite/upload/fileUpload",  
           secureuri:false,  
           fileElementId:'uploadfile',//file标签的id  
           dataType: 'text',//返回数据的类型  
            success: function (data, status) {  
                //把图片替换     
                 var  imgUrl=data.split(">")[1];
                  imgUrl=imgUrl.split("<")[0];		        	          
		          alert(imgUrl);
		         $("#summernote").summernote('insertImage', "resources/upload/"+imgUrl);
	            
            },  
            error: function (data, status, e) {  
              
            }  
        });  
                   
        }
        $("#submitsummernote").on("click",function(){
         alert($('#summernote').summernote('code'));
               alert($('#summernote').val());
        });
});
</script>	
</body>
</html>