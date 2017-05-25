$(document).ready(function () {

    function scrollWindow(e)
    {
        window.scrollTo(0,0);
    }

    editBtn.addEventListener("click",scrollWindow);

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var relativePath="none";
    function sendPhoto()
    {
        var formData = new FormData($('#photoeditform')[0]);

        $.ajax({
            type : 'POST',
            url : "postregistrationPhoto", // url записан в параметре action формы
            data : formData,
            contentType: false,
            processData: false,
            success: function(res) {
                if(res.indexOf("fail")>-1)
                {
                    photoError.innerHTML=res;
                    relativePath = "none"
                }
                else
                {
                    var img = document.getElementById("avatar");
                    img.src = res;
                    avaOnPage.src = res;
                    relativePath = res;
                }
            }
        });
    }

    selectPhoto.addEventListener("change",sendPhoto);

    $(function() {
        $('#photoeditform').submit(function() {
            if(relativePath.indexOf("none")>-1)
            {
                closeModal.click();
                return false; // отменяем отправку формы, т.е. перезагрузку страницы
            }
            $.ajax({
                type : 'POST',
                url : "postregistrationPhoto/save", // url записан в параметре action формы
                headers:{'X-CSRF-TOKEN':csrfToken},
                data : relativePath,
                contentType: 'application/json',
                success: function(res) {
                    closeModal.click();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    closeModal.click();
                }
            });
            return false; // отменяем отправку формы, т.е. перезагрузку страницы
        });
    });


    function windowResize(e)
    {
        var witdh;
        if(parseInt(document.documentElement.clientWidth)<500)
        {
            width = parseInt(document.documentElement.clientWidth)*0.8;
        }
        else
        {
            width=450;
        }
        modalPhoto.getElementsByClassName('modal-dialog')[0].style.width = width+"px";
        modalPhoto.getElementsByClassName('panel')[0].style.width = width+"px";
        photoeditform.getElementsByTagName('span')[0].style.width = (width-33)+"px";
    }

    windowResize();

    window.addEventListener('resize',windowResize);

})