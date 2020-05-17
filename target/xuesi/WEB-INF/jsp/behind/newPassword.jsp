<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>课程信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css"
          th:href="@{/layui/css/layui.css}"/>
</head>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改密码</legend>
</fieldset>
<form class="layui-form" id="form">

    <div class="layui-form-item">
        <label class="layui-form-label">当前密码</label>
        <div class="layui-input-inline">
            <input type="text" id="oldPassword" name="oldPassword" name="oldPassword" placeholder="请输入原密码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请输入新密码</label>
        <div class="layui-input-inline">
            <input type="text" id="password" name="password" name="password" placeholder="请输入新密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请确认密码</label>
        <div class="layui-input-inline">
            <input type="text" d="newPassword" name="newPassword" name="newPassword" placeholder="请确认密码"
                   class="layui-input">
        </div>
        <span id="passMsg"></span>
    </div>
    <!--submit的提交按钮-->
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="subedit">立刻修改</button>
        </div>
    </div>
</form>


</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"
        th:src="@{/layui/layui.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/date.js" th:src="@{/js1/date.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/password.js" th:src="@{/js1/password.js}"></script>
</html>