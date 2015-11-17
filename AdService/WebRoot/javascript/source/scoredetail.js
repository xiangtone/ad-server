var signstatus;
var paramId;
var isPop = false;
var pagetype;
var catagoryWall;
var positionWall;
var isSigned = false;
var perString;
var xmlhttp;
function senfe(){
	var s = 1.2;
    var s2 = 8;
	//获取展开/关闭按钮以方便改变
    var extension = document.getElementById("extendImage");
	//获取段落文字的div，以便通过改变告诉来实现展开/收缩效果
	var obj = document.getElementById("bbb");
	//offsetHeight 表示div当前的高度
	var oh = parseInt(obj.offsetHeight);
	//scrollHeight 表示div实际的高度，即所有内容显示完整的高度
	var h = parseInt(obj.scrollHeight);
	var nh = oh;   
	if(obj.getAttribute("oldHeight") == null){
		obj.setAttribute("oldHeight", oh);
	}
	//ceil()：将小数部分一律向整数部分进位
	var oldh = Math.ceil(obj.getAttribute("oldHeight"));
	var reSet = function(){
	//oh div当前高度  h div实际的高度 两者比较，如果oh<h则展开，否则收缩
	if (oh < h) {
		extension.src = "../images/android_shouqi.png";
		//nh = oh
		if(nh < h){
			//计算div当前高度
			nh = Math.ceil(h-(h-nh)/s);
			obj.style.height = nh+"px";
		}else{ 
			window.clearInterval(IntervalId);
		}
	} else {
		extension.src = "../images/android_zhankai.png";
		if(nh > oldh){
			var nhh = Math.ceil((nh-oldh)/s2);
			nh = nh-nhh;
			obj.style.height = nh+"px";
		}else{
			window.clearInterval(IntervalId);
		}
	}
  }
  var IntervalId = window.setInterval(reSet,10);
}

function requestLogData(id,ids,ac){
	var params = ""+window.pvparam.pvParam(id,ids,ac,pagetype);
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

function requestNextDetail(){
	document.body.scrollTop = 0;
	var adIdInput = document.getElementById("adId");
	var adId = document.getElementById("adId").value;
	var nextId = 0;
	var nextIndex = 0;
	var minIndex = 99999;
	var storage = window.sessionStorage;
	if(storage){
		 for(var i=0;i<storage.length;i++){
			  //key(i)获得相应的键，再用getItem()方法获得对应的值
			  var key = storage.key(i);
			  if(key == adId){
				  nextIndex = storage.getItem(key).split(",")[4];
				  nextIndex = parseInt(nextIndex) + 1;
				  break;
			  }
		}
		var positionNext;
		var catagoryNext;
		for(var j = 0; j < storage.length;j++){
			var keyValue = storage.key(j);
			var nextValue = storage.getItem(keyValue).split(",")[4];
			var nextCatagory = storage.getItem(keyValue).split(",")[5];
			if(nextValue < minIndex){
				minIndex = nextValue;
				nextId = keyValue;
				adIdInput.value = nextId;
			}
			if(nextValue == nextIndex && catagoryWall == nextCatagory){
				nextId = keyValue;
				adIdInput.value = nextId;
				positionNext = storage.getItem(nextId).split(",")[4];
				catagoryNext = storage.getItem(nextId).split(",")[5];
				requestData(nextId,positionNext,catagoryNext);
				break;
			}
		}
	}else{
		window.toast.toast("不支持本地存储");
	}
}

function requestData(adId,position,catagory){
	var toptitle = document.getElementById("toptitle");
	toptitle.style.position = "absolute";
	var curAdId = document.getElementById("adId");
	curAdId.value = adId;
	var params = ""+window.adParam.getAdParam(adId,pagetype);
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
            	 initData(adId,data,position,catagory);
            	 window.remove.removeLoadingView();
             }else{
            	 window.remove.removeLoadingView();
             }
         }
     }
     xmlhttp.open("post", serverUrl + "android/ad_detail.do?m="+params, true);
     xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
     xmlhttp.send("");
}

