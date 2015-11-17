var serverUrl = "/AdService/";
function storageData(key,value){
	if(window.sessionStorage){
		sessionStorage.setItem(key,value);
	}else{
		window.toast.toast("不支持本地存储");
	}
}

var EventUtil=new Object;
//移除绑定
EventUtil.removeEvent=function(oTarget,sEventType,funName){
    if(oTarget.removeEventListener){//for DOM;
       oTarget.removeEventListener(sEventType,funName, false);
    }else if(oTarget.detachEvent){
      oTarget.detachEvent("on"+sEventType,funName);
   } else oTarget["on"+sEventType]=null;//移除函数绑定
};

EventUtil.addEvent=function(oTarget,sEventType,funName){
	if(oTarget.addEventListener){//for DOM;
	  oTarget.addEventListener(sEventType,funName, false);
	}else if(oTarget.attachEvent){
	  oTarget.attachEvent("on"+sEventType,funName);
	}else{
	  oTarget["on"+sEventType]=funName;
	}
};