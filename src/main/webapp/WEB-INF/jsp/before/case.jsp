<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0">
  <title>学思网-专家师资</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
  <!-- nav部分 -->
  <div class="nav">
    <div class="layui-container">
      <!-- 公司logo -->
      <div class="nav-logo">
        <a href="#">
          <img src="${pageContext.request.contextPath}/img/logo.png " width="80"   height="90" alt="学思网">
        </a>
      </div>
      <div class="nav-list">
        <button>
          <span></span><span></span><span></span>
        </button>
        <ul class="layui-nav" lay-filter="">
          <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
          <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/index.jsp">课程信息</a></li>
          <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/case">专家师资</a></li>
          <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/home/index">后台管理</a></li>
          <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/about">关于学思网</a></li>
        </ul>
      </div>
    </div>
  </div>
  <!-- banner部分 -->
  <div class="banner case">
    <div class="title">
      <p>专家师资</p>
      <p class="en">Expert Teachers</p>
    </div>
  </div>
  <!-- main部分 -->
  <div class="main-case">
    <div class="layui-container">
      <div class="layui-row">
        <div class="layui-inline content">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case1.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content even center">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case2.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case3.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content even">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case4.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content center">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case5.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content even">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case6.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case7.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content even center">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case8.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
        <div class="layui-inline content">
          <div class="layui-inline case-img"><img src="${pageContext.request.contextPath}/img/case9.jpg"></div>
          <p class="lable">名牌工厂店</p>
          <p>一家工厂企业的商品展示网站，主要以卖高端服饰为主。主要以卖高端服饰为主。主要以卖高端服饰为主。</p>
        </div>
      </div>

    </div>
  </div>
  <!-- footer部分 -->
  <div class="footer">
    <div class="layui-container">
      <p class="footer-web">
        <a href="javascript:;">合作伙伴</a>
        <a href="javascript:;">企业画报</a>
        <a href="javascript:;">JS网</a>
        <a href="javascript:;">千图网</a>
        <a href="javascript:;">昵图网</a>
        <a href="javascript:;">素材网</a>
        <a href="javascript:;">花瓣网</a>
      </p>
      <div class="layui-row footer-contact">
        <div class="layui-col-sm2 layui-col-lg1"><img src="${pageContext.request.contextPath}/img/erweima.jpg"></div>
        <div class="layui-col-sm10 layui-col-lg11">
          <div class="layui-row">
            <div class="layui-col-sm6 layui-col-md8 layui-col-lg9">
              <p class="contact-top"><i class="layui-icon layui-icon-cellphone"></i>&nbsp;400-8888888&nbsp;&nbsp;&nbsp;(9:00-18:00)</p>
              <p class="contact-bottom"><i class="layui-icon layui-icon-home"></i>&nbsp;88888888@163.com</span></p>
            </div>
            <div class="layui-col-sm6 layui-col-md4 layui-col-lg3">
              <p class="contact-top"><span class="right">洛阳师范学院</span></p>
              <p class="contact-bottom"><span class="right">Copyright&nbsp;©&nbsp;2016-2018&nbsp;&nbsp;ICP&nbsp;备888888号</span></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<!--[if lt IE 9]>
  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
  layui.config({
    base: '${pageContext.request.contextPath}/js/'
  }).use('firm'); 
</script>
</body>
</html>