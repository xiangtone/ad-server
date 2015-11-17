/**
 * 表单提交相关
 * 
 * @author zhaozengbin
 */
// 根据submitID分开提交
$(function() {
	$("#mangguoDetailSearch").click(
			function() {
				$("#searchType").attr("value",2);
				$("#mangguoDetailForm").attr("action",
						"manage!mangguoDetailList.do").submit();
			});
	// 网站主收入审核明细
	$("#devEffectManageSearch").click(
			function() {
				$("#devEffectManageForm").attr("action",
						"manage!financeIncomeConfirmlist.do").submit();
			});
	// 网站主收入审核明细下载
	//lc
	$("#financeDownloadIncome").click(
			function() {
				$("#devEffectManageForm").attr("action",
						"manage!financeIncomelistDownload.do").submit();
			});
	
	//结算数据录入
	$("#confirm").click(
			function() {
				$("#campaignConfirm").attr("action",
						"manage!operationCampaignConfirmList.do").submit();
			});
	// 结算数据录入下载
	//lc
	$("#confirmDown").click(   
			function() {
				$("#campaignConfirm").attr("action",
						"manage!CampaignConfirmListDown.do").submit();
			});
	//业绩报表
	$("#achievementReport").click(
			function() {
				$("#achievement").attr("action",
				"manage!achievementReportList.do").submit();
			});
	// 业绩报表下载
	//lc
	$("#achievementDown").click(   
			function() {
				$("#achievement").attr("action",
				"manage!achievementReportListDown.do").submit();
			});
	
	// 运营开发者查询收入审核
	//lc
	
	$("#devDownloadInquire").click(
			function() {
				$("#listDevAdEffect").attr("action",
						"manage!listDevAdEffect.do").submit();
			});
	
	// 运营开发者查询收入审核下载
	//lc
	$("#devDownloadIncome").click(
			function() {
				$("#listDevAdEffect").attr("action",
						"manage!devDownloadIncomeManage.do").submit();
			});
	// 运营开发者账号管理列表下载
	//lc
	// 运营开发者账号管理列表下载
	//lc
	
	// 开发者广告查询列表
	$("#devAdStatSearch").click(function() {
		$("#devAdStatList").attr("action", "devAdStatList.do").submit();
	});
	// 开发者换量
	$("#devAdStatSearch_yibi").click(function() {
		$("#devAdStatList_yibi").attr("action", "devIncomeYiBiStat.do").submit();
	});
	// 开发者换量  导出
	$("#devAdStatDownload_yibi").click(function() {
		$("#devAdStatList_yibi").attr("action", "devIncomeYiBiStatExl.do").submit();
	});
	
	// 开发者收入明细查询列表
	$("#devIncomeDetailsSearch").click(function() {
		$("#devIncomeDetails").attr("action", "devIncomeDetails.do").submit();
	});
	// 开发者收入明细查询列表
	$("#devIncomeDetailsAndVConsumeSearch").click(function() {
		$("#devIncomeDetailsAndVConsume").attr("action", "DevIncomeAndVConsumeDetail.do").submit();
	});
	// 开发者广广告单价查询
	$("#devAdPriceSearch").click(function() {
		var value = $("#searchValue").attr("value");
		value = $.trim(value);
		if(value !==""&& value!=null){
			$("#devAdPrice").attr("action", "devAdPrice.do").submit();
		}
	});
	
	// 开发者广告查询列表下载
	$("#devAdStatDownload").click(
			function() {
				var isNull = document.getElementById("isNull").value;
				if(isNull == 1){
					$("#devAdStatList").attr("action", "devAdStatListDownload.do")
						.submit();
				}
				if(isNull == 0){
					alert('当前没有可导出数据!');
				}
			});
	// 开发者应用查询列表
	$("#devAppStatSearch").click(function() {
		$("#devAppStatList").attr("action", "devAppStatList.do").submit();
	});
	// 开发者应用查询列表下载
	$("#devAppStatDownload").click(
			function() {
				var isNull = document.getElementById("isNull").value;
				if(isNull == 1){
					$("#devAppStatList")
						.attr("action", "devAppStatListDownload.do").submit();
				}
				if(isNull == 0){
					alert('当前没有可导出数据!');
				}
			});
	// 开发者应渠道询列表
	$("#devChannelStatSearch").click(
			function() {
				$("#devChannelStatList")
						.attr("action", "devChannelStatList.do").submit();
			});
	// 开发者应渠道询下载
	$("#devChannelStatDownload").click(
			function() {
				var isNull = document.getElementById("isNull").value;
				if(isNull == 1){
					$("#devChannelStatList").attr("action",
						"devChannelStatListDownload.do").submit();
				}
				if(isNull == 0){
					alert('当前没有可导出数据!');
				}
			});

	// 运营开发者应用查询列表
	$("#devAppManage").click(
			function() {
				$("#devAppStatListManage").attr("action",
						"manage!devAppStatListManage.do").submit();
			});
	// 运营开发者应用查询列表下载
	$("#devAppManageDown").click(
			function() {
				$("#devAppStatListManage").attr("action",
						"manage!devAppStatListDownloadManage.do").submit();
			});
	// 运营开发者广告查询列表
	$("#devAdManage").click(
			function() {
				$("#devAdStatListManage").attr("action",
						"manage!devAdStatListManage.do").submit();
			});
	$("#channelsSearch").click(
			function() {
				$("#channelsIndexPage").attr("action",
						"channelsIndexPage1.do").submit();
			});
	
	$("#devPresentManage").click(
	 	    function() {
				$("#devPresentStatListManage").attr("action",
						"manage!devRewardList.do").submit();
			});
	$("#setActivity").click(
			function() {
				$("#setActivityManage").attr("action",
				"manage!updateActivityData.do").submit();
			});
	// 运营开发者广告查询列表下载
	$("#devAdManageDown").click(
			function() {
				$("#devAdStatListManage").attr("action",
						"manage!devAdStatListDownloadManage.do").submit();
			});
	$("#channelsDown").click(
			function() {
				$("#channelsIndexPage").attr("action",
				"channelsStatListDownloadManage1.do").submit();
			});

	// 运营后台-广告主查询列表
	$("#adverManageSearch").click(
			function() {
				$("#opeAdverStatList").attr("action",
						"manage!opeAdverStatList.do").submit();
			});
	// 运营后台-广告主查询列表下载
	$("#adverManageDown").click(
			function() {
				$("#opeAdverStatList").attr("action",
						"manage!opeAdverStatListDownload.do").submit();
			});
	// 运营后台-开发者渠道报表查询
	$("#DevChannelStatSearch").click(
			function() {
				$("#ReportOpeDevChannelStatType").attr("value",2);
				$("#opeDevChannelStatList").attr("action",
						"manage!opeDevChannelStatList.do").submit();
			});
	// 运营后台-开发者渠道报表下载
	$("#DevChannelStatDown").click(
			function() {
				$("#opeDevChannelStatList").attr("action",
						"manage!opeDevChannelStatListDownload.do").submit();
			});
	// 手机广告下载管理 查询列表
	$("#manage_adverManageSearch").click(
			function() {
				
				$("#wap_AdverStatList").attr("action",
						"manage!listAllWapAd.do").submit();
			});
	// 手机广告下载管理 -报表下载
	$("#manage_adverManageDown").click(
			function() {
				$("#wap_AdverStatList").attr("action",
						"manage!listAllWapAd_reportDown.do").submit();
			});
	// 网站主广告效果下载明细 查询列表
	$("#finance_devmonDetail_Search").click(
			function() {
				var statDateStartTime = $('#statDateStartTime').val();
				var statDateEndTime = $('#statDateEndTime').val();
				var settleStartTime = $('#settleStartTime').val();
				var settleEndTime= $('#settleEndTime').val();
				
				if(statDateStartTime == "" && statDateEndTime !=""  ){
					alert("请选择效果发生开始时间");
				}else if(statDateStartTime != "" && statDateEndTime ==""){
					alert("请选择效果发生结束时间");
				}else if(settleStartTime != "" && settleEndTime ==""){
					alert("请选择结算结束时间");
				}else if(settleStartTime == "" && settleEndTime !=""){
					alert("请选择结算开始时间");
				}else if(statDateStartTime > statDateEndTime){
					alert("效果开始时间必须小于结束时间");
				}else if(settleStartTime > settleEndTime){
					alert("结算开始时间必须小于结束时间");
				}else{
					$("#devmonDetail_Form").attr("action",
						"manage!AdvListDevmonDetailFindAll.do").submit();
				}
	});
	
	
	
	
	
	
	
	// 网站主广告效果下载明细 -报表下载 
	$("#finance_devmonDetail_Report").click(
			function() {
				$("#devmonDetail_Form").attr("action",
						"manage!AdvListDevmonDetailFindAllForReport.do").submit();
			});
	
	// 广告主消费明细  查询列表
	$("#conSumeDetail_Search").click(
			function() {
					$("#conSumeDetail_Form").attr("action",
						"manage!AdvConsumeDetailList.do").submit();
			});
	// 广告主消费明细  数据导出
	$("#conSumeDetail_Report").click(
			function() {
				$("#conSumeDetail_Form").attr("action",
						"manage!AdvConsumeDetailDownloadList.do").submit();
			});
	
	
	
	
	
	$("#sendmail").click(
			function() {
				var content = tinyMCE.get('content').getContent();
				$("#tip").attr("value",content);
				$("#saveMailsNoticetag").attr("action",
						"manage!saveMailsNotice.do").submit();
			}
			);
//	$("#sendmailtest").click(function() {
//		alert(11);
//		var mails = $("#ceshimail").attr("value");
//		document.getElementById('mailtest').value = mails;
//		$("#mailtest").attr("value",mails);
////		$("#saveMailsNoticetag").attr("action",
////				"manage!sendMailsNotice.do").submit();
//		
//		
//    	      //options是一个ajaxForm的配置对象。
//
//    			var options = {
//    			
//    			 //dataType:'script',
//    			 contentType: "application/x-www-form-urlencoded; charset=utf-8",
//    			 success: function(responseText, statusText){ 
//    				alert(responseText);
//    			 } //显示操作提示
//    			 };
//    			// bind form using 'ajaxForm' 
//    			$('#saveMailsNoticetag').ajaxForm(options);
//    			$('#saveMailsNoticetag').bind('form-pre-serialize', function(event, form, options, veto) {
//    				tinyMCE.triggerSave();
//    			});
//    			 alert(3);
//    			 $("#saveMailsNoticetag").attr("action",
//					"manage!sendMailsNotice.do").submit();
//	});
})
// 排序
function order(orderColumn, formId) {
	$("#orderColumn").val(orderColumn);
	if ($("#orderCondition").val() == 'desc') {
		$("#orderCondition").val("asc");
	} else {
		$("#orderCondition").val("desc");
	}
	$("#" + formId).submit();
}
//排序
function order(orderColumn, formId,searchType) {
	$("#orderColumn").val(orderColumn);
	if ($("#orderCondition").val() == 'desc') {
		$("#orderCondition").val("asc");
	} else {
		$("#orderCondition").val("desc");
	}
	$("#"+searchType).attr("value",2);
	$("#" + formId).submit();
}
//排序
function orderforchannel(orderColumn, formId,searchType) {
	$("#orderColumn").val(orderColumn);
	if ($("#orderCondition").val() == 'desc') {
		$("#orderCondition").val("asc");
	} else {
		$("#orderCondition").val("desc");
	}
	$("#"+searchType).attr("value",2);
	$("#channelsIndexPage").attr("action",
	"channelsIndexPage1.do").submit();
}


