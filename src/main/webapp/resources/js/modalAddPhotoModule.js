$(document).ready(function () {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var relativePathPhoto="none";

    function scrollWindow(e)
    {
        window.scrollTo(0,0);
    }

    myPhotoPanel.getElementsByClassName('close')[0].addEventListener("click",scrollWindow)

    function sendUserPhoto()
    {
        var formData = new FormData($('#addPhotoForm')[0]);

        $.ajax({
            type : 'POST',
            url : "addPhoto",
            data : formData,
            headers:{'X-CSRF-TOKEN':csrfToken},
            contentType: false,
            processData: false,
            success: function(res) {
                if(res.indexOf("fail")>-1)
                {
                    addPhotoError.innerHTML=res;
                    relativePathPhoto = "none";
                    addPhoto.src="";
                    successLoadPhoto.style.display="none";
                }
                else
                {
                    relativePathPhoto = res;
                    addPhotoOnModal();
                    addPhotoError.innerHTML="";
                }
                successLoadPhoto.style.display="none";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                //closeModalAddPhoto.click();
            }
        });
    }

    addPhotoSelect.addEventListener("change",sendUserPhoto);

    $('#addPhotoForm').submit(function() {
        if(relativePathPhoto.indexOf("none")>-1)
        {
            //closeModalAddPhoto.click();
            return false; // отменяем отправку формы, т.е. перезагрузку страницы
        }
        $.ajax({
            type : 'POST',
            url : "addPhoto/save", // url записан в параметре action формы
            headers:{'X-CSRF-TOKEN':csrfToken},
            data : relativePathPhoto,
            contentType: 'application/json',
            success: function(res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                if(result.indexOf("photo")>-1)
                {
                    alert("фото уже загружено");
                }
                else
                {
                    successLoadPhoto.style.display="";
                    addPhotoOnPanel()
                }
                relativePathPhoto="none";
                //closeModalAddPhoto.click();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                relativePathPhoto="none";
                //closeModalAddPhoto.click();
            }
        });
        return false; // отменяем отправку формы, т.е. перезагрузку страницы
    });

    function addPhotoOnPanel()
    {
        var rect;
        var spans = myPhotoContent.getElementsByTagName('span');
        if(spans.length>0)
        {
            var lastRect = spans[spans.length-1].getBoundingClientRect();
            var length = spans.length;
            for(i=0;i<length;i++)
            {
                rect = spans[spans.length-1].getBoundingClientRect();
                if(rect.top==lastRect.top)
                {
                    hidePanel.prepend(spans[spans.length-1].getElementsByTagName('img')[0]);
                    spans[spans.length-1].remove();
                }
                else
                {
                    break;
                }
            }
        }
        $('#hidePanel').append('<img src="'+relativePathPhoto+'">');
        var imgs = hidePanel.getElementsByTagName('img');
        countImg = 1;
        counter=0;
        imgs[imgs.length - 1].addEventListener("load",photoLoad);
    }

    function clickOnCloseModalAddPhoto() {
        addPhoto.src="";
        successLoadPhoto.style.display="none";
        addPhotoSelect.value = "";
        addPhotoError.value="";
    }

    closeModalAddPhoto.addEventListener("click",clickOnCloseModalAddPhoto);


    //addPhotoSubmit.addEventListener("click",submitAddPhoto);

    function addPhotoOnModal()
    {
        addPhoto.src=relativePathPhoto;
        addPhoto.onload = function()
        {
            var str = showAddPhoto.getElementsByClassName('panel')[0].style.width;
            str = str.replace("px", "");
            var widthStyle = parseInt(str) - 50;
            addContentOnModal(addPhoto, widthStyle, 335);
        }
    }
})