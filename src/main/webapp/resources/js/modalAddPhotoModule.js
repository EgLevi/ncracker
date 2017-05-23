$(document).ready(function () {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var relativePathPhoto="none";

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
                    relativePathPhoto = "none"
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
        var rows = $('#myPhotoContent > table > tbody > tr');
        var lastRow = rows[rows.length - 1];
        var cells = lastRow.getElementsByTagName('td');
        if(cells.length==4)
        {
            $('#myPhotoContent > table > tbody').append('<tr></tr>');
            rows = $('#myPhotoContent > table > tbody > tr');
        }
        lastRow = $('#myPhotoContent > table > tbody > tr:last');
        lastRow.append('<td><span class="button photoBtn" style="padding-right: 0px;padding-left: 0px;">'+
            '<img class="hrefImg media-object" src = "'+relativePathPhoto+'" style="width:120px; height:80px; display: inline-block; margin-top: 10px; margin-left:10px;">'+
        '</span></td>');
        cells = lastRow[0].getElementsByTagName('td');
        cells[cells.length - 1].addEventListener("click",clickOnPhoto);
    }

    function clickOnCloseModalAddPhoto() {
        addPhoto.src="";
        successLoadPhoto.style.display="none";
    }

    closeModalAddPhoto.addEventListener("click",clickOnCloseModalAddPhoto);


    //addPhotoSubmit.addEventListener("click",submitAddPhoto);

    function addPhotoOnModal()
    {
        addPhoto.src=relativePathPhoto;
        addPhoto.onload = function()
        {
            var scale;
            if((addPhoto.naturalWidth<850)&&(addPhoto.naturalHeight<350))
            {
                scale=1;
            }
            else if(addPhoto.naturalWidth/850>addPhoto.naturalHeight/350)
            {
                scale=850/addPhoto.naturalWidth;
            }
            else
            {
                scale=350/addPhoto.naturalHeight;
            }
            var width = addPhoto.naturalWidth*scale;
            var height = addPhoto.naturalHeight*scale;
            addPhoto.style.left = ((width*(1-1/scale) + 900 - width)/2)+"px";
            addPhoto.style.top = ((height*(1 - 1/scale) + 370 - height)/2)+"px";
            addPhoto.style.transform="scale("+scale+")";
        }
    }
})