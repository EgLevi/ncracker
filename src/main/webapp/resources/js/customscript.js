$(document).ready(function () {
    var elem = document.getElementById("con-over");
    elem.scrollTop = elem.scrollHeight;

    function clickOnCheck(e) {
        var formData = new FormData($('form')[0]);

        $.ajax({
            type: 'POST',
            url: "registration/check", // url записан в параметре action формы
            data: formData,
            contentType: false,
            processData: false,
            success: function (res) {
                if (res.indexOf("success") > -1) {
                    okIcon.style.color = "#00ff00";
                }
                else {
                    okIcon.style.color = "#ff0000";
                }
            }
        });
    }
    checkBtn.addEventListener("click", clickOnCheck);

})