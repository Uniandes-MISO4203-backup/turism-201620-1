(function (ng) {
    var mod = ng.module('homeModule', []);

  
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/home/';

            $sp.state('home', {
                url: '/home',
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
                        controller: 'tripListCtrl',
                        controllerAs: 'ctrl'    
                    }
                },
                resolve: {
                    model: 'tripModel',
                    trips: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]                }
            });
	}]);
})(window.angular);