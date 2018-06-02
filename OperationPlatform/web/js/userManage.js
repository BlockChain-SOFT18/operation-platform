var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {

    $scope.List=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var att="List";
        var postData=$.param({orgID:arr[2],att:att});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/UserManage",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    };

    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var userID=document.getElementById("UserID").value;
        var userName=document.getElementById("UserName").value;
        var state=document.getElementById("State").value;
        var att="Search";
        var postData=$.param({orgID:arr[2],UserID:userID,UserName:userName, State:state,att:att});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/UserManage",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    };

    $scope.Freeze=function (Count) {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var att="Freeze";
        var changeID=$scope.Info[Count].UserID;
        var postData=$.param({orgID:arr[2],ChangeID:changeID,att:att});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/UserManage",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            alert(result.data.Info+" is Freeze!");
        });
    };

    $scope.Active=function (Count) {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var att="Active";
        var changeID=$scope.Info[Count].UserID;
        var postData=$.param({orgID:arr[2],ChangeID:changeID,att:att});
        $http({
            method:"POST",
            url:"http://localhost:8080/OP/UserManage",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            alert(result.data.Info+" is Active!");
        });
    };

});



