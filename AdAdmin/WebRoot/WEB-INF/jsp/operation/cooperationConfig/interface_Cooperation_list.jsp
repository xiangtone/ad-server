<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者积分接口对接配置</title>
<link
	href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	
function interfaceCampaign(urlstr,id,width,height){	
	var url = urlstr+"?app_id="+id;
	new $.dialog({
		title:'添加或修改积分接口配置',
		page:url,
		width:width,
		height:height,
		drag:true,
		resize:true,
		cover:true,
		rang:true,
		autoPos:{left:'center',top:'center'},
		maxBtn:false}).ShowDialog();
}
	
function selData() {
	$("#ios_form").attr("action", "config!cooperConfigList.do").submit();
}
function refresh(){
	$("#ios_form").submit();
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">积分接口配置</div>
				<form action="#" method="post"	id="ios_form">
					<fieldset class="search_fieldset">
						<legend> 查询条件 </legend>
						<table>
							<tr>
							  <td>
								 <table width="100%">
									 <tr>
										<td>广告 ad_key：</td>
										 <td>
											<input type="text" value="${vo.app_id}" name="app_id" />
										  </td>
										  <td>接口URL：</td>
										  <td>
										   <input type="text" value="${vo.response_url}" name="response_url" />
										  </td>
										 </tr>
										</table>
									</td>
									<td width="5%" valign="middle" align="center">
										<div style="width: 100px;height: 100%;">
												<input name="button" type="button" onclick="selData();" value="查询" />
										</div>
									</td>
								</tr>
							</table>
				</fieldset>
				</form>
				<div class="bord_bom1px">
				<input name="button" type="button" onclick="interfaceCampaign('config!addConfig.do','',700,280);" value="添加" />
				</div>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"
						class="font_stat">
						<tr class="tr_td">
							<th>应用id</th>
							<th>添加日期</th>
							<th>接口URL</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${list}" var="k">
							<tr style="text-align: center;">
								<td>${k.app_id}</td>
								<td>${k.create_time}</td>
								<td>${k.response_url}</td>
								<td>
								<input name="button"
										type="button" onclick="interfaceCampaign('config!editConfig.do','${k.app_id}',700,200);" value="修改" />
								</td>		
							</tr>
						</c:forEach>
						<c:if test="${empty list}">
							<tr>
								<td align="center" colspan="4" style="text-align: center;">暂无数据！</td>
							</tr>
						</c:if>
					</table>
				</div>
				${pageInfo.pageInfoStr}
			</div>
		</div>
	</div>
</body>
</html>