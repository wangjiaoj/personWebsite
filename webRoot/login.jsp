<%@ page language="java" contentType="text/html; charset=UTF-8"  
pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="resources/css/lib/reset.css"/>
    <link rel="stylesheet" href="resources/css/login.css"/>
</head>
<body>
  <div class="content-body">
      <div class="background">
           <img src="resources/imgs/background.jpg" alt=""/>
      </div>
      <div class="login-region">
           <div class="user-link">
                <a class="login-link active"  href="">登录</a>
                <a class="pwd-link"   href="">找回密码</a>
           </div>
           <div class="login-div">
               <div id="login" class="active">
                   
                   <div><input id="userName" placeholder="请输入用户名" type="text" style="margin-top:30px"/></div>
                   <div> <input id="pwd" placeholder="请输入密码" type="password"/></div>
                   
                  <div> <button id="loginBtn">登录</button>  </div>
               </div>
               <div id="pwd-find">
                   <div><input  placeholder="请输入邮箱" type="text" style="margin-top:30px"/></div>
                   <div> <button>找回密码</button>  </div>
               </div>
          </div>
      </div>
  </div>
<script src="resources/js/lib/jquery-2.1.4/jquery.js"></script>
  <script>
      $(function(){
          $(".user-link>a").on("click",function(){
              $(this).siblings().removeClass("active");
              $(this).addClass("active");
              $("#login,#pwd-find").removeClass("active");
              if($(this).hasClass("login-link")){
                  $("#login").addClass("active");
              }
              else{
                  $("#pwd-find").addClass("active");
              }
              return false;
          });
          $("#loginBtn").on("click",function(){
             var formData="userName="+ $("#userName").val()+"&pwd="+$("#pwd").val();
          
            //var formData=$("#login-form").serialize();
	         $.ajax({      
	              url: "http://localhost:8080/personWebsite/user/login",                          
	              data: formData,//返回数据的类型  
	              type : "POST", 
	              success: function (data, status) {
	              if(data=="true"){
	                  window.location="http://localhost:8080/personWebsite/index.jsp";
	              }
	              else{
	                alert("账户名或密码错误");
	              }  
	                 
	             },  
	             error: function (data, status, e) {  
	                 
	             }  
	          });  
          
          });
      });
  </script>
</body>
</html>