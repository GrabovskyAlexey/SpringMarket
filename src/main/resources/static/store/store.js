angular.module('market-front').controller('storeController', function ($scope, $http, $location, $rootScope, $localStorage) {
    const contextPath = 'http://localhost:8189/api/v1';
    let currentPage = 1;
    $scope.loadProducts = function (pageIndex = 1) {
        currentPage = pageIndex;
        $http({
            url: contextPath + '/products', method: 'GET', params: {
                p: pageIndex
            }
        }).then(function (response) {
            $scope.emptyProductList = response.data.content.length <= 0
            if (response.data.numberOfElements < 1) {
                currentPage = response.data.totalPages;
                if(currentPage !== 0) {
                    $scope.loadProducts(currentPage)
                }
            } else {
                $scope.productsPage = response.data;
            }
        });
    }

    $scope.deleteProduct = function (product) {
        $http.delete(contextPath + '/products/' + product.id).then(function (response) {
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

    $scope.addToCart = function(product){
        let cartItem = {id: null, cartId: $localStorage.cartId, count: 1, price: product.price, product: product}
        console.log(cartItem)
        $http.put(contextPath + '/cart/', cartItem).then(function (response){
            $rootScope.cart_size = response.data.cart!=null?response.data.cart.length:0;
        })
    }

    $scope.loadProducts(1);
});