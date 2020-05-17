<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = new Date();
    String nowDate = sdf.format(date);
%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="国画|油画|水彩|版画|漫画|抽象画">
    <meta name="description" content="学思网艺术教育,为您提供各种艺术视频以及相关知识等在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,学思网,学习成就梦想！">
    <meta name="author" content="">
    <link rel="icon" href="../favicon.png" type="image/png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <link href="${pageContext.request.contextPath}/css/video-js.css" rel="stylesheet" type="text/css">
    <title>学思网艺术教育|国画|油画|水彩|版画|漫画|抽象画</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //div 两个哪个显示呢？
            if (null != "${sessionScope.userAccount}" && "${sessionScope.userAccount}" != "") {
                $("#regBlock").css("display", "none");
                $("#userBlock").css("display", "block");
            } else {
                $("#regBlock").css("display", "block");
                $("#userBlock").css("display", "none");
            }
            var falg = true;
            $("#videoPlayer").click(function () {
                if (falg) {
                    falg = false;
                    var params = {"videoId":${video.id}, "playNum":${video.playNum}};
                    $.post("${pageContext.request.contextPath}/video/updatePalyNum", params);
                }
            })
            //通过ajax更新播放次数

        });
        layui.use('element', function () {
            var element = layui.element;
        });
    </script>
</head>

<body class="w100">
<header>
    <div class="container">
        <span>欢迎来到学思网教育！</span>


        <div id="regBlock" style="display:none;float:right">
            <a href="javascript:;" id="reg_open"><img src="${pageContext.request.contextPath}/img/we.png">注册</a>
            <a href="javascript:;" id="login_open"><img src="${pageContext.request.contextPath}/img/we.png">登录</a>
        </div>

        <div id="userBlock" style="display:none;float:right">

            <a href="${pageContext.request.contextPath}/user/loginOut" id="loginout">退出</a>
            <a href="${pageContext.request.contextPath}/user/showMyProfile" id="account">${sessionScope.userAccount}</a>
        </div>

        <a onclick="JavaScript:addFavorite2()"><img src="${pageContext.request.contextPath}/img/sc.png"
                                                    draggable="false">加入收藏</a>
        <a onclick="pyRegisterCvt()" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2580094677&site=qq&menu=yes"><img
                src="${pageContext.request.contextPath}/img/we.png" draggable="false">联系我们</a>
        <a class="color_e4"><img src="${pageContext.request.contextPath}/img/phone.png" draggable="false"> 0375-208-9092&#x3000;&#x3000;0375-208-9051</a>

    </div>
</header>
<nav class="w100">
    <div class="container">
        <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="学思教育的logo"
             onclick="location.href='${pageContext.request.contextPath}/index.jsp'" draggable="false">
        <ul class="text_13 f_right">
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp" target="_blank">首页</a>
            </li>
            <li class="nav_down">
                高端课程<img src="${pageContext.request.contextPath}/img/nav_down.png" alt="" draggable="false">
                <ul id="nav_down" class="t_center">
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=1">国画</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=2">铅笔画</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=3">水彩</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=4">版画</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=5">漫画</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=6">抽象画</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=7">油画</a>
                    </li>
                    <li>
                        <a target="_blank" href="${pageContext.request.contextPath}/course/list?subjectId=8">钢笔画</a>
                    </li>


                </ul>
            </li>
            <li id="gkk" class="nav_choose">
                <a href="${pageContext.request.contextPath}/user/case">专家师资</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/home/index">后台管理</a>
            </li>
            <li class="nav_last">
                <a href="${pageContext.request.contextPath}/user/about">关于学思网</a>
            </li>
        </ul>
    </div>
</nav>
<div id="detail"></div>

