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
                      class="layui-badge-rim layui-bg-green">接口消息</span>列表
        </p>
        <div class="layui-colla-content layui-show">
            名称
            <div class="layui-inline">
                <input class="layui-input" name="sname" id="sname"
                       style="height: 30px;" autocomplete="off">
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
<script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>
  
<script type="text/html" id="opeartion">
    <button class="layui-btn layui-btn-primary layui-btn-xs" onclick="editRequest('{{d.id}}')">修改</button>
</script>
 	 
	<script type="text/javascript"
		src="${ctx}/js/lsz/MessageFace_m.js"></script>
	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#ztable',
				url : 'messageFaceList.php',
				cols : [ [ {
					type : 'checkbox'
				} , 

{field : 'name', title : '名称' },
{field : 'userId', title : '' },
{field : 'content', title : '内容' },
{field : 'status', title : '已读' },
{field : 'endTime', title : '结束时间' },
{field : 'startTime', title : '开始时间' },
{templet : '<div>{{ layui.laytpl.toDateString(d.createTime,"yyyy-MM-dd") }}</div>',title : '时间'},
 
{
					templet : '#opeartion',
					title : '操作'
				}

				] ],
				page : true,
				height : 500,

				cellMinWidth : 80
			});
		});
	</script>

</body>
</html>