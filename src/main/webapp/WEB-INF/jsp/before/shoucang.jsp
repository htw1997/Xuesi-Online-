<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
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
    <br>
    <menu>
        <div class="container clearfix">
            <ul class="clearfix f_left">
                <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/index.jsp">课程</a></li>
                <li><a href="${pageContext.request.contextPath}/user/about">关于我们</a></li>
                <li class="menu_active"><a href="${pageContext.request.contextPath}/user/showMyProfile">个人中心</a></li>
                <li class="menu_active"><a href="${pageContext.request.contextPath}/shoucang/showFavourite">我的收藏</a>
                </li>
            </ul>
        </div>
    </menu>
</header>
<br>
<main>

    <div id="app">

        <!--面包屑导航-->
        <div class="classify">
            <div class="container">
                <h2>我的收藏</h2>
                <div class="section">
                    <ul>
                        <!--收藏-->
                        <c:forEach items="${shoucangList}" var="shoucang" varStatus="i">

                            <li class="section-main" onclick="getVideo(${shoucang.videoid})">
                                <div class="thum" style="background-image: url('../${shoucang.coverPath}')">
                                    <!--http://vod.chengjian100.com/gkk/h5/c1/image/course/01.jpg-->
                                </div>
                                <p>
                                    <c:if test="${(i.index+1)>=10}">
                                        ${i.index+1}
                                    </c:if>
                                    <c:if test="${(i.index+1)<10}">
                                        0${i.index+1}
                                    </c:if> ${shoucang.title}</p>
                                <div class="classify-v-info">
                                <span class="count" title="观看次数"><img
                                        src="${pageContext.request.contextPath}/img/count.png"
                                        alt="">${shoucang.playNum}</span>
                                    <span class="duration" title="视频时长"><img
                                            src="${pageContext.request.contextPath}/img/player.png"
                                            alt="">${shoucang.videoSeconds}</span>
                                </div>
                            </li>

                        </c:forEach>

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

<script type="text/javascript">

    function getVideo(videoId) {
        //alert("${sessionScope.userAccount}");
        //判断用户是否登录
        //alert($("#isLogin").val());
        if ((null != "${sessionScope.userAccount}" && "${sessionScope.userAccount}" != "") || ($("#isLogin").val() == 1)) {
            //如果登录
            location.href = "${pageContext.request.contextPath}/video/showVideo?videoId=" + videoId + "&subjectName=" + '${subject.subjectName}';
        } else {
            //如果不登录，弹登录框

            $('#login_open').click(function () {
                $('#login').removeClass('hidden');
            });
        }


    }
</script>
</body>
</html>