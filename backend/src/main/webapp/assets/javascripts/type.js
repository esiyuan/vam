$(function () {
    $("#execTypeSelect").val($("#execTypeValue").val());
    $("#exceptionProcTypeSelect").val($("#exceptionProcTypeValue").val());
    $("#taskTypeStatus").val($("#taskTypeStatusValue").val());
    var errorMsgDiv = $('#errorMsg');
    if (errorMsgDiv) {
        errorMsgDiv.show(300).delay(3000).hide(300);
    }

});

function execTypeSelectF() {
    $("#execTypeValue").val($("#execTypeSelect").val());
}

function statusSelect() {
    $("#taskTypeStatusValue").val($("#taskTypeStatus").val());
}

function procTypeSelect() {
    $("#exceptionProcTypeValue").val($("#exceptionProcTypeSelect").val());
}

