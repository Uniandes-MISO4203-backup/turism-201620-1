(function (ng) {
    var mod = ng.module('contentModule', ['ngCrud']);

    mod.constant('contentModel', {
        name: 'content',
        displayName: 'Contents',
        url: 'contents',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            contentValue: {
                displayName: 'Content',
                type: 'String',
                required: true
            },
            date: {
                displayName: 'Date',
                type: 'Date',
                required: true
            }
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/content/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('content', {
                url: '/contents?page&limit',
                abstract: true,
                parent: 'tripDetail',
                views: {
                    tripChieldView: {
                        templateUrl: basePath + 'content.tpl.html',
                        controller: 'contentCtrl'
                    }
                },
                resolve: {
                    model: 'contentModel',
                    contents: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url);
                        }]                }
            });
            $sp.state('contentList', {
                url: '/list',
                parent: 'content',
                views: {
                    contentView: {
                        templateUrl: basePath + 'list/content.list.tpl.html',
                        controller: 'contentListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('contentNew', {
                url: '/new',
                parent: 'content',
                views: {
                    contentView: {
                        templateUrl: basePath + 'new/content.new.tpl.html',
                        controller: 'contentNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('contentInstance', {
                url: '/{contentId:int}',
                abstract: true,
                parent: 'content',
                views: {
                    contentView: {
                        template: '<div ui-view="contentInstanceView"></div>'
                    }
                },
                resolve: {
                    content: ['contents', '$stateParams', function (contents, $params) {
                            return contents.get($params.contentId);
                        }]
                }
            });
            $sp.state('contentDetail', {
                url: '/details',
                parent: 'contentInstance',
                views: {
                    contentInstanceView: {
                        templateUrl: baseInstancePath + 'detail/content.detail.tpl.html',
                        controller: 'contentDetailCtrl'
                    }
                }
            });
            $sp.state('contentEdit', {
                url: '/edit',
                sticky: true,
                parent: 'contentInstance',
                views: {
                    contentInstanceView: {
                        templateUrl: baseInstancePath + 'edit/content.edit.tpl.html',
                        controller: 'contentEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('contentDelete', {
                url: '/delete',
                parent: 'contentInstance',
                views: {
                    contentInstanceView: {
                        templateUrl: baseInstancePath + 'delete/content.delete.tpl.html',
                        controller: 'contentDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });

        }]);
})(window.angular);