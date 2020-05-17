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
<!--需要一个表单form-->
<form class="layui-form" id="form">
    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">讲师ID</label>
        <div class="layui-input-block">
            <!--将model中的数据填充到输入框里面-->
            <input type="text" name="vid"   class="layui-input layui-disabled" value="${speaker.id}">
        </div>
    </div>
    <!--讲师的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">讲师姓名</label>
        <div class="layui-input-block">
            <input type="text" name="speakerName"  class="layui-input" value="${speaker.speakerName}" >
        </div>
    </div>
    <!--讲师的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">讲师描述</label>
        <div class="layui-input-block">
            <input type="text" name="speakerDesc"  class="layui-input" value="${speaker.speakerDesc}" >
        </div>
    </div>

    <!--讲师的职称信息-->
    <c:if test="${user.rId == 'admin' }">
        <div class="layui-form-item">
            <label class="layui-form-label">讲师职称</label>
            <div class="layui-input-block">
                <select name="speakerJob" lay-filter="aihao">
                    <option value=""></option>
                    <option value="初级讲师">初级讲师</option>
                    <option value="中级讲师">中级讲师</option>
                    <option value="高级讲师">高级讲师</option>
                </select>
            </div>
        </div>
    </c:if>
    <!--视频封面-->
    <div class="layui-form-item">
        <label class="layui-form-label">讲师照片</label>
        <div class="layui-input-block">
            <input type="text" name="headImgUrl" id="headImgUrl" lay-verify="required" lay-reqtext="讲师照片是必填选项,岂能为空?" placeholder="请输入" autocomplete="off" class="layui-input " value="${speaker.headImgUrl}">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js1/speaker.js"></script>
</html>