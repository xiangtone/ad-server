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
<title>财务管理广告主收入审核 </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
$(document).ready(function (){
	$("#achievementDown").click(function(){
		$("#achievement").attr("action","${pageContext.request.contextPath}/finance/incomeAuditListDown.do").submit();
	});

	$("#achievementReport").click(function(){
		$("#achievement").attr("action","${pageContext.request.contextPath}/finance/incomeAuditList.do").submit();
	});

});	

function refresh(){
	$("#achievement").submit();
}


function sumit_num(){
	if($("#tb input:checkbox[checked][@value]").length<=0){
		alert("提示：请选择要提交的数据！！");
		return;
	}
	var ids=getCheckedId();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!submitIncomeNum.do',
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
		var url = "manage!enteringDayIncome.do?income_id="+id;
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
						<legend>收入明细表</legend>
						<div id="search_bar">
							<form action="${pageContext.request.contextPath}/finance/incomeAuditList.do" method="post" id="achievement">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>发生时间</td>
													<td>
														<input type="text" id="month_stat_date" name="month_stat_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}"/>						
														至<input type="text" id="month_end_date" name="month_end_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_end_date}"/>
													</td>	
													<td>广告主名称</td>
													<td><input name="adv" type="text" value="${bean.adv}" /></td>
												</tr>
												<tr>
													<td>活动ID/名称</td>
													<td>
														<input name="campaign" type="text" value="${bean.campaign}" />
													</td>
													
													<td>平台类型</td>
													<td>
														<select name="os" style="width: 153px;">
															<option value="">全部</option>
															<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
															<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
														</select>
													</td>
												</tr>
												<tr>
												<td >结算状态</td>
													<td>
														<select name="status" style="width: 153px;">
															<option value="" >全部</option>
															<option value="1" <c:if test="${bean.status ==1}">selected="selected"</c:if>>未结算</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>待审核</option>
															<option value="3" <c:if test="${bean.status ==3}">selected="selected"</c:if>>已审核</option>
														</select>
													</td>
													<td>销售人员</td>
													<td>
														<input name="sales" type="text" value="${bean.sales}" />
													</td>
												</tr>
												<tr>
												<td>结算方式</td>
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
												<button id="achievementReport" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('achievement');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<input type="button" value="申请" onclick="sumit_num();" />
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
							<tr class="tr_td">
								<th width="3%"  order="false"><input id="checkbox" type="checkbox" value="${k.id}" onclick="selectAll(this.checked);" />选择</th>
								<th >明细单号</th>
								<th >广告主名称</th>
								<th >活动ID</th>
								<th >活动名称</th>
								<th >日期</th>
								<th >系统</th>
								<th >结算方式</th>
								<th >结算单价</th>
								<th >预确认数</th>
								<th >预确认金额</th>
								<th >销售人员</th>
								<th >结算状态</th>
								<th >结算单号</th>
								
							</tr>
							<tr>
								<td align="center">汇总</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">${sum.sum_forecast_amount}</td>
								<td align="center">
								<font color="red">
									<c:if test="${sum.sum_income_cha_money!=null}">
										￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${sum.sum_income_cha_money}"  minFractionDigits="2" maxFractionDigits="2" />
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
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr style="text-align: center;">
									<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${vo.id}" <c:if test="${vo.status!=1}"> disabled</c:if>/>结算</td>
									<td>${vo.id}</td>
									<td><escore:subStr len="20" value="${vo.company_name}" /></td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.static_date}</td>
									<td>${vo.os}</td>
									<td>${vo.charge_type}</td>
									<td>${vo.price}</td>
									<td>${vo.confirm_amount}</td>
									<td>
										<c:if test="${vo.income_money!=null}">
										￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.income_money}"  minFractionDigits="2" maxFractionDigits="2" />
										</c:if>
									</td>
									<td>${vo.name}</td>
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
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="14" style="text-align: center;">暂无数据！</td>
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