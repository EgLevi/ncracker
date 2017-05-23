var changePhotoFlag=false;

function clickOnPhoto(e)
{
    window.scrollTo(0,0);
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
        if(!changePhotoFlag)
        {
            showPhotoBtn.click();
        }
    }
}

function mouseOnImg(e)
{
    this.getElementsByTagName('button')[0].style.display='';
}

function mouseOutImg(e)
{
    this.getElementsByTagName('button')[0].style.display='none';
}

function indexOfRow(rows, row)
{
    for(i=0;i<rows.length;i++)
    {
        if(rows[i]==row)
        {
            return i;
        }
    }
    return -1;
}


function deletePhoto(e)
{
    var src = getParent(this).getElementsByTagName('img')[0].src;
    src = src.substr(src.indexOf("/upload"),src.length);
    var row = getParent(getParent(getParent(this)));
    var rows = myPhotoContent.getElementsByTagName('tr');
    getParent(getParent(this)).remove();
    for(i=indexOfRow(rows,row);i<rows.length - 1;i++)
    {
        rows[i].append(rows[i+1].getElementsByTagName('td')[0]);
        if(rows[i+1].getElementsByTagName('td').length==0)
        {
            rows[i + 1].remove();
        }
    }
    $.ajax({
        type : 'DELETE',
        url : "myPhoto", // url записан в параметре action формы
        headers:{'X-CSRF-TOKEN':csrfToken},
        data : src,
        contentType: 'application/json',
        success: function(res) {
            var result;
            try {
                result = JSON.parse(res);
            } catch (e) {
                result = res;
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
    e.stopImmediatePropagation();
    e.stopPropagation();
}
$(document).ready(function () {
    function addEventOnPhoto()
    {
        var cells = myPhotoContent.getElementsByTagName('td');
        for (i=0;i<cells.length;i++)
        {
            cells[i].getElementsByTagName('span')[0].addEventListener("click",clickOnPhoto);
            cells[i].addEventListener("mouseover",mouseOnImg);
            cells[i].addEventListener("mouseout",mouseOutImg);
            cells[i].getElementsByTagName('button')[0].addEventListener('click',deletePhoto);
        }
    }

    addEventOnPhoto()

    function searchImgElement(imgs, str)
    {
        for(i=0;i<imgs.length;i++)
        {
            if(!imgs[i].src.localeCompare(str))
            {
                return i;
            }
        }
        return -1;
    }

    function flipOnShowPhoto(e)
    {
        if(showPhoto.className.localeCompare('modal fade in'))
        {
            return;
        }
        var imgs = myPhotoContent.getElementsByTagName('img');
        var index = searchImgElement(imgs, currentPhoto.src);
        if(index<0)
        {
            return;
        }
        if (e.keyCode == 39)//if press key 'right'
        {
            index++;
            if(index==imgs.length)
            {
                index=0;
            }
        }
        else if (e.keyCode == 37)//if press key 'left'
        {
            index--;
            if(index==-1)
            {
                index=imgs.length-1;
            }
        }
        getParent(imgs[index]).dispatchEvent(new Event("click"));
        changePhotoFlag=true;
    }

    function clickOnCloseMP(e)
    {
        changePhotoFlag=false;
    }

    closeModalPhoto.addEventListener("click",clickOnCloseMP);

    document.addEventListener("keydown",flipOnShowPhoto);

    //showPhoto.addEventListener("click",flipOnShowPhoto);
})