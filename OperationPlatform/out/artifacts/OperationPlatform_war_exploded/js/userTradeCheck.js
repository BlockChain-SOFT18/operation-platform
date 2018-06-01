$(document).ready(function () {
    $('#dataTables-example').dataTable();
});
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $scope.Search=function () {
        var tradeType=document.getElementById("TradeType").value;
        var tradeState=document.getElementById("TradeState").value;
        var userID=document.getElementById("UserID").value;
        var startDate=document.getElementById("StartDate").value;
        var endDate=document.getElementById("EndDate").value;
        var minMoney=document.getElementById("MinMoney").value;
        var maxMoney=document.getElementById("MaxMoney").value;
        var postData=$.param({TradeType:tradeType,TradeState:tradeState,UserID:userID,StartDate:startDate,
            EndDate:endDate,MinMoney:minMoney,MaxMoney:maxMoney});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/UserTradeCheck",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    }
});