(function (ng) {
    var mod = ng.module('tripviewModule', ['ui.bootstrap']);

  
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/tripview/';

            $sp.state('tripview', {
                url: '/tripview',
                abstract: true,
                views: {
                     mainView: {
                        templateUrl: basePath + 'tripview.tpl.html',
                        controller: 'tripviewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
             $sp.state('tripDetailInstance', {
                url: '/{tripId:int}',
                parent: 'tripview',
               views: {
                     tripDetailView: {
                        templateUrl: 'src/modules/trip/instance/detail/trip.detail.tpl.html',
                        controller: 'tripDetailCtrl',
                        controllerAs: 'ctrl'    
                    }
                }
            });
	}]);
})(window.angular);