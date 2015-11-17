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
<title>运营管理后台ios配置添加</title>
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
	dg.addBtn("save", "发送点击", function() {
		if(vaildateForm("colloca")){
			$("#colloca").ajaxSubmit(function(data) {
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.success=='true'){
						alert("操作成功！！");
					}else if(dataObj.sucess=='false'){
						alert("操作失败"+dataObj.message);
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}

			});
		}
	});
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right" >
				<form name="colloca" id="colloca" method="post" action="manage!sendClickInfo.do">
					<fieldset class="search_fieldset" style="width: 400px;">
					<legend>发送信息 </legend>
					<table width="500px;">
						<tr>
							<td width="23%" height="30" align="right">广告id ：</td>
							<td width="77%" align="left" class="ad_key">
								<input type="text" name="id" id="id"  maxlength="100" value="${vo.id}"/>*
							</td>
						</tr>
						<tr>
							<td width="23%" height="30" align="right">MAC值 ：</td>
							<td width="77%" align="left" class="mac">
								<input type="text" name="mac" id="mac" maxlength="100" value="${vo.mac}"/>
							</td>
						</tr>
						<tr>
							<td align="right">IP地址：</td>
							<td class="client_ip" align="left">
							<input name="client_ip" type="text" id="client_ip" maxlength="100"  value="${vo.client_ip}"/></td>
						</tr>
						<tr>
							<td align="right">OPENUDID值：</td>
							<td align="left" class="openudid">
							  <input type="text" name="openudid" id="openudid"  maxlength="50" value="${vo.openudid}"/></td>
						</tr>
						<tr>
							<td align="right">IDFA值：</td>
							<td class="idfa" align="left"><input name="idfa" type="text" id="idfa" maxlength="100" value="${vo.idfa}"/></td>
						</tr>
						<tr>
							<td align="right">IDFV值：</td>
							<td class="idfv" align="left">
							    <input type="text" name="idfv" id="idfv"  maxlength="100" value="${vo.idfv}"/>
							</td>
						</tr>
						<tr>
							<td align="right">渠道名称：</td>
							<td align="left" class="channel">
								<input type="text" name="channel" id="channel"  maxlength="100" value="${vo.channel}"/>*
							</td>
						</tr>
					</table>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>