
$(function(){
var mid = 0;
$(document).ready(function() {
        $("#test").click(function(){	
        var mes = document.getElementById('message').value
        $.get("/LevelChat/ajaxadd",{mes,mid});  
        });
    });
function getMessageChat() {
	$.get("/LevelChat/ajax",{mid}).done(function(data,status){
    var list = document.getElementById('list')
    if (data.length!=0)
    {
    	mid=data.length+1
    }
    for (i = 0; i < data.length; i++)
﻿

var store=
    {
        initial:function(){},
        aa:"name"
    };





