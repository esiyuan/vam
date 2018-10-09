$(function () {
//日期时间选择器
    laydate.render({
        elem: '#nextRunTimeStart', type: 'datetime'
    });
    laydate.render({
        elem: '#nextRunTimeEnd', type: 'datetime'
    });
    initPage(taskSearch)
    $("#statusSelect").val($("#selectStatus").val());
})


function taskSearch() {
    $("#listForm").submit();
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

}

function statusSelect() {
    $("#selectStatus").val($("#statusSelect").val());
}

