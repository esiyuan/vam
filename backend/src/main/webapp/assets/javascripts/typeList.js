$(function () {

});

function taskSearch(start) {
    console.info("search()")
    var searchform = $("#typeListForm");
    $("#typeListPageStart").val(start);
    searchform.attr("action", "search");
    searchform.submit();
}

function statusConvert(id, status) {
    console.info("statusConvert :", id, status)
    $("#statusConvertForm_status").val(status);
    $("#statusConvertForm_id").val(id);
    $("#statusConvertForm_bizType").val($("searchBizType").val());
    var statusConvertForm = $("#statusConvertForm");
    $.ajax({
        type: "POST",
        url: "convertStatus",
        data: statusConvertForm.serialize(),// 要提交的表单 
        success: function (msg) {
            console.info("msg =", msg)
        }
    });
    taskSearch($("#typeListPageStart").val());
    console.info("启动关闭表单提交")
}

function del(id) {
    console.info("del :", id)
    $.ajax({
        type: "post",
        url: "delete",
        data: "id=" + id,// 要提交的表单 
        success: function (msg) {
            console.info("msg =", msg)
        }
    });
    taskSearch($("#typeListPageStart").val());
    console.info("启动关闭表单提交")
}
