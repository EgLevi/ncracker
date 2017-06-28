function save(){
    /*var del_array = [];
     del_array = array[key['name']].slice();
     alert(del_array);*/
    var Options = [];	//список выбранных интересов в компоненте select
    var request = {};
    //Получаю массив из <option> в select
    $('#select option:selected').each(function(){
        Options.push(this.text);
    });
    array2[key['name']] = Options.slice();
    /*for(var i = 0; i < Options.length; i++){
     for(var j = 0; j < del_array.length; j++){
     if(del_array[j] == Options[i]){
     del_array.splice(j,1);
     Options.splice(i,1);
     i--;
     j--;
     break;
     }
     }
     }
     alert("Удалить из БД: "+del_array+"\nДобавить в БД:"+Options);*/
    Options = [];
    $('#writeUser option:selected').each(function(){
        Options.push(this.text);
    });

    if(Options.length != 0){
        array2['Другие'] = Options.slice();
        for(var i = 0; i < Options.length; i++){
            for(var j = 0; j < writeUser.length; j++){
                if(writeUser[j] == Options[i]){
                    writeUser.splice(j,1);
                    Options.splice(i,1);
                    i--;
                    j--;
                    break;
                }
            }
        }
        if(Options.length != 0){
            //alert("ОТПРАВЛЯЮ ЗАПРОС");
            var count = 0;
            for (var i = 0; i < Options.length; i++){
                request[i] = Options[i];
                count++;
            }
            request['count'] = count;
            request['category'] = 'Другие';
            $.ajax({
                type:"POST",
                url:"ajaxSaveInteres.html",
                data:JSON.stringify(request),
                contentType: "application/json",
                async: false
            });
        }
    }

    //alert("Добавить в БД:"+array2[key['name']]+"\nВ таблицу интересов добавить: "+Options);

    request = {};
    var count = 0;
    for(var i = 0; i < caterys.length; i++){
        for(var j = 0; j < array2[caterys[i]].length; j++){
            request[count] = array2[caterys[i]][j];
            count++;
        }
    }
    request['count'] = count;
    //alert(request['count']);
    $.ajax({
        type:"POST",
        url:"ajaxSave.html",
        data:JSON.stringify(request),
        contentType: "application/json"
    });

    var childs = myInterests.children;
    while(childs.length>0)
    {
        childs[0].remove();
    }//clear interests panel
    for(i=0;i<request.count;i++)
    {
        $('#myInterests').append('<span class="label label-primary" style="margin-left: 5px; display:inline-block;">'+request[i]+'</span>');
    }
}


var key;
var array;
var array2;
var writeUser;
var caterys;
$(document).ready(function() {
    writeUser = [];
    key = '';
    array = [];			//список интересов в БД
    array2 = [];			//список интересов пользователя
    var count = 0;
    $.ajax({
        type:"POST",
        url:"ajaxFullInterest.html",
        success: function(data){
            //получаю объект JavaScript
            var returnedData = JSON.parse(data);
            //получаю массив из объекта
            caterys = $.map(returnedData, function(value, index) {
                return [value];
            });
        },
        async: false
    });
    for(var i = 0; i < caterys.length; i++)
    {
        var request ={name:caterys[i]};
        $.ajax({
            type:"POST",
            url:"ajaxtest.html",
            data:JSON.stringify(request),
            contentType: "application/json",
            success: function(data){
                //получаю объект JavaScript
                var returnedData = JSON.parse(data);
                //получаю массив из объекта
                var mas = $.map(returnedData, function(value, index) {
                    return [value];
                });
                array[caterys[i]] = mas;
            },
            async: false,
            error : function(jqXHR, textStatus, errorThrown) {
                alert("Error! "+textStatus+" "+" "+jqXHR);
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });

        $.ajax({
            type:"POST",
            url:"ajaxGetInterestCat.html",
            data:JSON.stringify(request),
            contentType: "application/json",
            success: function(data){
                //получаю объект JavaScript
                var returnedData = JSON.parse(data);
                //получаю массив из объекта
                var mas = $.map(returnedData, function(value, index) {
                    return [value];
                });
                array2[caterys[i]] = mas;
            },
            async: false,
            error : function(jqXHR, textStatus, errorThrown) {
                alert("Error! "+textStatus+" "+" "+jqXHR);
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }
    $('.chosen-select').chosen();
    $('.select-tag').select2({
        tags: true
    });
    writeUser = array2['Другие'].slice();


    $('.list-group-item').on('click', function() {

        if(key != ''){
            var selected = [];	//список выбранных интересов в компоненте select
            //Получаю массив из <option> в select
            $('#select option:selected').each(function(){
                selected.push(this.text);
            });
            array2[key['name']] = selected;
        }
        key = {name: $(this).text()};
        //Здесь очистищаю select
        var action_list = document.getElementById("select");
        i = action_list.options.length;
        while (i--)
        {
            action_list.remove(i);
            $('#select').trigger('chosen:updated');
        }

        if($(this).text()!="Другие"){
            for(var i = 0; i < array[key['name']].length; i++){
                $('#select').append('<option value="'+i+'">'+array[key['name']][i]+'</option>');
                $('#select').trigger('chosen:updated');
            }
            var Options = [];	//список выбранных интересов в компоненте select

            //заполняю выбранные из категории интересы
            //Получаю массив из <option> в select
            $('#select > option').each(function(){
                Options.push(this.text);
            });
            for(var i = 0; i < Options.length; i++){
                //сравниваю каждый <option> с интересами и если они совпадают то делаю этот интерес selected
                for(var j = 0; j < array2[key['name']].length; j++){
                    if((array2[key['name']][j] == Options[i])){
                        $("#select option[value=" + i + "]").attr('selected', 'true');
                        $('#select').trigger('chosen:updated');
                        break;
                    }
                }
            }
        }
        else{
            for(var i = 0; i < array2[key['name']].length; i++){
                $('#writeUser').append('<option value="'+i+'">'+array2[key['name']][i]+'</option>');
                $("#writeUser option[value=" + i + "]").attr('selected', 'true');
            }
        }
        $('#select').trigger('chosen:open');
    });

    function changeInterface()
    {
        var child = document.getElementById('writeUser');
        var parent = getParent(child);
        var element = parent.children[1];
        element.style.width="100%"
        child = document.getElementById('select');
        parent = getParent(child);
        element = parent.children[1];
        element.style.width="90%"
    }

    changeInterface();

});