<div>
    <!--面包屑导航-->
    <div class="container mian-nav">公开课/${subjectName}</div>

    <div class="intro">
        <div class="container">
            <div class="v-intro">
                <div class="left" id="videoPlayer">
                    <video src="../video/${video.videoPath}" class="video-js vjs-default-skin" controls width="627"
                           height="280"
                           poster="../${video.coverPath}" data-setup="{}">
                    </video>
                </div>

                <div class="right">
                    <p class="right-title">${video.title}</p>
                    <div class="avatar">
                        <span style="background-image: url(../${video.speaker.headImgUrl})"></span>
                        <p><b>讲师：${video.speaker.speakerName}</b><br><i>${video.speaker.speakerDesc}</i></p>
                    </div>
                    <p class="video-intro">
                        <span>本节内容：</span> ${video.videoDesc}
                    </p>
                </div>
            </div>
            <c:if test="${isFavorites==true }">
                <span class="kcjs"><a class="content" title="已收藏" onclick="deleteShoucang(${shoucang.id},this)" href="javascript:void(0)">已收藏</a></span>
            </c:if>
            <c:if test="${isFavorites==false }">
            <span class="kcjs ">
                <a class="content " title="收藏" onclick="shoucang(${video.id},this)" href="javascript:void(0)">收藏</a>
            </span>
            </c:if>
            <div class="kcjs">
                <p class="title">课程介绍</p>
                <p class="content">${course.courseDesc}</p>
            </div>

            <!-- 留言的表单 -->
            <form class="layui-form" action="${pageContext.request.contextPath}/pinglun/saveWords.do" method="post">
                <input name="lw_name" value="${sessionScope.name}" hidden="hidden"/>
                <input name="lw_date" value="<%=nowDate%>" hidden="hidden"/>
                <input name="lw_for_video_id" value="${video.id}" hidden="hidden"/>
                <div class="layui-input-block" style="margin-left: 0;">
                    <textarea id="lw_content" name="lw_content" placeholder="请输入你的留言" class="layui-textarea" style="height: 150px;"></textarea>
                </div>
                <br/>
                <div class="layui-input-block" style="text-align: left;margin-left: 0;">
                    <input type="submit"  class="layui-btn" value="留言">
                </div>
            </form>
        </br>
            <!-- 留言信息列表展示 -->
            <div>
                <ul>
                    <!-- 先遍历留言信息（一条留言信息，下面的全是回复信息） -->
                    <c:forEach items="${requestScope.lw_list}" var="words">
                        <!-- 如果留言信息是在本文章下的才显示 -->
                        <c:if test="${words.lw_for_video_id eq video.id}">
                            <li style="border-top: 1px dotted #01AAED">
                                <br/>
                                <div style="text-align: left;color:#444">
                                    <div>
                                        <span style="color:#01AAED">${words.lw_name}</span>
                                    </div>
                                    <div>${words.lw_content}</div>
                                </div>
                                <div>
                                    <div class="comment-parent" style="text-align:left;margin-top:7px;color:#444">
                                        <span>${words.lw_date}</span>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <p>
                                            <a href="javascript:;" style="text-decoration: none;" onclick="btnReplyClick(this)">回复</a>
                                        </p>
                                        <hr style="margin-top: 7px;"/>
                                    </div>
                                    <!-- 回复表单默认隐藏 -->
                                    <div class="replycontainer layui-hide" style="margin-left: 61px;">
                                        <form action="${pageContext.request.contextPath}/pinglun/saveReply.do" method="post" class="layui-form">
                                            <input name="lr_for_video_id" id="lr_for_video_id" value="${video.id}" hidden="hidden"/>
                                            <input name="lr_name" id="lr_name" value="${sessionScope.name}" hidden="hidden"/>
                                            <input name="lr_date" id="lr_date" value="<%=nowDate%>" hidden="hidden"/>
                                            <input name="lr_for_name" id="lr_for_name" value="${words.lw_name}" hidden="hidden"/>
                                            <input name="lr_for_words" id="lr_for_words" value="${words.lw_id}" hidden="hidden"/>
                                            <input name="lr_for_reply" id="lr_for_reply" value="${reply.lr_id}" hidden="hidden"/>
                                            <div class="layui-form-item">
                                                <textarea name="lr_content" id="lr_content" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>
                                            </div>
                                            <div class="layui-form-item">
                                                <button id="replyBtn" class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <!-- 以下是回复信息 -->
                                <c:forEach items="${requestScope.lr_list}" var="reply">
                                    <!-- 每次遍历出来的留言下存在回复信息才展示（本条回复信息是本条留言下的就显示在当前留言下） -->
                                    <c:if test="${reply.lr_for_video_id eq video.id && reply.lr_for_words eq words.lw_id}">
                                        <div style="text-align: left;margin-left:61px;color: #444">
                                            <div>
                                                <span style="color:#5FB878">${reply.lr_name}&nbsp;&nbsp;</span>
                                            </div>
                                            <div>@${reply.lr_for_name}:&nbsp;&nbsp; ${reply.lr_content}</div>
                                        </div>
                                        <div>
                                            <div class="comment-parent" style="text-align:left;margin-top:7px;margin-left:61px;color:#444">
                                                <span>${reply.lr_date}</span>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <p>
                                                    <a href="javascript:;" style="text-decoration: none;" onclick="btnReplyClick(this)">回复</a>
                                                </p>
                                                <hr style="margin-top: 7px;"/>
                                            </div>
                                            <!-- 回复表单默认隐藏 -->
                                            <div class="replycontainer layui-hide" style="margin-left: 61px;">
                                                <form action="${pageContext.request.contextPath}/pinglun/saveReply.do" method="post" class="layui-form">
                                                    <input name="lr_for_video_id" id="lr_for_video_id" value="${video.id}" hidden="hidden"/>
                                                    <input name="lr_name" id="lr_name" value="${sessionScope.name}" hidden="hidden"/>
                                                    <input name="lr_date" id="lr_date" value="<%=nowDate%>" hidden="hidden"/>
                                                    <input name="lr_for_name" id="lr_for_name" value="${reply.lr_name}" hidden="hidden"/>
                                                    <input name="lr_for_words" id="lr_for_words" value="${words.lw_id}" hidden="hidden"/>
                                                    <input name="lr_for_reply" id="lr_for_reply" value="${reply.lr_id}" hidden="hidden"/>
                                                    <div class="layui-form-item">
                                                    <textarea name="lr_content" id="lr_content" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>
                                                    </div>
                                                    <div class="layui-form-item">
                                                        <button id="replyBtn" class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <!--目录-->
    <div class="catalog">
        <div class="container">
            <p class="title">目录</p>

            <c:forEach items="${course.videoList}" var="video">
                <div class="chapter" onclick="load(${video.id})">
                    <p class="biaoti"><a
                            href="${pageContext.request.contextPath}/video/showVideo?videoId=${video.id}&subjectName=${subjectName}">${video.title}</a>
                    </p>
                    <p class="lecturer">${video.videoDesc}</p>
                    <p class="lecturer">讲师：${video.speakerName}</p>
                    <div class="v-info">
                        <span class="count"><img src="${pageContext.request.contextPath}/img/count.png"
                                                 alt="">${video.playNum}</span>
                        <span class="duration"><img src="${pageContext.request.contextPath}/img/player.png"
                                                    alt="">${video.showTime}</span>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>

