/**
 * Created by Administrator on 16-4-16.
 */
$(function(){
    function IndexFun(){
        this.startDate;
        this.categoryId=1;
        this.endDate=new Date();
        this.timeLine = $("#timeLine ul"); //进度条
        this.getTopTag();
        this.initHeader();
        this.autoCompelete();
        this.getgetArticleListMinTime();
        this.initArticelLoad();
    }
    
    var fn=IndexFun.prototype;
	fn.initHeader=function(){
		var self=this;
		if($("#loginStatus").text()=="null"){
			$("#li-bar-more").remove();
		}
		$("#exitLogin").on("click",function(){
		   var form=document.forms[0];
			  form.submit();
		});
		$("#headbar ul li.choose").on("click",function(){
			  self.categoryId=$(this).data("id");
			console.log("categoryId"+self.categoryId);
			self.initArticelLoad();
			$("#timeLine>ul>li").removeClass("block").addClass("none");
		    $("#headbar ul li:not(#bar-more)").removeClass("active");
		    $(this).addClass("active");
		    return false;
		});
	    $("#bar-more").on("click",function(){
	        $("#bar-more").parent("li").addClass("on");
	        $("#bar-more").siblings("div").addClass("a-w-sel-do");
	          return false;
	    });
	    $(document).on("click",function(){
	        $("#bar-more").parent("li").removeClass("on");
	        $("#bar-more").siblings("div").removeClass("a-w-sel-do");
	  	    $("#autoCompelete").removeClass("a-w-sel-do");	
	    });
	    $("#keyWord").on("click",function(){
	  	   return false;
	    });
	 }

    fn.initTimeLine=function(){
        var self=this;
        var startMonth=self.startDate.getMonth()+1;
        var startYear=self.startDate.getFullYear();
        var endMonth=self.endDate.getMonth()+1;
        var endYear=self.endDate.getFullYear();
        var $ul,$li;
        $('<li class="none"> <a href="javascript:void(0)" class="year"><em>最近</em></a></li>').appendTo(self.timeLine);
        if((endMonth==startMonth)&&(startYear==endYear)){}
        else{    
	        for(var i=endYear;i>=startYear;i--){
	            $li=$('<li class="none">  </li>');
	            $year=$('<a href="javascript:void(0)"  class="year"><em>'+i+'</em></a>');
	            $month_ul=$('<ul class="month"></ul>');
	            if(startYear==endYear){
	                for(var j=endMonth ;j>=startMonth;j--){
	                    $month_li=$('<li><a href="javascript:void(0)" class="month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>'+j+'月</span></a></li>')
	                    $month_li.appendTo($month_ul);
	                }
	                $li.append($year,$month_ul).appendTo(self.timeLine);
	               // $('<li class="none"><a href="javascript:void(0)"  class="year"><em>最早一篇文章</em></a></li>').appendTo(self.timeLine);
	                break;
	            }    
	
	            if(i==startYear){
	                for(var j=12;j>=startMonth;j--){
	                    $month_li=$('<li><a href="javascript:void(0)" class="month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>'+j+'月</span></a></li>')
	                    $month_li.appendTo($month_ul);
	                }
	            }
	            else if(i==endYear){
	                for(var j=endMonth;j>=1;j--){
	                    $month_li=$('<li><a href="javascript:void(0)" class="month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>'+j+'月</span></a></li>')
	                    $month_li.appendTo($month_ul);
	                }
	            }
	            else{
	                for(var j=12;j>=1;j--){
	                    $month_li=$('<li><a href="javascript:void(0)" class="month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>'+j+'月</span></a></li>')
	                    $month_li.appendTo($month_ul);
	                }
	            }
	            $li.append($year,$month_ul).appendTo(self.timeLine);
	        }
        }
        $('<li class="none"><a href=""  class="year"><em>最早一篇文章</em></a></li>').appendTo(self.timeLine);
        $("#timeLine>ul>li>a").on("click",function(){
            $(this).parents("ul.timeLine").find("li.block").removeClass("block").addClass("none");
            $(this).parent("li").removeClass("none").addClass("block");
            $(this).siblings("ul").find("li a:eq(0)").addClass("select-month");
            var year=$(this).find("em").text();
            var month= $(this).siblings("ul").find("li a:eq(0)").find("span").text().split("月")[0];
            console.log("year"+year+"month"+month);
            self.ArticelLoadByDate(year,month);
            return false;
        });
        $("#timeLine ul ul.month>li>a").on("click",function(){
            $(this).parents("ul.month").find("a").removeClass("select-month");
            $(this).addClass("select-month");
            var year= $(this).parents("ul").siblings("a").find("em").text();
            var month=  $(this).find("span").text().split("月")[0];
            console.log("year"+year+"month"+month);
            self.ArticelLoadByDate(year,month);
            return false;
        })
    }
  
   
     
     
fn.initArticelLoad=function (){
	var self=this;
     $.ajax({
 	    url : "http://localhost:8080/personWebsite/arcticle/getArticleList",
 	    type : "POST", 
 	    dataType:"json",
 	    contentType:"application/json",    
 	    data:JSON.stringify({artilceId:1,categoryId:self.categoryId}),
 	    success : function(data) {    	 
 	       $("#article").empty();
 	        if(data.length==0){
 	        	$("#article").append($('<p style=" text-align: center;margin-top: 20px;">没有内容</p>'));
	    	 }
	    	 else{
	    	     for(var i=0;i<data.length;i++){
	    	 	   self.newOneList(data[i].Article,data[i].tagList)
	    	 	 }	
	    	 }
 	               
 	    },
 	    error:function(XMLHttpRequest, textStatus, errorThrown) {    
             alert(XMLHttpRequest.status);   // 200                    
         }    
    });
     
 }
  
fn.ArticelLoadByDate=function (year,month){
	var self=this;
	  $.ajax({
	 	    url : "http://localhost:8080/personWebsite/arcticle/getArticleByDate",
	 	    type : "POST", 
	 	    dataType:"json",
	 	    contentType:"application/json",    
	 	    data:JSON.stringify({artilceId:1,year:year,month:month,categoryId:self.categoryId}),
	 	    success : function(data) {  
	 	    	debugger
	 	       $("#article").empty();
	 	      for(var i=0;i<data.length;i++){
	 	    	 self.newOneList(data[i].Article,data[i].tagList)
	 	      }	               
	 	    },
	 	    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	             alert(XMLHttpRequest.status);   // 200                    
	         }    
	    });
	     
}
fn.ArticelLoadByTag=function (tagId){
	var self=this;
	  $.ajax({
	 	    url : "http://localhost:8080/personWebsite/arcticle/getArticleByTag",
	 	    type : "POST", 
	 	    dataType:"json",
	 	    contentType:"application/json",    
	 	    data:JSON.stringify({tagId:tagId,categoryId:self.categoryId}),
	 	    success : function(data) {  
	 	    	debugger
	 	       $("#article").empty();
	 	      for(var i=0;i<data.length;i++){
	 	    	 self.newOneList(data[i].Article,data[i].tagList)
	 	      }	               
	 	    },
	 	    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	             alert(XMLHttpRequest.status);   // 200                    
	         }    
	    });
	     
	 }
  fn.newOneList=function(dataItem,taglist){ 
     $mlist= $('<div class="m-list"></div>');
     $listTop= $('<div class="list-top"> <a class="list-herf" href="detail.jsp?articleId='+dataItem.articleId+'" title="查看全文"></a></div>');
     $listMiddle= $('<div class="list-middle"> </div>');
     $listContent=$('<div class="list-content"></div>');
     $contentBody=$('<div class="content-body"></div>');
     $tiitel= $('<h3 class="title">'+ dataItem.articleTitle+'</h3> ');
     var myDate=new Date(dataItem.writenTime)
    var artiDate= myDate.getFullYear()+'-'+(myDate.getMonth()+1)+'-'+myDate.getDate();
     $time= $('<p class="writenTime">'+artiDate+'</p> ');
     $content=$('<div class="content">'+dataItem.content+'</div>')
     $more=$(' <div class="more"><a href="javascript:void(0)" class="list-more-open">展开</a> </div> ');
     if(dataItem.content.length<5000){$contentBody.append($tiitel,$time,$content);}
     else{	$contentBody.append($tiitel,$time,$content,$more);}       
     $contentOptions=$('<div class="content-options"></div> ');
     $contentTags=$('<div class="content-tags"></div>');
     for(var i=0;i<taglist.length;i++){
          $item= $('<span><a href="javascript:void(0)">'+taglist[i]+'</a></span>');
          $contentTags.append($item);
     }
     $contentComments =$('<div class="content-comments"> <span>评论('+dataItem.commentsNum+')</span> </div>');//<span>热度('+dataItem.hits+')</span>

     $contentOptions.append($contentTags,$contentComments);

     $listContent.append($contentBody,$contentOptions);
     $listMiddle.append( $listContent);
     $mlist.append($listTop,$listMiddle);
     $("#article").append($mlist);

 }
 fn.autoCompelete=function(){
	 var self=this;
	$("#autoCompelete").on("click",".seli",function(){
		var id=$(this).find("a").data("id");
		console.log(id);
		self.ArticelLoadByTag(id);
	});
    $("#keyWord").keydown(function(event){
        if(event.which==13){
          var keyWord=$("#keyWord").val();
      	  $.ajax({
  		    url : "http://localhost:8080/personWebsite/arcticle/searchTag",
  		    type : "POST", 
  		    dataType:"json",
  		    contentType:"application/json",    
  		    data:JSON.stringify({key:keyWord}),
  		    success : function(data) {
  		    	$("#autoCompelete .addli").empty();
  		    	if(!$("#autoCompelete").hasClass("a-w-sel-do")){
  		          $("#autoCompelete").addClass("a-w-sel-do");	
  		    	}
  		
  		        for(var i=0;i<data.TagList.length;i++){  		        		         
  		  		     $a= $('<a class="tagli" data-id="'+data.TagList[i].tagId+'" href="javascript:void(0)">'+data.TagList[i].tagName+'<span>('+data.TagList[i].tagNum+')</span></a>');
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
 fn.getgetArticleListMinTime=function(){
	 var self=this;
	  $.ajax({
		    url : "http://localhost:8080/personWebsite/arcticle/getArticleListMinTime",
		    type : "POST", 
		    success : function(data) {
		    	if(data){
			    	self.startDate=new Date(data);
			    	self.initTimeLine();
		    	}	    	 
		    },   
		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	            alert(XMLHttpRequest.status);   // 200                    
	        }    
     });
           
 }
 fn.getTopTag=function(){
	 var self=this;
	 $(".g-menu").on("click","li:not(.userInfo)",function(){
		 var id=$(this).data("id");
		 console.log(id);
		 self.ArticelLoadByTag(id);
	});
	  $.ajax({
		    url : "http://localhost:8080/personWebsite/arcticle/getTopFourTag",
		    type : "POST", 
		    dataType:"json",
		    contentType:"application/json",  
		    success : function(data) {	
		         for(var i=0;i<data.length;i++){ 		
		 		     $li=$('<li data-id="'+data[i].tagId+'"><a href="javascript:void(0)"> <span class="txt">'+data[i].tagName+'</span> <span class="info">'+data[i].tagNum+'</span> </a></li>')
		             $li.appendTo($(".g-menu ul"));
		 		  }		    	 
		    },   
		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	            alert(XMLHttpRequest.status);   // 200                    
	        }    
     });
	  $.ajax({
		    url : "http://localhost:8080/personWebsite/user/getUserInfo",
		    type : "POST", 
		    dataType:"json",
		    contentType:"application/json",  
		    success : function(data) {			    	 
			      $(".userInfo .userEmail").text(data.email);
			      $(".userInfo .userName").text(data.userName);		       
		    },   
		    error:function(XMLHttpRequest, textStatus, errorThrown) {    
	            alert(XMLHttpRequest.status);   // 200                    
	        }    
   });      
 }
 new IndexFun();
});

 
 
	 

 