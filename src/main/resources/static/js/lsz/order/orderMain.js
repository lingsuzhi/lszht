$(function () {

    $("#saveBtn").click(tijiao);
});
function tijiao() {
    //提交订单

    var data2 = JSON.parse($("#linkForm").serializeJson());
    data2.datas = JSON.stringify(data1);

    data2.orderSetId = $("#orderSetId").val();
    data2 = JSON.stringify(data2);

    $.ajax({
        type: "POST",
        data: data2,
        url: "orderMainSave.php",
        async: true,
        contentType: "application/json",
        dataType: "JSON",
        success: function (data) {

            if (data.code == '2000') {
                 
                gengxinDanj   (data.data.id);
                chadanZT();

                layer.msg("保存成功", {
                    shade: 0.3,
                    icon: 1,
                    time: 1100
                });

            } else {
                layer.msg(data.message, {
                    icon: 5
                });
            }

        }
    });

}
function changeSl() {
    //对数量修改
    if ($("#saveBtn").hasClass("nBtn"))   return false;
    var trs = $("tbody tr");

    for (var i = 0; i < trs.length; i++) {
        var tr = trs.eq(i);
        var slTxt = tr.find("input[type='text']");
        if (slTxt) {
            data1[i].shuliang = parseFloat(slTxt.val());
            var tmpje = data1[i].shuliang * data1[i].proce;

            if (data1[i].zongjine != tmpje) {

                tr.find(".spanje").html(tmpje);
                data1[i].zongjine = tmpje;
            }
        }
    }
    //  shuaxinbiaoge();
}
function adddo(t) {
    if ($("#saveBtn").hasClass("nBtn"))   return false;
    var i = $(t).parent().parent().parent().find("input[type='text']");
    var val = parseInt(i.val());
    if (val) {
        val++;
    } else {
        val = 1;
    }

    i.val(val);
    changeSl();
    return false;
}


var table = layui.table;
var data1;
function shuaxinbiaoge() {
    //刷新表格
    table.reload('ztable', {
        data: data1,
    });
}
function gengxinDanj(orderId) {
    // if (orderId) {
    //     var data = $.ajax({url: 'orderChildList.php?orderId=' + orderId, async: false});
    //     if (data) {
    //         data1 = data.responseJSON.data;
    //         shuaxinbiaoge();
    //
    //
    //
    //
    //     }
    //
    // }
    if (orderId) {
        var data = $.ajax({url: 'orderById.php?id=' + orderId, async: false});
        if (data) {
            tmpData = data.responseJSON.data;

            $("form input[name='danhao']").val(tmpData.danhao);
            //   $("form input[name='daydate']").val(new Date());
            layui.laydate.render({
                elem: '#daydate' //指定元素

                , value: new Date(tmpData.daydate)
            });
            $("form input[name='allNumber']").val(tmpData.allNumber);
            $("form input[name='fuzeren']").val(tmpData.fuzeren);
            $("form input[name='remake']").val(tmpData.remake);
            $("form input[name='id']").val(tmpData.id);

            $("form input[name='customerId']").val(tmpData.customerId);
            $("#customerName").val(tmpData.customerName);
var datas = tmpData.datas;
data1 =JSON.parse(datas);
            shuaxinbiaoge();
        }

    }

}


