/*********************************
 * Themes, rules, and i18n support
 * Locale: Chinese; 中文
 *********************************/
//(function(factory) {
//    if (typeof define === 'function') {
//        define(function(require, exports, module){
//            var $ = require('jquery');
//            $._VALIDATOR_URI = module.uri;
//            require('../src/jquery.validator.js')($);
//            factory($);
//        });
//    } else {
//        factory(jQuery);
//    }
//}

(function($) {
    /* Global configuration
     */
    $.validator.config({
        //stopOnError: false,
        //theme: 'yellow_right',
        defaultMsg: "{0}格式不正确",
        loadingMsg: "正在验证...",

        // Custom rules
        rules: {
            curentDateTime : function (element, params) {
                var val = element.value;
                var date = new Date(val.replace(/\-/g, "\/"));
                var cur = new Date();
                if(date<=cur){
                    return "截止日期不能早于当前时间";
                }else{
                    return true;
                }
            },
            path: [/^\/[A-Za-z0-9]{1,9}$/,"路径请以/开头，且只能为英文字母或数字，总长度不能超过10"],
            nsrsbh: function (element, params) {
                //^([0-9a-zA-Z]){15}$|^\1{17}$|^\1{18}$|^\1{20}$
                return /^([0-9a-zA-Z]){15,20}$/.test(element.value) ||
                    '只能是15到20位数字或字母,当前' + (element.value).length + '位';
            }
            ,
            agencyCreditCode: function (element, params) {
                return /^([0-9a-zA-Z]){18}$/.test(element.value) ||
                    '只能是18位数字或字母,当前' + (element.value).length + '位';
            }
            ,
            digits: [/^\d+$/, "请输入数字"]
            ,
            letters: [/^[a-z]+$/i, "{0}只能输入字母"]
            ,
            tel: [/^(?:(?:0\d{2,3}[\- ]?[1-9]\d{6,7})|(?:[48]00[\- ]?[1-9]\d{6}))$|^\d{7,8}$/, "电话格式应如：0731-88992611"]
            ,
            mobile: [/^1[3-9]\d{9}$/, "手机号格式不正确"]
            ,
            email: [/^[\w\+\-]+(\.[\w\+\-]+)*@[a-z\d\-]+(\.[a-z\d\-]+)*\.([a-z]{2,4})$/i, "邮箱格式不正确"]
            ,
            qq: [/^[1-9]\d{4,}$/, "QQ号格式不正确"]
            ,
            date: [/^\d{4}-\d{1,2}-\d{1,2}$/, "请输入正确的日期,例:yyyy-MM-dd"]
            ,
            time: [/^([01]\d|2[0-3])(:[0-5]\d){1,2}$/, "请输入正确的时间,例:14:30或14:30:00"]
            ,
            datetime: [/^\d{4}-\d{1,2}-\d{1,2}\s([01]\d|2[0-3])(:[0-5]\d){1,2}$/, "请输入正确的日期,例:yyyy-MM-dd HH:mm:ss"]
            ,
            ID_card: [/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/, "请输入正确的身份证号码"]
            ,
            lxr: [/^[\u0391-\uFFE5]{2,8}$|^([A-Za-z]{1,10}\s?){1,3}$/, "请输入2到8位中文，或英文名"]
            ,
            url: [/^(https?|ftp):\/\/[^\s]+$/i, "网址格式不正确"]
            ,
            postcode: [/^[1-9]\d{5}$/, "邮政编码格式不正确"]
            ,
            chinese: [/^[\u0391-\uFFE5]+$/, "请输入中文"]
            ,
            chineseName: [/^[\u0391-\uFFE5]{2,25}$/, "请输入中文,2-25位"]
            ,
            username: [/^\w{6,20}$/, "请输入6-15位数字、字母、下划线"]
            ,
            password: [/^[0-9a-zA-Z]{8,16}$/, "密码由8-16位数字、字母组成"]
            ,
            je: [/^\d{1,14}$|^\d{1,14}\.\d{1,2}$/, "金额应为14位数字两位小数"]
            ,
            smallAmount: [/^\d{1,7}$|^\d{1,7}\.\d{1,2}$/, "小额最多为7位整数加2位小数"]
            ,
            szzm: [/^[0-9a-zA-Z]+$/, "只能是数字或字母"]
            ,
            nottszf: [/^[a-zA-Z\d\u4E00-\u9FA5]+$/i, "只能是中文字母或数字"]
            ,
            chineseEnglish: [/^[a-zA-Z\u4E00-\u9FA5]+$/i, "只能是中文或字母"]
            ,
            telmobile: [/^((?:(?:0\d{2,3}[\- ]?[1-9]\d{6,7})|(?:[48]00[\- ]?[1-9]\d{6}))$)|^\d{7,8}$|^1[3-9]\d{9}$/, "手机或电话格式不正确"]
            ,
            domain: [/^((\w+\.)?\w+\.(com|cn|org|net|cc|com\.cn|tv)|(localhost)|(127\.0\.0\.1)|(\d){1,3}\.(\d){1,3}\.(\d){1,3})(:[0-9]{1,5})?((\/\S*)?)*$/,"请填写正确的域名"]
            ,
            accept: function (element, params) {
                if (!params) return true;
                var ext = params[0];
                return (ext === '*') ||
                    (new RegExp(".(?:" + (ext || "png|jpg|jpeg|gif") + ")$", "i")).test(element.value) ||
                    this.renderMsg("只接受{1}后缀", ext.replace('|', ','));
            },
            yzm: function (element, params) {
                return $.ajax({
                    url: ctx + '/check.html',
                    data: {"code": $("#yzm").data('code'), "ctm": $("#yzm").data('ctm'), "input": $("#yzm").val()},
                    dataType: 'json',
                    type: 'post',
                    async: false,
                    success: function (result) {
                        if (result.error != null) {
                            getImage();
                        }
                    }
                });
            },
            betplfile: function (element, params) {
                if (!params) return true;
                var expTmp = "/(";
                var typeArray = params[0].split(",");
                for(var i=0; i<typeArray.length; i++){
                    if(i<typeArray.length-1){
                        expTmp += "(\\."+typeArray[i]+")|";
                    }else{
                        expTmp += "(\\."+typeArray[i]+")";
                    }
                }
                expTmp += ")$/";
                exp = eval(expTmp);
                //var expTmp = "/((\.ftl)|(\.html))$/";
                var exp = eval(expTmp);

                return exp.test(element.value.toLowerCase()) || "只接收"+ params[0]+"类型的文件"
            },
        }
    });

    /* Default error messages
     */
    $.validator.config({
        messages: {
            required: "{0}不能为空",
            remote: "{0}已被使用",
            integer: {
                '*': "请输入整数",
                '+': "请输入正整数",
                '+0': "请输入正整数或0",
                '-': "请输入负整数",
                '-0': "请输入负整数或0"
            },
            match: {
                eq: "{0}与{1}不一致",
                neq: "{0}与{1}不能相同",
                lt: "{0}必须小于{1}",
                gt: "{0}必须大于{1}",
                lte: "{0}必须小于或等于{1}",
                gte: "{0}必须大于或等于{1}"
            },
            range: {
                rg: "请输入{1}到{2}的数",
                gte: "请输入大于或等于{1}的数",
                lte: "请输入小于或等于{1}的数"
            },
            checked: {
                eq: "请选择{1}项",
                rg: "请选择{1}到{2}项",
                gte: "请至少选择{1}项",
                lte: "请最多选择{1}项"
            },
            length: {
                eq: "长度应为{1}位",
                rg: "长度应为{1}到{2}位",
                gte: "长度不能少于{1}位",
                lte: "长度不能超过{1}位",
                eq_2: "",
                rg_2: "",
                gte_2: "",
                lte_2: ""
            }
        }
    });

    /* Themes
     */
    var TPL_ARROW = '<span class="n-arrow"><b>◆</b><i>◆</i></span>';
    $.validator.setTheme({
        'simple_right': {
            formClass: 'n-simple',
            msgClass: 'n-right'
        },
        'simple_bottom': {
            formClass: 'n-simple',
            msgClass: 'n-bottom'
        },
        'yellow_top': {
            formClass: 'n-yellow',
            msgClass: 'n-top',
            msgArrow: TPL_ARROW
        },
        'yellow_right': {
            formClass: 'n-yellow',
            msgClass: 'n-right',
            msgArrow: TPL_ARROW
        },
        'yellow_bottom': {
            formClass: 'n-yellow',
            msgClass: 'n-bottom',
            msgArrow: TPL_ARROW
        },
        'yellow_right_effect': {
            formClass: 'n-yellow',
            msgClass: 'n-right',
            msgArrow: TPL_ARROW,
            msgShow: function ($msgbox, type) {
                var $el = $msgbox.children();
                if ($el.is(':animated')) return;
                if (type === 'error') {
                    $el.css({
                        left: '20px',
                        opacity: 0
                    }).delay(100).show().stop().animate({
                        left: '-4px',
                        opacity: 1
                    }, 150).animate({
                        left: '3px'
                    }, 80).animate({
                        left: 0
                    }, 80);
                } else {
                    $el.css({
                        left: 0,
                        opacity: 1
                    }).fadeIn(200);
                }
            },
            msgHide: function ($msgbox, type) {
                var $el = $msgbox.children();
                $el.stop().delay(100).show().animate({
                    left: '20px',
                    opacity: 0
                }, 300, function () {
                    $msgbox.hide();
                });
            }
        }
    });
})(jQuery);
//}));