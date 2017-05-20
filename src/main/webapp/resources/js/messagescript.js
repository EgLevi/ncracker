$(document).ready(function () {
    var dataLink = null;
    var dataId = -1;
    var chatId = document.getElementById("chatId").innerHTML;

    function getData() {
        $.ajax({
            url: ""+chatId+"/chat",
            type: "GET",
            dataType: "json",
            context: document.body,
            error: function () {
                getData();
            },
            success: function (data) {
                var result;
                try {
                    result = JSON.parse(data);
                } catch (e) {
                    result = data;
                }
                if (result.link != null) {
                    $('#chatHistory > div > ul').append('<li class="list-group-item">'+
                        '<div class="media">'+
                        '<div class="media-left">'+
                        '<img src="/'+result.userInfo.photo_ava+'" class="media-object img-rounded" style="width:60px; height:60px;">'+
                        '</div>'+
                        '<div class="media-body">'+
                        '<h3 class="media-heading" style="font-weight: 600;">'+result.username+'</h3>'+
                        '<p>'+result.textMessage+'</p>'+
                        '</div>'+
                        '<div class="media-left">'+
                        '<a href="' + result.link + '" download class="btn btn-link"><span '+
                        'class="glyphicon glyphicon-paperclip"></span> Скачать</a>'+
                        '</div>'+
                        '</div>'+
                        '</li>');
                }
                else
                {
                    $('#chatHistory > div > ul').append('<li class="list-group-item">'+
                        '<div class="media">'+
                        '<div class="media-left">'+
                        '<img src="/'+result.userInfo.photo_ava+'" class="media-object img-rounded" style="width:60px; height:60px;">'+
                        '</div>'+
                        '<div class="media-body">'+
                        '<h3 class="media-heading" style="font-weight: 600;">'+result.username+'</h3>'+
                        '<p>'+result.textMessage+'</p>'+
                        '</div>'+
                        '</div>'+
                        '</li>');
                }
                var cH = document.getElementById("chatHistory");
                cH.scrollTop = cH.scrollHeight;
                getData();
            }
        });
    }

    getData();

    function clickOnSendMsgBtn()
    {
        var formData = {message:{textMessage: message.value}, dataId:dataId};
        badLoad.style.display = "none";
        successLoad.style.display = "none";
        progressConteiner.style.display = "";
        progressBar.style.width = 0 + "%";
        $.ajax({
            url: ""+chatId+"/chat",
            contentType:"application/json",
            data: JSON.stringify(formData),
            type: 'POST',
        });
        message.value = '';
        //$('#file').val('');
        dataId = -1;
    }
    sendMsg.addEventListener("click",clickOnSendMsgBtn);

    function enterDown(event)
    {
        if(!event.shiftKey)
        {
            if(event.keyCode == 13)
            {
                sendMsg.click();
                event.preventDefault();
            }
        }
    }

    document.addEventListener("keydown",enterDown);


    function fileUploaded()
    {
        var formData = new FormData($('#fileForm')[0]);

        $.ajax({
            type : 'POST',
            url : "upload",
            data : formData,
            contentType: false,
            processData: false,
            success: function(res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                dataId = result.dataId;
                dataLink = result.dataLink;
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                badLoad.style.display = "";
                progressConteiner.style.display = "none";
            },
            xhr: function(){
                var xhr = $.ajaxSettings.xhr(); // получаем объект XMLHttpRequest
                xhr.upload.addEventListener('progress', function(evt){ // добавляем обработчик события progress (onprogress)
                    if(evt.lengthComputable) { // если известно количество байт
                        // высчитываем процент загруженного
                        var percentComplete = Math.ceil(evt.loaded / evt.total * 100);
                        // устанавливаем значение в атрибут value тега <progress>
                        // и это же значение альтернативным текстом для браузеров, не поддерживающих <progress>
                        progressBar.style.width = percentComplete + "%";
                        if(percentComplete == 100)
                        {
                            successLoad.style.display = "";
                            progressConteiner.style.display = "none";
                        }
                    }
                }, false);
                return xhr;
            }
        });
    }

    function fileClick(e)
    {
        e.stopPropagation();
    }

    file.addEventListener("click",fileClick);

    file.addEventListener("change",fileUploaded);

    function clickOnfileBtn(e)
    {
        file.click();
        e.preventDefault();
    }

    fileBtn.addEventListener("click",clickOnfileBtn);

})