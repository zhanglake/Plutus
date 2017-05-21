var HeadController = {
    init: function () {
        $("#user-name").html($("#main-user").text());
    }
}

$(document).ready(function () {
    HeadController.init();
});