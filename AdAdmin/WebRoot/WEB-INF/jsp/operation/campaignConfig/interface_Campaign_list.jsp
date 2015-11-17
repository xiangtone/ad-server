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
<title>IOS接口对接配置</title>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	
function interfaceCampaign(urlstr,id,width,height){	
	var url = urlstr+"?id="+id;
	new $.dialog({
		title:'添加或修改广告配置',
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
	$("#ios_form").attr("action", "manage!cpIosConfig.do").submit();
}
function refresh(){
	$("#ios_form").submit();
}
//重新刷新缓存
function reflushcache(ad_id){
	var url="manage!reflushCache.do"
	$.get(url, {ad_id:ad_id},
	     function(data){
		     alert("Data Loaded: " + data);
		 });
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>IOS接口对接配置 </legend>
						<form action="#" method="post"	id="ios_form">
							<table width="100%">
								<tr>
								  <td>
									 <table width="100%">
										 <tr>
											<td>广告 ad_key：</td>
											 <td>
												<input type="text" value="${vo.ad_key}" name="ad_key" />
											  </td>
											  <td>广告名称：</td>
											  <td>
											   <input type="text" value="${vo.ad_name}" name="ad_name" />
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
						</form>
					</fieldset>
					<div class="bord_bom1px">
						<input name="button" type="button" onclick="interfaceCampaign('manage!addCampaignConfig.do','',700,650);" value="添加" />
					</div>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"
							class="font_stat">
							<tr class="tr_td">
								<th width="5%">id</th>
								<th width="5%">广告key</th>
								<th width="5%">广告名称</th>
								<th width="45%">url</th>
								<th width="5%">广告id参数名</th>
								<th width="5%">设备参数名</th>
								<th width="5%">mac参数名</th>
								<th width="5%">投放id值</th>
								<th width="5%">idfa参数名</th>
								<th width="5%">回调接口参数名</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${list}" var="k">
								<tr style="text-align: center;">
									<td>${k.id}</td>
									<td>${k.ad_key}</td>
									<td>${k.ad_name}</td>
									<td>
										<escore:subStr len="80" value="${k.url}" />
									</td>
									<td>${k.adid_str}</td>
									<td>${k.deviceid_para }</td>
									<td>${k.udid }</td>
									<td>${k.placement_id }</td>
									<td>${k.idfa }</td>
									<td>${k.callback }</td>
									<td>
									<input name="button" type="button" onclick="interfaceCampaign('manage!eidteCampaignConfig.do','${k.id}',780,590);" value="修改" />
									<input name="button" type="button" onclick="interfaceCampaign('manage!sendClickInfoPage.do','${k.id}',700,450);" value="测试" />
									<input name="button" type="button" onclick="reflushcache('${k.id}');" value="刷新缓存" />
									</td>		
								</tr>
							</c:forEach>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
								</tr>
							</c:if>
						</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>
</html>