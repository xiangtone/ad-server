
function setAtt(obj,classValue,flag){
	if(flag==0){
		obj.setAttribute('class',classValue);   
	}
	if(flag==1){
		obj.setAttribute('className',classValue);   
	}
}

//还原表单数据样式
function reduce(obj,classValue){
	obj.setAttribute('class',classValue);   
	obj.setAttribute('className',classValue);  
}





//表单控件的验证 
function ondeto(name_id,name_error){
		var id_tag=document.getElementById(name_id);
		var error_tag=document.getElementById(name_error);
		var i = new RegExp(id_tag.getAttribute("reg"));
		var iclas =id_tag.getAttribute("class");
		var flag=0;		
		if(iclas==null){
			flag=1;
			iclas=id_tag.getAttribute("className");
		}
		if(iclas=="inp-form" || iclas=='inp-form-error'){	
				if(!i.test(id_tag.value)){					
					error_tag.style.visibility='visible';
					setAtt(id_tag,'inp-form-error',flag);
				}else{
					error_tag.style.visibility='hidden';
					setAtt(id_tag,'inp-form',flag);
				}
  		 }else if(iclas=="form-textarea" || iclas=='form-textareaerror'||iclas=='form-textareaerror input_validation-failed'){
  			 var a=document.getElementById("name_error_no2");
  			var application=document.getElementById("application");
  			var b=document.getElementById("name_error2_no3");
  			 if(id_tag.value == ''){  				
  				error_tag.style.visibility='hidden';
  				if(a){
  				a.style.visibility='visible'; 
  				}
  				if(b){
  					b.style.visibility='hidden';
  				}
  				if(iclas=="form-textarea"){
  					setAtt(id_tag,'form-textareaerror',flag); 					
  				}
  			 }else if(id_tag.value.length>100){	
  				error_tag.style.visibility='hidden';
  				b.style.visibility='visible';
  				a.style.visibility='hidden'; 
  				if(iclas=="form-textarea"){
  					setAtt(id_tag,'form-textareaerror',flag); 					
  				}
  				
  			 } else{
				if(!i.test(id_tag.value)){
					a.style.visibility='hidden';
					b.style.visibility='hidden';
					error_tag.style.visibility='visible';
					if(iclas=="form-textarea"){
	  					setAtt(id_tag,'form-textareaerror',flag); 					
	  				}
				}else{				
					a.style.visibility='hidden';					
					if(b){
					b.style.visibility='hidden';
					}					
					error_tag.style.visibility='hidden';							
						setAtt(id_tag,'form-textarea',flag);
					
				}
  			 }
	     }else if(name_id=="file_1_upfile"){
				if(!i.test(id_tag.value)){
					//id_tag.style.width='310px';
					id_tag.style.border='1px solid #d90e0e';
					id_tag.style.padding='5px';
					//id_tag.setAttribute('style','width:310px;border: 1px solid #d90e0e;padding: 5px;');
					error_tag.style.visibility='visible';
				}else{
					error_tag.style.visibility='hidden';
					//id_tag.style.width='310px';
					id_tag.style.border='1px solid #acacac';
					id_tag.style.padding='5px';
					//id_tag.setAttribute('style','width:310px;border: 1px solid #acacac;padding: 5px;');
				}
	     }else if(name_id=="file_2_upfile"){
				 if(!i.test(id_tag.value)){
					 	//id_tag.style.width='310px';
						id_tag.style.border='1px solid #d90e0e';
						id_tag.style.padding='5px';
						error_tag.style.visibility='visible';
				 }else{
					 	error_tag.style.visibility='hidden';
					 	//id_tag.style.width='310px';
						id_tag.style.border='1px solid #acacac';
						id_tag.style.padding='5px';
				 }
	     }else if(name_id=="file_3_upfile"){
				 if(!i.test(id_tag.value)){
					 	//id_tag.style.width='310px';
						id_tag.style.border='1px solid #d90e0e';
						id_tag.style.padding='5px';
						error_tag.style.visibility='visible';
				 }else{
					 	error_tag.style.visibility='hidden';
					 	//id_tag.style.width='310px';
						id_tag.style.border='1px solid #acacac';
						id_tag.style.padding='5px';
				 }
	     }else if(name_id=="file_4_upfile"){
				 if(!i.test(id_tag.value)){
					 	// id_tag.style.width='310px';
						id_tag.style.border='1px solid #d90e0e';
						id_tag.style.padding='5px';
						error_tag.style.visibility='visible';
				 }else{
					 	error_tag.style.visibility='hidden';
					 	// id_tag.style.width='310px';
						id_tag.style.border='1px solid #acacac';
						id_tag.style.padding='5px';
				 }
	     }
}

