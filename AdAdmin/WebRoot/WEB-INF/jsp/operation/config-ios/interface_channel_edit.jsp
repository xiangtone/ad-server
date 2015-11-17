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
	
				<form name="colloca" id="colloca" method="post" action="manage!collocationChannel.do">
				
				<input name="channel" type="hidden" id="channel_id" maxlength="20"  value="${vo.channel}" />
				<fieldset class="search_fieldset" >
				<legend>配置信息 </legend>
					<table width="100%" >
						<tr>
							<td align="right" width="20%">渠道编号 ：</td>
							<td  >
								<input type="text" name="channel" id="channel"  value="${vo.channel}"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">渠道名称 ：</td>
							<td  >
								<input type="text" name="channel_name" id="channel_name"  value="${vo.channel_name}"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">应用变量名 ：</td>
							<td  >
								<input type="text" name="adid_para" id="adid_para"  value="${vo.adid_para}"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">mac变量名 ：</td>
							<td  >
								<input type="text" name="deviceid_para" id=deviceid_para  value="${vo.deviceid_para}"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">时间变量 ：</td>
							<td  >
								<input type="text" name="time_para" id="time_para"  value="${vo.time_para}"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">IP变量名 ：</td>
							<td >
								<input type="text" name="client_ip" id="client_ip"  value="${vo.client_ip}"/>
							</td>
						</tr>
						
						<tr>
							<td align="right">激活回调URL ：</td>
							<td >
								<input type="text" name="url" id="url"  value="${vo.url}" size="80" />
							</td>
						</tr>
						
						
					</table>
					</fieldset>
				</form>
			
</body>
</html>