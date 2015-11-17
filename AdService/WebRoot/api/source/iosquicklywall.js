var pageNo = 1;
var pageCount = 0;

// 退出
function homeback(id) {
	document.location = "back://///back";
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
	downBottom.innerHTML = "<div class='downbgroung' id='downbgroung" + index + "'>";

	downloadDiv.appendChild(downTop);
	downloadDiv.appendChild(downMiddle);
	downloadDiv.appendChild(downBottom);

	boxRDiv.appendChild(downloadDiv);
	boxDiv.addEventListener('click', function() {
		downloadFile(item.ad_type, item.ad_url, item.app_url, item.id, index,
				item.apple_id, "0",item.appid,item.is_url_params,item.url_params);
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

function downloadFile(adType, adUrl, app_url, id, index, apple_id, pageType,appid,is_url_params,url_params) {
	document.location = "objc://///" + "yjfdown" + ":////&" + adType + ":////&"
			+ adUrl + ":////&" + app_url + ":////&" + id + ":////&" + apple_id
			+ ":////&" + pageType +":////&"+appid+":////&"+is_url_params+":////&"+url_params;  // cmd代表objective- c中的的方法名，parameter1自然就是参数了
}

function createDivider(id, target) {
	var divider = document.createElement("div");
	divider.setAttribute("id", id);
	divider.innerHTML = "<div class='divider_up'></div><div class='divider_bottom'></div>";
	var source = document.getElementById(target);
	source.appendChild(divider);
}

window.onscroll = function() {
	if ((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {

		if (pageNo <= pageCount) {
			document.location = "objc://///" + "requestData" + ":////&"
					+ pageNo + ":////&" + 0 + ":////&" + 0 + ":////&" + 0; // cmd代表objective-
																			// c中的的方法名，parameter1自然就是参数了
		}
	
	}
};