//调用file控件的click方法。打开文件选择器
//function clickFile(file_id){
//	document.getElementById(file_id).click();
//	}
////给text赋值
//function setValue(file_id,text_id,name_error){
//	
//	  //获取file对象
//	  var file = document.getElementById(file_id);
//	  var realpath;
//	 
//	  try{
//		 
//	   //文件物理路径
//		  var urlPath;
//		  //解决浏览器兼容，IE的判断必须放在最前面，纠结
//		  if(navigator.userAgent.indexOf("MSIE") != -1){//IE浏览器
//			  file.select();
//			  realpath =  document.selection.createRange().text;
//			    document.getElementById(text_id).value=realpath;
//		  }else if (navigator.userAgent.indexOf("Firefox") != -1 ) {  //火狐浏览器
//			 
//				urlPath = getFilePathWithFF(file);
//				alert(urlPath);
//				document.getElementById(text_id).value=file;
//		  }else if(navigator.userAgent.indexOf("Mozilla") != -1){//这里代表谷歌
//			 
//			    document.getElementById(text_id).value=file.value;
//		  }else{//其他浏览器
//			   document.getElementById(text_id).value=file.value;
//		  }
//	  } finally { 
//			document.selection.empty(); 
//	  }
//
//	 // ondeto(text_id,name_error);
//	}
//
////火狐下的处理
//function getFilePathWithFF(fileBrowser) {  
//
//	   netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
//	  
//	    var fileName=fileBrowser.value; 
//	    var file = Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);
//	        file.initWithPath( fileName.replace(/\//g, "\\\\") );
//	   return file.path;
//    
//}  
//开发者 添加应用 重置 
//function clearAll(statu){				
//					var name_id =  document.getElementById("name_id");
//					var name_id_size =  document.getElementById("name_id_size");
//					var name_id1 =  document.getElementById("name_id1");
//					var name_id2 =  document.getElementById("name_id2");
//					var file_1 =  document.getElementById("file_1");
//					var file_2 =  document.getElementById("file_2");
//					var file_3 =  document.getElementById("file_3");
//					var file_4 =  document.getElementById("file_4");
//					name_id.value = "";
//					name_id_size.value = "";
//					name_id1.value = "";
//					name_id2.value = "";
//					file_1.value = "";
//					file_2.value = "";
//					file_3.value = "";
//					file_4.value = "";
//			}	
//验证开发者两次输入的密码
function check_PassWord(){
	//第二次的密码
	var confimPassword = document.getElementById("confimPassword");
	//正则
	var reg = new RegExp(confimPassword.getAttribute("reg"));
	//第一个提示
	var error_tag=document.getElementById("name_error3");
	//第二个提示
	var error_tag4 = document.getElementById("name_error4");
	
	
	//首先验证正则
	if(!reg.test(confimPassword.value)){
		var error_tag=document.getElementById("name_error3");		
		error_tag4.setAttribute('style','display:none');
		error_tag.style.display="inline-block";
		confimPassword.className = "inp-form-error";
	}else{//当符合正则时
		//第一次输入的新密码
		var password = document.getElementById("password").value;
		//两次输入的密码一致
		if(password == confimPassword.value){	
			error_tag4.style.display="none";
			error_tag.setAttribute('style','display:none');
			name_error3.style.display = "none";
			confimPassword.className = "inp-form";
		}else{//两次输入的密码不一致
			error_tag.setAttribute('style','display:none');
			error_tag4.style.display = "block";
			name_error3.style.display = "none";
			confimPassword.className = "inp-form-error";
    	}
	}
	return false;
}
//开发者密码修改页面 表单验证
function checkPassWord(){
	var oldpass = ondeto_Form('oldpass','name_error1');
	var password = ondeto_Form('password','name_error2');
	var confimPassword = ondeto_Form('confimPassword','name_error3');
	if(oldpass && password && confimPassword){
		if($('#password').attr('value') ==  $('#confimPassword').attr('value')){//判断两次密码是否相同
			return true;
		}else{
			check_PassWord();
			return false;
		}
	}else{
		return false;
	}
}
//开发者财务资料添加验证
function checkFnancialInfoModify(){
	
	var type =  $('#type');
	var platform =  $('#platform');
	
	var cardCode_Name = ondeto_Form('cardCode_Name','name_error1');
	var card = ondeto_Form('file_1_upfile','name_error2');
	var userName = ondeto_Form('userName','name_error3');
	var bankName = ondeto_Form('bankName','name_error4');
	var bankSubbranch = ondeto_Form('bankSubbranch','name_error5');
	var bankAccount = ondeto_Form('bankAccount','name_error6');
	
	if(type.attr("value") == "0" && platform.attr("value") == "0"){
		document.getElementById('name_error7').style.visibility='visible';
	}
	if(type.attr("value") != "0" && platform.attr("value") != "0"){
		document.getElementById('name_error7').style.visibility='hidden';
	}	
	if( type.attr("value") != "0" && platform.attr("value") != "0" &&cardCode_Name && card && userName && bankName && bankSubbranch && bankAccount){
		return true;
	}else{
		return false;
	}
}
//开发者基本资料 提交验证
function checkDeveloperModify(){
	var realName = ondeto_Form('realName','name_error1');
	var mobilePhone = ondeto_Form('mobilePhone','name_error2');
	var qq = ondeto_Form('qq','name_error3');
	var postCode = ondeto_Form('postCode','name_error4');
	var mailingAddress = ondeto_Form('mailingAddress','name_error5');
	
	var devType = $('#devType').attr("value");//登录用户是个人还是公司 （2 ：公司）
	
	if(devType == '2'){//登陆用户为公司
		var companyName = ondeto_Form('companyName','name_error6');
		var companyAddress = ondeto_Form('companyAddress','name_error7');
		var websiteUrl = ondeto_Form('websiteUrl','name_error8');
		
		if(realName && mobilePhone && qq && postCode && mailingAddress && companyName && companyAddress && websiteUrl){
			return true;
		}else{
			return false;
		}
	}else{//登陆用户为个人
		if(realName && mobilePhone && qq && postCode && mailingAddress ){
			return true;
		}else{
			return false;
		}
	}
}
//开发者添加应用  表单验证
function check_AddDevApp(){
	alert("");
	/*
	var name = ondeto_Form('name','name_error');
	
	var type =  $('#type');
	var platform =  $('#platform');
	var category =  $('#category');
	
	var shortDesc =  ondeto_Form('shortDesc','name_error1');
	var shortD =  ondeto_Form('shortDesc','name_error_1');
	var longDesc =  ondeto_Form('longDesc','name_error_no2');
	var file_1_upfile =  ondeto_Form('file_1_upfile','name_error3');
	var file_2_upfile =  ondeto_Form('file_2_upfile','name_error4');
	var file_3_upfile =  ondeto_Form('file_3_upfile','name_error5');
	var file_4_upfile =  ondeto_Form('file_4_upfile','name_error6');

	if(type.attr("value") == "0" && platform.attr("value") == "0" && category.attr("value") == "0"){
		document.getElementById('name_error7').style.visibility='visible';
	}
	if(type.attr("value") != "0" && platform.attr("value") != "0" && category.attr("value") != "0"){
		document.getElementById('name_error7').style.visibility='hidden';
	}

	if(name && type.attr("value") != "0" && platform.attr("value") != "0" && category.attr("value") != "0" 
		&& shortDesc && shortD && longDesc && file_1_upfile && file_2_upfile && file_3_upfile && file_4_upfile){
		return true;
	}else{
		return false;
	}
	*/
	return true;
}
//开发者应用 详情页 表单验证
function check_AddDevApp_detail(){
	var name = ondeto_Form('name','name_error');
	
	var type =  $('#type');
	var platform =  $('#platform');
	var category =  $('#category');
	
	var shortDesc =  ondeto_Form('shortDesc','name_error1');
	var longDesc =  ondeto_Form('longDesc','name_error_no2');
	var file_2_upfile =  true;
	var file_3_upfile =  true;
	var file_4_upfile =  true;

	if(document.getElementById("file_2_upfile") != null){
		file_2_upfile =  ondeto_Form('file_2_upfile','name_error4');
	}
	if(document.getElementById("file_3_upfile") != null){
		file_3_upfile =  ondeto_Form('file_3_upfile','name_error4');
	}
	if(document.getElementById("file_4_upfile") != null){
		file_4_upfile =  ondeto_Form('file_4_upfile','name_error4');
	}
	
	if(type.attr("value") == "0" && platform.attr("value") == "0" && category.attr("value") == "0"){
		document.getElementById('name_error7').style.visibility='visible';
	}
	if(type.attr("value") != "0" && platform.attr("value") != "0" && category.attr("value") != "0"){
		document.getElementById('name_error7').style.visibility='hidden';
	}
	if(name && type.attr("value") != "0" && platform.attr("value") != "0" && category.attr("value") != "0" 
		&& shortDesc && longDesc  && file_2_upfile && file_3_upfile && file_4_upfile){
		return true;
	}else{
		return false;
	}
}

