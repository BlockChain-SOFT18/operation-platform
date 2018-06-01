function loadXMLDoc()
{
    var xmlhttp;
    if (window.XMLHttpRequest)
        xmlhttp=new XMLHttpRequest();
    else xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            xmlDoc=xmlhttp.responseXML;
            var info = xmlDoc.getElementsByTagName("Info");
            document.getElementById("ID").innerHTML=info[0].firstChild.nodeValue;
            document.getElementById("OrgName1").innerHTML=info[1].firstChild.nodeValue;
            document.getElementById("OrgName2").innerHTML=info[2].firstChild.nodeValue;
            document.getElementById("Classification").innerHTML=info[3].firstChild.nodeValue;
            document.getElementById("PersonName").innerHTML=info[4].firstChild.nodeValue;
            document.getElementById("PhoneNumber").innerHTML=info[5].firstChild.nodeValue;
        }
    }
    xmlhttp.open("POST","http://localhost:8080/OP/OrgInfo",true);
    xmlhttp.send();
}
