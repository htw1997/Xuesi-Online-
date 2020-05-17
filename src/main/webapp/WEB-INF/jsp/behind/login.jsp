<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="loginHtml" lang="cn" xmlns:th="http://www.thymeleaf.org">
<head>
	<meta charset="utf-8">
	<title>后台登录</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="${pageContext.request.contextPath}/img1/ico.ico" th:href="@{/img/ico.ico}">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all" th:href="@{/layui/css/layui.css}"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css1/public.css" media="all" th:href="@{/css1/public.css}"/>
</head>
<body class="loginBody">
	<form class="layui-form" >
		<!--头像-->
		<div class="login_face"><img src="../img1/head.jpg" th:src="@{/img1/head.jpg}" class="userAvatar" style="width: 100%;height: 100%"></div>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input type="text" placeholder="请输入用户名" autocomplete="off" id="username" name="username" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" autocomplete="off" id="password" name="password"   class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item" id="imgCode">
			<label for="code">验证码</label>
			<input type="text" placeholder="请输入验证码" autocomplete="off" id="code" name="code"  class="layui-input">
			<img src="../home/getCode" th:src="@{/home/getCode}"  onclick="changeCode()" id="codeImg">

		</div>
		<div class="layui-form-item">
			<button  type="button" class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
		</div>
		<div class="layui-form-item">
			<button  type="button" class="layui-btn layui-block" id="register">注册</button>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js" th:src="@{/layui/layui.js}" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js1/login.js" th:src="@{/js1/login.js}"></script>
</body>
</html>