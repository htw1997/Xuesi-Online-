<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>编辑课程信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<!--需要一个表单form-->
<form class="layui-form" id="form">
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">课程ID</label>
        <div class="layui-input-block">
            <!--将model中的数据填充到输入框里面-->
            <input type="text" name="vid"   class="layui-input layui-disabled" value="${course.id}">
        </div>
    </div>
    <!--讲师的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">课程描述</label>
        <div class="layui-input-block">
            <input type="text" name="courseDesc"  class="layui-input" value="${course.courseDesc}" >
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/course.js"></script>
</html>