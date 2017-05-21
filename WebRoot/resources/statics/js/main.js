var MainController = {
    init: function () {
        MainController.changeMenuClass();
    },
    changeMenuClass: function () {
        $(".left .menu li").removeClass("selected");
        $(".left .menu li").eq(0).addClass("selected");
    }
}

$(document).ready(function () {
    MainController.init();
});