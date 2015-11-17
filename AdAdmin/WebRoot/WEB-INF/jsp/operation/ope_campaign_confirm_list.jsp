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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript"><!--

function clearStatDate() {
	$('#month_stat_date').val('');
	$('#month_end_date').val('');
}

function refresh(){
	$("#campaignConfirm").submit();
}

//提交
function submitData(obj,id,os){
	$(obj).attr("disabled",true);
	var url = "manage!campaignConfirmSubmit.do?id="+id+"&os="+os;
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

//批量通过
function sumit_num() {
	if ($("#tb input:checkbox[checked][@value]").length <= 0) {
		alert("提示：请选择要提交的数据！！");
		return;
	}
	var ids="";
	var s = "";
	var checkedLength = $("input:checked").length;// 被选中的数量
	if (checkedLength > 0) {
		for ( var i = 0; i < checkedLength; i++) {
			var id = $("input:checked")[i].value;
			var income_remark = $("#income_remark_"+id).val();
			if(ids){
				ids=ids+","+id;
			}else{
				ids=id;
			}
			if(!income_remark){
				income_remark="正常";
			}
			if(s){
				s=s+","+income_remark;
			}else{
				s=income_remark;
			}
		}
		s=encodeURI(s);
		
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!submitInComeNum.do',
			type:'POST',
			data:'ids='+ids+"&inremark="+s,
			dataType:'text',
			beforeSend:function(){
			},
			success:function(data){
				if(data){
					if(data=0) {
						alert("审核失败");
					} else {
						alert("审核成功");
					}
					refresh();
				}
				
			}
		});
	}
}


