layui.use(['form', 'layer', 'jquery','carousel'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        carousel = layui.carousel,
        $ = layui.jquery;

    carousel.render({
        elem: '#test10'
        , width: '100%'
        , height: '100%'
        , interval: 5000
    });


//修改密码
    $("#subedit").on('click', function () {
        $.ajax({
            type: "post",
            url: "../home/editPassword",
            data: $("#form").serialize(),
            dataType: "json",
            success: function (re) {
                if (re.status == 200) {
                    layer.alert(re.message, {icon: 1}),
                    window.parent.location.reload()
                } else {
                    layer.alert(re.message, {icon: 2})
                    window.parent.location.reload()
                }

            }
        })

    })

//js判断两次密码是否一致
    $("#newPassword").blur(function () {

        var pass01 = $("#password").val();
        var pass02 = $("#newPassword").val();
        if (null == pass01 || "" == pass01 || null == pass02 || "" == pass02) {
            $("#passMsg").text("密码不能为空").css("color", "red");
            regIsCommitPsw = false;
        } else {
            if (pass01 != pass02) {
                regIsCommitPsw = false;

                $("#passMsg").text("两次密码输入不一致，请重新输入").css("color", "red");
            } else {
                regIsCommitPsw = true;
                $("#passMsg").text("密码相同，请继续注册");
            }
        }

    });
})