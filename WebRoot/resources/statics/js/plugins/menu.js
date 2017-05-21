$(document).ready(function () {
    slide();
});

function slide () {
    var verNav = $(".left>.menu>ul");
    var line = verNav.siblings(".sideLine");
    var curY = verNav.children("li.selected").position().top;
    verNav.find("li").mouseenter(function () {
        var thisY = $(this).position().top;
        line.stop(true, true).animate({
            top: thisY
        }, 200);
        return false
    }).end().mouseleave(function () {
        line.stop(true, true).animate({
            top: curY
        }, 400)
    }).trigger("mouseleave");
};