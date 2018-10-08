$(function () {
    initPage(taskSearch)
});

function taskSearch() {
    $("#typeListForm").submit();
}

function statusConvert(id, status) {
    console.info("statusConvert :", id, status)
    $("#statusConvertForm_status").val(status);
    $("#statusConvertForm_id").val(id);
    $("#statusConvertForm_bizType").val($("searchBizType").val());
    var statusConvertForm = $("#statusConvertForm");
    $.ajax({
        type: "POST",
        url: "convertStatus.htm",
        data: statusConvertForm.serialize(),// 要提交的表单 
        success: function (msg) {
            console.info("msg =", msg)
        }
    });
    $("#next_page").val($("#page_start").val())
    taskSearch();
    console.info("启动关闭表单提交")
}

function del(id) {
    console.info("del :", id)
    $.ajax({
        type: "post",
        url: "delete.htm",
        data: "id=" + id,// 要提交的表单 
        success: function (msg) {
            console.info("msg =", msg)
        }
    });
    $("#next_page").val($("#page_start").val())
    taskSearch();
    console.info("启动关闭表单提交")
}
