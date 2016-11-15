(function (ng) {
    var mod = ng.module('raitingModule', ['ngCrud','ui.router']);

    mod.constant('raitingModel', {
        name: 'raiting',
        displayName: 'Raitings',
        url: 'raitings',
        fields: {
            date: {
                displayName: 'Date',
                type: 'Date',
                required: true
            },
            value:{
                displayName: 'Value',
                type: 'Integer',
                required: true
            },
             textComment:{
                displayName: 'Comentario',
                type: 'String',
                required: false
            }
        }
    });
    
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/raiting/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('raiting', {
                url: '/raitings?page&limit',
                abstract: true,
                parent: 'tripDetail',
                views: {
                     tripChieldView: {
                        templateUrl: basePath + 'raiting.tpl.html',
                        controller: 'raitingCtrl'
                    }
                },
                resolve: {
                    model: 'raitingModel',
                    raitings: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url, $params);
                        }]                }
            });
            $sp.state('raitingItemTrip', {
                url: '/raitings?page&limit',
                abstract: true,
                parent: 'tripDetailItem',
                views: {
                    tripChieldView: {
                        templateUrl: basePath + 'raiting.tpl.html',
                        controller: 'raitingCtrl'
                    }
                },
                resolve: {
                    model: 'raitingModel',
                    raitings: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url, $params);
                        }]                }
            });
            $sp.state('raitingItemTripList', {
                url: '/list',
                parent: 'raitingItemTrip',
                views: {
                     raitingView: {
                        templateUrl: basePath + 'list/raiting.list.tpl.html',
                        controller: 'raitingListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('raitingList', {
                url: '/list',
                parent: 'raiting',
                views: {
                    raitingView: {
                        templateUrl: basePath + 'list/raiting.list.tpl.html',
                        controller: 'raitingListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('raitingNew', {
                url: '/new',
                parent: 'raitingItemTrip',
                views: {
                    raitingView: {
                        templateUrl: basePath + 'new/raiting.new.tpl.html',
                        controller: 'raitingNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('raitingInstance', {
                url: '/{raitingId:int}',
                abstract: true,
                parent: 'raitingItemTrip',
                views: {
                    raitingView: {
                        template: '<div ui-view="raitingInstanceView"></div>'
                    }
                },
                resolve: {
                    raiting: ['raitings', '$stateParams', function (raitings, $params) {
                            return raitings.get($params.raitingId);
                        }]
                }
            });
            $sp.state('raitingDetail', {
                url: '/details',
                parent: 'raitingInstance',
                views: {
                    raitingInstanceView: {
                        templateUrl: baseInstancePath + 'detail/raiting.detail.tpl.html',
                        controller: 'raitingDetailCtrl'
                    }
                }
            });
            $sp.state('raitingEdit', {
                url: '/edit',
                sticky: true,
                parent: 'raitingInstance',
                views: {
                    raitingInstanceView: {
                        templateUrl: baseInstancePath + 'edit/raiting.edit.tpl.html',
                        controller: 'raitingEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('raitingDelete', {
                url: '/delete',
                parent: 'raitingInstance',
                views: {
                    raitingInstanceView: {
                        templateUrl: baseInstancePath + 'delete/raiting.delete.tpl.html',
                        controller: 'raitingDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
           
	}]);
})(window.angular);