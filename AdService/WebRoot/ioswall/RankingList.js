var pageNo = 0;
var pageCount = 0;
var pageSize = 10;


window.onload=function(){
	
	
	var appkey =  getQueryString("appkey");
    requestData(pageSize,appkey);
 };

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

function requestData(pageSize, params) {
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
	xmlhttp.open("get", serverUrl + "agent/ad_picker.do" + "?appkey=" + params+"&pageSize="+pageSize,
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
//	var total = data.page.total;
//	pageCount = total/page;
//	if ((total % pageSize) == 0) { 
//		totalPages = totalRows / pageSize; 
//		} else { 
//		totalPages = totalRows / pageSize + 1; 
//		} 
//	

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
	
	var num = document.createElement("div");
	num.setAttribute("class", "downbgroung");
	num.innerHTML = index;
	boxLDiv.appendChild(num);
	
	var boxLDiv2 = document.createElement("div");
	boxLDiv2.setAttribute("class", "boxL2");
	
	var boxLImage = document.createElement("img");
	boxLImage.setAttribute("class", "logo");
	boxLImage.setAttribute("src", item.icon_url);
	boxLDiv2.appendChild(boxLImage);
	// 创建中间的模块
	var boxMDiv = document.createElement("div");
	boxMDiv.setAttribute("class", "boxM");
	boxMDiv.setAttribute("id", "boxM");
	boxMDiv.innerHTML = "<div id='title" + index + "' class='title'>"
			+ item.ad_name + "</div>";

	
	// 创建右边的模块
	var boxRDiv = document.createElement("div");
	boxRDiv.setAttribute("class", "boxR");
	boxRDiv.setAttribute("id", "boxR" + index);
	var downBottom = document.createElement("div");
	downBottom.setAttribute("class", "downbgroungr");
	downBottom.setAttribute("id", "downbgroung" + index);
	downBottom.innerHTML = "5652.22元";
	boxRDiv.appendChild(downBottom);

//	boxDiv.addEventListener('click', function() {
//		downloadFile(item.ad_type, item.ad_url, item.app_url, item.id, index,
//				item.apple_id, "0", item.appid, item.is_url_params,
//				item.url_params);
//		
//	}, false);

	// 加入到boxLM当中
	boxLMDiv.appendChild(boxLDiv);
	boxLMDiv.appendChild(boxLDiv2);
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
	if ((document.body.scrollHeight - (document.documentElement.clientHeight + document.body.scrollTop)) == 0) {

		if (pageNo <= pageCount) {
//			document.location = "objc://///" + "requestData" + ":////&"
//					+ pageNo + ":////&" + 0 + ":////&" + 0 + ":////&" + 0; // cmd代表objective-
			// c中的的方法名，parameter1自然就是参数了
		}

	}
};
