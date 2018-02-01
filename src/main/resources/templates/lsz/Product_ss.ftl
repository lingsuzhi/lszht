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

        .layui-table th {
            text-align: center;
        }

    </style>
</head>
<body>
<div class="layui-from">
    <div class="layui-colla-item">
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


                    </div>
                    <table class="layui-hide" id="ztable" lay-filter="useruv"></table>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>

    <script type="text/html" id="opeartion">
        <div style="position:relative;margin:0 0; ">
            <div style="position:absolute;left:0px; "><input type="text"   style="width: 66px;height: 24px;text-align:center;"  value="1"></div>
            <div style="position:absolute;left:48px; "><i class="layui-icon" onclick="adddo(this)" style="color:#009688; cursor: pointer;">&#xe61f;</i></div>
            <div style="position:absolute;left:72px; ">
                <button class="layui-btn layui-btn-primary layui-btn-xs"    lay-event="del">确定</button>
                <input type="hidden"  value="{{d.id}}">
            </div>
        </div>
      </script>
    <script>
        function adddo(t){
           var i = $(t).parent().parent().parent().find("input[type='text']") ;
           var val =  parseInt(i.val());
           val++;
           i.val(val);
            return false;
        }
        var table = layui.table;

            layui.use('table', function () {

            table.render({
                elem: '#ztable',
                url: 'productList.php',

                width: '100%',
                cols: [[

                    {field: 'number', title: '编号'},
                    {field: 'name', title: '名称', width: 222},
                    {field: 'spec', title: '规格', width: 155},
                    {field: 'proce', title: '价格'},
                    {field: 'company', title: '单位', width: 66},
                    {field: 'stock', title: '库存'},
                    {toolbar : '#opeartion', title: '操作', width: 150},


                ]],
                page: true,
                height: 500,

                cellMinWidth: 80
            });
        });

        $("#ssBtn").click(function(){
            table.reload('ztable', {
                where : {
                    name : $('#sname').val()
                  ,  typeId:""
                }

            });
            doEvent();
        });
        //监听工具条
        table.on('tool(useruv)', function(obj){
            var data = obj.data;
          if(obj.event === 'del') {


              var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
              if(index){
                  //如果是弹窗
                  var tr = $(event.target).closest("tr");
                  var id = tr.find("input[type='hidden']").val();
                  var sl = tr.find("input[type='text']").val();
                  if(sl && sl>0){

                  }else{
                      sl = '1';
                  }
                  var data1 ={shuliang:sl,
                  id:id
                  };
                  data1.number = data.number;
                  data1.name= data.name;
                  data1.spec= data.spec;
                  data1.company= data.company;
                  data1.proce= data.proce;
                  data1.manufacturer= data.manufacturer;
                  data1.barcode= data.barcode;


               var doStr =    JSON.stringify(data1);

                  parent.$('#parentDo').val(doStr).click();
                  obj.del();
                  //  parent.layer.close(index);
              }
          }
        });
                    layui.tree({
            elem: '#tree1' //传入元素选择器
            ,nodes: ${tree!}
            ,click: function(node){

                var pid = node.productType.id;
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

        function doEvent(){
            $("tbody").dblclick(function(){
                var tr = $(event.target).closest("tr");
                tr.find("button").click();


            });

        }
        doEvent();
    </script>


</body>
</html>