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
            url:"http://localhost:8080/OP/TradeInfo",
            headers : {
                'Content-Type' : "application/x-www-form-urlencoded;Charset=UTF-8"
            },
            data:postData
        }).then(function (result) {

            new Morris.Line({
                element: 'myfirstchart',
                data: [
                    { year: '2008', value: 20 },
                    { year: '2009', value: 10 },
                    { year: '2010', value: 5 },
                    { year: '2011', value: 5 },
                    { year: '2012', value: 20 }
                ],
                xkey: 'year',
                ykeys: ['value'],
                labels: ['Value']
            });

            $scope.Info = result.data.Info;
        });
    }
});