function initData(adId,data,position,catagory){
	positionWall = position;
	deleteDiv("bottomId");
	deleteDiv("bottomId2");
	createBottomDiv(document.getElementById("detailjifen"),"bottomId",position);
	createBottomDiv(document.getElementById("othercontent"),"bottomId2",position);
	if(window.orientation == 0 || window.orientation == 180){
		document.getElementById("blank").style.display='block';
		document.getElementById("bottomId").style.display='block';
		document.getElementById("bottomId2").style.display='none';
	}else if(window.orientation == 90 || window.orientation == -90){
		// Landscape
		document.getElementById("blank").style.display='none';
		document.getElementById("bottomId").style.display='none';
		document.getElementById("bottomId2").style.display='block';
	}
	var logo = document.getElementById("logo");
	logo.onerror = function(){
    	this.src = "../images/android_defaultlogo.png";
	};
	var title = document.getElementById("title");
	var catagoryTitle = document.getElementById("catagory");
	var update_version = document.getElementById("update_version");
	var softInfo = document.getElementById("bbb");
	var icon1 = document.getElementById("icon1");
	var icon2 = document.getElementById("icon2");
	var icon3 = document.getElementById("icon3");
	
	var topTitle = document.getElementById("wordtitle");
	topTitle.innerHTML = data.data.adDetail.detail_first;
	var toptitle = document.getElementById("toptitle");
	toptitle.style.position = "fixed";
	var introduce = document.getElementById("detailintroduce");
	if(pagetype == 1 || pagetype == 6){
		introduce.style.display = 'none';
	}else{
		introduce.innerHTML = data.data.adDetail.score_msg;
	}
	logo.src = data.data.adDetail.detail_icon_Url;
	title.innerHTML = data.data.adDetail.detail_first;
	catagoryTitle.innerHTML = data.data.adDetail.category_name.substring(3) + "|" +data.data.adDetail.detail_fourth.substring(3);
	update_version.innerHTML = data.data.adDetail.detail_second;
	var pos = data.data.adDetail.resourceUrl.lastIndexOf('/');
	var fileName = data.data.adDetail.resourceUrl.substring(pos+1);
	isInstalled(data.data.adDetail.resourceSize,fileName,data.data.adDetail.isDownload,data.data.adDetail.packageName,position,catagory);
	var bigDownload = document.getElementById("bigDownload"+position);
	if(bigDownload){
		bigDownload.addEventListener("click", function(){downloadApp(adId,data,"downbgroung",position,catagory,fileName);}, false);
	}
	
	var bigDownload2 = document.getElementById("bigDownloadland"+position);
	if(bigDownload2){
		bigDownload2.addEventListener("click", function(){downloadApp(adId,data,"downbgroungland",position,catagory,fileName);}, false);
	}
	softInfo.innerHTML = data.data.adDetail.detail_seventh;
	var extension = document.getElementById("extendImage");
	var obj = document.getElementById("bbb");
	extension.src = "../images/android_zhankai.png";
	obj.style.height = "75px";
	if(data.data.adDetail.adDetailPicture.length > 0){
		icon1.src = data.data.adDetail.adDetailPicture[0].detail_picture_Url;
		icon2.src = data.data.adDetail.adDetailPicture[1].detail_picture_Url;
		icon3.src = data.data.adDetail.adDetailPicture[2].detail_picture_Url;
		var item1 = document.getElementById("item1");
		if(item1){
			EventUtil.removeEvent(item1,"click",function(){showBigImage(data.data.adDetail.adDetailPicture[0].detail_picture_Url);});
			EventUtil.addEvent(item1,"click",function(){showBigImage(data.data.adDetail.adDetailPicture[0].detail_picture_Url);});
		}
		
		var item2 = document.getElementById("item2");
		if(item2){
			EventUtil.removeEvent(item2,"click",function(){showBigImage(data.data.adDetail.adDetailPicture[1].detail_picture_Url);});
			EventUtil.addEvent(item2,"click",function(){showBigImage(data.data.adDetail.adDetailPicture[1].detail_picture_Url);});
		}
		var item3 = document.getElementById("item3");
		if(item3){
			EventUtil.removeEvent(item3,"click",function(){showBigImage(data.data.adDetail.adDetailPicture[2].detail_picture_Url);});
			EventUtil.addEvent(item3,"click",function(){showBigImage(data.data.adDetail.adDetailPicture[2].detail_picture_Url);});
		}
	}
	getFourItem(adId,catagory);
}

