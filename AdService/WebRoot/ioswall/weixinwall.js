var pageNo = 0;
var pageCount = 0;
var pageSize = 10;


function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

// 退出
function homeback(id) {
	document.location = "back://///back";
}

function requestData(pageSize, params,userid) {
	var xmlhttp;
	document.getElementById('spinner').style.display = "";
	serverUrl = "/AdService/";
	params = params.replace(/\+/g, "%2B");
	params = params.replace(/\&/g, "%26");
	try {
		xmlhttp = new XMLHttpRequest();
	} catch (e) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("get", serverUrl + "agent/ad_picker.do" + "?appkey=" + params+"&pageSize="+pageSize+"&userId="+userid+"&pageNo="+pageNo+"&t="+(new Date()).getTime(),
			true);
	xmlhttp.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

	xmlhttp.onreadystatechange = function() {
		if (4 == xmlhttp.readyState) {
			if (200 == xmlhttp.status) {
				var data = eval("(" + xmlhttp.responseText + ")");
				if (data.code == 200) {
					if(data.page.total>0){
						scoreWall(data, pageSize);
					}else{
						document.getElementById('itemtitle1').style.display = "";
					}
					document.getElementById('spinner').style.display = "none";
					return;
				}
			} else {

			}
		}
	};
	xmlhttp.send("");
}




function dataUndefined(data) {
	if (data.list == undefined) {
		return;
	}
}

function scoreWall(data, pageSize) {

	dataUndefined(data);
	var total = data.page.total;
	if ((total % pageSize) == 0) { 
		pageCount = total/pageSize; 
	}else{ 
		pageCount = total/pageSize + 1; 
	} 
	

	var list = data.list;
	if (list.length > 0) {
		for ( var i in list) {
			var index = pageNo*pageSize + parseInt(i);
			drawItem(index, list[i]);
		}
	}
	pageNo++;
}

// catagory 0 积分墙 1我的足迹 2热门推荐 3通知中心默认页中我的足迹 4通知中心默认页中热门推荐
function drawItem(index, item) {
	var boxDiv = document.createElement("div");
	boxDiv.setAttribute("class", "box");
	boxDiv.setAttribute("id", "box" + index);

	var boxLMDiv = document.createElement("div");
	boxLMDiv.setAttribute("class", "boxLM");
	boxLMDiv.setAttribute("id", "boxLM" + index);

	// 创建item的图片模块
	var boxLDiv = document.createElement("div");
	boxLDiv.setAttribute("class", "boxL");
	boxLDiv.setAttribute("id", "boxL");

	var boxLImage = document.createElement("img");
	boxLImage.setAttribute("class", "logo");
	boxLImage.setAttribute("src", item.icon_url);

	boxLDiv.appendChild(boxLImage);
	// 创建中间的模块
	var boxMDiv = document.createElement("div");
	boxMDiv.setAttribute("class", "boxM");
	boxMDiv.setAttribute("id", "boxM");
	boxMDiv.innerHTML = "<div id='title" + index + "' class='title'>"
			+ item.ad_name + "</div><div id='desc" + index + "' class='desc'>"
			+ item.slogan+ "</div>";

	
	// 创建右边的模块
	var boxRDiv = document.createElement("div");
	boxRDiv.setAttribute("class", "boxR");
	boxRDiv.setAttribute("id", "boxR" + index);
	var downBottom = document.createElement("div");
	downBottom.setAttribute("class", "downbgroung");
	downBottom.setAttribute("id", "downbgroung" + index);
	downBottom.innerHTML = item.score+""+item.score_unit;
	boxDiv.addEventListener('click', function() {
		
		send_token()
		if(item.response_type&&item.response_type==5){
			bt_click_b(item.ad_id,item.icon_url,item.ad_name,item.store_url,item.score+""+item.score_unit,item.weixin_desc,item.keyword);
		}else{
			bt_click(item.ad_id,item.icon_url,item.ad_name,item.store_url,item.score+""+item.score_unit,item.slogan,item.task_desc);
		}
		
	}, false);
	boxRDiv.appendChild(downBottom);

//	boxDiv.addEventListener('click', function() {
//		downloadFile(item.ad_type, item.ad_url, item.app_url, item.id, index,
//				item.apple_id, "0", item.appid, item.is_url_params,
//				item.url_params);
//		
//	}, false);

	// 加入到boxLM当中
	boxLMDiv.appendChild(boxLDiv);
	// 加入到boxLM当中
	boxLMDiv.appendChild(boxMDiv);
	// 加入到box中
	boxDiv.appendChild(boxLMDiv);
	boxDiv.appendChild(boxRDiv);
	var dividerId = document.getElementById("divider0");
	var dividerUp = document.createElement("div");
	dividerUp.setAttribute("class", "divider_up");
	var dividerBottom = document.createElement("div");
	dividerBottom.setAttribute("class", "divider_bottom");
	var position = parseInt(index) + parseInt(1);
	dividerBottom.setAttribute("id", "divider" + position);
	insertAfter(boxDiv, dividerId);
	insertAfter(dividerUp, boxDiv);
	insertAfter(dividerBottom, dividerUp);
}


