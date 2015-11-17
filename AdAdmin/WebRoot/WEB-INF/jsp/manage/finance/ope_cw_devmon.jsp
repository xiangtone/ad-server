<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者下载收入审核</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/devAdEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>

<script type="text/javascript">
function mmm1(id,max){
	var doubleReg = /^[0-9]+$/;//整数
	var confirm =  document.getElementById(id+"confirmActivationAmount");  
	alert(confirm.value);
	var var_value_max = document.getElementById("var_value_max");  
	alert(var_value_max.innerHTML);
	var_value_max.innerHTML = 333;
	if(!doubleReg.test(confirm.value)){
		alert("确认激活数输入不合法！");
		return;
	}
	
	//$("#batchForm").attr("action",
	//"manage!saveConfirmTemp.do").submit();
}
      $(document).ready(function() {
	      $("#devType").val('${devAdStatListvo.devType}');
	      $("#appType").val('${devAdStatListvo.appType}');
         });
</script>
</head>

<body>
	<div class="head_bj head_bj_bt">
		<div class="header rel admin_head">
			<div class="clearfix land_top">
			<div class="land_nav fl">
				财务管理&nbsp;>&nbsp;开发者下载收入审核
			</div>
			</div>
		</div>
	</div>
<div class="main">
	<div class="content clearfix">
		<jsp:include page="../../../jsp/manage/common/manage_left.jsp"></jsp:include>
		<div class="content_right admin_right">
			<div class="bord_bom1px">开发者下载收入审核</div>
				<!--新增-->
				<form action="manage!listDevAdEffect.do"  id="listDevAdEffect" method="get">
					<input type="hidden" name="orderColumn" id="orderColumn" value="${devAdStatListvo.orderColumn }"/>
					<input type="hidden" name="orderCondition" id="orderCondition" value="${devAdStatListvo.orderCondition }"/>
					<!--新增end-->
					<table>
						<tr>
							<td>
								广告id:&nbsp&nbsp<input name="AD_ID" type="text" value="${ad_id }" />
							</td>
						</tr>
						<tr>
							<td>
								广告名称:<input name="AD_NAME" type="text" value="${ad_name }" />
							</td>
						</tr>	
						<tr>
							<td>
								应用id:&nbsp&nbsp<input name="APP_ID" type="text" value="${app_id }" />
							</td>
						</tr>	
						<tr>
							<td>
								应用名称:<input name="APP_NAME" type="text" value="${app_name }" />
							</td>
						</tr>		
						<tr>
							<td>
								状态:&nbsp&nbsp&nbsp
								<select name="status">
				  							<option value="-2">全部</option>
											<option value="0" <c:if test="${status == 0}"> selected="selected"</c:if>>待审核</option>											
											<option value="-1" <c:if test="${status == -1}"> selected="selected"</c:if>>未确认</option>
									  		<option value="1" <c:if test="${status == 1}"> selected="selected"</c:if>>通过</option>
									  		<option value="2" <c:if test="${status == 2}"> selected="selected"</c:if>>已结算</option>
								</select>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td>开始时间</td>
							<td>
								<input name="beginTime" id="beginTime" type="text" value="${beginDate}" class="Wdate" readonly="readonly"/>
							</td>
							<td>结束时间</td>
							<td>
								<input name="endTime" id="endTime" type="text" value="${endDate}" class="Wdate" readonly="readonly"/>
							</td>
							<td>
								<input value="查询" type="button" id="devDownloadInquire"  style="width:100px;height:50px"/></br>
							</td>
						</tr>
					</table>
					 <input name="download" type="button" id="devDownloadIncome" value="导出Excel" />
					<div>${pageInfo }</div>
				</form>
				<!--修改-->
			<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat">
				<tr class="tr_td">
					<td>统计时间</td>
					<td>广告ID</td>
					<td>广告名称</td>					
					<td><a href="#" onclick="order('STAT_DATE','listDevAdEffect')">日期</a></td>					
					<td>应用ID</td>
					<td>应用名称</td>
					<td>状态</td>
					<td>上线时间</td>
					<td>平均使用时长</td>
					<td>广告墙打开数</td>
					<td>应用下载数</td>					
					<!--<td>应用下载数</td>
					--><td><a href="#" onclick="order('ACTIVATION_AMOUNT','listDevAdEffect')">应用激活数</a></td>
					<td>激活数(状态2)</td>
					<!--<td>激活率</td>
					-->
					<td><a href="#" onclick="order('FIRST_ACTIVATION_AMOUNT','listDevAdEffect')">广告主一次确认激活数</a></td>
					<td><a href="#" onclick="order('SECOND_ACTIVATION_AMOUNT','listDevAdEffect')">广告主二次确认激活数</a></td>
					<td><a href="#" onclick="order('FORECAST_MONEY','listDevAdEffect')">预计收入</a></td>
					<td><a href="#" onclick="order('CONFIRM_MONEY','listDevAdEffect')">确认收入</a></td>
					<td>开发者结算差额</td>
					<td>运营确认激活数</br>
					<c:if test="${tagtemp==2}">
    				 <font id="var_value_max" color="red">${val}</font>
					</c:if>
					</td>
					<td>操作</td>
					<td>批量操作</td>
				</tr>
				<tr style="text-align: center;">
				<td>汇总</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				<td>-</td>
				
				<td>-</td>
				<td>-</td>
				<td>${adwall_pv_amountSum }</td>
				<td>${down_amountSum }</td>
				<td style="text-align: left;color: red;">${sum_activationAmount}</td>
				<td style="text-align: left;color: red;">${shell_amountSum}</td>
			    <td style="text-align: left;color: red;"><escore:formatNumber value="${sum_firstActivationAmount }" maxFractionDigits="2"/></td>
				<td style="text-align: left;color: red;"><escore:formatNumber value="${sum_secondActivationAmount }" maxFractionDigits="2"/></td>
			    <td style="text-align: left;color: red;"><escore:formatMoney value="${sum_forecastMoney }" maxFractionDigits="2" /></td>
			    <td style="text-align: left;color: red;"><escore:formatMoney value="${sum_confirmMoney }" maxFractionDigits="2" /></td>
			    <td style="text-align: left;color: red;"><escore:formatMoney value="${sum_balance }" maxFractionDigits="2" /></td>
			    <td style="text-align: left;color: red;" id="sum_confirmActivationAmount">
			    <escore:formatNumber value="${sum_confirmActivationAmount}" maxFractionDigits="2"/></td>
				<td>-</td>
				<td>-</td>
			</tr>
				<form id="batchForm" action="manage!devAEDealStatus.do" method="post">
					<input id="id" name="id" type="hidden" />
					<input id="status" name="status" type="hidden" />
					<input id="confirmAmount" name="confirmAmount" type="hidden"/>
					<input id="confirmAmounts" name="confirmAmounts" type="hidden"/>
					<c:forEach items="${devAdEffectConfirmList}" var="k">
						<tr>
							<td width="10%"><fmt:formatDate value="${k.createTime }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${k.adId }</td>
							<td>
							<c:if test="${tag_temp==0}">
								<a href="manage!listDevAdEffect.do?ad_idt=${k.adId }&beginDatet=${beginDate}&endDatet=${endDate}" >${k.adName }</a>
							</c:if>
							<c:if test="${tag_temp==null}">
								 ${k.adName }
							</c:if>
							</td>
							<td>${k.statDate}</td>
							<td>${k.appId}</td>
							<td>
								${k.appName}
							</td>
							<td>
							<c:if test="${k.status == 0}">
									待审核
								</c:if>
							<c:if test="${k.status == -1}">
									未确认
								</c:if>
								<c:if test="${k.status == 1}">
									通过
								</c:if>
								<c:if test="${k.status == 2}">
									已结算
								</c:if>
							</td>
							
							<td><fmt:formatDate value="${k.release_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${k.avgTime}</td>
							<td>${k.adwall_pv_amount}</td>
							<td>${k.down_amount}
							<c:if test="${k.down_amount==null}">
								 0
							</c:if>
							</td>
							<!--<td></td> 应用下载数 -->
							<td width="3%">
							<c:if test="${k.activationAmount!=null}">
								${k.activationAmount_String }
							</c:if>
							<c:if test="${k.activationAmount==null}">
								 为空
							</c:if>
							</td><!-- 应用激活数 -->
							<td width="3%"><c:if test="${k.shell_amount!=null}">${k.shell_amount_String }</c:if>
							<c:if test="${k.shell_amount==null}">为空</c:if></td><!-- 激活数2 -->
							<!--<td></td> 激活率 -->
							<td width="5%">
							<c:if test="${k.firstActivationAmount!=null}">
								<escore:formatNumber value="${k.firstActivationAmount }" maxFractionDigits="2"/>
								<input type="hidden" id="${k.id }firstActivationAmount" name="firstActivationAmount" value="${k.firstActivationAmount }"/>
							</c:if>
							<c:if test="${k.firstActivationAmount==null}">
								 为空
								<input type="hidden" id="${k.id }firstActivationAmount" name="firstActivationAmount" value="${k.firstActivationAmount }"/>
							</c:if>
							</td>
							<td width="5%">
							<c:if test="${k.secondActivationAmount!=null}">
								 <escore:formatNumber value="${k.secondActivationAmount }" maxFractionDigits="2"/>
							</c:if>
							<c:if test="${k.secondActivationAmount==null}">
								 为空
							</c:if>
							</td>
							<td>
							<c:if test="${k.forecastMoney!=null}">
								<escore:formatMoney value="${k.forecastMoney }" maxFractionDigits="2" />
							</c:if>
							<c:if test="${k.forecastMoney==null}">
								 为空
							</c:if>
							</td>
							<td>
							<c:if test="${k.confirmMoney!=null}">
								<escore:formatMoney value="${k.confirmMoney }" maxFractionDigits="2" />
							</c:if>
							<c:if test="${k.confirmMoney==null}">
								 为空
							</c:if>
							</td>
							<td width="5%">
							<c:if test="${k.balance!=null}">
								<escore:formatMoney value="${k.balance }" maxFractionDigits="2" />
							</c:if>
							<c:if test="${k.balance==null}">
								 为空
							</c:if>
							</td>
							<c:choose>
								<c:when test="${k.status == 0 and k.firstActivationAmount != null and tagtemp==2}">
									<td width="15%">
										<c:choose>
											<c:when test="${k.confirmActivationAmount == null}">
												<c:if test="${k.confirm_activation_temp != null}">
													Max:${mapss[k.adId]}&nbsp;&nbsp;&nbsp;&nbsp;<input onchange="saveConfirmTemp('${k.id}','${k.confirm_activation_temp}')"   id="${k.id }confirmActivationAmount" name="confirmActivationAmount" size="5" value="${k.confirm_activation_temp}" />
													<input type="hidden" id="${k.id }hidden" value="${k.confirm_activation_temp}"/>
												</c:if>
												<c:if test="${k.confirm_activation_temp == null}">
													Max:${mapss[k.adId]}&nbsp;&nbsp;&nbsp;&nbsp;<input onchange="saveConfirmTemp('${k.id}','0')"   id="${k.id }confirmActivationAmount" name="confirmActivationAmount" size="5" value="0" />
													<input type="hidden" id="${k.id }hidden" value="0"/>
												</c:if>
											</c:when>
											<c:otherwise>
												<input id="${k.id }confirmActivationAmount" name="confirmActivationAmount" size="5" value="${k.confirmActivationAmount }"/>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
											<input type="button" value="通过" onClick="singleDealAudit('${k.id}','1','${mapss[k.adId]}')" />
									</td>
									<td>
											<input id="checkbox" name="checkbox" type="checkbox" value="${k.id}_${k.adId}_${mapss[k.adId]}" />
									</td>
								</c:when>
								
								<c:otherwise>
									<td>
										<input name="confirmActivationAmount" disabled="disabled" size="5" value="${k.confirmActivationAmount }" />
									</td>
									<td align="center">
										<input type="button" value="通过"  disabled="disabled" />
									</td>
									<td>
										<input id="checkbox" name="checkbox" type="checkbox" value="${k.id}_${k.adId}_${mapss[k.adId]}" disabled="disabled" />
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
					<c:if test="${empty devAdEffectConfirmList}">
						<tr>
							<td align="center" colspan="21"  style="text-align: center;">暂无数据！</td>
						</tr>	
					</c:if>
				</form>
				
			</table>
			<div>${pageInfo }
		              	 </div>
			<!--修改end-->
			<c:if test="${not empty devAdEffectConfirmList}">
				<div class="fr"><input type="button" id="btn3" value="全选">批量操作：<input type="button" value="通过" onClick="batchDealAudit('1')" /><!--<input type="button" value="不通过" onclick="batchDealAudit('-1')" />--></div>
			</c:if>
		</div>
	</div>
</div>
</body>
		<!-- 时间控件相关start -->    
		    <link href="${pageContext.request.contextPath}/date/ui.daterangepicker.css" rel="stylesheet" type="text/css" />
		    <link href="${pageContext.request.contextPath}/date/ui-lightness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />    
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/jquery-ui-1.8.4.custom.min.js"></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/daterangepicker.jQuery.js"></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/ui.datepicker-zh-CN.js"></script>
		    <script type="text/javascript" src="${pageContext.request.contextPath}/date/calendar.js"></script>
		<!-- 时间控件相关end -->
</html>
