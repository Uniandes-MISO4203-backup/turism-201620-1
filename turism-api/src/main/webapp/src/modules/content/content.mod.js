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
        },
        parentContent: {
            displayName: 'Parent Content',
            type: 'Reference',
            model: 'contentModel',
            options: [],
            required: false
        }
    });

    mod.config(['$stateProvider',
        function ($sp) {
            var basePath = 'src/modules/content/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('content', {
                url: '/contents?page&limit',
                abstract: true,

                views: {
                    mainView: {
                        templateUrl: basePath + 'content.tpl.html',
                        controller: 'contentCtrl'
                    }
                },
                resolve: {
                    references: ['$q', 'Restangular', function ($q, r) {
                            return $q.all({
                                parentContent: r.all('contents').getList()
                            });
                        }],
                    model: 'contentModel',
                    contents: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
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
                url: '/{cotentId:int}',
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