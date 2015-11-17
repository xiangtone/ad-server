var pageNo = 1;
var pageCount = 0;

var TpageSize = 10;
var recPageNo = 1;
var recPageCount = 0;
var item;

// 0积分墙,1,我的足迹,2热门推荐，3通知中心
var isBottom = 0;

// 退出
function homeback(id) {
	document.location = "back://///back";
}
// 帮助
function help(id) {
	document.location = "help://///help";
}
// 反馈
function feedback(id) {
	document.location = "feedback://///feedback";
}

function requestData(pageType, pageSize, params) {
	var xmlhttp;
	document.location = "start://///start";
	serverUrl = "/AdService/";
	params = params.replace(/\+/g, "%2B");
	params = params.replace(/\&/g, "%26");
	try {
		xmlhttp = new XMLHttpRequest();
	} catch (e) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("post", serverUrl + "ios/ad_picker.do" + "?map=" + params,
			true);
	xmlhttp.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

	xmlhttp.onreadystatechange = function() {
		if (4 == xmlhttp.readyState) {
			if (200 == xmlhttp.status) {
				var data = eval("(" + xmlhttp.responseText + ")");
				if (data.data.adList == undefined) {
					document.getElementById('itemtitle1').style.display = "";
					document.location = "finish://///finish";
					return;
				}
				scoreWall(data, pageSize);
				document.location = "finish://///finish";
			} else {
				document.location = "alert://///"
						+ encodeURIComponent("积分墙请求错误");
			}
		}
	};
	xmlhttp.send("");

	// requestHotData(pageType, pageSize, params);
}
function requestHotData(pageType, pageSize, params) {
//	var xmlhttp2;
//	document.location = "start://///start";
//	serverUrl = "/AdService/";
//	params = params.replace(/\+/g, "%2B");
//	params = params.replace(/\&/g, "%26");
//	try {
//		xmlhttp2 = new XMLHttpRequest();
//	} catch (e) {
//		xmlhttp2 = new ActiveXObject("Microsoft.XMLHTTP");
//	}
//	xmlhttp2.open("post", serverUrl + "ios/hot.do" + "?map=" + params, true);
//	// commonIos/notice.do
//	xmlhttp2.setRequestHeader('Content-type',
//			'application/x-www-form-urlencoded');
//
//	xmlhttp2.onreadystatechange = function() {
//		if (4 == xmlhttp2.readyState) {
//			if (200 == xmlhttp2.status) {
//				var data = eval("(" + xmlhttp2.responseText + ")");
//				if (data.data.adList == undefined) {
//					document.getElementById('itemtitle6').style.display = "";
//					return;
//				}
//				scoreWall2(data, pageSize);
//				document.location = "finish://///finish";
//			} else {
//				// document.location = "alert://///"
//				// + encodeURIComponent("积分墙请求错误");
//			}
//		}
//	};
//	xmlhttp2.send("");

}

function dataUndefined(data) {
	if (data.data.adList == undefined) {
		return;
	}
}

function scoreWall(data, pageSize) {

	dataUndefined(data);
	pageCount = data.data.wallPage.pageCount;
	var list = data.data.adList;
	if (list.length > 0) {
		for ( var i in list) {
			var index = (pageNo - 1) * pageSize + parseInt(i);
			drawItem(index, list[i], 0);
		}
	}
	pageNo++;
}

function scoreWall2(data, pageSize) {
	dataUndefined(data);
	recPageCount = data.data.wallPage.pageCount;
	var list = data.data.adList;
	if (list.length > 0) {
		for ( var i in list) {
			var index = (recPageNo - 1) * pageSize + parseInt(i);
			drawItem2(index, list[i], 0);
		}
	}
	recPageNo++;
}

