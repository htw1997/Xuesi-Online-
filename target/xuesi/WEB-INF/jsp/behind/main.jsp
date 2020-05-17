<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="cn" xmlns:th="http://www.thymeleaf.org">

	<head >
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!--网页的标题-->
		<title>学思网后台视频资源管理系统</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css" th:href="@{/layui/css/layui.css}"/>
	</head>
	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin ">
			<div class="layui-header ">
				<div class="layui-logo" >学思网后台管理系统</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->
				<ul class="layui-nav layui-layout-right layui-bg-green">

					<li class="layui-nav-item">
						<a href="javascript:;">
							<!--用户的头像  用户名展示    img/xxx.jpg-->
							<img src="http://localhost:8080/xuesi/${user.faceImage}"  class="layui-nav-img"> <span th:text="${user.nickname}">${user.nickname}</span>
						</a>

					</li>
					<li class="layui-nav-item">
						<a href="${pageContext.request.contextPath}/home/loginout"  th:href="@{/home/loginout}">退了</a>
					</li>
				</ul>
			</div>

			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll">
					<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
					<ul class="layui-nav layui-nav-tree" lay-filter="test">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;">视频管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/video/video"  th:href="@{/video/video}"  target="main">上线视频</a>
								</dd>
							</dl>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/video/addVideo"  th:href="@{/video/addVideo}"  target="main">添加视频</a>
								</dd>
							</dl>
						</li>
					
						<li class="layui-nav-item" th:if="${session.user.rId} == 'admin'">
							<a href="javascript:;">课程管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/course/course"  th:href="@{/course/course}"  target="main">课程信息</a>
								</dd>
							</dl>
						</li>
						<li class="layui-nav-item" >
							<a href="javascript:;">讲师管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/speakers/speaker"  th:href="@{/speakers/speaker}"  target="main">讲师信息</a>
								</dd>
							</dl>
						</li>

						<li class="layui-nav-item" >
							<c:if test="${user.rId == 'admin' }">
							<a href="javascript:;">用户管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/user/beforeuser"th:href="@{/user/beforeuser}"  target="main">前台用户信息</a>
								</dd>
								<dd>
									<a href="${pageContext.request.contextPath}/home/behinduser"th:href="@{/home/behinduser}"  target="main">后台用户信息</a>
								</dd>
							</dl>
							</c:if>
						</li>
						<li class="layui-nav-item" th:if="${session.user.rId} == 'admin'">
							<a href="javascript:;">设置</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="${pageContext.request.contextPath}/home/returnPassword"th:href="@{/home/returnPassword}"  target="main">修改密码</a>
								</dd>
								<dd>
									<a href="${pageContext.request.contextPath}/home/returnEditAdmin"th:href="@{/home/returnEditAdmin}"  target="main">修改个人信息</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>

			<!--主体内容-->
			<div class="layui-body">
				<!--src 表示默认跳转的页面地址-->
				<iframe src="${pageContext.request.contextPath}/video/video" th:src="@{/video/video}" width="100%" height="100%" name="main"></iframe>
			</div>

			<div class="layui-footer">
				<!-- 底部固定区域 -->
				© 学思网- 版权
			</div>
		</div>
		<script src="${pageContext.request.contextPath}/layui/layui.js" th:src="@{/layui/layui.js}"></script>
		<script>
			//JavaScript代码区域
			layui.use('element', function() {
				var element = layui.element;

			});
		</script>
	</body>

</html>