<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台财务管理网站主提款财务审核</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/devApplyMoney.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
		//查询条件日期
		function clearData(statu){
				if(statu==0){
					var begin =  document.getElementById("begin");
					var end =  document.getElementById("end");
					begin.value = "";
					end.value = "";
				}else{
					var operatorBegin =  document.getElementById("operatorBegin");
					var operatorEnd =  document.getElementById("operatorEnd");
					operatorBegin.value = "";
					operatorEnd.value = "";
					}
				
			}

		function clearManegeData(){
				var ope_begin =  document.getElementById("ope_begin");
				var ope_end =  document.getElementById("ope_end");
				ope_begin.value = "";
				ope_end.value = "";
		}
		
		function checkInput(obj) {
			obj = $.trim(obj);
			var doubleReg = /^￥[-]?[0-9]+$|^￥[-]?[0-9]+[\.][0-9]{0,2}$/;
			if (doubleReg.test(obj)){
				obj = obj.substring(1,obj.length);
			}
			return obj;
		}
		//数据清零服带动数据连动
		function clearZero(value){
			var form = window.document.forms[1];
			var finance_duesSum =  document.getElementById("finance_duesSum");
			var finance_taxSum =  document.getElementById("finance_taxSum");
			var finance_moneySum =  document.getElementById("finance_moneySum");
			var finance_moneySum1 =  document.getElementById("finance_moneySum1");
			var finance_duesSum1 =  document.getElementById("finance_duesSum1");
			var finance_taxSum1 =  document.getElementById("finance_taxSum1");
			var elementValue = 0.0;
			var finance_moneySumtag = checkInput(finance_moneySum1.value);
			var finance_duesSumtag = checkInput(finance_duesSum1.value);
			var finance_taxSumtag = checkInput(finance_taxSum1.value);
						
			for(var i = 0;i<form.elements.length;i++){
				if(((form.elements[i].id).indexOf(value)!=-1)&&!form.elements[i].disabled&&form.elements[i].type == "text"){
					elementValue = elementValue+parseFloat(checkInput(form.elements[i].value));
					form.elements[i].value = 0.0;
					form.elements[i].onchange();
				}	
			}
			if("finance_tax".indexOf(value)!=-1){
				if(checkInput(finance_taxSum1.value)==window.parseFloat("${finance_taxSum}")){
					finance_taxSum.innerHTML ="￥"+parseFloat(parseFloat(
						finance_taxSumtag)-parseFloat(elementValue)).toFixed(2);
					finance_moneySum.innerHTML ="￥"+parseFloat(parseFloat(
						finance_moneySumtag)+parseFloat(elementValue)).toFixed(2);
					finance_moneySum1.value = checkInput(finance_moneySum.innerHTML);
					finance_taxSum1.value = checkInput(finance_taxSum.innerHTML);
				}
			}else{
				if(checkInput(finance_duesSum1.value)==window.parseFloat("${finance_duesSum}")){
					finance_duesSum.innerHTML ="￥"+parseFloat(parseFloat(
						finance_duesSumtag)-parseFloat(elementValue)).toFixed(2);
					finance_moneySum.innerHTML ="￥"+parseFloat(parseFloat(
						finance_moneySumtag)+parseFloat(elementValue)).toFixed(2);
					finance_moneySum1.value = checkInput(finance_moneySum.innerHTML);		
					finance_duesSum1.value = checkInput(finance_duesSum.innerHTML);	
				}
			}
		}


		//扣税
		function mmm1(id,value){
			var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;//金额，小数点后边最多两位小数
			var finance_tax =  document.getElementById(id+"finance_tax");
			var finance_dues =  document.getElementById(id+"finance_dues");
			
			var finance_taxc =  document.getElementById(id+"finance_taxc");
			var finance_duesc =  document.getElementById(id+"finance_duesc");
			
			if(!doubleReg.test(checkInput(finance_tax.value))){
				alert("税率输入不合法！");
				finance_tax.value = checkInput(finance_taxc.value);
				//financeMoney.focus();
				return;
			}
			var finance_money =  document.getElementById(id+"finance_money");
			var managerMoney =  document.getElementById(id+"managerMoney");
			var finance_duesSum =  document.getElementById("finance_duesSum");
			var finance_taxSum =  document.getElementById("finance_taxSum");
			var finance_moneySum =  document.getElementById("finance_moneySum");
			var finance_moneySum1 =  document.getElementById("finance_moneySum1");
			var finance_taxSum1 =  document.getElementById("finance_taxSum1");

			var finance_moneyTag = parseFloat(parseFloat(
					checkInput(managerMoney.innerHTML)-
					checkInput(finance_dues.value))-
					checkInput(finance_tax.value)).toFixed(2);
			if(finance_moneyTag<0){
				alert("税率和手续费之和不能大于申请支付！");
				finance_tax.value = checkInput(finance_taxc.value);
				//financeMoney.focus();
				return;
			}
			finance_money.innerHTML="￥"+parseFloat(parseFloat(
					checkInput(managerMoney.innerHTML)-
					checkInput(finance_dues.value))-
					checkInput(finance_tax.value)).toFixed(2);
			finance_taxSum.innerHTML ="￥"+parseFloat(parseFloat(
					checkInput(finance_taxSum.innerHTML))+parseFloat(
					checkInput(finance_tax.value)-
					checkInput(finance_taxc.value))).toFixed(2);
			finance_moneySum.innerHTML ="￥"+parseFloat(parseFloat(
					checkInput(finance_moneySum.innerHTML)-
					checkInput(finance_tax.value))+parseFloat(
					checkInput(finance_taxc.value))).toFixed(2);
			
			finance_taxc.value = checkInput(finance_tax.value);
			finance_moneySum1.value = checkInput(finance_moneySum.innerHTML);
			finance_taxSum1.value = checkInput(finance_taxSum.innerHTML);
		}
		//手续费
		function mmm2(id,value){
			var doubleReg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;//金额，小数点后边最多两位小数
			var finance_dues =  document.getElementById(id+"finance_dues");
			var finance_tax =  document.getElementById(id+"finance_tax");
			var finance_taxc =  document.getElementById(id+"finance_taxc");
			var finance_duesc =  document.getElementById(id+"finance_duesc");
			
			if(!doubleReg.test(checkInput(finance_dues.value))){
				alert("手续费输入不合法！");
				finance_dues.value = checkInput(finance_duesc.value);
				finance_dues.focus();
				return;
			}
			var finance_money =  document.getElementById(id+"finance_money");
		
			var managerMoney =  document.getElementById(id+"managerMoney");
			
			var finance_duesSum =  document.getElementById("finance_duesSum");
			var finance_taxSum =  document.getElementById("finance_taxSum");
			var finance_moneySum =  document.getElementById("finance_moneySum");
			var finance_moneySum1 =  document.getElementById("finance_moneySum1");
			var finance_duesSum1 =  document.getElementById("finance_duesSum1");
			
			
			var finance_moneyTag = parseFloat(parseFloat(checkInput($.trim(managerMoney.innerHTML))-
					checkInput($.trim(finance_dues.value)))-parseFloat(finance_tax.value)).toFixed(2);
			
			if(finance_moneyTag<0){
				alert("税率和手续费之和不能大于申请支付！");
				finance_dues.value = checkInput(finance_duesc.value);
				//financeMoney.focus();
				return;
			}
			
			finance_money.innerHTML ="￥"+parseFloat(parseFloat(checkInput(managerMoney.innerHTML)-checkInput(finance_dues.value))-parseFloat(finance_tax.value)).toFixed(2);
			
			finance_duesSum.innerHTML ="￥"+parseFloat(parseFloat(
					checkInput(	finance_duesSum.innerHTML))+parseFloat(
					checkInput(finance_dues.value))-parseFloat(
					checkInput(finance_duesc.value))).toFixed(2);
		
			finance_moneySum.innerHTML ="￥"+parseFloat(parseFloat(
					checkInput(finance_moneySum.innerHTML)-
					checkInput(finance_dues.value))+parseFloat(
					checkInput(finance_duesc.value))).toFixed(2);
			
			finance_duesc.value = checkInput(finance_dues.value);
			finance_moneySum1.value = checkInput(finance_moneySum.innerHTML);
			finance_duesSum1.value = checkInput(finance_duesSum.innerHTML);
			
		}
		
		
