var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var tradeType=document.getElementById("TradeType").value;
        var startDate=document.getElementById("StartDate").value;
        var endDate=document.getElementById("EndDate").value;
        var id;
        if(arr==null)
            id=2;
        else id=arr[2];
        var postData=$.param({orgID:id,TradeType:tradeType,StartDate:startDate, EndDate:endDate});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/TradeCheck",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    }
});
