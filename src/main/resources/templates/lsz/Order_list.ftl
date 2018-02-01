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
                      class="layui-badge-rim layui-bg-green">单据</span>列表
        </p>
        <div class="layui-colla-content layui-show">
            单号
            <div class="layui-inline">
                <input class="layui-input" name="danhao" id="danhao"
                       style="height: 30px;" autocomplete="off">
            </div>
            类型
            <div class="layui-inline">
                <input class="layui-input" name="danhao" id="danhao"
                       style="height: 30px;" autocomplete="off">
            </div>
            网点
            <div class="layui-inline">
                <input class="layui-input" name="danhao" id="danhao"
                       style="height: 30px;" autocomplete="off">
            </div>
            负责人
            <div class="layui-inline">
                <input class="layui-input" name="danhao" id="danhao"
                       style="height: 30px;" autocomplete="off">
            </div>
            <button class="layui-btn  layui-btn-sm    layui-btn-normal"
                    data-type="reload" id="ssBtn">
                <i class="layui-icon">&#xe615;</i>搜 索
            </button>

            <span style="float: right">
				</span>
        </div>
        <table class="layui-hide" id="ztable"></table>
    </div>

</div>
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>
  
<script type="text/html" id="opeartion">
 <input type="hidden"  value="{{d.id}}">
    <button class="layui-btn layui-btn-primary layui-btn-xs" onclick="editRequest('{{d.id}}')">修改</button>
</script>
 	 
	<script type="text/javascript"
		src="${ctx}/js/lsz/Order_m.js"></script>
	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#ztable',
				url : 'orderList.php',
				cols : [ [


                    {templet : '<div>{{ layui.laytpl.toDateString(d.daydate,"yyyy-MM-dd") }}</div>',title : '日期',width:110 },
                    {field : 'danhao', title : '单号',width:188 },
{field : 'customerName', title : '网点',width:222},
        {field : 'orderSetName', title : '单据类型' },
{field : 'allNumber', title : '流水号' },
{field : 'zsl', title : '数量',width:100 },
{field : 'zje', title : '金额',width:100 },
{field : 'shishouje', title : '实收金额' },
{field : 'dispatchDan', title : '调度单' },
{field : 'fuzeren', title : '负责人' },
{field : 'remake', title : '备注' },

				] ],
				page : true,
				height : 500,

				cellMinWidth : 80
			});
		});
	</script>

</body>
</html>