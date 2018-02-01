
require(["../../config"], function () {
    require(["jquery.full", "swfupload.full"], function ($) {
        $(function () {
            $("#FILE01").on("change", function () {
                PreviewImage(this, 'imgDiv1', 'imgHeadPhoto1', 'divPreview1');
            })

            //js本地图片预览，兼容ie[6-9]、火狐、Chrome17+、Opera11+、Maxthon3
            function PreviewImage(fileObj, imgDiv, imgPreviewId, divPreviewId) {
                $('#' + imgDiv).show();
                var allowExtention = ".jpg.png.bmp";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;
                var extention = fileObj.value.substring(fileObj.value.lastIndexOf(".") + 1).toLowerCase();
                var browserVersion = window.navigator.userAgent.toUpperCase();
                if (allowExtention.indexOf(extention) > -1) {
                    if (fileObj.files) {//HTML5实现预览，兼容chrome、火狐7+等
                        if (window.FileReader) {
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                document.getElementById(imgPreviewId).setAttribute("src", e.target.result);

                            }
                            reader.readAsDataURL(fileObj.files[0]);
                        } else if (browserVersion.indexOf("SAFARI") > -1) {
                            abc.layerAlert(false,"不支持Safari6.0以下浏览器的图片预览!");
                        }
                    } else if (browserVersion.indexOf("MSIE") > -1) {
                        if (browserVersion.indexOf("MSIE 6") > -1) {//ie6
                            document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);

                        } else {//ie[7-9]
                            fileObj.select();
                            if (browserVersion.indexOf("MSIE 9") > -1)
                                fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问
                            var newPreview = document.getElementById(divPreviewId + "New");


                            if (newPreview == null) {
                                newPreview = document.createElement("div");
                                newPreview.setAttribute("id", divPreviewId + "New");
                                newPreview.style.width = document.getElementById(imgPreviewId).width + "px";
                                newPreview.style.height = document.getElementById(imgPreviewId).height + "px";
                                newPreview.style.border = "solid 1px #d2e2e2";
                            }
                            newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";


                            var tempDivPreview = document.getElementById(divPreviewId);
                            tempDivPreview.parentNode.insertBefore(newPreview, tempDivPreview);
                            tempDivPreview.style.display = "none";

                        }
                    } else if (browserVersion.indexOf("FIREFOX") > -1) {//firefox
                        var firefoxVersion = parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);
                        if (firefoxVersion < 7) {//firefox7以下版本
                            document.getElementById(imgPreviewId).setAttribute("src", fileObj.files[0].getAsDataURL());
                        } else {//firefox7.0+
                            document.getElementById(imgPreviewId).setAttribute("src", window.URL.createObjectURL(fileObj.files[0]));
                        }
                    } else {
                        document.getElementById(imgPreviewId).setAttribute("src", fileObj.value);
                    }
                } else {
                    layer.msg("仅支持" + allowExtention + "为后缀名的文件!", {icon: 5});
                    fileObj.value = "";//清空选中文件
                    document.getElementById(imgPreviewId).setAttribute("src", "");

                }
            }
        })
        $("#submit").on("click", function () {
debugger
                        $.ajaxFileUpload({
                            url: ctx + "/imgdo/save.php",
                            secureuri: false,
                            fileElementId: 'FILE01',//file标签的id
                            dataType: 'json',
                            data: {
                                fname: name,
                                dir: dir
                            },
                            success: function (data) {

                                if (data.result.code == '2000') {
                                    layer.msg("操作成功", {offset: abc.winscrollTop(200),shade:0.3,icon: 1,time:1000}, function () {

                                    });

                                } else {
                                    layer.msg(data.result.message, {icon: 5});
                                }
                            }
                        });

        });
    })
})
