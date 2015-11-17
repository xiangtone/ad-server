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
<title>运营管理后台渠道配置</title>
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
	
function interfaceChannel(channel){	
	var url = "manage!addCollocationChannel.do?channel="+channel;
	new $.dialog({
		title:'添加或修改渠道配置',
		page:url,
		width:700,
		height:360,
		drag:true,
		resize:true,
		cover:true,
		rang:true,
		autoPos:{left:'center',top:'center'},
		maxBtn:false}).ShowDialog();
}
	

</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">渠道接口配置列表</div>
				<!--新增-->

				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"
						class="font_stat">
						<tr class="tr_td">
							<th>渠道编号</th>
							<th>渠道名称</th>
							<th>应用变量名</th>
							<th>mac变量名</th>
							<th>时间变量名</th>
							<th>IP变量名</th>
							<th>激活回调URL</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${list}" var="k">
							<tr style="text-align: center;">
								<td>${k.channel}</td>
								<td>${k.channel_name}</td>
								<td>${k.adid_para }</td>
								<td>${k.deviceid_para }</td>
								<td>${k.time_para }</td>
								<td>${k.client_ip}</td>
								<td>${k.url }</td>
								<td>
								
								<input name="button"
										type="button" onclick="interfaceChannel('${k.channel}');" value="配置" />
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
</body>
</html>