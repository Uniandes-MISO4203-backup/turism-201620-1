(function (ng) {
    var mod = ng.module('newsModule', ['ngCrud']);

    mod.constant('newsModel', {
        name: 'news',
        displayName: 'News',
        url: 'news',
        fields: {
            name:{
                displayName: 'Name',
                type: 'String',
                required: true
            },
             content:{
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
        function($sp){
            var basePath = 'src/modules/news/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('news', {
                url: '/news?page&limit',
                abstract: true,
                parent: 'tripDetailInstance',
                views: {
                        tripChieldView: {
                        templateUrl: basePath + 'news.tpl.html',
                        controller: 'newsCtrl'
                    }
                },
                resolve: {
                    model: 'newsModel',
                    news: ['Restangular', '$stateParams', 'model', function (r, $params, model) {
                            return r.all(model.url).getList(model.url);
                        }]                }
            });
            $sp.state('newsList', {
                url: '/list',
                parent: 'news',
                views: {
                    newsView: {
                        templateUrl: basePath + 'list/news.list.tpl.html',
                        controller: 'newsListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('newsNew', {
                url: '/new',
                parent: 'news',
                views: {
                    newsView: {
                        templateUrl: basePath + 'new/news.new.tpl.html',
                        controller: 'newsNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('newsInstance', {
                url: '/{newsId:int}',
                abstract: true,
                parent: 'news',
                views: {
                    newsView: {
                        template: '<div ui-view="newsInstanceView"></div>'
                    }
                },
                resolve: {
                    news: ['news', '$stateParams', function (news, $params) {
                            return news.get($params.newsId);
                        }]
                }
            });
            $sp.state('newsDetail', {
                url: '/details',
                parent: 'newsInstance',
                views: {
                    newsInstanceView: {
                        templateUrl: baseInstancePath + 'detail/news.detail.tpl.html',
                        controller: 'newsDetailCtrl'
                    }
                }
            });
            $sp.state('newsEdit', {
                url: '/edit',
                sticky: true,
                parent: 'newsInstance',
                views: {
                    newsInstanceView: {
                        templateUrl: baseInstancePath + 'edit/news.edit.tpl.html',
                        controller: 'newsEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('newsDelete', {
                url: '/delete',
                parent: 'newsInstance',
                views: {
                    newsInstanceView: {
                        templateUrl: baseInstancePath + 'delete/news.delete.tpl.html',
                        controller: 'newsDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
           
	}]);
})(window.angular);