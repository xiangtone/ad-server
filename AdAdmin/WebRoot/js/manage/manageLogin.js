/**
 * 管理员登录
 * @return
 **
 */
function toLogin(){
	if (document.getElementById("userName").value.trim() == "") {
		document.getElementById("userName").focus();
		return false;
	} 
	if (document.getElementById("passWord").value.trim() == "") {
		document.getElementById("passWord").focus();
		return false;
	}
}
String.prototype.trim = function(){ 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}; 

	
	
	
	
	
	
	