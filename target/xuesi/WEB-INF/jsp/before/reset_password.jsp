<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/forget_password.css">
    <link rel="icon" href="favicon.png" type="image/png">
    <title>学思网艺术教育|国画|油画|水彩|版画|漫画|抽象画</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
         function commitForm(){
                var pass01= $("#password").val();
                var pass02= $("#newPassword").val();
                if(null==pass01 || ""==pass01 || null==pass02 || ""==pass02){
                    $("#passMsg").text("密码不能为空").css("color","red");
                    return false;
                }else{
                    if(pass01!=pass02){
                        
                        $("#passMsg").text("两次密码输入不一致，请重新输入").css("color","red");
                        return false;
                    }else{
                       
                        $("#passMsg").text("");
                         return true;
                    }
                }
         }
    </script>
</head>

<body>
    <header>
        <div class="container">
            <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="小禅院">
        </div>
    </header>
    <main>
        <div class="container">
            <form class="ma" action="${pageContext.request.contextPath}/user/resetPassword">
               <input type="hidden" name="mail" value="${mail}"/>
                <div class="form_header">
                    <div class="form_title">
                        <h2>重置密码</h2>
                    </div>
                    
                </div>
                <div class="form_body">
                    <input type="password" placeholder="请输入新密码" id="password" name="password">
                    <input type="password" style="width:100%" placeholder="再次输入新密码" id="newPassword" name="newPassword"><span id="passMsg"></span>
                    <input type="submit" style="margin:0px;width:100%" value="提交" onclick="return commitForm()";>
                </div>
                
            </form>
        </div>
    </main>
</body>

</html>