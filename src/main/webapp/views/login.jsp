<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Авторизация</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/styles.css">
    <script src="../resources/js/globalscript.js"></script>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/jquery-3.2.0.min.js"></script>
    <script src="../resources/js/customscript.js"></script>
</head>
<body>

<img src="/resources/images/demoApp.png" style=" width:75%; height:90%;opacity:0.5;">
<a id="sign" class="btn btn-link" style="position: absolute; top:25%;right:50%;"><h1>Хочу так же</h1></a>
<div id="cursor" style="width:20px; height:20px; position: absolute;display:none; z-index: 10">
    <img src="/resources/images/cursor.png" style="width:14px; height:20px;">
</div>


<div class="container text-center">
    <div class="row">

        <div style="position:absolute; top:5px; right:5px;width: 25%; height:40%;">
            <div class="panel well"
            <jsp:include page="modules/loginModule.jsp"/>
        </div>


    </div>
</div>
</div>

<script src="../resources/js/bootstrap.min.js"></script>
<script>
    var topCursor;
    var leftCursor;
    var timer;
    var A=new Object();
    var B = new Object();

    var ratio;
    function getTargetPoint()
    {
        var rect = okBtn.getBoundingClientRect();
        B['x']=(rect.left+rect.right)/2;
        B['y']=(rect.top+rect.bottom)/2;
    }

    getTargetPoint();

    function clickOnSign(e)
    {
        if(cursor.style.display != "none")
        {
            return;
        }
        cursor.style.display = "";
        var parent = getParent(this);
        cursor.style.top = e.clientY+'px';
        cursor.style.left = e.clientX + 'px';
        A['x']=e.clientX;
        A['y']=e.clientY;
        ratio = (B['y']-A['y'])/(B['x']-A['x']);
        topCursor=e.clientY;
        leftCursor=e.clientX;
        timer = setInterval(replace,4);
    }


    function clickOnOKBtn(e)
    {
        timer = setTimeout(function()
        {
            $(this).toggleClass('hover');
            okBtn.focus();
            okBtn.click();
            clearTimeout(timer);
        },150);
    }

    okBtn.addEventListener("click",clickOnOKBtn);

    function replace()
    {
        if(topCursor>B['y'])
        {
            clearInterval(timer);
            clickOnOKBtn();
            return;
        }
        topCursor+=3*ratio;
        leftCursor+=3;
        cursor.style.top=topCursor+"px";
        cursor.style.left=leftCursor+"px";
    }
    sign.addEventListener("click",clickOnSign);
</script>

<jsp:include page="modules/footerModule.jsp"/>

</body>
</html>

