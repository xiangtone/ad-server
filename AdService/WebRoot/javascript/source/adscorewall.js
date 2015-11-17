	var pageNo = 1;
    var pageCount = 0;
    var pageSize = 10;
    var signPageCount = 0;
    var signPageNo = 1;
    var ids;
    var recoIds;
    var signStatus;
    var currPageType = 0;
    var isBottom = true;
    var perString;
	var isSignData = false; 
	var backReco;
	var xmlhttp;
	
	function requestLogData(id,ids,ac,pageType){
		var params = ""+window.pvparam.pvParam(id,ids,ac,pageType);
		params = params.replace(/\+/g, "%2B");
		params = params.replace(/\&/g, "%26");
		try {
	         xmlhttp = new XMLHttpRequest();
	     } catch (e) {
	         xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	     }
	     xmlhttp.open("post", serverUrl + "android/motion.do?m="+params, true);
	     xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	     xmlhttp.send("");
	}
	function requestData(pageType,isSign,catagory){
		currPageType = catagory;
		var params = "";
		var controller = "";
		if(catagory == 0){
			controller = "android/ad_picker.do";
			params = ""+window.dataparam.wallParam(pageSize,pageNo,pageType,isSign);
		}else if(catagory == 1){
			controller = "android/ad_picker.do";
			params = window.notifyParam.getNotifyParam(pageSize,signPageNo,0,"track");
		}
		params = params.replace(/\+/g, "%2B");
		params = params.replace(/\&/g, "%26");
		try {
	        xmlhttp = new XMLHttpRequest();
	    } catch (e) {
	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	     xmlhttp.open("post", serverUrl + controller + "?m="+params, true);
	     xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	     xmlhttp.onreadystatechange = function() {
	         if (4 == xmlhttp.readyState) {
	             if (200 == xmlhttp.status) {
	            	 var data = eval("("+xmlhttp.responseText+")");
	            	 if(catagory == 0){
	                 	scoreWall(data);
	             	}else if(catagory == 1){
	             		scoreSignWall(data);
	             	}
	             }else{
	            	 window.remove.removeLoadingView();
	             }
	         }
	     };
	     xmlhttp.send("");
	}
	

	function dataUndefined(data){
		if(data.data.adList == undefined){
    		window.remove.removeLoadingView();
        	return;
        }
	}
	
	function scoreSignWall(data){
		dataUndefined(data);
		window.remove.removeLoadingView();
		signPageCount = data.data.wallPage.pageCount;
    	signStatus = data.data.sign.sign_status;
		var list = data.data.adList;
		ids = "";
		if(list.length > 0){
			for(var i in list){
				var index = (signPageNo - 1) * pageSize + parseInt(i);
				drawItem2(index,list[i],1);
				if(i == (list.length - 1)){
		        	ids = ids + list[i].id;
			    }else{
			    	ids = ids + list[i].id + ",";
			    }
			}
		}else{
			if(signPageNo==1){
    			document.getElementById('itemtitle2').style.display = "";
    		}
		}
		signPageNo++;
		if(list.length > 0){
			requestLogData('',ids,6,0);
		}
	}
	function scoreWall(data){
		dataUndefined(data);
		window.remove.removeLoadingView();
		var listheader = document.getElementById("listheader");
		listheader.style.position = "fixed";
    	pageCount = data.data.wallPage.pageCount;
    	signStatus = data.data.sign.sign_status;
    	var list = data.data.adList;
    	ids = "";
    	if(list.length>0){
    		for(var i in list){
    			var index = (pageNo - 1) * pageSize + parseInt(i);
				drawItem(index,list[i],0);
				if(i == (list.length - 1)){
		        	ids = ids + list[i].id;
			    }else{
			    	ids = ids + list[i].id + ",";
			    }
			}
    	}else{
    		if(pageNo==1){
    			document.getElementById('itemtitle1').style.display = "";
    		}
    	}
		pageNo++;
		if(list.length> 0){
			requestLogData('',ids,6,0);
		}
	}
	
	
	
	var unit;
	//积分墙列表
	function drawItem(index,item,catagory){
		if(index == 0){
			var free_bonus = document.getElementById("free_bonus");
			free_bonus.innerHTML = "免费获取"+item.general.scoreUnit;
			unit = item.general.scoreUnit;
		}
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
	    //如果图片不存在则显示默认图片
 	    boxLImage.onerror = function(){
 	    	this.src = "../images/android_defaultlogo.png";
 	    };
	    //将创建的img元素加入boxLDiv中
	    boxLDiv.appendChild(boxLImage);
	    
	    //创建中间的模块
	    var boxMDiv = document.createElement("div");
	    boxMDiv.setAttribute("class", "boxM");
	    boxMDiv.setAttribute("id", "boxM");
    	if(item.isDownload != -1){
    		boxMDiv.innerHTML = "<div id='title"+index+"' class='title'>"+item.title+"</div><div id='desc"+index+"' class='desc'>"+item.general.wall_desc+"</div>";
    	}else{
    		boxMDiv.innerHTML = "<div id='title"+index+"' class='title'>"+item.title+"</div><div id='desc"+index+"' class='desc_sign_sign'>隔日签到，获取额外奖励</div>";
    	}
	    boxLMDiv.addEventListener('click', function() {
	    	gotoDetail(parseInt(item.id),item.ad_type,item.ad_url,item.title,catagory,index);
	    }, false);
	    
	  //创建右边的模块
		var boxRDiv = document.createElement("div");
		boxRDiv.setAttribute("class", "boxR");
		boxRDiv.setAttribute("id", "boxR"+index);
				
		var downloadDiv = document.createElement("div");
		var downTop = document.createElement("div");
		downTop.setAttribute("id","downTop"+index);
		downTop.setAttribute("class","downTop");
		downTop.innerHTML = "<span class='downloadplus'>+</span><span class='downjifen'>"+item.general.score+"</span>";
		downloadDiv.appendChild(downTop);
		var downMiddle = document.createElement("div");
 		downMiddle.setAttribute("class","downMiddle");
 		downMiddle.setAttribute("id","downMiddle"+index);
 		downMiddle.innerHTML = "<div class='downjinbi'></div>";
 		downloadDiv.appendChild(downMiddle);
	
		var downBottom = document.createElement("div");
		downBottom.setAttribute("class","downBottom");
		downBottom.setAttribute("id","downBottom"+index);
		downBottom.innerHTML = "<div class='downbgroung' id='downbgroung"+index+"'></div><div class='downsecond' id='downsecond"+index+"'></div><div class='downfront' id='downfront"+index+"'><span id='freeword"+index+"' class='freeword'></span></div>";
		downloadDiv.appendChild(downBottom);
		boxRDiv.appendChild(downloadDiv);
		downBottom.addEventListener('click', function() {
			downloadFile(item.ad_type,item.ad_url,item.resourceUrl,parseInt(item.id),item.title,item.packageName,item.resourceSize,item.isDownload,index,catagory,item.fileName);
		}, false);
		
		//加入到boxLM当中
	    boxLMDiv.appendChild(boxLDiv);
	    //加入到boxLM当中
	    boxLMDiv.appendChild(boxMDiv);
	    //加入到box中
	    boxDiv.appendChild(boxLMDiv);
		boxDiv.appendChild(boxRDiv);
		
		var dividerUp = document.createElement("div");
		dividerUp.setAttribute("class","divider_up");
		
		var dividerBottom = document.createElement("div");
		dividerBottom.setAttribute("class","divider_bottom");
		
		var position = parseInt(index)+ parseInt(1);
		if(catagory == 0){
			dividerBottom.setAttribute("id","divider"+position);
		}else if(catagory == 1){
			dividerBottom.setAttribute("id","signDivider"+position);
		}
		boxDiv.appendChild(dividerUp);
		boxDiv.appendChild(dividerBottom);
		var wallList = document.getElementById("wallList");
		wallList.appendChild(boxDiv);
	    rightWall(catagory,item.packageName,item.isDownload,item.resourceSize,item.fileName,item.ad_type,index);
	    //将id的值存储在本地，item.id为key，其他值为value
//		storageData(item.id,item.title +","+item.general.wall_icon_Url+ ","+item.ad_type+","+item.ad_url+","+index+","+catagory+","+item.isDownload+","+item.general.wall_desc);
	}
	
	function drawItem2(index,item,catagory){
		var boxDiv = document.createElement("div");
		boxDiv.setAttribute("class","box");
	    boxDiv.setAttribute("id","box2"+index);
		
		var boxLMDiv = document.createElement("div");
		boxLMDiv.setAttribute("class", "boxLM");
		boxLMDiv.setAttribute("id", "boxLM2"+index);
		
		//创建item的图片模块
	    var boxLDiv = document.createElement("div");
	    boxLDiv.setAttribute("class", "boxL");
	    boxLDiv.setAttribute("id", "boxL2");
	    
	    var boxLImage = document.createElement("img");
	    boxLImage.setAttribute("class", "logo");
	    boxLImage.setAttribute("src", item.general.wall_icon_Url);
	    //如果图片不存在则显示默认图片
 	    boxLImage.onerror = function(){
 	    	this.src = "../images/android_defaultlogo.png";
 	    };
	    //将创建的img元素加入boxLDiv中
	    boxLDiv.appendChild(boxLImage);
	    
	    //创建中间的模块
	    var boxMDiv = document.createElement("div");
	    boxMDiv.setAttribute("class", "boxM");
	    boxMDiv.setAttribute("id", "boxM2");

    	if(item.isDownload != -1&&(parseInt(signStatus) + 1) >= item.isDownload){
    		boxMDiv.innerHTML = "<div class='boxm_top'><div id='title2"+index+"' class='title_sign'>"+item.title+"</div></div><div id='descsign2"+index+"' class='desc_sign_sign'>隔日签到，获取额外奖励</div>";		
    	}else{
    		boxMDiv.innerHTML = "<div class='boxm_top'><div id='title2"+index+"' class='title_sign'>"+item.title+"</div><div id='title_jinbi2"+index+"' class='title_jinbi'></div></div><div id='descsign2"+index+"' class='desc_sign'>"+item.general.wall_desc+"</div>";		
    	}
	    
	    boxLMDiv.addEventListener('click', function() {
	    	gotoDetail(parseInt(item.id),item.ad_type,item.ad_url,item.title,catagory,index);
	    }, false);
	    
	  //创建右边的模块
		var boxRDiv = document.createElement("div");
		boxRDiv.setAttribute("class", "boxR");
		boxRDiv.setAttribute("id", "boxR2"+index);
		var downloadDiv = document.createElement("div");
		var downBottom = document.createElement("div");
		downBottom.setAttribute("class","downbottom_sign");
		downBottom.setAttribute("id","downBottom2"+index);
		var downMiddle2 = document.createElement("div");
		downMiddle2.setAttribute("class","downMiddle2");
		downMiddle2.setAttribute("id","downMiddle22"+index);
		downMiddle2.innerHTML = "<div class='downjinbi2'><span class='downloadplus'>+</span><span class='downjinbi2'>"+item.general.score+"</span></div>";
 		downloadDiv.appendChild(downMiddle2);
		downBottom.innerHTML = "<div class='downbgroung' id='downbgroungsign2"+index+"'></div><div class='downsecond' id='downsecondsign2"+index+"'></div><div class='downfront' id='downfront2"+index+"'><span id='freewordsign2"+index+"' class='freeword'></span></div>";
		downloadDiv.appendChild(downBottom);
		boxRDiv.appendChild(downloadDiv);
		downBottom.addEventListener('click', function() {
			downloadFile(item.ad_type,item.ad_url,item.resourceUrl,parseInt(item.id),item.title,item.packageName,item.resourceSize,item.isDownload,index,catagory,item.fileName);
		}, false);
		
		//加入到boxLM当中
	    boxLMDiv.appendChild(boxLDiv);
	    //加入到boxLM当中
	    boxLMDiv.appendChild(boxMDiv);
	    //加入到box中
	    boxDiv.appendChild(boxLMDiv);
		boxDiv.appendChild(boxRDiv);
		
		var dividerUp = document.createElement("div");
		dividerUp.setAttribute("class","divider_up");
		var dividerBottom = document.createElement("div");
		dividerBottom.setAttribute("class","divider_bottom");
		var position = parseInt(index)+ parseInt(1);
		dividerBottom.setAttribute("id","signDivider2"+position);
		boxDiv.appendChild(dividerUp);
		boxDiv.appendChild(dividerBottom);
		var wallList2 = document.getElementById("wallList2");
		wallList2.appendChild(boxDiv);
	    rightWall(catagory,item.packageName,item.isDownload,item.resourceSize,item.fileName,item.ad_type,index);
	    
	}
	
	function rightWall(catagory,packageName,isDownload,resourceSize,fileName,adType,index){
		var downword;
		if(catagory == 0){
			downword = document.getElementById("freeword"+index);
		}else if(catagory == 1){
			downword = document.getElementById("freewordsign2"+index);
		}
		if(catagory == 0){
			if(adType == 1){
				downword.innerHTML = "打开";
			}else{
				if(signStatus > 0){
					if(window.installed.isInstalled(packageName)){
						if(isDownload == -1 || (isDownload != 0 && (parseInt(signStatus) + 1) >= isDownload)){
							downword.innerHTML = "签到";
						}else{
							downword.innerHTML = "打开";
						}
					}else{
						if(isDownload == -1 || (isDownload != 0 && (parseInt(signStatus) + 1) >= isDownload)){
							downword.innerHTML = "签到";
						}else if(isDownload == 0){
							if(window.loaded.isLoaded(resourceSize,fileName)){
								var downsecond = document.getElementById("downsecond"+index);
								if(downsecond)
									downsecond.style.width = "80%";
								downword.innerHTML = "安装";
							}else{
								downword.innerHTML = "免费下载";
							}
						}else{
							downword.innerHTML = "已激活";
						}
					}
				}else{
					if(isDownload == 0){
						if(window.loaded.isLoaded(resourceSize,fileName)){
							var downsecond = document.getElementById("downsecond"+index);
							if(downsecond)
								downsecond.style.width = "80%";
							downword.innerHTML = "安装";
						}else{
							downword.innerHTML = "免费下载";
						}
					}else{
						if(window.installed.isInstalled(packageName)){
							downword.innerHTML = "打开";
						}else{
							downword.innerHTML = "已激活";
							
						}
					}
				}
			}
		}else if(catagory == 1){
			if(adType == 1){
				downword.innerHTML = "打开";
			}else{
				if(signStatus > 0){
					if(window.installed.isInstalled(packageName)){
						if(isDownload == -1 || (isDownload != 0 && (parseInt(signStatus) + 1) >= isDownload)){
							downword.innerHTML = "签到";
						}else{
							var downbgroungsign = document.getElementById("downbgroungsign2"+index);
							if(downbgroungsign)
							downword.innerHTML = "打开";
						}
					}else{
						if(isDownload == -1 || (isDownload != 0 && (parseInt(signStatus) + 1) >= isDownload)){
							downword.innerHTML = "签到";
						}else{
							var downbgroungsign = document.getElementById("downbgroungsign2"+index);
							if(downbgroungsign)
							downword.innerHTML = "打开";
						}
					}
				}
			}
		}
	}
	
	var oldIndex = -1;
	var oldSignedIndex = -1;
	var oldDefaultSignIndex = -1;
	function downloadFile(adType,adUrl,resourceUrl,id,title,packageName,resourceSize,isDownload,index,catagory,fileName){
		if(catagory == 0 || catagory == 1 || catagory == 3){
			if(adType == 1){
				window.location.href = adUrl;
				requestLogData(id,'',5,0);
			}else{
				if(window.installed.isInstalled(packageName)){
					window.open.openApp(packageName);
					if(isDownload != 0 && isDownload != -1 && (parseInt(signStatus) + 1) >= isDownload){
						if(catagory == 0){
							if(oldIndex != index){
								oldIndex = index;
								var freeWord = document.getElementById("freeword"+index);
								if(freeWord){
									freeWord.innerHTML = "签到";
								}
								window.score.addScore(id);
							}
						}else if(catagory == 1){
							if(oldSignedIndex != index){
								oldSignedIndex = index;
								if(isDownload == 2){
									var title_jinbi = document.getElementById("title_jinbi2"+index);
									if(title_jinbi)
										title_jinbi.style.display = 'none';
									var downword = document.getElementById("freewordsign2"+index);
									if(downword){
										downword.innerHTML = "打开";
									}
								}
								window.score.addScore(id);
							}
						}
					}
				}else{
					var pageType = 0 + "," + index + "," + catagory;
					var freeWord;
					if(catagory == 0){
						if(!window.loaded.isLoaded(resourceSize,fileName)){
							freeWord = document.getElementById("freeword"+index);
							if(freeWord)
								freeWord.innerHTML = "下载中";
						}
					}else if(catagory == 1){
						if(!window.loaded.isLoaded(resourceSize,fileName)){
							freeWord = document.getElementById("freewordsign2"+index);
							if(freeWord)
								freeWord.innerHTML = "下载中";
						}
					}
					window.wall.clickOnAndroid(resourceUrl,id,title,packageName,pageType,resourceSize,isDownload,signStatus);
					requestLogData(id,'',5,0);
				}
			}
		}
	}
	
	function gotoDetail(adId,ad_type,ad_url,title,catagory,index){
		if(!window.isNetwork.hasNetwork()){
			winodw.toast.toast("网络异常");
			return;
		}
		if(ad_type == 0){
			var url;
			if(catagory == 0 || catagory == 1 || catagory == 3){
				url = serverUrl + "html/scoredetail.html?adId="+adId+"&signstatus="+signStatus+"&title="+title+"&pagetype=0&position="+index+"&catagory="+catagory;
			}else{
				url = serverUrl + "html/scoredetail.html?adId="+adId+"&signstatus="+signStatus+"&title="+title+"&pagetype=6&position="+index+"&catagory="+catagory;
			}
			window.loading.addLoadingView(3);
			document.body.scrollTop = 0;
		    window.location.href = url;
		}else{
			window.loading.addLoadingView(3);
			document.body.scrollTop = 0;
			window.location.href = adUrl;
		}
	}
	
	window.onscroll = function(){
		if(isBottom){
			if((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {
				if(currPageType == 0){
					if(pageNo <= pageCount){
						window.loading.addLoadingView(0);
						requestData(0,0,0);
					}
				}else if(currPageType == 1){
					if(signPageNo <= signPageCount){
						window.loading.addLoadingView(0);
						requestData(0,1,1);
					}
				}
			}
		}
	};
	
	
	function backDefault(position,catagory){
		if(catagory == 0){
			var pos = document.getElementById("downbgroung"+position);
			if(pos)
			var freeWordm = document.getElementById("freeword"+position);
			if(freeWordm)
				freeWordm.innerHTML = "免费下载";
		}else if(catagory == 1){
			var pos = document.getElementById("downbgroungsign"+position);
			if(pos)
			var freeWordd = document.getElementById("freewordsign"+position);
			if(freeWordd)
				freeWordd.innerHTML = "免费下载";
		}
	}
	
	function updateWord(position,freeword){
		if(freeword)
			freeword.innerHTML = "安装";
	}
	
	
	//刷新应用的状态
	function changeState(index,value,catagory){
		if(catagory == 0){
			var downsecond = document.getElementById("downsecond"+index);
			if(downsecond)
				downsecond.style.width = "0";
			var downbgroung = document.getElementById("downbgroung"+index);
			if(downbgroung)
			var freeWordmt = document.getElementById("freeword"+index);
			if(freeWordmt)
				freeWordmt.innerHTML = "签到";
			var spannum = document.getElementById("num");spannum.style.display = 'none';
			var desc = document.getElementById("desc"+index);
			if(desc){
				desc.innerHTML = "隔日签到，获取额外奖励";
				desc.style.color = "#ec6941";
			}
		}else if(catagory == 1){
			var valuem = getClickedItem(index, catagory);
			if(valuem != '' && valuem.split(",")[0] == 2){
				var title_jinbi = document.getElementById("title_jinbi"+index);
				if(title_jinbi)
					title_jinbi.style.display = 'none';
				var freewordsign = document.getElementById("freewordsign"+index);
				if(freewordsign){
					freewordsign.innerHTML = "打开";
					}
				var desc_sign = document.getElementById("desc_sign"+index);
				if(desc_sign){
					desc_sign.innerHTML = valuem.split(",")[1];
				}
			}
		}
	}
	
	
	function switchChange(isLeft){
		if(isLeft){
			if(!window.isNetwork.hasNetwork()){
				window.toast.toast("网络异常");
				return;
			}
			document.getElementById("wallList").style.display = 'block';
			document.getElementById("wallList2").style.display = 'none';
			document.getElementById("item_bottom_notify").style.display = 'none';
			document.getElementById("item_bottom").style.display = 'block';
			document.getElementById("left").style.color = 'white';
			document.getElementById("right").style.color = '#bfbfbf';
			
			document.getElementById('itemtitle2').style.display = "none";
			isSignData = false;
			isBottom = true;
			currPageType = 0;
			if(pageNo==1){
				requestData(0,0,0);
			}
		}else{
			if(!window.isNetwork.hasNetwork()){
				window.toast.toast("网络异常");
				return;
			}
			document.getElementById("left").style.color = '#bfbfbf';
			document.getElementById("right").style.color = 'white';
			document.getElementById("wallList").style.display = 'none';
			document.getElementById("wallList2").style.display = 'block';
			document.getElementById("item_bottom_notify").style.display = 'block';
			document.getElementById("item_bottom").style.display = 'none';
			document.getElementById('itemtitle1').style.display = "none";
			isSignData = true;
			isBottom = true;
			if(signPageNo==1){
				window.loading.addLoadingView(0);
				requestData(0,1,1);
			}
		}
	}
	
	Date.prototype.format =function(format)
	{
		var o = {
			"M+" : this.getMonth()+1, //month
			"d+" : this.getDate(), //day
			"h+" : this.getHours(), //hour
			"m+" : this.getMinutes(), //minute
			"s+" : this.getSeconds(), //second
			"q+" : Math.floor((this.getMonth()+3)/3), //quarter
			"S" : this.getMilliseconds() //millisecond
			};
			if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
			(this.getFullYear()+"").substr(4- RegExp.$1.length));
			for(var k in o)if(new RegExp("("+ k +")").test(format))
			format = format.replace(RegExp.$1,
			RegExp.$1.length==1? o[k] :
			("00"+ o[k]).substr((""+ o[k]).length));
		return format;
	};
	
	var storage = window.localStorage; 
	
	function getClickedItem(position,catagory){
		var storage = window.sessionStorage;
		var value;
		if(storage){
			for(var j = 0; j < storage.length;j++){
				var keyValue = storage.key(j);
				var posValue = storage.getItem(keyValue).split(",")[4];
				var catagoryValue = storage.getItem(keyValue).split(",")[5];
				if(position == posValue && catagory == catagoryValue){
					value = storage.getItem(keyValue).split(",")[6] + ","+storage.getItem(keyValue).split(",")[7];
					break;
				}
			}
		}
		return value;
	}
	function goBack(){
		window.back.backToActivity();
	}
	function loadData(){
		window.loading.addLoadingView(0);
		document.getElementById("item_bottom_notify").style.display = 'none';
		requestData(0,0,0);
	};