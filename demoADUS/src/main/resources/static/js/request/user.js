$(function () {
  $('#Lon_Btn').on('click',function () {
    $.ajax({
        url: "/Lon",
        method: "post",
        data: {'id': $("#uid").val(),'password': $("#pwd").val()},
        // xhrFields: {
        //   withCredentials: true //允许跨域带Cookie
        // },
        success: function (result) {
            if(result == "Y")
                alert("普用用户登录成功");
            if(result == "AY")
                alert("管理员登陆成功");
        },
        error: function (result) {
            alert(result.responseText)
        }
    })
});
})