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
        var userID=document.getElementById("UserID").value;
        var startDate=document.getElementById("StartDate").value;
        var endDate=document.getElementById("EndDate").value;
        var att="Search";
        var postData=$.param({orgID:arr[2],UserID:userID,TradeType:tradeType,StartDate:startDate, EndDate:endDate,att:att});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/ManualCharge",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
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
            url:"http://localhost:8080/OP/ManualCharge",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            alert(result.data.Info);
        });
    };

});
