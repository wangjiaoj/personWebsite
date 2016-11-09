 $(function(){
        $("#summernote").summernote({
            height:320,
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
           var content= $('#summernote').summernote('code');
           var articleTitle=$("#articleTitle").val();
           var tagNameList=[];
           $(".content-tags span a").each(function(i,item){
        	  var tagContent=$(item).html() ;
        	  var tagName=tagContent.split("#")[1];
        	  tagNameList.push(tagName);
        	});
          var categoryId=$("#categoryId").val();
           if(window.location.search){
        	  var articleId=window.location.search.split("=")[1];
        	  var data=JSON.stringify({categoryId:categoryId,articleId:articleId,content:content,articleTitle:articleTitle,tagNameList:tagNameList});
              var url="http://localhost:8080/personWebsite/arcticle/editArticle";
           }
           else{
        	  var data=JSON.stringify({categoryId:categoryId,content:content,articleTitle:articleTitle,tagNameList:tagNameList});
              var url="http://localhost:8080/personWebsite/arcticle/addArticle";
           }
           var loginStatus=$("#loginStatus").text();
    	   if(loginStatus!="null"){
    	   
    	  
	        $.ajax({
	       	    url : url,
	       	    type : "POST", 
	       	    //dataType:"json",
	       	    contentType:"application/json",    
	       	    data:data,
	       	    success : function(data) {
	       	        alert(data);   
	       	        var articleId= data.split(":")[1];
	                   window.location="http://localhost:8080/personWebsite/detail.jsp?articleId="+articleId;       	        
	       	    },
	       	    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	                   alert(XMLHttpRequest.status);   // 200                    
	               }    
	          });
    	   }
        });
      
    
 
        $(".article-tags").on("click",function(){
            $(this).find("input").focus()
        });
        //标签事件
        $("#tag-input").keydown(function(event){
            if(event.which==13){
              var  $tag=  $('<span><a href="">#'+ $("#tag-input").val()+'</a></span>')
                $(".content-tags").append($tag);
                $("#tag-input").val("");
            }
        });
        
        
   if(window.location.search){
	   var articleId=window.location.search.split("=")[1];
	   var loginStatus=$("#loginStatus").text();
	   if(loginStatus!="null"){				
        $.ajax({
		    url : "http://localhost:8080/personWebsite/arcticle/getArticle",
		    type : "POST", 
		    dataType:"json",
		    contentType:"application/json",    
		    data:JSON.stringify({articleId:articleId}),
		    success : function(data) {
		    	debugger
		      $('#summernote').summernote('code',data.article.content);
		        $("#articleTitle").val(data.article.articleTitle);
		           
		       
		    	$(".content-tags").empty();
		        for(var i=0;i<data.tagList.length;i++){
		           $tag=$("<span><a href=''>#"+data.tagList[i]+"</a></span>");
		           $tag.appendTo($(".content-tags"));
		        }	   
		        
		    },
		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	            alert(XMLHttpRequest.status);   // 200                    
	        }    
      });
	   }
   }     
    });