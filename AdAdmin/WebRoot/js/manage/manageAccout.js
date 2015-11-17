/**
 * 根据id删除
 * 
 * @param id
 * @return
 */
function deleteById(id) {
	if (id == "") {
		return;
	} else {
		var flag = confirm("确认要删除吗？");
		if (flag) {
			document.getElementById("id").value = id;
			$("#baseForm").attr("action", "manage!deleteManageById.do")
					.submit();
		}
	}
}

/**
 * 跳转到修改密码页面
 * 
 * @param id
 * @param userName
 * @return
 */
function toModifyPasswordPage(id) {

	var flag = confirm("确认要修改密码吗？");
	if (flag) {
		window.location.href = "manage!toModifyPass.do?id=" + id;
	}
}

/**
 * 密码修改表单提交验证
 * 
 * @return
 */
function submitModifyPass() {
	var passWord = document.getElementById("passWord").value;
	var newPassWord = document.getElementById("newPassWord").value;
	var repeatNewPassWord = document.getElementById("repeatNewPassWord").value;

	if (passWord.trim() == "") {
		document.getElementById("passWord").focus();
		return false;
	} else if (newPassWord.trim() == "") {
		document.getElementById("newPassWord").focus();
		return false;
	} else if (repeatNewPassWord.trim() == "") {
		document.getElementById("repeatNewPassWord").focus();
		return false;
	} else {
		return true;
	}
}
/**
 * 重置密码
 * 
 * @param id
 * @return
 */
function reSetPassword(id) {
	var flag = confirm("密码重置后默认是：123456，确认要重置密码吗？");
	if (flag) {
		document.getElementById("id").value = id;
		$("#baseForm").attr("action", "manage!resetManagerPass.do").submit();
	}
}
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
