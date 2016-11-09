$(function(){
	$(".modify-bar ul li").click("click",function(){
	    $(this).siblings().removeClass("on");
	    $(this).addClass("on");
	    $(".modify-content>div").removeClass("on");
	    if($(this).hasClass("modifyInfo")){
	        $("#modifyInfo").addClass("on");
	    }
	    else if($(this).hasClass("modifyPhoto")){
	        $("#modifyPhoto").addClass("on");
	    }
	    else if($(this).hasClass("modifyEmail")){
	        $("#modifyEmail").addClass("on");
	    }
	    else{
	        $("#modifyPwd").addClass("on");
	    }
	    return false;
   });
	 $("#modifyInfoSub").on("click",function(){
         var formData=$("#modifyInfo form").serialize();
        debugger
        $.ajax({      
          url: "http://localhost:8080/personWebsite/user/uploadUserInfo",
          type : "POST", 
          data: formData,//返回数据的类型  JSON.stringify({userName:userName,introduce:introduce})
          success: function (data, status) {
        	  debugger
          if(data=="true"){
        	  alert("账户名和简介已修改");
          }
          else{
            alert("账户名或密码错误");
          }  
             
         },  
         error: function (data, status, e) {  
             
         }  
      });  
      
      });
	 $("#modifyPwdSub").on("click",function(){
         var newPwd= $("#newPwd").val();
		 var pwdaga=$("#newPwdaga").val();
		 if(pwdaga!=newPwd){
			 alert("新密码与确认输入不一致");
			 return false;
		 }
         var formData=$("#modifyPwd form").serialize();
         debugger
        $.ajax({      
          url: "http://localhost:8080/personWebsite/user/uploadUserPwd",                          
          data: formData,//返回数据的类型  
          success: function (data, status) {
        	  debugger
          if(data=="true"){
        	  alert("密码修改成功");
          }
          else{
            alert("账户名或密码错误");
          }  
             
         },  
         error: function (data, status, e) {  
             
         }  
      });  
      
      });
	 $("#modifyEmailSub").on("click",function(){
        // var formData="email="+ $("#email").val();
         var formData=$("#modifyEmail form").serialize();
          debugger
        //var formData=$("#login-form").serialize();
	      $.ajax({      
	          url: "http://localhost:8080/personWebsite/user/uploadMail",                          
	          data: formData,//返回数据的类型  
	          success: function (data, status) {
	        	  debugger
	          if(data=="true"){
	        	  alert("邮箱修改成功");
	          }
	          else{
	            alert("账户名或密码错误");
	          }  
	             
	         },  
	         error: function (data, status, e) {  
	             
	         }  
	      });  
      
      });
	$("#changePhotoSpan").on("click",function(){
	     $.ajaxFileUpload({
	         url: "http://localhost:8080/personWebsite/user/uploadPhoto",
	         secureuri:false,
	         fileElementId:'uploadfile',//file标签的id
	         dataType: 'text',//返回数据的类型
	         success: function (data, status) {
	             //把图片替换
	        	 debugger
	             var  imgUrl=data.split(">")[1];
	             imgUrl=imgUrl.split("<")[0];
	             alert("resources/upload/"+imgUrl);
	            
	              $("#modifyPhoto img").attr("src","resources/upload/"+imgUrl);
	         },
	         error: function (data, status, e) {
	
	         }
	     });
	});

});