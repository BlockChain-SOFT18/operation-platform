var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {

    $scope.Info=[{
        "No":"",
        "FileName":"",
        "Address":""
    }];

    $scope.Init=function () {
        $scope.Info=null;
    };

    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var fileType=document.getElementById("FileType").value;
        var accountType=document.getElementById("AccountType").value;
        var accountTime=document.getElementById("AccountTime").value;
        var postData=$.param({orgID:arr[2],FileType:fileType,AccountType:accountType,AccountTime:accountTime});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/FileDownload",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    };

    $scope.Download=function (Count) {
        window.open($scope.Info[Count].Address);
    }

});
