<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="resources/js/jquery-1.4.2.min.js" type="text/javascript" ></script>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <sec:csrfMetaTags />

    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.2.504/styles/kendo.bootstrap-v4.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/affablebean.css">
    <link rel="stylesheet" type="text/css" href="resources/css/LCstyle.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-attachment:fixed" topmargin="10">
<div class="navbar navbar-default"> <!-- navbar-default navbar-static-top-->
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://localhost:8081/ru.ncteam.levelchat/index.html">
                <img src="resources/images/logo4.png" alt="Logo" height="40" align="top">
            </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="http://localhost:8082/ru.ncteam.levelchat/index.html">Главная</a></li>
                <li><a href="http://localhost:8082/ru.ncteam.levelchat/postregistration#">Поддержка</a></li>
                <li><a href="http://localhost:8082/ru.ncteam.levelchat/postregistration#">О нас</a></li>
                <li><a href="http://localhost:8082/ru.ncteam.levelchat/contact.html">Контакты</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</div>
<div class="container-fluid" style="width:730px;">
    <div class="row col-md-12" style="width:430px; margin:auto;">
        <div class="panel panel-primary" style="background-color:#e5e8ed">
            <div class="panel-heading" style="background-color:#e5e8ed">
                <div style="width:80%; margin:auto; text-align: center; transform:scale(1.5);">
                    <img src="resources/images/search-title.png" alt="Logo" height="40" align="top">
                </div>
            </div>
            <div class="panel-body table-responsive" style="height: 420px">
                <div style="width:40%; margin-left: auto;margin-right: auto;margin-top: 100px;">
                    <div style="margin-top:10px;"><label class="fontface">Интересы</label></div>
                    <%--Пользователи для чата--%>
                        <div class="table-responsive" style="height: 130px;">
                            <div id="usersForChat" class="btn-group-vertical table-responsive" style="width:100%;">
                                <div id="fdivUFC">
                                    <label id="flabelusers" style="display:none">0</label>
                                    <button id="fusersForChat" type="button" class="btn btn-default fontface" style="display:none; width:100%">пользователь 1</button>
                                </div>
                            </div>
                        </div>
                    <%--end--%>
                    <div class="btn" style="transform:scale(2); width:100%; margin:auto;">
                        <!--<button type="button" class="btn btn-primary btn-block btn-lg" ></button>-->
                        <img src="resources/images/search.png" alt="Logo" height="40" align="top">
                    </div>
                    <div style="margin-top: 15px">
                        <div style="width:100%; margin-left: auto; margin-right: auto;text-align:center">
                            <button type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal">Параметры поиска собеседника</button>
                            <!-- Modal -->
                            <div class="modal fade" id="myModal" role="dialog" style="position:absolute;">
                                <div class="modal-dialog my-modal-dialog" style="">
                                    <div class="panel panel-primary" style="background-color:#e5e8ed; width: 400px; height:483px;">
                                        <div class="panel-heading" style="background-color:#e5e8ed">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <img src="resources/images/interest_for_search.png" alt="Logo" height="40" align="top" style="transform: scale(1);">
                                        </div>
                                        <div class="panel-body" style="height: 75%">
                                            <div>
                                                <div style="width:100px;margin:auto;">
                                                    <label class="fontface" style="text-align: center">Выбранные интересы</label>
                                                </div>
                                                <div id="listInterest" style="overflow: auto; height:55px;">
                                                    <div id="flistelement" class="listSelInterest" style="display: none;">
                                                        <label id="flabel" style="display: none;">0</label>
                                                        <span id="fspan" class="label fontface selInterest">Default Label</span>
                                                        <div id="fbtn" class="btn remBtn"><span class="glyphicon glyphicon-remove"></span></div>
                                                    </div>
                                                </div>
                                                <div style="margin-top:20px">
                                                    <label class="fontface">Категория</label>
                                                    <select id="category" class="form-control fontface" style="width:100%">
                                                    </select>

                                                    <div style="margin-top:10px;"><label class="fontface">Интересы</label></div>
                                                    <div class="table-responsive" style="height: 130px;">
                                                        <div id="interests" class="btn-group-vertical table-responsive" style="width:100%;">
                                                            <div id="fdivinterest">
                                                                <label id="flabelinterest" style="display:none">0</label>
                                                                <button id="finterest" type="button" class="btn btn-default fontface" style="display:none; width:100%">Apple</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-footer" style="background-color:#e5e8ed;">
                                            <div style="margin-left:auto; width:35%">
                                                <button id="okBtn" type="button" class="btn btn-primary">OK</button>
                                                <button id="cancelBtn" type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>

            </div>
        </div>

    </div> <!-- /.col-md-2 col-sm-12-->
</div>
<style>
    .inputFile input[type="file"]{

        display: none;/* скрываем input file */

    }
</style>


<%--
TODO: Добавить обработчик на алгоритм поиска
1) в нём должен быть инсерт в InterestList по interestId
2) запомнить группу InterestGroup
TODO: Так же создание чата по первому пользователю из этого алгоритма(скопировать старый код из другого проекта)
TODO: DASHBOARD!!!
--%>
<style>

    .remBtn
    {
        padding-left: 0px;
        padding-right: 5px;
        padding-top: 0px;
    }
    .selInterest
    {
        color: grey;
        border-style: solid;
        border-top-width: 2px;
        border-bottom-width: 2px;
        border-right-width: 2px;
        border-color: #337ab7;
        border-left-width: 2px;
    }
    .fontface
    {
        font-family: "segoe ui";
    }
    .my-modal-dialog
    {
        top:0px;
        left:15px;
        margin-top:0px;
        margin-bottom:0px;
    }
</style>

<script type="text/javascript">

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
    function getUsersForChat(e) {
        $.ajax({
            type:'GET',
            url:"search/getUsers",
            contentType: 'application/json',
            dataType: "json",
            success: function (res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }

                var childList = usersForChat.getElementsByTagName("div");
                var length=childList.length;

                for(i=0;i<length-1;i++)
                {
                    childList[1].remove();
                }
                var element;

                for (i = 0; i < result.length; i++) {
                    element = $("#fdivUFC").clone();
                    element[0].getElementsByTagName("button")[0].innerHTML = result[i].interestName;
                    element[0].getElementsByTagName("button")[0].addEventListener("click",clickOnInterest);
                    element[0].getElementsByTagName("button")[0].style.display = "block";
                    interests.appendChild(element[0]);
                }
            },
            error:function (jqXHR, textStatus, errorThrown) {
            }
        })

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

    function createListUsers() {

        
    }

    getCategory();
</script>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

<script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
</body>
</html>

