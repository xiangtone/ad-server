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
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right" >
				<form name="colloca" id="colloca" method="post" action="manage!collocationIos.do">
				<input name="placement_id" type="hidden" id="placement_id" maxlength="20"  value="${placement_id}" />
				<!-- input name="id" type="hidden" id="id" maxlength="20"  value="${vo.id}" /-->
					<fieldset class="search_fieldset" style="width: 400px;">
					<legend>配置信息 </legend>
					<table width="500px;">
					    <tr>
							<td width="23%" height="30" align="right">广告主键 ：</td>
							<td width="77%" align="left" class="id">
								<input type="text" name="id" id="id" reg="[a-zA-Z0-9]+" tip="广告主键 不能为空!"  maxlength="100" value="${configid}" />
							</td>
						</tr>
						<!-- 
						<tr>
							<td width="23%" height="30" align="right">广告主键 ：</td>
							<td width="77%" align="left" class="ad_key">
								<input type="text" name="ad_key" id="ad_key" reg="^([a-zA-Z0-9]|[-._]){4,19}$" tip="广告主键 不能为空!"  maxlength="100" value="${vo.ad_key}"/>
							</td>
						</tr>
						 
						<tr>
							<td width="23%" height="30" align="right">活动名称 ：</td>
							<td width="77%" align="left" class="ad_name">
								<input type="text" name="ad_name" id="ad_name" maxlength="100" value="${vo.ad_name}" reg="^[\u4e00-\u9fa5\w\W]{4,19}$"  tip="亲，活动名称为4-19个字符!"/>
							</td>
						</tr>
						<tr>
							<td align="right">URL地址：</td>
							<td class="url" align="left"><input
								name="url" type="text" id="url"
								maxlength="150"  value="${vo.url}"/></td>
						</tr>
						<tr>
							<td align="right">广告主键变量名：</td>
							<td align="left" class="adid_str"><input type="text"
								name="adid_str" id="adid_str"  maxlength="50" value="${vo.adid_str}"/></td>
						</tr>
						<tr>
							<td align="right">MAC变量名：</td>
							<td class="deviceid_para" align="left"><input
								name="deviceid_para" type="text" id="deviceid_para"
								maxlength="50" value="${vo.deviceid_para}"/></td>
						</tr>


						<tr>
							<td align="right">附带参数：</td>
							<td class="sourse_str" align="left"><input type="text"
								name="sourse_str" id="sourse_str"  maxlength="100" value="${vo.sourse_str}"/>
							</td>
						</tr>
						<tr>
							<td align="right">激活时间变量名：</td>
							<td align="left" class="eventtime_para"><input type="text"
								name="eventtime_para" id="eventtime_para"  maxlength="50" value="${vo.eventtime_para}"/></td>
						</tr>
						<tr>
							<td align="right">发送方式（GET/POST）：</td>
							<td class="send_type" align="left"><input type="text" name="send_type"
								id="send_type" maxlength="20" value="${vo.send_type}"/>
							</td>
						</tr>
						<tr>
							<td align="right">平台标示：</td>
							<td class="source" align="left"><input type="text"
								name="source" id="source" 
								maxlength="30" value="${vo.source}"/>
							</td>
						</tr>
						<tr>
							<td align="right">客户端IP：</td>
							<td class="client_ip" align="left"><input type="text"
								name="client_ip" id="client_ip" maxlength="100" value="${vo.client_ip}"/>
							</td>
						</tr>
						<tr>
							<td align="right">OPENUDID：</td>
							<td class="client_ip" align="left"><input type="text"
								name="client_ip" id="client_ip" maxlength="100" value="${vo.client_ip}"/>
							</td>
						</tr>
						<tr>
							<td align="right">IDFA：</td>
							<td class="client_ip" align="left"><input type="text"
								name="client_ip" id="client_ip" maxlength="100" value="${vo.client_ip}"/>
							</td>
						</tr>
						<tr>
							<td align="right">服务名：</td>
							<td class="client_ip" align="left"><input type="text"
								name="client_ip" id="client_ip" maxlength="100" value="${vo.client_ip}"/>
							</td>
						</tr>
						-->
					</table>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>