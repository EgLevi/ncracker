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
$(document).ready(function () {
    function addEventOnPhoto()
    {
        var cells = myPhotoContent.getElementsByTagName('td');
        for (i=0;i<cells.length;i++)
        {
            cells[i].getElementsByTagName('span')[0].addEventListener("click",clickOnPhoto);
        }
    }

    addEventOnPhoto()
})