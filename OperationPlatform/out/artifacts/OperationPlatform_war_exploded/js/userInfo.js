jQuery(document).ready(function($) {
    $('.theme-login1').click(function(){
        $('.theme-popover1-mask').fadeIn(100);
        $('.theme-popover1').slideDown(200);
    });
    $('.theme-poptit1 .close').click(function(){
        $('.theme-popover1-mask').fadeOut(100);
        $('.theme-popover1').slideUp(200);
    });
});

function Freeze() {
    var arr = document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
    var msg="Freeze";
    var id;
    if(arr==null)
        id=28;
    else id=arr[2];
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/UserInfo",
        dataType: "xml",
        data:{userID:id,msg:msg},
        success: function (data) {
            var info=data.getElementsByTagName("Info");
            var flag=info[0].firstChild.nodeValue!="0";
            if (!flag)
                alert("账户冻结失败 !");
            else alert("账户冻结成功 !");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function Active() {
    var arr = document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
    var msg="Active";
    var id;
    if(arr==null)
        id=28;
    else id=arr[2];
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/UserInfo",
        dataType: "xml",
        data:{userID:id,msg:msg},
        success: function (data) {
            var info=data.getElementsByTagName("Info");
            var flag=info[0].firstChild.nodeValue!="0";
            if (!flag)
                alert("账户激活失败 !");
            else alert("账户激活成功 !");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function ChangePwd() {
    var arr = document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
    var msg="ChangePwd";
    var pwd1=document.getElementById("pwd1").value;
    var pwd2=document.getElementById("pwd2").value;
    var id;
    if(arr==null)
        id=28;
    else id=arr[2];
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/UserInfo",
        dataType: "xml",
        data:{userID:id,msg:msg,pwd1:pwd1,pwd2:pwd2},
        success: function (data) {
            var info=data.getElementsByTagName("Info");
            var flag=info[0].firstChild.nodeValue!="false";
            if (!flag)
                alert("密码修改失败 !");
            else alert("密码修改成功 !");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function loadXMLDoc()
{
    var arr =document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
    var msg="Load";
    var id=28;
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/UserInfo",
        dataType: "xml",
        data:{userID:id,msg:msg},
        success: function (data) {
            var info=data.getElementsByTagName("Info");
            document.getElementById("ID").innerHTML=info[0].firstChild.nodeValue;
            document.getElementById("UserName").innerHTML=info[1].firstChild.nodeValue;
            document.getElementById("TrueName").innerHTML=info[2].firstChild.nodeValue;
            document.getElementById("State").innerHTML=info[3].firstChild.nodeValue;
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}
