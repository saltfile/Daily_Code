// 注册
require([
    'jquery',
    'jquery.plugins'
], function($) {

    $(function () {

        'use strict';

        // TODO: config
        var baseUrl = $.baseUrl,
            pageUrl = {

                captcha: {
                    get: baseUrl + '/verification/captcha.jpg?', // 获取验证码,
                    valid: baseUrl + '/verification/validCaptcha' // 校验验证码
                },
                sms: baseUrl + '/verification/phoneVerificationCode', // 获取短信验证码
                valid: baseUrl + '/verification/checkVerificationCode', // 校验身份
                register: baseUrl + '/register/signUp' // 注册
            },
            passwordEncrypt = true, // 密码是否md5加密
            verifiedPhone = '', // 短信校验通过的密码
            valid = {
                phone: {
                    regExp: /^1[0-9]{10}$/, // 全网通
                    msg: '请输入正确的手机号码'
                },
                captcha: {
                    regExp: /^[0-9a-z]{5}$/,
                    msg: '验证码错误'
                },
                sms: {
                    regExp: /^[0-9]{6}$/, // 6位数字
                    msg: '短信验证码错误'
                },
                username: {
                    regExp: /^.{3,20}$/,
                    msg: '用户名长度必须在3~20之间！'
                },
                password: {
                    regExp: /^(?=.*\d)(?=.*[A-Za-z])(?=.*[A-Z])(?=.*[`~!@#$%^&*?=\-+_.,:;'"()<>|\/\[\]{}￥￡€ ]).{8,20}$/,
                    msg: '密码8-20位，需要包含数字、字母且必须包含大写字母和符号(半角)'
                },
                email: {
                    regExp: /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
                    msg: '请输入正确的邮箱地址'
                }
            };

        // for ie<=9 placeholder polyfill
        var input = document.createElement('input');
        if (!('placeholder' in input)) {
            $('form label').css('display','block');
        }

        /**
         * step one: 验证身份
         * ====================
         */
        $('#J_valid').on('click', function () {
            var phone = checkPhone(),
                captcha = checkCaptcha(),
                sms = checkSms(),
                terms = checkTerms(); // 服务协议校验
            if (phone && captcha && sms&& terms) {
                ajaxPromise.submit(function() {
                    $('[name=form-valid]').find('.form-error').text("");
                    $('#J_valid').prop('disabled', true).text('处理中...');
                    var phoneNum = $("[name=phone]").val();
                    return $.ajax({
                        url: pageUrl.valid,
                        type: 'POST',
                        contentType: "application/xml; charset=utf-8",
                        data: generateValidSmsXml($.encrypt.encrypt(phoneNum),$('[name=sms]').val(),$('[name=captcha]').val()),
                        success: function (data) {

                            var rspType,rspCode,rspDesc;
                            $(data).find("Response").each(function() {
                                var response = $(this);
                                rspType = response.find("RspType").text();//读取节点属性
                                rspCode = response.find("RspCode").text();//读取子节点的值
                                rspDesc = response.find("RspDesc").text();//读取子节点的值
                            });
                            if(rspType && rspType == 0){
                                $('#stepOne').hide();
                                $('#stepTwo').show();
                                verifiedPhone = phoneNum;
                                //解析返回的sessionId并保存
                                $(data).find("VerificationRsp").each(function() {
                                    var response = $(this);
                                    var verificationSessionId = response.find("VerificationSessionId").text();//读取节点属性
                                    localStorage.setItem("sessionId",verificationSessionId);
                                });
                            }else {
                                showTips(rspDesc, null, $('[name=form-valid]'));
                                $('#J_valid').prop('disabled', false).text('下一步');
                            }
                        },
                        error: function () {
                            showTips('请求失败，请稍后重试', null, $('[name=form-valid]'));
                            $('#J_valid').prop('disabled', false).text('下一步');
                        }
                    })
                });
            }
            return false;
        });
        // 服务协议校验
        // ========================
        // 服务协议联动
        $('#terms').on('click', function() {
            $('#J_valid').prop('disabled', !$(this).is(":checked"));
        });
        // 校验服务协议
        function checkTerms() {
            var val = $('#terms').is(":checked");
            if (val) {
                hideTips($('#terms'));
            } else {
                showTips('请勾选已阅读并同意网站服务协议及个人信息保护政策', $('#terms'));
            }
            return val;
        }
        function generateValidSmsXml(mobile,code,captcha){
            return '<?xml version="1.0" encoding="UTF-8"?>'+
                "<CARoot><SessionHeader><ServiceCode>CA02004</ServiceCode><ActionCode>0</ActionCode>"+
                "<TransactionID>000003201311071020570001383790857</TransactionID>"+
                "<SrcSysID>11001</SrcSysID>"+
                "<DstSysID>10000</DstSysID>"+
                "<Request></Request>"+
                "<DigitalSign>302C021433BD45D34D4970225B2AF23913BE49CE6534AC6102142E77E0F300400619664C8ADF6D3C966A3343F10A</DigitalSign>"+
                "<ReqTime>"+new Date().getTime()+"</ReqTime>"+
                "</SessionHeader>"+
                "<SessionBody><VerificationReq>"+
                "<VerificationAccount>"+mobile+"</VerificationAccount>"+
                "<DynamicVerificatioCode>"+code+"</DynamicVerificatioCode>"+
                "<CaptchaCode>"+captcha+"</CaptchaCode>"+
                "<Identification>2</Identification>"+
                "</VerificationReq></SessionBody>"+
                "</CARoot>";
        }

        // blur
        $('[name=form-valid] input').on('blur', function () {
            var name = $(this).attr('name');
            switch (name) {
                case 'phone':
                    checkPhone($(this));
                    break;
                case 'captcha':
                    checkCaptcha($(this));
                    break;
                case 'sms':
                    checkSms($(this));
                    break;
            }
        });

        // 校验手机号码
        function checkPhone() {
            var phone = $('[name=phone]'),
                regExp = valid.phone.regExp,
                val = $.trim(phone.val());
            if (!val) {
                showTips('请输入手机号码', phone);
                return false;
            } else if (!regExp.test(val)) {
                showTips(valid.phone.msg, phone);
                return false;
            }
            hideTips(phone);
            return true;
        }

        // 校验验证码
        function checkCaptcha() {
            var returnStr = false;
            var captcha = $('[name=captcha]'),
                regExp = valid.captcha.regExp,
                val = $.trim(captcha.val());
            if (!val) {
                showTips('请输入验证码', captcha);
                return false;
            } else if (!regExp.test(val)) {
                showTips(valid.captcha.msg, captcha);
                return false;
            } else {
                $.ajax({
                    url : pageUrl.captcha.valid,
                    beforeSend: function(request) {
                        request.setRequestHeader("ticket","mengff");
                    },
                    type : "POST",
                    data : {
                        captcha : val
                    },
                    async:false,
                    success : function(data) {
                        var flag = $(data).find('Boolean').text();
                        console.info("图片验证码校验结果："+flag);
                        if(flag == "true"){
                            hideTips(captcha);
                            console.info("图片验证码校验成功！"+flag);
                            returnStr = true;
                        } else {
                            showTips('验证码错误', captcha);
                            updateCaptcha();
                            returnStr = false;
                        }
                    },
                    error: function() {
                        showTips('验证码错误', captcha);
                        updateCaptcha();
                        returnStr= false;
                    }
                });
                return returnStr;
            }
        }

        // 换验证码
        $('.change-captcha').on('click', function () {
            updateCaptcha();
            return false;
        });

        // 更新验证码
        function updateCaptcha() {
            var captcha = $('#J_captcha')[0];
            var img = new Image();
            var random = new Date().getTime();
            img.src = pageUrl.captcha.get + random;
            img.onload = function () {
                captcha.src = this.src;
            };
        }

        // 校验短信验证码
        function checkSms() {
            var sms = $('[name=sms]'),
                regExp = valid.sms.regExp,
                val = $.trim(sms.val());
            if (!val) {
                showTips('请输入短信验证码', sms);
                return false;
            } else if (!regExp.test(val)) {
                showTips(valid.sms.msg, sms);
                return false;
            }
            hideTips(sms);
            return true;
        }

        // 获取短信验证码
        $('#J_sms').on('click', function() {
            var self = $(this);
            // 校验手机号码和验证码
            var phone = checkPhone(),
                captcha = checkCaptcha();
            if (phone && captcha) {
                $.ajax({
                    url: pageUrl.sms,
                    type: 'POST',
                    contentType: "application/xml; charset=utf-8",
                    data: generateSmsXml($.encrypt.encrypt($("[name=phone]").val())),
                    success: function(data) {
                        var rspType,rspCode,rspDesc;
                        $(data).find("Response").each(function() {
                            var response = $(this);
                            rspType = response.find("RspType").text();//读取节点属性
                            rspCode = response.find("RspCode").text();//读取子节点的值
                            rspDesc = response.find("RspDesc").text();//读取子节点的值
                        });
                        if (rspType && rspType == 0) {
                            hideTips(null, $('[name=form-valid]'));
                            //获取成功后的倒计时
                            countdown(self);
                        } else {
                            showTips(rspDesc || '获取短信验证码失败，请稍后重试', null, $('[name=form-valid]'));
                        }
                    },
                    error: function(data) {
                        showTips(data.msg || '获取短信验证码失败，请稍后重试', null, $('[name=form-valid]'));
                        self.prop('disabled', false).text('获取短信验证码');
                    }
                });
            }
        });
        //封装请求的xml参数
        function generateSmsXml(mobile){
            return '<?xml version="1.0" encoding="UTF-8"?>'+
                "<CARoot><SessionHeader><ServiceCode>CA02004</ServiceCode><ActionCode>0</ActionCode>"+
                "<TransactionID>000003201311071020570001383790857</TransactionID>"+
                "<SrcSysID>11001</SrcSysID>"+
                "<DstSysID>10000</DstSysID>"+
                "<Request></Request>"+
                "<DigitalSign>302C021433BD45D34D4970225B2AF23913BE49CE6534AC6102142E77E0F300400619664C8ADF6D3C966A3343F10A</DigitalSign>"+
                "<ReqTime>"+new Date().getTime()+"</ReqTime>"+
                "</SessionHeader>"+
                "<SessionBody><RandCodeReq>"+
                "<SendType>00</SendType>"+
                "<UserPhoneNo>"+mobile+"</UserPhoneNo>"+
                "<Identification>2</Identification>"+
                "</RandCodeReq></SessionBody>"+
                "</CARoot>";
        }

        // 倒计时
        var showBackSeconds = null;
        function countdown(target) {
            target.prop('disabled', true).text('发送中...');
            var seconds = 60;
            showBackSeconds = setInterval(function () {
                seconds--;
                if (seconds === 0) {
                    clearInterval(showBackSeconds);
                    seconds = 60;
                    target.prop('disabled', false).text('获取短信验证码');
                } else {
                    target.text('已发送，' + seconds + 's 后可重新发送 ');
                }
            }, 1000);
        }

        /**
         * step two: 注册
         * ====================
         */
        $('#J_register').on('click', function () {
            $.getParam();
            //注册之前先解析,然后保存参数
            //http://localhost:8018/register/register.html?channelId=11006&backUrl=http://localhost:8029/
            var username = checkUsername(),
                password = checkPassword(),
                passwordAgain = checkPasswordAgain(),
                email = checkEmail(),
                province = checkProvince();

            if (username && password && passwordAgain && email && province) {
                //  ajaxPromise.submit(function() {
                $('[name=form-info]').find('.form-error').text("");
                $('#J_register').prop('disabled', true).text('注册中...');
                $.ajax({
                    url: pageUrl.register,
                    type: 'POST',
                    contentType: "application/xml; charset=utf-8",
                    data: generateRegisterXml(
                        $.encrypt.encrypt($("[name=phone]").val()),
                        $.encrypt.encrypt($("[name=username]").val()),
                        $.encrypt.encrypt($.md5($('[name=password]').val())),
                        $.encrypt.encrypt($('[name=password]').val()),
                        $.encrypt.encrypt($('[name=email]').val()),
                        $('[name=province]').val(),
                        localStorage.getItem("sessionId")
                    ),
                    success: function (data) {
                        var rspType,rspCode,rspDesc;
                        $(data).find("Response").each(function() {
                            var response = $(this);
                            rspType = response.find("RspType").text();//读取节点属性
                            rspCode = response.find("RspCode").text();//读取子节点的值
                            rspDesc = response.find("RspDesc").text();//读取子节点的值
                            if("WeakPassword" == rspDesc){
                                rspDesc = "密码口令过弱，请修改";
                            }
                        });
                        if(rspType && rspType == 0){
                            $('#stepTwo').hide();
                            $('#stepThree').show();
                        } else {
                            showTips(rspDesc, null, $('[name=form-info]'));
                            $('#J_register').prop('disabled', false).text('填写完成并提交');
                        }
                    },
                    error: function () {
                        showTips('注册失败，请稍后重试', null, $('[name=form-info]'));
                        $('#J_register').prop('disabled', false).text('填写完成并提交');
                    }
                });
                //   });
            }
            return false;
        });
        function generateRegisterXml(mobile,username,password,newRsaPwd,email,province,sessionId){
            return '<?xml version="1.0" encoding="UTF-8"?>'+
                "<CARoot><SessionHeader><ServiceCode>CA01002</ServiceCode><ActionCode>0</ActionCode>"+
                "<TransactionID>000003201311051722320001383643352</TransactionID>"+
                "<SrcSysID>11001</SrcSysID>"+
                "<DstSysID>10000</DstSysID>"+
                "<Request></Request>"+
                "<DigitalSign>302C021433BD45D34D4970225B2AF23913BE49CE6534AC6102142E77E0F300400619664C8ADF6D3C966A3343F10A</DigitalSign>"+
                "<ReqTime>"+new Date().getTime()+"</ReqTime>"+
                "</SessionHeader>"+
                "<SessionBody><RegUserInfo>"+
                "<UserPhoneNo>"+mobile+"</UserPhoneNo>"+
                "<UserEmail>"+email+"</UserEmail >"+
                "<UserName>"+username+"</UserName>"+
                "<ProvinceCode>"+province+"</ProvinceCode>"+
                "<PassWord>"+password+"</PassWord>"+
                "<NewRsaPassWord>"+newRsaPwd+"</NewRsaPassWord>"+
                "<VerificationSessionId>"+sessionId+"</VerificationSessionId>"+
                "<Identification>4</Identification>"+
                "</RegUserInfo></SessionBody>"+
                "</CARoot>";
        }

        // blur
        $('[name=form-info] input').on('blur', function () {
            var name = $(this).attr('name');
            switch (name) {
                case 'username':
                    checkUsername($(this));
                    break;
                case 'email':
                    checkEmail($(this));
                    break;
            }
        });

        // keyup
        $('[name=form-info] input').on('keyup', function () {
            var name = $(this).attr('name');
            switch (name) {
                case 'password':
                    checkPassword($(this));
                    break;
                case 'passwordAgain':
                    checkPasswordAgain($(this));
                    break;
            }
        });

        $('[name=form-info] select').on('change', function () {
            checkProvince($(this));
        });

        // 校验用户名
        function checkUsername() {
            var username = $('[name=username]'),
                regExp = valid.username.regExp,
                val = $.trim(username.val());
            if (!val) {
                showTips('请输入用户名', username);
                return false;
            } else if (!regExp.test(val)) {
                showTips(valid.username.msg, username);
                return false;
            }
            hideTips(username);
            return true;
        }

        // 校验密码
        function checkPassword() {
            var password = $('[name=password]'),
                pwdCheck = $.checkPassword,
                val = $.trim(password.val());
            if (!val) {
                showTips('请输入密码', password);
                return false;
            } else {
                if (pwdCheck(val) !== null) {
                    showTips(pwdCheck(val), password);
                    return false;
                } else if (verifiedPhone) {
                    var lastFour = verifiedPhone.substring(verifiedPhone.length - 4, verifiedPhone.length);
                    if (val.indexOf(lastFour) > -1) {
                        showTips('密码不能含有手机号后四位', password);
                        return false;
                    }
                }
            }
            hideTips(password);
            checkPasswordAgain();
            return true;
        }

        // 校验确认密码
        function checkPasswordAgain() {
            var passwordAgain = $('[name=passwordAgain]'),
                val = $.trim(passwordAgain.val()),
                compare = $.trim($('[name=password]').val());
            if (!val) {
                showTips('请再次输入密码', passwordAgain);
                return false;
            } else if (val !== compare) {
                showTips('两次输入的密码不一致，请重新输入', passwordAgain);
                return false;
            }
            hideTips(passwordAgain);
            return true;
        }

        // 校验邮箱地址
        function checkEmail() {
            var email = $('[name=email]'),
                regExp = valid.email.regExp,
                val = $.trim(email.val());
            if (!val) {
                showTips('请输入邮箱地址', email);
                return false;
            } else if (!regExp.test(val)) {
                showTips(valid.email.msg, email);
                return false;
            }
            hideTips(email);
            return true;
        }

        // 校验省份
        function checkProvince() {
            var province = $('[name=province]'),
                val = $.trim(province.val());
            if (!val) {
                showTips('请选择省份', province);
                return false;
            }
            hideTips(province);
            return true;
        }

        /**
         * common
         * ====================
         */
        // 显示提示
        function showTips(msg, target, form) {
            if (form) {
                var form = $(form),
                    tips = form.find('.form-error');
                tips.html('<i class="iconfont icon-warn1"></i> ' + msg).fadeIn();
            } else {
                var item = $(target).parent(),
                    tips = item.find('.help-block');
                if (tips.length) {
                    tips.text(msg).show();
                } else {
                    item.append($('<p class="help-block">' + msg + '</p>'));
                }
            }
            $(target).closest('.form-group').addClass('has-error');
        }

        // 隐藏提示
        function hideTips(target, form) {
            if (form) {
                var form = $(form),
                    tips = form.find('.form-error');
                tips.html('').fadeOut();
            } else {
                var item = $(target).parent(),
                    tips = item.find('.help-block');
                if (tips.length) {
                    tips.text('').hide();
                    item.closest('.form-group').removeClass('has-error');
                }
            }
        }



        // ajax promise
        var ajaxPromise = {
            _promise: {
                state: function () {}
            },
            submit: function (ajax) {
                if (this._promise.state() === 'pending') {
                    return;
                } else {
                    return this._promise = ajax();
                }
            }
        };

    });

});
function clickText(){
//注册成功进行跳转页面
    var channlId = window.localStorage.getItem("channlId");
    var urlHref= window.localStorage.getItem(channlId);
    window.location.href=urlHref;
}