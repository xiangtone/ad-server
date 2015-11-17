<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
//list鼠标路过效果
$(document).ready(function (){
	// android效果录入明细
	$("#activityFindAll_Search").click(
			function() {
					$("#my_form").attr("action",
						"manage!advConfirmationDetailList.do").submit();
			});
	// android效果录入明细 数据导出
	$("#activityFindAll_Report").click(
			function() {
				$("#my_form").attr("action",
						"manage!advConfirmationDetailDownList.do").submit();
	});
});

function refresh(){
	$("#my_form").submit();
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>android效果录入明细 </legend>
						<div id="search_bar">
							<form  method="post" id="my_form" action="manage!advConfirmationDetailList.do">
								<input type="hidden" name="pageRecord"  value="${pageInfo.pageSize}"/>
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>发生日期</td>
													<td>
														<input name="static_start_date" id="static_start_date" type="text"	value="${bean.static_start_date}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
														至<input name="static_end_date" id="static_end_date" type="text"	value="${bean.static_end_date}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
													</td>
													<td>渠道包ID</td>
													<td>
														<input name="package_id" id="package_id" type="text" value="${bean.package_id}" maxlength="50" />
													</td>
													<td>媒体名称</td>
													<td>
														<input name="media_name" type="text" value="${bean.media_name}" maxlength="50" />
													</td>
												</tr>
												<tr>
													<td>活动名称/ID</td>
													<td>
														<input name="campaign" type="text" value="${bean.campaign}" maxlength="50" />
													</td>
													<td>媒体类型</td>
													<td>
														<select name="media_type" style="width: 157px;">
															<option value="">全部</option>
															<option <c:if test="${bean.media_type==0}"> selected="selected"</c:if> value="0">平台</option>
															<option <c:if test="${bean.media_type==1}"> selected="selected"</c:if> value="1">渠道</option>
														</select>
													</td>	
													<td>对账情况</td>
													<td>
														<select name="balance_status" style="width: 157px;">
															<option value="">全部</option>
															<option <c:if test="${bean.balance_status==0}"> selected="selected"</c:if> value="0">未对账</option>
															<option <c:if test="${bean.balance_status==1}"> selected="selected"</c:if> value="1">已对账</option>
														</select>
													</td>						
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button id="activityFindAll_Search" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
												
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<input type="button" value="导出数据" id="activityFindAll_Report" />
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
							<tr class="tr_td tb_head">
								<th width="3%">序号</th>					
								<th>ID</th>							
								<th>编号</th>
								<th>渠道包ID</th>
								<th>文件名</th>
								<th>发生时间</th>
								<th>活动ID</th>
								<th>活动名称</th>
								<th>媒体名称</th>
								<th>渠道包号</th>
								<th>媒体类型</th>
								<th>单价</th>
								<th>确认数</th>
								<th>广告形式</th>
								<th>平台激活数</th>
								<th>广告主确认数</th>
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
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td><font color="red">${sum.sum_platform_amount}</font></td>		
								<td>-</td>
								<td>-</td>
								<td><font color="red">${sum.sum_amount}</font></td>			
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td style="text-align: center;">
											${status.index+1}
										</td>
										<td>
											<font <c:if test="${vo.balance_id eq null}">color="red"</c:if> >
												${vo.id}
											</font>
										</td>
										<td>
											<escore:subStr len="20" value="${vo.code}" />
										</td>
										<td>${vo.package_id}</td>
										<td>
											<escore:subStr len="25" value="${vo.file_name}" />
										</td>
										<td>${vo.static_date}</td>
										<td>${vo.campaign_id}</td>
										<td>${vo.campaign_name}</td>
										<td>${vo.media_name}</td>
										<td>${vo.remarks}</td>
										<td>
											<c:choose>
												<c:when test="${vo.media_type ==0}">
													平台
												</c:when>
												<c:when test="${vo.media_type== 1}">
									     			 渠道
												</c:when>
												<c:otherwise>
									 				未知
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											${vo.out_price}
										</td>
										<td>
											${vo.confirm_amount}
										</td>
										<td>${vo.type_name}</td>
										<td>${vo.sys_activate}</td>
										<td>${vo.confirm_num}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
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