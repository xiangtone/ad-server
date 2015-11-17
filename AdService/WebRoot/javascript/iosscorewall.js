    var pageNo = 1;
    var pageCount = 0;
   	
    var TpageSize = 10;
	var recPageNo = 1;
    var recPageCount = 0;
   
   	var trackPageNo = 1;
    var trackPageCount = 0;
    
	var scoreTag=0;
	//0积分墙,1,我的足迹,2热门推荐，3通知中心
    var isBottom = 0;
	
    var xmlhttp;
	function requestData(pageType,pageSize,params){
		document.location="start://///";
 		serverUrl= "/AdService/";
		params = params.replace(/\+/g, "%2B");
		params = params.replace(/\&/g, "%26");
		 try {
		        xmlhttp = new XMLHttpRequest();
		    } catch (e) {
		        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		    }
	     xmlhttp.open("post", serverUrl + "ios/ad_picker.do" + "?map="+params, true);
	     xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	     
	     xmlhttp.onreadystatechange = function() {
	         if (4 == xmlhttp.readyState) {
	             if (200 == xmlhttp.status){
	            	 var data = eval("("+xmlhttp.responseText+")");
	            	 if(data.data.adList == undefined){
	            		 document.location="alert://///"+encodeURIComponent("加载成功，但暂无数据...");
	                 	return;
	                 }
	            	 scoreWall(data,pageSize);
					 document.location="finish://///";
	             }else{
	            	 document.location="alert://///"+encodeURIComponent("积分墙请求错误");
	             }
	         }
	     };
	     xmlhttp.send("");
	     
	}
	
	function dataUndefined(data){
		if(data.data.adList == undefined){
        	return;
        }
	}
	
	function scoreWall(data,pageSize){
		dataUndefined(data);
    	pageCount = data.data.wallPage.pageCount;
    	var list = data.data.adList;
    	scoreTag=data.data.totalInteger;
//		numShowFun();
    	if(list.length > 0){
			for(var i in list){
					var index = (pageNo - 1) * pageSize + parseInt(i);
           			drawItem(index,list[i],0);
				}
    	}
		pageNo++;
	}
	
	//catagory 0 积分墙     1我的足迹    2热门推荐  3通知中心默认页中我的足迹   4通知中心默认页中热门推荐
	function drawItem(index,item,catagory){
		
		var boxDiv = document.createElement("div");
		boxDiv.setAttribute("class","box");
	    boxDiv.setAttribute("id","box"+index);
		
		var boxLMDiv = document.createElement("div");
		boxLMDiv.setAttribute("class", "boxLM");
		boxLMDiv.setAttribute("id", "boxLM"+index);
		
		//创建item的图片模块
	    var boxLDiv = document.createElement("div");
	    boxLDiv.setAttribute("class", "boxL");
	    boxLDiv.setAttribute("id", "boxL");
	  
	    var boxLImage = document.createElement("img");
	    boxLImage.setAttribute("class", "logo");
	    boxLImage.setAttribute("src", item.general.wall_icon_Url);
		
	    boxLDiv.appendChild(boxLImage);
	    //创建中间的模块
	    var boxMDiv = document.createElement("div");
	    boxMDiv.setAttribute("class", "boxM");
	    boxMDiv.setAttribute("id", "boxM");
		
	    if(catagory == 0){
	    	boxMDiv.innerHTML = "<div id='title"+index+"' class='title'>"+item.title+"</div><div id='catagory"+index+"' class='catagory'>"+item.general.wall_desc+"</div><div id='desc"+index+"' class='desc'>"+item.score_msg+"</div>";
	    }else if(catagory == 1 || catagory == 3){
    		boxMDiv.innerHTML = "<div class='boxm_top'><div id='title"+index+"' class='title_sign'>"+item.title+"</div><div id='title_jinbi"+index+"' class='title_jinbi'><div><span>"
			+item.general.score+"</span><span>"+item.general.scoreUnit+"</span></div><div id='desc"+index+"' class='desc_sign'>"+item.create_time+"</div></div></div>";		
    	}else if(catagory == 2 || catagory == 4){
	    	boxMDiv.innerHTML = "<div id='title"+index+"' class='title_reco'>"+item.title+"</div><div id='desc"+index+"' class='desc_reco'>"+item.general.wall_desc+"</div>";			
	    }
	    
	  //创建右边的模块
		var boxRDiv = document.createElement("div");
		
		boxRDiv.setAttribute("class", "boxR");
		boxRDiv.setAttribute("id", "boxR"+index);
		
		var downloadDiv = document.createElement("div");

		var downTop = document.createElement("div");
		downTop.setAttribute("id","downTop"+index);
		downTop.setAttribute("class","downTop");
		downTop.innerHTML = "<span class='downloadplus'></span><span class='downjifen'>+"+item.general.score+"</span>";
		
		var downMiddle = document.createElement("div");
		downMiddle.setAttribute("class","downMiddle");
		downMiddle.setAttribute("id","downMiddle"+index);
		downMiddle.innerHTML = "<span class='downjinbi'>"+item.general.scoreUnit+"</span>";
		
		var downBottom = document.createElement("div");
		 if(catagory == 0){
			downBottom.setAttribute("class","downBottom");
			downBottom.setAttribute("id","downBottom"+index);
			var downword;
			if(item.ad_type == 1){
				downword = "";
			}else{
				downword = "";
			}
			downBottom.innerHTML = "<div class='downsecond' id='downsecond"+index+"'></div><div class='downbgroung' id='downbgroung"+index+"'><span id='freeword"+index+"' class='freeword'>"+downword+"</span></div>";
			
	    	downloadDiv.appendChild(downTop);
			downloadDiv.appendChild(downMiddle);
			downloadDiv.appendChild(downBottom);
	    }else if(catagory == 1 || catagory == 3){
				downBottom.setAttribute("class","downBottom1");
				downBottom.setAttribute("id","downBottom1"+index);
				var downword;
				if(item.ad_type == 1){
					downword = "已完成";
				}else{
					downword = "已完成";
				}
				downBottom.innerHTML = "<div class='downsecond1' id='downsecond1"+index+"'></div><div class='downbgroung1' id='downbgroung1"+index+"'><span id='freeword1"+index+"' class='freeword1'>"+downword+"</span><img class='freewordimg' src='../images/yjfok.png'></img></div>";
				downloadDiv.appendChild(downBottom);
    	}else if(catagory == 2 || catagory == 4){
				downBottom.setAttribute("class","downBottom2");
				downBottom.setAttribute("id","downBottom2"+index);
				var downword;
				if(item.ad_type == 1){
					downword = "免费下载";
				}else{
					downword = "免费下载";
				}
				downBottom.innerHTML = "<div class='downsecond' id='downsecond"+index+"'></div><div class='downbgroung' id='downbgroung"+index+"'><span id='freeword"+index+"' class='freeword'>"+downword+"</span></div>";
				downloadDiv.appendChild(downBottom);
	    }
		 
		boxRDiv.appendChild(downloadDiv);
		boxDiv.addEventListener('click', function() {
			if(catagory == 2 || catagory == 4){//热门推荐
				downloadFile(item.ad_type,item.ad_url,item.app_url,item.id,index,item.apple_id,"6");
			}else if(catagory == 0){//积分墙
				downloadFile(item.ad_type,item.ad_url,item.app_url,item.id,index,item.apple_id,"0");
			}
			
		}, false);
		
		//加入到boxLM当中
	    boxLMDiv.appendChild(boxLDiv);
	    //加入到boxLM当中
	    boxLMDiv.appendChild(boxMDiv);
	    //加入到box中
	    boxDiv.appendChild(boxLMDiv);
		boxDiv.appendChild(boxRDiv);
		
		var dividerId = null;
		if(catagory == 0){
			dividerId = document.getElementById("divider" + index);
		}else if(catagory == 1){
			dividerId = document.getElementById("signDivider" + index);
		}else if(catagory == 2){
			dividerId = document.getElementById("hotDivider" + index);
		}else if(catagory == 3){
			dividerId = document.getElementById("signContentDivider" + index);
		}else if(catagory == 4){
			dividerId = document.getElementById("hotContentDivider" + index); 
		}
		
		var dividerUp = document.createElement("div");
		dividerUp.setAttribute("class","divider_up");
		var dividerBottom = document.createElement("div");
		dividerBottom.setAttribute("class","divider_bottom");
		var position = parseInt(index)+ parseInt(1);
		if(catagory == 0){
			dividerBottom.setAttribute("id","divider"+position);
		}else if(catagory == 1){
			dividerBottom.setAttribute("id","signDivider"+position);
		}else if(catagory == 2){
			dividerBottom.setAttribute("id","hotDivider"+position);
		}else if(catagory == 3){
			dividerBottom.setAttribute("id","signContentDivider"+position);
		}else if(catagory == 4){
			dividerBottom.setAttribute("id","hotContentDivider"+position);
		}
		
		insertAfter(boxDiv,dividerId);
		insertAfter(dividerUp,boxDiv);
		insertAfter(dividerBottom,dividerUp);
//		$(boxDiv).insertAfter(dividerId);
//	    $(dividerUp).insertAfter(boxDiv);
//	    $(dividerBottom).insertAfter(dividerUp);
	    //将id的值存储在本地，item.id为key，其他值为value
//		storageData(item.id,item.title +","+item.general.wall_icon_Url+ ","+item.ad_type+","+item.ad_url+","+index);
	}
	
		
	function insertAfter(newElement, targetElement){
		var parent = targetElement.parentNode;
		if (parent.lastChild == targetElement) {
			// 如果最后的节点是目标元素，则直接添加。因为默认是最后
			parent.appendChild(newElement);
		}else {
			parent.insertBefore(newElement, targetElement.nextSibling);
			//如果不是，则插入在目标元素的下一个兄弟节点 的前面。也就是目标元素的后面
		}
	}
	
	function downloadFile(adType,adUrl,app_url,id,index,apple_id,pageType){
		    document.location="objc://///"+"yjfdown"+":////&"+adType+":////&"+adUrl+":////&"+app_url+":////&"+id+":////&"+apple_id+":////&"+pageType;  //cmd代表objective- c中的的方法名，parameter1自然就是参数了 
		}
	
	window.onscroll = function(){
		if((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {
			if(isBottom==0){
				if(pageNo <= pageCount){
						document.location="objc://///"+"requestData"+":////&"+pageNo+":////&"+0+":////&"+0+":////&"+0;  //cmd代表objective- c中的的方法名，parameter1自然就是参数了 
					}
			}else if(isBottom==1)
			{	
				if(trackPageNo <=trackPageCount){
						 document.location="objc://///"+"requestNotifyData"+":////&"+trackPageNo+":////&"+"track"+":////&"+0+":////&"+0; 
					}
				
			}else if(isBottom==2)
			{	
				if(recPageNo <=recPageCount){
					document.location="objc://///"+"requestNotifyData"+":////&"+recPageNo+":////&"+"hot"+":////&"+0+":////&"+0; 
					}
				
			}
				
		}
		
	};
	
//	function switchChange(isLeft){
//		document.getElementById("notify_titlebar").style.display = "none";
//		document.getElementById("yjf_hint").style.display = "none";
//		if(isLeft){
//			document.getElementById("left").style.background="#007aff";
//			document.getElementById("left").style.color="white";
//			document.getElementById("right").style.background="transparent";
//			document.getElementById("right").style.color="#007aff";
//			isBottom = 0;
//			document.getElementById("notify_center").style.display = "none";
//			document.getElementById("wall_list").style.display = "";
//		}else{
//			isBottom = 3;
//			document.getElementById("news").style.display="none";
//			document.getElementById("left").style.background="transparent";
//			document.getElementById("left").style.color="#007aff";
//			document.getElementById("right").style.background="#007aff";
//			document.getElementById("right").style.color="white";
//		
//			document.getElementById("notify_center").style.display = "";
//			document.getElementById("wall_list").style.display = "none";
//			
//			document.getElementById("notify_zuji").style.display = "none";
//			document.getElementById("notify_tuijian").style.display = "none";
//			document.getElementById("notify_baodian").style.display = "none";
//			document.getElementById("notify_fankui").style.display = "none";
//			document.getElementById("notify_content").style.display = "";
//			requestNotifyLocal(0,"");
//		}
//	}
	
	function createDivider(id,target){
		var divider = document.createElement("div");
		divider.setAttribute("id",id);
		divider.innerHTML = "<div class='divider_up'></div><div class='divider_bottom'></div>";
		var source = document.getElementById(target);
		source.appendChild(divider);
	}
	
//	
//	
//	var storage = window.localStorage; 
//	
//	function saveValue(key,score){
//		if (storage) { 
//            storage.setItem(key, score);   
//        }else{
//        	return false;
//        } 
//	}
	
	
	
