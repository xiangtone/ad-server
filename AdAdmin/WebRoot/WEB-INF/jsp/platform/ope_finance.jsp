<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" >
	function checkdeductions(flag){
		if(flag=='1'){
			var financeTax=document.getElementById("financeTax");
			financeTax.disabled=true;
			if(financeTax.value>=0 && financeTax.value<100){
				
			}else{
				financeTax.value="";
			}
			document.getElementById("deductionsMes").style.display="none";
		}else if(flag=='2'){
			document.getElementById("financeTax").disabled=false;
		}
	}
	
	function deductionsClick(){
		var financeTax=document.getElementById("financeTax").value;
		var deductionsMes=document.getElementById("deductionsMes");
		if(document.getElementById("financeTax").disabled==true){
			financeTax=-1;
		}
		if(document.getElementById("financeTax").disabled==false){
			if(financeTax == null) {
				return false;
			}
			if(financeTax!=""){
				if(financeTax>=0 && financeTax < 100){
					reg = /\.[0-9]{1}$|^[1-9]\.[0-9]{1}$|^[1-9]{1}[\d]*$|^0$/;
					if(financeTax.match(reg)){

					}else{
						deductionsMes.style.display="";
						return false;
					}
				} else {
					deductionsMes.style.display="";
					return false;
				}
			}else{
				deductionsMes.style.display="";
				return false;
			}
		}
		if(true){
			//var quickLy_task =$("#quickly_task").val();
			var url = "manage!updateFinanceTaxrate.do";
			if(confirm("确认要执行该操作吗？")){
				$.ajax({
					url:url,
					type:'POST',
					data:'financeTax='+financeTax,
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
			}//取消操作
		}
	}
	
	function applyDevAppManager(){
		var quickLy_task =$("#quickly_task").val();
		var url = "manage!updateQuickLyTaskEScore.do";
		if(confirm("确认要执行该操作吗？")){
			$.ajax({
				url:url,
				type:'POST',
				data:'quickLy_task='+quickLy_task,
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
		}//取消操作
		
	}	
	
	function refresh(){
		$("#my_from").submit();	
	}
	
	
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
					<div class="admin_right">
						<div class="content_right content_new">
						<div class="bord_bom1px">财务设置</div>
							<!--修改-->
							<fieldset>
								<legend>线上数据</legend> 
									<table>
										<tr><td>货币与积分的汇率：</td><td>1：${lineData.moneyScoreRate }</td></tr>
									</table>
							</fieldset>
						<form id="updateConfigEScore" action="manage!updateFinanceData.do" method="post">
							<input type="hidden" name="id" value="${modifyData.id == null?0:modifyData.id }" /> <!-- id为0代表是要插入 -->
							<fieldset>
								<legend>修改设置</legend>
									<table>
										<tr>
											<td colspan="2"><font color="red">*注：以下设置修改后将在第二天生效 </font></td>
										</tr>
									</table>
									<table><tr><td>货币与积分的汇率：</td><td class="moneyScoreRate">1：<input name="moneyScoreRate" class="w_input" type="text" value="${modifyData.moneyScoreRate }" reg="^[0-9]*$" tip="请填写数字！" /></td></tr></table>
							</fieldset>
							<!--修改end-->
							<div>
								<input name="submit" id="submit" type="submit" value="保存" />
							</div>
						</form>
						
						<form id="my_from" action="manage!configFinance.do" method="post">
							<input type="hidden" name="id" value="${quickLy.id == null?0:quickLy.id }" /> <!-- id为0代表是要插入 -->
							<fieldset>
								<legend>修改快速任务货币设置</legend>
									<table>
										<tr>
											<td colspan="2"><font color="red">*注：以下设置修改后将在第二天生效 </font></td>
										</tr>
									</table>
									<table><tr><td>货币与快速任务金币的汇率：</td><td class="quickly_task">1：<input name="quickly_task" id="quickly_task" class="w_input" type="text" value="${quickLy.quickly_task }" reg="^[0-9]*$" tip="请填写数字！" /></td></tr></table>
							</fieldset>
							<!--修改end-->
							<div>
								<input type="button" value="保存" onclick="applyDevAppManager();" />
							</div>
						</form>
						
						<form>
							<input type="hidden" name="deductionsset" /> <!-- id为0代表是要插入 -->
							<fieldset>
								<legend>扣税设置</legend>
									<table>
										<tr>
											<td>
												<input name="taxType" type="radio" value='1'
													<c:if test="${configPushDelay.configType eq 'FINANCETAX' && 
														(configPushDelay.configValue eq '' || configPushDelay.configValue eq '-1.0') }">
														checked="checked"
													</c:if>	
													onchange="checkdeductions('1')" />　　按个人劳务报酬所得扣税
											</td>
										</tr>
										<tr>
											<td>
												<input name="taxType" type="radio" value='2'
												<c:if test="${configPushDelay.configType eq 'FINANCETAX' && 
													configPushDelay.configValue ne '' && configPushDelay.configValue ne '-1.0' }">
													checked="checked"
												</c:if>
												onchange="checkdeductions('2')" />　　按固定税率扣税　　　
												
												<input type="text" id="financeTax" name="financeTax" style="text-align:right;width:30px;" 
												<c:if test="${configPushDelay.configValue eq '-1.0' }">value=""</c:if>
												<c:if test="${configPushDelay.configValue ne '-1.0' }">value="${configPushDelay.configValue }"</c:if>
												<c:if test="${configPushDelay.configType eq 'FINANCETAX' && 
													(configPushDelay.configValue eq '' || configPushDelay.configValue eq '-1.0') }">disabled="true"</c:if>
												/>%
												<span id="deductionsMes" class="red_c" style="color:#DC143C;display: none;">
												</>　请输入100以内的税率，可输入一位小数</span>
											</td>
										</tr>
									</table>
							</fieldset>
							<div>
								<input name="" type="button" onclick="deductionsClick()" value="保存" />
							</div>
						</form>
						
					</div>
					<span class="clear_span"></span>
		</div>
		</div>
	</div>
</body>
</html>
