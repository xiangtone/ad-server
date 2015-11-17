var xmlhttp;
function submitData(){
	var opinion = document.getElementById("opinion").value;
	if(opinion.length <= 0){
		window.toast.toast("意见不能为空!");
		return;
	}
	if(opinion.length > 100){
		window.toast.toast("意见不能大于100字符!");
		return;
	}
	valid();
	var emailAddr = document.getElementById("emailAddr").value;
	var params = window.feedback.getFeedBackParam(opinion,emailAddr);
	params = params.replace(/\+/g, "%2B");
	params = params.replace(/\&/g, "%26");
	
	try {
        xmlhttp = new XMLHttpRequest();
    } catch (e) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.open("post", serverUrl + "android/heplDesc.do?m="+params, true);
    xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xmlhttp.send("");
    xmlhttp.onreadystatechange = function() {
        if (4 == xmlhttp.readyState) {
            if (200 == xmlhttp.status) {
            	window.toast.toast("提交成功");
            }
        }
    };
}

function valid(){
	 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 email = document.getElementById("emailAddr").value;
	 if(email.length <= 0){
		 window.toast.toast("亲，邮箱不能为空!");
         myreg.focus();
		 return;
	 }else if(!myreg.test(email))
     {
		 window.toast.toast('请输入有效的E_mail！');
         myreg.focus();
         return;
     }
}

function dothings(id){
	  var txt1=document.getElementById(id);
	  txt1.value="";
	  txt1.focus();
}