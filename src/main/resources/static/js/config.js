requirejs.config({
    baseUrl :ctx+"/js/lib",
    paths : {
        "jquery-3.1.0" : "../plugin/jquery-3.1.0",
        "owl.carousel" : "../plugin/owl.carousel",
        "bootstrap" : "../plugin/bootstrap",
        "jquery.validate" : "../plugin/jquery.validate",
        "cropper" : "../plugin/cropper",
        "jquery.easyui.min" : "../plugin/jquery.easyui.min",
        "ztree":"ztree/jquery.ztree.core",
        "ztree.excheck":"ztree/jquery.ztree.excheck",
        "ztree.exedit":"ztree/jquery.ztree.exedit",
        "nicevalidator":"nicevalidator/jquery.validator",
        "nicevalidator.zh_CN":"nicevalidator/zh_CN",
        "layer":"layer/layer",
        "abc.common":"../util/common",
        "swfupload.handlers":"swfupload/handlers",
        "swfupload.swfupload":"swfupload/swfupload",
        "abc.ajaxfileupload":"../util/ajaxfileupload",
        "commenttj": "../cms/commenttj/statistics",
        "colpick": "../plugin/colpick",
        "jqplot": "../util/jquery.jqplot",
        "highlighter": "../util/jqplot.highlighter.min",
        "jquery.zoom": "../plugin/jquery.zoom",
        "barRenderer": "../util/jqplot.barRenderer",
        "categoryAxisRenderer": "../util/jqplot.categoryAxisRenderer",
        "dateAxisRenderer": "../plugin/jqplot.dateAxisRenderer",
        "canvasAxisLabelRenderer": "../plugin/jqplot.canvasAxisLabelRenderer",
        "canvasTextRenderer": "../plugin/jqplot.canvasTextRenderer",
        "uploadify" : "uploadify/jquery.uploadify.min",
        "blockUI": "../plugin/jquery.blockUI",
        "autocomplete": "autocomplete/jquery.bigautocomplete",
        "tagEditor": "tagEditor/jquery.tagsinput"
    },
    shim: {
    	"autocomplete": {
            deps: ["jquery-3.1.0"]
        },
        "owl.carousel": {
            deps: ["jquery-3.1.0"]
        },
        "bootstrap": {
            deps: ["jquery-3.1.0"]
        },
        "ztree": {
            deps: ["jquery-3.1.0"]
        },
        "ztree.excheck": {
            deps: ["jquery-3.1.0", "ztree"]
        },
        "ztree.exedit": {
            deps: ["jquery-3.1.0", "ztree"]
        },
        "nicevalidator": {
            deps: ["jquery-3.1.0"]
        },
        "nicevalidator.zh_CN": {
            deps: ["jquery-3.1.0", "nicevalidator"]
        },
        "layer": {
            deps: ["jquery-3.1.0"]
        },
        "colpick": {
            deps: ["jquery-3.1.0"]
        },
        "abc.common": {
            deps: ["jquery-3.1.0","layer","blockUI"]
        },
        "abc.ajaxfileupload": {
            deps: ["jquery-3.1.0"]
        },
        "jqplot": {
            deps: ["jquery-3.1.0"]
        },
        "highlighter": {
            deps: ["jquery-3.1.0", "jqplot"]
        },
        "uploadify": {
            deps: ["jquery-3.1.0"]
        },
        "jquery.zoom": {
            deps: ["jquery-3.1.0"]
        },
        "barRenderer": {
            deps: ["jquery-3.1.0", "jqplot"]
        },
        "categoryAxisRenderer": {
            deps: ["jquery-3.1.0", "jqplot","barRenderer"]
        },
        "dateAxisRenderer": {
            deps: ["jquery-3.1.0","jqplot"]
        },
        "canvasAxisLabelRenderer": {
            deps: ["jquery-3.1.0","jqplot","canvasTextRenderer"]
        },
        "canvasTextRenderer":
        {
            deps: ["jquery-3.1.0", "jqplot"]
        },
        "tagEditor":{
        	deps:["jquery-3.1.0"]
        }
    },
    urlArgs: "v=" + (new Date()).getTime()
});

define("jquery.full",["jquery-3.1.0","blockUI","jquery.easyui.min","bootstrap","ztree", "ztree.excheck","ztree.exedit","nicevalidator","nicevalidator.zh_CN","layer","abc.common","abc.ajaxfileupload","colpick","jquery.zoom"],function($){
    layer.config({
        offset: abc.winscrollTop(100)
    });
    return $;
});

define("swfupload.full", ["swfupload.handlers", "swfupload.swfupload"], function ($) {
    //return $.noConflict(true);
    //return SWFUpload;
    return $;
});



define("ajaxfileupload.full", ["abc.ajaxfileupload"], function ($) {
    //return $.noConflict(true);
    return $;
});

define("highlighter.full", ["highlighter"], function ($) {
    //return $.noConflict(true);
    return $;
});