function send_token(){
	var xmlhttp;
	
	serverUrl = "/AdService/";
	try {
		xmlhttp = new XMLHttpRequest();
	} catch (e) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("get", serverUrl + "weixin/get_token.do?&userId="+userid+"&st="+(new Date()).getTime,
			true);
	xmlhttp.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

	xmlhttp.onreadystatechange = function() {
		if (4 == xmlhttp.readyState) {
			if (200 == xmlhttp.status) {
			} else {

			}
		}
	};
	xmlhttp.send("");
	
}

function bt_click(ad_id,icon_url,ad_name,download_url,score_unit,task_desc,score_desc){
	document.getElementById("dg1").style.left=(document.body.clientWidth-293)/2+"px";
	document.getElementById("dg1").style.top=((document.documentElement.clientHeight-300)/2+window.document.body.scrollTop)+"px";
	document.getElementById("b").style.height=window.screen.availHeight+"px";
	
	
	//判断是哪个大1、如果是在一屏幕以内
	//alert(window.document.body.offsetHeight+"----"+window.screen.availHeight);
	
	document.body.scroll="no";
	document.getElementById("dg1").style.display = "block";
	document.getElementById("b").style.display = "block";
	document.getElementById("logo").setAttribute("src",icon_url);
	document.getElementById("ad_name").innerHTML=ad_name;
	d_url=encodeURIComponent(encodeURIComponent(download_url));
	document.getElementById("score_unit").innerHTML="立即赚取"+score_unit;
	document.getElementById("slogan").innerHTML=task_desc;
	document.getElementById("score_desc").innerHTML=score_desc;
//	document.ontouchstart =function(){return false;};
	//stopScroll();
	
}


function bt_click_b(ad_id,icon_url,ad_name,download_url,score_unit,weixin_desc,keyword){
	document.getElementById("dg2").style.left=(document.body.clientWidth-293)/2+"px";
	document.getElementById("dg2").style.top=((window.screen.availHeight-300)/2+window.document.body.scrollTop)+"px";
	document.getElementById("b").style.height=window.document.body.offsetHeight+"px";
	document.getElementById("dg2").style.display = "block";
	document.getElementById("b").style.display = "block";
	document.getElementById("logo_b").setAttribute("src",icon_url);
	document.getElementById("ad_name_b").innerHTML=ad_name;
	document.getElementById("code_b").innerHTML=keyword;
	d_url=encodeURIComponent(encodeURIComponent(download_url));
	document.getElementById("weixin_desc").innerHTML=weixin_desc;
	
}



function insertAfter(newElement, targetElement) {
	var parent = targetElement.parentNode;
	if (parent.lastChild == targetElement) {
		// 如果最后的节点是目标元素，则直接添加。因为默认是最后
		parent.appendChild(newElement);
	} else {
		parent.appendChild(newElement);
	}
}

function downloadFile(adType, adUrl, app_url, id, index, apple_id, pageType,
		appid, is_url_params, url_params) {
	document.location = "objc://///" + "yjfdown" + ":////&" + adType + ":////&"
			+ adUrl + ":////&" + app_url + ":////&" + id + ":////&" + apple_id
			+ ":////&" + pageType + ":////&" + appid + ":////&" + is_url_params
			+ ":////&" + url_params; // cmd代表objective-
										// c中的的方法名，parameter1自然就是参数了
}

function createDivider(id, target) {
	var divider = document.createElement("div");
	divider.setAttribute("id", id);
	divider.innerHTML = "<div class='divider_up'></div><div class='divider_bottom'></div>";
	var source = document.getElementById(target);
	source.appendChild(divider);
}

window.onscroll = function() {
	document.getElementById("b").style.height=window.document.body.offsetHeight+"px";
	if ((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {
		if (pageNo <= pageCount) {
			requestData(pageSize, appkey,userid);
		}
	}
}
	