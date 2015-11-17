/**
 * 开发者收入审核 
 */


 /**
  * 单个审核处理
  */
 function singleDealAudit(id,status,max){
	 
	var confirmAmountDocument = document.getElementById(id+"confirmActivationAmount");//当前的确认数
	var confirmAmount =  confirmAmountDocument.value;
	if(confirmAmount == ""){
		alert("确认激活数不能空！");
		document.getElementById(id+"confirmActivationAmount").focus();
		return;
	}

	if (!/^\d+(\.\d{0,2})?$/.test(confirmAmount)){
		alert("确认激活数输入有误,保留2位小数！");
		document.getElementById(id+"confirmActivationAmount").focus();
		return;
	}
	//运营确认激活数需大于等于广告主第一次确认数
	var firstActivationAmountDocument = document.getElementById(id+"firstActivationAmount");//广告主第一次确认数
	var firstActivationAmount = firstActivationAmountDocument.value;
	
	if(parseFloat(confirmAmount)>parseFloat(max)){
		alert("确认激活数不能大于Max！");
		document.getElementById(id+"confirmActivationAmount").focus();
		return;
	}
	var var_value_max = document.getElementById("var_value_max");  
	if(var_value_max.innerHTML<0){
		alert("可分配余额不足！");
	}
	var msg = "";
	if(status == 1){//通过
		msg = "确认要审核通过吗？";
	}else{//不通过
		msg = "确认要审核不通过吗？";
	}
 	var flag = confirm(msg);
	if(flag){
		//alert(document.getElementById("confirmAmount"));
//		document.getElementById("confirmAmount").value = confirmAmount;
//		document.getElementById("id").value = id;
//		document.getElementById("status").value = status;
		$("#confirmAmount").attr("value",confirmAmount);
		$("#id").attr("value",id);
		$("#status").attr("value",status);
		$("#batchForm").attr("action","manage!devAEDealStatus.do").submit();
	}
 }
 
 /**
  * 批量审核处理
  */
 function batchDealAudit(status){
	 var checkedLength = $("input:checked").length;//被选中的数量
	 if (checkedLength > 0) {
		 var confirmAmounts = new Array();
		 for(var i = 0; i < checkedLength; i++){
			var checkvalue = $("input:checked")[i].value;
			var checkvalues = checkvalue.split("_");
			var confirmAmount = document.getElementById(checkvalues[0]+"confirmActivationAmount").value;
			var totalConfirm = confirmAmount;
			
			if(confirmAmount == ""){
				alert("确认激活数不能空！");
				document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
				return;
			}
			if(!/^\d+(\.\d{0,2})?$/.test(confirmAmount)){
				alert("确认激活数输入有误,保留2位小数");
				document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
				return;
			}
			if(parseFloat(confirmAmount)>parseFloat(checkvalues[2])){
				alert("确认激活数不能大于Max！");
				document.getElementById(id+"confirmActivationAmount").focus();
				return;
			}
			for (var j = 0; j < checkedLength; j++) {
				var checkvalue2 = $("input:checked")[j].value;
			    var checkvalues2 = checkvalue2.split("_");
			    var confirmAmount2 = document.getElementById(checkvalues2[0]+"confirmActivationAmount").value;
			  if(i!=j){
			  	  if(checkvalues[1]==checkvalues2[1]){
				  	totalConfirm = parseFloat(totalConfirm)+parseFloat(confirmAmount2);
				  }
			  }
			}
			if(parseFloat(totalConfirm)>parseFloat(checkvalues[2])){
				alert("单个广告的确认激活数总数不能大于Max数！");
				document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
				return;
			}
			confirmAmounts[i] = confirmAmount;
		 }
		var msg = "";
		if(status == 1){
			msg = "确认要审核通过吗？";
		}else{
			msg = "确认要审核不通过吗？";
		}
		var flag = confirm(msg);
		if(flag){
			//document.getElementById("confirmAmounts").value = confirmAmounts;
			//document.getElementById("status").value = status;
			$("#confirmAmounts").attr("value",confirmAmounts);
			$("#status").attr("value",status);
			$("#batchForm").attr("action","manage!devAEBatchDealStatus.do").submit();
		}
	}else{
		alert("请选择要操作的数据！");
		return;
	}
 }

 
 
 