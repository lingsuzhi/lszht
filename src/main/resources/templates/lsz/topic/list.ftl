<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css" media="all">
    <style type="text/css">
        body {
            overflow-y: scroll;
        }
         .layui-table th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="layui-from">
    <div class="layui-colla-item">
        <p class="layui-colla-title">
				<span id="menutreeclickdetailid"
                      class="layui-badge-rim layui-bg-green">热门话题</span>列表
        </p>
        <div class="layui-colla-content layui-show">
            名称
            <div class="layui-inline">
                <input class="layui-input" name="sname" id="sname"
                       style="height: 30px;" autocomplete="off">
            </div>
            &nbsp;病种
            <div class="layui-inline">
                <select name="diseasescode" style="Width: 150px; height: 30px;"
                        id="diseasescode" lay-verify="required" class="layui-input">
                    <option value=""></option>
                <#if topicDiseases?? && ( topicDiseases?size gt 0 )>
                    <#list  topicDiseases as obj>
                        <option value="${(obj.id)!}"> ${(obj.name)!}</option>
                    </#list>
                </#if>

                </select>
            </div>

            <button class="layui-btn  layui-btn-sm    layui-btn-normal"
                    data-type="reload" id="ssBtn">
                <i class="layui-icon">&#xe615;</i>搜 索
            </button>

            <span style="float: right"> <a id="delbtn"
                                           class="layui-btn layui-btn-sm"><i class="layui-icon">&#xe640;</i>删除</a>
					<a id="addBtn" class="layui-btn layui-btn-sm"><i
                            class="layui-icon">&#xe608;</i>新增</a> &nbsp;
				</span>
        </div>
        <table class="layui-hide" id="ztable"></table>
    </div>

</div>
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/html" id="yyyymmdd">
    {{#

    var fn = function(a){
    if(a){
    var date = new Date(a);
    return date.toLocaleDateString();
    }
    return '';
    };
    }}

    {{ fn(d.birthday) }}

</script>
<script type="text/html" id="opeartion">
    <button class="layui-btn layui-btn-primary layui-btn-xs" onclick="editRequest('{{d.id}}')">修改</button>
</script>
<script type="text/html" id="t_sex">
    {{#
    var fn = function(a){
    if(a==1){
    return         '<span style="color: #333;">男</span>';
    }
    return  '<span style="color: #F581B1;">女</span>';
    };
    }}

    {{ fn(d.sex) }}
</script>
<script type="text/javascript" src="${ctx}/js/lsz/Topic_m.js"></script>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#ztable',
            url: 'topicList.php',
            cols: [[{
                type: 'checkbox'
            }, {
                field: 'title',
                title: '名称',
                sort: true,
                minWidth: 150
            }, {
                field: 'description',
                title: '问题描述'
                //	}, {
                //		field : 'userid',
                //		title : '客户'
            }, {
                templet: '#t_sex',
                title: '性别',
                align: 'center'
            }, {
                field: 'age',
                title: '年龄'
            }, {
                field: 'diseasesName',
                title: '病种名',
                sort: true
            }, {
                field: 'docname',
                title: '药师'
            }, {
                field: 'answer',
                title: '回答'
            }, {
                templet: '#opeartion',
                title: '操作'
            }

            ]],
            page: true,
            height: 500,

            cellMinWidth: 80
        });
    });
</script>

</body>
</html>