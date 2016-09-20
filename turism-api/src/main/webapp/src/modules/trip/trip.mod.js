/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('tripModule', ['ngCrud', 'ui.router']);

    mod.constant('tripModel', {
        name: 'trip',
        displayName: 'Trip',
		url: 'trips',
        fields: {            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            image: {
                displayName: 'Image',
                type: 'Image',
                required: true
            },
            price: {
                displayName: 'Price',
                type: 'Long',
                required: true
            },
            quota: {
                displayName: 'Quota',
                type: 'Integer',
                required: true
            },
            duration: {
                displayName: 'Duration',
                type: 'Integer',
                required: true
            },        
            date: {
                displayName: 'Date',
                type: 'Date',
                required: true 
            },
            origin:{
                displayName: 'Origin',
                type: 'String',
                required: true
            },
            destination:{
                displayName: 'Destination',
                type: 'String',
                required: true
            } 
            
        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/trip/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('trip', {
                url: '/trips?page&limit',
                abstract: true,
                parent: 'agencyDetail',
                views: {
                     agencyChieldView: {
                        templateUrl: basePath + 'trip.tpl.html',
                        controller: 'tripCtrl'
                    }
                },
                resolve: {
                    model: 'tripModel',
                    trips: ['agency', '$stateParams', 'model', function (agency, $params, model) {
                            return agency.getList(model.url, $params);
                        }]                }
            });
            $sp.state('tripList', {
                url: '/list',
                parent: 'trip',
                views: {
                    tripView: {
                        templateUrl: basePath + 'list/trip.list.tpl.html',
                        controller: 'tripListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tripNew', {
                url: '/new',
                parent: 'trip',
                views: {
                    tripView: {
                        templateUrl: basePath + 'new/trip.new.tpl.html',
                        controller: 'tripNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tripInstance', {
                url: '/{tripId:int}',
                abstract: true,
                parent: 'trip',
                views: {
                    tripView: {
                        template: '<div ui-view="tripInstanceView"></div>'
                    }
                },
                resolve: {
                    trip: ['trips', '$stateParams', function (trips, $params) {
                            return trips.get($params.tripId);
                        }]
                }
            });
            $sp.state('tripDetail', {
                url: '/details',
                parent: 'tripInstance',
                views: {
                    tripInstanceView: {
                        templateUrl: baseInstancePath + 'detail/trip.detail.tpl.html',
                        controller: 'tripDetailCtrl'
                    }
                }
            });
            $sp.state('tripEdit', {
                url: '/edit',
                sticky: true,
                parent: 'tripInstance',
                views: {
                    tripInstanceView: {
                        templateUrl: baseInstancePath + 'edit/trip.edit.tpl.html',
                        controller: 'tripEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tripDelete', {
                url: '/delete',
                parent: 'tripInstance',
                views: {
                    tripInstanceView: {
                        templateUrl: baseInstancePath + 'delete/trip.delete.tpl.html',
                        controller: 'tripDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('tripCategory', {
                url: '/category',
                parent: 'tripDetail',
                abstract: true,
                views: {
                    tripChieldView: {
                        template: '<div ui-view="tripCategoryView"></div>'
                    }
                },
                resolve: {
                    category: ['trip', function (trip) {
                            return trip.getList('category');
                        }],
                    model: 'categoryModel'
                }
            });
            $sp.state('tripCategoryList', {
                url: '/list',
                parent: 'tripCategory',
                views: {
                    tripCategoryView: {
                        templateUrl: baseInstancePath + 'category/list/trip.category.list.tpl.html',
                        controller: 'tripCategoryListCtrl'
                    }
                }
            });
            $sp.state('tripCategoryEdit', {
                url: '/edit',
                parent: 'tripCategory',
                views: {
                    tripCategoryView: {
                        templateUrl: baseInstancePath + 'category/edit/trip.category.edit.tpl.html',
                        controller: 'tripCategoryEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    pool: ['Restangular', 'model', function (r, model) {
                            return r.all(model.url).getList();
                        }]
                }
            });
            
            $sp.state('tripNews', {
                url: '/news',
                parent: 'tripDetail',
                abstract: true,
                views: {
                    tripChieldView: {
                        template: '<div ui-view="tripNewsView"></div>'
                    }
                },
                resolve: {
                    category: ['trip', function (trip) {
                            return trip.getList('news');
                        }],
                    model: 'newsModel'
                }
            });
            $sp.state('tripNewsList', {
                url: '/list',
                parent: 'tripNews',
                views: {
                    tripNewsView: {
                        templateUrl: baseInstancePath + 'news/list/trip.news.list.tpl.html',
                        controller: 'tripNewsListCtrl'
                    }
                }
            });
            $sp.state('tripNewsEdit', {
                url: '/edit',
                parent: 'tripNews',
                views: {
                    tripNewsView: {
                        templateUrl: baseInstancePath + 'news/edit/trip.news.edit.tpl.html',
                        controller: 'tripNewsEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    pool: ['Restangular', 'model', function (r, model) {
                            return r.all(model.url).getList();
                        }]
                }
            });
            
            $sp.state('tripGallery', {
                url: '/tripGallery',
                views: {
                     mainView: {
                        templateUrl: basePath + 'list/trip.gallery.tpl.html',
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
