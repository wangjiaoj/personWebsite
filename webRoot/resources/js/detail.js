/**
 * Created by Administrator on 16-4-21.
 */
$(function(){
	  function articleDetail(){
	         this.initlimit();
	         this.initAutoCompelete();
	         this.initCommentOperation();
	       this.initUserInfo();
	  }
	  var fn=articleDetail.prototype;
	  
	  fn.initlimit=function(){
		  var  self=this;
			if($("#loginStatus").text()=="null"){
				$("#deleteArticle").parent("li").remove();
				$("#editArticle").parent("li").remove();
			}
			if(window.location.search){
				var articleId= window.location.search.split("=")[1];			
				self.arcicleLoad(articleId);
			}
			$("#editArticle").on("click",function(){
				window.location="http://localhost:8080/personWebsite/edit.jsp"+window.location.search;
			});
			$("#deleteArticle").on("click",function(){
				if(confirm("确定要删除该文章吗？")){				         
				}
				else{
					 return false;
				}
				 
				 if(window.location.search){
					 var articleId=window.location.search.split("=")[1];
			      	  $.ajax({
				  		    url : "http://localhost:8080/personWebsite/arcticle/deleteArticleByArticleId",
				  		    type : "POST", 
				  		  dataType:"json",
				  		    contentType:"application/json",    
				  		    data:JSON.stringify({articleId:articleId}),
				  		    success : function(data) {
				  		    	debugger
				  		       alert(data.result);			  		    	
				  		        window.location="http://localhost:8080/personWebsite/index.jsp";
				  		    },
				  		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
				  	             debugger
				  		    	alert(XMLHttpRequest.status);   // 200                    
				  	        }    
				         });
				 };
			});
	  }
	  
	 fn.initAutoCompelete=function(){
		 var self=this;
		  $(document).on("click",function(){	        
		  	  $("#autoCompelete").removeClass("a-w-sel-do");	
		  });
		 $("#autoCompelete").on("click",function(){	  
		  	 return false; 
		  });
		 $("#autoCompelete").on("click",".seli",function(){
			 var id=$(this).find("a").data("id");
			 console.log("id"+id);
			 self.arcicleLoad(id);		 
		 });
		$("#keyWord").keydown(function(event){
	        if(event.which==13){
	          var keyWord=$("#keyWord").val();
	      	  $.ajax({
	  		    url : "http://localhost:8080/personWebsite/arcticle/searchArticle",
	  		    type : "POST", 
	  		    dataType:"json",
	  		    contentType:"application/json",    
	  		    data:JSON.stringify({key:keyWord}),
	  		    success : function(data) {	      
	  		        $("#autoCompelete .addli").empty();
	  		    	if(!$("#autoCompelete").hasClass("a-w-sel-do")){
	  		          $("#autoCompelete").addClass("a-w-sel-do");	
	  		    	}
	  		
	  		        for(var i=0;i<data.articleInfoList.length;i++){  		        		         
	  		  		     $a= $('<a class="tagli" data-id="'+data.articleInfoList[i][0]+'" href="javascript:void(0)">'+data.articleInfoList[i][1]+'</a>');
	  		  		     $seli= $(' <div class="seli"></div>');
	  		  		     $seli.append($a).appendTo($("#autoCompelete .addli"))  ;
	  		        }
	  		    
	  		    },
	  		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	  	            alert(XMLHttpRequest.status);   // 200                    
	  	        }    
	         });
	             
	        }
	    });	
	  }
   
	 fn.initCommentOperation=function(){
		 var self=this;
			$("#commentsList").on("click",".replayOperation",function(){
			    var repalyName=	$(this).parents(".commmentInfo").find(".theCommentName").text();
				$("#replayName").html('回复<a id="CommentreplayName"">'+repalyName+'</a> <span id="replayInfo" style="float: right;cursor: pointer;">取消</span>');     
			});
			$("#replayName").on("click","#replayInfo",function(){
				$("#replayName").empty();
			});
			$("#commentsList ul").on("click",".deleteOperation",function(){
			      var commentId=$(this).parents(".commmentInfo").attr("data-comment");
			  	  var articleId= window.location.search.split("=")[1];	
				  $.ajax({
					    url : "http://localhost:8080/personWebsite/comment/delComment",
					    type : "POST", 			    
					    contentType:"application/json",    
					    data:JSON.stringify({commentId:commentId,articleId:articleId}),
					    success : function(data) {
					       debugger
					       var result=data.split(":")[1];
					       if(result=="true"){
					    	   $(".commmentInfo").each(function(){
					    		   if($(this).attr("data-comment")==commentId){$(this).remove(); }		    				   		    	   
					    	   });
					       }	        
					    },
					    error:function(XMLHttpRequest, textStatus, errorThrown) {    
				            alert(XMLHttpRequest.status);   // 200                    
				        }    
			    });
		    });
			$("#commentSubit").on("click",function(){
				var content=$("#commentContent").val();
				var mail=$("#commentMail").val();
				var reviewerName=$("#reviewerName").val();
				var replayName=$("#CommentreplayName").text();
				var articleId= window.location.search.split("=")[1];			
				  $.ajax({
					    url : "http://localhost:8080/personWebsite/comment/addComment",
					    type : "POST", 			 
					    contentType:"application/json",    
					    data:JSON.stringify({content:content,mail:mail,reviewerName:reviewerName,replayName:replayName,articleId:articleId}),
					    success : function(data) {
					       debugger
					       var id=data.split(":")[1]
					       
					       self.NewComment(id,content,reviewerName,replayName);
					    },
					    error:function(XMLHttpRequest, textStatus, errorThrown) {    
				            alert(XMLHttpRequest.status);   // 200                    
				        }    
			      });
				
			});
			 
	 }
	 
	 fn.NewComment=function(id,content,reviewerName,replayName){
		  $li=$('<li></li>');
	        $commmentInfo=$('<div class="commmentInfo" data-comment="'+id+'"> </div>');
	        $detail=$('<div class="detailComment"> </div>');
	        $theCommentName=$('<span class="theCommentName"><a href="#">'+reviewerName+'</a></span>');
	        $replayFlag=$('<span class="replayFlag">回复</span>');
	        $replay=$('<span> <a href="#">'+replayName+'</a></span>');
	        $content=$('<span>:'+content+'</span>');
	        if(replayName){$detail.append($theCommentName,$replayFlag,$replay,$content);}
	        else{$detail.append($theCommentName,$content);}
	        
	        $operation=$('<div class="commentOperation"><sapn class="deleteOperation" data-commentid=""><a href="#">删除</a></sapn>'+
	        ' <span class="replayOperation"><a href="#">回复</a></span> </div>');
	        $commmentInfo.append($detail,$operation);
	        $li.append($commmentInfo).appendTo($("#commentsList ul"));
	 }
	 
	 fn.arcicleLoad=function(articleId){
		 var self=this;
		 $.ajax({
			    url : "http://localhost:8080/personWebsite/arcticle/getArticle",
			    type : "POST", 
			    dataType:"json",
			    contentType:"application/json",    
			    data:JSON.stringify({articleId:articleId}),
			    success : function(data) {
			        debugger	        	       
			        $(".article-content .main-content").html(data.article.content);
			        $(".article-content h2 a").text(data.article.articleTitle);
			    	$(".info .tags").empty();
			        for(var i=0;i<data.tagList.length;i++){
			           $tag=$("<span><a href=''>#"+data.tagList[i]+"</a></span>");
			           $tag.appendTo($(".info .tags"));
			        }	   
			        $("#CommentNum").text("评论("+ data.article.commentsNum+")") ;
			        $("#commentsList ul").empty();
			        if(data.article.commentsNum>0){		        			        
			        	for(var i=0;i<data.CommentList.length;i++){		        				        				        			        		 
			        		self.NewComment(data.CommentList[i][0],data.CommentList[i][1],data.CommentList[i][3],data.CommentList[i][4]);			        		 
			        	}
			        }
			    },
			    error:function(XMLHttpRequest, textStatus, errorThrown) {    
		            alert(XMLHttpRequest.status);   // 200                    
		        }    
	      });
	}
	 fn.initUserInfo=function(){
		  
		 $.ajax({
			    url : "http://localhost:8080/personWebsite/user/getUserInfo",
			    type : "POST", 
			    dataType:"json",
			    contentType:"application/json",    		   
			    success : function(data) {
			      debugger
			      $(".personPhoto img").attr("src","resources/upload/"+data.photoURL);
			      $("#userName").text(data.userName);
			      $("#introduction").text(data.introduction);		       
			    },
			    error:function(XMLHttpRequest, textStatus, errorThrown) {    
		            alert(XMLHttpRequest.status);   // 200                    
		        }    
	      });
	 }
 new articleDetail();
 
});