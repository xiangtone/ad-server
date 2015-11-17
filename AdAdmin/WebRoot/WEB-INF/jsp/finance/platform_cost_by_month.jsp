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
<title>运营管理后台运营管理开发者收入确认</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css"/>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01{
	display: none;
}
</style>
<script type="text/javascript">

function selectAll(value){
	var b=value;
	$("#tb input:checkbox[disabled!='disabled']").attr("checked", b);
}


//获取所有选中的id
function getCheckedId(){
	var ids="";
	if($("#tb input:checkbox[checked][@value]").length>0){
		$("#tb input:checkbox[checked][@value]").each(function(){
			if(ids){
				ids=ids+","+$(this).val();
			}else{
				ids=$(this).val();
			}
		});
	}
	return ids;
}



function refresh(){
	$("#devIncomeAudit").attr("action","${pageContext.request.contextPath}/manage!financeIncomeConfirmlist.do").submit();
}




$(document).ready(function (){
	//运营开发者收入审核list
	$("#devIncomeAuditList").click(
			function() {
				$("#devIncomeAudit").attr("action",
						"${pageContext.request.contextPath}/paltformCostByMonth.do").submit();
			});
	//  运营开发者收入审核list下载
	$("#devIncomeAuditListDownload").click(function(){
				$("#devIncomeAudit").attr("action",
						"${pageContext.request.contextPath}/devIncomeQueryDownload.do").submit();
	});
	initOrder();
});

