

 /**
  * excel表格提交确认
  */
 function checkNull(){
 	var file = document.getElementById("file").value;
 	if(file == ""){
 		document.getElementById("file").focus();
 		return false;
 	}
 	//var flag = confirm("如果有重复的将会被覆盖，确认提交吗？");
 	if(flag){
 		return true;
 	}else{
 		return false;
 	}
 }

 /**
  * excel表格提交确认
  */
 function checkIosNull(){
 	var file = document.getElementById("file_ios").value;
 	if(file == ""){
 		document.getElementById("file_ios").focus();
 		return false;
 	}
 	//var flag = confirm("如果有重复的将会被覆盖，确认提交吗？");
 	if(flag){
 		return true;
 	}else{
 		return false;
 	}
 }
 
 /**
  * 批量审核处理
  */
// function batchDealAudit(status){
//	 if ($("input:checked").length > 0) {
//		var msg = "";
//		var flag = confirm("确认要审核通过吗？");
//		if(flag){
//		
//			$("#s_date").attr("value",status);
//			$("#cam_id").attr("value",status);
//			$("#batchForm").attr("action","manage!aEBatchDealStatus.do").submit();
//		}
//	}else{
//		alert("请选择要操作的数据！");
//		return;
//	}
// }

 
 
 