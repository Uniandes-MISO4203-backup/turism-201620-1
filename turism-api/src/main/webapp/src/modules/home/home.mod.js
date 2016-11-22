(function (ng) {
    var mod = ng.module('homeModule', ['ui.router']);

  
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/home/';

            $sp.state('home', {
                url: '/home?categoryId',
                abstract: true,
                views: {
                     mainView: {
                        templateUrl: basePath + 'home.tpl.html',
                        controller: 'homeCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            
             $sp.state('homeTripGallery', {
                url: '',
                parent: 'home',
               views: {
                     tripGalleryView: {
                        templateUrl: 'src/modules/trip/list/trip.gallery.tpl.html',
                        controller: 'tripGalleryCtrl',
                        controllerAs: 'ctrl'    
                    }
                }
            });
	}]);
})(window.angular);