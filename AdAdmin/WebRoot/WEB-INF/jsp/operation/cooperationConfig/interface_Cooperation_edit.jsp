<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control"  content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分接口编辑</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"	charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		if(vaildateForm("colloca")){
			$("#colloca").ajaxSubmit(function(data) {
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
				<form name="colloca" id="colloca" method="post" action="config!saveConfig.do">
				<input name="app_id" type="hidden" id="app_id" maxlength="20"  value="${vo.app_id}" />
				<fieldset class="search_fieldset" >
				<legend>配置信息 </legend>
					<table width="100%" >
						<tr>
							<td align="right" width="25%">接口URL ：</td>
							<td>
								<input type="text" name="response_url" size="80" id="response_url"  value="${vo.response_url}"/>
							</td>
						</tr>
					</table>
					</fieldset>
				</form>
</body>
</html>