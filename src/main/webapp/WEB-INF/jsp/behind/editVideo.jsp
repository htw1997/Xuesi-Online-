<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>编辑视频</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<!--需要一个表单form-->
<form class="layui-form" id="form">
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频ID</label>
        <div class="layui-input-block">
            <!--将model中的数据填充到输入框里面-->
            <input type="text" name="vid"   class="layui-input layui-disabled" value="${video.id}">
        </div>
    </div>
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频标题</label>
        <div class="layui-input-block">
            <input type="text" name="videoDesc"  class="layui-input" value="${video.title}" >
        </div>
    </div>
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频描述</label>
        <div class="layui-input-block">
            <input type="text" name="videoDesc"  class="layui-input" value="${video.videoDesc}" >
        </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label">视频状态</label>
    <div class="layui-input-block">
        <select name="status" lay-filter="aihao">
            <option value="">请选择视频状态</option>
            <option value="1">已发布</option>
            <option value="0">已下线</option>
        </select>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/video.js"></script>
</html>