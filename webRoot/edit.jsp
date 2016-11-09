 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>uploadFile</title>
    <script src="resources/js/lib/jquery-2.1.4/jquery.js"></script>
    <script src="resources/js/lib/ajaxfileupload.js"></script>
    <link rel="stylesheet" href="resources/css/lib/reset.css"/>
    <!-- include libraries BS3 -->
    <link rel="stylesheet" href="resources/css/bootstrap.css" />
    <script type="text/javascript" src="resources/js/bootstrap.js"></script>

    <!-- include summernote -->
    <link rel="stylesheet" href="resources/js/lib/summernote/summernote.css">
    <script type="text/javascript" src="resources/js/lib/summernote/summernote.js"></script>
    <script type="text/javascript" src="resources/js/lib/summernote/summernote-zh-CN.js"></script>
    <link rel="stylesheet" href="resources/css/backHome.css"/>
     <link rel="stylesheet" href="resources/css/edit.css"/>
    <style>     
    </style>
</head>
<body>
<div class="main">
<div class="content-body">
<span id="loginStatus" style="display:none"><%=session.getAttribute("loginStatus")%></span>
    <div class="article-title">
        <input placeholder="请输入标题" id="articleTitle" type="text"/>
    </div>
    <div style="margin-bottom:10px;">
          <label for="name">文章分类选择列表</label>
	      <select class="form-control" id="categoryId">
	         <option value="1">前端技术</option>
	         <option value="2">生活随笔</option>
	          
	      </select>
      </div>
    <div id="summernote"></div>
   <div>
       <div class="article-tags">
           <div class="content-tags"> </div>
           <input maxlength="20" class="" id="tag-input" placeholder="添加相关标签，用回车分隔">
       </div>
   </div>

    <input class="btn btn btn-primary" type="submit" id="submitsummernote" value="提交">
</div>

<div class="control" style="" id="control">
    <ul>
         <li><a id="backHome" href="index.jsp" class="backHome" target="_top"><em>返回首页</em></a></li>
    </ul>
</div>

</div>
 <script type="text/javascript" src="resources/js/edit.js"></script>
<script>
</script>
</body>
</html>