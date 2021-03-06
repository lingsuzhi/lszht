﻿<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/plugins/font-awesome/css/font-awesome.min.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/src/css/app.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/src/css/themes/default.css" media="all" id="skin" kit-skin/>
</head>

<body class="kit-theme">
<div class="layui-layout layui-layout-admin kit-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">后台管理系统</div>
        <div class="layui-logo kit-logo-mobile">K</div>
        <ul class="layui-nav layui-layout-left kit-nav">
        <#--<li class="layui-nav-item"><a href="javascript:;">控制台</a></li>-->
            <#--<li class="layui-nav-item"><a href="javascript:;">商品管理</a></li>-->
        </ul>
        <ul class="layui-nav layui-layout-right kit-nav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <i class="layui-icon">&#xe63f;</i> 皮肤
                </a>
                <dl class="layui-nav-child skin">
                    <dd><a href="javascript:;" data-skin="default" style="color:#393D49;"><i
                            class="layui-icon">&#xe658;</i> 默认</a></dd>
                    <dd><a href="javascript:;" data-skin="orange" style="color:#ff6700;"><i
                            class="layui-icon">&#xe658;</i> 橘子橙</a></dd>
                    <dd><a href="javascript:;" data-skin="pink" style="color:#FA6086;"><i
                            class="layui-icon">&#xe658;</i> 少女粉</a></dd>
                    <dd><a href="javascript:;" data-skin="blue.1" style="color:#00c0ef;"><i
                            class="layui-icon">&#xe658;</i> 天空蓝</a></dd>
                    <dd><a href="javascript:;" data-skin="red" style="color:#dd4b39;"><i class="layui-icon">&#xe658;</i>
                        枫叶红</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${ctx}/images/6.png" style="width: 32px;height: 32px" class="layui-nav-img"> lsz
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" kit-target
                           data-options="{url:'basic.html',icon:'&#xe658;',title:'基本资料',id:'966'}"><span>基本资料</span></a>
                    </dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black kit-side">
        <div class="layui-side-scroll">
            <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>

            <#if menuNodes?? && (menuNodes?size > 0) >
                <#list menuNodes as menuNode>
                    <#assign menu=menuNode.menu>
                    <#assign childNode = menuNode.childNode>
                        <#if menu?? && menu.type=="2"><!-- 如果1级 并且 是菜单类型 -->
                    </#if>
                        <#if menu?? && menu.type=="1"><!-- 如果1级 并且 是目录类型 -->
                        <li class="layui-nav-item <#if menuNode_index==0> layui-nav-itemed</#if>">
                            <a class="" href="javascript:;"><i class="layui-icon">&#xe654;</i>   <span> ${menu.menuName}</span></a>

                            <#if childNode?? && (childNode?size > 0)>
                                <dl class="layui-nav-child">
                                    <#list childNode as node>
                                        <#assign childMenu = node.menu>
                                        <#if childMenu?? && childMenu.status==true && childMenu.type=="2">
                                            <dd>
                                                <a href="javascript:;" data-url="${ctx}${childMenu.menuUrl}" data-id='${childMenu.id}'
                                                   data-title="${childMenu.menuName}" kit-target
                                                   data-id='2'><i class="layui-icon">&#xe600;</i><span> ${childMenu.menuName}</span></a>
                                            </dd>
                                        </#if>
                                        <#if childMenu??  && childMenu.status==true && childMenu.type=="1">
                                            <dd><!-- 如果2级 并且 是目录类型 -->
                                                <div class="main_inav_add_t">
                                                    <div class="yuan fl"></div>${childMenu.menuName}</div>
                                                <#assign child2Nodes = node.childNode>
                                                <#if child2Nodes?? && (child2Nodes?size > 0)>
                                                    <div class="main_inav_add_d">
                                                        <#list child2Nodes as child2Node>
                                                            <#assign child2Menu = child2Node.menu>
                                                            <#if child2Menu?? && child2Menu.status==true && child2Menu.type=="2">

                                                            </#if>
                                                        </#list>
                                                    </div>
                                                </#if>
                                            </dd>
                                        </#if>
                                    </#list>
                                </dl>
                            </#if>
                        </li>
                    </#if>
                </#list>
            </#if>

            </ul>
        </div>
    </div>
    <div class="layui-body" id="container">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;"><i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop">&#xe63e;</i>
            请稍等...
        </div>
    </div>


</div>
<!-- <script type="text/javascript">
    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
</script> -->
<script src="${ctx}/plugins/layui/layui.js"></script>
<script>
    var message;
    layui.config({
        base: 'src/js/',
        version: '1.0.1'
    }).use(['app', 'message'], function () {
        var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;
        //主入口
        app.set({
            type: 'iframe'
        }).init();

        $('dl.skin > dd').on('click', function () {
            var $that = $(this);
            var skin = $that.children('a').data('skin');
            switchSkin(skin);
        });
        var setSkin = function (value) {
                    layui.data('kit_skin', {
                        key: 'skin',
                        value: value
                    });
                },
                getSkinName = function () {
                    return layui.data('kit_skin').skin;
                },
                switchSkin = function (value) {
                    var _target = $('link[kit-skin]')[0];
                    _target.href = _target.href.substring(0, _target.href.lastIndexOf('/') + 1) + value + _target.href.substring(_target.href.lastIndexOf('.'));
                    setSkin(value);
                },
                initSkin = function () {
                    var skin = getSkinName();
                    switchSkin(skin === undefined ? 'default' : skin);
                }();
    });
</script>
</body>

</html>