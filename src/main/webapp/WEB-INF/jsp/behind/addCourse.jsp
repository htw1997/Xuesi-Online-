<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html  lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>添加课程信息</title>
    <!--http://119.23.190.69:8080/layui/css/layui.css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>

    <script type="text/javascript">
        $.ajax({
            url: '../subject/findSubjectTypeList',
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#subjectId').append(
                        console.log("无奈呀"),
                        new Option(item.subjectName, item.id))// 下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });
    </script>

</head>
<style>
    .layui-layer-d{
        color: #000000;
        font-size: 10px;
    }
</style>
<body>
<!--需要一个表单form-->
<form class="layui-form" id="form">

    <!--课程的标题-->
    <div class="layui-form-item">
        <label class="layui-form-label">课程名称</label>
        <div class="layui-input-block">
            <input type="text" value="${course.courseTitle}"    name="courseTitle"   placeholder="请添加课程名称" class="layui-input" >
        </div>
    </div>
    <!--讲师的描述信息-->
    <div class="layui-form-item">
        <label class="layui-form-label">课程描述</label>
        <div class="layui-input-block">
            <input type="text" value="${course.courseDesc}"    name="courseDesc"   placeholder="请对课程进行描述" class="layui-input" >
        </div>
    </div>

    <label class="layui-form-label">所属学科 </label>
    <div class="layui-input-inline">
        <select id="subjectId" name="subjectId" lay-verify="required" lay-filter="business"
                autocomplete="off">
            <option value="">请选择学课类别</option>
        </select>
    </div>



    <!--submit的提交按钮-->
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="submit">立即提交</button>
        </div>
    </div>
</form>
</body>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js1/course.js"></script>
</html>