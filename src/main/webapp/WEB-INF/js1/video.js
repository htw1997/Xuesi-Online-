//封装播放视频的方法
function fun(path) {
    console.log('路径是：' + path)

    //播放一个视频  ../video/qe.mp4
    //需要弹框进行视频播放
    layui.use('layer', function () {
        //需要申明一个播放视频的标签
        var loadsrc = "<video  width='100%' height='100%'  controls='controls' autoplay='autoplay'>"  +
            "<source src="  + path + " type='video/mp4'></source>" +
            "</video>"
        var layer = layui.layer;
        layer.open({
            title: "视频预览",
            type: 1,
            area: ['90%', '90%'],
            content: loadsrc
        })
    })
}

layui.use(['table', 'upload', 'layer', 'laydate'], function () {
    //实例化layui模块 table-->数据表格模块  $ 表示我们的jquery模块
    var table = layui.table,
        upload = layui.upload,
        layer = layui.layer,
        laydate = layui.laydate,
        $ = layui.jquery;

    //执行一个 table 实例
    var tableIns = table.render({
        //绑定body里面的table标签-->id
        elem: '#videodemo',
        //table表格的高度
        height: 600,
        //请求后台的地址(获取video的视频信息)
        url: 'http://localhost:8080/xuesi/video/getAll',
        //表格的名称
        title: '用户表',
        //开启分页
        page: true,
        //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        toolbar: '#bar',
        //异步请求时 解析数据的方式
        parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.status, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.total, //解析数据长度
                "data": res.item //解析数据列表
            };
        },

        //生成table表头(手动编写)-->解析后台的数据
        cols: [
            [ //表头
                {
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                field: 'id',
                title: 'ID',
                sort: true,
                fixed: 'left'
            }, {
                field: 'title',
                title: '视频标题',
                align: 'center'
            }, {
                field: 'speakerId',
                title: '讲师',
                align: 'center'
            },{
                field: 'videoPath',
                title: '视频',
                width: 150,
                align: 'center',
                //   默认显示的应该是一个视频地址video/wanmeiguanxi.MP4
                //   自定义显示内容-->显示一个按钮-->点击以后触发播放视频的功能   fun("nini")
                //d 表示当前行数据  此处的d相当于一个video对象
                templet: function (d) {
                    var path = d.videoPath

                    return "<button class='layui-btn layui-btn-radius layui-btn-normal layui-btn-xs' onclick='fun(\"" + path + "\")'>播放</button>"
                }
            }, {
                field: 'videoDesc',
                title: '描述',
                edit: 'text',
                align: 'center'
            }, {
                field: 'videoSeconds',
                title: '时长',
                sort: true,
                align: 'center'
            }, {
                field: 'coverPath',
                title: '封面图',
                width: 150,
                align: 'center',
                templet: function (d) {
                    var path = d.coverPath;
                    //需要一个图片标签进行图片展示
                    return "<img src=../" + path + " style='width: 150px; height: 150px;'/>"
                }
            }, {
                field: 'status',
                title: '状态',
                align: 'center',
                templet: function (d) {
                    var stu = d.status;
                    if (stu == 1) {
                        return "<span class='layui-btn layui-btn-warm layui-btn-sm'>发布</span>"
                    } else {
                        return "<span class='layui-btn layui-btn-disabled layui-btn-sm'>下线</span>"
                    }
                }
            }, {
                field: 'playNum',
                title: '视频播放次数',
                align: 'center',
                sort: true,
            }, {
                field: 'likeCounts',
                title: '视频收藏人数',
                align: 'center',
                sort: true,
            },
                {
                field: 'createTime',
                title: '上传时间',
                align: 'center',
                templet: function (d) {

                    return showTime(d.createTime);
                }
            }, {
                fixed: 'right',
                width: 165,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]
    });


    //监听头工具栏事件
    table.on('toolbar(videotest)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id),
            //将选中的数据放在我们的data数组里面
            data = checkStatus.data; //获取选中的数据
        //需要从data数组中将所有的id获取出来
        //声明一个数组,来存放选中的数据的id
        var ids = new Array();
        //循环遍历我们的data数组
        for (var i in data) {

            ids[i] = data[i].id;
        }
        switch (obj.event) {
            case 'add':
                layer.open({
                    type: 2,
                    title: '上传视频',
                    area: ['60%', '60%'],
                    content: '../video/addVideo'

                })
                break;
            case 'delete':
                if (data.length === 0) {
                    layer.msg('请选择一行');
                } else {
                    //执行删除操作
                    layer.confirm("数据可贵! 是否确认删除?", function (index) {
                        //使用我们的ajax进行请求发送  异步的形式
                        //异步请求    //同步请求
                        //使用jauery的形式写ajax
                        $.ajax({
                            type: "post",
                            url: "http://localhost:8080/xuesi/video/del",
                            dataType: 'json',
                            data: {
                                //key     value
                                "ids": ids
                            },
                            traditional: true, //向后台传送数组的时候,必须申明为true
                            success: function (re) {
                                //如果成功了   后台应该返回一个成功的消息 让我们前端进行展示给用户
                                //如果失败了   同上
                                if (re.status == 200) {
                                    layer.alert(re.message, {
                                        icon: 1
                                    }, function (index) {
                                        layer.close(index)
                                        tabIns.reload()
                                    })
                                } else {
                                    layer.alert(re.message, {
                                        icon: 2
                                    }, function (index) {
                                        layer.close(index)
                                    })
                                }

                            }
                        })

                    })

                }
                break;
            case 'up':
                //批量上传数据
                //指定允许上传的文件类型
                upload.render({
                    elem: '#test3'
                    , url: '../video/upexcel' //改成您自己的上传接口
                    , accept: 'file' //普通文件
                    , exts: 'xlsx|xls'
                    , done: function (res) {
                        layer.msg(res.message);
                    }
                });
                break;
            case 'down':
                window.location.href = "http://localhost:8080/xuesi/video/down?filename=moban.xlsx"
                break;
        }
        ;
    });

    //监听单元格编辑
    table.on('edit(videotest)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        //update video set video_path="1"  where id="001"
        $.ajax({
            type: "post",
            url: "http://localhost:8080/xuesi/video/update",
            dataType: "json",
            data: {
                "vid": data.id,
                "field": field,
                "value": value
            },
            success: function (re) {
                layer.msg(re.message)
            }
        })

    });

    //模糊查询操作
    $("#re").on("click", function () {
        console.log($("#vidoId").val())
        console.log($("#videoDesc").val())
        //需要获取到输入框的值,然后请求后台,获取数据以后重新加载我们的数据表格
        table.reload('videodemo', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            url: "http://localhost:8080/xuesi/video/selectByLike",
            where: {//设置异步请求的参数
                "vid": $("#vidoId").val(),
                "videoDesc": $("#videoDesc").val()
            }
        })
    })

    //包含下拉框的多条件查询
    $("#sel").on("click", function () {
        // console.log($("#selStu").val())
        //如何获取到select选择框里面的值
        //向后台请求数据,刷新table
        table.reload('videodemo', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            url: "http://localhost:8080/xuesi/video/getAll",
            where: {//设置异步请求的参数
                "stu": $("#selStu").val(),
                "timerange": $("#timerange").val()
            }
        })

    })

    //时间的实例化
    //日期时间范围选择
    laydate.render({
        elem: '#timerange'
        , range: '~' //或 range: '~' 来自定义分割字符
    });


    //视频上传操作
    //选完文件后不自动上传
    upload.render({
        //绑定选择文件的按钮(id)
        elem: '#choosevideo'
        //向后台发起请求的地址
        , url: '../video/uploadvideo'
        //自动提交设置为false-->取消自动提交
        , auto: false
        , accept: 'video'
        //给上传的文件取名字  key(file)   -    value(视频文件)
        , field: 'file'
        //绑定我们上传按钮(id)
        , bindAction: '#uploadvideo'
        //上传成功以后的回调函数
        , done: function (res) {
            //上传了文件以后 ,需要文件的一些基本信息
            if (res.status == 200) {
                //需要将上传成功以后的文件名称放在我们的input输入框中
                $("#videoPath").val(res.message)
            } else {
                layer.msg("网络延迟,上传失败!")
            }
        }
    });

    //图片上传
    upload.render({
        //绑定选择文件的按钮(id)
        elem: '#chooseimg'
        //向后台发起请求的地址
        , url: '../video/uploadimg'
        //自动提交设置为false-->取消自动提交
        , auto: false
        //给上传的文件取名字  key(file)   -    value(视频文件)
        , field: 'file'
        //绑定我们上传按钮(id)
        , bindAction: '#uploadimg'
        //上传成功以后的回调函数
        , done: function (res) {
            //上传了文件以后 ,需要文件的一些基本信息
            if (res.status == 200) {
                //需要将上传成功以后的文件名称放在我们的input输入框中
                $("#coverPath").val(res.message)
            } else {
                layer.msg("网络延迟,上传失败!")
            }
        }
    });

    //提交表单内容
    $("#submit").on("click", function () {
        // var fs = new FormData(document.getElementById("form"))
        //将我们的文本信息序列化的同时序列化我们的file文件  'file': 文件
        $.ajax({
            type: 'post',
            url: '../video/addvideo',
            dataType: 'json',
            // contentType:false,
            // processData:false,
            data: $("#form").serialize(),
            success: function (res) {
                //在前端的控制台进行输出
                layer.alert(res.message, {
                    icon: 1
                }, function () {
                    //重新刷新我们的table表格数据
                    window.parent.location.reload()
                })
            }
        })
    })

    //监听行工具事件
    table.on('tool(videotest)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent ==='look'){
            layer.open({
                type: 2,
                title: '查看评论',
                area: ['100%', '100%'],
                content: '../pinglun/toPinglunView?vid=' + data.id
            })
        }else if (layEvent === 'edit') {
            //需要弹框加载一个编辑页面  -- editVideo.html--
            //保证编辑页面的输入框会自动地填充我们选中行的数据
            //需要通过当前行数据的id 取后台找当前行的数据并且返回一个编辑页面
            layer.open({
                type: 2,
                title: '编辑视频信息',
                area: ['60%', '60%'],
                content: '../video/getEdit?vid=' + data.id
            })
        } else if (layEvent === 'del') {
            //执行删除操作  -->得到删除数据的id     data.id
            layer.confirm('真的删除行么?', function (index) {
                //向服务端发送删除指令
                $.ajax({
                    type: 'post',
                    url: "../video/delById",
                    dataType: "json",
                    data: {
                        "id": data.id
                    },
                    success: function (re) {
                        console.log("我是靓仔")
                        if (re.status == 200) {
                            obj.del(); //删除对应行（tr）的DOM结构
                            layer.alert(re.message, {icon: 1})
                        } else {
                            layer.alert(re.message, {icon: 2})
                        }
                    }
                })
            });
        }
    });


    //提交编译以后的表单
    $("#subedit").on("click", function () {
        $.ajax({
            type: 'post',
            url: '../video/edit',
            dataType: 'json',
            data: $("#form").serialize(),
            success: function (res) {
                //在前端的控制台进行输出
                layer.alert(res.message, {
                    icon: 1
                }, function () {
                    //重新刷新我们的table表格数据
                    window.parent.location.reload()
                })
            }
        })
    })

    //监听单元格编辑
    table.on('look(videotest)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段
        //update video set video_path="1"  where id="001"
        $.ajax({
            type: "post",
            url: "http://localhost:8080/xuesi/pinglun/toPinglunView",
            dataType: "json",
            data: {
                "vid": data.id,
                "field": field,
                "value": value
            },
            success: function (re) {
                layer.msg(re.message)
            }
        })

    });



})

