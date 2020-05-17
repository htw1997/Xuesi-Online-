<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="keywords" content="国画|油画|水彩|版画|漫画|抽象画">
    <meta name="description" content="学思网艺术教育,为您提供各种艺术视频以及相关知识等在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,学思网,学习成就梦想！">
    <meta name="author" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.png" type="image/png">
    <title>学思网艺术教育|国画|油画|水彩|版画|漫画|抽象画</title>
</head>

<body>
    <header>
        <div class="container top_bar clearfix">
            <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="学思网">
            <div id="tele">
                <span>183-371-59695</span>
                <span>183-371-59695</span>
            </div>
        </div>
        <menu>
            <div class="container clearfix">
                <ul class="clearfix f_left">
                    <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">课程</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/about">关于我们</a></li>
                    <li class="menu_active"><a href="${pageContext.request.contextPath}/user/showMyProfile">个人中心</a></li>
                    <li class="menu_active"><a href="${pageContext.request.contextPath}/shoucang/showFavourite">我的收藏</a></li>
                </ul>
                <div id="search_group">
                    <input type="text" placeholder="搜索课程">
                    <span id="search"></span>
                </div>
                <div id="user_bar">
                    <a href="user/showMyProfile">
                      <c:if test="${empty user.imgurl}">
                         <img id="avatar" src="../img/avatar_lg.png" alt="">
                      </c:if>
                      
                      <c:if test="${not empty user.imgurl}">
                         <img id="avatar" src="${user.imgurl}" alt="">
                      </c:if>
                       
                    </a>
                    <a href="${pageContext.request.contextPath}/user/loginOut" id="lay_out">退出</a>
                </div>
            </div>
        </menu>
    </header>
    <main>
        <div class="container">
            <h2>我的资料</h2>
            <div id="profile_tab">
                <ul class="profile_tab_header f_left clearfix">
                    <li><a href="${pageContext.request.contextPath}/user/changeProfile">更改资料</a></li>
                    <li class="profile_tab_line">|</li>
                    <li><a href="${pageContext.request.contextPath}/user/changeAvatar">更改头像</a></li>
                    <li class="profile_tab_line">|</li>
                    <li><a href="${pageContext.request.contextPath}/user/passwordSafe">密码安全</a></li>
                </ul>
                <div class="proflle_tab_body">
                    
                    <div class="proflle_tab_workplace clearfix">
                        <div class="profile_avatar_area">
                            <c:if test="${empty user.imgurl}">
		                         <img id="avatar" src="${pageContext.request.contextPath}/img/avatar_lg.png" alt="">
		                      </c:if>
		                      
		                      <c:if test="${not empty user.imgurl}">
		                         <img id="avatar" width="180px" heigth="180px" src="${user.imgurl}" alt="">
		                      </c:if>
                            <p>
                            <c:if test="${not empty user.nickname}">
						          <span>${user.nickname}</span>
						    </c:if>
						    <%-- 
						    <c:if test="${empty user.nickname}">
						          <span>无名氏</span>
						    </c:if> --%>
                                                                          欢迎回来！</p>
                        </div>
                        <ul class="profile_ifo_area">
                            <li><span class="dd">昵&#x3000;称：</span>${user.nickname}</li>
                            <li><span class="dd">性&#x3000;别：</span>
                                <c:if test="${user.sex=='woman'}">
                                    		女
                                </c:if>
                                <c:if test="${user.sex=='man'}">
                                    		男
                                </c:if> 
                             </li>
                            <li><span class="dd">生&#x3000;日：</span><time data="1990-06-06">${user.birthday}</time></li>
                            <li><span class="dd">邮箱号码：</span>${user.email}</li>
                            <li><span class="dd">所在地：</span>${user.address}</li>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <div class="container">
            <ul>
                <li><img src="${pageContext.request.contextPath}/img/xuesi_foot.png" alt="" id="foot_logo"></li>
                <li>版权所有：学思网在线教育&#x3000;&#x3000;&#x3000;&copy;&nbsp;www.xuesi.art</li>
            </ul>
        </div>
    </footer>
</body>
</html>