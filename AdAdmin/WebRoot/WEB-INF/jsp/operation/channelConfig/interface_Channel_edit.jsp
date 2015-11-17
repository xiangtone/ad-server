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
<title>渠道接口配置编辑</title>
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
				<form name="colloca" id="colloca" method="post" action="config!saveChannelConfig.do">
				<input name="channel" type="hidden" id="channel" maxlength="20"  value="${vo.channel}" />
				<fieldset class="search_fieldset" >
				<legend>配置信息 </legend>
					<table width="100%" >
						<tr>
							<td align="right" width="25%">渠道名称 ：</td>
							<td>
								<input type="text" name="channel_name" id="channel_name"  value="${vo.channel_name}"/>
							</td>
						</tr>
						<tr>
							<td align="right" width="25%">渠道账号 ：</td>
							<td>
								<select name="channel_id" style="width: 155px;">
									<option value="">请选择</option>					
									<c:forEach items="${clist}" var="entity">
										<option value="${entity.id}" <c:if test="${vo.channel_id eq entity.id}"> selected="selected" </c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">渠道接口 ：</td>
							<td>
								<input type="text"  size="70"  name="url" id="url"  value="${vo.url}"/>
							</td>
						</tr>
						<tr>
							<td align="right">广告id参数名 ：</td>
							<td>
								<input type="text"  name="adid_para" id="adid_para"  value="${vo.adid_para}"/>
							</td>
						</tr>
						<tr>
							<td align="right">MAC参数名称 ：</td>
							<td>
								<input type="text" name="deviceid_para" id=deviceid_para  value="${vo.deviceid_para}"/>
							</td>
						</tr>
						<tr>
							<td align="right">时间戳参数名 ：</td>
							<td>
								<input type="text" name="time_para" id=time_para  value="${vo.time_para}"/>
							</td>
						</tr>
						<tr>
							<td align="right">IP参数名 ：</td>
							<td>
								<input type="text" name="client_ip" id=client_ip  value="${vo.client_ip}"/>
							</td>
						</tr>
						<tr>
							<td align="right">OPENUDID参数名 ：</td>
							<td>
								<input type="text" size="50" name="openudid" id="openudid"  value="${vo.openudid}"/>
							</td>
						</tr>
						<tr>
							<td align="right">IDFA参数名 ：</td>
							<td >
								<input type="text"  size="50" name="idfa" id="idfa"  value="${vo.idfa}" />
							</td>
						</tr>
						
						<tr>
							<td align="right">IDFV参数名 ：</td>
							<td >
								<input type="text" name="idfv" id="idfv"  value="${vo.idfv}" />
							</td>
						</tr>
						<tr>
							<td align="right">服务名称 ：</td>
							<td>
								<input type="text" name="service_name" id="service_name"  value="${vo.service_name}" />
							</td>
						</tr>
						<tr>
							<td align="right">激活回调比率：</td>
							<td>
								<input type="text" name="pass_rate" id="pass_rate"  value="${vo.pass_rate}" /><span style="color:red">(0.0-1.0)</span> 
							</td>
						</tr>
					</table>
					</fieldset>
				</form>
</body>
</html>