function showBigImage(imageUrl){
	document.body.scrollTop = 0;
	var largeImage = document.getElementById("largeImage");
	largeImage.src = imageUrl;
	setTimeout(function(){
		 var bigImageDiv = document.getElementById("big_image");
		 bigImageDiv.style.display = 'inline';
		 isPop = true;
	},1000);
}

function dismiss(event){
	var bigImageDiv = document.getElementById("big_image");
	if(bigImageDiv.style.display == 'inline'){
		bigImageDiv.style.display = 'none';
		isPop = false;
	}
	
}
function downloadApp(adId,data,value,position,catagory,fileName){
	if(window.installed.isInstalled(data.data.adDetail.packageName)){
		window.open.openApp(data.data.adDetail.packageName);
		if(isDownload != 0 && isDownload != -1 && (parseInt(signstatus) + 1) >= isDownload && !isSigned){
			isSigned = true;
			window.score.addScore(adId);
		}
   }else{
	 var isDownload = data.data.adDetail.isDownload;
	 var posground = document.getElementById(value+position);
	 posground.style.backgroundColor = "#313131";
	 window.wall.clickOnAndroid(data.data.adDetail.resourceUrl,adId,data.data.adDetail.detail_first,data.data.adDetail.packageName,pagetype + ","+position+ ","+catagory,data.data.adDetail.resourceSize,isDownload,signstatus);
	 if(window.loaded.isLoaded(data.data.adDetail.resourceSize,fileName)){
		 requestLogData(adId,'',5,pagetype);
	 }
  }
}
function getFourItem(adId,catagory){
	var storage = window.sessionStorage;
	var otherimage1 = document.getElementById("otherimage1");
	var otherword1 = document.getElementById("otherword1");
	
	var otherimage2 = document.getElementById("otherimage2");
	var otherword2 = document.getElementById("otherword2");
	
	var otherimage3 = document.getElementById("otherimage3");
	var otherword3 = document.getElementById("otherword3");
	
	var otherimage4 = document.getElementById("otherimage4");
	var otherword4 = document.getElementById("otherword4");
	
	otherword1.innerHTML = '';
	otherword2.innerHTML = '';
	otherword3.innerHTML = '';
	otherword4.innerHTML = '';
	if(storage){
		 for(var i=0;i<storage.length;i++){
			  //key(i)获得相应的键，再用getItem()方法获得对应的值
			  var key = storage.key(i);
			  var catagoryItem = storage.getItem(key).split(",")[5];
			  if(otherword4.innerHTML != ''){
				  break;
			  }
			  if(key != adId && !isNaN(key) && catagoryItem == catagory){
				  var item = storage.getItem(key);
				  var array = item.split(",");
				  if(otherword1.innerHTML == ''){
					  var ad_id1 = key;
					  otherimage1.src = array[1];
					  otherimage1.onerror = function(){
					    	this.src = "../images/android_defaultlogo.png";
						};
					  otherword1.innerHTML = array[0];
					  var otherfirst = document.getElementById("otherfirst");
					  if(otherfirst){
						  EventUtil.removeEvent(otherfirst,"click",function(){gotoDetail(ad_id1, array[2], array[3],array[0],array[4],array[5]);});
						  EventUtil.addEvent(otherfirst,"click",function(){gotoDetail(ad_id1, array[2], array[3],array[0],array[4],array[5]);});
					  }
				  }else if(otherword2.innerHTML == ''){
					  var ad_id2 = key;
					  otherimage2.src = array[1];
					  otherimage2.onerror = function(){
					    	this.src = "../images/android_defaultlogo.png";
						};
					  otherword2.innerHTML = array[0];
					  var othersecond = document.getElementById("othersecond");
					  if(othersecond){
						  EventUtil.removeEvent(othersecond,"click",function(){gotoDetail(ad_id2, array[2], array[3],array[0],array[4],array[5]);});
						  EventUtil.addEvent(othersecond,"click",function(){gotoDetail(ad_id2, array[2], array[3],array[0],array[4],array[5]);});
					  }
				  }else if(otherword3.innerHTML == ''){
					  var ad_id3 = key;
					  otherimage3.src = array[1];
					  otherimage3.onerror = function(){
					    	this.src = "../images/android_defaultlogo.png";
						};
					  otherword3.innerHTML = array[0];
					  var otherthird = document.getElementById("otherthird");
					  if(otherthird){
						  EventUtil.removeEvent(otherthird,"click",function(){gotoDetail(ad_id3, array[2], array[3],array[0],array[4],array[5]);});
						  EventUtil.addEvent(otherthird,"click",function(){gotoDetail(ad_id3, array[2], array[3],array[0],array[4],array[5]);});
					  }
				  }else if(otherword4.innerHTML == ''){
					  var ad_id4 = key;
					  otherimage4.src = array[1];
					  otherimage4.onerror = function(){
					    	this.src = "../images/android_defaultlogo.png";
						};
					  otherword4.innerHTML = array[0];
					  var otherfouth = document.getElementById("otherfouth");
					  if(otherfouth){
						  EventUtil.removeEvent(otherfouth,"click",function(){gotoDetail(ad_id4, array[2], array[3],array[0],array[4],array[5]);});
						  EventUtil.addEvent(otherfouth,"click",function(){gotoDetail(ad_id4, array[2], array[3],array[0],array[4],array[5]);});
					  }
				  }
			  }else{
				  continue;
			  }
		}
		 var otherfirst = document.getElementById("otherfirst");
		 var othersecond = document.getElementById("othersecond");
		 var otherthird = document.getElementById("otherthird");
		 var otherfouth = document.getElementById("otherfouth");
		 if(otherword1.innerHTML == ''){
			 otherfirst.style.display = 'none';
		 }else{
			 otherfirst.style.display = '';
		 }
		 if(otherword2.innerHTML == ''){
			 othersecond.style.display = 'none';
		 }else{
			 othersecond.style.display = '';
		 }
		 
		 if(otherword3.innerHTML == ''){
			 otherthird.style.display = 'none';
		 }else{
			 otherthird.style.display = '';
		 }
		 
		 if(otherword4.innerHTML == ''){
			 otherfouth.style.display = 'none';
		 }else{
			 otherfouth.style.display = '';
		 }
	}else{
		window.toast.toast("这个浏览器不支持本地存储");
	}
}