/***********************************广告主Js*********************************************/
//广告主 新旧密码验证 
function adv_check_PassWord(){
	//第二次的密码
	var confimPassword = document.getElementById("password2");
	//正则
	var reg = new RegExp(confimPassword.getAttribute("reg"));
	//第一个提示
	var error_tag=document.getElementById("name_error3");
	//第二个提示
	var error_tag4 = document.getElementById("name_error4");
	
	//首先验证正则
	if(!reg.test(confimPassword.value)){
		var error_tag=document.getElementById("name_error3");
		
		error_tag4.setAttribute('style','display:none');
		
		error_tag.setAttribute('style','display:inline');
		confimPassword.setAttribute('class','inp-form-error'); 
		
	}else{//当符合正则时
		//第一次输入的新密码
		var password = document.getElementById("password1").value;
		
		//两次输入的密码一致
		if(password == confimPassword.value){
			error_tag4.setAttribute('style','display:none');
			
			error_tag.setAttribute('style','display:none');
			confimPassword.setAttribute('class','inp-form'); 
		}else{//两次输入的密码不一致
			error_tag.setAttribute('style','display:none');
			
    		error_tag4.setAttribute('style','display:inline');
    		confimPassword.setAttribute('class','inp-form-error'); 
		}
	}
}
//广告主密码修改 表单提交验证
function check_Adv_PassWord(){
	var oldpass = ondeto_Form('oldPassword','name_error1');
	var password = ondeto_Form('password1','name_error2');
	var confimPassword = ondeto_Form('password2','name_error3');
	
	if(oldpass && password && confimPassword){
		if($('#password1').attr('value') ==  $('#password2').attr('value')){//判断两次密码是否相同
			return true;
		}else{
			adv_check_PassWord();
			return false;
		}
	}else{
		return false;
	}
}
//广告主 财务资料添加验证
function checkAdverFinanceModify(){
	var cardCode_Name = ondeto_Form('cardCode_Name','name_error1'); 
	var card = ondeto_Form('file_1_upfile','name_error2'); 
	var bankName = ondeto_Form('bankName','name_error4'); 
	var bankSubbranch = ondeto_Form('bankSubbranch','name_error5'); 
	var bankAccount = ondeto_Form('bankAccount','name_error6'); 
	var bankCity = ondeto_Form('bankCity','name_error7'); 
	
	if(cardCode_Name && card && bankName && bankSubbranch && bankAccount && bankCity){
		return true;
	}else{
		return false;
	}
}
//广告主 资本资料 添加表单那验证
function checkAdverBasic(){
	var realName = ondeto_Form('realName','name_error1');
	var mobilePhone = ondeto_Form('mobilePhone','name_error2');
	var qq = ondeto_Form('qq','name_error3');
	var postCode = ondeto_Form('postCode','name_error4');
	var mailingAddress = ondeto_Form('mailingAddress','name_error5');
	
	var devType = $('#devType').attr("value");//登录用户是个人还是公司 （2 ：公司）
	
	if(devType == '2'){//登陆用户为公司
		var companyName = ondeto_Form('companyName','name_error6');
		var companyAddress = ondeto_Form('companyAddress','name_error7');
		var websiteUrl = ondeto_Form('websiteUrl','name_error8');
		
		if(realName && mobilePhone && qq && postCode && mailingAddress && companyName && companyAddress && websiteUrl){
			return true;
		}else{
			return false;
		}
	}else{//登陆用户为个人
		if(realName && mobilePhone && qq && postCode && mailingAddress ){
			return true;
		}else{
			return false;
		}
	}
}
//广告主 添加应用表单验证
function check_adv_addAdd(){

	var type = $('#type');//活动类别
	var platform = $('#platform');
	var category = $('#category');
	var price = $('#finance_price');
	
//	var point_desc_status = $('#point_desc_status');//付费条件
//	var point_desc = $('#point_desc'); //付费条件 其他
	var budgetN = document.getElementsByName("budgetN");//广告预算
	var budgetDayId = $('#budgetDayId');//预算 详细
	var minPrice = $('#minPrice');
	
	var budget = document.getElementById("budget").value; //总预算  
	var actualBalance = document.getElementById("actualBalance").value;  
	var creditBalance = document.getElementById("creditBalance").value;  
	var minDayBudget = document.getElementById("minDayBudget").value;//最小日预算
	
	var canSubmit = true;
	var budgetN_value;
	for(var i=0;i<budgetN.length;i++){
		if(budgetN[i].type=="radio" && budgetN[i].checked){
			budgetN_value = budgetN[i].value;
		}
	}
	if (price.attr('value') < minPrice.attr('value')){
	  	alert("您输入的单价必须不小于"+minPrice.attr('value')+"元");
	  	canSubmit = false;
	    return false;
	}
	if(budgetN_value == '2'){
		minDayBudget =  minDayBudget *1;
		if(budgetDayId.attr('value') < minDayBudget){
			alert("您输入的日预算必须不小于"+minDayBudget+"元");
			canSubmit = false;
			return false;
		}
		if(parseFloat(budgetDayId.attr('value')) > parseFloat(budget)){
			alert("日预算不能大于总预算！");
			canSubmit = false;
			return false;
		}
	}
	//总预算验证
	var sum = actualBalance*1 + creditBalance*1;
	if(budget > sum){
  		alert("您输入的总预算必须不大于账户余额+信用额度余额！");
  		canSubmit = false;
  		return false;
  	}
	if(type.attr('value') != '0' && platform.attr('value') != '0' && category.attr('value') != '0'){
		var error_tag=document.getElementById("name_error12");
		error_tag.style.visibility='hidden';
	}
	if(canSubmit){
		var adName = ondeto_Form('adName','name_error1');
		var price = ondeto_Form('finance_price','name_error2');
		var budget = ondeto_Form('budget','name_error3');
		var title = ondeto_Form('title','name_error4');
		var shortDesc = ondeto_Form('shortDesc','name_error5');
		var longDesc = ondeto_Form('longDesc','name_error6');
		var file_1_upfile = ondeto_Form('file_1_upfile','name_error7');
		var file_2_upfile = ondeto_Form('file_2_upfile','name_error9');
		var file_3_upfile = ondeto_Form('file_3_upfile','name_error10');
		var file_4_upfile = ondeto_Form('file_4_upfile','name_error11');
		
		if(type.attr('value') == '0' && platform.attr('value') == '0' && category.attr('value') == '0'){
			var error_tag=document.getElementById("name_error12");
			error_tag.style.visibility='visible';
			
		}
		if(adName && price &&  budget && title && shortDesc && longDesc && file_1_upfile && file_2_upfile && file_3_upfile && file_4_upfile 
				&& type.attr('value') != '0' && platform.attr('value') != '0' && category.attr('value') != '0'){
			$("#adAdd").attr("action","adAdd.do").submit();
		}else{
			return false;
		}
	}
}
//广告主 广告详情页 表单验证
function check_adv_Modify(){
	
	var type = $('#type');//活动类别
	var platform = $('#platform');
	var category = $('#category');
	var price = $('#finance_price');
//	var point_desc_status = $('#point_desc_status');//付费条件
//	var point_desc = $('#point_desc'); //付费条件 其他
	var budgetN = document.getElementsByName("budgetN");//广告预算
	var budgetDayId = $('#budgetDayId');//预算 详细
	var minPrice = $('#minPrice');
	
	var budget = document.getElementById("budget").value; //总预算  
	var actualBalance = document.getElementById("actualBalance").value;  
	var creditBalance = document.getElementById("creditBalance").value;  
	var minDayBudget = document.getElementById("minDayBudget").value;//最小日预算
	
	var canSubmit = true;
	var budgetN_value;
	for(var i=0;i<budgetN.length;i++){
		if(budgetN[i].type=="radio" && budgetN[i].checked){
			budgetN_value = budgetN[i].value;
		}
	}
	if (price.attr('value') < minPrice.attr('value')){
	  	alert("您输入的单价必须不小于"+minPrice.attr('value')+"元");
	  	canSubmit = false;
	    return false;
	}
	if(budgetN_value == '2'){
		minDayBudget =  minDayBudget *1;
		if(budgetDayId.attr('value') < minDayBudget){
			alert("您输入的日预算必须不小于"+minDayBudget+"元");
			canSubmit = false;
			return false;
		}
		if(parseFloat(budgetDayId.attr('value')) > parseFloat(budget)){
			alert("日预算不能大于总预算！");
			canSubmit = false;
			return false;
		}
	}
	//总预算验证
	var sum = actualBalance*1 + creditBalance*1;
	if(budget > sum){
  		alert("您输入的总预算必须不大于账户余额+信用额度余额！");
  		canSubmit = false;
  		return false;
  	}
	if(type.attr('value') != '0' && platform.attr('value') != '0' && category.attr('value') != '0'){
		var error_tag=document.getElementById("name_error12");
		error_tag.style.visibility='hidden';
	}

	if(canSubmit){
		var adName = ondeto_Form('adName','name_error1');
		var price = ondeto_Form('finance_price','name_error2');
		var budget = ondeto_Form('budget','name_error3');
		var title = ondeto_Form('title','name_error4');
		var shortDesc = ondeto_Form('shortDesc','name_error5');
		var longDesc = ondeto_Form('longDesc','name_error6');
		
		var file_2_upfile =  true;
		var file_3_upfile =  true;
		var file_4_upfile =  true;
	
		if(document.getElementById("file_2_upfile") != null){
			file_2_upfile =  ondeto_Form('file_2_upfile','name_error8');
		}
		if(document.getElementById("file_3_upfile") != null){
			file_3_upfile =  ondeto_Form('file_3_upfile','name_error8');
		}
		if(document.getElementById("file_4_upfile") != null){
			file_4_upfile =  ondeto_Form('file_4_upfile','name_error8');
		}
		if(type.attr('value') == '0' && platform.attr('value') == '0' && category.attr('value') == '0'){
			var error_tag=document.getElementById("name_error12");
			error_tag.style.visibility='visible';
			
		}
		if(adName && price &&  budget && title && shortDesc && longDesc  && file_2_upfile && file_3_upfile && file_4_upfile 
				&& type.attr('value') != '0' && platform.attr('value') != '0' && category.attr('value') != '0'){
			$("#batchForm").attr("action","adDModify.do").submit();
		}else{
			return false;
		}
	}
}

