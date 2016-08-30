(function (ng) {
    var mod = ng.module('commentModule', ['ngCrud']);

    mod.constant('commentModel', {
        name: 'comment',
        displayName: 'Comments',
        url: 'comments',
        fields: {
            name: {
                displayName: 'Title',
                type: 'String',
                required: true
            },
            text: {
                displayName: 'Text',
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
            var basePath = 'src/modules/comment/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('comment', {
                url: '/comment?page&limit',
                abstract: true,
                parent: 'tripDetail',
                views: {
                    agencyChieldView: {
                        templateUrl: basePath + 'comment.tpl.html',
                        controller: 'commentCtrl'
                    }
                },
                resolve: {
                    model: 'commentModel',
                    comments: ['trip', '$stateParams', 'model', function (trip, $params, model) {
                            return trip.getList(model.url, $params);
                        }]}
            });
            $sp.state('commentList', {
                url: '/list',
                parent: 'comment',
                views: {
                    commentView: {
                        templateUrl: basePath + 'list/comment.list.tpl.html',
                        controller: 'commentListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentNew', {
                url: '/new',
                parent: 'comment',
                views: {
                    commentView: {
                        templateUrl: basePath + 'new/comment.new.tpl.html',
                        controller: 'commentNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentInstance', {
                url: '/{commentId:int}',
                abstract: true,
                parent: 'comment',
                views: {
                    commentView: {
                        template: '<div ui-view="commentInstanceView"></div>'
                    }
                },
                resolve: {
                    comment: ['comments', '$stateParams', function (comments, $params) {
                            return comments.get($params.commentId);
                        }]
                }
            });
            $sp.state('commentDetail', {
                url: '/details',
                parent: 'commentInstance',
                views: {
                    commentInstanceView: {
                        templateUrl: baseInstancePath + 'detail/comment.detail.tpl.html',
                        controller: 'commentDetailCtrl'
                    }
                }
            });
            $sp.state('commentEdit', {
                url: '/edit',
                sticky: true,
                parent: 'commentInstance',
                views: {
                    commentInstanceView: {
                        templateUrl: baseInstancePath + 'edit/comment.edit.tpl.html',
                        controller: 'commentEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('commentDelete', {
                url: '/delete',
                parent: 'commentInstance',
                views: {
                    commentInstanceView: {
                        templateUrl: baseInstancePath + 'delete/comment.delete.tpl.html',
                        controller: 'commentDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });

        }]);
})(window.angular);