 /**
  * 运营单个审核处理
  */
 function singleDealAuditDevInOper(id,status){
	
	var managerDesc = document.getElementById(id+"description");
	var managerMoney =  document.getElementById(id+"manageMoney").value;//运营确认消耗积分收入
	var forecastIncomeMoney =  document.getElementById(id+"forecastIncomeId").value;//预计消耗积分收入
	var msg = "";
	
	if(status == 1){//通过
		
		if(managerMoney == "" ){
			alert("确认金额格式不对！");
			managerMoney.focus();
			return;
		}else{
			
			managerMoney = $.trim(managerMoney);
			forecastIncomeMoney = $.trim(forecastIncomeMoney);
			managerMoney = parseFloat(managerMoney);
			forecastIncomeMoney = parseFloat(forecastIncomeMoney); 
			
			if(!/^\d+(\.\d{0,4})?$/.test(managerMoney)){
				alert("确认消耗积分收入输入有误！");
				document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
				return;
			}
			if(!/^\d+(\.\d{0,2})?$/.test(managerMoney)){
				alert("确认激活数输入有误,保留2位小数");
				document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
				return;
			}
			if(managerMoney > forecastIncomeMoney){
				alert("确认消耗积分收入不能大于预计消耗积分收入！");
				managerMoney.focus();
				return;
			}
		}
//		if(managerMoney.value != forecastIncomeMoney.value){//如果运营确认消耗积分收入与预计消耗积分收入不等，则运营说明不能空
//			if(managerDesc.value == ""){//运营说明必填
//				alert("说明不能空！");
//				managerDesc.focus();
//				return;
//			}
//		}
		msg = "确认要审核通过吗？";
	}
 	var flag = confirm(msg);
 	
	if(flag){	
		document.getElementById("manageMoney").value = managerMoney;
		//document.getElementById("description").value = managerDesc.value;
		document.getElementById("id").value = id;
		document.getElementById("confirm_status").value = status;
		$("#batchForm").attr("action","manage!updateSingleDevConsume.do").submit();
		
	}
 }
 
 /**
  * 运营批量审核处理
  */
 function batchDealAuditDevInOper(status){
	 var checkedLength = $("input:checked").length;//被选中的数量
	 if (checkedLength > 0) {
		var msg = "";
		var mMoneys = new Array();//存放金额数组
		//var mDescs = new Array()//存放说明数组

		if(status == 1){//只有审核通过时才对运营金额做验证
			 for(var i = 0; i < checkedLength; i++){
				var id = $("input:checked")[i].value;
				var mMoney = document.getElementById(id+"manageMoney").value;//运营确认金额
				var forecastIncomeMoney =  document.getElementById(id+"forecastIncomeId").value;//预计消耗积分收入
				//var mDesc = document.getElementById(id+"description").value;//运营说明
				if(mMoney == "" ){//不能为空或者为0
					alert("运营确认金额格式不对！");
					document.getElementById(id+"manageMoney").focus();
					return;
				}else{	
					
					mMoney = $.trim(mMoney);
					forecastIncomeMoney = $.trim(forecastIncomeMoney);
					mMoney = parseFloat(mMoney);
					forecastIncomeMoney = parseFloat(forecastIncomeMoney); 
					
					if(!/^\d+(\.\d{0,4})?$/.test(mMoney)){
						alert("确认消耗积分收入输入有误！");
						document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
						return;
					}
					if(!/^\d+(\.\d{0,2})?$/.test(mMoney)){
						alert("确认激活数输入有误,保留2位小数");
						document.getElementById(checkvalues[0]+"confirmActivationAmount").focus();
						return;
					}
					if(mMoney > forecastIncomeMoney){						
						alert("确认消耗积分收入不能大于预计消耗积分收入！");
						document.getElementById(id+"manageMoney").focus();
						return;
					}
				}
//			if(mMoney != forecastIncomeMoney){//如果运营确认消耗积分收入与预计消耗积分收入不等，则运营说明不能空
//				if(mDesc == ""){//运营说明必填
//						alert("说明不能空！");
//				document.getElementById(id+"description").focus();
//				return;
//				}
//				}
			//mDescs[i] = mDesc;
			mMoneys[i] = mMoney;
			 }
			msg = "确认要审核通过吗？";
	}
			else{//不通过
		    msg = "确认要审核不通过吗？";
		}
		var flag = confirm(msg);
		
		if(flag){
			document.getElementById("manageMoneys").value = mMoneys;
			//document.getElementById("descriptions").value = mDescs;
			document.getElementById("confirm_status").value = status;
			$("#batchForm").attr("action","manage!updateBatchDevConsume.do").submit();
			
		}
	}else{
		alert("请选择要操作的数据！");
		return;
	}
 }
 
$(function() {
	//网站主积分消耗财务审核列表下载
		$("#IntegralConsumeDownloadFinance").click(
				function() {
					$("#WebsiteMainIntegralConsumeDetail").attr("action",
							"manage!devDownloadIntegralConsumeList.do").submit();
				});
	 
 });
$(function() {
	//网站主积分消耗财务审核列表查询
		$("#IntegralConsumeSelect").click(
				function() {
					$("#WebsiteMainIntegralConsumeDetail").attr("action",
							"manage!WebsiteMainIntegralConsumeDetailSelect.do").submit();
				});
	 
 });