//批量不通过
function sumit_num_not() {
	if ($("#tb input:checkbox[checked][@value]").length <= 0) {
		alert("提示：请选择要提交的数据！！");
		return;
	}
	var ids="";
	var income_remarks = "";
	var checkedLength = $("input:checked").length;// 被选中的数量
	if (checkedLength > 0) {
		for ( var i = 0; i < checkedLength; i++) {
			var id = $("input:checked")[i].value;
			var income_remark = $("#income_remark_"+id).val();
			if(ids){
				ids=ids+","+id;
			}else{
				ids=id;
			}
			if(!income_remark){
				income_remark="正常";
			}
			if(income_remarks){
				income_remarks=income_remarks+","+income_remark;
			}else{
				income_remarks=income_remark;
			}
		}
		
		income_remarks=encodeURI(income_remarks);
		
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!submitInComeNumNot.do',
			type:'POST',
			data:'ids='+ids+"&inremark="+income_remarks,
			dataType:'text',
			beforeSend:function(){
			},
			success:function(data){
				if(data){
					if(data=0) {
						alert("审核失败");
					} else {
						alert("审核成功");
					}
					refresh();
				}
				
			}
		});
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
						<legend>活动确认数</legend>
						<div id="search_bar">
							<form action="manage!operationCampaignConfirmList.do" method="post" id="campaignConfirm">
								<div>
									<input type="hidden" name="pageRecord" value="${pageInfo.pageSize}"/>
								</div>
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告主主</td>
													<td><input name="company_name" type="text" value="${bean.company_name}" /></td>
													<td>发生时间</td>
													<td>
														<input type="text" id="month_stat_date" name="month_stat_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_stat_date}"/>						
													至
														<input type="text" id="month_end_date" name="month_end_date"  onclick="WdatePicker()" class="Wdate" value="${bean.month_end_date}"/>
													</td>	
												</tr>
												<tr>
													<td>活动ID/名称</td>
													<td>
														<input name="campaign" type="text" value="${bean.campaign}" />
													</td>
													<td>状态</td>
													<td>
														<select name="status"  style="width: 155px;">
															<option value="">全部</option>											
															<option value="1" <c:if test="${bean.status ==1}">selected="selected"</c:if>>未审核</option>
															<option value="3" <c:if test="${bean.status ==3}">selected="selected"</c:if>>已审核</option>
															<option value="9" <c:if test="${bean.status ==9}">selected="selected"</c:if>>不通过</option>
														</select>
													</td>
													<td>平台类型	</td>
													<td>
														<select name="os"  style="width: 150px;">
															<option value="">全部</option>
															<option value="android" <c:if test="${bean.os =='android'}">selected="selected"</c:if>>Android</option>
															<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if>>Ios</option>
														</select>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('my_form');" id="confirm"  class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
				<!-- <input name="confirmDown" type="button" id="confirmDown" value="导出Excel"/> -->
				<input type="button" value="批量通过" onclick="sumit_num();" />
				<input type="button" value="批量不通过" onclick="sumit_num_not();" />
			</div>
				<div class="main_table">
				<form action="${pageContext.request.contextPath}/manage!submitInComeNum.do" method="post" id="campaignInvoice">
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
					<tr class="tr_td">	
					<th order="false">
						操作审核
					</th>
						<th width="3%">结算单号</th>
						<th>广告主名称</th>
						<th>活动id</th>
						<th>活动名称</th>
						<th>结算周期</th>
						<th>系统类型</th>
						<th>单价</th>
						<th>结算方式</th>
						<th>预确认数</th>
						<th>预确认金额</th>
						<th>结算金额</th>
						<th>核减比例</th>
						<th>状态</th>
						<th>销售人员</th>
						<th>已备注原因</th>
						<th>备注</th>
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
							<font color="red">
								<c:if test="${sum.sum_forecast_money!=null}">
									￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${sum.sum_forecast_money}"  minFractionDigits="2" maxFractionDigits="2" />
								</c:if>
							</font>
						</td>
							<td align="center">
							<font color="red">
							<c:if test="${sum.sum_income_money!=null}">
									￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${sum.sum_income_money}"  minFractionDigits="2" maxFractionDigits="2" />
								</c:if>
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
						<td align="center">
							--
						</td>
					</tr>
					<c:forEach items="${list}" var="vo" varStatus="status">
						<tr style="text-align: center;">						
								<td>
									<c:if test="${vo.status==null||vo.status==1}"><input id="income_id_${vo.id}" name="income_id" type="checkbox" value="${vo.id}"/>未通过</c:if>
									<c:if test="${vo.status==3}"><input id="income_id_${vo.id}" name="income_id" type="checkbox" value="${vo.id}" disabled="disabled"/>已通过</c:if>
									<c:if test="${vo.status==9}"><input id="income_id_${vo.id}" name="income_id" type="checkbox" value="${vo.id}" disabled="disabled"/>不通过</c:if>
								</td>
							<td style="text-align: center;">${vo.id }</td>
							<td>${vo.company_name}</td>
							<td>${vo.campaign_id}</td>
							<td>${vo.campaign_name}</td>
							<td>${vo.month_stat_date} 至  ${vo.month_end_date}</td>
							<td>${vo.os}</td>
							<td>${vo.price}</td>
							<td>${vo.balance_model}</td>
							<td>${vo.forecast_amount}</td>
							<td>
								<c:if test="${vo.forecast_money!=null}">
									￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.forecast_money}"  minFractionDigits="2" maxFractionDigits="2" />
								</c:if>
							</td>
							<td>
								<c:if test="${vo.income_money!=null}">
									￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.income_money}"  minFractionDigits="2" maxFractionDigits="2" />
								</c:if>
							</td>
							<td>
										<c:choose>
											<c:when test="${vo.subtract_ratio!= null}">
												<fmt:formatNumber value="${vo.subtract_ratio}" type="percent" pattern="#0.0#" />%
											</c:when>
											<c:otherwise>
											 	0.0%
											</c:otherwise>
										</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${vo.status == 3}">
										已审核
									</c:when>
									<c:when test="${vo.status == 9}">
										不通过
											</c:when>
								
									<c:otherwise>
									未审核
									</c:otherwise>
								</c:choose>
							</td>
							<td>${vo.name}</td>
							<td>${vo.income_remark}</td>
							<td> 
										<c:choose>
											<c:when test="${vo.status==1}">
												<input id="income_remark_${vo.id}" name="income_remark" value="${vo.income_remark}"/>
											</c:when>
											<c:otherwise>
												<input value="${vo.income_remark}" disabled="disabled"/>
											</c:otherwise>
										</c:choose>
							
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>
				</table>
				</form>
				</div>
				${pageInfo.pageInfoStr}
			</div>
		</div>
	</div>
</body>
</html>