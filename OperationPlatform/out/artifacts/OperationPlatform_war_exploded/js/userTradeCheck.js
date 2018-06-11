var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"userID"+"=([^;]*)(;|$)"));
        var tradeType=document.getElementById("TradeType").value;
        var startDate=document.getElementById("StartDate").value;
        var endDate=document.getElementById("EndDate").value;
        var postData=$.param({userID:arr[2],TradeType:tradeType,StartDate:startDate, EndDate:endDate});
        $http({
            method:"POST",
            url:"../UserTradeCheck",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    }
});