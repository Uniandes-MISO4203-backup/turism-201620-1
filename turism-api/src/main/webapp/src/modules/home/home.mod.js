(function (ng) {
    var mod = ng.module('homeModule', []);

  
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/home/';

            $sp.state('home', {
                url: '/home',
                views: {
                     mainView: {
                        templateUrl: basePath + 'home.tpl.html',
                        controller: 'homeCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);