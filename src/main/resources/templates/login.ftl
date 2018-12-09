<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Login</title>
    <script>
        if (window != window.top) top.location.href = self.location.href;
    </script>
    <link href="${ctx}/plugins/layui/css/layui.css" rel="stylesheet" />
    <link href="${ctx}/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="${ctx}/build/css/login.css" rel="stylesheet" />
    <link href="${ctx}/plugins/sideshow/css/normalize.css" rel="stylesheet" />
    <link href="${ctx}/plugins/sideshow/css/demo.css" rel="stylesheet" />
    <link href="${ctx}/plugins/sideshow/css/component.css" rel="stylesheet" />
    <!--[if IE]>
    <script src="${ctx}/plugins/sideshow/js/html5.js"></script>
    <![endif]-->
    <style>
        canvas {
            position: absolute;
            z-index: -1;
        }

        .kit-login-box header h1 {
            line-height: normal;
        }

        .kit-login-box header {
            height: auto;
        }

        .content {
            position: relative;
        }

        .codrops-demos {
            position: absolute;
            bottom: 0;
            left: 40%;
            z-index: 10;
        }

        .codrops-demos a {
            border: 2px solid rgba(242, 242, 242, 0.41);
            color: rgba(255, 255, 255, 0.51);
        }

        .kit-pull-right button,
        .kit-login-main .layui-form-item input {
            background-color: transparent;
            color: white;
        }

        .kit-login-main .layui-form-item input::-webkit-input-placeholder {
            color: white;
        }

        .kit-login-main .layui-form-item input:-moz-placeholder {
            color: white;
        }

        .kit-login-main .layui-form-item input::-moz-placeholder {
            color: white;
        }

        .kit-login-main .layui-form-item input:-ms-input-placeholder {
            color: white;
        }

        .kit-pull-right button:hover {
            border-color: #009688;
            color: #009688
        }
    </style>
</head>


<body class="kit-login-bg">
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="kit-login-box">
                <header>
                    <h1>用户登录</h1>
                </header>
                <div class="kit-login-main">
                    <form action="${ctx}/api/loginDo.op" class="layui-form" method="post">
                        <div class="layui-form-item">
                            <label class="kit-login-icon">
                                <i class="layui-icon">&#xe612;</i>
                            </label>
                            <input type="text" name="username" lay-verify="required" autocomplete="off" value="admin"  class="layui-input">
                        </div>
                        <div class="layui-form-item">
                            <label class="kit-login-icon">
                                <i class="layui-icon">&#xe642;</i>
                            </label>
                            <input type="password" name="password" lay-verify="required" autocomplete="off" value="123456"  class="layui-input">
                        </div>
                        <#--<div class="layui-form-item">-->
                            <#--<label class="kit-login-icon">-->
                                <#--<i class="layui-icon">&#xe642;</i>-->
                            <#--</label>-->
                            <#--<input type="number" name="validCode" lay-verify="required|number" autocomplete="off" placeholder="输入右侧计算结果." class="layui-input">-->
                            <#--<span class="form-code" id="changeCode" style="position:absolute;right:2px; top:2px;">-->
                                    <#--<img src="${ctx}/build/images/GetVerfiyCode.png" id="refImg" style="cursor:pointer;" title="点击刷新"/>-->
                                <#--</span>-->
                        <#--</div>-->
                        <div style="color: #ff493d">${erro!}</div>
                        <div class="layui-form-item">
                            <div class="kit-pull-left kit-login-remember">
                                <input type="checkbox"   value="true" lay-skin="primary" checked title="记住帐号?">
                            </div>
                            <div class="kit-pull-right">
                                <button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                                    <i class="fa fa-sign-in" aria-hidden="true"></i> 登录
                                </button>
                            </div>
                            <div class="kit-clear"></div>
                        </div>
                    </form>
                </div>
                <footer>
                    <p>lsz © <a href="${ctx}/index" style="color:white; font-size:18px;" target="_blank">后台管理系统</a></p>
                </footer>
            </div>
        </div>
    </div>
</div>
<!-- /container -->

<script src="${ctx}/plugins/layui/layui.js"></script>
<script src="${ctx}/plugins/sideshow/js/TweenLite.min.js"></script>
<script src="${ctx}/plugins/sideshow/js/EasePack.min.js"></script>
<script src="${ctx}/plugins/sideshow/js/rAF.js"></script>
<script src="${ctx}/plugins/sideshow/js/demo-1.js"></script>
<script>
    layui.use(['layer', 'form'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        $('#changeCode').on('click', function() {
            $('#changeCode > img')[0].src = '${ctx}/build/images/GetVerfiyCode.png';
        });

        //清理左侧菜单缓存
        var index = layer.load(2, {
            shade: [0.3, '#333']
        });
        $(window).on('load', function() {
            layer.close(index);

        }());

    });
</script>
</body>

</html>