<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台统计开发者应用统计</title>
<link href="${pageContext.request.contextPath}/css/main.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#devType").val('${vo.devType}');
		$("#appType").val('${vo.appType}');
	});
</script>
<style>
table,td{ border:1px solid #000; border-collapse:collapse}
</style>
</head>

<body>
	<div class="head_bj head_bj_bt">
		<div class="header rel admin_head">
			<div class="clearfix land_top">
				<div class="land_nav fl">数据统计&nbsp;>&nbsp;开发者应用统计</div>
			</div>
		</div>
	</div>
	<div class="main">

		<div class="content clearfix">
			<jsp:include page="../../../jsp/manage/common/manage_left.jsp" />
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">开发者应用统计</div>
					<fieldset class="search_fieldset">
						<legend> 查询条件 </legend>
						<form action="manage!devAppStatListManage.do"
							id="devAppStatListManage" method="get">
							<input type="hidden" name="orderColumn" id="orderColumn"
								value="${vo.orderColumn }" /> <input type="hidden"
								name="orderCondition" id="orderCondition"
								value="${vo.orderCondition }" />
							<table>
								<tr>
									<td>统计类型</td>
									<td><c:choose>
											<c:when test="${vo.statType==2}">
												<input name="statType" type="radio" value="1" />综合数据<input
													name="statType" type="radio" value="2" checked="checked" />日数据
										</c:when>
											<c:otherwise>
												<input name="statType" type="radio" value="1"
													checked="checked" />综合数据<input name="statType"
													type="radio" value="2" />日数据
										</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td>广告类型</td>
									<td><input name="" type="radio" value="" checked="checked" />广告墙
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<table>
											<tr>
												<td><select name="devType" id="devType">
														<option value="devId">开发者ID</option>
														<option value="realName">联系人</option>
												</select>
												</td>
												<td><input type="text" name="devText"
													value="${vo.devText}">
												</td>
												<td><select name="appType" id="appType">
														<option value="appId">应用ID</option>
														<option value="appName">应用名称</option>
												</select>
												</td>
												<td><input type="text" name="appText"
													value="${vo.appText}" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<table>
											<tr>
												<td>开始时间</td>
												<td><input type="text" name="startTime" size="15"
													id="startTime" value="${vo.startTime }" class="Wdate"
													readonly="readonly" />
												</td>
												<td>结束时间</td>
												<td><input type="text" name="endTime" size="15"
													id="endTime" value="${vo.endTime }" class="Wdate"
													readonly="readonly" />
												</td>
												<td><input name="find" type="button" id="devAppManage"
													value="查询" />
												</td>
												<td><input name="download" type="button"
													id="devAppManageDown" value="导出Excel" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
					</fieldset>
					${pageInfo.pageInfoStr}
					<table width="100%" cellpadding="0" cellspacing="1"
						class="table_bod1 font_stat" >
						<tr class="tr_td tb_head">
							<td><div class="date"></div> <a href="#"
								onclick="order('STAT_DATE','devAppStatListManage')">日期</a></td>
							<td><a href="#"
								onclick="order('DEV_ID','devAppStatListManage')">开发者ID</a></td>
							<td><a href="#"
								onclick="order('REAL_NAME','devAppStatListManage')">联系人</a></td>
							<td><a href="#"
								onclick="order('APP_ID','devAppStatListManage')">应用ID</a></td>
							<td><a href="#"
								onclick="order('APP_NAME','devAppStatListManage')">应用名称</a></td>
							<td><a href="#">SDK版本号</a></td>
							<td><a href="#"
								onclick="order('RELEASE_TIME','devAppStatListManage')">上线时间</a>
							</td>
							<td><a href="#"
								onclick="order('TOTAL_VISITOR_AMOUNT','devAppStatListManage')">累计用户</a>
							</td>
							<td><a href="#"
								onclick="order('NEW_VISITOR_AMOUNT','devAppStatListManage')">新用户</a>
							</td>
							<td><a href="#"
								onclick="order('NEW_VISITOR_RATE','devAppStatListManage')">新用户比例</a>
							</td>
							<td><a href="#"
								onclick="order('VISITOR_AMOUNT','devAppStatListManage')">启动用户</a>
							</td>
							<td><a href="#"
								onclick="order('VISIT_AMOUNT','devAppStatListManage')">启动次数</a>
							</td>
							<td><a href="#"
								onclick="order('AVG_STAY_TIME','devAppStatListManage')">平均使用时长</a>
							</td>
							<td><a href="#"
								onclick="order('ACTIVE_VISITOR_RATE','devAppStatListManage')">活跃率</a>
							</td>
							<td><a href="#"
								onclick="order('AVG_RETURNING_RATE','devAppStatListManage')">回访率</a>
							</td>
							<td><a href="#"
								onclick="order('SCORE_VISITOR_AMOUNT','devAppStatListManage')">消耗积分用户数</a>
							</td>
							<td><a href="#"
								onclick="order('SCORE_VISITOR_RATE','devAppStatListManage')">消耗积分用户数率</a>
							</td>
						</tr>
						<c:if test="${!empty rdadsr}">
							<tr>
								<td>汇总</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td align="center">--</td>
								<td>${summary.totalVisitorAmount } <c:if
										test="${summary.totalVisitorAmount==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td>${summary.newVisitorAmount } <c:if
										test="${summary.newVisitorAmount==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td><c:if test="${summary.newVisitorRate!=null}">
										<fmt:formatNumber value="${summary.newVisitorRate*100 }"
											pattern="##.##" />%
								</c:if> <c:if test="${summary.newVisitorRate==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td>${summary.visitorAmount} <c:if
										test="${summary.visitorAmount==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td>${summary.visitAmount } <c:if
										test="${summary.visitAmount==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td>${summary.avgTime } <c:if
										test="${summary.avgStayTime==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td><c:if test="${summary.activeVisitorRate!=null}">
										<fmt:formatNumber value="${summary.activeVisitorRate*100 }"
											pattern="##.##" />%
								</c:if> <c:if test="${summary.activeVisitorRate==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td><c:if test="${summary.avg_returning_rate!=null}">
										<fmt:formatNumber value="${summary.avg_returning_rate*100 }"
											pattern="##.##" />%
								</c:if> <c:if test="${summary.avg_returning_rate==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td>${summary.scoreVisitorAmount } <c:if
										test="${summary.scoreVisitorAmount==null}">
										<div align="center">--</div>
									</c:if>
								</td>
								<td><c:if test="${summary.scoreVisitorRate!=null}">
										<fmt:formatNumber value="${summary.scoreVisitorRate*100 }"
											pattern="##.##" />%
								</c:if> <c:if test="${summary.scoreVisitorRate==null}">
										<div align="center">--</div>
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty rdadsr}">
							<c:forEach items="${rdadsr}" var="report" varStatus="status">
								<tr>
									<td><c:if test="${report.statDate!=null}">
									${report.statDate}
								</c:if> <c:if test="${report.statDate==null}">
									${report.timeInterval }
								</c:if>
									</td>
									<td>
										<!--
								${report.devEmail }
							--> <a
										href="manage!editDev.do?dev_id=${report.devId }">
											<c:choose>
												<c:when test="${report.zb_status == 1}">
													<font color="#24a319"><b>*${report.devId}</b> </font>
												</c:when>
												<c:when test="${report.zb_status == 2}">
													<font color="red">ZB${report.devId}</font>
												</c:when>
												<c:otherwise>
													<font color="blue">${report.devId}</font>
												</c:otherwise>
											</c:choose> </a>
									</td>
									<td><a
										href="manage!editDev.do?dev_id=${report.devId }">
											<c:choose>
												<c:when test="${report.zb_status == 1}">
													<font color="#24a319"><b>*${report.realName}</b> </font>
												</c:when>
												<c:when test="${report.zb_status == 2}">
													<font color="red">ZB${report.realName}</font>
												</c:when>
												<c:otherwise>
													<font color="blue">${report.realName}</font>
												</c:otherwise>
											</c:choose> </a>
									</td>
									<td><a
										href="manage!detailDevApp.do?appId=${report.appId }">
											<c:choose>
												<c:when test="${report.zb_status == 1}">
													<font color="#24a319"><b>*${report.appId}</b> </font>
												</c:when>
												<c:when test="${report.zb_status == 2}">
													<font color="red">ZB${report.appId}</font>
												</c:when>
												<c:otherwise>
													<font color="blue">${report.appId}</font>
												</c:otherwise>
											</c:choose> </a>
									</td>
									<td><a
										href="manage!detailDevApp.do?appId=${report.appId }">
											<c:choose>
												<c:when test="${report.zb_status == 1}">
													<font color="#24a319"><b>*${report.appName}</b> </font>
												</c:when>
												<c:when test="${report.zb_status == 2}">
													<font color="red">ZB${report.appName}</font>
												</c:when>
												<c:otherwise>
													<font color="blue">${report.appName}</font>
												</c:otherwise>
											</c:choose> </a>
									</td>
									<td ><c:if test="${!empty report.sdk_versionList}">
											<c:forEach items="${report.sdk_versionList}" var="sdk"
												varStatus="status">
												<c:if test="${!status.last}">${sdk.sdkVersion}<br/>
													<hr/>
												</c:if>
												<c:if test="${status.last}">${sdk.sdkVersion}</c:if>
											</c:forEach>
										</c:if>
									</td>
									<td><fmt:formatDate value="${report.releaseTime }"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>${report.totalVisitorAmount }</td>
									<td>${report.newVisitorAmount }</td>
									<td><fmt:formatNumber
											value="${report.newVisitorRate*100 }" pattern="##.##" />%</td>
									<td>${report.visitorAmount}</td>
									<td>${report.visitAmount }</td>
									<td>${report.avgTime }</td>
									<td><fmt:formatNumber
											value="${report.activeVisitorRate*100 }" pattern="##.##" />%
									</td>
									<td><fmt:formatNumber
											value="${report.avg_returning_rate*100 }" pattern="##.##" />%
									</td>
									<td>${report.scoreVisitorAmount }</td>
									<td><fmt:formatNumber
											value="${report.scoreVisitorRate*100 }" pattern="##.##" />%
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty rdadsr}">
							<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
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
<link
	href="${pageContext.request.contextPath}/date/ui.daterangepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/date/ui-lightness/jquery-ui-1.8.4.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/date/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/date/daterangepicker.jQuery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/date/ui.datepicker-zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/date/calendar.js"></script>
<!-- 时间控件相关end -->
</html>
