var pageNo = 1;
var pageCount = 0;
var pageSize = 10;
var ids;
var perString;
var xmlhttp;
function requestLogData(id,ids,ac){
	var params = ""+window.pvparam.pvParam(id,ids,ac,"1");
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

function requestData(){
	var params = ""+window.dataparam.wallParam(pageSize,pageNo,1,0);
	params = params.replace(/\+/g, "%2B");
	params = params.replace(/\&/g, "%26");
			  
	 try {
         xmlhttp = new XMLHttpRequest();
     } catch (e) {
         xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
     }
     
     xmlhttp.onreadystatechange = function() {
         if (4 == xmlhttp.readyState) {
             if (200 == xmlhttp.status) {
            	 var data = eval("("+xmlhttp.responseText+")");
            	 recommendWall(data);
             }else{
            	 window.remove.removeLoadingView();
             }
         }
     };
     xmlhttp.open("post", serverUrl + "android/ad_picker.do?m="+params, true);
     xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
     xmlhttp.send("");
}

function dataUndefined(data){
	if(data.data.adList == undefined){
		window.remove.removeLoadingView();
    	return;
    }
}

function recommendWall(data){
	dataUndefined(data);
	pageCount = data.data.wallPage.pageCount;
	var list = data.data.adList;
	ids = "";
	if(list.length > 0){
		for(var i in list){
			var index = (pageNo - 1) * pageSize + parseInt(i);
			drawItem(index,list[i]);
			if(i == (list.length - 1)){
	        	ids = ids + list[i].id;
		    }else{
		    	ids = ids + list[i].id + ",";
		    }
		}
	}else{
//		 var keyVal = data.data.adList;
//    	 if(keyVal==undefined || keyVal=="" || keyVal==null){
//    		
//         }
		if(pageNo==1){
			 document.getElementById('itemtitle1').style.display = "";
		}
		
	}
	
	
	pageNo++;
	window.remove.removeLoadingView();
	requestLogData('',ids,6);
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
    boxMDiv.innerHTML = "<div id='title"+index+"' class='title_reco'>"+item.title+"</div><div id='catagory"+index+"' class='catagory'>"+item.category+" | "+item.general.wall_left_third.substring(3)+"</div><div id='desc"+index+"' class='desc_reco'>"+item.general.wall_desc+"</div>";			
    
    boxLMDiv.addEventListener('click', function() {
    	gotoDetail(parseInt(item.id),item.ad_type,item.ad_url,item.title,index);
    }, false);
    
  //创建右边的模块
	var boxRDiv = document.createElement("div");
	
	boxRDiv.setAttribute("class", "boxR");
	boxRDiv.setAttribute("id", "boxR"+index);
			
	var downloadDiv = document.createElement("div");
	
	var downMiddle = document.createElement("div");
		downMiddle.setAttribute("class","downMiddle");
		downMiddle.setAttribute("id","downMiddle"+index);
		downMiddle.innerHTML = "<div class='downjinbi'></div>";
		downloadDiv.appendChild(downMiddle);
	
	var downBottom = document.createElement("div");
	downBottom.setAttribute("class","downbottom_sign");
	downBottom.setAttribute("id","downBottom"+index);
	var downword;
	if(window.installed.isInstalled(item.packageName)){
		downword = "打开";
	}else if(window.loaded.isLoaded(item.resourceSize,item.fileName)){
		downword = "安装";
	}else{
		downword = "免费下载";
	}
	downBottom.innerHTML = "<div class='downbgroung' id='downbgroung"+index+"'></div><div class='downsecond' id='downsecond"+index+"'></div><div class='downfront' id='downfront"+index+"'><span id='freeword"+index+"' class='freeword'>"+downword+"</span></div>";
	downloadDiv.appendChild(downBottom);
	boxRDiv.appendChild(downloadDiv);
	downBottom.addEventListener('click', function() {
		downloadFile(item.ad_type,item.ad_url,item.resourceUrl,parseInt(item.id),item.title,item.packageName,item.resourceSize,item.isDownload,index,item.fileName);
	}, false);
	
	//加入到boxLM当中
    boxLMDiv.appendChild(boxLDiv);
    //加入到boxLM当中
    boxLMDiv.appendChild(boxMDiv);
    //加入到box中
    boxDiv.appendChild(boxLMDiv);
	boxDiv.appendChild(boxRDiv);
	
	var dividerDiv = document.getElementById("wallList").lastChild;
	var dividerUp = document.createElement("div");
	dividerUp.setAttribute("class","divider_up");
	
	var position = parseInt(index)+ parseInt(1);
	var dividerBottom = document.createElement("div");
	dividerBottom.setAttribute("class","divider_bottom");
	dividerBottom.setAttribute("id","divider"+position);
	insertAfter(boxDiv,dividerDiv);
	insertAfter(dividerUp,boxDiv);
	insertAfter(dividerBottom,dividerUp);
	
	
	if(!window.installed.isInstalled(item.packageName) && window.loaded.isLoaded(item.resourceSize,item.fileName)){
		var downsecond =document.getElementById("downsecond"+index);
		downsecond.style.width = "80%";
	}
    //将id的值存储在本地，item.id为key，其他值为value
	storageData(item.id,item.title +","+item.general.wall_icon_Url+ ","+item.ad_type+","+item.ad_url+","+index+",0");
}

function gotoDetail(adId,ad_type,ad_url,title,index){
	if(ad_type == 0){
		var url = serverUrl + "html/scoredetail.html?adId="+adId+"&signstatus=0&title="+title+"&pagetype=1&position="+index+"&catagory=0";
//		var url = "file:///android_asset/html/scoredetail.html?adId="+adId+"&signstatus=0&title="+title+"&pagetype=1&position="+index+"&catagory=0";
		window.loading.addLoadingView(3);
		document.body.scrollTop = 0;
	    window.location.href = url;
	}else{
		window.loading.addLoadingView(3);
		document.body.scrollTop = 0;
		window.location.href = adUrl;
	}
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


function downloadFile(adType,adUrl,resourceUrl,id,title,packageName,resourceSize,isDownload,index,fileName){
	var pageType = 1 + "," + index + "," + "0";
	if(adType == 1){
		window.location.href = adUrl;
		requestLogData(id,'', 5, 0);
	}else{
		if(window.installed.isInstalled(packageName)){
			window.open.openApp(packageName);
		}else{
			var freeWord = document.getElementById("freeword"+index);
			if(!window.loaded.isLoaded(resourceSize,fileName)){
				freeWord.innerHTML = "下载中";
//				var posground = document.getElementById("downbgroung"+index);
//				posground.style.backgroundColor = "#313131";
			}
			window.wall.clickOnAndroid(resourceUrl,id,title,packageName,pageType,resourceSize,isDownload,0);
			if(!window.loaded.isLoaded(resourceSize,fileName)){
				requestLogData(id,'', 5, 0);
			}
		}
	}
}

window.onscroll = function(){
	if((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {
		if(pageNo <= pageCount){
			window.loading.addLoadingView(0);
			requestData();
		}
	}
};


function goBack(){
	window.back.backToActivity();
}


function updateWord(position,freeword){
	if(freeword)
		freeword.innerHTML = "安装";
}

function updateProgress(percent,position,catagory){
	var freeword = document.getElementById("freeword"+position);
	var perWidth = percent * 80;
//	var downbgroung = document.getElementById("downbgroung"+position);
//	downbgroung.style.backgroundColor = "#313131";
	var downsecond = document.getElementById("downsecond"+position);
	downsecond.style.width = perWidth+"%";
	var perNumber = percent * 100;
	if(perNumber < 10){
		perString = perNumber.toString().substring(0,1);
	}else if(perNumber >= 10 && perNumber < 100){
		perString = perNumber.toString().substring(0,2);
	}else if(perNumber >= 100){
		perString = perNumber.toString().substring(0,3);
	}
	if(freeword)
		freeword.innerHTML = perString+"%";
	if(percent >= 1.0){
		updateWord(position,freeword);
	}
}
function changeState(index,value,catagory){
	var downsecond = document.getElementById("downsecond"+index);
	downsecond.style.width = "0";
//	var downbgroung = document.getElementById("downbgroung"+index);
//	downbgroung.style.backgroundColor = "#3489e4";
	var freeWord = document.getElementById("freeword"+index);
	if(freeWord)
		freeWord.innerHTML = "打开";
}
function backDefault(position,catagory){
//	var downbgroung = document.getElementById("downbgroung"+position);
//	downbgroung.style.backgroundColor = "#3489e4";
	var freeWord = document.getElementById("freeword"+position);
	if(freeWord)
		freeWord.innerHTML = "免费下载";
}


function loadData(){
	window.loading.addLoadingView(0);
	requestData();
}