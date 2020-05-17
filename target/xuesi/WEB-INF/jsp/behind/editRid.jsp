<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>编辑用户权限</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<!--需要一个表单form-->
<form class="layui-form" id="form">
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-block">
            <!--将model中的数据填充到输入框里面-->
            <input type="text" name="vid"   class="layui-input layui-disabled" value="${admin1.id}">
        </div>
    </div>
    <!--用户的信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">权限状态</label>
        <div class="layui-input-block">
            <select name="rId" lay-filter="aihao">
                <option value="">请选择权限</option>
                <option value="1">管理员</option>
                <option value="2">用户</option>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/editRid.js"></script>
</html>