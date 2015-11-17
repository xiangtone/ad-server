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
<title>运营管理后台财务管理开发者提款运营审核</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/devApplyMoney.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>

	<style type="text/css">
		.ipt{
		}
	
	
	</style>
<script type="text/javascript">
	function checkInput(obj) {
		obj = obj.trim();
		var doubleReg = /^￥[-]?[0-9]+$|^￥[-]?[0-9]+[\.][0-9]{0,2}$/;
		if (doubleReg.test(obj)){
			obj = obj.substring(1,obj.length);
		}
		return obj;
	}

	function changeSum(obj, managerMoneyId, oldManagerMoneyId) {
		//新的确认金额
		var newManagerMoney = document.getElementById(managerMoneyId);
		//初始的确认金额
		var oldManagerMoney = document.getElementById(oldManagerMoneyId);

		if (!isNaN(newManagerMoney.value)) {
			//统计的确认金额
			var sum = document.getElementById("sum_ManagerMoney").innerHTML;

			if (newManagerMoney.value == "") {
				newManagerMoney.value = 0;
			}
			
			var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;//金额，小数点后边最多两位小数
			if(!doubleReg.test(newManagerMoney.value)){
				alert("只能输入两位小数");
				newManagerMoney.value = checkInput(oldManagerMoney.value);
				return;
			}
			if(newManagerMoney.value > obj){
				alert("确认金额不能大于申请金额");
				newManagerMoney.value = checkInput(oldManagerMoney.value);
				return;
			}
			
			//计算后的数据
			var selt = (checkInput(sum) - oldManagerMoney.value)
					+ parseFloat(newManagerMoney.value);

			document.getElementById("sum_ManagerMoney").innerHTML = "￥"+parseFloat(
					selt).toFixed(2);
			oldManagerMoney.value = checkInput(newManagerMoney.value);
		} else {
			newManagerMoney.value = checkInput(oldManagerMoney.value);
			alert("只能输入两位小数");
		}

	}

	/**
	 * tb效果
	 *	
	 **/
	$(document).ready(function (){
		$("#tb tr").hover(function (){
			 $(this).addClass('bgclor01');
		},function (){
			  $(this).removeClass('bgclor01');  
		});
		var trs =  $("#tb").find("tr");
		for(i = 0;i<trs.length;i++){
			var obj = trs[i];
			if(i %2 == 0){
				obj.style.backgroundColor ='#EEEEEE';
			}
				 
		}  
	});	
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>渠道提款运营审核</legend>
						<div id="search_bar">
							<form action="manage!listChaperAudit.do" method="post" id="my_form">
								<div>
									<input type="hidden" name="pageRecord"  value="${pageInfo.pageSize}"/>
								</div>
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>
														<select name="keyword">
															<option value="CHA_ID" <c:if test="${bean.keyword == 'CHA_ID'}">selected="selected"</c:if>>渠道ID</option>
															<option value="CHA_EMAIL"  <c:if test="${bean.keyword == 'CHA_EMAIL'}">selected="selected"</c:if>>渠道账号</option>
															<option value="KAIHU_NAME" <c:if test="${bean.keyword == 'KAIHU_NAME'}">selected="selected"</c:if>>开户人</option>
															<option value="INVOICE_NAME" <c:if test="${bean.keyword == 'INVOICE_NAME'}">selected="selected"</c:if>>发票号</option>
														</select>
													</td>
													<td><input name="value" type="text" value="${bean.value }"	maxlength="30" /></td>
													<td>支付状态</td>
													<td>
														<select name="status">
															<option value="">全部</option>
															<option value="0" <c:if test="${bean.status == '0'}">selected="selected"</c:if>>待审核</option>
															<option value="1" <c:if test="${bean.status == '1'}">selected="selected"</c:if>>通过</option>
															<option value="-1" <c:if test="${bean.status == '-1'}">selected="selected"</c:if>>不通过</option>
															<option value="2" <c:if test="${bean.status == '2'}">selected="selected"</c:if>>付款成功</option>
															<option value="-2" <c:if test="${bean.status == '-2'}">selected="selected"</c:if>>付款失败</option>
															<option value="-3" <c:if test="${bean.status == '-3'}">selected="selected"</c:if>>已撤销申请</option>
														</select>
													</td>
													<td>发票状态</td>
													<td>
													<select name="invoice_status">
														<option value="">全部</option>
														<option value="0" <c:if test="${bean.invoice_status == 0}">selected="selected"</c:if>>开票</option>
														<option value="1" <c:if test="${bean.invoice_status == 1}">selected="selected"</c:if>>未开票</option>
													</select>
													</td>
												</tr>
												<tr>
													<td>申请日期：&nbsp;开始时间</td>
													<td>
														<input name="begin" id="rechargeDate" type="text" value="${bean.begin}" onfocus="WdatePicker();" maxlength="20"	class="Wdate" readonly="readonly" /></td>
													<td >结束时间</td>
													<td>
														<input name="end" id="rechargeDate" type="text" value="${bean.end}" onfocus="WdatePicker()" maxlength="20" class="Wdate" readonly="readonly" />
													</td>
													<td>申请单编号</td>
													<td><input name="id" value="${bean.id}" type="text"	maxlength="10" /></td>
												</tr>
												<tr>
													<td>操作日期：&nbsp;开始时间</td>
													<td>
														<input name="operatorBegin" id="rechargeDate" type="text" value="${bean.operatorBegin}" onfocus="WdatePicker();" maxlength="20" class="Wdate" readonly="readonly" />
													</td>
													<td>结束时间</td>
													<td><input name="operatorEnd" id="rechargeDate" type="text"
														value="${bean.operatorEnd}" onfocus="WdatePicker()" maxlength="20"
														class="Wdate" readonly="readonly" /></td>
													<td >操作人</td>
													<td>
														<input name="operator" value="${bean.operator }" type="text" maxlength="30" />
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
						</div>
					</fieldset>
					<!--新增end-->
				<div class="main_table">
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
					<!--修改-->
					<tr class="tr_td">
						<th width="5%">单号</th>
						<th width="8%">申请时间</th>
						<th width="5%">渠道ID</th>
						<th width="10%">渠道账户</th>
						<th width="5%">开户人</th>
						<th width="5%">发票状态</th>						
						<th width="5%">发票号</th>						
						<th width="8%">申请金额</th>
						<th width="5%">支付状态</th>
						<th width="8%">确认金额</th>
						<th width="8%">操作人</th>
						<th width="8%">操作时间</th>
						<th>运营说明</th>
						<th width="5%">操作</th>
						<th width="3%">批量</th>
					</tr>
					<tr>
						<td style="text-align: center;">汇总</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td id="sum_ApplyMoney"><escore:formatMoney value="${dMOperAuditSum.applyMoney }" maxFractionDigits="2" /></td>
						<td>-</td>
						<td id="sum_ManagerMoney"><escore:formatMoney value="${dMOperAuditSum.managerMoney }" maxFractionDigits="2" /></td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
					</tr>
					<form id="batchForm" action="" method="post">
						<input id="id" name="id" type="hidden" />
						<input id="status" name="status" type="hidden" />
						<input id="mDesc" name="mDesc" type="hidden" />
						<input id="mDescs" name="mDescs" type="hidden" />
						<input id="mMoney" name="mMoney" type="hidden" />
						<input id="invoice_suv" name="invoice_suv" type="hidden" />
						<input id="invoice_suvS" name="invoice_suvS" type="hidden" />
						<input id="invoice_statu" name="invoice_statu" type="hidden" />
						<input id="invoice_statusS" name="invoice_statusS" type="hidden" />
						<input id="mMoneys" name="mMoneys" type="hidden" />
						<c:forEach items="${listDMOperAudit}" var="k">
							<tr>
								<td>${k.id }</td>
								<td>
									<span title="<fmt:formatDate value="${k.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
										<fmt:formatDate value="${k.createTime}" pattern="yyyy-MM-dd" />
									</span>
								</td>
								<td>${k.channelId}</td>
								<td>
									${k.chaEmail}
								</td>
								<td>${k.kaihu_name}</td>
								<td style="text-align: center;">														
										<c:choose>
										<c:when test="${k.status == 0}">
											<select name="invoice_status" id="${k.id}invoice_status" >
												<option value="0" <c:if test="${k.invoice == 0}">selected="selected"</c:if>>开票</option>
												<option value="1" <c:if test="${k.invoice == 1}">selected="selected"</c:if>>未开票</option>
											</select>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${k.invoice == 0}">
													开票
												</c:when>
												<c:when test="${k.invoice == 1}">
													未开票
												</c:when>									
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${k.status == 0}">
											<input type="text" id="${k.id}invoice_name" name="invoice_name" class="ipt" value="${k.invoice_name}" />
										</c:when>
										<c:otherwise>
											<input type="text" id="${k.id}invoice_name" name="invoice_name" class="ipt" value="${k.invoice_name}" disabled="disabled"/>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<escore:formatMoney value="${k.applyMoney}" maxFractionDigits="2" />
									<input id="${k.id}applyMoney" name="${k.id}applyMoneyId" type="hidden" value="${k.applyMoney}" />
								</td>
								<td>
									<c:choose>
										<c:when test="${k.status == 0}">
											待审核
										</c:when>
										<c:when test="${k.status == 1}">
											通过
										</c:when>
										<c:when test="${k.status == -1}">
											不通过
										</c:when>
										<c:when test="${k.status == 2}">
											付款成功
										</c:when>
										<c:when test="${k.status == -2}">
											付款失败
										</c:when>
										<c:when test="${k.status == -3}">
											已撤销申请
										</c:when>
									</c:choose>
								</td>
								<td>
									<c:choose>
									<c:when test="${k.status == 0}">
										<c:choose>
											<c:when test="${k.managerMoney == null}">
												<input type="hidden" id="${k.id}oldManagerMoney" class="ipt" value="${k.applyMoney}" />
												<input id="${k.id}managerMoney" name="managerMoney" class="ipt" value="${k.applyMoney}" onkeyup="changeSum(${k.applyMoney},'${k.id}managerMoney','${k.id}oldManagerMoney')" style="width: 80px;" />
											</c:when>
											<c:otherwise>
												<input type="hidden" id="${k.id}oldManagerMoney" class="ipt" value="${k.managerMoney}"/>
												<input id="${k.id}managerMoney" name="managerMoney" class="ipt" value="${k.managerMoney}" onkeyup="changeSum(${k.applyMoney},'${k.id}managerMoney','${k.id}oldManagerMoney')" style="width: 80px;" />
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<input name="managerMoney" class="ipt" value="<escore:formatMoney value="${k.managerMoney}" maxFractionDigits="2" />" disabled="disabled" style="width: 80px;" />
									</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${k.status == -3}">
											渠道
										</c:when>
										<c:otherwise>
											${k.operator}
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<span title="<fmt:formatDate value="${k.managerTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
										<fmt:formatDate value="${k.managerTime}" pattern="yyyy-MM-dd" />
									</span>
								</td>
								<td>
									<span title="${k.managerDesc}">
										<input name="${k.id}managerDesc" class="ipt" value="${k.managerDesc}" disabled="disabled" style="width: 80px;" />
									</span>
								</td>
								<td align="center"><c:choose>
										<c:when test="${k.status == 0}">
											<input type="button" value="通过"
												onclick="singleChaDealAuditInOper('${k.id}','1')" />
											<input type="button" value="不通过"
												onclick="singleChaDealAuditInOper('${k.id}','-1')" />
										</c:when>
										<c:otherwise>
											<input type="button" value="通过" disabled="disabled" />
											<input type="button" value="不通过" disabled="disabled" />
										</c:otherwise>
									</c:choose></td>

								<td style="text-align: center;"><c:choose>
										<c:when test="${k.status == 0}">
											<input id="checkbox" name="checkbox" type="checkbox"
												value="${k.id}" />
										</c:when>
										<c:otherwise>
											<input id="checkbox" name="checkbox" type="checkbox"
												disabled="disabled" />
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
						<c:if test="${empty listDMOperAudit}">
							<tr>
								<td align="center"  style="text-align: center;" colspan="14">暂无数据！</td>
							</tr>
						</c:if>
					</form>
				</table>
				</div>
				<div>${pageInfo.pageInfoStr}</div>
				<!--修改end-->
				<c:if test="${not empty listDMOperAudit}">
					<div class="fr">
						<input type="button" id="btn3" value="全选" />
						批量操作：<input	type="button" value="通过" onclick="batchChaDealAuditInOper('1')" />
						&nbsp;<input type="button" value="不通过" onclick="batchChaDealAuditInOper('-1');" />
					</div>
				</c:if>
			</div>
			<span class="clear_span" style="height: 20px;"></span>
		</div>
		</div>
	</div>
</body>
</html>
