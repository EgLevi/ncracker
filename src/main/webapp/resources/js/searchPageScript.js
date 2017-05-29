$(document).ready(function () {
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var username_by_inter;

    $.ajaxSetup({
        headers: {csrfHeader: csrfToken}
    });

    var data = [];

    function clearListSelectedInterest() {
        var childList = listInterest.getElementsByClassName("listSelInterest");
        var length = childList.length;
        for (i = 0; i < length - 1; i++) {
            childList[1].remove();
        }
    }

    $("#okBtn").click(
        function () {
            if (data.length > 0)//если есть данные на обновление, то посылаем соответствующий запрос
            {
                $.ajax({
                    type: 'POST',
                    url: "search",
                    headers: {'X-CSRF-TOKEN': csrfToken},
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (res) {

                        cancelBtn.click();
                        getUsersForChat();

                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                    }
                });
                data = [];
                clearListSelectedInterest()
            }
            else {
                cancelBtn.click();
            }

        }
    );

    function getParent(el) {
        return el.parentElement || el.parentNode;
    }


    function deleteInterest(e) {
        var listBtn = listInterest.getElementsByClassName("btn");
        var index;
        for (i = 0; i < listBtn.length; i++) {
            if (listBtn[i] == this) {
                index = i;
                break;
            }
        }
        if (!!index) {
            var parent = getParent(listBtn[index]);
            var indexInData = indexDataByInterestId(parent.getElementsByTagName("label")[0].innerHTML);
            data.splice(indexInData, 1);
            parent.remove();
        }
        else {
            return;
        }
    }

    function existInData(interestId) {
        for (i = 0; i < data.length; i++) {
            if (data[i].interestId === interestId) {
                return true;
            }
        }
        return false;
    }

    function indexDataByInterestId(interestId) {
        for (i = 0; i < data.length; i++) {
            if (data[i].interestId === interestId) {
                return i;
            }
        }
        return -1;
    }

    function clickOnInterest(e) {
        var parentEl = getParent(this);
        if (!existInData(parentEl.getElementsByTagName("label")[0].innerHTML)) {
            var element = $("#flistelement").clone();
            element[0].getElementsByTagName("label")[0].innerHTML = parentEl.getElementsByTagName("label")[0].innerHTML;
            element[0].getElementsByTagName("span")[0].innerHTML = this.innerHTML;
            element[0].style.display = "inline";
            element[0].getElementsByTagName("div")[0].addEventListener("click", deleteInterest);
            listInterest.appendChild(element[0]);
            var childs = listInterest.children;
            element =childs[childs.length-1];
            if(element.offsetHeight>16)
            {
                listInterest.insertBefore(document.createElement('br'),element);
            }
            //если такого элемента нет, то вносим его в список интересов
            data.push({
                "interestId": parentEl.getElementsByTagName("label")[0].innerHTML,
                "interestName": this.innerHTML
            });
        }
    }

    function getUsersForChat() {
        $.ajax({
            type: 'GET',
            url: "/search/getUsersForChat",
            contentType: 'json',
            dataType: "json",
            success: function (res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                username_by_inter = res.usersName[0];
                start_chat();
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        })

    }

    function getInterestsByCategory(e) {
        $.ajax({
            type: 'GET',
            url: "search/getInterests/" + category.options[category.selectedIndex].innerText,
            contentType: 'application/json',
            dataType: "json",
            success: function (res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                interest_list_id = result.interestId;
                var childList = interests.getElementsByTagName("div");
                var length = childList.length;
                for (i = 0; i < length - 1; i++) {
                    childList[1].remove();
                }

                var element;

                for (i = 0; i < result.length; i++) {
                    element = $("#fdivinterest").clone();
                    element[0].getElementsByTagName("label")[0].innerHTML = result[i].interestId;
                    element[0].getElementsByTagName("button")[0].innerHTML = result[i].interestName;
                    element[0].getElementsByTagName("button")[0].addEventListener("click", clickOnInterest);
                    element[0].getElementsByTagName("button")[0].style.display = "block";
                    interests.appendChild(element[0]);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }

    category.addEventListener("change", getInterestsByCategory);

    function getCategory() {

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
                    element.innerHTML = result[i].categoryName;
                    category.append(element);
                }
                category.options[0].select = true;
                getInterestsByCategory();
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    }


    function start_chat() {
        var ulogin = {ulogin: username_by_inter};
        $.ajax({
            headers: {'X-CSRF-TOKEN': csrfToken},
            type: "POST",
            url: "search/startchat",
            contentType: "application/json",
            //TODO Доделать
            dataType: "json",
            data: JSON.stringify(ulogin),
            success: function (res) {
                cancelBtn.click();
                alert(res);
                var json = JSON.parse(res);
                var url = "http://localhost:8081/chats/" + json.idChat
                alert(url);
                location.href = url;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("К сожалению не удалось найти собеседника с данными интересами. Попробуйте выбрать другие")
            }
        });
    }


    getCategory();
})
;