$(document).ready(function () {
function showChatPanel(e)
{
    var a = this.getElementsByTagName("a")[0];
    chatId = a.attributes[0].value;
    window.location="chats/"+chatId;
    e.preventDefault();
}

function addEventOnChats()
{
    var buttons = listChats.getElementsByTagName('li');
    for(i=0;i<buttons.length;i++)
    {
        buttons[i].addEventListener("click",showChatPanel);
    }
}

addEventOnChats();
})