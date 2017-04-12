<%-- 
    Document   : algoritmFindUsers
    Created on : 05.04.2017, 11:00:30
    Author     : vara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Алгоритм поиска пользователей</title>
    </head>
    <body>
        /*выбираем тип чата-> если групповой то выбираем какие то категории интересов, по ним выполняется поиск бесед
                           -> если личный, то выбираем набор интересов и по ним выпадает список пользователей и рядом с ником количество совпадений. 
                           компоненты radio,2 list, button , listUser
        */
        /*ЛИЧНЫЙ ЧАТ
        выбираем пользователя по региону, который указан в анкете пользователя.Возраст, пол и остальное так же учитывается
        <input type="radio" name="typechat" value="ie"> Group<Br>
        <input type="radio" name="typechat" value="opera"> lichka<Br>
        
        <form> 
        <p><input list="interests"></p>
        <datalist id="cocktail">
             <option>Аперитивы</option>
             <%
                 
                 %>
        </datalist> 
        </form> 
    </body>
</html>
