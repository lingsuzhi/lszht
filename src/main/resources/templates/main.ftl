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
.layui-container{
    width: 1024px;
}

</style>
<body>


</body>
<script>
    $(function () {
//       $(".lszbtn").click(function(){
//           $(this).addClass("nBtn");
//           console.log(1)
//       }) ;
        $("#test").click(function () {
            $("#test2").addClass("nBtn");
        });
        $("#test3").click(function () {
            $("#test2").removeClass("nBtn");
        });
    });

</script>
<script>

    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#dayDate' //指定元素
            ,
            value: new Date()
        });
    });
</script>
</html>