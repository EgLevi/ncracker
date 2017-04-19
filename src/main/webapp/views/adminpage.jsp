<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<script src="resources/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<head>
	<style>
        .btn-default-hover{
            display: inline-block; /* Строчно-блочный элемент */
            position: relative; /* Относительное позиционирование */
         }

        .btn-default-hover:hover::after {
            content: attr(data-title); /* Выводим текст */
            position: absolute; /* Абсолютное позиционирование */
            /*left: 0%;
            top: 10%;*/ /* Положение подсказки */
            left: 0; bottom: 34px;
            z-index: 1; /* Отображаем подсказку поверх других элементов */
            background: rgba(255,255,230,0.9); /* Полупрозрачный цвет фона */
            font-family: Arial, sans-serif; /* Гарнитура шрифта */
            font-size: 11px; /* Размер текста подсказки */
            padding: 5px 10px; /* Поля */
            border: 1px solid #333; /* Параметры рамки */
        }


        .dropbtn {
        background-color: #FFFFFF;
        color: white;
        padding: 6px 12px;
        font-size: 16px;
        border: 1px solid #ccc;
        cursor: pointer;
        height: 34px;
        }

        .dropbtn:hover, .dropbtn:focus {
            background-color: #FFFFFF;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 270px;
            overflow: auto;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .label-content
        {
            min-width: 160px;
            width:270px;
            z-index: 1;
            color: black;
            padding: 6px 12px;
            margin-bottom: 0px;
            font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
            font-size: 14px;
            font-weight:100;
        }

        .dropdown-content a {
            color: black;
            padding: 6px 12px;
            text-decoration: none;
            display: block;
        }

        .dropdown a:hover {background-color: #f1f1f1}

        .show {display:block;}
        
        .element-center{
        	margin:0 auto;
        }
        
        .indent-down{
        	margin-bottom:30px;
        }
        
        .indent-top{
        	margin-top:30px;
        }
    </style>
    <title>Интересы пользователей</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <textarea id="txtarea" rows="2" cols="20" name="text" style="position:absolute; top: 0px; left: 0px; display:none;"></textarea>
    <textarea id="txtareaSelect" rows="2" cols="20" name="text" style="position:absolute; top: 0px; left: 0px; display:none; z-index:3"></textarea>

    <button type="button" id="removeBtn" data-title="Удалить строки" class="btn btn-default" style="position:absolute; margin-bottom:0px; height: 34px; display:none;"><span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></button>


    <div class="container indent-top">
    <div class="panel panel-default control-main">
            <div class="panel-heading">
                <div class="input-group element-center">
		        	<div class="dropdown indent-down">
		                <label id="labelSlct" class="label-content">Выбрать категорию интересов</label>
		                <button id="selectBtn" class="dropbtn btn btn-default"><span id="spanBtn" class="glyphicon glyphicon-chevron-down" style="color:#999999;"></span></button>
		                <div id="myDropdown" class="dropdown-content">
			                <c:if test="${!empty categoryInterestsList}">
			                    <c:forEach items="${categoryInterestsList}" var="categoryInterest">
			                        <a>${categoryInterest.categoryName}<span class="glyphicon glyphicon-remove" style="position:absolute; color:#FF0000; right:0%; top:inherit; padding:6px;"></span></a>
			                    </c:forEach>
			                </c:if>
			                <a>Добавить новый элемент</a>
		                </div>
		            </div>
		            <button type="button" id="commitBtn" data-title="Сохранить изменения" class="btn btn-default btn-default-hover" style="margin-bottom:0px; height: 34px;"><span class="glyphicon glyphicon-ok" style="color:#00FF00;"></span></button>
		            <button type="button" id="cancelBtn" data-title="Отменить" class="btn btn-default btn-default-hover" style="margin-bottom:0px; height: 34px;"><span class="glyphicon glyphicon-repeat" style="color:#FF0000;"></span></button>
		        </div>
            </div>
            <div class="panel-body table-responsive" style="height:480px;">
                <table class="table table-bordered element-center" onselectstart="return false" id="table" style="margin-top:30px; width: 609px; border: 1px solid #ddd;">
		            <thead>
		                <tr>
		                    <th style="width: 118px;">ID</th>
		                    <th>Интерес</th>
		                </tr>
		            </thead>
		            <tbody id="tableBody">
		            </tbody>
		        </table>
            </div>
        </div>
    </div>
    <script type="text/javascript">
	    var data = [];//data for update
	    var dataDelete = [];
	    var dataInsert = [];
	    var coordiantes;
	    var row=0;
	    var column=0;
	    var prevSelIndex=undefined;
	    var categoryId=0;
	    var categoryName=0;
	    var valueOption = "";
	    var selectedRow = [];
	    var mouseBtnDown = false;
	
	    function sameSign(v1, v2)
	    {
	        if ((v1 * v2) > 0) {
	            return true;
	        }
	        return false;
	    }
	
	
	    var table = document.getElementById('table')
	    var trList = table.getElementsByTagName('tr')
	    var tdList;
	
	    function sendDataChangeSelect()//send request on interests of category
	    {
	    	if(valueOption != "")
	    	{

		    	$.ajax({
		            type: 'POST',
		            url: "adminpage",
		            contentType: 'application/json',
		            data : valueOption,
		            success: function (res) {
		            	var result;
		            	try {
		            	    result = JSON.parse(res); 
		            	} catch (e) {
		            	    result = res;
		            	}
		            	if(result=="fail")
		            	{
		            		alert("Some problem");
		            		return;
		            	}
		                var elem;
		                var childElem;
		                elem = document.createElement('a');
		                elem.innerHTML = valueOption+'<span class="glyphicon glyphicon-remove" style="position:absolute; color:#FF0000; right:0%; top:inherit; padding:6px;"></span>';
		                myDropdown.insertBefore(elem, myDropdown.childNodes.item(myDropdown.childNodes.length - 2));
		                changeSelected(valueOption);
		                aList = myDropdown.getElementsByTagName('a');
		                aList[aList.length - 2].addEventListener("mousedown", addNewOptionToSelect);
	                    aList[aList.length - 2].children[0].addEventListener("mousedown", deleteCategory);
	                    categoryName = valueOption;
	    	            valueOption="";
	            		getInterestOfCategory();
		            },
		        	error: function (jqXHR, textStatus, errorThrown)
		        	{
			            valueOption="";
		        	}
		        });
	    	}
	    }
	
	    
	    function checkResponse(res)
	    {
	    	var result;
        	try {
        	    result = JSON.parse(res); 
        	} catch (e) {
        	    result = res;
        	}
        	if(result!="success")
        	{
        		reloadTableAfterError();
        	}
	    }
	    
	    function reloadTableAfterError()
	    {
	    	alert("Some problem, commit is cancel");
    		getInterestOfCategory();
	    }
	
	
	    function sendDataChangeTable(e)//send data about change in table (commit change)
	    {
	    	if(data.length>0)
	    	{
		        $.ajax({
		            type: 'POST',
		            url: "adminpage/update",
		            contentType: 'application/json',
		            data : JSON.stringify(data),
		            success: function (res){
		            	checkResponse(res);
		            },
		        	error: function (jqXHR, textStatus, errorThrown)
		        	{
		        		reloadTableAfterError();
		        	}
		        });
                data = [];
	    	}
	
	    	if(dataInsert.length>0)
	    	{
		        $.ajax({
		            type: 'POST',
		            url: "adminpage/put/" + categoryName,
		            contentType: 'application/json',
		            data: JSON.stringify(dataInsert),
		            success: function (res) {
		            	var result;
		                try {
		                    result = JSON.parse(res);
		                } catch (e) {
		                    result = res;
		                }

		                for (i = 0; i < result.length; i++)
		                {
		                    tdList = trList[trList.length - 2 - i].getElementsByTagName('td');
		                    tdList[0].innerHTML = result[result.length - 1 - i];
		                }
		                addEventInTable();
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		            	reloadTableAfterError();
		            }
		        });
                dataInsert = [];
	    	}
	
	    	if(dataDelete.length>0)
	    	{
		        $.ajax({
		            type: 'DELETE',
		            url: "adminpage/delete/" + categoryName,
		            contentType: 'application/json',
		            data: JSON.stringify(dataDelete),
		            success: function (res) {
		            	checkResponse(res);
		            },
		            error: function (jqXHR, textStatus, errorThrown) {
		            	reloadTableAfterError();
		            }
		        });
            	dataDelete=[];
	    	}
	    }
	
	    function reloadData(e)//click on cancel button
	    {
	    	getInterestOfCategory();
	    }
	    
	    function compareElement(a,b)
	    {
	    	if(a > b)
	    	{
	    		return 1;
	    	}
	    	else
	    	{
	    		return -1;
	    	}
	    }
	
	    function deleteRowsFromTable(e)
	    {
	    	selectedRow = selectedRow.sort(compareElement);
	        for(i=0;i<selectedRow.length;i++)
	        {
	            tdList = trList[selectedRow[0]].getElementsByTagName('td');
	            dataDelete.push({ "interestId": tdList[0].innerHTML, "interestName": tdList[1].innerHTML });
	            trList[selectedRow[0]].remove();
	        }
	        selectedRow.splice(0,selectedRow.length);
	    }
	
	    commitBtn.addEventListener("click", sendDataChangeTable);
	
	    cancelBtn.addEventListener("click", reloadData);
	
	    removeBtn.addEventListener("mousedown",deleteRowsFromTable);
	    
	    var textareaInFocus = false;
	
	    function keyDownTxtArea(e)//keyDown in textArea in table cell
	    {
	        if (e.keyCode == 13)
	        {
	            tdList = trList[row].getElementsByTagName('td');
	            thList = table.getElementsByTagName('th');
	            tdList[column].innerHTML = txtarea.value;
	            txtarea.value = "";
	            txtarea.style.display = "none";
	            if (row == trList.length - 1) {
	                dataInsert.push({ "interestId": 0, "interestName": tdList[column].innerHTML });
	                var elem;
	                var childElem;
	                elem = tableBody.insertRow(row);
	                childElem = document.createElement('td');
	                childElem.innerHTML = "";
	                elem.insertBefore(childElem, elem.firstChild);
	                childElem = document.createElement('td');
	                childElem.innerHTML = "*";
	                elem.insertBefore(childElem, elem.lastChild);
	                trList = table.getElementsByTagName('tr')
	                tdList = trList[row+1].getElementsByTagName('td')
	                for (i = 1; i < tdList.length; i++) {
	                    tdList[i].addEventListener("click", clickOnElement);
	                }
	            }
	            else
	            { 
	                data.push({ "interestId": tdList[0].innerHTML, "interestName": tdList[column].innerHTML });
	            }
	        }
	        if (e.keyCode == 9)
            {
                var trListInner = tableBody.getElementsByTagName("tr");
                var tdListInner = trListInner[row].getElementsByTagName("td");
                coordinates = tdListInner[column].getBoundingClientRect();
                txtarea.style.top = (coordinates.top + 5) + "px";
                txtarea.style.left = (coordinates.left + 5) + "px";
                txtarea.style.width = tdListInner[column].clientWidth + "px";
                txtarea.style.height = tdListInner[column].clientHeight + "px";
                txtarea.value = tdListInner[column].innerHTML;
                txtarea.style.display = "inline";
                row = tdListInner[column].parentElement.rowIndex;
                column = tdListInner[column].cellIndex;
                textareaInFocus = true;
            }
	    }
	    
	    function focusProc(e)
        {
            if (textareaInFocus)
            {
                txtarea.focus();
            }
        }

        function blurProc(e)
        {
            if (textareaSelectInFocus)
            {
                txtareaSelect.focus();
            }
        }


        window.addEventListener("blur", blurProc, true);

        window.addEventListener("focus", focusProc, true);
	
	    function keyDownTxtAreaSelect(e)//kewdown in textarea in select
	    {
	        if (e.keyCode == 13) {
	        	valueOption = txtareaSelect.value;
	        	txtareaSelect.value = "";
	        	txtareaSelect.style.display = "none";
	            sendDataChangeSelect();
	        }
	    }
	
	
	    txtarea.addEventListener("keydown", keyDownTxtArea);
	
	    txtareaSelect.addEventListener("keydown", keyDownTxtAreaSelect);
	
	    function clickOnTxtSelect(e)
	    {
	        e.stopPropagation();
	    }
	
	    txtareaSelect.addEventListener("mousedown", clickOnTxtSelect);
	
	
	    function clickOnTxt(e) {
	        e.stopPropagation();
	    }
	
	    txtarea.addEventListener("mousedown", clickOnTxt);
	    
	    function flipOnTable(e)
        {
            if (selectedRow.length>0)
            {
                var currentRow;
                if (e.keyCode == 38)//if press key 'up'
                {
                    if (selectedRow[selectedRow.length - 1] == 1)
                    {
                        return;
                    }
                    currentRow = selectedRow[selectedRow.length - 1] - 1;
                }
                else if (e.keyCode == 40)//if press key 'down'
                {
                    if (selectedRow[selectedRow.length - 1] == trList.length - 2)
                    {
                        return;
                    }
                    currentRow = selectedRow[selectedRow.length - 1] + 1;
                }
    	        for(i=0;i<selectedRow.length;i++)
    	        {
                    trList[selectedRow[i]].style.backgroundColor = "#FFF";
    	        }
    	        selectedRow.splice(0,selectedRow.length);
                selectedRow.push(currentRow);
                trList[currentRow].style.backgroundColor = "#AAA";
                var coordinates = trList[currentRow].getBoundingClientRect();
                removeBtn.style.top = (coordinates.top) + "px";
                removeBtn.style.left = (coordinates.left - 40) + "px";//40 it is size button with gliphicon
                removeBtn.style.display = "inline";
            }
        }

        window.addEventListener("keydown",flipOnTable);
	
        function existInArray(element, index, array)
	    {
	        if(element==this)
	        {
	            return true;
	        }
	        return false;
	    }    

	    function mouseDownOnElement(e)
	    {
	        mouseBtnDown = true;
	        if (this.parentElement.rowIndex != trList.length-1)//-1 it is header of table
	        {
	            if (selectedRow.findIndex(existInArray, this.parentElement.rowIndex) == -1)
	            {
	                selectedRow.push(this.parentElement.rowIndex);
	                trList[this.parentElement.rowIndex].style.backgroundColor = "#AAA";
	                if (selectedRow.length == 1) {
	                    var coordinates = this.getBoundingClientRect();
	                    var str = removeBtn.style.height;
	                    str = str.replace("px", "");
	                    removeBtn.style.top = (coordinates.top) + "px";
	                    removeBtn.style.left = (coordinates.left - 40) + "px";//40 it is size button with gliphicon
	                    removeBtn.style.display = "inline";
	                }
	            }
	        }
	        e.stopPropagation();
	    }
	
	    function mouseMoveInElement(e) {
	        if (mouseBtnDown)
	        {
	            if(selectedRow[selectedRow.length-1]!=this.parentElement.rowIndex)//doing some if we are in new cell
	            {
	                if (selectedRow.length > 1) {
	                    var prevDif = selectedRow[selectedRow.length - 1] - selectedRow[selectedRow.length - 2];
	                    var dif = this.parentElement.rowIndex - selectedRow[selectedRow.length - 1];
	                }
	                else if (selectedRow.length > 0)
	                {
	                    var dif = this.parentElement.rowIndex - selectedRow[selectedRow.length - 1];
	                    var prevDif = dif;
	                }
	                if (sameSign(prevDif, dif))//if we go in same way, then...
	                {
	                    var step = dif / Math.abs(dif);
	                    var length = selectedRow.length;
	                    for (var i = selectedRow[length - 1] + step; i != this.parentElement.rowIndex; i += step) {
	                        selectedRow.push(i);
	                        trList[i].style.backgroundColor = "#AAA";
	                    }
	                    if (this.parentElement.rowIndex != trList.length-1)
	                    {
	                        selectedRow.push(this.parentElement.rowIndex);
	                        trList[this.parentElement.rowIndex].style.backgroundColor = "#AAA";
	                    }
	                }//previous block is adding select
	                else//cancel select
	                {
	                    var startIndex = selectedRow.indexOf(this.parentElement.rowIndex);
	                    var length = selectedRow.length;
	                    for (var i = startIndex + 1; i < length; i++) {
	                        trList[selectedRow[i]].style.backgroundColor = "#FFF";
	                        selectedRow.splice(i, 1);
	                    }
	                }
	            }
	        }
	    }
	
	    function mouseUpOnElement(e) {
	        mouseBtnDown = false;
	    }
	
	    function mouseDownOnDocument(e)
	    {
	        var length = selectedRow.length;
	        for (i = 0; i < length; i++)
	        {
	            trList[selectedRow[i]].style.backgroundColor = "#FFF";
	        }
	        selectedRow.splice(0, selectedRow.length);
	        txtarea.value = "";
	        txtarea.style.display = "none";
	        removeBtn.style.display = "none";
	
	        if (!e.target.matches('.dropbtn')) {
	
	            hideDropDown();
	        }
	        txtareaSelect.value = "";
	        txtareaSelect.style.display = "none";
	        
	        textareaInFocus = false;
	        
	        textareaSelectInFocus = false;
	
	    }
	
	    function hideDropDown()
	    {
	        var dropdowns = document.getElementsByClassName("dropdown-content");
	        var i;
	        for (i = 0; i < dropdowns.length; i++) {
	            var openDropdown = dropdowns[i];
	            if (openDropdown.classList.contains('show')) {
	                openDropdown.classList.remove('show');
	            }
	        }
	    }
	
	    document.addEventListener("mouseup", mouseUpOnElement);
	    document.addEventListener("mousedown", mouseDownOnDocument);
	
	
	
	    function clickOnElement(e)//click on table element
	    {
	        
	        coordinates = this.getBoundingClientRect();
	        txtarea.style.top = (coordinates.top+5) + "px";
	        txtarea.style.left = (coordinates.left + 5) + "px";
	        txtarea.style.width = this.clientWidth + "px";
	        txtarea.style.height = this.clientHeight + "px";
	        //txtarea.innerHTML = this.innerHTML;
	        txtarea.value = this.innerHTML;
	        txtarea.style.display = "inline";
	        txtarea.focus();
	        textareaInFocus = true;
	        row = this.parentElement.rowIndex;
	        column = this.cellIndex;
	    }
	
	    var textareaSelectInFocus = false;
	    
	    function addNewOptionToSelect(e)
	    {
	        if (aList[aList.length-1] == this)
	        {
	            coordinates = labelSlct.getBoundingClientRect();
	            txtareaSelect.style.top = coordinates.top + "px";
	            txtareaSelect.style.left = coordinates.left + "px";
	            txtareaSelect.style.width = labelSlct.clientWidth + "px"
	            txtareaSelect.style.height = labelSlct.clientHeight + "px"
	            txtareaSelect.style.display = "inline";
	            hideDropDown();
                textareaSelectInFocus = true;
	        }
	        else
	        {
	            if (this != prevSelIndex)
	            {
	                changeSelected(this.innerText)
	            	prevSelIndex = this;
	            	//var labelList = hideId.getElementsByTagName("label");
	            	//categoryId = labelList[idSelect.options.selectedIndex].innerHTML;
	            	categoryName = this.innerText;
	            	addEventInTable();
	            	getInterestOfCategory();
	            	hideDropDown();
	            }
	        }
        	e.stopPropagation();
	    }
        
        function getInterestOfCategory()//get data of table by category
        {
            var trBodyList = tableBody.getElementsByTagName("tr");//delete all old elements
            var length = trBodyList.length;
            for (i = 0; i < length; i++) {
                trBodyList[0].remove();
            }

            $.ajax({
                type: 'GET',
                url: "adminpage/"+categoryName,
                contentType: 'application/json',
                dataType:"json",
                success: function (res) {
                	var result;
                	try {
                	    result = JSON.parse(res); 
                	} catch (e) {
                	    result = res;
                	}
                	var trBodyList = tableBody.getElementsByTagName("tr");//delete all old elements
                    var length = trBodyList.length;
                    for (i = 0; i < length; i++) {
                        trBodyList[0].remove();
                    }
                    var elem;
                    var childElem;
                    for (i = 0; i < res.length; i++) {
                        elem = tableBody.insertRow(i);
                        childElem = document.createElement('td');
                        childElem.innerHTML = res[i].interestName;
                        elem.insertBefore(childElem, elem.firstChild);
                        childElem = document.createElement('td');
                        childElem.innerHTML = res[i].interestId;
                        elem.insertBefore(childElem, elem.lastChild);
                    }
                    elem = tableBody.insertRow(res.length);
                    childElem = document.createElement('td');
                    childElem.innerHTML = "";
                    elem.insertBefore(childElem, elem.firstChild);
                    childElem = document.createElement('td');
                    childElem.innerHTML = "*";
                    elem.insertBefore(childElem, elem.lastChild);
                    addEventInTable();
                }
            });
        }


        function addEventInTable()
        {
        	trList = table.getElementsByTagName('tr')
            for (i = 0; i < trList.length-1; i++) {
                tdList = trList[i].getElementsByTagName('td');
                for (j = 1; j < tdList.length; j++) {
                    tdList[j].addEventListener("click", clickOnElement);
                }
                if (tdList.length>0)
                {
                    tdList[0].addEventListener("mousedown", mouseDownOnElement);
                    tdList[0].addEventListener("mousemove", mouseMoveInElement);
                    tdList[0].addEventListener("mouseup", mouseUpOnElement);
                }
            }
        	tdList = trList[trList.length-1].getElementsByTagName('td');
            for (j = 1; j < tdList.length; j++) {
                tdList[j].addEventListener("click", clickOnElement);
            }
        }

        function startTable()//start loading table
        {
            categoryName = aList[0].innerText;
            getInterestOfCategory();
        }

        //startTable();

        addEventInTable();

        //part script for manage own select
        function showDropDown() {
            document.getElementById("myDropdown").classList.toggle("show");
        }


        function showDropDownSpan(e) {
            document.getElementById("myDropdown").classList.toggle("show");
            e.stopPropagation();
        }

        function changeSelected(value) {
            labelSlct.innerHTML = value;
        }

        function selectedOption(e) {
            changeSelected(this.innerText)
        }
        
        
        function sendDeleteCategoryCommand(categoryName, element)//send data about change in table
	    {
	        $.ajax({
	            type: 'DELETE',
	            url: "adminpage/deleteCategory/"+categoryName,
	            success: function (res) {
	            	var result;
	            	try {
	            	    result = JSON.parse(res); 
	            	} catch (e) {
	            	    result = res;
	            	}
	            	if(result!="success")
	            	{
	            		alert("Some problem with delete category");
	            		return;
	            	}
	                element.parentNode.remove();
                    labelSlct.innerHTML = "Выбрать категорию интересов";
                    for(i=1;i<trList.length;i++)
                    {
                    	trList[1].remove();	
                    }
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            }
	        });
	
	    }
        

        function deleteCategory(e)
        {
            sendDeleteCategoryCommand(this.parentNode.innerText,this);
            e.stopPropagation();
        }

        var aList = myDropdown.getElementsByTagName('a');
        for (i = 0; i < aList.length-1; i++) {
            aList[i].addEventListener("mousedown", addNewOptionToSelect);
            aList[i].children[0].addEventListener("mousedown", deleteCategory);
        }
        aList[aList.length - 1].addEventListener("mousedown", addNewOptionToSelect);

        selectBtn.addEventListener("mousedown", showDropDown);
        spanBtn.addEventListener("mousedown", showDropDownSpan);
    </script>
</body>
</html>