function gotoDetail(adId,adType,adUrl,name,position,catagory){
	if(adType == 0){
		document.body.scrollTop = 0;
		requestData(adId,position,catagory);
	}else{
		var title = document.getElementById("wordtitle");
		title.innerHTML = name;
		document.body.scrollTop = 0;
	    window.location.href = adUrl;
	}
}
function goBack(){
	if(isPop){
		document.getElementById("big_image").style.display='none';
		isPop = false;
	}else{
		history.go(-1);
	}
}


function getParamters(){
	var params = window.location.search;
	var param = params.indexOf('?');
	if (param == -1)
		  return "";
	params = params.substring(param + 1);
	var array = new Array();
	array = params.split("&");
	paramId = array[0].substring(5);
	signstatus = array[1].substring(array[1].length - 1);
	var title = document.getElementById("wordtitle");
	title.innerHTML = decodeURI(array[2]).substring(6);
	pagetype = array[3].substring(array[3].length - 1);
	positionWall = array[4].substring(9);
	catagoryWall = array[5].substring(9);
}
function loadData(){
	getParamters();
	requestData(paramId,positionWall,catagoryWall);
	window.remove.removeLoadingView();
};
function isInstalled(resourceSize,fileName,isDownload,packageName,position,catagory){
	var downloadword = document.getElementById("downloadword"+position);
	var downloadword2 = document.getElementById("downloadwordland"+position);
	if(pagetype == 0){
		if(signstatus > 0){
			if(window.installed.isInstalled(packageName)){
				if(isDownload == -1 || (isDownload != 0 && (parseInt(signstatus) + 1) >= isDownload)){
					downloadword.innerHTML = "签到";
					downloadword2.innerHTML = "签到";
				}else{
					downloadword.innerHTML = "打开";
					downloadword2.innerHTML = "打开";
				}
			}else{
				if(isDownload == -1 || (isDownload != 0 && (parseInt(signstatus) + 1) >= isDownload)){
					downloadword.innerHTML = "签到";
					downloadword2.innerHTML = "签到";
				}else if(isDownload == 0){
					if(window.loaded.isLoaded(resourceSize,fileName)){
						var pos = document.getElementById("downsecond"+position);
						var pos2 = document.getElementById("downsecondland"+position);
						if(pos)
							pos.style.width = "50%";
						if(pos2)
							pos2.style.width = "50%";
						downloadword.innerHTML = "安装";
						downloadword2.innerHTML = "安装";
					}else{
						downloadword.innerHTML = "免费下载";
						downloadword2.innerHTML = "免费下载";
					}
				}else{
					downloadword.innerHTML = "打开";
					downloadword2.innerHTML = "打开";
				}
			}
		}else{
			if(isDownload == 0){
				if(window.loaded.isLoaded(resourceSize,fileName)){
					var pos = document.getElementById("downsecond"+position);
					var pos2 = document.getElementById("downsecondland"+position);
					if(pos)
						pos.style.width = "50%";
					if(pos2)
						pos2.style.width = "50%";
					downloadword.innerHTML = "安装";
					downloadword2.innerHTML = "安装";
				}else{
					downloadword.innerHTML = "免费下载";
					downloadword2.innerHTML = "免费下载";
				}
			}else{
				if(window.installed.isInstalled(packageName)){
					downloadword.innerHTML = "打开";
					downloadword2.innerHTML = "打开";
				}else{
					downloadword.innerHTML = "签到";
					downloadword2.innerHTML = "签到";
				}	
			}
		}
	}else if(pagetype == 1 || pagetype == 6){
		if(window.installed.isInstalled(packageName)){
			downloadword.innerHTML = "打开";
			downloadword2.innerHTML = "打开";
		}else if(window.loaded.isLoaded(resourceSize,fileName)){
			var pos = document.getElementById("downsecond"+position);
			var pos2 = document.getElementById("downsecondland"+position);
			if(pos)
				pos.style.width = "50%";
			if(pos2)
				pos2.style.width = "50%";
			downloadword.innerHTML = "安装";
			downloadword2.innerHTML = "安装";
		}else{
			downloadword.innerHTML = "免费下载";
			downloadword2.innerHTML = "免费下载";
		}
	}
}

