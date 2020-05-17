<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>视频信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" th:href="@{/layui/css/layui.css}"/>
</head>

<body>
<!--模糊查询    m满足其中一种条件即可 or -->
<div class="videolike" style="margin-top: 10px;">
    ID:
    <div class="layui-inline">
        <input class="layui-input" name="speakerId" id="speakerId" autocomplete="off">
    </div>
    简介:
    <div class="layui-inline">
        <input class="layui-input" name="speakerDesc" id="speakerDesc" autocomplete="off">
    </div>
    <button type="button" class="layui-btn" id="re">
        <i class="layui-icon">&#xe615;</i>
    </button>


</div>


<!--表示加载数据的表格标签-->
<table class="layui-hide" id="videodemo" lay-filter="videotest"></table>

<!--自定义表头工具栏-->
<script type="text/html" id="bar">
    <c:if test="${user.rId == 'admin' }">
    <div class="layui-btn-group">
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe654;</i>
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="delete">
            <i class="layui-icon">&#xe640;</i>
        </button>
    </div>


    <div class="layui-btn-group" c:if="${user.rId} == 'admin'">
        <!--下载数据导入的模板-->layui-icon-upload-drag
        <!--<a class="layui-btn" href="/video_clips/videos/down?filename=moban.xlsx">数据模板</a>-->
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="up" id="test3">
            <i class="layui-icon layui-icon-upload-drag"></i>
        </button>
        <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" lay-event="down">
            <i class="layui-icon layui-icon-download-circle"></i>
        </button>
    </div>
    </c:if>
</script>
<!--自定义行工具栏按钮功能-->
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <c:if test="${user.rId == 'admin' }">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </c:if>
</script>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js" th:src="@{/layui/layui.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/date.js" th:src="@{/js1/date.js}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/speaker.js" th:src="@{/js1/speaker.js}"></script>
</html>