</script>			
</head>

<body>
<div class="main">
	<div class="content clearfix">
		<div class="content_right admin_right" style="overflow-x: scroll;">
			<div class="bord_bom1px">
				提款支付
			</div>
			<!--新增-->
			<form  method="post" id="listDMFinanceAudit" action="manage!financeApplymoneyList.do">
				<input type="hidden" name="pageRecord" value="${pageRecord}" id="pageRecord"/>
				<table width="100%" border="1" cellpadding="1" cellspacing="0" bordercolor="#85caff">
					    <tr>
						      <td style="color: blue" bgcolor="#E8E8E8">网站主帐号</td>
						      <td><input name="dev_email" id="dev_email" type="text" value="${bean.dev_email}" maxlength="30"/></td>
						      <td style="color: blue" bgcolor="#E8E8E8">操作人名称  </td>
						      <td><input name="operatorMan" id="operatorMan" type="text" value="${bean.operatorMan}" maxlength="30"/>
							</td>
				    	</tr>
						<tr>							
						<td style="color: blue" bgcolor="#E8E8E8" >申请日期</td>
							<td style="color: blue" width="40%">
								<input name="begin" id="begin" type="text" value="${bean.begin}"  onfocus="WdatePicker();" maxlength="20" class="Wdate" readonly="readonly" style="cursor: pointer;" />至
								<input name="end" id="end" type="text" value="${bean.end}" onfocus="WdatePicker();" maxlength="20" class="Wdate" readonly="readonly" style="cursor: pointer;"/>
							    <input type="button" onclick="clearData(0);" value="清空" style="cursor: pointer;" />
							</td>
							<td style="color: blue" bgcolor="#E8E8E8">支付类型</td>
						    <td>
							<select name="payType" id="payType" style="width: 155px">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${bean.payType == '0'}">
						  				<option value="0" selected="selected">银行转账</option>
						  				<option value="1">现金</option>
						  				<option value="2">支票</option>
									</c:when>
									<c:when test="${bean.payType == '1'}">
						  				<option value="0">银行转账</option>
						  				<option value="1" selected="selected">现金</option>
						  				<option value="2">支票</option>
									</c:when>
									<c:when test="${bean.payType == '2'}">
						  				<option value="0">银行转账</option>
						  				<option value="1">现金</option>
						  				<option value="2" selected="selected">支票</option>
									</c:when>
									<c:otherwise>
						  				<option value="0">银行转账</option>
						  				<option value="1">现金</option>
						  				<option value="2">支票</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
						</tr>						
						<tr>
							<td style="color: blue" bgcolor="#E8E8E8">操作日期</td>
							<td style="color: blue">
								<input name="operatorBegin" id="operatorBegin" type="text" value="${bean.operatorBegin}" onfocus="WdatePicker()" maxlength="20" class="Wdate" readonly="readonly" style="cursor: pointer;" />至							
								<input name="operatorEnd" id="operatorEnd" type="text" value="${bean.operatorEnd}" onfocus="WdatePicker()" maxlength="20" class="Wdate" readonly="readonly" style="cursor: pointer;" />
						        <input type="button" onclick="clearData(1)" value="清空" style="cursor: pointer;" />						        
						    </td>
						    <td style="color: blue" bgcolor="#E8E8E8">支付状态</td>
							<td>
							<select name="status" id="statusm" style="width: 155px">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${bean.status == '1'}">
						  				<option value="1" selected="selected">未支付</option>
						  				<option value="2">已支付</option>
						  				<option value="-2">拒付</option>
									</c:when>
									<c:when test="${bean.status =='2'}">
						  				<option value="1">未支付</option>
						  				<option value="2" selected="selected">已支付</option>
						  				<option value="-2">拒付</option>
									</c:when>
									<c:when test="${bean.status == '-2'}">
						  				<option value="1">未支付</option>
						  				<option value="2">已支付</option>
						  				<option value="-2" selected="selected">拒付</option>
									</c:when>
									
									<c:otherwise>
						  				<option value="1">未支付</option>
						  				<option value="2">已支付</option>
						  				<option value="-2">拒付</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
						</tr>
						<tr>
							<td style="color: blue" bgcolor="#E8E8E8" >运营审核日期</td>
							<td style="color: blue" width="40%">
								<input name="ope_begin" id="ope_begin" type="text" value="${bean.ope_begin}"  onfocus="WdatePicker();" maxlength="20" class="Wdate" readonly="readonly" style="cursor: pointer;" />至
								<input name="ope_end" id="ope_end" type="text" value="${bean.ope_end}" onfocus="WdatePicker();" maxlength="20" class="Wdate" readonly="readonly" style="cursor: pointer;"/>
							    <input type="button" onclick="clearManegeData();" value="清空" style="cursor: pointer;" />
							</td>							
							<td style="color: blue" bgcolor="#E8E8E8">发票状态</td>
						    <td>
							<select name="invoice" id="invoice" style="width: 155px">
								<option value="">全部</option>
								<c:choose>
									<c:when test="${bean.invoice == 0}">
						  				<option value="0" selected="selected">已开发票</option>
						  				<option value="1">未开发票</option>
									</c:when>
									<c:when test="${bean.invoice == 1}">
						  				<option value="0">已开发票</option>
						  				<option value="1" selected="selected">未开发票</option>
									</c:when>								
									<c:otherwise>
						  				<option value="0">已开发票</option>
						  				<option value="1">未开发票</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
						</tr>
						<tr>
						<td style="color: blue" bgcolor="#E8E8E8" >单号</td>
							<td style="color: blue" width="40%">	
								<input name="id" id="id" type="text" value="${bean.id}" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>						
							</td>	
						</tr>		
					<tr>
					<td  colspan="4" bgcolor="#E8E8E8">
						<div style=" text-align: center;">
							<input name="" id="financeApplymoney" value="查询" type="button" style="cursor: pointer;" />										
							<input name="" type="button" id="financeApplymoneyDownload" value="导出Excel" style="cursor: pointer;" />
						</div>
					</td>
				</tr>
				</table>				
			</form>
			<div class="main_table">
			<form id="batchForm" action="" method="post">
				<div>
					<input id="id" name="id" type="hidden"/>
					<input id="ids" name="ids" type="hidden"/>
					<input id="fDesc" name="fDesc" type="hidden"/>
					<input id="fDescs" name="fDescs" type="hidden"/>
					<input id="fMoney" name="fMoney" type="hidden"/>
					<input id="fMoneys" name="fMoneys" type="hidden"/>
					<input id="pay_type" name="pay_type" type="hidden"/>
					<input id="pay_types" name="pay_types" type="hidden"/>
					<input id="finance_tax" name="finance_tax" type="hidden"/>
					<input id="finance_taxs" name="finance_taxs" type="hidden"/>
					<input id="finance_dues" name="finance_dues" type="hidden"/>
					<input id="finance_duess" name="finance_duess" type="hidden"/>
					<input id="balance" name="balance" type="hidden"/>
					<input id="balances" name="balances" type="hidden"/>
					
					<input id="exp_payType" name="payType" type="hidden"/>
					<input id="exp_status" name="status" type="hidden"/>
					<input id="exp_operatorBegin" name="operatorBegin" type="hidden"/>
					<input id="exp_operatorEnd" name="operatorEnd" type="hidden"/>
					<input id="exp_dev_email" name="dev_email" type="hidden"/>
					<input id="exp_begin" name="begin" type="hidden"/>
					<input id="exp_end" name="end" type="hidden"/>
					<input id="exp_ope_begin" name="ope_begin" type="hidden"/>
					<input id="exp_ope_end" name="ope_end" type="hidden"/>
					<input id="exp_operatorMan" name="operatorMan" type="hidden"/>
				</div>
			<table width="100%" cellpadding="0" cellspacing="1" class="font_stat" id="tb">
			<!--修改-->
				<tr class="tr_td">
					<th>申请单号</th>
					<th>申请时间</th>
					<th>网站主</th>
					<th>开户名</th>
					<th>城市</th>
					<th>银行名称</th>
					<th>开户行名称</th>
					<th>开户帐号</th>
					<th>支付类型</th>
					<th>发票情况</th>
					<th>申请金额</th>
					<th>账户余额</th>
					<th>支付金额</th>
					<th>扣税<input type="button" onclick="clearZero('finance_tax')" value="清零" /></th>
					<th>手续费<input type="button" onclick="clearZero('finance_dues')" value="清零" /></th>
					<th>支付状态</th>
					<th>操作人</th>
					<th>操作日期</th>
					<th>操作</th>
					<th>选择</th>
				</tr>
					<tr>
							<td style="color: red">汇总</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td width="165px" style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red"><escore:formatMoney value="${appleMoneySum }" maxFractionDigits="2" /></td>
							<td style="color: red">--</td>
							<td id="finance_moneySum" style="color: red"><escore:formatMoney value="${finance_moneySum }" maxFractionDigits="2" /></td>
							<td id="finance_taxSum" style="color: red"><escore:formatMoney value="${finance_taxSum }" maxFractionDigits="2" /></td>
							<td id="finance_duesSum" style="color: red"><escore:formatMoney value="${finance_duesSum }" maxFractionDigits="2" /></td>
							<td style="color: red">--</td>
							<input type="hidden" id="finance_moneySum1" value="${finance_moneySum}" />
							<input type="hidden" id="finance_taxSum1" value="${finance_taxSum}" />
							<input type="hidden" id="finance_duesSum1" value="${finance_duesSum}" />
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
							<td style="color: red">--</td>
					</tr>
					
					
					<c:forEach items="${entiy}" var="k">
						<tr>
							<td>${k.id }</td>
							<td>
								<fmt:formatDate value="${k.createTime}" pattern="yyyy-MM-dd" />
							</td>
							<td>${k.devEmail }</td>
							<td>${k.kaihu_name }</td>
							<td>${k.bank_city }</td>
							<td>${k.bank_name }</td>
							<td>${k.bank_subbranch }</td>
							<td width="165px">${k.bank_account }</td>
							<c:choose>
								<c:when test="${k.pay_type == '0'}">
									<td>银行转账</td>
								</c:when>
								<c:when test="${k.pay_type == '1'}">
							  		<td>现金</td>
								</c:when>
								<c:when test="${k.pay_type == '2'}">
							  		<td>支票</td>
								</c:when>
								<c:when test="${k.pay_type == null}">
							  		<td></td>
								</c:when>
							</c:choose>
							<td>${k.invoice_name}</td>
							<td id="${k.id}managerMoney">
							<escore:formatMoney value="${k.managerMoney}" maxFractionDigits="2" /></td>
							<td><escore:formatMoney value="${k.balance}" maxFractionDigits="2" /></td>
							<c:choose>
								<c:when test="${k.status == '1'}">
									<td id="${k.id }finance_money">
										<escore:formatMoney value="${k.finance_money }" maxFractionDigits="2" />
									</td>
									<td>
										<input onchange="mmm1('${k.id }','${k.finance_tax }')"  id="${k.id }finance_tax" type="text" value="${k.finance_tax }" style="width: 50px"/>
										<input id="${k.id }finance_taxc"  value="${k.finance_tax }"  type="hidden"/>
									</td>
									<td>
										<input onchange="mmm2('${k.id }','${k.finance_dues }')" id="${k.id }finance_dues" type="text" value="${k.finance_dues }" style="width: 50px"/>
										<input id="${k.id }finance_duesc" value="${k.finance_dues }"  type="hidden"/>
									</td>
									<td>未支付</td>
								</c:when>
								<c:when test="${k.status == '2'}">									
									<td><escore:formatMoney value="${k.finance_money }" maxFractionDigits="2" /></td>
									<td><input  id="${k.id }finance_tax" type="text" value="<escore:formatMoney value="${k.finance_tax }" maxFractionDigits="2" />" disabled="disabled" style="width: 50px"/></td>
									<td><input  id="${k.id }finance_dues" type="text" value="<escore:formatMoney value="${k.finance_dues }" maxFractionDigits="2" />" disabled="disabled" style="width: 50px"/></td>
							  		<td>已支付</td>
								</c:when>
								<c:when test="${k.status == '-2'}">									
									<td><escore:formatMoney value="${k.finance_money }" maxFractionDigits="2" /></td>
									<td><input id="${k.id }finance_tax" type="text" value="<escore:formatMoney value="${k.finance_tax }" maxFractionDigits="2" />" disabled="disabled" style="width: 50px"/></td>
									<td><input id="${k.id }finance_dues" type="text" value="<escore:formatMoney value="${k.finance_dues }" maxFractionDigits="2" />" disabled="disabled" style="width: 50px"/></td>
							  		<td>拒付</td>
								</c:when>
							</c:choose>
							<td>${k.finance_name}</td>
							<td><fmt:formatDate value="${k.financeTime}" pattern="yyyy-MM-dd" /></td>
							<td width="80px">
								<c:choose>
									<c:when test="${k.status == '1'}">
										<select name="${k.id }pay_type" id="${k.id }pay_type">
											<option value="0">银行转账</option>
							  				<option value="1">现金</option>
							  				<option value="2">支票</option>
										</select>
										<input type="button" onclick="singleDealAuditInFinance('${k.id}','2')" value="支付" style="background-color:transparent;border:0" />
										<input type="button" onclick="singleDealAuditInFinance('${k.id}','-2')" value="拒付" style="background-color:transparent;border:0" />
									</c:when>
									<c:otherwise>
										<select name="${k.id }pay_type" id="${k.id }pay_type" disabled="disabled">
											<option value="0">银行转账</option>
							  				<option value="1">现金</option>
							  				<option value="2">支票</option>
										</select>
										<input type="button" value="支付" disabled="disabled" style="background-color:transparent;border:0" />
										<input type="button" value="拒付" disabled="disabled" style="background-color:transparent;border:0" />
									</c:otherwise>
								</c:choose>	
							</td>
							<td>
							  <c:choose>
								<c:when test="${k.status == '1'}">
								<input id="checkbox" name="checkboxtag" type="checkbox"  value="${k.id}"  />
								</c:when>
								<c:otherwise>
								<input id="checkbox" name="checkboxtag" type="checkbox"  value="${k.id}" disabled="disabled" />
								</c:otherwise>
							</c:choose>	
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty entiy}">
						<tr>
							<td colspan="18" align="center" style="text-align: center;">暂无记录！</td>
						</tr>
					</c:if>
			</table>
			</form>
			</div>
			<div>${pageInfo.pageInfoStr}</div>
			<!--修改end-->
			<c:if test="${not empty entiy}">
				<div class="fr"><input type="button" id="btn4" value="全选" />批量操作：<input type="button" value="支付" onclick="batchDealAuditInFinance('2')"/><input type="button" value="拒付" onclick="batchDealAuditInFinance('-2')" /></div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>
