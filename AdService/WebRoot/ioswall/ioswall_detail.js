var pageNo = 0;
var pageCount = 0;
var pageSize = 10;


window.onload=function(){
	var appkey =  getQueryString("appkey");
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



function dataUndefined(data) {
	if (data.list == undefined) {
		return;
	}
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
