angular.module('market-front').controller('ordersController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5000/orders/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
                console.log($scope.MyOrders)
            });
    }

    $scope.goToPay = function (orderId) {
        $location.path('/order_pay/' + orderId);
    }

    $scope.loadOrders();
});