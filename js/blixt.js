$(function () {
    var e = $('pre.code');
    for (var i = 0; i < e.length; i++) {
        e.eq(i).html(prettyPrintOne(e.eq(i).html()));
    }
});
