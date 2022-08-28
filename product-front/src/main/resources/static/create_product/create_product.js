angular.module('market-front').controller('createProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8190/api/v1';

    $scope.createNewProduct = function () {
        if ($scope.new_product == null) {
            alert("Форма не заполнена");
            return;
        }
        console.log($scope.new_product)
        $http.post(contextPath + '/products', $scope.new_product)
            .then(function successCallback(response) {
                    $scope.new_product = null;
                    alert("Продукт успешно добавлен");
                    $location.path('/store');
                }, function failCallback(response) {
                    console.log(response.data)
                    alert(response.data.message);
                }
            );
    }
    $scope.getCategories = () => {
        $http.get(contextPath + '/categories')
            .then(function(response){
            $scope.categories = response.data;
            console.log($scope.categories)
        })
    }
    $scope.getCategories();
});