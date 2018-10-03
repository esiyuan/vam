function taskSearch(start) {
    console.info("search()")
    var form = $("#typeListForm");
    $("#typeListPageStart").val(start);
    form.attr("action", "search");
    form.submit();
}