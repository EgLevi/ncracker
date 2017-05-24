<%--
  Created by IntelliJ IDEA.
  User: vara
  Date: 23.05.2017
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form>
    <p><a href="http://vk.com/" class="enjoy-css">Добавьте свои интересы, чтобы появились ваши личные плитки ${userInfo.user_id}</a></p>
</form>

<style>
.enjoy-css {
display: inline-block;
-webkit-box-sizing: content-box;
-moz-box-sizing: content-box;
box-sizing: content-box;
position: relative;
cursor: pointer;
top: 0;
padding: 0 22px;
border: 4px solid;
font: normal normal bold 20px/50px Arial, Helvetica, sans-serif;
color: rgba(12,12,12,1);
text-align: center;
text-transform: uppercase;
-o-text-overflow: clip;
text-overflow: clip;
background: #ffffff;
}

.enjoy-css:hover {
border: 4px solid rgba(5,5,5,1);
color: #000000;
background: #c6c6c6;
}
</style>