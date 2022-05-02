/* requirejs 配置
 ================================== */
//在test或uat环境不需要修改缺省值
var sso_base_url = "https://zhengqi.10086.cn/ecsso";
{
    var jss=document.scripts;
    var common_js_url=jss[jss.length-1].src;
    var common_js_related_path = "/js/common.js";
    var div1 = common_js_url.indexOf(common_js_related_path);
    if(div1 >= 0) {
        sso_base_url = common_js_url.substring(0,div1);
    }
}

require.config({
  baseUrl: "../js",
  paths: {
    'requirejs': 'libs/require/require.min',
    "jquery": "libs/jquery/jquery.min",
    "text": "libs/require/text.min",
    "jquery.plugins": "libs/jquery.plugins/plugins",
    "rsa": "libs/rsa/jsencrypt.min",
    "passwordCheck": "libs/pwdCheck/passwordCheck.min"
  },
  waitSeconds: 0,
  map: {
    '*': {
      'css': 'libs/require/css.min'
    }
  },
  shim: {
    "jquery.plugins": {
      deps: ["jquery"]
    }
  }
});

// common
require([
  'jquery',
  'jquery.plugins',
  'rsa',
  'passwordCheck'
], function ($) {
  $.baseUrl = sso_base_url;
  $.getParam = function () {
    var url = location.search; //获取url中"?"符后的字串
    if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      var channelId = strs[0].split("=")[0];
      var channelId2 = strs[0].split("=")[1];
      var backUrl = strs[1].split("=")[0];
      var backUrl2 = unescape(strs[1].split("=")[1]);
      // for(var i = 0; i < strs.length; i ++) {
      //     //保存渠道和连接地址
      //     var k = strs[i].split("=")[0];
      //     var v = strs[i].split("=")[1];
      //     window.localStorage.setItem(k,v);
      // }
      if ("channelId" == channelId && "backUrl" == backUrl) {
        window.localStorage.setItem(channelId2, backUrl2);
        window.localStorage.setItem("channlId", channelId2);
      } else {
        alert("BU传递参数错误");
        //处理不友好，页面服务该处参数不能传递错误，否则无法处理，此处跳转可以404错误页面
        return;
      }
    }
  };
  // Encrypt with the public key...
  $.encrypt = new JSEncrypt();
  $.encrypt.setPublicKey("-----BEGIN PUBLIC KEY-----"+
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVxlQoDoaS2FR+WsJbO6UxzMY2"+
      "KEKNoQWaGs8m5417ctHsC688weR6txw5oIPHtOZUg7hLUCUoFMWKV//ONzd+wO75"+
      "I9MTdWp234kbcbS2CcWgIgsHGVVPDYEVpOq0Cm6yjh3WSKrDFpTwODuWQ/rW7iZn"+
      "vEojFaeFCKcSJPoKEwIDAQAB"+
      "-----END PUBLIC KEY-----");
  // 获取门户密码校验方法
  $.checkPassword = function (pwd) {
    return window.PasswordCheck.STAFF_PASSWORD_CHECK(pwd);
  }
  // 绑定额外密码规则弹窗
  $('.showRuleModal').unbind('click').bind('click', function () {
    var modal = $('.extraRuleModal');
    if (modal && modal.length > 0) {
      $('.shadowBox').show();
      modal.show();
    } else {
      var shadow = document.createElement('div');
      shadow.className = 'shadowBox';
      $('body').append(shadow);
      var extraRule = document.createElement('div');
      extraRule.className = 'extraRuleModal';
      extraRule.innerHTML = '<h5>口令需避免以下易猜解口令规则</h5>' +
                        '<a class="close-btn">&#215;</a>' +
                        '<p>含有以下任意字符（不区分大小写）均定义为“易猜口令”：</p>' +
                        '<p>admin、cmcc、sun、console、root、yunmas、pingan、HBsmc、nicai、ZQwlan、HBnmcHBnmc123、HBsmc_123、yunmas@2020、pingan#199071、nicai!1230.、ZQwlan@CMCC、admin+root、admin+888888、sun-123456、console_888888</p>';
      $('body').append(extraRule);
      $('.extraRuleModal').find('.close-btn').unbind('click').bind('click', function () {
        $('.shadowBox').hide();
        $('.extraRuleModal').hide();
      })
    }
  });
});
