(function (ng) {
    var mod = ng.module('taxModule', ['ngCrud']);

    mod.constant('taxModel', {
        name: 'tax',
        displayName: 'Tax',
        url: 'taxes',
        fields: {
            name:{
                displayName: 'Name',
                type: 'String',
                required: true
            },
             description:{
                displayName: 'Description',
                type: 'String',
                required: true
            },
            value: {
                displayName: 'Value',
                type: 'Number',
                required: true
            }
        }
    });
    
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/tax/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('tax', {
                url: '/taxes?page&limit',
                abstract: true,
                parent: 'tripDetail',
                views: {
                     tripChieldView: {
                        templateUrl: basePath + 'tax.tpl.html',
                        controller: 'taxCtrl'
                    }
                },
                resolve: {
                    model: 'taxModel',
                    taxes: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url);
                        }]                }
            });
            $sp.state('taxList', {
                url: '/list',
                parent: 'tax',
                views: {
                    taxView: {
                        templateUrl: basePath + 'list/tax.list.tpl.html',
                        controller: 'taxListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('taxNew', {
                url: '/new',
                parent: 'tax',
                views: {
                    taxView: {
                        templateUrl: basePath + 'new/tax.new.tpl.html',
                        controller: 'taxNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('taxInstance', {
                url: '/{taxId:int}',
                abstract: true,
                parent: 'tax',
                views: {
                    taxView: {
                        template: '<div ui-view="taxInstanceView"></div>'
                    }
                },
                resolve: {
                    tax: ['taxes', '$stateParams', function (taxes, $params) {
                            return taxes.get($params.taxId);
                        }]
                }
            });
            $sp.state('taxDetail', {
                url: '/details',
                parent: 'taxInstance',
                views: {
                    taxInstanceView: {
                        templateUrl: baseInstancePath + 'detail/tax.detail.tpl.html',
                        controller: 'taxDetailCtrl'
                    }
                }
            });
            $sp.state('taxEdit', {
                url: '/edit',
                sticky: true,
                parent: 'taxInstance',
                views: {
                    taxInstanceView: {
                        templateUrl: baseInstancePath + 'edit/tax.edit.tpl.html',
                        controller: 'taxEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('taxDelete', {
                url: '/delete',
                parent: 'taxInstance',
                views: {
                    taxInstanceView: {
                        templateUrl: baseInstancePath + 'delete/tax.delete.tpl.html',
                        controller: 'taxDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
           
	}]);
})(window.angular);