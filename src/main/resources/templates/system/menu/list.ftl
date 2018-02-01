<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <script type="text/javascript">
        <#assign ctx=request.getContextPath()>
        var ctx = "${ctx}";
        var initPageLink = "${initPageLink}";
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ny_ht.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/demo.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/lib/layer/skin/default/layer.css">
    <style type="text/css">
        .ztree{border: none}
    </style>
<body>
<div class="container-fluid m_top_30 nycol_list">
    <form action="" method="post">
        <table class="ny_tab_t">
            <tr>
                <td>
                    <div class="nycon_l_t_btn text-right">
                        <div class="btn btn-md btn-info">
                            <a href="${ctx}/system/menu/edit.php">创建菜单</a>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="depart_l panel">
    <div id="treeDemo" class="ztree"></div>
</div>
    <form action="" method="post">
        <div class="nycon_list_b depart_r js_page_div">
        </div>
    </form>
<div class="clear"></div>

<#if allMenu?? && (allMenu?size > 0) >
<div hidden>
    <ul>
        <#list allMenu as menu>
            <#if menu.type !="3">
             <li class="menu_li" data-menu-id="${menu.id!}" data-menu-pid="${menu.parentId!}" data-menu-name="${menu.menuName!}" ></li>
            </#if>
        </#list>
    </ul>
</div>
</#if>
<script data-main="${ctx}/js/abc/system/menu/list.js?v=${.now?string("yyyyMMddhhmmssSSSS")}" src="${ctx}/js/require.js"></script>
</body>
</html>