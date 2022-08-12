angular.module('market-front').controller('createProductController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/api/v1';

    $scope.createNewProduct = function () {
        if ($scope.new_product == null) {
            alert("Форма не заполнена");
            return;
        }
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
});