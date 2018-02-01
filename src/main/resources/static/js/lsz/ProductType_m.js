	var table = layui.table;
	function delRequest(ids){
		if (ids){
			var strArr=ids.split(",");
			for(var i in strArr){
				var str = strArr[i];
				$.get("productTypeDel.php" + "?id=" + str);
				
			}
			
		}
		$("#ssBtn").click();
	}

    function checkDo(){
        var texts =  $("#linkForm *");
        if(texts.length>0){
            for(var i  = 0;i<texts.length;i++  ){
                var textbox =  texts.eq(i);
                var event = textbox.attr("dataEvent");
                if(event == "nonull"){
                    if(textbox.val()==''){
                        layer.msg("验证错误，请填写完毕", {  shade: 0.3, icon: 2, time: 1000});
                        textbox.select();
                        return false;
                    }
                }
            }
        }
        return true;
    }
	 function editRequest(id){
		var uri  = "productTypeEdit.php";
		if (id){
			uri += "?id=" + id;
		}
  
            layer.open({
                type: 2
                , area: ['90%','90%']
                , shade: 0//不显示遮罩
                , title: '热点问题'
                // , offset: 1 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + 1 //防止重复弹出
                , content: uri
                //, btn: ['确定','关闭']
                //, btnAlign: 'r' //按钮居中
//                ,yes:function( ){
//                	
//                }
 
            });
 
	}
$(function() {
	$("#addBtn").click(function(){
		editRequest();
	});
   $("#ssBtn").click(function(){
		table.reload('ztable', {
			where : {
				name : $('#sname').val()
				,  parentId : ""
			}
		});
       doEvent();
   });
   
	$("#delbtn").click(function() {
		var data = layui.table.checkStatus('ztable').data;
		var ids = '';
		for (var i = 0; i < data.length; i++) {
			if (i > 0)
				ids += ",";
			ids += data[i].id;
		}
		if (ids) {
			layer.confirm('确认要删除吗？', function(index) {
				delRequest(ids);
				console.log(ids);
				layer.close(index);
			});
		}

	});
})