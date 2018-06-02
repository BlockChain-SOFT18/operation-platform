var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var startDate=document.getElementById("StartDate").value;
        var endDate=document.getElementById("EndDate").value;
        var channel=document.getElementById("Channel").value;
        var postData=$.param({orgID:arr[2],StartDate:startDate, EndDate:endDate,Channel:channel});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/Poundage",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    }
});
