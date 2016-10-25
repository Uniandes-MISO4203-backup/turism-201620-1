(function (ng) {
    var mod = ng.module('roleModule', ['ngCrud']);
    mod.controller('roleCtrl', ['$rootScope', 'Restangular','$state', function ($rootScope, Restangular) {

        $rootScope.auth = function () {
                Restangular.all("users").customGET('me').then(function (response) {
                    if (response == null) {
                        $rootScope.category = false;
                        $rootScope.agency = false;
                        $rootScope.client = false;
                        $rootScope.product = false;
                        $rootScope.visitor = true;
                    } else {
                        var roles = $rootScope.roles = response.roles;
                        if (roles.indexOf("client") !== -1) {
                            $rootScope.category = false;
                            $rootScope.agency = false;
                            $rootScope.client = true;
                            $rootScope.product = false;
                            $rootScope.visitor = false;
                        }
                        if (roles.indexOf("agency") !== -1) {
                            $rootScope.category = false;
                            $rootScope.agency = true;
                            $rootScope.client = false;
                            $rootScope.product = false;
                            $rootScope.visitor = false;
                        }
                        if (roles.indexOf("admin") !== -1) {
                            $rootScope.category = true;
                            $rootScope.agency = true;
                            $rootScope.client = true;
                            $rootScope.product = true;
                            $rootScope.visitor = false;
                        }
                    }
                });
            };
        $rootScope.auth();
        $rootScope.$on('logged-in', function () {
            $rootScope.auth();
        });

        $rootScope.$on('logged-out', function () {
            $rootScope.auth();
        });
    }]);
})(window.angular);




