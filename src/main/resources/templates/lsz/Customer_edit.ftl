<#assign ctx=request.getContextPath()>
</br>
<link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css" media="all">
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common/layuiUtil.js"></script>
<form class="layui-form" action="" id="linkForm"
      style="margin-right: 15px;">
    <input type="hidden" name="id" value="${(obj.id)!}">
 <div class="layui-form-item"><label class="layui-form-label">名称</label><div class="layui-input-block"> <input type="text" name="name"   class="layui-input" value="${(obj.name)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">描述</label><div class="layui-input-block"> <input type="text" name="description"   class="layui-input" value="${(obj.description)!}"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">类别</label>
        <div class="layui-input-block">


            <input type="hidden" name="typeId" id="parentDo" value="${(obj.typeId)!}">
            <input type="text" id="parentDoTxt" class="layui-input" value="${(obj.parentName)!}">

        </div>
    </div><div class="layui-form-item"><label class="layui-form-label">等级</label><div class="layui-input-block"> <input type="text" name="level"   class="layui-input" value="${(obj.level)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">电话</label><div class="layui-input-block"> <input type="text" name="phone"   class="layui-input" value="${(obj.phone)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">邮箱</label><div class="layui-input-block"> <input type="text" name="email"   class="layui-input" value="${(obj.email)!}"></div></div>
<div class="layui-form-item"><label class="layui-form-label">备注</label><div class="layui-input-block"> <input type="text" name="remake"   class="layui-input" value="${(obj.remake)!}"></div></div>
   <div class="layui-form-item">
<label class="layui-form-label">状态</label>
<div class="layui-input-block">
<input type="radio" name="status" value="true" title="启用" checked>
<input type="radio" name="status" value="false" title="禁用" <#if obj?? && obj.status??&&!obj.status>checked</#if>>
</div>
</div>

    
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
    $(function () {
        $("#parentDoTxt").click(function () {
            //弹窗
            layer.open({
                type: 2
                , title: "双击选择"//不显示标题栏
                // ,closeBtn: false

                , area: ['90%', '90%']
                , shade: 0//不显示遮罩
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , content:  "${ctx}/customerType/customerTypeView.php"
                , success: function (layero) {

                }
            });
        })
        $("#parentDo").click(function () {

            var id = $(this).val();
            if (id) {

                $.get("${ctx}/customerType/customerTypeById.php?id=" + id, function (data) {
                    if (data.code == 2000) {
                        var bo = data.data;
                        $("#parentDoTxt").val(bo.name);
                    }
                });
            }

        });
        $("#parentDo").click();
    });
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
                url: "customerSave.php",
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
