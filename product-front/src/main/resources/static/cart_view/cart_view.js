angular.module('market-front').controller('cartController', function ($scope, $http, $rootScope, $localStorage) {
    $scope.loadCart = function () {
        let path;
        if ($localStorage.cartId == null) {
            path = $rootScope.contextPath + '/cart/api/v1/cart/';
        } else {
            path = $rootScope.contextPath + '/cart/api/v1/cart/' + $localStorage.cartId
        }
        $http.get(path).then(function (response){
            console.log(response.data)
            $scope.cart = response.data.cart;
            console.log($scope.cart)
            $rootScope.cart_size = $scope.cart.length;
        })
    }

    $scope.deleteProductFromCart = function (product) {
        $http.delete($rootScope.contextPath + '/cart/api/v1/cart/' + $localStorage.cartId +'/'+ product.id)
            .then(function (response) {
                $scope.loadCart();
        });
    }

    $scope.createOrder = () => {
        console.log("start ordering")
        $http.post($rootScope.contextPath + '/cart/api/v1/cart/' + $localStorage.cartId +'/order', $scope.address)
        .then(function (response){
            console.log("create order");
        });
    }

    $scope.loadCart();
});