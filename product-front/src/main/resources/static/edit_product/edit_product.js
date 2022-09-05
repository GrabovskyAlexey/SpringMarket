angular.module('market-front').controller('editProductController', function ($scope, $http, $rootScope, $routeParams, $location) {
    $scope.getCategories = () => {
        $http.get($rootScope.contextPath + '/products/api/v1/categories')
            .then(function(response){
                $scope.categories = response.data;
                console.log($scope.categories)
            })
    }
    $scope.getCategories();

    $scope.updateProduct = function () {
        $http.put($rootScope.contextPath + '/products/api/v1/products', $scope.update_product)
            .then(function successCallback(response) {
                    $scope.update_product = null;
                    alert("Продукт успешно обновлен");
                    $location.path('/store');
                }, function failCallback(response) {
                    alert(response.data.message);
                }
            );
    }
    $scope.loadProduct = function (){
        $http.get($rootScope.contextPath + '/products/api/v1/products/' + $routeParams.productId)
            .then(function successCallback(response){
                $scope.update_product = response.data;
            }, function errorCallback(response){
            alert(response.data.messages);
            $location.path('/store');
        });
    }

    $scope.loadProduct();
});