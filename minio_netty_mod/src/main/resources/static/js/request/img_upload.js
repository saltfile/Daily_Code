
function uploadImg() {
    var fileObj = document.getElementById("FileUpload").files[0]; // js 获取文件对象
    var reads = new FileReader();

    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {//判断有没有选择图片
        $.messager.alert('提示', "请选择图片");
        return;
    }
    var formFile = new FormData();
    formFile.append("action", "UploadVMKImagePath");
    formFile.append("file", fileObj);//添加字段和对应的值
    var data = formFile;
    $.ajax({
        url: "http://localhost:8888/Upload",
        data: data,
        type: "Post",
        dataType: "json",
        headers: {//token验证没有不加
            token: sessionStorage.getItem("token"),
        },
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (result) {
            $.messager.alert('提示', "上传成功");
            if (result.responseText == "y") {
                //这里为你你成功后的回调,可以传成功后显示在本地
                reads.readAsDataURL(fileObj);//把文件对象读成base64，读完直接放到src中
                reads.onload = function (e) {
                    document.getElementById(id).src = this.result;
                }
                console.log("成功")
            }
            else
            console.log("失败")
        },
        error: function (result) {
            if (result.responseText == "y") {
                //这里为你你成功后的回调,可以传成功后显示在本地
                reads.readAsDataURL(fileObj);//把文件对象读成base64，读完直接放到src中
                reads.onload = function (e) {

                }
                console.log("成功")
            }
            else
            console.log("失败")

        }
    });
}



// $(function () {
//     $('#imgdata').on('change',function () {
//         console.log("已选择文件");
//
//         $.ajax({
//             url: "/Upload",
//             method: "post",
//             data: new FormData($("#imgdata")[0]),
//             // xhrFields: {
//             //   withCredentials: true //允许跨域带Cookie
//             // },
//             success: function (result) {
//                 if(result == "Y")
//                     alert("成功");
//                 else
//                 alert("失败");
//             },
//             error: function (result) {
//                 alert(result.responseText)
//             }
//         })
//     });
// })