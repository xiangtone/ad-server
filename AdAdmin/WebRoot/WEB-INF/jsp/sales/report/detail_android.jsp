<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理在线活动查询</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
function refresh(){
	$("#my_form").submit();	
}



function info(){
	var url = "salesReportDetail_android.do";
	new $.dialog({
		title:'添加物料',
		page:url,
		width:975,
		height:635,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

$(document).ready(function (){
});		
</script>
<!-- <style>
table,td{ border:1px solid #000; border-collapse:collapse}
</style> -->
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>查询</legend>
						<div id="search_bar">
							<form action="salesReportDetail_android.do" id="my_form" method="post">
								<div>
									<input type="hidden" name="campaign_id" value="${bean.campaign_id}" />
								</div>
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td >开始日期</td>
													<td>
														<input type="text" id="statStartTime" name="start_date" value="${bean.start_date}" maxlength="20" onfocus="WdatePicker()" class="Wdate"/>
													</td>
													<td>截止日期</td>
													<td>
														<input type="text" id="statEndTime" name="end_date" value="${bean.end_date}" maxlength="20" onfocus="WdatePicker()" class="Wdate" />									 		
													</td>
													<td>包名称/ID：</td>
													<td>
														<input type="text" name="packageInfo" value="${bean.packageInfo}" />
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
							<tr class="tr_td">
								<th width="10%">日期</th>
								<th width="5%">包</th>
								<th width="10%">计费方式</th>
								<th width="5%">平台激活数</th>
								<th width="8%">平均单价</th>
								<th width="8%">效果数</th>
								<th width="8%">预计收入</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td>
											${vo.static_date}
										</td>
										<td>
											${vo.file_name}
										</td>
										<td>
											${vo.charge_type}
										</td>
										<td>
											${vo.sys_activate}
										</td>
										<td>
											${vo.in_price}
										</td>
										<td>
											${vo.confirm_amount}
										</td>
										<td>
											${vo.confirm_money}
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
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