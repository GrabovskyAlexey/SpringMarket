angular.module('market-front').controller('cartController', function ($scope, $http, $rootScope,$localStorage) {
    const contextPath = 'http://localhost:8190/api/v1';
    $scope.loadCart = function () {
        let path;
        if ($localStorage.cartId == null) {
            path = contextPath + '/cart/';
        } else {
            path = contextPath + '/cart/' + $localStorage.cartId
        }
        $http.get(path).then(function (response){
            console.log(response.data)
            $scope.cart = response.data.cart;
            console.log($scope.cart)
            $rootScope.cart_size = $scope.cart.length;
        })
    }

    $scope.deleteProductFromCart = function (product) {
        $http.delete(contextPath + '/cart/' + $localStorage.cartId +'/'+ product.id)
            .then(function (response) {
                $scope.loadCart();
        });
    }

    $scope.loadCart();
});