$(document).ready(function () {
    function sendUserInfo(e)
    {
        var formData={};
        if(document.getElementsByName("name")[0].value!="")
        {
            formData.name=document.getElementsByName("name")[0].value;
        }
        if(document.getElementsByName("surname")[0].value!="")
        {
            formData.surname=document.getElementsByName("surname")[0].value;
        }
        var options = document.getElementsByName("sex")[0].options;
        var selOpt=-1;
        for(i=0;i<options.length;i++)
        {
            if(options[i].selected)
            {
                selOpt=i;
                break;
            }
        }
        if(selOpt>=0)
        {
            if(selOpt==0)
            {
                formData.sex=null;
            }
            else
            {
                formData.sex=options[selOpt].value;
            }
        }
        if(document.getElementsByName("country")[0].value!="")
        {
            formData.country=document.getElementsByName("country")[0].value;
        }
        if(document.getElementsByName("city")[0].value!="")
        {
            formData.city=document.getElementsByName("city")[0].value;
        }
        if(document.getElementsByName("age")[0].value!="")
        {
            formData.age=document.getElementsByName("age")[0].value;
        }
        if(document.getElementsByName("email")[0].value!="")
        {
            formData.email=document.getElementsByName("email")[0].value;
        }
        $.ajax({
            type : 'POST',
            url : "edituserinfo", // url записан в параметре action формы
            headers:{'X-CSRF-TOKEN':csrfToken},
            data : JSON.stringify(formData),
            contentType: 'application/json',
            success: function(res) {
                var result;
                try {
                    result = JSON.parse(res);
                } catch (e) {
                    result = res;
                }
                if(result == "")
                {
                    closeEditUserInfoBtn.click();
                    e.preventDefault();
                    return;
                }
                for (var field in result)
                {
                    resetErrorLabels();
                    field.name;
                    document.getElementById(field).innerHTML = result[field];
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                resetErrorLabels();
                closeEditUserInfoBtn.click();
                e.preventDefault();
            }
        });
        formData=undefined;
        return;
    }

    function resetErrorLabels()
    {
        document.getElementById("emailError").innerHTML = "";
        document.getElementById("countryError").innerHTML = "";
        document.getElementById("cityError").innerHTML = "";
        document.getElementById("nameError").innerHTML = "";
        document.getElementById("surnameError").innerHTML = "";
        document.getElementById("ageError").innerHTML = "";
    }

    saveUserInfoBtn.addEventListener("click",sendUserInfo);

    closeEditUserInfoBtn.addEventListener("click",closeEditUserInfo);

    function closeEditUserInfo()
    {
        window.location="userpage";
    }

})