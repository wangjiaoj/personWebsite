<%@ page language="java" contentType="text/html; charset=UTF-8"  
pageEncoding="UTF-8"%>  
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>记录生活</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="resources/css/lib/reset.css"/>
       <link rel="stylesheet" href="resources/css/bootstrap.css" />
    <link rel="stylesheet" href="resources/css/index.css"/>
    <style>

    </style>
</head>
<body>
<span id="loginStatus" style="display:none"><%=session.getAttribute("loginStatus")%></span>
 
    <div class="g-hd">
        <div id="headbar">
            <div class="m-nav">
                <ul>
                    <li class="choose"><a href="">首页</a></li>
                    <li class="choose active" data-id="1"><a href="">前端技术</a></li>
                    <li class="choose" data-id="2"><a href="">生活随笔</a></li>
                    
                    <li id="li-bar-more">
                        <a id="bar-more" href="">更多 <span class="more">&nbsp;&nbsp;&nbsp;</span></a>
                        <div class="a-w-sel a-w-sel-4 tbtag ">
                            <div class="w-sel w-sel-4" style="margin-top: 0px;">
                                <div class="selc">
                                    <div class="selcc tbtag">
                                        <div class="seli"><a class="nx-1" href="/personWebsite/modifyInfo.jsp"  target="view_window">帐号设置</a></div>
                                        <div class="seli"><a class="nx-2" href="/personWebsite/edit.jsp"  target="view_window">添加文章</a></div>
                                        <div class="seli" id="exitLogin">
                                        <form medthod="post" action="user/exitlogin">
                                           <a class="nx-3" href="javascript:void(0)">退出</a>
                                        </form>	
                                             
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <div  class="nav2" id="searchbox">
                    <div >
                        <a href="" class="btn"></a>
                        <input type="text" id="keyWord" placeholder="搜索标签"/>
                    </div>
                     
                        <div class="a-w-sel a-w-sel-4 tbtag" id="autoCompelete">
                            <div class="w-sel w-sel-4" style="margin-top: 0px;">
                                <div class="selc">
                                    <div class="selcc tbtag addli">
                                        <div class="seli"><a class=" " href="">标签</a></div>                                       
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                 
            </div>

        </div>
    </div>
    <div class="g-bd">
        <div class="g-bdc">
            <div class="g-sd" id="lside">
                <div class="g-box">
                    <div class="g-menu">
                        <ul>
                            <li class="userInfo"><a href="javascript:void(0)">
                                <span class="txt userName" >王娇娇</span>
                                <span class="info userEmail">2386467485@qq.com</span>
                            </a></li>           
                        </ul>
                    </div>
                </div>
            </div>
            <div class="g-mn" id="main">
              <div  id="article">
                <div class="m-list">
                    <div class="list-top">
                        <a  class="list-herf" href="" title="查看全文"></a>
                    </div>
                    <div class="list-middle">
                        <div class="list-content">
                            <div class="content-body">
                                <h2 class="title">好一座危楼谁是主人谁是客</h2>
                                <p>2016.04.19</p>
                                <div class="content">
                                    <p>
                                        "好一座危楼谁是主人谁是客，只三间老屋，时宜明月时宜风"---------出自《天下第一楼》。天下第一楼，剧描写了创业于清代同治年间、传至民国初年的老字号烤鸭店“福聚德”由入不敷出、势如累卵到东山再起、名噪京华而又面临倒闭的曲折发展历程；
                                        歌颂了卢孟实、玉雏姑娘、罗大头、常贵等人的聪明才智、事业心与实干精神；控诉、批判了游手好闲的败家子习气和黑暗腐朽的社会势力
                                    </p>
                                </div>
                                <div class="more">
                                    <a href="" class="list-more-open">展开</a>
                                </div>
                            </div>

                            <div class="content-options">
                                <div class="content-tags">
                                    <span><a href="">天下第一楼</a></span>
                                    <span><a href="">话剧</a></span>
                                    <span><a href="">何冀平</a></span>
                                </div>
                                <div class="content-comments">
                                    <span>评论(3)</span>
                                    <span>热度(23)</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="m-list">
                    <div class="list-top">
                        <a  class="list-herf" href="" title="查看全文"></a>
                    </div>
                    <div class="list-middle">
                        <div class="list-content">
                            <div class="content-body">
                                <h2 class="title">只三间老屋，时宜明月时宜风</h2>
                                <p>2016.04.19</p>
                                <div class="content">
                                    <p>
                                        "好一座危楼谁是主人谁是客，只三间老屋，时宜明月时宜风"---------出自《天下第一楼》。天下第一楼，该剧描写了创业于清代同治年间、传至民国初年的老字号烤鸭店“福聚德”由入不敷出、势如累卵到东山再起、名噪京华而又面临倒闭的曲折发展历程； 歌颂了卢孟实、玉雏姑娘、罗大头、常贵等人的聪明才智、事业心与实干精神；控诉、批判了游手好闲的败家子习气和黑暗腐朽的社会势力
                                    </p>
                                </div>
                                <div class="more">
                                    <a href="" class="list-more-open">展开</a>
                                </div>
                            </div>

                            <div class="content-options">
                                <div class="content-tags">
                                    <span><a href="">天下第一楼</a></span>
                                    <span><a href="">何冀平</a></span>
                                    <span><a href="">话剧</a></span>
                                </div>
                                <div class="content-comments">
                                    <span>评论(3)</span>
                                    <span>热度(23)</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
              </div>
              <!-- --------
              <div id="pagination">
                    <a href="" class="page prv"></a>
                    <div class="num c1">2&nbsp;/&nbsp;22</div>
                    <a href="" class="page nxt"></a>
                </div>
                ------- -->
            </div>
             <div class="g-sd" id="rside">
                 <div class="g-timeLine" id="timeLine">
                     <ul class="timeLine">
                         <!----
                         <li class="none">
                             <a href="" class="year"><em>最近</em></a>
                         </li>
                         <li class="block">
                             <a href=""  class="year"><em>2016</em></a>
                             <ul class="month"><li>
                                 <a href="" class="slect-month month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>5月</span></a>
                             </li>
                              <li>
                                 <a href="" class="month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>4月</span></a>
                             </li>
                             <li>
                                 <a href="" class="month"><em class="time-bor1"></em><em class="time-dot"></em><em class="time-bor2"></em><span>3月</span></a>
                             </li></ul>
                         </li>
                         <li class="none"><a href=""  class="year"><em>2015</em></a></li>
                         <li class="none"><a href=""  class="year"><em>最早一篇文章</em></a></li>
                         --->
                     </ul>
                 </div>
             </div>
        </div>
    </div>
    <script src="resources/js/lib/jquery-2.1.4/jquery.js"></script>
     <script type="text/javascript" src="resources/js/bootstrap.js"></script>
    <script src="resources/js/index.js"></script>
</body>
</html>