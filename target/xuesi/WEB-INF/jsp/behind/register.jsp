<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<!--需要一个表单form-->
<form class="layui-form" id="form">
    <!--用户的电话号码-->
    <div class="layui-form-item">
        <label class="layui-form-label">验证手机</label>
        <div class="layui-input-inline">
            <input type="tel" name="tel"  id="tel" lay-verify="required|phone" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="getTelCode">获取短信</button>
        </div>
    </div>
    <!--用户的密码-->
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="text" name="password" id="password"  class="layui-input" >
        </div>
    </div>
    <!--手机短信验证码-->
    <div class="layui-form-item">
        <label class="layui-form-label">验证码</label>
        <div class="layui-input-inline">
            <input type="text" name="code" id="code"  class="layui-input" >
        </div>
    </div>
    <!--submit的提交按钮-->
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="subregister">立刻注册</button>
        </div>
    </div>
</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/login.js"></script>
</html>