function drawItem2(index, item, catagory) {

	var boxDiv = document.createElement("div");
	boxDiv.setAttribute("class", "box");
	boxDiv.setAttribute("id", "box2" + index);

	// 左边模块
	var boxLMDiv = document.createElement("div");
	boxLMDiv.setAttribute("class", "boxLM");
	boxLMDiv.setAttribute("id", "boxLM2" + index);

	var boxLDiv = document.createElement("div");// 创建item的图片模块
	boxLDiv.setAttribute("class", "boxL");
	boxLDiv.setAttribute("id", "boxL2");
	var boxLImage = document.createElement("img");
	boxLImage.setAttribute("class", "logo");
	boxLImage.setAttribute("src", item.general.wall_icon_Url);
	boxLDiv.appendChild(boxLImage);

	// 创建中间的模块
	var boxMDiv = document.createElement("div");
	boxMDiv.setAttribute("class", "boxM");
	boxMDiv.setAttribute("id", "boxM2");
	boxMDiv.innerHTML = "<div id='title2" + index + "' class='title_reco'>"
			+ item.title + "</div><div id='desc2" + index
			+ "' class='desc_reco'>" + item.general.wall_desc + "</div>";

	// 创建右边的模块
	var boxRDiv = document.createElement("div");
	boxRDiv.setAttribute("class", "boxR");
	boxRDiv.setAttribute("id", "boxR2" + index);
	var downBottom = document.createElement("div");
	downBottom.setAttribute("class", "downbgroung2");
	downBottom.setAttribute("id", "downbgroung2" + index);
	downBottom.innerHTML = "免费";
	boxRDiv.appendChild(downBottom);

	boxDiv.addEventListener('click', function() {
		downloadFile(item.ad_type, item.ad_url, item.app_url, item.id, index,
				item.apple_id, "6", item.appid, item.is_url_params,
				item.url_params, item.general.wall_icon_Url);
	}, false);
	// 加入到boxLM当中
	boxLMDiv.appendChild(boxLDiv);
	// 加入到boxLM当中
	boxLMDiv.appendChild(boxMDiv);
	// 加入到box中
	boxDiv.appendChild(boxLMDiv);
	boxDiv.appendChild(boxRDiv);

	// var dividerId = document.getElementById("divider2");
	var dividerUp = document.createElement("div");
	dividerUp.setAttribute("class", "divider_up");
	var dividerBottom = document.createElement("div");
	dividerBottom.setAttribute("class", "divider_bottom");
	boxDiv.appendChild(dividerUp);
	boxDiv.appendChild(dividerBottom);
	// insertAfter2(boxDiv, dividerId);
	// insertAfter2(dividerUp, boxDiv);
	// insertAfter2(dividerBottom, dividerUp);
	var wall_list2 = document.getElementById("wall_list2");
	// var divider2 = document.getElementById("divider2");
	wall_list2.appendChild(boxDiv);

}

