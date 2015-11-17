function submitData(){
	var opinion = document.getElementById("opinion").value;
	if(opinion.length <= 0){
		document.location="alert://///"+encodeURIComponent("意见不能为空");
		return;
	}
	if(opinion.length > 100){
		document.location="alert://///"+encodeURIComponent("意见不能大于100字符");
		return;
	}
	valid();
	var emailAddr = document.getElementById("emailAddr").value;
	document.location="objc://///"+"iosfeedback"+":////&"+opinion+":////&"+emailAddr; 
}

function valid(){
	 var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 email = document.getElementById("emailAddr").value;
	 if(email.length <= 0){
		 document.location="alert://///"+encodeURIComponent("亲，邮箱不能为空!");
         myreg.focus();
		 return;
	 }else if(!myreg.test(email))
     {
		 document.location="alert://///"+encodeURIComponent("请输入有效的E_mail");
         myreg.focus();
         return;
     }
}

function dothings(id){
	  var txt1=document.getElementById(id);
	  txt1.value="";
	  txt1.focus();
}