var serverUrl = "/AdService/";
function storageData(key,value){
	if(window.sessionStorage){
		sessionStorage.setItem(key,value);
	}else{
			 alert('这个浏览器不支持本地存储');
	}
}

