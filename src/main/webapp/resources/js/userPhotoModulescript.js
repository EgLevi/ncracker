var changePhotoFlag=false;
var closeMPBtnClick = false;

var countImg = 0;
var counter =0;



function photoLoad(e)
{
    counter++;
    if(counter==countImg)
    {
        imgAlignment(hidePanel);
    }
}

function imgAlignment(imgSource)
{
    var str;
    var maxHeight=120;
    var widthPanel = myPhotoContent.clientWidth-20;
    var sumWidth=0;
    str="";
    var index=0;
    var imgs = imgSource.getElementsByTagName('img');
    var scale;
    var scales;
    var indexes;
    var strScale;
    while(index<imgs.length)
    {
        sumWidth=0;
        str="";
        scales = new Array();
        indexes = new Array();
        while(index<imgs.length)
        {
            scale=maxHeight/imgs[index].naturalHeight;
            if((sumWidth+scale*imgs[index].naturalWidth+5)>widthPanel)
            {
                scale=(widthPanel - 5*scales.length)/sumWidth;
                for(i=0;i<scales.length;i++)
                {
                    scales[i]=scales[i]*scale;
                    sumWidth*=scale;
                }
                break;
            }
            else
            {
                scales.push(scale);
                indexes.push(index);
                sumWidth+=scale*imgs[index].naturalWidth+5;
            }
            index++;
        }
        if(sumWidth<widthPanel)
        {
            scale=(widthPanel - 5*scales.length)/sumWidth;
            for(i=0;i<scales.length;i++)
            {
                scales[i]=scales[i]*scale;
            }
        }
        for(i=0;i<scales.length;i++)
        {
            strScale='scale('+scales[i]+')';
            str+='<span style="position:relative;">' +
                '<div style="position:relative; display:inline-block;">'+
                '<img src="'+imgs[indexes[i]].src+'" style="width:'+imgs[indexes[i]].naturalWidth*scales[i]+'px; height:'+imgs[indexes[i]].naturalHeight*scales[i]+'px; margin-right: 5px; margin-top:5px;">' +
                '<button type="button" style="position:absolute; right:7px; top:2px; color:#ff0000; display: none;" class="close">×</button>'+
                '</div>'+
                '</span>';
        }
        $('#myPhotoContent > table > tbody').append(str);
    }
    addEventOnPhoto();
    for(i=0;hidePanel.childNodes.length;i++)
    {
        hidePanel.childNodes[0].remove();
    }
}


function parseStrToInt(str)
{
    str = str.replace("px","");
    return parseInt(str);
}

