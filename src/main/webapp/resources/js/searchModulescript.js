$(document).ready(function () {
function SearchScriptObject()
{

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