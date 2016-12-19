/**
 * 
 */
/*var EndDate=new Date(Date.parse("2016-11-01 00:00:00".replace(/-/g,"/")));
var StartDate=new Date(Date.parse("2016-06-20 00:00:00".replace(/-/g,"/")));
var enddate=EndDate.getFullYear() + '-' + (EndDate.getMonth() + 1) + '-' + EndDate.getDate() + ' ' + EndDate.getHours() + ':' + EndDate.getMinutes() + ':' + EndDate.getSeconds();
var startdate=StartDate.getFullYear() + '-' + (StartDate.getMonth() + 1) + '-' + StartDate.getDate() + ' ' + StartDate.getHours() + ':' + StartDate.getMinutes() + ':' + StartDate.getSeconds();*/
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http,trade) {
	times();
	function times(){
		/*$http.get("http://127.0.0.1:2222/time?access_token=8b543c88-09c2-4c10-9f1f-62a69c8ba7c3").then(function (response){
			$scope.time=response.data;
			
		});*/
		
		$http({
			method:'POST',
			url:'http://127.0.0.1:2222/time',
			data:{access_token:'5346ff68-920f-4f52-9f63-c6f39128ea65'},
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
			transformRequest:function(obj){
				var str=[];
				for(var p in obj){
					str.push(encodeURIComponent(p)+"="+encodeURIComponent(obj[p]));
				}
				return str.join("&");
			}
		}).then(function (response){
			$scope.time=response.data;
	        
		});	
		}
	
	$scope.submit=function(page){
		/*$http.get("http://127.0.0.1:2222/trade?startdate="+$scope.startDate+"&endDate="+$scope.endDate+"&page="+page+"&pagesize=10").then(function (response) {
	        $scope.myWelcome = response.data;
	        $scope.count=response.data[0].size;
	        trade.setCount($scope.count);
	        $scope.counts=parseInt(($scope.count+10-1)/10);
	        $scope.pagecount=[];
	        for(var i=0;i<$scope.counts;i++){
	        	$scope.pagecount[i]=i;
	        }
	    });*/
		$http({
			method:'POST',
			url:'http://127.0.0.1:2222/trade',
			data:{access_token:'720c6d53-14d1-495f-acc5-d9a25def4d2f',startdate:$scope.startDate,endDate:$scope.endDate,page:page,pagesize:10},
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
			transformRequest:function(obj){
				var str=[];
				for(var p in obj){
					str.push(encodeURIComponent(p)+"="+encodeURIComponent(obj[p]));
				}
				return str.join("&");
			}
		}).then(function (response){
			$scope.myWelcome = response.data;
	        $scope.count=response.data[0].size;
	        trade.setCount($scope.count);
	        $scope.counts=parseInt(($scope.count+10-1)/10);
	        $scope.pagecount=[];
	        for(var i=0;i<$scope.counts;i++){
	        	$scope.pagecount[i]=i;
	        }
		});
	}
	
   /*  $scope.count=function(){
    	$scope.myWelcome=$scope.myWelcome+1;
    }; */
	
    $scope.flag=trade.getflag();
    $scope.flags=function(){
    	$scope.flag=trade.setflag($scope.flag);
    	
    };
});
app.factory('trade',function(){
	var service={};
	var flag=true;
	var count;
	service.setflag=function(flag){
		return !flag;
	};
	service.setCount=function(Count){
		count=Count;
	}
	service.getCount=function(){
		return count;
	}
	service.getflag=function(){
		return flag;
	}
	return service;
});

app.directive('draggable', function($document) {
    var startX=0, startY=0,x=0,y=0;
    return function(scope, element, attr) {
    	element.css({
       position: 'relative',
       border: '1px solid red',
       backgroundColor:'white', 
       cursor: 'pointer'
    
      });
      element.bind('mousedown', function(event) {
    	startX = event.screenX - x;
        startY = event.screenY - y;
        $document.bind('mousemove', mousemove);
        $document.bind('mouseup', mouseup);
      });

      function mousemove(event) {
       element.css({
    	   backgroundColor: 'rgba(0,0,0,.7)'
       })
    	y = event.screenY - startY;
        x = event.screenX - startX;
        element.css({
          top: y + 'px',
          left:  x + 'px'
        });
      }

      function mouseup() {
    	  element.css({
    		  backgroundColor:'white'
    			
    	  })
        $document.unbind('mousemove', mousemove);
        $document.unbind('mouseup', mouseup);
        
      }
    }
 });


app.directive('draggable2', function($document) {
    var startX=0, startY=0,x=0,y=0;
    return function(scope, element, attr) {
    	element.css({
       position: 'relative',
       border: '1px solid red',
       backgroundColor:'white', 
       cursor: 'pointer'
    
      });
      element.bind('mousedown', function(event) {
    	startX = event.screenX - x;
        startY = event.screenY - y;
        $document.bind('mousemove', mousemove);
        $document.bind('mouseup', mouseup);
      });

      function mousemove(event) {
       element.css({
    	   backgroundColor: 'rgba(0,0,0,.7)'
       })
    	y = event.screenY - startY;
        x = event.screenX - startX;
        element.css({
          top: y + 'px',
          left:  x + 'px'
        });
      }

      function mouseup() {
    	  element.css({
    		  backgroundColor:'white'
    			
    	  })
        $document.unbind('mousemove', mousemove);
        $document.unbind('mouseup', mouseup);
        
      }
    }
 });