<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
<head>
    <title>修改信息</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="resources/css/lib/reset.css"/>
    <link rel="stylesheet" href="resources/css/backHome.css"/>
      <link rel="stylesheet" href="resources/css/modifyInfo.css"/>
      <script src="resources/js/lib/jquery-2.1.4/jquery.js"></script>
  <script src="resources/js/lib/ajaxfileupload.js"></script>
</head>
<body>
<div class="content-body">
    <div class="background"><img src="resources/imgs/background.jpg" alt=""/></div>
    <div class="modify-region">
             <div id="modify-header"><span>修改信息</span></div>
             <div id="modify-body">
                 <div class="modify-bar">
                     <ul>
                         <li class="modifyPhoto ">修改头像</li>
                         <li class="modifyInfo on"> 修改个人信息</li>
                         <li class="modifyPwd">修改密码</li>
                         <li class="modifyEmail">修改邮箱绑定</li>
                     </ul>
                 </div>
                 <div class="modify-content">
                       <div id="modifyPhoto">
                               <img src="resources/imgs/64.png" class="blogsettingztag" style="max-width:80px;">
                                <input id="uploadfile" name="file" type="file"/>
                                
                               <p>支持jpg、gif、png、bmp格式</p>
                               <div> <span id="changePhotoSpan">修改头像</span></div>
                       </div>
                       <div id="modifyInfo" class="on">
                        <form action="" medthod="post">
                          <div>
                               <span>名称</span>
                               <input type="text" name="userName" id="userName"/>
                          </div>
                          <span class="introduceSpan">介绍</span>
                          <textarea name="introduce" id="introduce" name="introduce" cols="30" rows="10"></textarea>
                         </form>
                          <span id="modifyInfoSub" class="submit">确认</span>
                       </div>
                       <div id="modifyPwd">
                           <form action="" medthod="post">
                           <div>
                               <span>原密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                               <input type="password" name="pwd" id="pwd"/>
                           </div>
                           <div>
                               <span>新密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                               <input type="password" name="newPwd" id="newPwd"/>
                           </div>
                            </form>
                           <div>
                               <span>确认新密码</span>
                               <input type="password" id="newPwdaga"/>
                           </div>
                           <div>
                               <span id="modifyPwdSub" class="submit">确认</span>
                           </div>
                       </div>
                       <div id="modifyEmail">           
                           <div>
                               <form action="" medthod="post">
                               <span>新邮箱</span>
                               <input type="text" name="email" id="email"/>
                               </form>
                           </div>
                               <span id="modifyEmailSub" class="submit">确认</span>
                       </div>
                 </div>
             </div>
    </div>
</div>


<div class="control" style="" id="control">
    <ul>
          <li><a id="backHome" href="index.jsp" class="backHome" target="_top"><em>返回首页</em></a></li>         
    </ul>
</div>
    

 <script src="resources/js/modifyInfo.js"></script>
<script>
</script>
</body>
</html>