<#assign ctx=request.getContextPath()>
</br>
<link rel="stylesheet" href="${ctx}/plugins/layui/css/layui.css" media="all">
<script type="text/javascript" src="${ctx}/plugins/layui/layui.all.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>

<script type="text/javascript" charset="utf-8"        src="${ctx}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"        src="${ctx}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"       src="${ctx}/ueditor/lang/zh-cn/zh-cn.js"></script>

<form class="layui-form" action="" id="linkForm"
      style="margin-right: 15px;">
    <input type="hidden" name="id" value="${(obj.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" required dataEvent="nonull" placeholder="请输入" class="layui-input"
                   value="${(obj.title)!}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" placeholder="请输入" class="layui-input" value="${(obj.description)!}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="true" title="男" checked> <input
                type="radio" name="sex" value="false" title="女" <#if obj?? && !obj.sex>checked</#if>>
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄</label>
        <div class="layui-input-block">
            <input type="text" name="age" required dataEvent="nonull"
                   placeholder="请输入" class="layui-input" value="${(obj.age)!}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">病种</label>
        <div class="layui-input-block">
            <select name="diseasescode" id="selectId1">
                <option value=""></option>


            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">医师</label>
        <div class="layui-input-block">
            <input type="text" name="docname" placeholder="请输入"
                   class="layui-input" value="${(obj.docname)!}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">回答 </label>
        <div class="layui-input-block">
            <script id="editor" type="text/plain"        style="width: 1024px; height: 500px;">
            ${(obj.answer)!}
            </script>
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
    layui.use('form', function () {
        var form = layui.form;
        form.render();
    });
            var ue = UE.getEditor('editor');
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
            data1.answer = UE.getEditor('editor').getContent();

            data1 = JSON.stringify(data1);

            $.ajax({
                type: "POST",
                data: data1,
                url: "topicSave.php",
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
                        //    parent.$('#ssBtn').click();

                            closeWindo();
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

