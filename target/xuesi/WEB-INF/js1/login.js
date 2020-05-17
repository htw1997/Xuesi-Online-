layui.use(['form', 'layer', 'jquery','carousel'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        carousel = layui.carousel,
         $ = layui.jquery;

    carousel.render({
        elem: '#test10'
        ,width: '100%'
        ,height: '100%'
        ,interval: 5000
    });

    //登录操作
    form.on('submit(login)', function (data) {
        //向后台发送请求 AJAX
        $.ajax({
            type: "post",
            url: "/xuesi/home/login",
            data: data.field,
            dataType: "json",
            success: function (re) {
                if (re.status == 200) {
                    //判定如果成功-->直接跳转到主页面
                    //location.href="${pageContext.request.contextPath}/video/list"
                    window.location.href = "http://localhost:8080/xuesi/home/returnmain"
                } else {
                    //如果失败-->打印失败的信息,继续留在登录界面
                    layer.alert(re.message, {icon: 2})
                }
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    //注册操作--获取注册页面
    $("#register").on("click", function () {
        //点击以后,需要弹出一个注册的页面
        layer.open({
            type: 2,
            title: '用户注册',
            area: ['30%', '40%'],
            content: 'http://localhost:8080/xuesi/home/returnRegister'
        })

    })

    //注册操作--获取手机短信
    $("#getTelCode").on('click', function () {
        $.ajax({
            type: "post",
            url: "/xuesi/home/getTelCode",
            data: {
                "tel": $("#tel").val()
            },
            dataType: "json",
            success: function (re) {
                layer.alert(re.message)
            }
        })

    })

    //注册操作--完成注册
    $("#subregister").on('click', function () {
        $.ajax({
            type: "post",
            url: "/xuesi/home/register",
            data: {
                    "tel": $("#tel").val(),
                "password":$("#password").val(),
                    "code": $("#code").val()
                },
            dataType: "json",
            success: function (re) {
                if(re.status == 200){
                    layer.alert(re.message, {icon: 1})
                }else{
                    layer.alert(re.message, {icon: 2})
                }

            }
        })

    })


    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
})


//需要编写一个点击验证码进行验证码切换的功能
function changeCode() {
    //先获取到img标签
    var img = document.getElementById("codeImg")
    //设置一个新的src地址 http://localhost:8080/
    //因为请求的地址是一样的,浏览器有可能会直接从缓存中将上一次获取到的数据返回回来
    img.src = "/xuesi/home/getCode?time=" + new Date().getTime()
}