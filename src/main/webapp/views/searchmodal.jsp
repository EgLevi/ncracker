<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="resources/js/jquery-1.4.2.min.js" type="text/javascript" ></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
    <link rel="stylesheet" type="text/css" href="../resources/css/affablebean.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/bootstrap_min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/LCstyle.css">
    <script src="../resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
</head>
<body style="background-attachment:fixed" topmargin="10">
<div class="container-fluid" style="width:730px;">
    <div class="row col-md-12" style="width:430px; margin:auto;">
        <div class="panel panel-primary" style="background-color:#e5e8ed; width: 400px">
            <div class="panel-heading" style="background-color:#e5e8ed">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <img src="resources/images/interest_for_search.png" alt="Logo" height="40" align="top" style="transform: scale(1);">
            </div>
            <div class="panel-body table-responsive" style="height: 500px">
                <div>
                    <div style="width:100px;margin:auto;">
                        <label class="fontface" style="text-align: center">Выбранные интересы</label>
                    </div>
                    <div class="table-responsive" style="height:45px; width:100%">
                        <div id="listInterest" style="width:100%">
                            <label id="flabel" style="display:none">0</label>
                            <span id="fspan" class="label fontface selInterest" style="display: none;">Default Label</span>
                            <div id="fbtn" class="btn remBtn" style="display: none"><span class="glyphicon glyphicon-remove"></span></div>
                        </div>
                    </div>
                    <div style="margin-top:20px">
                        <label class="fontface">Категория</label>
                        <select id="category" class="form-control fontface" style="width:100%">
                        </select>

                        <div style="margin-top:10px;"><label class="fontface">Интересы</label></div>
                        <div id="interests" class="btn-group-vertical" style="width:100%;">
                            <div id="fdivinterest">
                                <label id="flabelinterest" style="display:none">0</label>
                                <button id="finterest" type="button" class="btn btn-default fontface" style="display:none; width:100%">Apple</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-footer" style="background-color:#e5e8ed;">
                <div style="margin-left:auto; width:35%">
                    <button type="button" class="btn btn-primary">OK</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>

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
</style>
<script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

<script type="text/javascript">


    var data=[];


    function getParent(el){
        return el.parentElement || el.parentNode;
    }


    function deleteInterest(e)
    {
        var list = listInterest.getElementsByClassName("label");
        var listBtn = listInterest.getElementsByTagName("div");
        var listLabel = listInterest.getElementsByTagName("label");
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
            listLabel[index].remove();
            list[index].remove();
            this.remove();
        }
        else
        {
            return;
        }
    }


    function clickOnInterest(e)
    {
        var parentEl = getParent(this);
        var element = $("#flabel").clone();
        element[0].innerHTML = parentEl.getElementsByTagName("label")[0].innerHTML;
        listInterest.appendChild(element[0]);
        element = $("#fspan").clone();
        element[0].innerHTML = this.innerHTML;
        element[0].style.display = "inline";
        listInterest.appendChild(element[0]);
        element = $("#fbtn").clone();
        element[0].addEventListener("click",deleteInterest);
        element[0].style.display = "inline";
        listInterest.appendChild(element[0]);
        data.push({ "interestId": parentEl.getElementsByTagName("label")[0].innerHTML, "interestName": this.innerHTML });
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
                for(i=0;i<childList.length-1;i++)
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
</script>
</body>
</html>

