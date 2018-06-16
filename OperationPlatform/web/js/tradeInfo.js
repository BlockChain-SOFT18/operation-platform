function loadXMLDoc() {
    Morris.Line({
        element: 'myfirstchart',
        data: [
            { year: '2014', value: 13 },
            { year: '2015', value: 17 },
            { year: '2016', value: 25 },
            { year: '2017', value: 29 },
            { year: '2018', value: 33 }
        ],
        xkey: 'year',
        ykeys: ['value'],
        labels: ['Value']
    });
}

var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    $scope.Search=function () {
        var arr = document.cookie.match(new RegExp("(^| )"+"orgID"+"=([^;]*)(;|$)"));
        var tradeType=document.getElementById("TradeType").value;
        var Date1=document.getElementById("Date1").value;
        var Date2=document.getElementById("Date2").value;
        var postData=$.param({orgID:arr[2],TradeType:tradeType,Date1:Date1, Date2:Date2});
        $http({
            method:"POST",
            url:"../TradeInfo",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;charset=UTF-8"
            },
            data:postData
        }).then(function (result) {
            $scope.Info = result.data.Info;
        });
    }
});



