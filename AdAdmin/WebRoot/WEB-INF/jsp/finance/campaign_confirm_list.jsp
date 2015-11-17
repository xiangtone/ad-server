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
<title>运营管理后台活动查询统计 </title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript"><!--

function refresh(){
	$("#campaignConfirm").submit();
}

/**
 *  修改数据
 *	
 **/
function editConfirmation(id){
	var url = "${pageContext.request.contextPath}/finance/campaignBlanceAcount.do?id="+id;
	new $.dialog({
		title:'修改数据',
		page:url,
		width:450,
		height:290,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

//发布
function publish(id){
	var url = "manage!campaignConfirmpublish.do?Id="+id;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("保存成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
	//取消操作	
}
/**
 * tb效果
 *	
 **/
$(document).ready(function (){
});

</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>活动确认数</legend>
						<div id="search_bar">
							<form action="${pageContext.request.contextPath}/finance/campaignConfirmList.do" method="post" id="campaignConfirm">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>活动名称</td>
													<td><input name="campaign_name" type="text" value="${bean.campaign_name}" /></td>
													<td>活动ID</td>
													<td><input name="campaign_id" type="text" value="${bean.campaign_id}" /></td>
													<td>状态</td>
													<td>
														<select name="status"  style="width: 155px;">
															<option value="">全部</option>
															<option value="0" <c:if test="${bean.status == 0}">selected="selected"</c:if>>未发布</option>
															<option value="1" <c:if test="${bean.status ==1}">selected="selected"</c:if>>已发布</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>生成发票</option>
														</select>
													</td>
												</tr>
												<tr>
													<td >广告主ID</td>
													<td><input name="adv_id" type="text" value="${bean.adv_id}" /></td>
													<td >效果发生时间</td>
													<td style="color: blue;">
														<input type="text" id="month_stat_date" name="month_stat_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}"/>						
														至<input type="text" id="month_end_date" name="month_end_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_end_date}"/>
													</td>	
														<td  colspan="2"></td>						
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
						</div>
					</fieldset>
					
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
						<tr class="tr_td">	
						<th order="false">
							<input type="checkbox" id="btn7" />
						</th>
							<th width="3%">序号</th>
							<th>效果发生时间</th>
							<th>广告主id</th>
							<th>活动id</th>
							<th>活动名称</th>
							<th>预确认数</th>
							<th>确认数</th>
							<th>单价</th>
							<th>结算方式</th>
							<th>结算金额</th>
							<th>平台类型</th>
							<th>状态</th>
							<th>录数人</th>
							<th>操作</th>
						</tr>
						<tr>
							<td align="center">
								汇总
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
							<td align="center">
								--
							</td>
							<td align="center">
								--
							</td>
							<td align="center">
								<font color="red">${sum.sum_forecast_amount}</font>
							</td>
							<td align="center">
								<font color="red">${sum.sum_income_amount}</font>
							</td>
							<td align="center">
								--
							</td>
							<td align="center">
								--
							</td>
								<td align="center">
								<font color="red"><escore:formatMoney
												value="${sum.sum_income_money}" maxFractionDigits="2" />
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
							<td align="center">
								--
							</td>		
						</tr>
						<c:forEach items="${list}" var="vo" varStatus="status">
							<tr style="text-align: center;">
							<td align="center">
								<input  name="com_checkbox" type="checkbox" adv_id="${vo.adv_id}" value="${vo.id }^${vo.adv_id}" <c:if test="${vo.status== 2}">disabled="disabled"</c:if> />
							</td>
								<td style="text-align: center;">
									${status.index+1}
								</td>
								<td>${vo.month_stat_date}至${vo.month_end_date}</td>
								<td>${vo.adv_id}</td>
								<td>${vo.campaign_id}</td>
								<td>${vo.campaign_name}</td>
								<td>${vo.forecast_amount}</td>
								<td>${vo.income_amount}</td>
								<td>${vo.price}</td>
								<td>${vo.charge_type}</td>
								<td><fmt:formatNumber  type="number" pattern="###,###" value="${vo.income_money}"/></td>
								<td>${vo.os}</td>
								<td>
									<c:choose>
										<c:when test="${vo.status == 1}">
											已发布
										</c:when>
										<c:when test="${vo.status == 2}">
											生成发票
										</c:when>
										<c:otherwise>
							 				未发布
										</c:otherwise>
									</c:choose>
								</td>
								<td>${vo.create_user_name}</td>
								<td>
									<c:if test="${vo.status == 0 || vo.status == 2}">
										<input type="button" value="修改" onclick="editConfirmation('${vo.id}');" style="cursor: pointer;" />
										<input type="button" value="发布" onclick="publish('${vo.id}')" style="cursor: pointer;" />
									</c:if>
									<c:if test="${vo.status == 1 }">
										<input type="button" value="修改" disabled="disabled" onclick="editConfirmation('${vo.id}');" style="cursor: pointer;" />
										<input type="button" value="发布" disabled="disabled" onclick="publish('${vo.id}')" style="cursor: pointer;" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty list}">
							<tr>
								<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
							</tr>
						</c:if>
					</table>
				</div>
				<div>${pageInfo.pageInfoStr}</div>
			</div>
		</div>
	</div>
</body>
</html>