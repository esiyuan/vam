function initPage(formSend) {
    var cur_index = $("#page_start").val();
    var totalPage = $("#page_total").val();
    pagination({
        cur: cur_index,
        total: totalPage,
        len: 10,
        targetId: 'pagination',
        callback: function (total) {
            var oPages = document.getElementsByClassName('page-index');
            for (var i = 0; i < oPages.length; i++) {
                oPages[i].onclick = function () {
                    var next_page = this.getAttribute('data-index');
                    $("#next_page").val(next_page);
                    formSend();
                }
            }
            var goPage = document.getElementById('go-search');
            goPage.onclick = function () {
                var index = document.getElementById('yeshu').value;
                if (!index || (+index > total) || (+index < 1)) {
                    return;
                }
                $("#next_page").val(index);
                formSend();
            }
        }
    });
}

