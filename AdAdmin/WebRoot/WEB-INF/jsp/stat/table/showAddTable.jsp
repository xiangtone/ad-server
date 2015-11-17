<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告单价设置</title>
<link href="${pageContext.request.contextPath}/css/main.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script language="javascript">

 var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		if(vaildateForm("editFor")){
			$("#editFor").ajaxSubmit(function(data) {
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						alert("操作失败"+dataObj.error);
						dg.curWin.refresh();
					}else{
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
				}else{
					alert("操作失败，请重试！！");
					dg.curWin.refresh();
				}

			});
		}
	});
 
</script>	
</head>

<body>
	<div class="main">

		<div class="content clearfix">
				<div class="content_right content_new">				
    <form name="editFor" id="editFor" action="manage!addTable.do" method="post" >
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#999999" class="style_table">
        <!--编辑部分 -->
        <tr><td>页面显示名称:<font color="red">*</font></td><td><input name="jspName" id="jspName" value="${data.jspName}" size="100"/> <font color="red">${wrong_message.jspName_error}</font></td></tr>
        <tr><td>菜单显示名称:</td><td><input name="menuName" id="menuName" value="${data.menuName}" size="100"><font color="red">${wrong_message.menuName_error}</font></td></tr>
        <tr><td>请求URL:</td><td><input name="menuRequest" id="menuRequest" value="${data.menuRequest}" size="100"><font color="red">${wrong_message.menuName_error}</font></td></tr>
        <tr><td>查看角色:<font color="red">*</font></td><td><input name="role" id="role" value="${data.role}"><font color="red">${wrong_message.role_error}</font></td></tr>
        <tr><td>数据源SQL:<font color="red">*</font></td><td><textarea name="sql" id="sql" cols ="100" rows = "5" >${data.originSql}</textarea><font color="red">${wrong_message.sql_error}</font></td></tr>
        <tr><td>总和SQL:</td><td><textarea name="totalsql" id="totalsql" cols ="100" rows = "5" >${data.totalSql}</textarea></td></tr>
        <tr><td>分页SQL:</td><td><textarea name="countsql" id="countsql" cols ="100" rows = "5" >${data.countSql}</textarea></td></tr>    
        <tr><td>分页大小:<font color="red">*</font></td><td><input name="pageSize" id="pageSize" value="${data.pageSize}"><font color="red">${wrong_message.pageSize_error}</font></td></tr>
        <tr><td>起始日期:</td><td><input name="defaultStart" id="defaultStart" value="${data.defaultStart}"> <font color="red">${wrong_message.defaultStart_error}</font></td></tr>
        <tr><td>结束日期:</td><td><input name="defaultEnd" id="defaultEnd" value="${data.defaultEnd}"> <font color="red">${wrong_message.defaultEnd_error}</font></td></tr>
        <tr><td>是否启用:</td><td><select id="status" name="status"><option value="1" <c:if test="${data.status==1}">selected</c:if>>启用</option>
        <option value="9" <c:if test="${data.status==9}">selected</c:if>>弃用</option>
        </select></td></tr>
        <tr><td>附注:</td><td><input name="note" id="note" value="${data.note}" size="100"></td></tr>
        </tr>  
        </table>
    </form>
				
				</div>
			</div>
		</div>
</body>

</html>
