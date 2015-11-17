<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台运营管理开发者收入确认</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css"/>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01{
	display: none;
}
</style>
<script type="text/javascript">




function refresh(){
	$("#devIncomeAudit").attr("action","${pageContext.request.contextPath}/channelCostList.do").submit();
}




$(document).ready(function (){
	//  运营开发者收入审核list下载
	$("#devIncomeAuditListDownload").click(function(){
				$("#devIncomeAudit").attr("action",
						"${pageContext.request.contextPath}/finance/devIncomeAuditListDownload.do").submit();
	});
});



function sumit_num(){
	if($("#tb input:checkbox[checked][@value]").length<=0){
		alert("提示：请选择要提交的数据！！");
		return;
	}
	var ids=getCheckedId();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!submitChannelCost.do',
		type:'POST',
		data:'ids='+ids,
		dataType:'text',
		beforeSend:function(){
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					entering(dataObj.data);
					//refresh();
				}
			}
			
		}
	});
	
	
	
}

//获取所有选中的id
function getCheckedId(){
	var ids="";
	if($("#tb input:checkbox[checked][@value]").length>0){
		$("#tb input:checkbox[checked][@value]").each(function(){
			if(ids){
				ids=ids+","+$(this).val();
			}else{
				ids=$(this).val();
			}
		});
	}
	return ids;
}

/**
 * 申请
 *	
 **/
function entering(id){
		var url = "manage!channelDayIncome.do?income_id="+id;
		new $.dialog({
			title:'录入',
			page:url,
			width:820,
			height:300,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

/**
 * 全选
 **/
function selectAll(value){
	var b=value;
	$("#tb input:checkbox[disabled!='disabled']").attr("checked", b);
}


</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>渠道成本</legend>
						<div id="search_bar">
							<form id="devIncomeAudit" name="devIncomeAudit" action="${pageContext.request.contextPath}/channelCostList.do" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td >日期</td>
													<td>
														<input type="text" id="statStartTime" name="statStartTime" value="${bean.statStartTime}" maxlength="20" onfocus="WdatePicker()" class="Wdate"/>
													至
														<input type="text" id="statEndTime" name="statEndTime" value="${bean.statEndTime}" maxlength="20" onfocus="WdatePicker()" class="Wdate" />									 		
													</td>
													<td >结算状态</td>
													<td>
														<select name="status" style="width: 153px;">
															<option value="" >全部</option>
															<option value="1" <c:if test="${bean.status ==1}">selected="selected"</c:if>>未结算</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>待审核</option>
															<option value="3" <c:if test="${bean.status ==3}">selected="selected"</c:if>>已审核</option>
														</select>
													</td>
													<td>渠道人员</td>
													<td>
														<input name="sales" type="text" value="${bean.sales}" />
													</td>
												</tr>
												<tr>
													<td>活动ID/活动名称</td>
													<td>
														<input name="campaign" type="text" maxlength="20" value="${bean.campaign}" style="border:#999 1px solid;height:20px;background:#fff no-repeat right;" />
													</td>
													<td>渠道ID/渠道名称</td>
													<td>
														<input name="channel" type="text" maxlength="20" value="${bean.channel}" style="border:#999 1px solid;height:20px;background:#fff no-repeat right;" />
													</td>
													
													
													<td>平台类型</td>
													<td>
														<select name="os" style="width: 157px;">
															<option value="">全部</option>
															<option <c:if test="${bean.os=='android'}"> selected="selected"</c:if> value="android">android</option>
															<option <c:if test="${bean.os=='ios'}"> selected="selected"</c:if> value="ios">ios</option>
														</select>
													</td>			
												</tr>
												<tr>
													<td>投放方式</td>
														<td>
															<select name="charge_type" style="width: 153px;">
																<option value="">全部</option>
																<option <c:if test="${bean.charge_type eq 'CPA'}">selected="selected" </c:if> value="CPA" >CPA</option>
																<option <c:if test="${bean.charge_type eq 'CPC'}">selected="selected" </c:if> value="CPC" >CPC</option>
																<option <c:if test="${bean.charge_type eq 'CPD'}">selected="selected" </c:if> value="CPD" >CPD</option>
																<option <c:if test="${bean.charge_type eq 'CPM'}">selected="selected" </c:if> value="CPM" >CPM</option>
																<option <c:if test="${bean.charge_type eq 'CPT'}">selected="selected" </c:if> value="CPT" >CPT</option>
															</select>
														</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button id="devIncomeAuditList" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button  onclick="resetAll('devIncomeAudit');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table> 
							</form>
						</div>
					</fieldset>
					<div>
					<!-- <input name="download" type="button" id="devIncomeAuditListDownload" value="导出Excel" /> -->
					</div>
					<input type="button" value="申请" onclick="sumit_num();" />
					<div class="main_table">
					<!--修改-->
					<form id="batchForm" action="${pageContext.request.contextPath}/finance/devIncomeStatus.do" method="post">			
						<table width="100%" cellpadding="0"cellspacing="1" class="font_stat" id="tb">
							<tr class="tr_td tb_head">
								<th width="3%"  order="false"><input id="checkbox" type="checkbox" value="${k.id}" onclick="selectAll(this.checked);" />选择</th>
								<th order="false">成本明细单号</th>
								<th width="5%" order="false">渠道ID</th>
								<th order="false">渠道名称</th>
								<th order="false">活动ID</th>
								<th order="false">活动名称</th>
								<th order="false">系统</th>
								<th order="false">日期</th>
								<th order="false">投放方式</th>
								<th order="false">投放单价</th>
								<th order="false">预确认数</th>
								<th order="false">预确认金额</th>
								<th order="false">渠道人员</th>
								<th order="false">成本结算状态</th>
								<th order="false">成本结算单号</th>
							</tr>
							<c:if test="${!empty list}">
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
										${sum.sum_amount}
									</td>
									<td align="center">
										<font color="red">
											<c:if test="${sum.sum_cost!=null}">
											￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${sum.sum_cost}"  minFractionDigits="2" maxFractionDigits="2" />
										</c:if>
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
									
								</tr>
								<c:forEach items="${list}" var="vo">
								<tr style="text-align: center;">
									<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${vo.id}" <c:if test="${vo.status!=1}"> disabled</c:if>/>结算</td>
									<td>${vo.id}</td>
									<td>${vo.channel_id}</td>
									<td>${vo.channel_name}</td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.os}</td>
									<td>${vo.static_date}</td>
									<td>${vo.charge_type}</td>
									<td>${vo.price}</td>
									<td>${vo.confirm_amount}</td>
									<td>
									<c:if test="${vo.income_money!=null}">
										￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.income_money}"  minFractionDigits="2" maxFractionDigits="2" />
										</c:if>
									</td>
									<td>${vo.channe_manager}</td>
									<td>
										<c:choose>
											<c:when test="${vo.status == 1}">
												未结算
											</c:when>
											<c:when test="${vo.status == 2}">
												待审核
											</c:when>
											<c:when test="${vo.status == 3}">
												已审核
											</c:when>
											<c:otherwise>
								 				
											</c:otherwise>
										</c:choose>
									</td>
									<td>${vo.balance_id}</td>
								</tr>
							
								</c:forEach>
								</c:if>
								<c:if test="${empty list}">
									<tr>
										<td align="center" colspan="18" style="text-align: center;">暂无数据！</td>
									</tr>	
								</c:if>
						</table>
					</form>
				</div>
				<div>${pageInfo.pageInfoStr}</div>
				<span class="clear_span" style="height: 10px;"></span>
			</div>
		</div>
	</div>
</body>
</html>