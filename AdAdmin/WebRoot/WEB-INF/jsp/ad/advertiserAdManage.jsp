<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台广告主广告管理</title>
		<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
		<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	</head>
	<body>
		<div class="main">

			<div class="content clearfix">
					<div class="content_right admin_right">
						<div class="bord_bom1px">
							广告主广告管理
						</div>
						<form action="manage!findAdByCondition.do" method="get">
						<div>
							<table class="mar_bom10">
								<tr>
									<td>
										<input name="value" type="text" value="${value}" />
									</td>
									<td>
										<select name="name">
											<option value="ID"
												<c:if test="${name eq 'ID'}">selected</c:if>>
												广告ID
											</option>
											<option value="AD_NAME"
												<c:if test="${name eq 'AD_NAME'}">selected</c:if>>
												广告名称
											</option>
											<option value="ADV_EMAIL"
												<c:if test="${name eq 'ADV_EMAIL'}">selected</c:if>>
												广告主
											</option>
										</select>
									</td>
									<td>状态：
										<select name="status">
											 <option value="">全部</option>
										 	<c:forEach items="${adStatusList}" var="obj" varStatus="k">
										 		${obj.adStatusOption }
										 	</c:forEach>
										</select>
									</td>
									<td>
									操作系统:
									<select name="os" id="os"  >
									    <option value="">全部</option> 										
										<option value="android"
										    <c:if test="${os eq 'android'}">selected</c:if>
											/>android</option>
										<option value="ios"
											<c:if test="${os eq 'ios'}">selected</c:if>
											/>ios</option>  
									</select>
									</td>
									<td>
									广告形式:
									<select name="type_id" id="type_id"  >
									    <option value="">全部</option> 
										<c:forEach items="${typeList}" var="entry">
										<option value="${entry.id}"
										    <c:if test="${entry.id eq type_id}">selected</c:if>
											>${entry.name}</option> 
										</c:forEach>
									</select>
									</td>
									<td>
										<input type=submit name="submit" value="搜索" />
									</td>
								</tr>
							</table>
						</div>
						
						${pageInfo.pageInfoStr}
						<table width="100%" cellpadding="0" cellspacing="1"
							class="table_bod1 font_stat">
							<tr class="tr_td">
								<td>
									广告ID
								</td>
								<td>
									广告名称
								</td>
								<td>
									操作系统
								</td>
								<td>
									广告形式
								</td>
								<td>
									广告主
								</td>
								<td>
									联系人
								</td>
							
								<td>
									上线时间
								</td>
								<td>
									下线时间
								</td>
								<td>
									状态
								</td>
								<td>
									结算单价
								</td>
								<td>
									财务单价
								</td>
								<td>
									总预算
								</td>
								<td>
									总预算余额
								</td>
								<td>
									日预算
								</td>
								<td>
									日预算余额
								</td>
								
								<td>
									活动指数
								</td>
								
								<td>
									操作
								</td>
							</tr>
							<c:if test="${!empty allAdList}">
							<c:forEach items="${allAdList}" var="obj" varStatus="k">
								<tr>
									<td>
										${obj.id }
									</td>
									<td>
										<a href="manage!advExamine.do?id=${obj.id}"><font color="blue">${obj.adName }</font></a>
									</td>
									<td>
									${obj.os}
									</td>
									<td>
								      <c:forEach items="${typeList}" var="entry">
										  <c:if test="${entry.id eq obj.type_id}">
										     ${entry.name}
										  </c:if>		
									  </c:forEach>
									</td>
									<td>
										<a href="manage!advertiserDetal.do?advertiserId=${obj.adv_id}"><font color="blue">${obj.advEmail}</font></a>
									</td>
									<td>
										<c:forEach items="${advertiserName}" var="entry">
											<c:if test="${entry.key==obj.id}">${entry.value}</c:if>
										</c:forEach>
									</td>
									
									<td>
									<fmt:formatDate value="${obj.releaseTime }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss"/>
										
									</td>
									<td>
									<fmt:formatDate value="${obj.down_time }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss"/>
										
									</td>
									<td>
										${obj.statusName }
										
									</td>
									<td>
										<escore:formatMoney value="${obj.adPrice }" maxFractionDigits="2" />
									</td>
									<td>
										<escore:formatMoney value="${obj.finance_price }" maxFractionDigits="2" />
									</td>
									<td>
										<escore:formatMoney value="${obj.budget }" maxFractionDigits="2" />
									</td>
									<td>
										<escore:formatMoney value="${obj.budgetBalance }" maxFractionDigits="2" />
									</td>
									<td>
										<c:if test="${obj.budgetDay != '' && obj.budgetDay != '0.0'}"><escore:formatMoney value="${obj.budgetDay }" maxFractionDigits="2" /></c:if>
										<c:if test="${obj.budgetDay == '' || obj.budgetDay == '0.0'}">--</c:if>
									</td>
									<td>
										<c:if test="${obj.budgetDayBalance == '' || obj.budgetDayBalance == '0.0'}">--</c:if>
										<c:if test="${obj.budgetDayBalance != '' && obj.budgetDayBalance != '0.0'}"><escore:formatMoney value="${obj.budgetDayBalance }" maxFractionDigits="2" /></c:if>
									</td>
									
									<td>
										${obj.order_num}
									</td>
									
									<td align="center">				
										<input type="button" value="审核通过" 
											<c:if test="${obj.status != '1' || (obj.resourceSize eq 0 && obj.os eq 'android' && obj.adType != 1)}">disabled="disabled"</c:if>
											onclick="window.location.href='manage!updateAdvStatus.do?advertisementId=${obj.id}&status=2'" />
										<br />
										<input type="button" value="审核不通过"
											<c:if test="${obj.status != '1'}">disabled="disabled"</c:if>
											onclick="window.location.href='manage!updateAdvStatus.do?advertisementId=${obj.id}&status=3'" />
										<br />
										<c:if test="${obj.status == '2' || obj.status == '5' || obj.status == '8' }">
										<input type="button" value="上线" onClick="window.location.href='manage!updateAdvStatus.do?advertisementId=${obj.id}&status=4'" />
										</c:if>
										<c:if test="${obj.status != '2' && obj.status != '5' && obj.status != '8' }">
										<input type="button" value="上线" disabled="disabled" onClick="window.location.href='manage!updateAdvStatus.do?advertisementId=${obj.id}&status=4'" />
										</c:if>
										<br/>
										<input type="button" value="下线"
											<c:if test="${obj.status ne '4'}">disabled="disabled"</c:if>
											onclick="window.location.href='manage!updateAdvStatus.do?advertisementId=${obj.id}&status=5'" />
									</td>
								</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty allAdList}">
								<tr>
									<td align="center" colspan="20" style="text-align: center;">暂无记录！</td>
								</tr>	
							</c:if>
						</table>
						<div>
							${pageInfo.pageInfoStr}
						</div>
						</form>
					</div>
			</div>
		</div>
	</body>
</html>
