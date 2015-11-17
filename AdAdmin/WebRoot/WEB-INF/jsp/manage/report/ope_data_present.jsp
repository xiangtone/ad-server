<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后台统计开发者赠送记录统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	function clearStatDate() {
		$('#stat_date_begin').val('');
		$('#stat_date_end').val('');
	}
	function refresh() {
		$("#my_form").submit();
	}
	
	$(document).ready(function() {
	});
</script>
</head>


<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>开发者赠送记录</legend>
						<form action="manage!devRewardList.do"	id="my_form" method="post">
							<table width="100%">
								<tr>
									<td>
										<table width="100%">
											<tr>
												<td>开发者ID</td>
												<td>
													<input name="dev_Id" id="dev_Id" type="text" value="${bean.dev_Id}"	onkeyup="value=value.replace(/[^\d]/g,'')" />
												</td>
												<td>开发者名称</td>
												<td><input name="dev_Name" id="dev_Name" type="text" value="${bean.dev_Name }" /></td>
												<td>
													开始时间 <input type="text" name="stat_date_begin" id="stat_date_begin" size="15" value="${bean.stat_date_begin }" class="Wdate" onfocus="WdatePicker()" />
													结束时间 <input type="text" name="stat_date_end" id="stat_date_end" size="15" 	value="${bean.stat_date_end }" class="Wdate" onfocus="WdatePicker()" />
													<input type="button" id="statTimeClear" name="statTimeClear" maxlength="10"	value="清空" onclick="clearStatDate()" />
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
					</fieldset>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" class="font_stat" id="tb">
						<tr class="tr_td">
							<th>赠送时间</th>
							<th>开发者ID</th>
							<th>开发者</th>
							<th>赠送金额</th>
						</tr>
						<c:if test="${!empty entry}">
							<td>汇总</td>
							<td align="center">--</td>
							<td align="center">${reward_money}</td>
							<td style="color: red;"><escore:formatMoney
									value="${reward_money}" maxFractionDigits="2" />
							</td>
						</c:if>
						<c:if test="${!empty entry}">
							<c:forEach items="${entry}" var="k" varStatus="status">
								<tr>
									<td><fmt:formatDate value="${k.create_time}"
											pattern="yyyy-MM-dd" />
									</td>
									<td>${k.dev_id }</td>
									<td>${k.dev_Email }</td>
									<td><escore:formatMoney value="${k.money }"
											maxFractionDigits="2" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty entry}">
							<td colspan="14" align="center" style="text-align: center;">暂无记录！</td>
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
