<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
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
						<legend>广告查询 </legend>
						<div id="search_bar">
							<form action="manage!adOffLineLog.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
										<tr>
											<td>广告ID</td>
											<td>
												<input type="text" name="id" value="${bean.id}" />
											</td>
												<td>发生日期</td>
											<td>
												<input name="static_date" type="text" value="${bean.static_date}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
											</td>
											<td>广告形式</td>
											<td>
												<select name="type_id" id="adForm">
													<option value="">全部</option>
													<option value="0" <c:if test="${bean.type_id ==0}">selected="selected"</c:if> >积分墙</option>
													<option value="1" <c:if test="${bean.type_id ==1}"> selected="selected"</c:if> >推荐墙</option>
													<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
													<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>广告主ID/名称</td>
											<td><input type="text" name="adv" value="${bean.adv}" /></td>
											<td>活动ID/名称</td>
											<td>
												<input type="text" name="campaign" value="${bean.campaign}" />
											</td>
											<td>平台类型</td>
											<td>
												<select name="os" id="os">
													<option value="">全部</option>
													<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
													<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>下线方式</td>
											<td>
												<select name="offline_type" id="offline_type">
													<option value="">全部</option>
													<option <c:if test="${bean.offline_type =='0'}">selected="selected" </c:if> value="0" >超量</option>
													<option <c:if test="${bean.offline_type =='1'}">selected="selected" </c:if> value="1" >人工</option>
												</select>
											</td>
											</tr>
									</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
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
							<th width="3%">序号</th>
							<th width="3%">AD_ID</th>
							<th width="10%">广告主名称</th>
							<th width="3%">活动ID</th>
							<th width="10%">活动名称</th>
							<th width="3%">OS</th>
							<th width="3%">广告形式</th>
							<th width="3%">限量</th>
							<th width="5%">下线方式</th>
							<th width="8%">上线时间</th>
							<th width="8%">下线时间</th>
							<th width="5%">展示</th>
							<th width="5%">点击</th>
							<th width="5%">下载</th>
							<th width="5%">激活</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;">
										${status.index+1}
									</td>
									<td>
										${vo.ad_id}
									</td>
									<td>
										<escore:subStr len="25" value="${vo.adv_name}" />
									</td>
									<td>
										${vo.campaign_id}
									</td>
									<td>
										<escore:subStr len="20" value="${vo.campaign_name}" />
									</td>
									<td>
										${vo.os}
									</td>
									<td style="text-align:center;">
										<c:choose>
											<c:when test="${vo.type_id ==0}">
												积分墙
											</c:when>
											<c:when test="${vo.type_id ==1}">
												推荐墙
											</c:when>
											<c:when test="${vo.type_id ==2}">
												九宫格
											</c:when>
											<c:when test="${vo.type_id ==4}">
												Banner
											</c:when>
											<c:when test="${vo.type_id ==5}">
												插屏
											</c:when>
										</c:choose>
									</td>
									<td style="text-align: right;">
										${vo.budget_day}${vo.budget_type}
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.offline_type==1}">
												人工
											</c:when>
											<c:when test="${vo.offline_type==0}">
												超量
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<fmt:formatDate value="${vo.online_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<fmt:formatDate value="${vo.offline_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td style="text-align: right;">
										${vo.pv}</td>
									<td style="text-align: right;">
										${vo.click}</td>
									<td style="text-align: right;">
										${vo.download}</td>
									<td style="text-align: right;">
										${vo.activate}
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
