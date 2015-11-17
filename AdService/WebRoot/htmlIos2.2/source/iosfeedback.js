
function homeback() {
			document.location = "back://///back";
			}

//奖励失败
function clickitem1() {
	document.getElementById("checkbox1").style.backgroundColor='#1E90FF';
	document.getElementById("checkbox2").style.backgroundColor='#f5f5f5';
	document.getElementById('item3').style.display = "";
	document.getElementById('emailAddrdiv').style.display = "none";
	document.getElementById('opinion').value="我为什么没有得到积分?";
}

//其他问题
function clickitem2() {
	document.getElementById("checkbox1").style.backgroundColor='#f5f5f5';
	document.getElementById("checkbox2").style.backgroundColor='#1E90FF';
	document.getElementById('item3').style.display = "none";
	document.getElementById('emailAddrdiv').style.display = "";
	document.getElementById('opinion').value="请描述您遇到的问题或意见，我们将尽快修复并回复您！";
	document.location="hiddenselect://///"+encodeURIComponent("");
}

function selectAd() {
	document.location="showselect://///"+encodeURIComponent("");
}

function selectData(name, rowid) {
	document.getElementById('selectvalue').innerHTML=name;
	document.getElementById('select').value = rowid;
}


function submitData(){
	var desc = document.getElementById("opinion").value;
	if(desc.length <= 0){
		document.location="alert://///"+encodeURIComponent("意见不能为空");
		return;
	}
	if(desc.length > 100){
		document.location="alert://///"+encodeURIComponent("意见不能大于100字符");
		return;
	}
	var displayTag = document.getElementById('emailAddrdiv').style.display;
	var adId = document.getElementById('select').value;
	var type = 0;// 类型(0奖励失败，1 建议)
	if(displayTag =="")
	{	type =1;
		adId ="0";
		valid();
	}else if(adId =="0"){
		 document.location="alert://///"+encodeURIComponent("亲，请选择不返积分的广告!");
		 return;
	}
	var emailAddr = document.getElementById("emailAddr").value;
	document.location="objc://///"+"iosfeedback"+":////&"+desc+":////&"+emailAddr +":////&"+type+":////&"+adId; 
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
