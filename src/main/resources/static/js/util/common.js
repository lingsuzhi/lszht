/**
 * Created by liuqi on 2017/6/1.
 */
;!function(window, $){
	
	/*$("a[href^='"+ctx+"']").click(function(){
		abc.block();
	});
	$("input[type='button'][value='查询']").click(function(){
		abc.block();
	});*/

    window.abc = {
    	
    	winscrollTop:function (offset){
    			return window.parent?($(window.parent.document).scrollTop()+offset)+'px':
    				($(document).scrollTop()+offset)+'px';
    	},	
    	
    	block:function(){
    		$.blockUI({ message: '<img src="'+ctx+'/images/loading1.gif" >',css: { top :abc.winscrollTop(300)},baseZ:100000});
    	},
    	unblock:function(){
    		$.unblockUI();
    	},
    		
        layerAlert :function(success, msg, go_url){
            if(success){
                if(go_url){
                    layer.alert(msg, {offset: abc.winscrollTop(200),icon: 1,closeBtn: 0}, function(){
                        window.location.href = go_url;
                    });
                }else{
                    layer.alert(msg, {offset: abc.winscrollTop(200),icon: 1,closeBtn: 0});
                }
            }else{
                layer.alert(msg, {offset: abc.winscrollTop(200),icon: 5,closeBtn: 0});
            }
        },

        layerAjaxConfirm : function(req_type, req_url, req_json, back_url){
            layer.confirm('确认操作？', {title:'操作提示',btn: ['确认','取消'], icon: 3, offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                    function(){
            	        abc.block();
                        $.ajax({
                            type: req_type,
                            url: req_url,
                            data: req_json,
                            async: true,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                            	abc.unblock();
                                if (data && data.code == "2000") {
                                    if(back_url){
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000},function(){
                                            window.location.href = back_url;
                                        });
                                    }else{
                                        layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000});
                                    }
                                } else {
                                    abc.layerAlert(false, '操作失败: '+data.message);
                                }
                            }
                        });
                    }
            );
        },
        
        //自定义显示内容ajax提交确认框
        ajaxConfirm: function(req_type, req_url, req_json, back_url,confirmMsg,okmsg,failmsg,sec){
        	var _sec=sec?sec:1000;
            layer.confirm(confirmMsg, {title:'操作提示',btn: ['确认','取消'], icon: 3,offset: abc.winscrollTop(200),closeBtn: 0,zIndex:90000},
                    function(){
            	        abc.block();
                        $.ajax({
                            type: req_type,
                            url: req_url,
                            data: req_json,
                            async: false,
                            contentType: "application/json",
                            dataType: "JSON",
                            success: function (data) {
                            	abc.unblock();
                                if (data && data.code == "2000") {
                                	layer.msg(okmsg, {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:_sec},function(){
                                		window.location.href = back_url;
                                	});
                                } else {
                                    abc.layerAlert(false, failmsg+":"+data.message);
                                }
                            }
                        });
                    }
            );
        },

        goPage : function(){
            var page = $('#_goPs').val();
            var url = $('#_goPage').attr("data-url");
            if (page.match("^[1-9][0-9]*$")) {
                window.location.href = url.replace("[:page]", page);
            } else {
                return;
            }
        },

        getCheckBoxIds : function(){
            var ids;
            $(".js_checkbox:checked").each(function(i){
                if(0==i){
                    ids = $(this).val();
                }else{
                    ids += (","+$(this).val());
                }
            });
            return ids;
        },


        ajaxPage: function(url){
            $.ajax({
                type: 'GET',
                url: url,
                data: '',
                async: false,
                contentType: "application/json",
                dataType: "JSON",
                success: function (data) {
                    $(".js-body-tr").empty().append(data.bodyHtml);
                    $(".js-page-tr").empty().append(data.pageHtml);
                    if($(".js_currLink").length > 0){
                        $(".js_currLink").val(data.currLink);
                    }
                }
            });
        },

        ajaxGoPage: function(){
            var page = $('#_goPs').val();
            var url = $('#_goPage').attr("data-url");
            if (page.match("^[1-9][0-9]*$")) {
                $.ajax({
                    type: 'GET',
                    url: url.replace("[:page]", page),
                    data: '',
                    async: false,
                    contentType: "application/json",
                    dataType: "JSON",
                    success: function (data) {
                        $(".js-body-tr").empty().append(data.bodyHtml);
                        $(".js-page-tr").empty().append(data.pageHtml);
                        if($(".js_currLink").length > 0){
                            $(".js_currLink").val(data.currLink);
                        }
                    }
                });
            } else {
                return;
            }
        }
    };

    //全选checkbox
    $(".js_selectAll").click(function(){
        if($(this).attr("data-check")=='true'){
            $(this).attr("data-check",false);
            $.each($(".js_checkbox"),function (){
                this.checked=false;
            });
        }else{
            $(this).attr("data-check",true);
            $.each($(".js_checkbox"),function (){
                this.checked="checked";
            });
        }
    });


}(window, jQuery);