//有返回值的 验证
function ondeto_Form(name_id,name_error){

	var id_tag = document.getElementById(name_id);
	var error_tag = document.getElementById(name_error);
	
	var i = new RegExp(id_tag.getAttribute("reg"));
	var iclas = id_tag.getAttribute("class");
	var flag = 0;

	if(iclas == null){
		flag = 1;
		iclas = id_tag.getAttribute("className");
	}
	if(iclas=="inp-form" || iclas=='inp-form-error'){
			if(!i.test(id_tag.value)){
				error_tag.style.visibility='visible';
				setAtt(id_tag,'inp-form-error',flag);
				
				return false;
			}else{
				error_tag.style.visibility='hidden';
				setAtt(id_tag,'inp-form',flag);
				return true;
			}
	}else if(iclas=="form-textarea" || iclas=='form-textareaerror'){
			if(!i.test(id_tag.value)){
				error_tag.style.visibility='visible';
				setAtt(id_tag,'form-textareaerror',flag);
				
				return false;
			}else{
				error_tag.style.visibility='hidden';
				setAtt(id_tag,'form-textarea',flag);
				return true;
			}
     }else if(name_id=="file_1_upfile"){
			if(!i.test(id_tag.value)){
				id_tag.style.border='1px solid #d90e0e';
				id_tag.style.padding='5px';
				error_tag.style.visibility='visible';
				
				return false;
			}else{
				error_tag.style.visibility='hidden';
				id_tag.style.border='1px solid #acacac';
				id_tag.style.padding='5px';
				return true;
			}
     }else if(name_id=="file_2_upfile"){
			 if(!i.test(id_tag.value)){
					id_tag.style.border='1px solid #d90e0e';
					id_tag.style.padding='5px';
					error_tag.style.visibility='visible';
					
					return false;
			 }else{
				 	error_tag.style.visibility='hidden';
					id_tag.style.border='1px solid #acacac';
					id_tag.style.padding='5px';
					return true;
			 }
     }else if(name_id=="file_3_upfile"){
			 if(!i.test(id_tag.value)){
					id_tag.style.border='1px solid #d90e0e';
					id_tag.style.padding='5px';
					error_tag.style.visibility='visible';
					
					return false;
			 }else{
				 	error_tag.style.visibility='hidden';
					id_tag.style.border='1px solid #acacac';
					id_tag.style.padding='5px';
					return true;
			 }
     }else if(name_id=="file_4_upfile"){
			 if(!i.test(id_tag.value)){
					id_tag.style.border='1px solid #d90e0e';
					id_tag.style.padding='5px';
					error_tag.style.visibility='visible';
					
					return false;
			 }else{
				 	error_tag.style.visibility='hidden';
					id_tag.style.border='1px solid #acacac';
					id_tag.style.padding='5px';
					return true;
			 }
     }
}
//添加收藏夹 兼容FF
function AddFavorite(){
	//收藏夹保存地址
	var sURL = 'http://www.adwalker.cn';
	//收藏夹别名
	var sTitle = '平台首页';
    try{
        window.external.addFavorite(sURL, sTitle);
    }catch (e){
        try{
            window.sidebar.addPanel(sTitle, sURL, "");
        } catch (e){
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}








