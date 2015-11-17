<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
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
function sendBalanceMail(adID){
	var url = "${pageContext.request.contextPath}/sendBalanceMail.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+adID,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
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


function bad_debt(adID){
	var url = "bad_debt.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+adID,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
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



/**
 * 查看修改信息
 *	
 **/
function info(id){
		var url = "balanceAccountInfo.do?id="+id;
		new $.dialog({
			title:'对账信息',
			page:url,
			width:575,
			height:500,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}




/**
 * 查看修改信息
 *	
 **/
function findUpdateAdv(adId){
		var url = "addPayments.do?id="+adId;
		new $.dialog({
			title:'回款',
			page:url,
			width:575,
			height:300,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}




function add_confirm_money(adId){
	var url = "add_confirm_money.do?id="+adId;
	new $.dialog({
		title:'回款',
		page:url,
		width:575,
		height:300,
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
						<legend>销售对账</legend>
						<div id="search_bar">
							<form action="balanceaccount.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告主ID/名称</td>
													<td><input type="text" name="adv" value="${bean.adv}" /></td>
													<td>结算方式</td>
													<td>
														<select name="charge_type">
															<option value="">全部</option>
															<option <c:if test="${bean.charge_type =='CPM'}"> selected="selected"</c:if> value="CPM" >CPM</option>
															<option <c:if test="${bean.charge_type =='CPC'}"> selected="selected"</c:if> value="CPC" >CPC</option>
															<option <c:if test="${bean.charge_type =='CPD'}"> selected="selected"</c:if> value="CPD" >CPD</option>
															<option <c:if test="${bean.charge_type =='CPA'}"> selected="selected"</c:if> value="CPA" >CPA</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>活动ID/名称</td>
													<td>
														<input type="text" name="campaign" value="${bean.campaign}" />
													</td>
													<td>对账状态</td>
													<td>
														<select name="balance_status">
															<option value="">全部</option>
															<option value="0" <c:if test="${bean.balance_status ==0}">selected="selected" </c:if>>未对账</option>
															<option value="10" <c:if test="${bean.balance_status ==10}">selected="selected" </c:if> >已发邮件</option>
															<option value="20" <c:if test="${bean.balance_status ==20}">selected="selected" </c:if>>已对账</option>
														</select>
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
								<th width="15%">发生日期</th>
								<th width="8%">广告主ID</th>
								<th width="10%">广告主名称</th>
								<th width="8%">活动ID</th>
								<th width="10%">活动名称</th>
								<th width="5%">OS</th>
								<th width="10%">结算方式</th>
								<th width="10%">预确认效果数</th>
								<th width="10%">预确认金额</th>
								<th width="10%">回款金额</th>
								<th width="5%">销售人员</th>
								<th width="10%">状态</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td style="text-align: center;">
											${status.index+1}
										</td>
										<td>
											<fmt:formatDate value="${vo.start_date}" pattern="yyyy-MM-dd"/>
											至
											<fmt:formatDate value="${vo.end_date}" pattern="yyyy-MM-dd"/>
										</td>
										<td>
											${vo.adv_id}
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
										<td>
											${vo.charge_type}
										</td>
										<td>
											${vo.forecast_amount}
										</td>
										<td>
											<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.forecast_money}"  minFractionDigits="2" maxFractionDigits="2" />
										</td>
										<td>
											<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.payments}"  minFractionDigits="2" maxFractionDigits="2" />
										</td>
										<td>
											${vo.salesman_name}
										</td>
										<td>
											<c:choose>
												<c:when test="${vo.balance_status ==0}">
													未对账
												</c:when>
												<c:when test="${vo.balance_status ==10}">
													已发邮件
												</c:when>
												<c:when test="${vo.balance_status ==20}">
													已对账
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<input type="button" value="查看" onclick="info('${vo.id}');" <c:if test="${(vo.status==-5)}">disabled</c:if> <escore:security code="BTN_AD_ONLINE" type="button" /> />
											<input type="button" value="对账" onclick="sendBalanceMail('${vo.id}','${vo.status}');" <c:if test="${(vo.status==-5)}">disabled</c:if> <escore:security code="BTN_AD_ONLINE" type="button" /> />
											<input type="button" value="确认金额" onclick="add_confirm_money('${vo.id}','${vo.status}');" <c:if test="${(balance_status!=10)||(vo.status==-5)}">disabled</c:if> <escore:security code="BTN_AD_ONLINE" type="button" /> />
											<input type="button" value="回款" onclick="findUpdateAdv('${vo.id}');" <c:if test="${vo.status==-5}">disabled</c:if> <escore:security code="BTN_AD_MODIFY" type="button" /> />
											<!-- <input type="button" value="结账" onclick="findUpdateAdv('${vo.id}');" <c:if test="${vo.status==-5}">disabled</c:if> <escore:security code="BTN_AD_MODIFY" type="button" /> /> -->
											<input type="button" value="坏账" onclick="bad_debt('${vo.id}');" <c:if test="${vo.status==-5}">disabled</c:if> <escore:security code="BTN_AD_MODIFY" type="button" /> />
										</td>
										<td>
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