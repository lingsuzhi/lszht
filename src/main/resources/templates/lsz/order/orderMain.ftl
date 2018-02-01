<#assign ctx=request.getContextPath()>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css" media="all">

</head>
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>

<style>

    .lszbtn {
        display: inline-block;
        vertical-align: middle;
        width: 60px;
        line-height: 19px;
        padding: 15px 10px;
        margin-right: -3px;
        margin-bottom: -1px;
        font-size: 12px;
        text-align: center;
        color: #666;
        -webkit-transition: all .3s;
        cursor: pointer;
    }

    .lszbtn i {
        color: #009688;
    }

    .nBtn {
        pointer-events: none;
        cursor: not-allowed;
        box-shadow: none;
        opacity: .65;

    }

    .nBtn i {
        color: #dddddd;
    }

    .lszbtn i {
        font-size: 32px;
    }

    .lszbtn:hover {
        background-color: #f2f2f2;
        color: #000;
    }

    .layui-container {
        width: 1024px;
    }

    .layui-table th {
        text-align: center;
    }
</style>
<body>
<p class="layui-colla-title">
				<span id="menutreeclickdetailid"
                      class="layui-badge-rim layui-bg-green">${(orderSet.title)!}
                </span>
    <input type="hidden" id="orderSetId" value="${(orderSet.id)!}">
</p>
<input type="hidden" id="parentDo" value="">
<div class="layui-container">

    <div class="layui-row">
        <div class="layui-col-xs3">
            <div class="lszbtn" id="xinzengBtn">
                <i class="layui-icon">&#xe61f;</i>
                <div>新增</div>
            </div>
            <div class="lszbtn" id="editBtn">
                <i class="layui-icon">&#xe642;</i>
                <div>编辑</div>
            </div>

            <div class="lszbtn" id="delBtn">
                <i class="layui-icon">&#xe640;</i>
                <div>删除</div>
            </div>
        </div>

        <div class="layui-col-xs2">
            <div class="lszbtn" id="saveBtn">
                <i class="layui-icon">&#xe62a;</i>
                <div>保存</div>
            </div>

            <div class="lszbtn" id="cancelBtn">
                <i class="layui-icon">&#x1006;</i>
                <div>取消</div>
            </div>
        </div>
        <div class="layui-col-xs2">
            <div class="lszbtn" id="leftBtn">
                <i class="layui-icon">&#xe65a;</i>
                <div>前单</div>
            </div>

            <div class="lszbtn" id="rightBtn">
                <i class="layui-icon">&#xe65b;</i>
                <div>后单</div>
            </div>
        </div>
        <div class="layui-col-xs3">
            <div class="lszbtn" id="zenghangBtn">
                <i class="layui-icon">&#xe654;</i>
                <div>增行</div>
            </div>

            <div class="lszbtn" id="excelBtn">
                <i class="layui-icon">&#xe624;</i>
                <div>Excel</div>
            </div>
            <div class="lszbtn" id="printBtn">
                <i class="layui-icon">&#xe660;</i>
                <div>打印</div>
            </div>
        </div>
        <div class="layui-col-xs2" id="test2">
            <div class="lszbtn" id="settingBtn">
                <i class="layui-icon">&#xe614;</i>
                <div>设置</div>
            </div>

        <#--<div class="lszbtn" id="toolBtn">-->
        <#--<i class="layui-icon">&#xe631;</i>-->
        <#--<div>工具</div>-->
        <#--</div>-->
        </div>
    </div>
</div>


<div class="layui-container">
    <form class="layui-form" action="" id="linkForm"
          style="margin-right: 15px;">
        <div class="layui-row">

            <div class="layui-col-xs5">
                <div class="layui-form-item"><label class="layui-form-label">单号：</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="id" id="id">
                        <input type="text" readonly name="danhao" STYLE="    border: none;" class="layui-input" ">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs3">
                <div class="layui-form-item"><label class="layui-form-label">日期</label>
                    <div class="layui-input-block"><input type="text" name="daydate" id="daydate" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs3">
                <div class="layui-form-item"><label class="layui-form-label">流水号：</label>
                    <div class="layui-input-block"><input type="text" readonly name="allNumber"  STYLE="    border: none;" class="layui-input">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs5">
                <div class="layui-form-item"><label class="layui-form-label">客户</label>
                    <div class="layui-input-block">


                        <input type="hidden" name="customerId" id="customerId">

                        <div style="position:relative;margin:0 0; ">
                            <div style="position:absolute;left:0px; width: 100%;   ">
                                <input type="text" id="customerName" class="layui-input">
                            </div>
                            <div style="position:absolute;left:48px;   left: 88%;    top: 3px; ">
                                <i class="layui-icon" onclick="customerShow()" style=" vertical-align: top;    font-size: 28px; color:#666; cursor: pointer;">&#xe611;</i>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
            <div class="layui-col-xs3">
                <div class="layui-form-item"><label class="layui-form-label">负责人</label>
                    <div class="layui-input-block"><input type="text" name="fuzeren" class="layui-input"></div>
                </div>
            </div>
            <div class="layui-col-xs4">
                <div class="layui-form-item"><label class="layui-form-label">备注</label>
                    <div class="layui-input-block"><input type="text" name="remake" class="layui-input"></div>
                </div>
            </div>
        </div>
    </form>

    <table class="layui-hide" id="ztable" lay-filter="useruv"></table>
</div>


</body>

<script type="text/html" id="zongjine">
    <span class="spanje">{{d.zongjine?d.zongjine:0}}</span>
</script>

<script type="text/html" id="shuliang">

    <div style="position:relative;margin:0 0; ">

        <div style="position:absolute;left:0px; "><input type="text" onchange="changeSl()"
                                                         style="width: 66px;height: 24px;text-align:center;"
                                                         value="{{d.shuliang?d.shuliang:0}}"></div>
        <div style="position:absolute;left:48px; "><i class="layui-icon" onclick="adddo(this)"
                                                      style="color:#009688; cursor: pointer;">&#xe61f;</i></div>
    </div>
</script>
<script type="text/html" id="opeartion">

    <button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</button>
</script>
<script>
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#daydate' //指定元素


        });
    });
    layui.use('table', function () {

        layui.table.render({
            elem: '#ztable',


            cols: [[


                {field: 'number', title: '编号', width: 80},
                {field: 'name', title: '名称', width: 200},
                {field: 'spec', title: '规格', width: 200},
                {field: 'company', title: '单位', width: 66},
                {field: 'proce', title: '价格'},
                {templet: '#shuliang', title: '数量'},
                {templet: '#zongjine', title: '金额'},

                {field: 'barcode', title: '条形码'},
                {
                    toolbar: '#opeartion',
                    title: '操作',
                    align: 'center'
                }
            ]],
            page: false,
            height: 500,

        })
        ;
    });
    $(function () {

        gengxinDanj("${(orderBO.id)!}");
        chadanZT();
    })
    $("#customerId").click(function () {

        var id = $(this).val();
        if (id) {

            $.get("${ctx}/customer/customerById.php?id=" + id, function (data) {
                if (data.code == 2000) {
                    var bo = data.data;
                    $("#customerName").val(bo.name);
                }
            });
        }

    });
    var ctx = "${ctx}";
</script>
</html>

<script type="text/javascript" src="${ctx}/js/lsz/order/orderMain.js"></script>


