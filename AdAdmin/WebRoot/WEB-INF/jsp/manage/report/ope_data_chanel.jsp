<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台统计开发者渠道统计</title>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
	</head>
	<body>
		<div class="head_bj head_bj_bt">
			<div class="header rel admin_head">
				<div class="clearfix land_top">
					<div class="land_nav fl">
						数据统计&nbsp;>&nbsp;开发渠道统计
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
						开发者渠道统计
					</div>
					<fieldset class="search_fieldset">
						<legend>
							查询条件
						</legend>
						<form action="manage!opeDevChannelStatList.do" id="opeDevChannelStatList" method="get">
						<input type="hidden" id="ReportOpeDevChannelStatType"
						name="ReportOpeDevChannelStatType" />
						<input type="hidden" name="orderColumn" id="orderColumn" value="${vo.orderColumn }"/>
						<input type="hidden" name="orderCondition" id="orderCondition" value="${vo.orderCondition }"/>
						<table>
							<tr>
								<td>统计类型</td>
								<td>
									<c:choose>
										<c:when test="${vo.statType==2}">
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
													<select name="devStatType">
														<option value="DEV_ID" <c:if test="${vo.devStatType eq 'DEV_ID'}">selected</c:if> >
															开发者ID
														</option>
														<option value="DEV_EMAIL" <c:if test="${vo.devStatType eq 'DEV_EMAIL'}">selected</c:if> >
															开发者
														</option>
														<option value="REAL_NAME" <c:if test="${vo.devStatType eq 'REAL_NAME'}">selected</c:if> >
															联系人
														</option>
													</select>
												</td>
												<td>
													<input name="devStatValue" type="text" value="${vo.devStatValue}"/>
												</td>
												<td>
													<select name="appStatType">
														<option value="APP_ID" <c:if test="${vo.appStatType eq 'APP_ID'}">selected</c:if> >
															应用ID
														</option>
														<option value="APP_NAME" <c:if test="${vo.appStatType eq 'APP_NAME'}">selected</c:if> >
															应用名称
														</option>
													</select>
												</td>
												<td>
													<input name="appStatValue" type="text" value="${vo.appStatValue}"/>
												</td>
												<td>
													渠道代号	
												</td>
												<td>
													<input name="channel" id="channel" type="text" value="${vo.channel}"/>
												</td>
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
												<input type="text" name="startTime" size="15" id="startTime" value="${vo.startTime }" class="Wdate" readonly="readonly"/>
											</td>
											<td>结束时间</td>
											<td>
												<input type="text" name="endTime" size="15" id="endTime" value="${vo.endTime }" class="Wdate" readonly="readonly"/>
											</td>
											<td>
												<input name="" type="button" id="DevChannelStatSearch" value="查询" />
											</td> 
											<td>
												<input name="" type="button" id="DevChannelStatDown" value="导出Excel" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</fieldset>
					${pageInfo.pageInfoStr}
					<table width="100%" cellpadding="0" cellspacing="1"	class="table_bod1 font_stat">
						<tr class="tr_td">
							<td>
								<div class="date"></div><a href="#" onclick="order('STAT_DATE','opeDevChannelStatList','ReportOpeDevChannelStatType')">日期</a>
							</td>
							<td>
								<a href="#" onclick="order('CHANNEL','opeDevChannelStatList','ReportOpeDevChannelStatType')">渠道</a>
							</td>
							<td>
								<a href="#" onclick="order('DEV_ID','opeDevChannelStatList','ReportOpeDevChannelStatType')">开发者ID</a>
							</td>
							<td><a href="#" onclick="order('DEV_EMAIL','opeDevChannelStatList','ReportOpeDevChannelStatType')">开发者</a></td>
							<td>
								<a href="#" onclick="order('REAL_NAME','opeDevChannelStatList','ReportOpeDevChannelStatType')">联系人</a>
							</td>
							<td>
							   
							
								<a href="#" onclick="order('APP_ID','opeDevChannelStatList','ReportOpeDevChannelStatType')">应用ID</a>
								
							</td>
							<td>
								<a href="#" onclick="order('APP_NAME','opeDevChannelStatList','ReportOpeDevChannelStatType')">应用名称</a>
								
							</td>
							<td>
								<a href="#" onclick="order('newVisitorAmount','opeDevChannelStatList','ReportOpeDevChannelStatType')">新用户</a>
							</td>
							<td>
								<a href="#" onclick="order('totalVisitorAmount','opeDevChannelStatList','ReportOpeDevChannelStatType')">累计用户</a>
							</td>
							<td>
								<a href="#" onclick="order('visitorAmount','opeDevChannelStatList','ReportOpeDevChannelStatType')">启动用户</a>
							</td>
							<td>
								<a href="#" onclick="order('visitAmount','opeDevChannelStatList','ReportOpeDevChannelStatType')">启动次数</a>
							</td>
							<td>
								<a href="#" onclick="order('totalVisitAmount','opeDevChannelStatList','ReportOpeDevChannelStatType')">累计启动次数</a>
							</td>
							<td>
								<a href="#" onclick="order('activeVisitorRate','opeDevChannelStatList','ReportOpeDevChannelStatType')">活跃率</a>
							</td>
							<td>
								<a href="#" onclick="order('activeActivationRate','opeDevChannelStatList','ReportOpeDevChannelStatType')">应用激活率</a>
							</td>
							<td>
								<a href="#" onclick="order('scoreVisitorAmount','opeDevChannelStatList','ReportOpeDevChannelStatType')">消耗积分用户数</a>
							</td>
							<td>
								<a href="#" onclick="order('count_Down_Income','opeDevChannelStatList','ReportOpeDevChannelStatType')">确认下载收入</a>
							</td>
						</tr>
						<c:if test="${!empty rdadsr}">
						<tr><td>汇总</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>${sum.newVisitorAmountSum}</td><td>${sum.totalVisitorAmountSum}</td><td>${sum.visitorAmountSum}</td><td>${sum.visitAmountSum}</td><td>${sum.totalVisitAmountSum}</td>
						<td><fmt:formatNumber value="${sum.activeVisitorRateSum*100 }" pattern="##.##" />%</td>
						<td><fmt:formatNumber value="${sum.activeActivationRateSum*100 }" pattern="##.##" />%</td>
						<td>${sum.scoreVisitorAmountSum}</td>
						<td>
							<c:if test="${not empty sum.count_Down_IncomeSum }">
								<escore:formatMoney value="${sum.count_Down_IncomeSum }" maxFractionDigits="2" />
							</c:if>
						</td>						
						</tr>
						<c:forEach items="${rdadsr}" var="obj"> 
						<tr>
							<td>
								<c:if test="${obj.statDate!=null}">
									${obj.statDate}
								</c:if>
								<c:if test="${obj.statDate==null}">
									${obj.timeInterval }
								</c:if>
							</td>
							<td>
								${obj.channel }
							</td>
							<td>
							<a href ="manage!editDev.do?dev_id=${obj.devId }">
									<font color="blue">${obj.devId }</font>
								</a>
							</td>
							<td>
								<a href ="manage!editDev.do?dev_id=${obj.devId }">
								<font color="blue">${obj.devEmail }</font>
								</a>
							</td>
							<td>
							<a href ="manage!editDev.do?dev_id=${obj.devId }">
								<font color="blue">${obj.realName }</font>
								</a>
							</td>
							<td>
							<a href ="manage!detailDevApp.do?appId=${obj.appId }">
									<font color="blue">${obj.appId }</font>
							</a>
							</td>
							<td>
							<a href ="manage!detailDevApp.do?appId=${obj.appId }">
									<font color="blue">${obj.appName }</font>
							</a>
							</td>
							<td>
								${obj.newVisitorAmount }
							</td>
							<td>
								${obj.totalVisitorAmount }
							</td>
							<td>
								${obj.visitorAmount }
							</td>
							<td>
								${obj.visitAmount }
							</td>
							<td>
								${obj.totalVisitAmount }
							</td>
							<td>
								<fmt:formatNumber value="${obj.activeVisitorRate*100 }" pattern="##.##" />%
							</td>
							<td>
								<fmt:formatNumber value="${obj.activeActivationRate*100 }" pattern="##.##" />%
							</td>
							<td>
								${obj.scoreVisitorAmount }
							</td>
							<td>
								<c:if test="${not empty obj.count_Down_Income}">
									<escore:formatMoney value="${obj.count_Down_Income }" maxFractionDigits="2" />
								</c:if>
							</td>
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty rdadsr}">
							<tr>
								<td align="center" colspan="20" style="text-align: center;">暂无记录！</td>
							</tr>	
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
