<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="国画|油画|水彩|版画|漫画|抽象画">
    <meta name="description" content="学思网艺术教育,为您提供各种艺术视频以及相关知识等在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,学思网,学习成就梦想！">
    <meta name="author" content="">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="icon" href="favicon.png" type="image/png">
    <title>学思网艺术教育|国画|油画|水彩|版画|漫画|抽象画</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        
        $(function(){
           
           var sex = '${user.sex}';
           $("input[name='sex']").each(function(i,obj){
               //obj是dom对象     不是jquery对象
               //alert(obj+","+i);
               //alert(obj.value+","+sex);
               if($(obj).val()==sex){
                 // obj.checked=true;
                 $(obj).attr("checked",true);
               }
           });
           
           var address ='${user.address}'; //""  河南-平顶山
           
           if(null!=address && address!=""){
               var arr=address.split("-");
        
	            $("#city").citySelect({
		            prov: arr[0],  //默认省份 
		            city: arr[1],  //默认城市
		            nodata: "none"
		        });
           }else{
                $("#city").citySelect({
		            prov: "河南",  //默认省份 
		            city: "平顶山",  //默认城市
		            nodata: "none"
		        });
           }
           
         });   
         
         function commitForm(){
            
            var address= $(".prov").val()+"-"+$(".city").val();
            
            $("#address").val(address);
            
           // $("form").commit();
             return true;
         }
    </script>
</head>

<body class="w100">
    <header>
        <div class="container top_bar clearfix">
            <img src="${pageContext.request.contextPath}/img/xuesi.png" alt="学思网">
            <div id="tele">
                <span>183-371-59695</span>
                <span>183-371-59695</span>
            </div>
        </div>
        <menu>
            <div class="container clearfix">
                <ul class="clearfix f_left">
                    <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">课程</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li class="menu_active"><a href="${pageContext.request.contextPath}/user/showMyProfile">个人中心</a></li>
                </ul>
                <div id="search_group">
                    <input type="text" placeholder="搜索课程">
                    <span id="search"></span>
                </div>
                <div id="user_bar">
                    <a href="${pageContext.request.contextPath}/user/showMyProfile">
                      <c:if test="${empty user.imgurl}">
                         <img id="avatar" src="${pageContext.request.contextPath}/img/avatar_lg.png" alt="">
                      </c:if>
                      
                      <c:if test="${not empty user.imgurl}">
                         <img id="avatar" src="${user.imgurl}" alt="">
                      </c:if>
                       
                    </a>
                    <a href="${pageContext.request.contextPath}/user/loginOut" id="lay_out">退出</a>
                </div>
            </div>
        </menu>
    </header>
    <main>
        <div class="container">
            <h2>我的资料</h2>
            <div id="profile_tab">
                <ul class="profile_tab_header f_left clearfix">
                    <li><a href="${pageContext.request.contextPath}/user/changeProfile">更改资料</a></li>
                    <li class="profile_tab_line">|</li>
                    <li><a href="${pageContext.request.contextPath}/user/changeAvatar">更改头像</a></li>
                    <li class="profile_tab_line">|</li>
                    <li><a href="${pageContext.request.contextPath}/user/passwordSafe">密码安全</a></li>
                </ul>
                <div class="proflle_tab_body">
                    <h3><a href="${pageContext.request.contextPath}/user/showMyProfile">返回个人中心</a></h3>
                    <div class="proflle_tab_workplace clearfix">
                        <div class="profile_avatar_area">
                            <img width="180px" height="180px"  src="${user.imgurl}">
                        </div>
                        <div class="profile_ifo_area">
                            <!--http://localhost/video/user/changeProfile-->
                            <form action="${pageContext.request.contextPath}/user/updateUser">
                                <input name="id" type="hidden" value="${user.id}">
                                <div class="form_group">
                                    <span class="dd">昵&#x3000;称：</span><input type="text" name="nickname" value="${user.nickname}" >
                                </div>
                                <div class="form_group">
                                    <span class="dd">性&#x3000;别：</span>
                                    <input type="radio" id="man" value="man" name="sex"><label for="man">男</label>
                                    <input type="radio" id="woman" value="woman" name="sex"><label for="woman">女</label>
                                </div>
                                <div class="form_group">
                                    <span class="dd">生&#x3000;日：</span>  <!-- 1990-10-04 -->
                                    <input type="text"  name="birthday" value="${user.birthday}">
                                </div>
                                <div class="form_group">
                                    <span class="dd">邮箱号码：</span>
                                    <span >${user.email}</span>
                                </div>
                                <div class="form_group">
                                    <span class="dd">所在地：</span>
                                    <div id="city">
                                        <select class="prov" name="prov"></select>
                                        <select class="city" disabled="disabled" name="city"></select>
                                    </div>
                                    <input type="hidden" name="address" id="address"/>
                                </div>
                                <div class="form_submit dd">
                                    <input type="submit" onclick="return commitForm();"  value="保&#x3000;存">
                                    <a href="${pageContext.request.contextPath}/user/changeProfile">重置</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <div class="container">
            <ul>
                <li><img src="${pageContext.request.contextPath}/img/xuesi_foot.png" alt="" id="foot_logo"></li>
                <li>版权所有：学思网教育&#x3000;&#x3000;&#x3000;&copy;&nbsp;www.chengjian100.com</li>
            </ul>
        </div>
    </footer>
</body>

<script src="${pageContext.request.contextPath}/js/jquery.cityselect.min.js"></script>

</html>