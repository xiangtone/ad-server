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
<title>运营管理后台业绩报表 </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

/**
 * 查看平台成本明细
 *	
 **/

function findPlaDetail(id,month_stat_date,month_end_date,cost_date) {
	var url = "manage!getPlaDetailInfo.do?confirm_id="+id+"&month_stat_date="+month_stat_date+"&month_end_date="+month_end_date+"&cost_date="+cost_date;
	new $.dialog({
		title:'平台成本明细',
		page:url,
		width:1100,
		height:430,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

/**
 * 查看渠道成本明细
 *	
 **/

function findChaDetail(id,month_stat_date,month_end_date,cost_date) {
	var url = "manage!getChaDetailInfo.do?confirm_id="+id+"&month_stat_date="+month_stat_date+"&month_end_date="+month_end_date+"&cost_date="+cost_date;
	new $.dialog({
		title:'渠道成本明细',
		page:url,
		width:1100,
		height:430,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}
		/**
 * 查看
 *	
 **/
function findData(id){
		var url = "manage!findDataPhoto.do?id="+id;
		new $.dialog({
			title:'查看截图',
			page:url,
			width:600,
			height:500,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

		
function publish(id){
	var url = "prePublishachievement.do?id="+id;
	new $.dialog({
		title:'查看截图',
		page:url,
		width:600,
		height:350,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

function reduceCampaign(id){
	var url = "manage!reduceAchievementCampaign.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+id,
			dataType:'text',
			beforeSend:function(){
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
					refresh();
				}
			}
			
		}
		});
	}//取消操作
}

function refresh(){
	$("#achievement").submit();
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>业绩报表</legend>
						<div id="search_bar">
							<form action="manage!achievementReportList.do" method="post" id="achievement">
								<table  width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>活动ID/名称</td>
													<td>
														<input name="campaign" type="text" value="${bean.campaign}" />
													</td>
													<td>效果发生时间</td>
													<td style="color: blue;">
														<input type="text" id="month_stat_date" name="month_stat_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}"/>						
														至<input type="text" id="month_end_date" name="month_end_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_end_date}"/>
													</td>
												</tr>
												<tr>
													<td>广告主ID/名称</td>
													<td><input name="adv" type="text" value="${bean.adv}" /></td>
													<td>状态</td>
													<td>
														<select name="status"  style="width: 155px;">
															<option value="">全部</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>未发布</option>
															<option value="3" <c:if test="${bean.status ==3}">selected="selected"</c:if>>已发布</option>
															<option value="8" <c:if test="${bean.status ==8}">selected="selected"</c:if>>通过</option>
															<option value="9" <c:if test="${bean.status ==9}">selected="selected"</c:if>>不通过</option>
														</select>
													</td>
													<td>业绩月份</td>
													<td>
														<input type="text" id="month" name="month" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate" value="${bean.month }">
													</td>	
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
												<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
													<button id="achievementReport" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
													<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
												</div>
											</td>
										</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<input name="achievementDown" type="button" id="achievementDown" value="导出Excel"/>
					<div class="main_table" style="overflow: scroll;">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
							<tr class="tr_td">
								<th rowspan="2">月份</th>
								<th rowspan="2">发生时间</th>
								<th rowspan="2">广告主名称</th>
								<th rowspan="2">活动id</th>
								<th rowspan="2">活动名称</th>
								<th rowspan="2">预确认数</th>
								<th rowspan="2">确认数</th>
								<th rowspan="2">损耗</th>
								<th rowspan="2">单价</th>
								<th rowspan="2">结算<br/>方式</th>
								<th rowspan="2">总收入</th>
								<th rowspan="2">总成本</th>
								<th rowspan="2">总毛利</th>
								<th colspan="3">平台</th>
								<th colspan="3">渠道</th>
								<th rowspan="2">平台类型</th>
								<th rowspan="2">状态</th>
								<th rowspan="2">销售人员</th>
								<th rowspan="2">大区</th>
								<th rowspan="2">资料</th>
								<th rowspan="2">操作</th>
							</tr>
							<tr class="tr_td">	
								<th>收入</th>
								<th>成本</th>
								<th>毛利</th>
								<th>收入</th>
								<th>成本</th>
								<th>毛利</th>
							</tr>
							<tr>
								<td align="center">
									汇总
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									<font color="red">${sum.sum_forecast_amount}</font>
								</td>
								<td align="center">
									<font color="red">${sum.sum_income_amount}</font>
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
									<td align="center">
									<font color="red">
										<escore:formatMoney	value="${sum.sum_income_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_cost_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<fmt:formatNumber value="${sum.sum_gross_profit}" type="percent" pattern="#0.0#" />%
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_income_plm_money}" maxFractionDigits="2" />
										</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney	value="${sum.sum_cos_plm_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
									<fmt:formatNumber value="${sum.sum_plm_gross_profit}"
											type="percent" pattern="#0.0#" />%
										</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney	value="${sum.sum_income_cha_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<escore:formatMoney value="${sum.sum_cost_cha_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td align="center">
									<font color="red">
										<fmt:formatNumber value="${sum.sum_cha_gross_profit}" type="percent" pattern="#0.0#" />%
									</font>
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>
								<td align="center">
									--
								</td>				
								<td align="center">
									--
								</td>				
							</tr>
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr style="text-align: center;">
									<td>${vo.month}</td>
									<td>${vo.month_stat_date}至${vo.month_end_date}</td>
									<td><escore:subStr len="20" value="${vo.company_name}" /></td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.forecast_amount}</td>
									<td>${vo.income_amount}</td>
									<td>
										<fmt:formatNumber value="${vo.ullage}" type="percent" pattern="#0.00" />%</td>
									<td>${vo.price}</td>
									<td>${vo.charge_type}</td>
									<td><fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.income_money}"/></td>
									<td><fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.cost_money}"/></td>
									<td>
										<c:choose>
											<c:when test="${vo.gross_profit_rate!= null}">
												<fmt:formatNumber value="${vo.gross_profit_rate}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
								 				0.0%
											</c:otherwise>
										</c:choose>
									</td>
									<td><fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.platform_income_money}"/></td>
									<td>
										<a href="javascript:void(0);" onclick="findPlaDetail('${vo.id}','${vo.month_stat_date}','${vo.month_end_date}','${vo.cost_date}')" style="color: red;" title="查看">
											<fmt:formatNumber  type="number" pattern="###,###0.0#" value="${vo.platform_cost_money}"/>
										</a>
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.pla_gross_profit_rate!= null}">
												<fmt:formatNumber value="${vo.pla_gross_profit_rate}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
								 				0.0%
											</c:otherwise>
										</c:choose>
									</td>
									<td><fmt:formatNumber  type="number" pattern="#0.0#" value="${vo.channel_income_money}"/></td>
									<td>
										<a href="javascript:void(0);" onclick="findChaDetail('${vo.id}','${vo.month_stat_date}','${vo.month_end_date}','${vo.cost_date}')"><fmt:formatNumber  type="number" pattern="#0.0#" value="${vo.channel_cost_money}"/></a>
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.cha_gross_profit_rate!= null}">
												<fmt:formatNumber value="${vo.cha_gross_profit_rate}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
								 				0.0%
											</c:otherwise>
										</c:choose>
									</td>
									<td>${vo.os}</td>
									<td>
										<c:choose>
											<c:when test="${vo.status == 2}">
												未发布
											</c:when>
											<c:when test="${vo.status == 3}">
												已发布
											</c:when>
											<c:when test="${vo.status == 8}">
												通过
											</c:when>
											<c:when test="${vo.status == 9}">
												不通过
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${vo.name}</td>
									<td>
										 <c:forEach items="${areaList}" var="entity">
											<c:if test="${vo.area_type eq entity.id}"> ${entity.area_name}</c:if>
										</c:forEach>
									</td>
									<td>
										<a href="javascript:void(0);" onclick="findData('${vo.id}')"><font color="red;">查看</font></a>
									</td>
									<td>
										<input type="button" value="发布" <c:if test="${vo.status == 3 || vo.status==8}"> disabled="disabled" </c:if> onclick="publish('${vo.id}')" style="cursor: pointer;" />
										<input type="button" value="还原" <c:if test="${vo.status == 3 || vo.status==8}"> disabled="disabled" </c:if> onclick="reduceCampaign('${vo.id}')" style="cursor: pointer;" />
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="25" style="text-align: center;">暂无数据！</td>
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