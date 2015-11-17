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

/**
 * 运营单个下载结算处理
 */
function singleIncomeAudit(id,obj){
	$(obj).attr("disabled","disabled");
	var msg ="确认要结算吗？";
	var flag = confirm(msg);
	if(flag){
		var url="${pageContext.request.contextPath}/finance/devIncomeStatus.do";
		$.ajax({
				url:url,
				type:'POST',
				data:'id='+id,
				dataType:'text',
				beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
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

function batchIncomeAudit(obj){
	$(obj).attr("disabled","disabled");
	 var checkedLength = $("input:checked").length;//被选中的数量
	 if (checkedLength > 0) {
			msg = "确认要结算选中项吗？";
		var flag = confirm(msg);
		if(flag){
			var ids=getCheckedId();
			var url="${pageContext.request.contextPath}/finance/updateBatchDevIncome.do";
			$.ajax({
				url:url,
				type:'POST',
				data:'ids='+ids,
				dataType:'text',
				beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
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
	}else{
		alert("请选择要操作的数据！");
		return;
	}
}

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


/**
 * 查看修改信息
 *	
 **/
function aduditNotPass(id){
		var url ="${pageContext.request.contextPath}/finance/devIncomeAduit.do?ids="+id;
		new $.dialog({
			title:'请录入不通过原因！！',
			page:url,
			width:575,
			height:300,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}


/**
 * 运营一键结算
 */
function keyBalance(){
	 var checkedLength = $("#tb input:checked").length;//被选中的数量
	 if (checkedLength > 0) {
			msg = "确认要结算选中项吗？";
		var flag = confirm(msg);
		if(flag){
			var ids=getCheckedId();
			aduditNotPass(ids);
		}
	}else{
		alert("请选择要操作的数据！");
		return;
	}
}



function refresh(){
	$("#devIncomeAudit").attr("action","${pageContext.request.contextPath}/finance/devIncomeAuditList.do").submit();
}




$(document).ready(function (){
	//运营开发者收入审核list
	$("#devIncomeAuditList").click(
			function() {
				$("#devIncomeAudit").attr("action",
						"${pageContext.request.contextPath}/finance/devIncomeAuditList.do").submit();
			});
	//  运营开发者收入审核list下载
	$("#devIncomeAuditListDownload").click(function(){
				$("#devIncomeAudit").attr("action",
						"${pageContext.request.contextPath}/finance/devIncomeAuditListDownload.do").submit();
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
						<legend>网站主收入审核 </legend>
						<div id="search_bar">
							<form id="devIncomeAudit" name="devIncomeAudit" action="${pageContext.request.contextPath}/finance/devIncomeAuditList.do" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td >开始日期</td>
													<td>
														<input type="text" id="statStartTime" name="statStartTime" value="${bean.statStartTime}" maxlength="20" onfocus="WdatePicker()" class="Wdate"/>
													</td>
													<td>截止日期</td>
													<td>
														<input type="text" id="statEndTime" name="statEndTime" value="${bean.statEndTime}" maxlength="20" onfocus="WdatePicker()" class="Wdate" />									 		
													</td>
													<td >开发者佣金</td>
													<td>
														<select name="dev_confirmMoney" style="width: 153px;">
															<option value="" >全部</option>
															<option value="1" <c:if test="${bean.dev_confirmMoney ==1}">selected="selected"</c:if>>不足1元</option>
															<option value="5" <c:if test="${bean.dev_confirmMoney ==5}">selected="selected"</c:if>>不足5元</option>
															<option value="10" <c:if test="${bean.dev_confirmMoney ==10}">selected="selected"</c:if>>不足十元</option>
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
													<td>结算状态</td>
													<td>
														<select name="status" style="width: 153px;">
															<option value="" >全部</option>
															<option value="1" <c:if test="${bean.status ==1}">selected="selected"</c:if>>未审核</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>已审核</option>
															<option value="-1" <c:if test="${bean.status ==-1}">selected="selected"</c:if>>审核不通过</option>
														</select>
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
						<input type="button" value="通过" onclick="batchIncomeAudit(this);" />
						<input type="button" value="不通过" onclick="keyBalance();" />
						<input name="download" type="button" id="devIncomeAuditListDownload" value="导出Excel" />
					</div>
					<div class="main_table">
					<!--修改-->
					<form id="batchForm" action="${pageContext.request.contextPath}/finance/devIncomeStatus.do" method="post">			
						<table width="100%" cellpadding="0"cellspacing="1" class="font_stat" id="tb">
							<tr class="tr_td tb_head">
								<th order="false">
									<input id="checkbox" type="checkbox" value="" onclick="selectAll(this.checked);" />
								</th>
								<th order="false">发生时间</th>
								<th width="5%" order="false">网站主ID</th>
								<th order="false">网站主名称</th>
								<th order="false">应用ID</th>
								<th>应用名称<order:order url='${pageInfo.url}'  orderName='app_name' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th order="false">活动ID</th>
								<th order="false">活动名称</th>
								<th order="false">结算状态</th>
								<th width="10%" >网站主确认佣金<order:order url='${pageInfo.url}'  orderName='confirm_money' orderMode='desc' base="${pageContext.request.contextPath}" /></th>
								<th width="5%" order="false">业绩提交人</th>
								<th width="10%" order="false">业绩提交时间</th>
								<th width="5%" order="false">审核人</th>
								<th width="5%" order="false">审核时间</th>
								<th order="false">操作</th>
							</tr>
								<c:if test="${!empty devList}">
								<input id="id" name="id" type="hidden" />
								<input id="status" name="status" type="hidden" />
								<input id="confirmAmount" name="confirmAmount" type="hidden"/>
								<input id="confirmAmounts" name="confirmAmounts" type="hidden"/>
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
										<td align="center"><escore:formatMoney value="${summary.sum_sumMoney}" maxFractionDigits="2" />
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
								<c:forEach items="${devList}" var="vo">
								<tr>
									<td align="center">
										<input id="checkbox" name="dev_checkbox" type="checkbox" value="${vo.id}" <c:if test="${vo.status!=1}">disabled="disabled"</c:if> />
									</td>
									<td align="center">
										<c:if test="${vo.effect_time==null }">--</c:if>
									<c:if test="${vo.effect_time!=null}"><fmt:formatDate value="${vo.effect_time }" pattern="yyyy-MM-dd " /></c:if>
									</td>
									<td align="center">
										${vo.dev_id }
									</td>
									<td align="center">
										${vo.dev_email }
									</td>
									<td align="center">
										${vo.app_id }
									</td>
									<td align="center">
										${vo.app_name }
									</td>
									<td align="center">
										${vo.campaign_id }
									</td>
									<td align="center">
										${vo.campaign_name}
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${vo.status ==1}">
												未审核
											</c:when>
											<c:when test="${vo.status ==2}">
												已审核
											</c:when>
											<c:when test="${vo.status ==-1}">
												审核未通过
											</c:when>
										</c:choose>
									</td>
									<td align="center">
										<c:if test="${vo.confirmMoney!=null}">
											￥<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.confirmMoney}"  minFractionDigits="2" maxFractionDigits="2" />
										</c:if>
									</td>
									<td align="center">					
										${vo.manager_name}
									</td>
									<td align="center">					
										${vo.manager_time}
									</td>
									<td align="center">					
										${vo.finance_name}
									</td>
									<td align="center">
										${vo.finance_time}
									</td>
									<td align="center">
										<input  id="settle" name="settle" onclick="singleIncomeAudit('${vo.id}',this)" type="button" value="通过" <c:if test="${vo.status!=1}">disabled="disabled"</c:if>></input>
										<input  id="settle" name="settle" onclick="aduditNotPass('${vo.id}')" type="button" value="不通过" <c:if test="${vo.status!=1}">disabled="disabled"</c:if>></input>
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