// catagory 0 积分墙 1我的足迹 2热门推荐 3通知中心默认页中我的足迹 4通知中心默认页中热门推荐
function drawItem(index, item, catagory) {

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
	boxLImage.setAttribute("src", item.general.wall_icon_Url);

	boxLDiv.appendChild(boxLImage);
	// 创建中间的模块
	var boxMDiv = document.createElement("div");
	boxMDiv.setAttribute("class", "boxM");
	boxMDiv.setAttribute("id", "boxM");
	boxMDiv.innerHTML = "<div id='title" + index + "' class='title'>"
			+ item.title + "</div><div id='catagory" + index
			+ "' class='catagory'>" + item.general.wall_desc
			+ "</div><div id='desc" + index + "' class='desc'>"
			+ item.score_msg + "</div>";

	// 创建右边的模块
	var boxRDiv = document.createElement("div");
	boxRDiv.setAttribute("class", "boxR");
	boxRDiv.setAttribute("id", "boxR" + index);

	var downloadDiv = document.createElement("div");
	var downTop = document.createElement("div");
	downTop.setAttribute("id", "downTop" + index);
	downTop.setAttribute("class", "downTop");
	downTop.innerHTML = "<span class='downloadplus'></span><span class='downjifen'>+"
			+ item.general.score + "</span>";

	var downMiddle = document.createElement("div");
	downMiddle.setAttribute("class", "downMiddle");
	downMiddle.setAttribute("id", "downMiddle" + index);
	downMiddle.innerHTML = "<span class='downjinbi'>" + item.general.scoreUnit
			+ "</span>";
	var downBottom = document.createElement("div");
	downBottom.setAttribute("class", "downBottom");
	downBottom.setAttribute("id", "downBottom" + index);
	downBottom.innerHTML = "<div class='downbgroung' id='downbgroung" + index
			+ "'>";

	downloadDiv.appendChild(downTop);
	downloadDiv.appendChild(downMiddle);
	downloadDiv.appendChild(downBottom);

	boxRDiv.appendChild(downloadDiv);
	boxDiv.addEventListener('click', function() {
		downloadFile(item, item.ad_type, item.ad_url, item.app_url, item.id,
				index, item.apple_id, "0", item.appid, item.is_url_params,
				item.url_params, item.title, item.score_msg,
				item.general.wall_icon_Url);
	}, false);

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

	// 将id的值存储在本地，item.id为key，其他值为value
	// storageData(item.id,item.title +","+item.general.wall_icon_Url+
	// ","+item.ad_type+","+item.ad_url+","+index);
}
function insertAfter(newElement, targetElement) {
	var parent = targetElement.parentNode;
	if (parent.lastChild == targetElement) {
		// 如果最后的节点是目标元素，则直接添加。因为默认是最后
		parent.appendChild(newElement);
	} else {
		parent.appendChild(newElement);
		// parent.insertBefore(newElement, targetElement.nextSibling);
		// 如果不是，则插入在目标元素的下一个兄弟节点 的前面。也就是目标元素的后面
	}
}

function downloadFile(itemt, adType, adUrl, app_url, id, index, apple_id,
		pageType, appid, is_url_params, url_params, title, scoremsg, ad_scr) {

//	adType =4;
	if (adType == 4) {
		item = itemt;
		document.getElementById("dg2").style.display = "";
		document.getElementById("alertback").style.display = "";

		document.getElementById("ad_name_b").innerHTML = title;
		document.getElementById("desc0").innerHTML = scoremsg;
		document.getElementById('logo_b').src = ad_scr;

		document.getElementById("weixin_desc").innerHTML = item.search_desc;//搜索描述

		document.getElementById("code_b").innerHTML = item.keyword;//关键字
		
	} else {
		document.location = "objc://///" + "yjfdown" + ":////&" + adType
				+ ":////&" + adUrl + ":////&" + app_url + ":////&" + id
				+ ":////&" + apple_id + ":////&" + pageType + ":////&" + appid
				+ ":////&" + is_url_params + ":////&" + url_params; // cmd代表objective-
	}
}

function keywordcopy() {

	document.location = "objc://///" + "yjfdown" + ":////&" + item.ad_type
			+ ":////&" + item.ad_url + ":////&" + item.app_url + ":////&"
			+ item.id + ":////&" + item.apple_id + ":////&" + "0" + ":////&"
			+ item.appid + ":////&" + item.is_url_params + ":////&"
			+ item.url_params; // cmd代表objective- c中的的方法名，parameter1自然就是参数了

}

window.onscroll = function() {
	if ((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {
		if (isBottom == 0) {
			if (pageNo <= pageCount) {
				document.location = "objc://///" + "requestData" + ":////&"
						+ pageNo + ":////&" + 0 + ":////&" + 0 + ":////&" + 0; // cmd代表objective-
				// c中的的方法名，parameter1自然就是参数了
			}
		}
	}

};

function createDivider(id, target) {
	var divider = document.createElement("div");
	divider.setAttribute("id", id);
	divider.innerHTML = "<div class='divider_up'></div><div class='divider_bottom'></div>";
	var source = document.getElementById(target);
	source.appendChild(divider);
}
