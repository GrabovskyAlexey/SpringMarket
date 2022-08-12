angular.module('market-front').controller('cartController', function ($scope, $http, $rootScope) {
    const contextPath = 'http://localhost:8189/api/v1';
    $scope.loadCart = function () {
        $http.get(contextPath + '/cart').then(function (response){
            $scope.cart = response.data.cart;
            $rootScope.cart_size = $scope.cart.length;
            console.log(response.data)
        })
    }

    $scope.deleteProductFromCart = function (product) {
        $http.delete(contextPath + '/cart/' + product.id)
            .then(function (response) {
                $scope.loadCart();
        });
    }

    $scope.loadCart();
});