function updateProgress(percent,position,catagory){
	var posground = document.getElementById("downbgroung"+position);
	if(posground)
		posground.style.backgroundColor = "#313131";
	var posground2 = document.getElementById("downbgroungland"+position);
	if(posground2)
		posground2.style.backgroundColor = "#313131";
	var freeword = document.getElementById("downloadword"+position);
	var freeword2 = document.getElementById("downloadwordland"+position);
	var perWidth = percent * 50;
	var pos = document.getElementById("downsecond"+position);
	var pos2 = document.getElementById("downsecondland"+position);
	if(pos)
		pos.style.width = perWidth+"%";
	if(pos2)
		pos2.style.width = perWidth+"%";
	var perNumber = percent * 100;
	if(perNumber < 10){
		perString = perNumber.toString().substring(0,1);
	}else if(perNumber >= 10 && perNumber < 100){
		perString = perNumber.toString().substring(0,2);
	}else if(perNumber >= 100){
		perString = perNumber.toString().substring(0,3);
	}
	if(freeword){
		freeword.innerHTML = perString+"%";
	}
	if(freeword2){
		freeword2.innerHTML = perString+"%";
	}
	if(percent >= 1.0){
		updateWord(position,freeword);
		updateWord(position,freeword2);
	}
}

function updateWord(position,freeword){
	if(freeword)
		freeword.innerHTML = "安装";
}
function backDefault(position,catagory){
	var bottomId = document.getElementById("bottomId");
	bottomId.style.position = "absolute";
	var posground = document.getElementById("downbgroung"+position);
	if(posground)
		posground.style.backgroundColor = "#3489e4";
	var posground2 = document.getElementById("downbgroungland"+position);
	if(posground2)
		posground2.style.backgroundColor = "#3489e4";
	var freeword = document.getElementById("downloadword"+position);
	var freeword2 = document.getElementById("downloadwordland"+position);
	if(freeword)
		freeword.innerHTML = "免费下载";
	if(freeword2)
		freeword2.innerHTML = "免费下载";
	bottomId.style.position = "fixed";
}
function changeState(index,value,catagory){
	var downloadword = document.getElementById("downloadword"+index);
	var downloadword2 = document.getElementById("downloadwordland"+index);
	var pos = document.getElementById("downsecond"+index);
	var pos2 = document.getElementById("downsecondland"+index);
	if(pos)
		pos.style.width = "0";
	if(pos2)
		pos2.style.width = "0";
	var posground = document.getElementById("downbgroung"+index);
	if(posground)
		posground.style.backgroundColor = "#3489e4";
	var posground2 = document.getElementById("downbgroungland"+index);
	if(posground2)
		posground2.style.backgroundColor = "#3489e4";
	if(downloadword)
		downloadword.innerHTML = "打开";
	if(downloadword2)
		downloadword2.innerHTML = "打开";
}

