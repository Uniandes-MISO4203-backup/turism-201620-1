(function (ng) {
    var mod = ng.module('faqModule', ['ngCrud']);

    mod.constant('faqModel', {
        name: 'faq',
        displayName: 'FAQs',
        url: 'faqs',
        fields: {
            name:{
                displayName: 'Title',
                type: 'String',
                required: true
            },
             question:{
                displayName: 'Question',
                type: 'String',
                required: true
            },
            response: {
                displayName: 'Answer',
                type: 'String',
                required: true
            }
        }
    });
    
     mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/faq/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('faq', {
                url: '/faqs?page&limit',
                abstract: true,
                parent: 'agencyDetail',
                views: {
                     agencyChieldView: {
                        templateUrl: basePath + 'faq.tpl.html',
                        controller: 'faqCtrl'
                    }
                },
                resolve: {
                    model: 'faqModel',
                    faqs: ['agency', '$stateParams', 'model', function (agency, $params, model) {
                            return agency.getList(model.url, $params);
                        }]                }
            });
            $sp.state('faqList', {
                url: '/list',
                parent: 'faq',
                views: {
                    faqView: {
                        templateUrl: basePath + 'list/faq.list.tpl.html',
                        controller: 'faqListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('faqNew', {
                url: '/new',
                parent: 'faq',
                views: {
                    faqView: {
                        templateUrl: basePath + 'new/faq.new.tpl.html',
                        controller: 'faqNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('faqInstance', {
                url: '/{faqId:int}',
                abstract: true,
                parent: 'faq',
                views: {
                    faqView: {
                        template: '<div ui-view="faqInstanceView"></div>'
                    }
                },
                resolve: {
                    faq: ['faqs', '$stateParams', function (faqs, $params) {
                            return faqs.get($params.faqId);
                        }]
                }
            });
            $sp.state('faqDetail', {
                url: '/details',
                parent: 'faqInstance',
                views: {
                    faqInstanceView: {
                        templateUrl: baseInstancePath + 'detail/faq.detail.tpl.html',
                        controller: 'faqDetailCtrl'
                    }
                }
            });
            $sp.state('faqEdit', {
                url: '/edit',
                sticky: true,
                parent: 'faqInstance',
                views: {
                    faqInstanceView: {
                        templateUrl: baseInstancePath + 'edit/faq.edit.tpl.html',
                        controller: 'faqEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('faqDelete', {
                url: '/delete',
                parent: 'faqInstance',
                views: {
                    faqInstanceView: {
                        templateUrl: baseInstancePath + 'delete/faq.delete.tpl.html',
                        controller: 'faqDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
           
	}]);
})(window.angular);