function clickOnPhoto(e)
{
    closeMPBtnClick = false;
    window.scrollTo(0,0);
    var img = this.getElementsByTagName("img")[0];
    currentPhoto.src=img.src;
    currentPhoto.onload = function() {
        var str = showPhoto.getElementsByClassName('panel')[0].style.width;
        str = str.replace("px", "");
        var widthStyle = parseInt(str) - 50;
        addContentOnModal(currentPhoto, widthStyle,400);
        if (!changePhotoFlag) {
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

function addEventOnPhoto()
{
    var cells = myPhotoContent.getElementsByTagName('span');
    for (i=0;i<cells.length;i++)
    {
        cells[i].addEventListener("click",clickOnPhoto);
        cells[i].addEventListener("mouseover",mouseOnImg);
        cells[i].addEventListener("mouseout",mouseOutImg);
        cells[i].getElementsByTagName('button')[0].addEventListener('click',deletePhoto);
    }
}


function deletePhoto(e)
{
    var src = getParent(this).getElementsByTagName('img')[0].src;
    var currentSpan = getParent(getParent(this));
    src = src.substr(src.indexOf("/upload"),src.length);
    var spans = myPhotoContent.getElementsByTagName('span');
    var rect;
    var currentSpanRect = currentSpan.getBoundingClientRect();
    var index=-1;
    for(i=0;i<spans.length;i++)
    {
        rect = spans[i].getBoundingClientRect();
        if(rect.top == currentSpanRect.top)
        {
            index=i;
            break;
        }
    }//на данном этапе имеем индекс первого элемента ряда, в котором начинаются изменения
    getParent(getParent(this)).remove();
    if(index>=0)
    {
        while(spans.length>index)
        {
            if(spans.length==0)
            {
                break;
            }
            hidePanel.append(spans[index].getElementsByTagName('img')[0]);
            spans[index].remove();
        }
        imgAlignment(hidePanel);
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
        getParent(getParent(imgs[index])).dispatchEvent(new Event("click"));
        changePhotoFlag = true;
    }

    function clickOnCloseMP(e)
    {
        changePhotoFlag = false;
        closeMPBtnClick = true;
    }

    closeModalPhoto.addEventListener("click",clickOnCloseMP);

    document.addEventListener("keydown",flipOnShowPhoto);

    function slidePhoto(e)
    {
        if(closeMPBtnClick)
        {
            return;
        }
        if(e.which == 1)
        {
            var rect = this.getBoundingClientRect();
            var imgs = myPhotoContent.getElementsByTagName('img');
            var index = searchImgElement(imgs, currentPhoto.src);
            if(index<0)
            {
                return;
            }
            var half = parseInt(showPhoto.getElementsByClassName('panel')[0].style.width)/2;
            if(e.clientX > half)
            {
                index++;
                if(index==imgs.length)
                {
                    index=0;
                }
            }
            else
            {
                index--;
                if(index==-1)
                {
                    index=imgs.length-1;
                }
            }
            getParent(getParent(imgs[index])).dispatchEvent(new Event("click"));
            changePhotoFlag=true;
        }
    }

    showPhoto.addEventListener("click",slidePhoto);

    /*function dblclickForSlide(e)
    {
        e.preventDefault();
        e.changeBubbles=true;
    }*/

    function preventSelectPhoto(e)
    {
        e.preventDefault();
        e.stopPropagation();
        e.changeBubbles=true;
        return false;
    }

    currentPhoto.addEventListener("mousedown",preventSelectPhoto);
    showPhoto.addEventListener("selectstart",preventSelectPhoto);
    currentPhoto.addEventListener("selectstart",preventSelectPhoto);
    showPhoto.addEventListener("mousedown",preventSelectPhoto);



    function windowResize(e)
    {
        showPhoto.getElementsByClassName('panel')[0].style.width = (parseInt(document.documentElement.clientWidth)*0.8)+"px";
        showPhoto.getElementsByClassName('modal-dialog')[0].style.width = (parseInt(document.documentElement.clientWidth)*0.8)+"px";
        showAddPhoto.getElementsByClassName('panel')[0].style.width = (parseInt(document.documentElement.clientWidth)*0.8)+"px";
        showAddPhoto.getElementsByClassName('modal-dialog')[0].style.width = (parseInt(document.documentElement.clientWidth)*0.8)+"px";
        addContentOnModal(currentPhoto, parseInt(showPhoto.getElementsByClassName('panel')[0].style.width)-50,400);
        addContentOnModal(addPhoto, parseInt(showAddPhoto.getElementsByClassName('panel')[0].style.width)-50, 335);
        var spans = myPhotoContent.getElementsByTagName('span');
        while(spans.length>0)
        {
            hidePanel.append(spans[0].getElementsByTagName('img')[0]);
            spans[0].remove();
        }
        imgAlignment(hidePanel);
    }

    windowResize();

    window.addEventListener('resize',windowResize);




    function getAllPhoto()
    {
        $.ajax({
            url: "myPhotos/getAllPhoto",
            type: "GET",
            dataType: "json",
            context: document.body,
            error: function (jqXHR, textStatus, errorThrown) {

            },
            success: function (data) {
                var result;
                try {
                    result = JSON.parse(data);
                } catch (e) {
                    result = data;
                }
                for(i=0;i<result.length;i++)
                {
                    $('#hidePanel').append('<img src="'+result[i].photoRef+'">');
                }
                var imgs = hidePanel.getElementsByTagName('img');
                countImg = result.length;
                counter=0;
                for(i=0;i<imgs.length;i++)
                {
                    imgs[i].addEventListener("load",photoLoad);
                }
            }
        });
    }

    getAllPhoto();

    function mouseOnPhotoPanel(e) {
        document.body.style.overflow = 'hidden';
        if(document.body.scrollHeight==document.body.offsetHeight)
        {
            document.body.style.paddingRight = '17px';
        }
    }

    function mouseOutPhotoPanel(e) {
        document.body.style.overflow = 'auto';
        document.body.style.paddingRight = '0px';
    }

    myPhotoContent.addEventListener("mouseover", mouseOnPhotoPanel);

    myPhotoContent.addEventListener("mouseout", mouseOutPhotoPanel);


})