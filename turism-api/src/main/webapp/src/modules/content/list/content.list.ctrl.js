(function (ng) {

    var mod = ng.module("contentModule");

    mod.controller("contentListCtrl", ["$scope", '$state', 'contents', '$stateParams','Restangular',
        function ($scope, $state, contents, $params,Restangular) {
            $scope.records = contents;

            //Paginación
            this.itemsPerPage = $params.limit;
            this.currentPage = $params.page;
            this.totalItems = contents.totalRecords;
            
            $scope.categorys = [];
            
      
            $scope.filtrar = function (parentCategory) {
                Restangular.all("contents").customGET(parentCategory).then(function (response) {                    
                        $scope.records=response;
                });
            };

            this.pageChanged = function () {
                $state.go('contentList', {page: this.currentPage});
            };

            $scope.actions = {
                create: {
                    displayName: 'Create',
                    icon: 'plus',
                    fn: function () {
                        $state.go('contentNew');
                    }
                },
                refresh: {
                    displayName: 'Refresh',
                    icon: 'refresh',
                    fn: function () {
                        $state.reload();
                    }
                },
                cancel: {
                    displayName: 'Go back',
                    icon: 'arrow-left',
                    fn: function () {
                        $state.go('agencyDetail');
                    }
                }

            };
            $scope.recordActions = {
                detail: {
                    displayName: 'Detail',
                    icon: 'eye-open',
                    fn: function (rc) {
                        $state.go('contentDetail', {contentId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                edit: {
                    displayName: 'Edit',
                    icon: 'edit',
                    fn: function (rc) {
                        $state.go('contentEdit', {contentId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                },
                delete: {
                    displayName: 'Delete',
                    icon: 'minus',
                    fn: function (rc) {
                        $state.go('contentDelete', {contentId: rc.id});
                    },
                    show: function () {
                        return true;
                    }
                }
            };
        }]);
})(window.angular);