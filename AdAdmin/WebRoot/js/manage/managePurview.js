
/**
 * 重置密码
 * @param roleId
 * @return
 */
function resetPassInRole(userId){
	var flag = confirm("密码重置后默认是：123456，确认要重置密码吗？");
	if(flag){
		document.getElementById("userId").value = userId;
		$("#baseForm").attr("action",
		"manage!resetPassInRole.do").submit();
	}
}

/**
 * 跳转
 * @param url
 * @return
 */
function href(url){alert(url);
	window.location.href = url+".do";
}

String.prototype.trim = function() { 
return this.replace(/(^\s*)|(\s*$)/g, ""); 
};





