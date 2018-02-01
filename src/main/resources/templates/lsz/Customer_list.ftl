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

    <style type="text/css">

    .layui-table-cell{
    height:56px;
    }
    .layui-table-header{
    height:34px;
    }
    </style>
    <script type="text/html" id="userimg">
        {{#

        var fn = function(a){
        if(a){
        return  '<img src="${ctx}' + a + '" style="height: 55px;">';
        }
        return  '<img src="${ctx}/images/zanwu.png" style="height: 55px;">'
        };
        }}

        {{ fn(d.image) }}
    </script>
</head>
<body>
<div class="layui-from">
    <div class="layui-colla-item">
        <p class="layui-colla-title">
				<span id="menutreeclickdetailid"
                      class="layui-badge-rim layui-bg-green">网点</span>列表
        </p>
        <div class="layui-container" style="margin-left: 0px;margin-right: 0px;width: 100%">

            <div class="layui-row">
                <div class="layui-col-xs2">
                    <ul id="tree1"></ul>
                </div>
                <div class="layui-col-xs10">
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
        </div>
</div>
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>
  
<script type="text/html" id="opeartion">
 <input type="hidden"  value="{{d.id}}">
    <button class="layui-btn layui-btn-primary layui-btn-xs" onclick="editRequest('{{d.id}}')">修改</button>
 <button class="layui-btn layui-btn-xs"  onclick="setimg('{{d.id}}')" >图片</button>
</script>
 	 
	<script type="text/javascript"
		src="${ctx}/js/lsz/Customer_m.js"></script>
	<script>
		layui.use('table', function() {
			var table = layui.table;

			table.render({
				elem : '#ztable',
				url : 'customerList.php',
				cols : [ [ {
					type : 'checkbox'
				} , 

{field : 'name', title : '名称' },
{field : 'description', title : '描述' },
                    {
                        templet : '#userimg',
                        title : '图片'
                    },

{field : 'typeName', title : '类别' },
{field : 'level', title : '等级' },
{field : 'phone', title : '电话' },
{field : 'email', title : '邮箱' },
{field : 'remake', title : '备注' },
{templet : '<div>{{  qyjy(d.status) }}</div>', title : '状态' ,width:66},
 
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
        layui.tree({
            elem: '#tree1' //传入元素选择器
            ,nodes: ${tree!}
            ,click: function(node){
                // console.log(node) //node即为当前点击的节点数据
                var pid = node.customerType.id;
                if ( pid){
                    table.reload('ztable', {
                        where : {
                            typeId : pid
                        ,name:""
                        }
                    });
                    doEvent();
                }

            }
        });
        function doEvent() {
            $("tbody").dblclick(function (e) {

                var tr = $(event.target).closest("tr");
                var input = tr.find("input[type='hidden']");
                var id = input.val();
                if (id) {
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    if (index) {
                        //如果是弹窗
                        parent.$('#customerId').val(id).click();
                        parent.layer.close(index);
                    }
                }


            });
        };
        doEvent();
	</script>
<input type="file" id="file" onchange="getFilePath()"style="filter:alpha(opacity=0);opacity:0;width: 0;height: 0;"/>
</body>
</html>