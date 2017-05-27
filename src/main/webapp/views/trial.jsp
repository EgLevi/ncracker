<button id="sign" style="position: absolute; top:50%;right:50%;">Присоединиться</button>
<div id="cursor" style="width:20px; height:20px; position: absolute;display:none; z-index: 10">
<img src="/resources/images/cursor.png" style="width:14px; height:20px;">
</div>
<button id="okBtn" class="btn btn-primary" style="position: absolute; top:2%;right:2%;">OK</button>

<script src="../resources/js/jquery-3.2.0.min.js"></script>

<link rel="stylesheet" href="../resources/css/bootstrap.min.css">

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
       cursor.style.display = "";
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
            $(this).toggleClass('active');
            okBtn.focus();
            clearTimeout(time);
        },150);
    }

    okBtn.addEventListener("click",clickOnOKBtn);

    function replace()
    {
        if(topCursor<B['y'])
        {
            clearInterval(timer);
            okBtn.click();
            return;
        }
        topCursor-=3*Math.abs(ratio);
        leftCursor+=3;
        cursor.style.top=topCursor+"px";
        cursor.style.left=leftCursor+"px";
    }
    sign.addEventListener("click",clickOnSign);
</script>
