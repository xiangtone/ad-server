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

/**
 * tb效果
 *	
 **/
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
						<legend>在线活动查询</legend>
						<div id="search_bar">
							<form action="campaignRunningList.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告主名称：</td>
													<td><input type="text" name="adName" value="${bean.adName}" /></td>
													<td>活动名称：</td>
													<td>
														<input type="text" name="campaignName" value="${bean.campaignName}" />
													</td>
												</tr>
												<tr>
													<td>系统：</td>
													<td><select name="os" id="os" style="width: 155px;">
												<option value="" >全部</option>
												<option value="android" <c:if test="${bean.os == 'android'}">selected="selected"</c:if>>安卓</option>
												<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if>>iOS</option>
											</select></td>
													<td>计费方式：</td>
													<td>
														<select name="blance_mode" style="width: 155px;">
															<option value="">全部</option>
															<option value="CPA"<c:if test="${bean.blance_mode == 'CPA'}">selected="selected"</c:if>>CPA</option>
															<option value="CPC"<c:if test="${bean.blance_mode == 'CPC'}">selected="selected"</c:if>>CPC</option>
															<option value="CPD"<c:if test="${bean.blance_mode == 'CPD'}">selected="selected"</c:if>>CPD</option>
															<option value="CPM"<c:if test="${bean.blance_mode == 'CPM'}">selected="selected"</c:if>>CPM</option>
															<option value="CPT"<c:if test="${bean.blance_mode == 'CPT'}">selected="selected"</c:if>>CPT</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>上线时间：</td>
													<td>
													<input  id="online_time" name="online_time" type="text" onclick="WdatePicker()" value="${bean.online_time}" class="Wdate" readonly="readonly"/></td>
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
								<th>广告主名称</th>
								<th>活动名称</th>
								<th>计费方式</th>
								<th>系统</th>
								<th>上线时间</th>
								<th>状态</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td>
											${vo.adv_name}
										</td>
										<td>
											${vo.campaign_name}
										</td>
										<td>
											${vo.blance_mode}
										</td>
										<td>
											${vo.os}
										</td>
										<td>
											${vo.online_time}
										</td>
										
										<td>
											${vo.status_name}
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<td colspan="7" align="center" style="text-align: center;">暂无记录！</td>
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