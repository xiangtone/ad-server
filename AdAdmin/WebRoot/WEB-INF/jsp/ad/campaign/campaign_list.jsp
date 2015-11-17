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
<title>运营管理后台活动查询统计</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css"/>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	/**
	 * 审核活动
	 *	
	 **/
	function auditingActivity(actId) {
		var url = "manage!preAuditCampaign.do?campaign_id=" + actId;
		new $.dialog({
			title:'活动信息',
			page:url,
			width:735,
			height:480,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	/**
	 * 查看活动
	 *	
	 **/

	function findAvti(id) {
		var url = "manage!campaignInfo.do?campaign_id="+id;
		new $.dialog({
			title:'活动信息',
			page:url,
			width:535,
			height:430,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}

	/**
	 * 修改活动
	 *	
	 **/
	function updateActi(id) {
		var url = "manage!editCampaign.do?id=" + id;
		new $.dialog({
			title:'修改活动',
			page:url,
			width:780,
			height:510,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	/**
	 * 修改活动单价
	 *	
	 **/
	function updatePrice(id) {
		var url = "manage!editCampaignPrice.do?id=" + id;
		new $.dialog({
			title:'修改活动单价',
			page:url,
			width:450,
			height:190,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	
	/**
	 * 
	 *	
	 **/
	function activityAdv(email,id){		
		var url = "manage!addCampaign.do?advEmail="+email+"&adv_id="+id;
		
	}
	
	function addPlacement111(id){
		var url = "manage!addCampaignCategory.do?id=216";
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
	
	
	function cheshi(id){
		var url = "manage!addCampaignCategory.do?id=216";
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
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<fieldset class="search_fieldset">
					<legend>活动查询统计</legend>
				<!--新增-->
				<form action="manage!campaignList.do" method="post"	id="my_form">
					<table style="width: 100%;">
						<tr>
						<td>
						<table width="100%">
						<tr>
							<td>广告主ID/名称</td>
							<td>
								<input name="adv" type="text" value="${bean.adv}" />
							</td>
							<td>活动ID/名称</td>
							<td>
								<input name="campaign" type="text" value="${bean.campaign}" />
							</td>
							<td>提交人</td>
							<td>
								<input name="create_user"  type="text"	value="${bean.create_user}" />
							</td>
						</tr>
						<tr>
							<td>平台类型</td>
							<td>
								<select name="os">
									<option value="">全部</option>
									<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
									<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
								</select>
							</td>
							<td>结算方式</td>
							<td>
								<select name="charge_type">
									<option value="">全部</option>
									<option <c:if test="${bean.charge_type eq 'CPA'}">selected="selected" </c:if> value="CPA" >CPA</option>
									<option <c:if test="${bean.charge_type eq 'CPC'}">selected="selected" </c:if> value="CPC" >CPC</option>
									<option <c:if test="${bean.charge_type eq 'CPD'}">selected="selected" </c:if> value="CPD" >CPD</option>
									<option <c:if test="${bean.charge_type eq 'CPM'}">selected="selected" </c:if> value="CPM" >CPM</option>
									<option <c:if test="${bean.charge_type eq 'CPT'}">selected="selected" </c:if> value="CPT" >CPT</option>
								</select>
							</td>
							<td>状态</td>
							<td><select name="status" id="status" style="width: 155px;">
									<option value=" ">全部</option>
									<option value="-50"
										<c:if test="${bean.status =='-50'}">selected="selected"</c:if>>活动草稿</option>
									<option value="-40"
									<c:if test="${bean.status =='-40'}">selected="selected"</c:if>>活动待审核</option>
									<option value="-30"
										<c:if test="${bean.status =='-30'}">selected="selected"</c:if>>待投放</option>
									<option value="-20"
										<c:if test="${bean.status =='-20'}">selected="selected"</c:if>>投放草稿</option>
									<option value="-10"
										<c:if test="${bean.status =='-10'}">selected="selected"</c:if>>投放待审核</option>
									<option value="1"
										<c:if test="${bean.status =='1'}">selected="selected"</c:if>>已发布</option>
									<option value="-1"
										<c:if test="${bean.status =='-1'}">selected="selected"</c:if>>活动结束</option>
							</select>
							</td>
						</tr>
					</table>
					</td>
					<td width="12%" valign="middle" align="right">
						<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
							<button onclick="search('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
							<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
						</div>
					</td>
						</tr>
					</table>
				</form>
				</fieldset>
				<div class="main_table">
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
					<!--修改-->
					<tr class="tr_td">
						<th width="3%">广告主ID</th>
						<th>广告主名称</th>
						<th>活动ID</th>
						<th>活动名称</th>
						<th width="4%">活动类型</th>
						<th width="5%">销售</th>
						<th width="5%">活动限制</th>
						<th width="3%">接入单价</th>
						<th width="5%">平台类型</th>
						<th width="3%">计费方式</th>
						<th width="5%">状态</th>
						<th width="5%">提交人</th>
						<th width="10%">操作</th>
					</tr>
					<c:forEach items="${list}" var="vo">
						<tr style="text-align: center;">
							<td>${vo.adv_id}</td>
							<td>${vo.company_name}</td>
							<td>${vo.id }</td>
							<td>
								<a href="javascript:void(0);" onclick="findAvti('${vo.id}')">${vo.campaign_name}</a>
							</td>
							<td>
								<c:forEach items="${sList}" var="entry">
									<c:if test="${vo.category_id eq entry.id}">${entry.name}</c:if>
								</c:forEach>
							</td>
							<td>${vo.salesman_name}</td>
							<td>${vo.budget}</td>
							<td>${vo.price}</td>
							<td>${vo.os }</td>
							<td>${vo.charge_type}</td>
							<td>
								${vo.status_name}
							</td>
							<td>${vo.create_user_name}</td>
							<td>
								<input type="button" value="修改活动" onclick="updateActi('${vo.id}')" <escore:security code="BTN_CAMPAIGN_EDIT" type="button" />  />
								<input type="button" value="修改单价" onclick="updatePrice('${vo.id}')" />
								<input type="button" value="审核活动" onclick="auditingActivity('${vo.id}')" <c:if test="${vo.status!=-40}"> disabled</c:if> <escore:security code="BTN_CAMPAIGN_AUDIT" type="button" /> />
							<!--  <input type="button" value="添加素材" onclick="addMaterial('${vo.placement_id}')" <c:if test="${(vo.status!=-50)&&(vo.status!=-40)}"> disabled</c:if> />-->	
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
				<div style="display: block;height: 15px;"></div>
				${pageInfo.pageInfoStr}
			</div>
		</div>
	</div>
</body>
</html>