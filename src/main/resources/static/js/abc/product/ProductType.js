require(["../../config"], function () {
    require(["jquery.full", "../abc/util/pagination", "../abc/util/date"], function ($) {
    	
    	  var setting = {
                  view: {dblClickExpand: false,showLine: false},
                  data: {simpleData: {enable: true}},
                  callback: {
                      onClick: function(e,treeId, treeNode) {
                          var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                          zTree.expandNode(treeNode);
                          //点击给上级菜单栏位赋值；
                          if(treeNode.id == -1){
                              $("#parentName").val('');
                              $("[name='parentId']").val('');
                              $("[name='parentType']").val('');
                          }else{
                              $("#parentName").val(treeNode.name);
                              $("[name='parentId']").val(treeNode.id);
                              $("[name='parentType']").val(treeNode.type);
                          }
                      }
                  }
              };

              var zNodes = [{ id:-1, pId:-2, name:"所有数据", open:true}];

              var parentId = $("[name='parentId']").val();
              $(".menu_li").each(function(){
                  var obj = {};
                  obj.id= $(this).attr("data-menu-id");
                  obj.pId= $(this).attr("data-menu-pid");
                  obj.name= $(this).attr("data-menu-name");
                  obj.type= $(this).attr("data-menu-type");
                  if(obj.pId =="" || obj.pId =="null"){
                      obj.pId = -1;
                  }
                  obj.myAttr = $(this).attr("data-menu-id");
                  zNodes.push(obj);
                  //初始化为parentType栏位赋值
                  if(parentId == obj.id){
                      $("[name='parentType']").val(obj.type);
                  }
              });
              $.fn.zTree.init($("#treeDemo"), setting, zNodes);

              //点击上级菜单弹出树形结构层
              $("#parentName").click(function(){
                  $(".js_pop_menu").show();
              });
    	
              var $validatorFrom = $("form").validator({
                  theme: 'yellow_top',
                  timely: 1,
                  fields: {
                      "menuName": "required;length[2~32];",
                      "menuUrl": "length[~128];",
                      "perms": "length[~500];",
                      "icon": "length[~64];",
                      "sort":"integer;range[0~1000];"
                  }
              });
              $validatorFrom.validator().trigger("showtip");
              //点击取消 隐藏对话框
              $('body').on('click', '.js_close', function(){
                  $(".main_modal").hide();
              });
        $('#queryBtn').on("click", function () {

            window.location.href = ctx + "/product/productType.php";
        })

        $('#back').click(function () {
            window.location.href = ctx + "/product/productType.php";
        });
        $("a[data-type='delSig']").click(function () {
            abc.ajaxConfirm("POST", $(this).attr("data-href"), '', '', $(this).attr("data-confirm"), $(this).attr("data-okMsg"), $(this).attr("data-failMsg"));
        });
        $("#submit").on("click", function () {
            if ($("#linkForm").isValid()) {
                layer.confirm('是否保存？', {icon: 3, title: "操作提示", btn: ['确认', '取消'], offset: '200px', closeBtn: 0},
                    function (index) {
                        var data1 = JSON.parse($("#linkForm").serializeJson());
                           
 //
                        data1=JSON.stringify(data1);
                        $.ajax({
                            type: "POST",
                            data:  data1,
                            url: ctx + "/product/productTypeSave.php",
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                                if (data.code == '2000') {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200), shade: 0.3, icon: 1, time: 1000});
                                    setTimeout(function () {
                                        $('#back').click();
                                    }, 2000);
                                } else {
                                    layer.msg(data.message, {icon: 5});
                                }

                            }
                        });
                    }
                );
            }
        });
    });
})
