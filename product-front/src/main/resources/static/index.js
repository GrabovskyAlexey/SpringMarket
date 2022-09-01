(function () {
    angular
        .module('market-front', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/edit_product/:productId', {
                templateUrl: 'edit_product/edit_product.html',
                controller: 'editProductController'
            })
            .when('/create_product', {
                templateUrl: 'create_product/create_product.html',
                controller: 'createProductController'
            })
            .when('/cart_view', {
                templateUrl: 'cart_view/cart_view.html',
                controller: 'cartController'
            })
            .when('/report', {
                templateUrl: 'report/report.html',
                controller: 'reportController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.webMarketUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.webMarketUser.token;
        }
    }
})();

angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http, $localStorage) {
        $rootScope.contextPath = 'http://localhost:5000/products/api/v1';

        $scope.loadCartSize = function () {
            let path;
            if ($localStorage.cartId == null) {
                path = $rootScope.contextPath + '/cart/';
            } else {
                path = $rootScope.contextPath + '/cart/' + $localStorage.cartId
            }
            $http.get(path).then(function (response) {
                if (response.data.cart != null) {
                    $rootScope.cart_size = response.data.cart.length;
                }
                $localStorage.cartId = response.data.id
            })
        };

        $rootScope.isUserLoggedIn = function () {
            return !!$localStorage.webMarketUser;
        }

        $scope.tryToLogIn = function () {
            $http.post($rootScope.contextPath + '/auth', $scope.user)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};
                        $scope.user.username = null;
                        $scope.user.password = null;
                    }
                }, function errorCallback(response) {
                    alert(response.data.message);
                });
        }

        $scope.tryToLogout = function () {
            $scope.clearUser();
            if ($scope.user.username) {
                $scope.user.username = null;
            }
            if ($scope.user.password) {
                $scope.user.password = null;
            }
        }

        $scope.clearUser = function () {
            delete $localStorage.webMarketUser;
            $http.defaults.headers.common.Authorization = '';
        }

        $scope.tryToRegister = function () {
            $http.post($rootScope.contextPath + '/register', $scope.newUser)
                .then(function successCallback(response) {
                    if (response.data.token) {
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                        $localStorage.webMarketUser = {username: $scope.newUser.username, token: response.data.token};

                        $scope.newUser.username = null;
                        $scope.newUser.password = null;
                        $scope.newUser.email = null;
                    }
                }, function errorCallback(response) {
                    alert(response.data.message);
                });
        }
        $scope.loadCartSize();
    }
)
