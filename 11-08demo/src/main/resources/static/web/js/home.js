//document.write("<h1>这是一个标题</h1>");  直接显示文字
function myFunction()
{
    var uid=$("#uid").val();
    $.post("/nul",{id:uid},function (result) {
        if(result.success())
            alert(result.msg)
        else
            alert("erro "+result.msg)
    })
}