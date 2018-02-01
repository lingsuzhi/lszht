<#assign ctx=request.getContextPath()>
</br>
<link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css" media="all">
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>
<form class="layui-form" action="" id="linkForm"
      style="margin-right: 15px;">
    <input type="hidden" name="id" value="${(obj.id)!}">
 <div class="layui-form-item"><label class="layui-form-label">日期</label><div class="layui-input-block"> <input type="text" name="daydate"   class="layui-input" value="${(obj.daydate)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">单据类型id</label><div class="layui-input-block"> <input type="text" name="orderSetId"   class="layui-input" value="${(obj.orderSetId)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">网点id</label><div class="layui-input-block"> <input type="text" name="customerId"   class="layui-input" value="${(obj.customerId)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">天流水号</label><div class="layui-input-block"> <input type="text" name="dayNumber"   class="layui-input" value="${(obj.dayNumber)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">流水号</label><div class="layui-input-block"> <input type="text" name="allNumber"   class="layui-input" value="${(obj.allNumber)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">总数量</label><div class="layui-input-block"> <input type="text" name="zsl"   class="layui-input" value="${(obj.zsl)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">总金额</label><div class="layui-input-block"> <input type="text" name="zje"   class="layui-input" value="${(obj.zje)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">实收金额</label><div class="layui-input-block"> <input type="text" name="shishouje"   class="layui-input" value="${(obj.shishouje)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">调度id</label><div class="layui-input-block"> <input type="text" name="dispatchId"   class="layui-input" value="${(obj.dispatchId)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">调度次数</label><div class="layui-input-block"> <input type="text" name="dispatchNumber"   class="layui-input" value="${(obj.dispatchNumber)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">用户id</label><div class="layui-input-block"> <input type="text" name="userId"   class="layui-input" value="${(obj.userId)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">管理员id</label><div class="layui-input-block"> <input type="text" name="adminId"   class="layui-input" value="${(obj.adminId)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">负责人</label><div class="layui-input-block"> <input type="text" name="fuzeren"   class="layui-input" value="${(obj.fuzeren)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">经办人</label><div class="layui-input-block"> <input type="text" name="jingbanren"   class="layui-input" value="${(obj.jingbanren)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">备注</label><div class="layui-input-block"> <input type="text" name="remake"   class="layui-input" value="${(obj.remake)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">保留字段</label><div class="layui-input-block"> <input type="text" name="zzz1"   class="layui-input" value="${(obj.zzz1)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">保留字段2</label><div class="layui-input-block"> <input type="text" name="zzz2"   class="layui-input" value="${(obj.zzz2)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">折扣</label><div class="layui-input-block"> <input type="text" name="discount"   class="layui-input" value="${(obj.discount)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">数据</label><div class="layui-input-block"> <input type="text" name="datas"   class="layui-input" value="${(obj.datas)!}"></div></div>

    
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="submitBtn">提 交</button>
            <button type="button" class="layui-btn layui-btn-primary"
                    id="colseBtn">关 闭
            </button>
        </div>
    </div>

</form>

<script>
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
    function closeWindo() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }
    function checkDo() {
        var texts = $("#linkForm *");
        if (texts.length > 0) {
            for (var i = 0; i < texts.length; i++) {
                var textbox = texts.eq(i);
                var event = textbox.attr("dataEvent");
                if (event == "nonull") {
                    if (textbox.val() == '') {
                        layer.msg("验证错误，请填写完毕", {shade: 0.3, icon: 2, time: 1000});
                        textbox.select();
                        return false;
                    }
                }
            }
        }
        return true;
    }
    $("#submitBtn").click(function () {

        if (checkDo()) {
            var data1 = JSON.parse($("#linkForm").serializeJson());
            data1 = JSON.stringify(data1);

            $.ajax({
                type: "POST",
                data: data1,
                url: "orderSave.php",
                async: true,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {

                    if (data.code == '2000') {
                        layer.msg("操作成功", {
                            shade: 0.3,
                            icon: 1,
                            time: 1500
                        });
                        setTimeout(function () {

                            closeWindo();
                            parent.$('#ssBtn').click();

                        }, 1500);

                    } else {
                        layer.msg(data.message, {
                            icon: 5
                        });
                    }

                }
            });

        }
    });
    $("#colseBtn").click(function () {
        closeWindo();
    });
</script>
