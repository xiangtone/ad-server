<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台运营管理IOS线下收入录入</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css"/>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<style type="text/css">
.img01{
	display: none;
}
</style>
<script type="text/javascript">


function below(){
	var url = "${pageContext.request.contextPath}/manage!iosbelowEnteringDate.do";
	new $.dialog({
		title:'导入ios数据',
		page:url,
		width:700,
		height:150,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

/**
 * 运营单个激活
 */
function singleIncomeAudit(id,type){
	var msg = "";
		msg = "确认要结算吗？";
	var flag = confirm(msg);
	if(flag){
		var url="${pageContext.request.contextPath}/manage!iosbelowEnterStatus.do";
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+id+"&type="+type,
			dataType:'text',
			beforeSend:function(){
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
					refresh();
				}
			}
		}
	});
	}
}


function refresh(){
	$("#IncomeAudit").attr("action","${pageContext.request.contextPath}/manage!iosBelowListData.do").submit();
}


$(document).ready(function (){
//运营ios线下数据
$("#iosBelow").click(
		function() {
			$("#IncomeAudit").attr("action",
					"${pageContext.request.contextPath}/manage!iosBelowListData.do").submit();
		});
});

/**
 * 运营一键结算
 */
function keyBalance(){
			msg = "确认要结算选中项吗？";
		var flag = confirm(msg);
		if(flag){
			$("#IncomeAudit").attr("action","${pageContext.request.contextPath}/manage!keyIosbelowStatus.do").submit();
		}
	
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>ios线下数据录入</legend>
						<div id="search_bar">
							<form id="IncomeAudit" name="IncomeAudit" action="${pageContext.request.contextPath}/manage!iosBelowListData.do" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
											<tr>																						
													<td >投放ID/投放名称</td>
													<td>
														<input name="campaign" type="text" maxlength="20" value="${bean.campaign}" style="border:#999 1px solid;height:20px;background:#fff no-repeat right;" />
													</td>
													<td >MAC</td>
													<td>
														<input name="income_mac" type="text" maxlength="20" value="${bean.income_mac}" style="border:#999 1px solid;height:20px;background:#fff no-repeat right;" />
													</td>
												</tr>
												<tr>
													<td >开始日期</td>
													<td>
														<input type="text" id="statStartTime" name="statStartTime" value="${bean.statStartTime}" maxlength="20" onfocus="WdatePicker()" class="Wdate"/>
													至
														<input type="text" id="statEndTime" name="statEndTime" value="${bean.statEndTime}" maxlength="20" onfocus="WdatePicker()" class="Wdate" />									 		
													</td>
												
													<td >账户状态</td>
													<td>
														<select name="status" style="width: 148px;">
															<option value="" >全部</option>
															<option value="-1" <c:if test="${bean.status ==-1}">selected="selected"</c:if>>激活失败</option>
															<option value="0" <c:if test="${bean.status ==0}">selected="selected"</c:if>>未激活</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>激活中</option>
															<option value="3" <c:if test="${bean.status ==3}">selected="selected"</c:if>>已激活</option>
															<option value="4" <c:if test="${bean.status ==4}">selected="selected"</c:if>>重复激活</option>
														</select>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button id="iosBelow" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button id="devIncome" onclick="resetAll('devIncomeAudit');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table> 
							</form>
						</div>
					</fieldset>
					<div>
						<input type="button" value="设备标识导入" onclick="below();"/>
						<input type="button" value="一键确认" onclick="keyBalance();" <c:if test="${bean.status!=0 && bean.status!=-1}">disabled="disabled"</c:if>/>
					</div>
					<div class="main_table">
							<table width="100%" cellpadding="0"cellspacing="1" class="font_stat" id="tb">
								<tr class="tr_td tb_head">
									<th order="false">投放ID</th>
									<th order="false">投放名称</th>
									<th order="false">mac</th>
									<th order="false">openudid</th>
									<th order="false">idfa</th>
									<th order="false">发生时间</th>
									<th order="false">状态</th>
									<th order="false">操作</th>
								</tr>
									<c:if test="${!empty entiy}">
									<input id="id" name="id" type="hidden"  value="${vo.id}"/>
									<input id="ad_id" name="ad_id" type="hidden"  value="${vo.ad_id}"/>
									
									<c:forEach items="${entiy}" var="vo">
									<tr>
										<td align="center">
											${vo.placement_id}
										</td>
										<td align="center">
											${vo.ad_name}
										</td>
										<td align="center">
											${vo.income_mac}
										</td>
										<td align="center">
											${vo.openudid}
										</td>
										<td align="center">
											${vo.idfa}
										</td>
										<td align="center">
												<c:if test="${vo.create_time!=null}"><fmt:formatDate value="${vo.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></c:if>
										</td>
										<c:choose>
										<c:when test="${vo.status == -1}">
											<td>激活失败</td>
										</c:when>
										<c:when test="${vo.status == 0}">
											<td>未激活</td>
										</c:when>
										<c:when test="${vo.status == 2}">
											<td>激活中</td>
										</c:when>
										<c:when test="${vo.status == 3}">
											<td>已激活</td>
										</c:when>
										<c:otherwise>
											<td>重复激活</td>
										</c:otherwise>
									</c:choose>
										<td align="center">
											<input  id="settle" name="settle" onclick="singleIncomeAudit('${vo.id}',2)" type="button" value="激活" <c:if test="${vo.status==2|| vo.status==3||vo.status==4||vo.status==5}">disabled="disabled"</c:if>></input>
										</td>
									</tr>
									</c:forEach>
									</c:if>
									<c:if test="${empty entiy}">
										<tr>
											<td align="center" colspan="8" style="text-align: center;">暂无数据！</td>
										</tr>	
									</c:if>
							</table>
					</div>
					<div>${pageInfo.pageInfoStr}</div>
					<span class="clear_span" style="height: 10px;"></span>
				</div>
			</div>
		</div>
	</body>
</html>