</div>

<!--页脚-->
<footer>
    <ul>
        <li>
            <img src="${pageContext.request.contextPath}/img/xuesi_foot.png" width="120" height="120">
        </li>
        <li class="mt25">
            <h3>校区地址</h3>
            <ul>
                <li>地址<br>河南省洛阳市洛阳师范学院学思网教育</li>

            </ul>
        </li>
        <li class="mt25">
            <h3>联系我们</h3>
            <ul id="foo_icon">
                <li>河南省洛阳市学思网教育</li>
                <li>e-mail:18337159695@163.com</li>
                <li>电话:18337159695 18337159695</li>
            </ul>
        </li>

    </ul>
    <div class="record">学思网教育 &copy; 豫ICP备13013243号;学思网教育</div>
</footer>

<script src="${pageContext.request.contextPath}/js/video.js"></script>
<script src="${pageContext.request.contextPath}/js/index.js"></script>

<script type="text/javascript">
    $("#loginout2").click(function () {

        $.get("${pageContext.request.contextPath}/user/loginOut", null, function () {
            /* $("#regBlock").css("display","block");
            $("#userBlock").css("display","none"); */
            location.href = "../../../index.jsp";
        });

    });

    /**
     * 收藏课程
     * @param id  视频ID
     *
     */

    function shoucang(id, obj) {

        $.ajax({
            url: 'http://localhost:8080/xuesi/shoucang/addShoucang/' + id,
            type: 'post',

            dataType: 'json',
            success: function (result) {
                if (result.success == false) {
                    dialog('提示', result.message, 1);
                } else {
                    $(obj).html("已收藏").attr("title", "已收藏").parent().addClass("sc-end");
                    dialog('提示', result.message, 0);
                }
            }
        });
    }

    /**
     * 删除课程
     * @param id  视频ID
     *
     */

    function deleteShoucang(id, obj) {

        $.ajax({
            url: 'http://localhost:8080/xuesi/shoucang/deleteShoucang/' + id,
            type: 'post',

            dataType: 'json',
            success: function (result) {
                if (result.success == false) {
                    dialog('提示', result.message, 1);
                } else {
                    $(obj).html("收藏").attr("title", "收藏").parent().addClass("sc-end");
                    dialog('提示', result.message, 0);
                }
            }
        });
    }
    $("#replyBtn").click(function(){
        var lr_for_video_id = $("#lr_for_video_id").val();
        var lr_name = $("#lr_name").val();
        var lr_date = $("#lr_date").val();
        var lr_for_name = $("#lr_for_name").val();
        var lr_content = $("#lr_content").val();
        var lr_for_words = $("#lr_for_words").val();
        $.ajax({
            url: '${pageContext.request.contextPath}/pinglun/saveReply.do',
            type: 'POST',
            data: [{
                lr_for_video_id: lr_for_video_id,
                lr_name: lr_name,
                lr_date: lr_date,
                lr_for_name: lr_for_name,
                lr_content: lr_content,
                lr_for_words: lr_for_words
            }],
            success: function(data){
                layer.open({
                    title: '提示信息',
                    content: '留言成功',
                    btn: ['确定'],
                    btn1: function(index){
                        $("body").html(data);
                    }
                });
            },
            error: function(){
                layer.open({
                    title: '提示信息',
                    content: '出现未知错误'
                });
            }
        });
    });
</script>
<script type="text/javascript">
    function btnReplyClick(elem) {
        var $ = layui.jquery;
        if($(this)){
        }else if(!$(this)){
            $(elem).parent('p').parent('.comment-parent').siblings('.replycontainer').toggleClass('layui-show');
        }
        $(elem).parent('p').parent('.comment-parent').siblings('.replycontainer').toggleClass('layui-hide');
        if ($(elem).text() == '回复') {
            $(elem).text('收起')
        } else {
            $(elem).text('回复')
        }
    }
</script>
</body>

</html>
