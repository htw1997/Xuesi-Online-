<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="国画|油画|水彩|版画|漫画|抽象画">
    <meta name="description" content="学思网艺术教育,为您提供各种艺术视频以及相关知识等在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,学思网,学习成就梦想！">
    <meta name="author" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.png" type="${pageContext.request.contextPath}/image/png" />
    <title>学思网艺术教育|国画|油画|水彩|版画|漫画|抽象画</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/gVerify.js"></script>
    <script type="text/javascript">

        $(function(){

            //div 两个哪个显示呢？
            if(null!="${sessionScope.userAccount}" && "${sessionScope.userAccount}" !=""){
                $("#regBlock").css("display","none");
                $("#userBlock").css("display","block");
            }else{
                $("#regBlock").css("display","block");
                $("#userBlock").css("display","none");
            }

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

            <a href="javascript:;" id="loginout">退出</a>
            <a href="${pageContext.request.contextPath}/user/showMyProfile" id="account">${sessionScope.userAccount}</a>
        </div>

        <a onclick="JavaScript:addFavorite2()"><img src="${pageContext.request.contextPath}/img/sc.png" draggable="false">加入收藏</a>
        <a onclick="pyRegisterCvt()" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1844167725&site=qq&menu=yes"><img src="${pageContext.request.contextPath}/img/we.png" draggable="false">联系我们</a>
        <a class="color_e4"><img src="${pageContext.request.contextPath}/img/phone.png" draggable="false"> 18337159695&#x3000;&#x3000;18337159695</a>

    </div>
</header>
<nav class="w100">
    <div class="container">
        <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="学思网教育的logo"  width="80"   height="90" onclick="location.href='#'" draggable="false">
        <ul class="text_13 f_right">
            <li>
                <a href="#" target="_blank">首页</a>
            </li>
            <li class="nav_down">
                课程分类<img src="${pageContext.request.contextPath}/img/nav_down.png" alt="" draggable="false">
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
                <a href="${pageContext.request.contextPath}/user/about" >关于学思网</a>
            </li>
        </ul>
    </div>
</nav>

<!--banner图-->
<div class="banner index-banner"></div>

<!--模块化课程-->
<div class="course">
    <div class="container">
        <p class="title">在线课程，从绘画小白到画界大咖，画圣并不遥远</p>
        <div class="course-info">
            <table cellspacing="10">
                <tr>
                    <td colspan="2">
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=1">
                            <img src="${pageContext.request.contextPath}/img/国画.jpg" alt="" class="image scale" draggable="false">
                            <div class="headline">
                                <span>国画</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                    <td>
                        <!--上线时修改id-->
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=2">
                            <img src="${pageContext.request.contextPath}/img/铅笔画.jpg" alt="" class="image scale" draggable="false">
                            <div class="headline">
                                <span>铅笔画</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                    <td rowspan="2" class="one_three">
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=3" >
                            <img src="${pageContext.request.contextPath}/img/水彩3.jpg" alt="" class="image" draggable="false">
                            <div class="headline">
                                <span>水彩</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td >
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=4" >
                            <img src="${pageContext.request.contextPath}/img/版画.jpg" alt="" class="image" draggable="false">
                            <div class="headline">
                                <span>版画</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                    <td colspan="2">
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=5">
                            <img src="${pageContext.request.contextPath}/img/漫画.jpg" alt="" class="image scale" draggable="false">
                            <div class="headline">
                                <span>漫画</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td rowspan="2" class="one_three">
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=6" class="opacity2">
                            <img src="${pageContext.request.contextPath}/img/抽象画.jpg" alt="" class="image" draggable="false">
                            <div class="headline">
                                <span>抽象</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                    <td colspan="2">
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=8">
                            <img src="${pageContext.request.contextPath}/img/钢笔画.jpg" alt="" class="image scale" draggable="false">
                            <div class="headline">
                                <span>钢笔画</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                    <td colspan="2" class="three_two">
                        <img src="${pageContext.request.contextPath}/img/qidai.jpg" alt="" class="image" draggable="false">
                        <div class="headline">
                            更多课程，敬请期待...
                        </div>
                    </td>
                </tr>
                <tr>

                    <td colspan="3"class="three_two">
                        <a href="${pageContext.request.contextPath}/course/list?subjectId=7">
                            <img src="${pageContext.request.contextPath}/img/油画.jpg" alt="" class="image scale" draggable="false">
                            <div class="headline">
                                <span>油画</span>
                                <img src="${pageContext.request.contextPath}/img/arrow.png" alt="">
                            </div>
                        </a>
                    </td>
                </tr>
            </table>
            <!--马上试听-->
            <a onclick="pyRegisterCvt()" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1844167725&site=qq&menu=yes">
                <div class="audition">高级课程</div>
            </a>
        </div>
    </div>
</div>


<!--报名表单-->
<div class="form_area">
    <div class="container">
        <p class="title"><b>这个世界上可以选择的很多，可以改变命运的选择很少<br />你现在准备好向梦想出发了吗？</b></p>
        <form id="iform" action="#" method="post">
            <div class="form_line1"></div>
            <div class="form_line2"></div>
            <div class="wrap">
                <div><label for="name">姓名：</label><input name="name" id="name" type="text" class="form-control" /></div>
                <div><label for="tel">手机号：</label><input name="tel" id="tel" type="text" class="form-control" /></div>
                <div><label for="qq">QQ：</label><input name="qq" id="qq" type="text" class="form-control" /></div>
            </div>
            <input class="button" type="submit" onclick="return baoming()" value="立即报名" />
        </form>
    </div>
</div>

<!--页脚-->
<footer>

    <ul>
        <li>
            <img src="${pageContext.request.contextPath}/img/xuesi_foot.png">
        </li>
        <li class="mt25">
            <h5>校区地址</h5>
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

<!--登录注册弹出框-->
<div class="mask hidden" id="login">
    <div class="mask_content">
        <div class="mask_content_header">
            <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="" class="ma">
        </div>
        <div class="mask_content_body">
            <form id="loginForm" action="#">
                <h3>快速登录</h3>
                <input type="phone" id="loginEmail" placeholder="请输入邮箱" name="email">
                <input type="password" id="loginPassword" placeholder="请输入密码" name="password">
                <div id="forget">
                    <a href="${pageContext.request.contextPath}/user/forgetPassword">忘记密码？</a>
                </div>
                <input type="submit" onclick="return commitLogin()" value="登&#x3000;录">
            </form>
        </div>
        <div class="mask_content_footer">
            <span id="login_close">关&#x3000;闭</span>
        </div>
    </div>
</div>

<!--用户注册弹出框-->
<div class="mask hidden" id="reg">
    <div class="mask_content">
        <div class="mask_content_header">
            <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="" class="ma">
        </div>
        <div class="mask_content_body">
            <form id="regForm" action="user/insertUser">
                <h3>新用户注册</h3>
                <input type="phone" id="regEmail" placeholder="请输入邮箱" name="email"><span id="emailMsg"></span>
                <input type="password" id="regPsw" placeholder="请输入密码" name="password">
                <input type="password" id="regPswAgain" placeholder="请再次输入密码" name="psw_again"><span id="passMsg"></span>
                <div id="yzm" class="form-inline">
                    <input type="text" name="yzm" style="width: 45%; display: inline-block;">
                    <div id="v_container" style="width: 45%;height: 40px;float:right;"></div>
                </div>
                <input type="submit" onclick="return commitRegForm();" value="注&#x3000;册">
            </form>
        </div>
        <div class="mask_content_footer">
            <span id="reg_close">关&#x3000;闭</span>
        </div>
    </div>
</div>




</body>

<script src="${pageContext.request.contextPath}/js/index.js"></script>
</html>
