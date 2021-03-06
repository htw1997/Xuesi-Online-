<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="国画|油画|水彩|版画|漫画|抽象画">
    <meta name="description" content="学思网艺术教育,为您提供各种艺术视频以及相关知识等在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,学思网,学习成就梦想！">
    <meta name="author" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="icon" href="favicon.png" type="image/png">
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <title>学思网艺术教育|国画|油画|水彩|版画|漫画|抽象画</title>
    <script type="text/javascript">
       
       var regIsCommitOldPass=false;
       var regIsCommitPsw=false;
       
       $(function(){
          
          $("#oldPassword").blur(function(){
               
                
                var oldPass= $("#oldPassword").val();
                if(null!=oldPass && oldPass!=""){
                       
                       var params={"password":oldPass};
		                 // alert(params);
		                  $.post("${pageContext.request.contextPath}/user/validatePassword",params,function(data){
		                      if(data=="success"){
		                         regIsCommitOldPass=true;
		                         $("#oldMsg").text("原密码正确").css("color","green");
		                      }else{
		                         regIsCommitOldPass=false;
		                         $("#oldMsg").text("原密码错误").css("color","red");

		                      }
		                  });
                }
          });
          
          $("#newPassword").blur(function(){
               var pass01= $("#newPassword").val();
                var pass02= $("#newPassword").val();
                if(null==pass01 || ""==pass01 || null==pass02 || ""==pass02){
                    $("#passMsg").text("密码不能为空").css("color","red");
                    regIsCommitPsw =false;
                }else{
                    if(pass01!=pass02){
                        regIsCommitPsw=false;
                        $("#passMsg").text("两次密码输入不一致，请重新输入").css("color","red");
                    }else{
                        regIsCommitPsw=true;
                        $("#passMsg").text("密码相同");
                    }
                }
                
          });


       });
       function updatePassword() {
           if (regIsCommitOldPass && regIsCommitPsw) {
               alert($("form").serialize());
               $.post("${pageContext.request.contextPath}/user/updatePassword",$("form").serialize(),function (data) {
                   if (data == "success"){

                       return true;
                   } else {
                       $("#prompt").text("修改失败").css("color","red");
                       return false;
                   }
               })
           }else {
               $("#prompt").text("填写不正确无法提交").css("color","red");
               return false;
           }
       }

    </script>
</head>

<body class="w100">
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
                    <li><a href="#">关于我们</a></li>
                    <li class="menu_active"><a href="${pageContext.request.contextPath}/user/showMyProfile">个人中心</a></li>
                </ul>

                <div id="user_bar">
                    <a href="${pageContext.request.contextPath}/user/showMyProfile">
                      <c:if test="${empty user.imgurl}">
                         <img id="avatar" src="${pageContext.request.contextPath}/img/avatar_lg.png" alt="">
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
                    <h3><a href="${pageContext.request.contextPath}/user/showMyProfile">返回个人中心</a></h3>
                    <div class="proflle_tab_workplace clearfix">
                        <div class="profile_avatar_area">
                        
                           <c:if test="${empty user.imgurl}">
		                         <img id="avatar" src="${pageContext.request.contextPath}/img/avatar_lg.png" alt="">
		                      </c:if>
		                      
		                      <c:if test="${not empty user.imgurl}">
		                         <img id="avatar" width="180px" height="180px" src="${user.imgurl}" alt="">
		                      </c:if>
                           
                        </div>
                        <div class="profile_ifo_area">
                            <form  action="${pageContext.request.contextPath}/user/showMyProfile">
                                <div class="form_group">
                                    <span class="dd">旧&#x3000;密&#x3000;码：</span>
                                    <input type="password" id="oldPassword" name="oldPassword"><span id="oldMsg"></span>
                                </div>
                                <div class="form_group">
                                    <span class="dd">新&#x3000;密&#x3000;码：</span>
                                    <input type="password" id="newPassword"  name="newPassword">
                                </div>
                                <div class="form_group">
                                    <span class="dd">确认新密码：</span>
                                    <input type="password" id="newPassword02" name="newPassword02"><span id="passMsg"></span>
                                </div>
                                <div class="form_submit dd">
                                    <input type="submit"   onclick="return updatePassword()" value="保&#x3000;存">
                                    <a href="#">取消</a>
                                </div>
                            </form>
                            <span id="prompt"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <div class="container">
            <ul>
                <li><img src="${pageContext.request.contextPath}/img/xuesi_foot.png" alt="" id="foot_logo"></li>
                <li>版权所有：学思网教育&#x3000;&#x3000;&#x3000;&copy;&nbsp;www.xuesi.art</li>
            </ul>
        </div>
    </footer>
</body>

</html>