	var table = layui.table;
	function delRequest(ids){
		if (ids){
			var strArr=ids.split(",");
			for(var i in strArr){
				var str = strArr[i];
				$.get("topicDel.php" + "?id=" + str);
				
			}
			
		}
		$("#ssBtn").click();
	}


	function editRequest(id){
		var uri  = "topicEdit.php";
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
				,diseasescode : $('#diseasescode').val()
			}
		});
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