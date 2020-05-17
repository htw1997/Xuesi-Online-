<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>编辑讲师信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改个人信息</legend>
</fieldset>
<!--需要一个表单form-->
<form class="layui-form" id="form">
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-block">
            <!--将model中的数据填充到输入框里面-->
            <input type="text" name="vid"   class="layui-input layui-disabled" value="${user.id}">
        </div>
    </div>
    <!--用户名-->
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username"  class="layui-input layui-disabled" value="${user.username}" >
        </div>
    </div>

    <!--用户昵称-->
    <div class="layui-form-item">
        <label class="layui-form-label">用户昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname"  class="layui-input" value="${user.nickname}" >
        </div>
    </div>

    <!--用户头像-->
    <div class="layui-form-item">
        <label class="layui-form-label">用户头像</label>
        <div class="layui-input-block">
            <input type="text" name="faceImage" id="faceImage" lay-verify="required" lay-reqtext="讲师照片是必填选项,岂能为空?" placeholder="请输入" autocomplete="off" class="layui-input layui-disabled" value="${user.faceImage}">
            <!--图片上传-->
            <div class="layui-upload">
                <button type="button" class="layui-btn layui-btn-normal" id="chooseimg">选择图片</button>
                <button type="button" class="layui-btn" id="uploadimg">开始上传</button>
            </div>
        </div>
    </div>
    <!--submit的提交按钮-->
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="subedit">立即提交</button>
        </div>
    </div>
</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/geren.js"></script>
</html>