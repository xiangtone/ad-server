<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>财务管理付款</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#paymentReport").click(
		function() {
			$("#payment").attr("action", "${pageContext.request.contextPath}/manage!financeChannelPaymentlist.do").submit();
		});
	});
	function refresh() {
		$("#payment").submit();
	}
	//批量付款
	function sumit_num() {
		if ($("#tb input:checkbox[checked][@value]").length <= 0) {
			alert("提示：请选择要提交的数据！！");
			return;
		}
		var ids="";
		var payment_remarks = "";
		var checkedLength = $("input:checked").length;// 被选中的数量
		if (checkedLength > 0) {
			for ( var i = 0; i < checkedLength; i++) {
				var id = $("input:checked")[i].value;
				var payment_remark = $("#payment_remark_"+id).val();
				if(ids){
					ids=ids+","+id;
				}else{
					ids=id;
				}
				if(payment_remarks){
					payment_remarks=payment_remarks+","+payment_remark;
				}else{
					payment_remarks=payment_remark;
				}
			}
			$.ajax({
				url:'${pageContext.request.contextPath}/manage!submitPaymentChannelNum.do',
				type:'POST',
				data:'ids='+ids+"&remarks="+payment_remarks,
				dataType:'text',
				beforeSend:function(){
				},
				success:function(data){
					if(data){
						if(data=0) {
							alert("付款失败");
						} else {
							alert("付款成功");
						}
						refresh();
					}
					
				}
			});
		}
	}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="margtop10 mar_bom10">
						<legend>付款管理</legend>
						<div id="search_bar">
							<form action="${pageContext.request.contextPath}/manage!financeChannelPaymentlist.do" method="post" id="payment" name="payment">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>发生时间</td>
													<td><input type="text" id="month_stat_date" name="month_stat_date" onclick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}" />至<input type="text" id="month_end_date" name="month_end_date" onclick="WdatePicker()" class="Wdate" value="${bean.month_end_date}" /></td>
													<td>渠道ID/名称</td>
													<td><input name="channel" type="text" value="${bean.channel}" /></td>
												</tr>
												<tr>
													<td>活动ID/名称</td>
													<td><input name="campaign" type="text" value="${bean.campaign}" /></td>
													<td>平台类型</td>
													<td><select name="os" style="width: 153px;">
														<option value="">全部</option>
														<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android">android</option>
														<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios">ios</option>
													</select></td>
												</tr>
												<tr>
													<td>付款状态</td>
													<td><select name="payment_status" style="width: 153px;">
														<option value="">全部</option>
														<option value="0" <c:if test="${bean.payment_status ==0}">selected="selected"</c:if>>未付款</option>
														<option value="1" <c:if test="${bean.payment_status ==1}">selected="selected"</c:if>>已付款</option>
													</select></td>
													<td>渠道人员</td>
													<td><input name="sales" type="text" value="${bean.sales}" /></td>
												</tr>
												<tr>
													<td>结算方式</td>
													<td><select name="balance_model" style="width: 153px;">
														<option value="">全部</option>
														<option <c:if test="${bean.balance_model eq 'CPA'}">selected="selected"</c:if> value="CPA">CPA</option>
														<option <c:if test="${bean.balance_model eq 'CPC'}">selected="selected"</c:if> value="CPC">CPC</option>
														<option <c:if test="${bean.balance_model eq 'CPD'}">selected="selected"</c:if> value="CPD">CPD</option>
														<option <c:if test="${bean.balance_model eq 'CPM'}">selected="selected"</c:if> value="CPM">CPM</option>
														<option <c:if test="${bean.balance_model eq 'CPT'}">selected="selected"</c:if> value="CPT">CPT</option>
													</select></td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px; height: 100%; display: inline; line-height: 20px;">
												<button id="paymentReport" class="button button-pill button-primary" style="line-height: 21px; height: 21px; font-size: 13px; padding-left: 10px; padding-right: 10px;">查询</button>
												<button onclick="resetAll('payment');" class="button button-pill button-primary" style="line-height: 21px; height: 21px; font-size: 13px; padding-left: 10px; padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<input type="button" value="批量付款" onclick="sumit_num();" />
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="table_bod1 font_stat">
							<tr class="tr_td">
								<th>付款状态</th>
								<th>明细单号</th>
								<th>渠道ID</th>
								<th>渠道名称</th>
								<th>活动ID</th>
								<th>活动名称</th>
								<th>系统</th>
								<th>结算周期</th>
								<th>结算方式</th>
								<th>结算单价</th>
								<th>预确认数</th>
								<th>预确认金额</th>
								<th>结算金额</th>
								<th>核减比例</th>
								<th>销售人员</th>
								<th>备注</th>
							</tr>
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr style="text-align: center;">
									<td>
										<c:if test="${vo.payment_status==null||vo.payment_status==0}"><input id="payment_id_${vo.id}" name="payment_id" type="checkbox" value="${vo.id}"/>未付款</c:if>
										<c:if test="${vo.payment_status==1}"><input id="payment_id_${vo.id}" name="payment_id" type="checkbox" value="${vo.id}" disabled="disabled"/>已付款</c:if>
									</td>
									<td>${vo.id}</td>
									<td>${vo.channel_id}</td>
									<td>${vo.channel_name}</td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.os}</td>
									<td>${vo.month_stat_date}&nbsp;&nbsp;至&nbsp;&nbsp;${vo.month_end_date}</td>
									<td>${vo.balance_model}</td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.price}" /></td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.forecast_amount}" /></td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.forecast_money}" /></td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.income_money}" /></td>
									<td><fmt:formatNumber type="number" pattern="###,###0.0#" value="${vo.subtract_ratio}" />%</td>
									<td>${vo.channe_manager}</td>
									<td>
									<c:if test="${vo.payment_status==1}">
									<input type="text" id="payment_remark_${vo.id}" name="payment_remark" value="${vo.payment_remark}" disabled="disabled" /></c:if>
									<c:if test="${vo.payment_status==null||vo.payment_status==0}">
									<input type="text" id="payment_remark_${vo.id}" name="payment_remark" value="${vo.payment_remark}"/></c:if>
									
									
									
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="16" style="text-align: center;">暂无数据！</td>
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