// 检查金额
function checkMoney(val, minMoney) {
	if (val != null) {
		var money = $("#applyMoneySussess").attr("value");
		if (val > 0 && money != '' && money > 0) {
			var reg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;
			if(!reg.test(val)){
				alert("提款金额只支持两位小数！");
				$("#applyMoney").attr("value", parseFloat(money.substring(0, money.indexOf(".") + 3)));
			}
			if (parseFloat(money.substring(0, money.indexOf(".") + 3)) < val) {
				alert("您无法申请高于已确认金额的钱数！");
				$("#applyMoney").attr("value", parseFloat(money.substring(0, money.indexOf(".") + 3)));
			}
			if (val < parseFloat(minMoney.substring(0, minMoney.indexOf(".") + 3)) && val != null
					|| money < parseFloat(minMoney.substring(0, minMoney.indexOf(".") + 3))) {
				$("#submit").attr("disabled", true);
			} else {
				$("#submit").attr("disabled", false);
			}
		} else {
			if (money == '' || money == 0) {
				alert("您可能还没有可提现的金额！");
			}
			if (val < 0) {
				alert("请输入不小于零合法数字！");
			}
			$("#applyMoney").attr("value", '');
		}
	}
}
// APP跳转
function jumpApp( val) {
	if (val != null) {
		if (val != 0) {
			window.location.href =  "toUpdateDevApp.do?id=" + val;
		}
	}
}
// 检测是否勾选同意协议
function onAgreement(agreement) {
	if (agreement.checked == true) {
		document.getElementById('submit').disabled = false;
	}
	if (agreement.checked == false) {
		document.getElementById('submit').disabled = true;
	}
}
// 包变 单个应用和综合查询
function changeStatRange(val) {
	if (val == 1) {
		var nodes = document.getElementById("developerAppId").childNodes;
		for ( var i = 0; i < nodes.length; i++) {
			if (nodes[i].id == "default") {
				nodes[i].selected = "selected";
			}
		}
		document.getElementById("developerAppId").disabled = true;
		document.getElementById("is_Click_flag_developerAppId").value = "0";
	} else {
		document.getElementById("developerAppId").disabled = false;
		document.getElementById("is_Click_flag_developerAppId").value = "1";
	}
}
//包变 单个应用和综合查询
function changeChannelRange(val) {
	if (val == 1) {
		document.getElementById("channel").disabled = true;
	} else {
		document.getElementById("channel").disabled = false;
	}
}



