jQuery(document).ready(function($) {
    $('.theme-login1').click(function(){
        $('.theme-popover1-mask').fadeIn(100);
        $('.theme-popover1').slideDown(200);
    });
    $('.theme-login2').click(function(){
        $('.theme-popover2-mask').fadeIn(100);
        $('.theme-popover2').slideDown(200);
    });
    $('.theme-poptit1 .close').click(function(){
        $('.theme-popover1-mask').fadeOut(100);
        $('.theme-popover1').slideUp(200);
    });
    $('.theme-poptit2 .close').click(function(){
        $('.theme-popover2-mask').fadeOut(100);
        $('.theme-popover2').slideUp(200);
    });
});

function validate1() {
    var username=document.getElementById("log1").value;
    var password=document.getElementById("pwd1").value;
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/Login",
        dataType: "xml",
        data:{UserName:username,Password:password,Type:1},
        success: function (data) {
            alert(data);
            var info=data.getElementsByTagName("Info");
            var flag=((info[0].firstChild.nodeValue)!="false");
            if (!flag)
                alert("用户名或密码不正确，请重新登录 !");
            else {
                var Days = 30;
                var exp　= new Date();
                exp.setTime(exp.getTime() + Days*24*60*60*1000);
                document.cookie = "userID" + "="+ escape (data) + "";expires="" + exp.toGMTString();
                location.href ="./userInfo.html";
                location.href="./userTradeCheck.html";
                window.location.href="./userInfo.html";
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.readyState);
            alert(XMLHttpRequest.status);
            alert(textStatus);
        }
    });
}

function validate2() {
    var username=document.getElementById("log2").value;
    var password=document.getElementById("pwd2").value;
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/OP/Login",
        dataType: "xml",
        data:{UserName:username,Password:password,Type:2},
        success: function (data) {
            var info=data.getElementsByTagName("Info");
            var flag=((info[0].firstChild.nodeValue)!="false");
            if (!flag)
                alert("用户名或密码不正确，请重新登录 !");
            else {
                var Days = 30;
                var exp　= new Date();
                exp.setTime(exp.getTime() + Days*24*60*60*1000);
                document.cookie = "orgID" + "="+ escape (data) + "";expires="" + exp.toGMTString();
                location.href ="./orgInfo.html";
                location.href="./tradeCheck.html";
                location.href="./fileDownload.html";
                location.href="./manualCharge.html";
                location.href="./poundage.html";
                location.href="./tradeInfo.html";
                location.href="./userManage.html";
                window.location.href="./orgInfo.html";
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown,data) {
            alert(XMLHttpRequest.readyState);
            alert(XMLHttpRequest.status);
            alert(textStatus);
        }
    });
}


