/**
 * 开发者收入审核 
 */


 /**
  * 单个下载结算处理
  */
 function singleDealAudit(id,type){
	var msg = "";
		msg = "确认要结算吗？";
 	var flag = confirm(msg);
	if(flag){
		$("#id").attr("value",id);
		$("#batchForm").attr("action","manage!financeIncomeStatus.do?type="+type).submit();
	}
 }
 

 
 /**
  * 批量审核处理
  */
 function batchDealAudit(){
	 var checkedLength = $("input:checked").length;//被选中的数量
	 if (checkedLength > 0) {
			msg = "确认要结算选中项吗？";
		var flag = confirm(msg);
		if(flag){
			$("#batchForm").attr("action","manage!financeBatchIncome.do").submit();
		}
	}else{
		alert("请选择要操作的数据！");
		return;
	}
 }
 