function saveConfirmTemp(id,confirm_activation_temp) {
	var var_value_max = document.getElementById("var_value_max");  
	var sum_confirmActivationAmount = document.getElementById("sum_confirmActivationAmount"); 
	var confirm =  document.getElementById(id+"confirmActivationAmount"); 
	var hidden =  document.getElementById(id+"hidden"); 
	var temp = (confirm.value*10000-hidden.value*10000)/10000;
	var var_temp = (parseInt(var_value_max.innerHTML*10000-temp*10000, 0))/10000;
	if(var_temp<0){
		 alert("可分配余额不足！");
		 confirm.value = hidden.value;
		return;
	}
	var doubleReg = /^[0-9]+$/;//整数
	if(!doubleReg.test(confirm.value)){
		alert("确认激活数输入不合法！");
		 confirm.value = hidden.value;
		return;
	}
	var_value_max.innerHTML = var_temp;
	hidden.value = confirm.value;
	sum_confirmActivationAmount.innerHTML = (parseInt(sum_confirmActivationAmount.innerHTML*10000+temp*10000, 0))/10000;
	var url="manage!saveConfirmTemp.do?id="+id+"&temp="+confirm.value;
	if(id !="" && id != 0){
		$.get(url, function(msg){
			alert("ok");
		});
	}
}



//  运营开发者收入审核list下载
$("#devIncomeAuditListDownload").click(
		function() {
			$("#devIncomeAudit").attr("action",
					"manage!operationDevIncomeAuditListDownload.do").submit();
		});