function initOrder(){
	 $(".tb_head th").css("cursor","pointer");
	 var code=$.cookie("table_order");
	 if(code){
		 $("#"+code).removeClass('img01');
	 }

	 $(".tb_head th").click(function(){
		 if($(this).attr("order")&&($(this).attr("order")=='false')){
			 return;
		 }
		 var obj;
		 if($(this).find(".img01").length==2){
			 obj=$(this).find("img").eq(0);
		 }else{
			 obj=$(this).find(".img01");
			 obj.removeClass('img01');
			 obj.siblings().addClass('img01');
		 }
		 changeOrder(obj.attr("id"),obj.attr("url"));
	 });    	 
}
function changeOrder(key,url){
	 $.cookie("table_order",key);
	 window.location.href=url;
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>平台成本</legend>
						<div id="search_bar">
							<form id="devIncomeAudit" name="devIncomeAudit" action="${pageContext.request.contextPath}/paltformCostByMonth.do" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>年</td>
													<td>
														<select name="year" style="width: 153px;">
															<option value="" >全部</option>
															<option value="2013" <c:if test="${bean.year =='2013'}">selected="selected"</c:if>>2013</option>
															<option value="2014" <c:if test="${bean.year =='2014'}">selected="selected"</c:if>>2014</option>
														</select>
													</td>
													<td>月</td>
													<td>
														<select name="month" style="width: 153px;">
															<option value="" >全部</option>
															<option value="01" <c:if test="${bean.month =='01'}">selected="selected"</c:if>>01</option>
															<option value="02" <c:if test="${bean.month =='02'}">selected="selected"</c:if>>02</option>
															<option value="03" <c:if test="${bean.month =='03'}">selected="selected"</c:if>>03</option>
															<option value="04" <c:if test="${bean.month =='04'}">selected="selected"</c:if>>04</option>
															<option value="05" <c:if test="${bean.month =='05'}">selected="selected"</c:if>>05</option>
															<option value="06" <c:if test="${bean.month =='06'}">selected="selected"</c:if>>06</option>
															<option value="07" <c:if test="${bean.month =='07'}">selected="selected"</c:if>>07</option>
															<option value="08" <c:if test="${bean.month =='08'}">selected="selected"</c:if>>08</option>
															<option value="09" <c:if test="${bean.month =='09'}">selected="selected"</c:if>>09</option>
															<option value="10" <c:if test="${bean.month =='10'}">selected="selected"</c:if>>10</option>
															<option value="11" <c:if test="${bean.month =='11'}">selected="selected"</c:if>>11</option>
															<option value="12" <c:if test="${bean.month =='12'}">selected="selected"</c:if>>12</option>
														</select>
													</td>
													<td>平台类型</td>
													<td>
														<select name="os" style="width: 157px;">
															<option value="">全部</option>
															<option <c:if test="${bean.os=='android'}"> selected="selected"</c:if> value="android">android</option>
															<option <c:if test="${bean.os=='ios'}"> selected="selected"</c:if> value="ios">ios</option>
														</select>
													</td>		
												</tr>
												<tr>
													<td>开发者id/账号</td>
													<td>
														<input name="dev" type="text" maxlength="20"  value="${bean.dev}" style="border:#999 1px solid;height:20px;background:#fff no-repeat right;" />
													</td>											
													<td>活动ID/活动名称</td>
													<td>
														<input name="campaign" type="text" maxlength="20" value="${bean.campaign}" style="border:#999 1px solid;height:20px;background:#fff no-repeat right;" />
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button id="devIncomeAuditList" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button  onclick="resetAll('devIncomeAudit');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table> 
							</form>
						</div>
					</fieldset>
					<div>
						<!-- <input name="download" type="button" id="devIncomeAuditListDownload" value="导出Excel" />-->
					</div>
					<div class="main_table">
					<!--修改-->
					<form id="batchForm" action="${pageContext.request.contextPath}/finance/devIncomeStatus.do" method="post">			
						<table width="100%" cellpadding="0"cellspacing="1" class="font_stat" id="tb">
							<tr class="tr_td tb_head">
								<th order="false">发生时间</th>
								<th width="5%" order="false">网站主ID</th>
								<th order="false">网站主名称</th>
								<th order="false">平台类型</th>
								<th order="false">应用ID</th>
								<th>应用名称<order:order url='${pageInfo.url}'  orderName='app_name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th order="false">活动ID</th>
								<th order="false">活动名称</th>
								<th width="10%" >网站主确认佣金<order:order url='${pageInfo.url}'  orderName='dev_cost' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
							</tr>
								<c:if test="${!empty devList}">
								<input id="id" name="id" type="hidden" />
								<input id="status" name="status" type="hidden" />
								<input id="confirmAmount" name="confirmAmount" type="hidden"/>
								<input id="confirmAmounts" name="confirmAmounts" type="hidden"/>
								<tr>
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
										--
									</td>
									<td align="center">
										--
									</td>
								
									<td align="center">
										--
									</td>
								
									<td align="center">
										<fmt:formatNumber  type="number" pattern="###,###.##" value="${summary.sum_sumMoney}"  minFractionDigits="2" maxFractionDigits="2" />
									</td>
								</tr>
								<c:forEach items="${devList}" var="vo">
								<tr>
									<td align="center">
										${vo.year}
									</td>
									<td align="center">
										${vo.dev_id }
									</td>
									<td align="center">
										${vo.dev_email}
									</td>
									<td align="center">
										${vo.os}
									</td>
									<td align="center">
										${vo.app_id }
									</td>
									<td align="center">
										${vo.app_name}
									</td>
									<td align="center">
										
										${vo.campaign_id}
									</td>
									<td align="center">
										${vo.ad_name}
									</td>
									<td align="center">
										<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.dev_cost}"  minFractionDigits="2" maxFractionDigits="2" />
									</td>
								</tr>
								</c:forEach>
								</c:if>
								<c:if test="${empty devList}">
									<tr>
										<td align="center" colspan="18" style="text-align: center;">暂无数据！</td>
									</tr>	
								</c:if>
						</table>
					</form>
				</div>
				<div>${pageInfo.pageInfoStr}</div>
				<span class="clear_span" style="height: 10px;"></span>
			</div>
		</div>
	</div>
</body>
</html>