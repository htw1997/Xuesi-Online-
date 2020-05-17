<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>上传视频视频</title>
    <!--http://119.23.190.69:8080/layui/css/layui.css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        $.ajax({
            url: '../course/findCourseTypeList',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#courseId').append(
                        console.log("无奈呀"),
                        new Option(item.courseTitle, item.id))// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });
    </script>
</head>
<style>
    .layui-layer-d {
        color: #000000;
        font-size: 10px;
    }

</style>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>上传视频</legend>
</fieldset>
<!--需要一个表单form-->
<form class="layui-form" id="form">

    <!--视频的标题-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频标题</label>
        <div class="layui-input-block">
            <input type="text" value="${video.title}" name="title" placeholder="请添加视频标题" class="layui-input">
        </div>
    </div>
    <!--视频的课程信息-->
    <!--视频的标题-->
    <!--<form class="layui-form" action="" lay-filter="prg" style="margin-top: 2%">-->

    <label class="layui-form-label">所有课程 </label>
    <div class="layui-input-inline">
        <select id="courseId" name="courseId" lay-verify="required" lay-filter="business"
                autocomplete="off">
            <option value="">请选择课程类别</option>
        </select>
    </div>


    <!--
    <div class="layui-form-item">
        <label class="layui-form-label">课程分类</label>
        <div class="layui-input-block">
            <input type="text" value="${video.courseId}" name="courseId" placeholder="请输入课程所对应的id" class="layui-input">
        </div>
    </div>
    -->
    <!--视频输入选项-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频信息</label>
        <div class="layui-input-block">
            <input type="text" name="videoPath" id="videoPath" lay-verify="required" lay-reqtext="视频是必填选项,岂能为空?"
                   placeholder="请输入" autocomplete="off" class="layui-input layui-disabled">
            <!--视频上传-->
            <div class="layui-upload">
                <button type="button" class="layui-btn layui-btn-normal" id="choosevideo">选择视频</button>
                <button type="button" class="layui-btn" id="uploadvideo">开始上传</button>
            </div>
        </div>
    </div>

    <!--视频的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频描述</label>
        <div class="layui-input-block">
            <input type="text" value="${video.videoDesc}" name="videoDesc" placeholder="请对视频进行描述" class="layui-input">
        </div>
    </div>
    <!--视频封面-->
    <div class="layui-form-item">
        <label class="layui-form-label">视频封面</label>
        <div class="layui-input-block">
            <input type="text" name="coverPath" id="coverPath" lay-verify="required" lay-reqtext="视频封面是必填选项,岂能为空?"
                   placeholder="请输入" autocomplete="off" class="layui-input layui-disabled">
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
            <button type="button" class="layui-btn" id="submit">立即提交</button>
        </div>
    </div>
</form>
</body>


<script src="${pageContext.request.contextPath}/layui/layui.js" th:src="@{/layui/layui.js}"></script>
<script src="${pageContext.request.contextPath}/js1/video.js" th:src="@{/js1/video.js}"></script>
</html>