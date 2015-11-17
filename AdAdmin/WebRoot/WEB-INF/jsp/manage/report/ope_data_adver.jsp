<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台统计广告主统计</title>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
	</head>
	<body>
		<div class="head_bj head_bj_bt">
			<div class="header rel admin_head">
				<div class="clearfix land_top">
					<div class="land_nav fl">
						数据统计&nbsp;>&nbsp;开发者应用统计
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
						广告主统计
					</div>
					<fieldset class="search_fieldset">
						<legend>
							查询条件
						</legend>
						<form action="manage!opeAdverStatList.do" id="opeAdverStatList" method="post">
						<input type="hidden" name="orderColumn" id="orderColumn" value="${vo.orderColumn }"/>
						<input type="hidden" name="orderCondition" id="orderCondition" value="${vo.orderCondition }"/>
						<input type="hidden" name="isNotAll" value="true"/>
						<table>
							<tr>
								<td>
									统计类型
								</td>
								<td>
									<input name="statType" type="radio" value="1" <c:if test="${vo.statType eq '1'}">checked="checked"</c:if> />
									综合数据
									<input name="statType" type="radio" value="2" <c:if test="${vo.statType eq '2'}">checked="checked"</c:if> />
									日数据
								</td>
							</tr>
							<tr>
								<td>
									广告类型
								</td>
								<td>
									<input name="adType" type="radio" value="1" checked="checked"/>
									广告墙
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table>
										<tr>
											<td>
												<select name="adverStatType">
													<option value="ADV_ID" <c:if test="${vo.adverStatType eq 'ADV_ID'}">selected</c:if> >
														广告主ID
													</option>
													<option value="ADV_EMAIL" <c:if test="${vo.adverStatType eq 'ADV_EMAIL'}">selected</c:if> >
														广告主
													</option>
													<option value="REAL_NAME" <c:if test="${vo.adverStatType eq 'REAL_NAME'}">selected</c:if> >
														联系人
													</option>
												</select>
											</td>
											<td>
												<input name="adverStatValue" type="text" value="${vo.adverStatValue}"/>
											</td>
											<td>
												<select name="adStatType">
													<option value="AD_ID" <c:if test="${vo.adStatType eq 'AD_ID'}">selected</c:if> >
														广告ID
													</option>
													<option value="AD_NAME" <c:if test="${vo.adStatType eq 'AD_NAME'}">selected</c:if> >
														广告名称
													</option>
												</select>
											</td>
											<td>
												<input name="adStatValue" type="text" value="${vo.adStatValue}"/>
											</td>
											<td>
												<select name="status">
													<!-- 
													<option value="5" <c:if test="${vo.status eq '5'}">selected</c:if> >
														已下线
													</option>
													<option value="4" <c:if test="${vo.status eq '4'}">selected</c:if> >
														上线
													</option>
													<option value="0" <c:if test="${vo.status eq '0'}">selected</c:if> >
														待审核
													</option>
													 -->
													<option value="-1" <c:if test="${vo.status eq '-1'}">selected</c:if> >全部</option>
												 	<c:forEach items="${adStatusList}" var="obj" varStatus="k">
												 		${obj.adStatusOption }
												 	</c:forEach>
												</select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table>
										<tr>
											<td>
												开始时间
											</td>
											<td>
												<input name="startTime" id="startTime" type="text" value="${vo.startTime}" class="Wdate" readonly="readonly"/>
											</td>
											<td>
												结束时间
											</td>
											<td>
												<input name="endTime" id="endTime" type="text" value="${vo.endTime}" class="Wdate" readonly="readonly"/>
											</td>
											<td>
												<!--<input name="" type="submit" value="查询" />-->
												<input name="" type="button" id="adverManageSearch" value="查询" />
											</td>
											<td>
												<input name="" type="button" id="adverManageDown" value="导出Excel" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<!-- 
							<tr>
								<td colspan="2">
									<a href="#">今天</a>|
									<a href="#">昨天</a>|
									<a href="#">过去一周</a>|
									<a href="#">过去一月</a>|
									<a href="#">本周开始到今天</a>|
									<a href="#">本月开始到今天</a>|
									<a href="#">上一月</a>
								</td>
							</tr>
							 -->
						</table>
						
					</fieldset>
					${pageInfo.pageInfoStr}
					<table width="100%" cellpadding="0" cellspacing="1" class="table_bod1 font_stat">
						<tr class="tr_td">
							<td>
								<a href="#" onClick="order('statDate','opeAdverStatList')">日期</a>
							</td>
							<td>
								<a href="#" onClick="order('advId','opeAdverStatList')">广告主ID</a>
							</td>
							<td>
								<a href="#" onClick="order('advEmail','opeAdverStatList')">广告主</a>
							</td>
							<td>
								<a href="#" onClick="order('realName','opeAdverStatList')">联系人</a>
							</td>
							<td>
								<a href="#" onClick="order('adId','opeAdverStatList')">活动ID</a>
							</td>
							<td>
								<a href="#" onClick="order('adName','opeAdverStatList')">活动名称</a>
							</td>
							<td>
								<a href="#" onClick="order('releaseTime','opeAdverStatList')">上线时间</a>
							</td>
							<td>
								<a href="#" onClick="order('status','opeAdverStatList')">广告状态</a>
							</td>
							<td>
								<a href="#" onClick="order('pvAmount','opeAdverStatList')">广告展示数</a>
							</td>
							<td>
								<a href="#" onClick="order('downAmount','opeAdverStatList')">下载数</a>
							</td>
							<td>
								<a href="#" onClick="order('installAmount','opeAdverStatList')">安装数</a>
							</td>
							<td>
								<a href="#" onClick="order('activationAmount','opeAdverStatList')">激活数</a>
							</td>
							<td>
								<a href="#" onClick="order('cost','opeAdverStatList')">广告费用</a>
							</td>
							<!-- 
							<td>
								推广明细
							</td>
							 -->
						</tr>
						<c:if test="${!empty adverReportList}">
						<tr><td>汇总</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>--</td><td>${sum.pvAmountSum }</td><td>${sum.downAmountSum }</td><td>${sum.installAmountSum }</td><td>${sum.activationAmountSum }</td><td><escore:formatMoney value="${sum.costSum }" maxFractionDigits="2" /></td></tr>
						<c:forEach items="${adverReportList}" var="obj">
						<tr>
							<td>
								<c:if test="${obj.timeInterval != null && obj.timeInterval != ''}">
									${obj.timeInterval }
								</c:if>
								<c:if test="${obj.timeInterval == null || obj.timeInterval == ''}">
									${obj.statDate}
								</c:if>

							</td>
							<td>
							<a href ="manage!advertiserDetal.do?advertiserId=${obj.advId }">
								<font color="blue">${obj.advId }</font>
								</a>
							</td>
							<td>
							<a href ="manage!advertiserDetal.do?advertiserId=${obj.advId }">
								<font color="blue">${obj.advEmail }</font>
								</a>
							</td>
							<td>
							<a href ="manage!advertiserDetal.do?advertiserId=${obj.advId }">
								<font color="blue">${obj.realName }</font>
								</a>
							</td>
							<td>
							<a href ="manage!advExamine.do?id=${obj.adId }">
								<font color="blue">${obj.adId }</font>
								</a>
							</td>
							<td>
							<a href ="manage!advExamine.do?id=${obj.adId }">
								<font color="blue">${obj.adName }</font>
								</a>
							</td>
							<td>
							<fmt:formatDate value="${obj.releaseTime }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss"/>
								
							</td>
							<td>
								${obj.statusName }
								</td>
							<td>
								${obj.pvAmount }
							</td>
							<td>
								${obj.downAmount }
							</td>
							<td>
								${obj.installAmount}
							</td>
							<td>
								${obj.activationAmount }
							</td>
							<td>
								<escore:formatMoney value="${obj.cost }" maxFractionDigits="2" />
							</td>
							<!-- 
							<td align="center">
								<a href="ope_data_advdet.html">查看</a>
							</td>
							 -->
						</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty adverReportList}">
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