angular.module('market-front').controller('storeController', function ($scope, $http, $location, $rootScope, $localStorage) {
    $scope.loadProducts = function () {
        $http.get($rootScope.contextPath + '/products/api/v1/products').then(function (response) {
            console.log(response.data)
            $scope.productsPage = response.data;
        });
    }

    $scope.deleteProduct = function (product) {
        $http.delete($rootScope.contextPath + '/products/api/v1/products/' + product.id).then(function (response) {
            alert(response.data.message)
            $scope.loadProducts(currentPage)
        });
    }

    $scope.range = function (min, max, step) {
        step = step || 1;
        let input = [];
        for (let i = min; i <= max; i += step) {
            input.push(i);
        }
        return input;
    };

    $scope.editProduct = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.addToCart = function (product) {
        let cartItem = {id: null, cartId: $localStorage.cartId, count: 1, price: product.price, productName: product.title, productId: product.id}
        console.log(cartItem)
        $http.put($rootScope.contextPath + '/cart/api/v1/cart/' + $localStorage.cartId, cartItem).then(function (response) {
            $rootScope.cart_size = response.data.cart != null ? response.data.cart.length : 0;
        })
    }

    $scope.loadProducts(1);
});