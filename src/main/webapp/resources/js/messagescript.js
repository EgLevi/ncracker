$(document).ready(function () {

    function scrolChatTowardBottom() {
        var cH = document.getElementById("chatHistory");
        cH.scrollTop = cH.scrollHeight;
    }

    scrolChatTowardBottom();

    var dataLink = null;
    var dataId = -1;
    var chatId = document.getElementById("chatId").innerHTML;

    function getData() {
        $.ajax({
            url: "" + chatId + "/chat",
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
                    $('#chatHistory > div > ul').append('<li class="list-group-item">' +
                        '<div class="media">' +
                        '<div class="media-left">' +
                        '<img src="/' + result.userInfo.photo_ava + '" class="media-object img-rounded" style="width:60px; height:60px;">' +
                        '</div>' +
                        '<div class="media-body">' +
                        '<h5 class="media-heading" style="font-weight: 600;">' + result.username + '</h5>' +
                        '<p>' + result.textMessage + '</p>' +
                        '</div>' +
                        '<div class="media-left">' +
                        '<a href="' + result.link + '" download class="btn btn-link"><span ' +
                        'class="glyphicon glyphicon-paperclip"></span> Скачать</a>' +
                        '</div>' +
                        '</div>' +
                        '</li>');
                }
                else {
                    $('#chatHistory > div > ul').append('<li class="list-group-item">' +
                        '<div class="media">' +
                        '<div class="media-left">' +
                        '<img src="/' + result.userInfo.photo_ava + '" class="media-object img-rounded" style="width:60px; height:60px;">' +
                        '</div>' +
                        '<div class="media-body">' +
                        '<h5 class="media-heading" style="font-weight: 600;">' + result.username + '</h5>' +
                        '<p>' + result.textMessage + '</p>' +
                        '</div>' +
                        '</div>' +
                        '</li>');
                }
                var cH = document.getElementById("chatHistory");
                cH.scrollTop = cH.scrollHeight;
                getData();
            }
        });
    }

    function scrollChatToBottom(e) {
        if (this.scrollTop == (this.scrollHeight - this.clientHeight))//если прокручен до конца
        {
            $.ajax({
                url: "" + chatId + "/read",
                type: "GET",
                error: function () {
                },
                success: function (data) {
                }
            });
        }
    }

    function mouseOnMessages(e) {
        document.body.style.overflow = 'hidden';
        document.body.style.paddingRight = '17px';
    }

    function mouseOutMessages(e) {
        document.body.style.overflow = 'auto';
        document.body.style.paddingRight = '0px';
    }

    document.getElementById("chatHistory").addEventListener("scroll", scrollChatToBottom);

    document.getElementById("chatHistory").addEventListener("mouseover", mouseOnMessages);

    document.getElementById("chatHistory").addEventListener("mouseout", mouseOutMessages);

    getData();

    function clickOnSendMsgBtn() {
        var formData = {message: {textMessage: message.value}, dataId: dataId};
        badLoad.style.display = "none";
        successLoad.style.display = "none";
        progressConteiner.style.display = "";
        progressBar.style.width = 0 + "%";
        $.ajax({
            url: "" + chatId + "/chat",
            contentType: "application/json",
            data: JSON.stringify(formData),
            type: 'POST',
        });
        message.value = '';
        //$('#file').val('');
        dataId = -1;
    }

    sendMsg.addEventListener("click", clickOnSendMsgBtn);

    function enterDown(event) {
        if (!event.shiftKey) {
            if (event.keyCode == 13) {
                if(message.value!="")
                {
                    sendMsg.click();
                }
                event.preventDefault();
            }
        }
    }

    document.addEventListener("keydown", enterDown);


    function fileUploaded() {
        var formData = new FormData($('#fileForm')[0]);

        $.ajax({
            type: 'POST',
            url: "upload",
            data: formData,
            contentType: false,
            processData: false,
            success: function (res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                dataId = result.dataId;
                dataLink = result.dataLink;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                badLoad.style.display = "";
                progressConteiner.style.display = "none";
            },
            xhr: function () {
                var xhr = $.ajaxSettings.xhr(); // получаем объект XMLHttpRequest
                xhr.upload.addEventListener('progress', function (evt) { // добавляем обработчик события progress (onprogress)
                    if (evt.lengthComputable) { // если известно количество байт
                        // высчитываем процент загруженного
                        var percentComplete = Math.ceil(evt.loaded / evt.total * 100);
                        // устанавливаем значение в атрибут value тега <progress>
                        // и это же значение альтернативным текстом для браузеров, не поддерживающих <progress>
                        progressBar.style.width = percentComplete + "%";
                        if (percentComplete == 100) {
                            successLoad.style.display = "";
                            progressConteiner.style.display = "none";
                        }
                    }
                }, false);
                return xhr;
            }
        });
    }

    function fileClick(e) {
        e.stopPropagation();
    }

    file.addEventListener("click", fileClick);

    file.addEventListener("change", fileUploaded);

    function clickOnfileBtn(e) {
        file.click();
        e.preventDefault();
    }

    fileBtn.addEventListener("click", clickOnfileBtn);

    function startWindow()
    {
        var width = parseInt(document.documentElement.clientWidth);
        if((width < 610) || ((width > 760) && (width < 1000)))
        {
            messageBlock.className="col-xs-7 center-block";
            senderBlock.className = "col-xs-5";
        }
        if((width > 610) && !((width > 760) && (width < 1000)))
        {
            messageBlock.className="col-xs-9 center-block";
            senderBlock.className = "col-xs-3";
        }
        if((width < 400))
        {
            messageBlock.className="col-xs-6 center-block";
            senderBlock.className = "col-xs-6";
        }
    }

    startWindow();

    function windowResize(e)
    {
        var width = parseInt(document.documentElement.clientWidth);
        if((width < 610) || ((width > 760) && (width < 1000)))
        {
            messageBlock.className="col-xs-7 center-block";
            senderBlock.className = "col-xs-5";
        }
        if((width > 610) && !((width > 760) && (width < 1000)))
        {
            messageBlock.className="col-xs-9 center-block";
            senderBlock.className = "col-xs-3";
        }
        if((width < 400))
        {
            messageBlock.className="col-xs-6 center-block";
            senderBlock.className = "col-xs-6";
        }
    }

    window.addEventListener('resize',windowResize);

})