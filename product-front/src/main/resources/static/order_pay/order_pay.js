angular.module('market-front')
    .controller('orderPayController',
        function ($scope, $http, $location, $localStorage, $routeParams) {

            $scope.loadOrder = function () {
                $http({
                    url: 'http://localhost:5000/orders/api/v1/orders/' + $routeParams.orderId,
                    method: 'GET'
                }).then(function (response) {
                    $scope.order = response.data;
                    console.log($scope.order)
                });
            };

            $scope.loadOrder()

            function loadAsync(url, callback) {
                var s = document.createElement('script');
                s.setAttribute('src', url);
                s.onload = callback;
                document.head.insertBefore(s, document.head.firstElementChild);
            }

            loadAsync('https://www.paypal.com/sdk/js?client-id=AT1fVTVj-_8MXDHA3pwMjqNHPcXBZl_LVuGdtxQjGCfAoClh9YAsAE4CBKFS7sv4CEV8aZIf9C-oHjcL&currency=RUB',
                function () {
                    paypal.Buttons({
                        // Set up the transaction
                        createOrder: function (data, actions) {
                            return fetch('http://localhost:5000/orders/api/v1/paypal/create/' + $scope.order.id, {
                                method: 'post',
                                headers: {
                                    'content-type': 'application/json'
                                }
                            }).then(function (response) {
                                console.log("createOrder")
                                return response.text();
                            });
                        },

                        // Finalize the transaction
                        onApprove: function (data, actions) {
                            console.log(data)
                            return fetch('http://localhost:5000/orders/api/v1/paypal/capture/' + data.orderID, {
                                method: 'post',
                                headers: {
                                    'content-type': 'application/json'
                                }
                            }).then(function (response) {
                                $location.path('/orders');
                                console.log("onApprove")
                                response.text().then(msg => alert(msg));

                            });
                        }
                    }).render('#paypal-buttons');
                });
        });