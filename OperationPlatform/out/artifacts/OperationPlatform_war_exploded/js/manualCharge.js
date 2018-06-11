var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {

    $scope.Info=[{
        "OrderID":"",
        "OrderTime":"",
        "UserID":"",
        "TradeType":"",
        "TradeMoney":"",
        "TradeState":"",
        "Money":""
    }];

    $scope.Init=function () {
        $scope.Info=null;
    };

    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var tradeType=document.getElementById("TradeType").value;
        var time=document.getElementById("Time").value;
        var att="Search";
        var postData=$.param({orgID:arr[2],TradeType:tradeType,Time:time,att:att});
        $http({
            method:"POST",
            url:"../ManualCharge",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    };

    $scope.Adjust=function (Count) {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var att="Adjust";
        var changeID=$scope.Info[Count].UserID;
        var money=$scope.Info[Count].Money;
        var postData=$.param({orgID:arr[2],ChangeID:changeID,Money:money,att:att});
        $http({
            method:"POST",
            url:"../ManualCharge",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            alert(result.data.Info);
            window.location.reload();
        });
    };

});
