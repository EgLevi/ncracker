function getParent(el){
    return el.parentElement || el.parentNode;
}

function addContentOnModal(obj, widthStyle, heightStyle)
{
    var scale;
    if((obj.naturalWidth<widthStyle)&&(obj.naturalHeight<heightStyle))
    {
        scale=1;
    }
    else if(obj.naturalWidth/widthStyle>obj.naturalHeight/heightStyle)
    {
        scale=widthStyle/obj.naturalWidth;
    }
    else
    {
        scale=heightStyle/obj.naturalHeight;
    }
    var width = obj.naturalWidth*scale;
    var height = obj.naturalHeight*scale;
    obj.style.left = ((width*(1-1/scale) + widthStyle+50 - width)/2)+"px";
    obj.style.top = ((height*(1 - 1/scale) + heightStyle + 30 - height)/2)+"px";
    obj.style.transform="scale("+scale+")";
}

function setScaleOnImg(obj,widthStyle)
{
    var width = obj.naturalWidth*scale;
    var height = obj.naturalHeight*scale;
    obj.style.left = ((width*(1-1/scale) + widthStyle+50 - width)/2)+"px";
    obj.style.top = ((height*(1 - 1/scale) + 430 - height)/2)+"px";
    obj.style.transform="scale("+scale+")";
}
$(document).ready(function () {
var chatId = -1;

})