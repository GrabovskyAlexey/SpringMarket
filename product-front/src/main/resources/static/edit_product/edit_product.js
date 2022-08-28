angular.module('market-front').controller('editProductController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8190/api/v1';

    $scope.getCategories = () => {
        $http.get(contextPath + '/categories')
            .then(function(response){
                $scope.categories = response.data;
                console.log($scope.categories)
            })
    }
    $scope.getCategories();

    $scope.updateProduct = function () {
        $http.put(contextPath + '/products', $scope.update_product)
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
        $http.get(contextPath + '/products/' + $routeParams.productId)
            .then(function successCallback(response){
                $scope.update_product = response.data;
            }, function errorCallback(response){
            alert(response.data.messages);
            $location.path('/store');
        });
    }

    $scope.loadProduct();
});