function deleteDiv(divId){
   var my = document.getElementById(divId);
   if (my != null)
       my.parentNode.removeChild(my);
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

function createBottomDiv(target,divId,position){
	var bottomDiv = document.createElement("div");
	bottomDiv.setAttribute("id",divId);
	if(divId == 'bottomId'){
		bottomDiv.innerHTML = "<div class='downbgroung' id='downbgroung"+position+"'></div><div class='downsecond' id='downsecond"+position+"'></div><div id='bigDownload"+position+"' class='bigdownload'><span id='downloadword"+position+"' class='downloadword'>免费下载</span></div>";
	}else if(divId == 'bottomId2'){
		bottomDiv.innerHTML = "<div class='downbgroung' id='downbgroungland"+position+"'></div><div class='downsecond2' id='downsecondland"+position+"'></div><div id='bigDownloadland"+position+"' class='bigdownload2'><span id='downloadwordland"+position+"' class='downloadword'>免费下载</span></div>";
	}
	insertAfter(bottomDiv,target);
}

function orientationChange(){
	// Portrait 
	if(window.orientation == 0 || window.orientation == 180){
		var blank = document.getElementById("blank");
		if(blank)
			blank.style.display='block';
		var bottomId = document.getElementById("bottomId");
		if(bottomId)
			bottomId.style.display='block';
		var bottomId2 = document.getElementById("bottomId2");
		if(bottomId2)
			bottomId2.style.display='none';
	}else if(window.orientation == 90 || window.orientation == -90){
		// Landscape
		var blank = document.getElementById("blank");
		if(blank)
			blank.style.display='none';
		var bottomId = document.getElementById("bottomId");
		if(bottomId)
			bottomId.style.display='none';
		var bottomId2 = document.getElementById("bottomId2");
		if(bottomId2)
			bottomId2.style.display='block';
	}
} 
window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", orientationChange, false); 