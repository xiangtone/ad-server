	var pageNo = 1;
    var pageCount = 0;
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
	             if (200 == xmlhttp.status) {
	            	 var data = eval("("+xmlhttp.responseText+")");
	            	 if(data.data.adList == undefined){
	            		 document.location="alert://///"+encodeURIComponent("加载成功，但暂无数据...");
	                  	 return;
	                 }
	             	pageCount = data.data.wallPage.pageCount;
	             	var list = data.data.adList;
	             	if(list.length > 0){
	             		for(var i in list){
	             			  var index = (pageNo - 1) * pageSize +parseInt(i);
	 	     	                drawItem(index,list[i]);
	        			}
	             	}
	 				pageNo++;
					 document.location="finish://///";
	             }
	         }
	     };
	     xmlhttp.send("");
	}
	
	function drawItem(index,item){
		
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
	    //将创建的img元素加入boxLDiv中
	    boxLDiv.appendChild(boxLImage);
	    
	    //创建中间的模块
	    var boxMDiv = document.createElement("div");
	    boxMDiv.setAttribute("class", "boxM");
	    boxMDiv.setAttribute("id", "boxM");
	    boxMDiv.innerHTML = "<div id='title"+index+"' class='title'>"+item.title+"</div><div id='desc"+index+"' class='desc'>"+item.general.wall_desc+"</div>";		
	    
	  //创建右边的模块
		var boxRDiv = document.createElement("div");
		boxRDiv.setAttribute("class", "boxR");
		boxRDiv.setAttribute("id", "boxR"+index);
		var downBottom = document.createElement("div");
		downBottom.setAttribute("class","downBottom");
		downBottom.setAttribute("id","downBottom"+index);
		var downword;
		if(item.ad_type == 1){
			downword = "打开详情";
		}else{
			downword = "免费下载";
		}
		downBottom.innerHTML = "<div class='downIconbgroung' id='downIconbgroung"+index+"'></div>" +
				"<div class='downbgroung' id='downbgroung"+index+"'><span id='freeword"+index+"' class='freeword'>"+downword+"</span></div>";
		boxRDiv.appendChild(downBottom);
		boxDiv.addEventListener('click', function() {
//			alert("苹果id"+item.apple_id);
			downloadFile(item.ad_type,item.ad_url,item.app_url,item.id,index,item.apple_id);
		}, false);
		
		//加入到boxLM当中
	    boxLMDiv.appendChild(boxLDiv);
	    //加入到boxLM当中
	    boxLMDiv.appendChild(boxMDiv);
	    //加入到box中
	    boxDiv.appendChild(boxLMDiv);
		boxDiv.appendChild(boxRDiv);
		
		var dividerId = "divider" + index;
		var previousFocus = document.getElementById(dividerId);
//		var boxId =  document.getElementById("box"+index);
		
		var divider = document.createElement("div");
		divider.setAttribute("class","divider");
		var position = parseInt(index)+ parseInt(1);
		divider.setAttribute("id","divider"+position);
		
		insertAfter(boxDiv,previousFocus);
		insertAfter(divider,boxDiv);
//		$(boxDiv).insertAfter(previousFocus);
//	    $(divider).insertAfter(boxId);
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
	
	function downloadFile(adType,adUrl,app_url,id,index,apple_id){
		
		    document.location="objc://///"+"yjfdown"+":////&"+adType+":////&"+adUrl+":////&"+app_url+":////&"+id+":////&"+apple_id;  //cmd代表objective- c中的的方法名，parameter1自然就是参数了 
	
	}
	

	window.onscroll = function(){
		
		if((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {
			if(pageNo <= pageCount){
				 document.location="objc://///"+"requestData"+":////&"+pageNo+":////&"+0+":////&"+0+":////&"+0;  //cmd代表objective- c中的的方法名，parameter1自然就是参数了 
			}
			}
	};
	
	