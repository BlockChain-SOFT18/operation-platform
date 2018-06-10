function loadXMLDoc()
{
    var arr = document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
    var id;
    if(arr==null)
        id=2;
    else id=arr[2];
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/OrgInfo",
        dataType: "xml",
        data:{orgID:id},
        success: function (data) {
            var info=data.getElementsByTagName("Info");
            document.getElementById("ID").innerHTML=info[0].firstChild.nodeValue;
            document.getElementById("OrgName1").innerHTML=info[1].firstChild.nodeValue;
            document.getElementById("OrgName2").innerHTML=info[2].firstChild.nodeValue;
            document.getElementById("Classification").innerHTML=info[3].firstChild.nodeValue;
            document.getElementById("PersonName").innerHTML=info[4].firstChild.nodeValue;
            document.getElementById("PhoneNumber").innerHTML=info[5].firstChild.nodeValue;
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
