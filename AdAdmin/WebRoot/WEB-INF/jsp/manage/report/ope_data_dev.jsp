<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台统计开发者广告统计</title>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
    	<script type="text/javascript">
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
						数据统计&nbsp;>&nbsp;开发者广告统计
					</div>
				</div>
			</div>
		</div>
		<div class="main">

			<div class="content clearfix">
				<jsp:include page="../../../jsp/manage/common/manage_left.jsp" />
				<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">
						开发者广告统计
					</div>
					<fieldset class="search_fieldset">
						<legend>查询条件</legend>
						<form action="manage!devAdStatListManage.do" id="devAdStatListManage" method="get">
						<input type="hidden" name="orderColumn" id="orderColumn" value="${devAdStatListvo.orderColumn }"/>
						<input type="hidden" name="orderCondition" id="orderCondition" value="${devAdStatListvo.orderCondition }"/>
						<table>
							<tr>
								<td>统计类型</td>
								<td>
									<c:choose>
										<c:when test="${devAdStatListvo.statType==2}">
											<input name="statType" type="radio" value="1" />综合数据<input name="statType" type="radio" value="2" checked="checked"/>日数据
										</c:when>
										<c:otherwise>
											<input name="statType" type="radio" value="1" checked="checked"/>综合数据<input name="statType" type="radio" value="2"/>日数据
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td>广告类型</td>
								<td>
									<input name="" type="radio" value="" checked="checked"/>广告墙
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table>
										<tr>
											<td>
												<select name="devType" id="devType">
													<option value="devId">开发者ID</option>
													<option value="realName">联系人</option>
  												</select>
											</td>
											<td>
												<input type="text" name="devText" value="${devAdStatListvo.devText}">
											</td>
											<td>
												<select name="appType" id="appType">
  													<option value="appId">应用ID</option>
													<option value="appName">应用名称</option>
												</select>
											</td>
											<td><input type="text" name="appText" value="${devAdStatListvo.appText}"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table>
										<tr>
											<td>开始时间</td>
											<td>
												<input type="text" name="startTime" size="15" value="${devAdStatListvo.startTime }" class="Wdate" readonly="readonly"/>
											</td>
											<td>结束时间</td>
											<td>
												<input type="text" name="endTime" size="15" value="${devAdStatListvo.endTime }" class="Wdate" readonly="readonly"/>
											</td>
											<td>
												<input name="find" type="button" id="devAdManage" value="查询" />
											</td>
											<td>
												<input name="download" type="button" id="devAdManageDown" value="导出Excel" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						
					</fieldset>
					${pageInfo.pageInfoStr}
					<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat">
						<tr class="tr_td">
							<td><a href="#" onclick="order('STAT_DATE','devAdStatListManage')">日期</a></td>
							<td><a href="#" onclick="order('DEV_ID','devAdStatListManage')">开发者ID</a></td>
							<td><a href="#" onclick="order('REAL_NAME','devAdStatListManage')">联系人</a></td>
							<td><a href="#" onclick="order('APP_ID','devAdStatListManage')">应用ID</a></td>
							<td><a href="#" onclick="order('APP_NAME','devAdStatListManage')">应用名称</a></td>
							<td><a href="#" onclick="order('RELEASE_TIME','devAdStatListManage')">上线时间</a></td>
							<td><a href="#" onclick="order('ADWALL_PV_AMOUNT','devAdStatListManage')">广告墙打开数</a></td>
							<td><a href="#" onclick="order('DOWN_AMOUNT','devAdStatListManage')">应用下载数</a></td>
							<td><a href="#" onclick="order('ACTIVATION_AMOUNT','devAdStatListManage')">应用激活数</a></td>
							<td><a href="#" onclick="order('ACTIVATION_RATE','devAdStatListManage')">应用激活率</a></td>
							<td><a href="#" onclick="order('FORECAST_ACTIVATION_INCOME','devAdStatListManage')">预计下载收入</a></td>
							<td><a href="#" onclick="order('confirm_activation_amount','devAdStatListManage')">确认激活数</a></td>
							<td><a href="#" onclick="order('CONFIRM_ACTIVATION_INCOME','devAdStatListManage')">确认下载收入</a></td>
							<td><a href="#" onclick="order('FORECAST_USCORE_INCOME','devAdStatListManage')">预计消耗积分</a></td>
							<td><a href="#" onclick="order('CONFIRM_USCORE_INCOME','devAdStatListManage')">确认消耗积分收入</a></td>
						</tr>
						<c:if test="${!empty rdadsr}">
							<td>汇总</td>
							<td align="center">--</td>
							<td align="center">--</td>
							<td align="center">--</td>
							<td align="center">--</td>
							<td align="center">--</td>
							<td>
								${summary.adwallPvAmount }
								<c:if test="${summary.adwallPvAmount==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
								${summary.downAmount }
								<c:if test="${summary.downAmount==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
								${summary.activationAmount }
								<c:if test="${summary.activationAmount==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
								<c:if test="${summary.activationRate!=null}">
									<fmt:formatNumber value="${summary.activationRate*100 }" pattern="##.##" />%
								</c:if>
								<c:if test="${summary.activationRate==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
								<escore:formatMoney value="${summary.forecastActivationIncome }" maxFractionDigits="2" />
								<c:if test="${summary.forecastActivationIncome==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
							<c:choose>
									<c:when test="${summary.forecastActivationIncome!=null}">
										<c:choose>
										<c:when test="${devAdStatListvo.startTime >= numberformat_StartTime }">
										<fmt:formatNumber value="${summary.confirm_activation_amount }" type="number" pattern="0"/>
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${summary.confirm_activation_amount }" type="number" pattern="0.0"/>
										</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<div align="center">--</div>
									</c:otherwise>
							</c:choose>
							
							</td>
							<td>
								<escore:formatMoney value="${summary.confirmActivationIncome }" maxFractionDigits="2" />
								<c:if test="${summary.confirmActivationIncome==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
								<escore:formatNumber value="${summary.forecastUscoreIncome }" maxFractionDigits="2"/>
								<c:if test="${summary.forecastUscoreIncome==null}">
									<div align="center">--</div>
								</c:if>
							</td>
							<td>
								<escore:formatMoney value="${summary.confirmUscoreIncome }" maxFractionDigits="2" />
								<c:if test="${summary.confirmUscoreIncome==null}">
									<div align="center">--</div>
								</c:if>
							</td>
						</c:if>
						<c:if test="${!empty rdadsr}">
						<c:forEach items="${rdadsr}" var="report" varStatus="status">
						<tr>
							<td>
								<c:if test="${report.statDate!=null}">
									${report.statDate}
								</c:if>
								<c:if test="${report.statDate==null}">
									${report.timeInterval }
								</c:if>
							</td>
							<td>
							<a href ="manage!editDev.do?dev_id=${report.devId }">
								<c:choose>
									<c:when test="${report.zb_status == 1}">
										<font color="#24a319"><b>*${report.devId}</b></font>  
									</c:when>
									<c:when test="${report.zb_status == 2}">
										<font color="red">ZB${report.devId }</font>  
									</c:when>
									<c:otherwise>
									<font color="blue">	${report.devId}</font>
									</c:otherwise>
								</c:choose>
								</a>
							</td>
							<td>
							<a href ="manage!editDev.do?dev_id=${report.devId }">
								<c:choose>
									<c:when test="${report.zb_status == 1}">
										<font color="#24a319"><b>*${report.realName}</b></font>  
									</c:when>
									<c:when test="${report.zb_status == 2}">
										<font color="red">ZB${report.realName }</font>  
									</c:when>
									<c:otherwise>
									<font color="blue">	${report.realName}</font>
									</c:otherwise>
								</c:choose>
								</a>
							</td>
							<td>
							<a href ="manage!detailDevApp.do?appId=${report.appId }">
								<c:choose>
									<c:when test="${report.zb_status == 1}">
										<font color="#24a319"><b>*${report.appId}</b></font>  
									</c:when>
									<c:when test="${report.zb_status == 2}">
										<font color="red">ZB${report.appId }</font>  
									</c:when>
									<c:otherwise><font color="blue">${report.appId}</font></c:otherwise>
								</c:choose>
								</a>
							</td>
							<td>
							<a href ="manage!detailDevApp.do?appId=${report.appId }">
								<c:choose>
									<c:when test="${report.zb_status == 1}">
										<font color="#24a319"><b>*${report.appName}</b></font>  
									</c:when>
									<c:when test="${report.zb_status == 2}">
										<font color="red">ZB${report.appName}</font>  
									</c:when>
									<c:otherwise><font color="blue">${report.appName}</font></c:otherwise>
								</c:choose>
								</a>
							</td>
							<td>
								<fmt:formatDate value="${report.releaseTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								${report.adwallPvAmount }
							</td>
							<td>
								${report.downAmount }
							</td>
							<td>
								${report.activationAmount }
							</td>
							<td>
								<fmt:formatNumber value="${report.activationRate*100 }" pattern="##.##" />%
							</td>
							<td>
								<escore:formatMoney value="${report.forecastActivationIncome }" maxFractionDigits="2" />
							</td>
							<td>
							<c:if test="${report.statDate!=null}">
								<c:choose>
									<c:when test="${report.statDate >= numberformat_StartTime }">
										<c:choose>
										<c:when test="${report.confirm_activation_amount == null }">
										0
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${report.confirm_activation_amount }" type="number" pattern="0"/>
										</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
										<c:when test="${report.confirm_activation_amount == null }">
										0.0
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${report.confirm_activation_amount }" type="number" pattern="0.0"/>
										</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:if> 
							<c:if test="${report.statDate==null}">
								<c:set var="statDate" value="${fn:substring(report.timeInterval, 0, 10)}" />
								<c:choose>
									<c:when test="${statDate >= numberformat_StartTime }">
										<c:choose>
										<c:when test="${report.confirm_activation_amount == null }">
										0
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${report.confirm_activation_amount }" type="number" pattern="0"/>
										</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
										<c:when test="${report.confirm_activation_amount == null }">
										0.0
										</c:when>
										<c:otherwise>
										<fmt:formatNumber value="${report.confirm_activation_amount }" type="number" pattern="0.0"/>
										</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:if>
							
							</td>
							<td>
								<escore:formatMoney value="${report.confirmActivationIncome }" maxFractionDigits="2" />
							</td>
							<td>
								<escore:formatNumber value="${report.forecastUscoreIncome }" maxFractionDigits="2"/>
							</td>
							<td>
								<escore:formatMoney value="${report.confirmUscoreIncome }" maxFractionDigits="2" />
							</td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty rdadsr}">
							<td colspan="14" align="center" style="text-align: center;">暂无记录！</td>
						</c:if>
					</table>
					${pageInfo.pageInfoStr}
				</form>
				</div>
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
