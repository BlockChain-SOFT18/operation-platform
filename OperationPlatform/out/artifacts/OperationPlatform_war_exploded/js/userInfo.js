function loadXMLDoc()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
        xmlhttp=new XMLHttpRequest();
    else xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    var arr = document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            xmlDoc=xmlhttp.responseXML;
            var info = xmlDoc.getElementsByTagName("Info");
            document.getElementById("ID").innerHTML=info[0].firstChild.nodeValue;
            document.getElementById("UserName").innerHTML=info[1].firstChild.nodeValue;
            document.getElementById("TrueName").innerHTML=info[2].firstChild.nodeValue;
            document.getElementById("State").innerHTML=info[3].firstChild.nodeValue;
            alert(info[4].firstChild.nodeValue);
        }
    }
    xmlhttp.open("POST","http://localhost:8080/OP/UserInfo",true);
    xmlhttp.setRequestHeader("Content-Type"
        , "application/x-www-form-urlencoded");
    xmlhttp.send("userID="+unescape(arr[2]));
}
