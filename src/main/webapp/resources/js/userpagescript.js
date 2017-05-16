$(document).ready(function () {
var csrfToken = $("meta[name='_csrf']").attr("content");
var relativePath="none";


function showEditUserInfo(e)
{
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    editUserInfoTable.style.display = "";
    e.preventDefault();
}

function closeEditUserInfo()
{
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    userinfotable.style.display = "";
    serchCompanionBtn.style.display = "";
}

closeEditUserInfoBtn.addEventListener("click",closeEditUserInfo);

edituserinfoBtn.addEventListener("click",showEditUserInfo);

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

function sendUserInfo(e)
{
    var formData={};
    if(document.getElementsByName("name")[0].value!="")
    {
        formData.name=document.getElementsByName("name")[0].value;
    }
    if(document.getElementsByName("surname")[0].value!="")
    {
        formData.surname=document.getElementsByName("surname")[0].value;
    }
    if(document.getElementsByName("sex")[0].value!="")
    {
        formData.sex=document.getElementsByName("sex")[0].value;
    }
    if(document.getElementsByName("country")[0].value!="")
    {
        formData.country=document.getElementsByName("country")[0].value;
    }
    if(document.getElementsByName("city")[0].value!="")
    {
        formData.city=document.getElementsByName("city")[0].value;
    }
    if(document.getElementsByName("age")[0].value!="")
    {
        formData.age=document.getElementsByName("age")[0].value;
    }
    if(document.getElementsByName("email")[0].value!="")
    {
        formData.email=document.getElementsByName("email")[0].value;
    }
    $.ajax({
        type : 'POST',
        url : "edituserinfo", // url записан в параметре action формы
        headers:{'X-CSRF-TOKEN':csrfToken},
        data : JSON.stringify(formData),
        contentType: 'application/json',
        success: function(res) {
            var result;
            try {
                result = JSON.parse(res);
            } catch (e) {
                result = res;
            }
            if(result == "")
            {
                var listLabel = $('#aboutUserTable > h4');
                if(document.getElementsByName("age")[0].value!="")
                {
                    listLabel[0].innerHTML='<label>Возраст:</label>'+document.getElementsByName("age")[0].value;
                }
                if(document.getElementsByName("sex")[0].value!="")
                {
                    listLabel[4].innerHTML='<label>Пол:</label>'+document.getElementsByName("sex")[0].value;
                }
                if(document.getElementsByName("email")[0].value!="")
                {
                    listLabel[3].innerHTML='<label>email:</label>'+document.getElementsByName("email")[0].value;
                }
                if(document.getElementsByName("country")[0].value!="")
                {
                    listLabel[1].innerHTML='<label>Страна:</label>'+document.getElementsByName("country")[0].value;
                }
                if(document.getElementsByName("city")[0].value!="")
                {
                    listLabel[2].innerHTML='<label>Город:</label>'+document.getElementsByName("city")[0].value;
                }
                var username = userinfotable.getElementsByTagName("h1");
                var innerStr = username[0].innerHTML;
                var name = innerStr.substr(0,innerStr.indexOf(" "));
                var surname = innerStr.substr(innerStr.indexOf(" "),innerStr.length);
                if(document.getElementsByName("name")[0].value!= "")
                {
                    username[0].innerHTML = document.getElementsByName("name")[0].value;
                }
                else
                {
                    username[0].innerHTML=name;
                }
                if(document.getElementsByName("surname")[0].value!= "")
                {
                    username[0].innerHTML = username[0].innerHTML +" " +document.getElementsByName("surname")[0].value;
                }
                else
                {
                    username[0].innerHTML = username[0].innerHTML + " " + surname;
                }
                resetErrorLabels();
                closeEditUserInfoBtn.click();
                e.preventDefault();
                return;
            }
            for (var field in result)
            {
                document.getElementById(field).innerHTML = result[field];
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            resetErrorLabels();
            closeEditUserInfoBtn.click();
            e.preventDefault();
        }
    });
    formData=undefined;
    return;
}

function resetErrorLabels()
{
    document.getElementById("emailError").innerHTML = "";
    document.getElementById("countryError").innerHTML = "";
    document.getElementById("cityError").innerHTML = "";
    document.getElementById("nameError").innerHTML = "";
    document.getElementById("surnameError").innerHTML = "";
    document.getElementById("ageError").innerHTML = "";
}

saveUserInfoBtn.addEventListener("click",sendUserInfo);

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

var dataLink = null;
var dataId = -1;
var chatId = -1;
function getParent(el){
    return el.parentElement || el.parentNode;
}

function clickOnImg()
{
    var parent = getParent(getParent(this));
    var link = parent.getElementsByTagName("a")[0];
    link.click();
}

function getData() {
    $.ajax({
        url: "chats/"+chatId+"/chat",
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
                $('#chatHistory'+chatId+'> div > ul').append('<li class="list-group-item">'+
                    '<div class="media">'+
                    '<div class="media-left">'+
                    '<img src="'+result.userInfo.photo_ava+'" class="media-object img-rounded" style="width:60px; height:60px;">'+
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
                $('#chatHistory'+chatId+'> div > ul').append('<li class="list-group-item">'+
                    '<div class="media">'+
                    '<div class="media-left">'+
                    '<img src="'+result.userInfo.photo_ava+'" class="media-object img-rounded" style="width:60px; height:60px;">'+
                    '</div>'+
                    '<div class="media-body">'+
                    '<h3 class="media-heading" style="font-weight: 600;">'+result.username+'</h3>'+
                    '<p>'+result.textMessage+'</p>'+
                    '</div>'+
                    '</div>'+
                    '</li>');
            }
            var cH = document.getElementById("chatHistory"+chatId);
            cH.scrollTop = cH.scrollHeight;
            getData();
        }
    });
}

function getAllUserMessages()
{

    $.ajax({
        url: "allMessages",
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
            var msg="";
            for(i=0;i<result.length;i++)
            {
                if(result[i].textMessage != null)
                {
                    msg = result[i].textMessage;
                }
                if (result[i].userData != null) {
                    $('#chatHistory'+result[i].chat.chatId+'> div > ul').append('<li class="list-group-item">'+
                        '<div class="media">'+
                        '<div class="media-left">'+
                        '<img src="'+result[i].userInfo.photo_ava+'" class="media-object img-rounded" style="width:60px; height:60px;">'+
                        '</div>'+
                        '<div class="media-body">'+
                        '<h3 class="media-heading" style="font-weight: 600;">'+result[i].userInfo.name+'</h3>'+
                        '<p>'+msg+'</p>'+
                        '</div>'+
                        '<div class="media-left">'+
                        '<a href="' + result[i].userData.dataLink + '" download class="btn btn-link"><span '+
                        'class="glyphicon glyphicon-paperclip"></span> Скачать</a>'+
                        '</div>'+
                        '</div>'+
                        '</li>');
                }
                else
                {
                    $('#chatHistory'+result[i].chat.chatId+'> div > ul').append('<li class="list-group-item">'+
                        '<div class="media">'+
                        '<div class="media-left">'+
                        '<img src="'+result[i].userInfo.photo_ava+'" class="media-object img-rounded" style="width:60px; height:60px;">'+
                        '</div>'+
                        '<div class="media-body">'+
                        '<h3 class="media-heading" style="font-weight: 600;">'+result[i].userInfo.name+'</h3>'+
                        '<p>'+msg+'</p>'+
                        '</div>'+
                        '</div>'+
                        '</li>');
                }
                msg="";
            }

        }
    });
}

getAllUserMessages();

function clickOnSendMsgBtn()
{
    var formData = {message:{textMessage: message.value}, dataId:dataId};
    badLoad.style.display = "none";
    successLoad.style.display = "none";
    progressConteiner.style.display = "";
    progressBar.style.width = 0 + "%";
    $.ajax({
        url: "chats/"+chatId+"/chat",
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
        url : "chats/upload",
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

file.addEventListener("change",fileUploaded);

/*$("#file").change(function (event) {
 var file = $('#file').val();
 console.log(file);

 if (file != '') {
 var formData = new FormData();
 formData.append('file', $('input[type=file]')[0].files[0]);
 $.ajax({
 url: 'upload',
 data: formData,
 dataType: "json",
 processData: false,
 contentType: false,
 type: 'POST',
 success: function (data) {
 var json = $.evalJSON($.toJSON(data));
 dataId = json.dataId;
 dataLink = json.dataLink;
 $('#labe').html("Success upload");
 },
 error: function (err) {
 alert("Не удалось загрузить файл на сервер");
 }
 });

 }

 });*/


//elem.scrollTop = elem.scrollHeight;




function showChatPanel(e)
{
    var a = this.getElementsByTagName("a")[0];
    chatId = a.attributes[0].value;
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    chatPanel.style.display = "";
    var chats = chatPanel.getElementsByClassName("panel-body");
    for(i=0;i<chats.length;i++)
    {
        chats[i].style.display = "none";
    }
    var cH = document.getElementById("chatHistory"+chatId);
    cH.style.display = "";
    cH.scrollTop = cH.scrollHeight;
    getData();
    e.preventDefault();
}

function addEventOnChats()
{
    var buttons = listChats.getElementsByTagName('li');
    for(i=0;i<buttons.length;i++)
    {
        buttons[i].addEventListener("click",showChatPanel);
    }
}

addEventOnChats();

function showChatListPanel(event)
{
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    chatListPanel.style.display = "";
    event.preventDefault();
}

menuMyMessage.addEventListener("click",showChatListPanel);

function showSearchPanel(event)
{
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    searchPanel.style.display="";
    event.preventDefault();
}

menuSearch.addEventListener("click",showSearchPanel);

function showMainPanels(event)
{
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    userinfotable.style.display="";
    menuSearch.style.display="";
    event.preventDefault();
}

menuMain.addEventListener("click",showMainPanels);



function clickOnPhoto(e)
{
    var img = this.getElementsByTagName("img")[0];
    currentPhoto.src=img.src;
    currentPhoto.onload = function()
    {
        var scale;
        if((currentPhoto.naturalWidth<850)&&(currentPhoto.naturalHeight<400))
        {
            scale=1;
        }
        else if(currentPhoto.naturalWidth/850>currentPhoto.naturalHeight/400)
        {
            scale=850/currentPhoto.naturalWidth;
        }
        else
        {
            scale=400/currentPhoto.naturalHeight;
        }
        var width = currentPhoto.naturalWidth*scale;
        var height = currentPhoto.naturalHeight*scale;
        currentPhoto.style.left = ((width*(1-1/scale) + 900 - width)/2)+"px";
        currentPhoto.style.top = ((height*(1 - 1/scale) + 430 - height)/2)+"px";
        currentPhoto.style.transform="scale("+scale+")";
        showPhotoBtn.click();
    }
}

function getUserPhotos()
{
    $.ajax({
        url: "userPhoto",
        type: "GET",
        dataType: "json",
        error: function(jqXHR, textStatus, errorThrown) {
        },
        success: function (data) {
            var result;
            try {
                result = JSON.parse(data);
            } catch (e) {
                result = data;
            }
            for (i=0; i<result.length; i++)
            {
                $('#myPhotoContent').append('<span class="button photoBtn" style="padding-right: 0px;padding-left: 0px;"><img class="hrefImg media-object" src = "'+result[i]+'"' +
                    ' style="width:120px; height:80px; display: inline-block; margin-top: 10px; margin-left:10px;">' +
                    '</span>');
                var spans = myPhotoContent.getElementsByTagName("span");
                var lastAddElement = spans[spans.length-1];
                lastAddElement.addEventListener("click",clickOnPhoto);
                /*$('#myPhotoContent').append('<img src="'+result[i]+'" class="media-object" ' +
                 'style="width:120px; height:80px; display: inline-block; margin-top: 10px; margin-left:10px;">');*/
            }
        }
    });
}

function showMyPhotoPanel(event)
{
    var remEl = $(".removableElement");
    for(i=0;i<remEl.length;i++)
    {
        remEl[i].style.display = "none";
    }
    myPhotoPanel.style.display="";
    getUserPhotos();
    event.preventDefault();
}

menuPhoto.addEventListener("click",showMyPhotoPanel);


function SearchScriptObject()
{
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");

    $.ajaxSetup({
        headers: {csrfHeader:csrfToken}
    });

    var data=[];

    function clearListSelectedInterest()
    {
        var childList = listInterest.getElementsByClassName("listSelInterest");
        var length=childList.length;
        for(i=0;i<length-1;i++)
        {
            childList[1].remove();
        }
    }

    function clickOnOkBtn(e)
    {
        if (data.length > 0)//если есть данные на обновление, то посылаем соответствующий запрос
        {
            $.ajax({
                type: 'POST',
                url: "search",
                headers:{'X-CSRF-TOKEN':csrfToken},
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (res) {
                    cancelBtn.click();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
            data = [];
            clearListSelectedInterest()
        }
        else
        {
            cancelBtn.click();
        }
    }

    okBtn.addEventListener("click",clickOnOkBtn);



    function getParent(el){
        return el.parentElement || el.parentNode;
    }


    function deleteInterest(e)
    {
        var listBtn = listInterest.getElementsByClassName("btn");
        var index;
        for(i=0;i<listBtn.length;i++)
        {
            if(listBtn[i]==this)
            {
                index=i;
                break;
            }
        }
        if(!!index)
        {
            var parent = getParent(listBtn[index]);
            var indexInData = indexDataByInterestId(parent.getElementsByTagName("label")[0].innerHTML);
            data.splice(indexInData,1);
            parent.remove();
        }
        else
        {
            return;
        }
    }

    function existInData(interestId)
    {
        for(i=0;i<data.length;i++)
        {
            if(data[i].interestId == interestId)
            {
                return true;
            }
        }
        return false;
    }

    function indexDataByInterestId(interestId)
    {
        for(i=0;i<data.length;i++)
        {
            if(data[i].interestId == interestId)
            {
                return i;
            }
        }
        return -1;
    }

    function clickOnInterest(e)
    {
        var parentEl = getParent(this);
        if(!existInData(parentEl.getElementsByTagName("label")[0].innerHTML))
        {
            var element = $("#flistelement").clone();
            element[0].getElementsByTagName("label")[0].innerHTML = parentEl.getElementsByTagName("label")[0].innerHTML;
            element[0].getElementsByTagName("span")[0].innerHTML = this.innerHTML;
            element[0].style.display = "inline";
            element[0].getElementsByTagName("div")[0].addEventListener("click",deleteInterest);
            listInterest.appendChild(element[0]);
            //если такого элемента нет, то вносим его в список интересов
            data.push({ "interestId": parentEl.getElementsByTagName("label")[0].innerHTML, "interestName": this.innerHTML });
        }
    }

    function getInterestsByCategory(e)
    {
        $.ajax({
            type: 'GET',
            url: "search/getInterests/"+category.options[category.selectedIndex].innerText,
            contentType: 'application/json',
            dataType: "json",
            success: function (res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }

                var childList = interests.getElementsByTagName("div");
                var length=childList.length;
                for(i=0;i<length-1;i++)
                {
                    childList[1].remove();
                }

                var element;

                for (i = 0; i < result.length; i++) {
                    element = $("#fdivinterest").clone();
                    element[0].getElementsByTagName("label")[0].innerHTML=result[i].interestId;
                    element[0].getElementsByTagName("button")[0].innerHTML = result[i].interestName;
                    element[0].getElementsByTagName("button")[0].addEventListener("click",clickOnInterest);
                    element[0].getElementsByTagName("button")[0].style.display = "block";
                    interests.appendChild(element[0]);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }

    category.addEventListener("change",getInterestsByCategory);

    function getCategory()
    {

        $.ajax({
            type: 'GET',
            url: "search/getCategories",
            contentType: 'application/json',
            dataType: "json",
            success: function (res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                var element;
                for (i = 0; i < result.length; i++) {
                    element = document.createElement("option");
                    element.innerHTML=result[i].categoryName;
                    category.append(element);
                }
                category.options[0].select=true;
                getInterestsByCategory();
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }

    getCategory();
}
var searchObj = new SearchScriptObject();
})