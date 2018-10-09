$(function () {
//日期时间选择器
    laydate.render({
        elem: '#createTimeStart', type: 'datetime'
    });
    laydate.render({
        elem: '#createTimeEnd', type: 'datetime'
    });
    initPage(taskSearch)

});

function taskSearch() {
    $("#listForm").submit();
    $("#next_page").val($("#page_start").val())
}

function del(id) {
    console.info("del :", id)
    $.ajax({
        type: "post",
        url: "delete.htm",
        data: "id=" + id,// 要提交的表单 
        success: function (msg) {
            taskSearch();
        }
    });

}

function move(id) {
    console.info("move :", id)
    $.ajax({
        type: "post",
        url: "move.htm",
        data: "id=" + id,// 要提交的表单 
        success: function (msg) {
            taskSearch();
        }
    });



}

