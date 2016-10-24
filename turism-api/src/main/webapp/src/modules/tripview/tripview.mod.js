(function (ng) {
    var mod = ng.module('tripviewModule', []);

  
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
                url: '',
                parent: 'tripview',
               views: {
                     tripGalleryView: {
                        templateUrl: 'src/modules/trip/instance/detail/trip.detail.tpl.html',
                        controller: 'tripDetailCtrl',
                        controllerAs: 'ctrl'    
                    }
                }
            });
	}]);
})(window.angular);