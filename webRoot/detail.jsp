<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.*" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
 
%>
<!DOCTYPE html>
<html>
<head>
    <title>好一座危楼谁是主人谁是客</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="resources/css/lib/reset.css"/>
 
   <!-- include libraries BS3 -->
    <link rel="stylesheet" href="resources/css/bootstrap.css" />

    <link rel="stylesheet" href="resources/css/detail.css"/>
     <link rel="stylesheet" href="resources/css/backHome.css"/>
</head>
<body>
    <div class="g-doc">
    <span id="loginStatus" style="display:none"><%=session.getAttribute("loginStatus")%></span>
        <div class="g-hd"> </div>
        <div class="g-bd">
            <div class="g-sd">
                <div class="personPhoto">
                    <a href=""><img src="resources/imgs/personphoto.jpg" alt=""/></a>
                </div>
                <h1 id="userName">王娇娇</h1>
                <p id="introduction" style="padding-top:30px">前端小白一枚，邮箱2386467485@qq.com，欢迎勾搭</p>
                 <div class="search">
                     <div class=searchBox><a href="" class="btn"></a><input type="text" id="keyWord" placeholder="请输入关键词"/></div>
                     
                     <div class="a-w-sel a-w-sel-4 tbtag" id="autoCompelete">
                            <div class="w-sel w-sel-4" style="margin-top: 10px;">
                                <div class="selc">
                                    <div class="selcc tbtag addli">
                                        <div class="seli"><a class=" " href="">标签</a></div>                                       
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                 </div>
            </div>
            <div class="g-mn">
                <div class="">
                    <div class="contentBox">
                        <div class="article-content">
                            <h2 class="title"><a href="">天下第一楼</a></h2>
                            <div class="main-content">
                                <p>该剧描写了创业于清代同治年间、传至民国初年的老字号烤鸭店“福聚德”由入不敷出、势如累卵到东山再起、名噪京华而又面临倒闭的曲折发展历程；
                                    歌颂了卢孟实、玉雏姑娘、罗大头、常贵等人的聪明才智、事业心与实干精神；控诉、批判了游手好闲的败家子习气和黑暗腐朽的社会势力</p>
                            </div>
                        </div>
                        <div class="info">
                            <div class="tags">
                                <span><a href="">#天下第一楼</a></span>
                                <span><a href="">#何冀平</a></span>
                                <span><a href="">#话剧</a></span>
                            </div>
                            <div class="articleTime">
                                <a href="">时间：2016-04-18</a>
                            </div>
                        </div>
                    </div>
                    <div class="otherArticle">
                        <a href="" class="nextArticle">下一篇></a>
                    </div>
                    <div class="comments">
                        <div id="makeComments">
                            <div id="CommentNum">评论(2)</div>
                            <div id="replayName"> </div>
                            <div>
                                <textarea id="commentContent"  style="width:80%;height: 100px;"></textarea>
                            </div>
                            <div>邮箱</div>
                            <div>
                                <input id="commentMail" placeholder="请输入邮箱，必须选项"  type="text"/>
                            </div>
                            <div>昵称</div>
                            <div>
                                <input id="reviewerName" placeholder="请输入昵称，必须选项" type="text"/>
                            </div>
                            <div>
                                <button id="commentSubit">提交</button>
                            </div>
                        </div>
                        <div id="commentsList">
                            <ul>
                                <li>
                                    <div class="commmentInfo" data-comment="">
                                        <div class="detailComment">
                                            <span  class="theCommentName"><a href="#">123455</a></span>
                                            <span class="replayFlag">回复</span>
                                            <span> <a href="#">34345565</a></span>
                                            <span>:ssssssss</span>
                                            <span></span>
                                        </div>
                                       <div class="commentOperation">
                                           <sapn class="deleteOperation" data-commentId=""><a href="#">删除</a></sapn>
                                           <span class="replayOperation"><a href="#">回复</a></span>
                                       </div>
                                    </div>
                                </li>

                                <li>
                                    <div class="commmentInfo" data-comment="">
                                        <div class="detailComment">
                                            <span class="theCommentName"><a href="#">123455</a></span>
                                            <span class="replayFlag">回复</span>
                                            <span> <a href="#">34345565</a></span>
                                            <span>:ssssssss</span>
                                            <span></span>
                                        </div>
                                        <div class="commentOperation">
                                            <sapn class="deleteOperation"><a href="#">删除</a></sapn>
                                            <span class="replayOperation"><a href="#">回复</a></span>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                   
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="g-ft">
             <span> © 2386467485 | Powered by LOFTER  </span>
        </div>
    </div>
    <div class="control" style="" id="control">
        <ul>
            <li><a id="backHome" href="index.jsp" class="backHome" target="_top"><em>返回首页</em></a></li>
            <li><a id="editArticle" href="javascript:void(0)" class="editArticle" target="_top"><em></em></a></li><!--编辑-->
            <li><a id="deleteArticle" href="javascript:void(0)" class="deleteArticle" target="_top"><em></em></a></li><!--删除-->
        </ul>
    </div>
      <script src="resources/js/lib/jquery-2.1.4/jquery.js"></script>
      <script type="text/javascript" src="resources/js/detail.js"></script>
     <script type="text/javascript" src="resources/js/bootstrap.js"></script>
</body>
</html>