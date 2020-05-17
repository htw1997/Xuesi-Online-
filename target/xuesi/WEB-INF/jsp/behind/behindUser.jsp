<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>后台用户信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" th:href="@{/layui/css/layui.css}"/>
</head>

<body>
<!--模糊查询    m满足其中一种条件即可 or -->
<div class="videolike" style="margin-top: 10px;">
    用户ID:
    <div class="layui-inline">
        <input class="layui-input" name="userId" id="userId" autocomplete="off">
    </div>
    用户登录名:
    <div class="layui-inline">
    <div class="layui-inline">
        <input class="layui-input" name="email" id="email" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="re">
        <i class="layui-icon">&#xe615;</i>
    </button>

</div>


<!--表示加载数据的表格标签-->
<table class="layui-hide" id="videodemo" lay-filter="videotest"></table>

<!--自定义表头工具栏-->
<script type="text/html" id="bar">



    <div class="layui-btn-group" >
        <!--下载数据导入的模板-->layui-icon-upload-drag
        <!--<a class="layui-btn" href="/video_clips/videos/down?filename=moban.xlsx">数据模板</a>-->
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="up" id="test3">
            <i class="layui-icon layui-icon-upload-drag"></i>
        </button>
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="down">
            <i class="layui-icon layui-icon-download-circle"></i>
        </button>
    </div>
</script>
<!--自定义行工具栏按钮功能-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js" th:src="@{/layui/layui.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/date.js" th:src="@{/js1/date.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/behindUser.js" th:src="@{/js1/behindUser.js}"></script>
</html>