$("#zenghangBtn").click(zenghangDo);
$("#parentDo").click(function () {

    var str = $(this).val();
    if (str) {

        var obj = JSON.parse(str);
        if (obj.proce) {
            obj.zongjine = parseFloat(obj.proce) * parseFloat(obj.shuliang);
        }
        data1.push(obj);

        shuaxinbiaoge();
    }

});
function zenghangDo() {
    layer.open({
        type: 2
        , title: "双击选择"//不显示标题栏
        // ,closeBtn: false

        , area: ['90%', '90%']
        , shade: 0//不显示遮罩
        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
        , content: ctx + "/product/ssproduct.php"
        , success: function (layero) {

        }
    });
}
//网点选择
function customerShow() {
    if ($("#saveBtn").hasClass("nBtn"))   return false;

    layer.open({
        type: 2
        , title: "双击选择"//不显示标题栏
        // ,closeBtn: false

        , area: ['90%', '90%']
        , shade: 0//不显示遮罩
        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
        , content: ctx + "/customer/customerView.php"
        , success: function (layero) {

        }
    });
}
//监听工具条
table.on('tool(useruv)', function (obj) {
    if ($("#saveBtn").hasClass("nBtn"))   return false;
    var data = obj.data;
    if (obj.event === 'del') {
        killdata(data.number);

        obj.del();

    }
});
function killdata(number) {

    if (number) {
        for (var i = 0; i < data1.length; i++) {
            if (data1[i].number == number) {
                data1.splice(i, 1);
            }
        }
    }
}
function chadanZT() {
    //查单状态
    btnYes($("#xinzengBtn"));
    btnYes($("#editBtn"));
    btnYes($("#delBtn"));
    btnNo($("#saveBtn"));
    btnNo($("#cancelBtn"));
    btnYes($("#leftBtn"));
    btnYes($("#rightBtn"));
    btnNo($("#zenghangBtn"));
    btnYes($("#excelBtn"));
    btnYes($("#printBtn"));
    btnYes($("#settingBtn"));

}
function btnNo(btn) {
    if (btn.hasClass("nBtn")) {

    } else {
        btn.addClass("nBtn");
    }
}
function btnYes(btn) {
    if (btn.hasClass("nBtn")) {
        btn.removeClass("nBtn");
    } else {

    }
}
function gaidanZT() {
    //编辑状态
    btnNo($("#xinzengBtn"));
    btnNo($("#editBtn"));
    btnNo($("#delBtn"));
    btnYes($("#saveBtn"));
    btnYes($("#cancelBtn"));
    btnNo($("#leftBtn"));
    btnNo($("#rightBtn"));
    btnYes($("#zenghangBtn"));
    btnYes($("#excelBtn"));
    btnYes($("#printBtn"));
    btnYes($("#settingBtn"));

}
//新增
$("#xinzengBtn").click(function () {
    gaidanZT();
    $("form input[name='danhao']").val("");
    //   $("form input[name='daydate']").val(new Date());
    layui.laydate.render({
        elem: '#daydate' //指定元素

        , value: new Date()
    });
    $("form input[name='allNumber']").val("");
    $("form input[name='fuzeren']").val("");
    $("form input[name='remake']").val("");
    $("form input[name='id']").val("");

    data1 = new Array();
    shuaxinbiaoge();
})
$("#editBtn").click(function () {
    gaidanZT();
})
$("#cancelBtn").click(function () {
    location.reload();
})
$("#rightBtn").click(function () {
    $.get("orderRight.php" + "?id=" + $("#id").val() + "&orderSetId=" + $("#orderSetId").val(), function (data) {
        if (data.code == 2000) {
            if (data.data) {

                var id = data.data.id;
                gengxinDanj(id);

            }else{
                layer.msg("查询失败。。。", {  shade: 0.3, icon: 2, time: 1000});
            }

        } else {
            console.log(data.msg)

        }
    });
});
$("#leftBtn").click(function () {
    $.get("orderLeft.php" + "?id=" + $("#id").val() + "&orderSetId=" + $("#orderSetId").val(), function (data) {
        if (data.code == 2000) {
            if (data.data) {

                var id = data.data.id;
                gengxinDanj(id);

            }else{
                layer.msg("查询失败。。。", {  shade: 0.3, icon: 2, time: 1000});
            }

        } else {
            console.log(data.msg)

        }
    });
})
$("#delBtn").click(function () {
    var danhao = $("form input[name='danhao']").val();
    if (danhao) {
        layer.confirm('确认要删除吗？</br>' + "单号:" + danhao, function (index) {

            $.get("orderMainDelByDanhao.php" + "?danhao=" + danhao, function (data) {
                if (data.code == 2000) {
                    layer.msg("操作成功", {shade: 0.3, icon: 1, time: 1000});

                    $("#leftBtn").click();
                } else {
                    console.log(data.msg)
                }
            });
            layer.close(index);
        });
    } else {
        alert("单号错误，删除失败");
    }

})
$("#excelBtn").click(function(){
    var id = $("#id").val();
    if(id){
    //  alert(  layui.laytpl.toDateString (new Date()));
        var url =ctx + "/order/downfile/" + + layui.laytpl.toDateString (new Date(),"yyyy-MM-dd HH;mm;ss") + ".xls";// +"?orderId=" + id;
        var form = $("<form></form>").attr("action", url).attr("method", "get");
        form.append($("<input></input>").attr("type", "hidden").attr("name", "orderId").attr("value", id));
        form.appendTo('body').submit().remove();

    }

})

