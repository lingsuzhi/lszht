<script type="text/javascript"> <#assign ctx=request.getContextPath()> var ctx = "${ctx}";</script>
 
<table class="table table-hover" style="" cellspacing="0" cellpadding="0" border="0" width="100%">
    <thead class="pn-lthead">
    <tr>
 
        <th width="54">序号</th>
        <th>菜单名称</th>
        <th>上级菜单</th>
        <th>菜单URL</th>
        <th>授权标识</th>
        <th>类型</th>
        <th>状态</th>
        <th>排序号</th>
        <th>操作选项</th>
    </tr>
    </thead>
    <tbody class="js-body-tr pn-ltbody">
    <#if menus?? && (menus?size > 0) >
        <#list menus as menu>
        <tr>
 
            <td> </td>
            <td>${(menu.menuName)!}</td>
            <td>
                <#if (menu.parentId)! =="">--<#else>${(menu.parentName)!}</#if>
            </td>
            <td><#if (menu.type)! == "2" || (menu.type)! == "3">${(menu.menuUrl)!}<#else>--</#if></td>
            <td><#if (menu.type)! == "2" || (menu.type)! == "3">${(menu.perms)!}<#else>--</#if></td>
            <td>
                <#if (menu.type)! == "1">
                    <div class="btn btn-info btn-xs btn_nocursor">目录</div>
                <#elseif (menu.type)! == "2">
                    <div class="btn btn-success btn-xs btn_nocursor">菜单</div>
                <#elseif (menu.type)! == "3">
                    <div class="btn btn-warning btn-xs btn_nocursor">按钮</div>
                </#if>
            </td>
            <td class="js-div-status">
                <#if menu.status?? && menu.status>
                    <div class="btn btn-success btn-xs btn_nocursor">启用</div>
                <#else>
                    <div class="btn btn-danger btn-xs btn_nocursor">停用</div>
                </#if>
            </td>
            <td>${(menu.sort)!}</td>
            <td>
                <a data-href="${ctx}/system/menu/edit.php?id=${menu.id}" class="js_edit pn-opt">编辑</a> |
                <#if menu.status?? && menu.status>
                    <a data-href="${ctx}/system/menu/enable.php?id=${menu.id}&enable=false" class="js_enable pn-opt">停用</a>
                <#else>
                    <a data-href="${ctx}/system/menu/enable.php?id=${menu.id}&enable=true" class="js_enable pn-opt">启用</a> |
                    <a data-href="${ctx}/system/menu/delete/${menu.id}.php" class="js_delete pn-opt">删除</a>
                </#if>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
         <#include "../../common/paginationJs.ftl">