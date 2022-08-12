angular.module('market-front').controller('storeController', function ($scope, $http, $location, $rootScope) {
    const contextPath = 'http://localhost:8189/api/v1';
    let currentPage = 1;
    $scope.loadProducts = function (pageIndex = 1) {
        currentPage = pageIndex;
        $http({
            url: contextPath + '/products', method: 'GET', params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log('Content length: ' + response.data.content.length)
            $scope.emptyProductList = response.data.content.length <= 0
            if (response.data.numberOfElements < 1) {
                currentPage = response.data.totalPages;
                if(currentPage !== 0) {
                    $scope.loadProducts(currentPage)
                }
            } else {
                console.log(response);
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
        $http.put(contextPath + '/cart', product).then(function (response){
            $rootScope.cart_size++;
            console.log('Cart size in store = ' + $rootScope.cart_size)
        })
    }

    $scope.loadProducts(1);
});