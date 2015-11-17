<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js"></script>
<link href="${pageContext.request.contextPath}/css/regist/validate.css" rel="stylesheet" type="text/css" />
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
			window.location.href="manage!updateFinanceTaxrate.do?financeTax="+financeTax;
		}
	}
</script>
</head>
<body>
	<div class="main admin_main" style="background:#F6F6F6">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">SDK参数设置</div>
						<div style="display: block;float: left;">
							<!--修改-->
							<fieldset style="width: 500px;">
								<legend>当前规则</legend> 
									<table>
										<tr>
											<td>积分获取延时时间：</td>
											<td>
												${score_delay_time.configValue}
											</td>
										</tr>
									</table>
							</fieldset>
						</div>
						<div style="display: block;float: left;">
						<form id="updateConfigEScore" action="manage!updateAlarmConfig.do" method="POST">
							<input type="hidden" name="id" value="${modifyData.id == null?0:modifyData.id }" /> <!-- id为0代表是要插入 -->
							<fieldset style="width: 500px;">
								<legend>修改设置</legend>
									<table>
										<tr>
											<td>积分获取延时时间：</td>
											<td class="score_delay_time">
												<input id="advPlatformPercentage" maxlength="3"  name="score_delay_time" class="w_input" style="text-align:right;width:30px;" type="text" value="${score_delay_time.configValue}" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
												<font color="red">(单位：秒)</font>
											</td>
										</tr>
									</table>
									<!--  
									 <table>
									 	<tr>
									 		<td>详情页停留时间：</td>
									 		<td class="detial_stay_time">
									 			<input name="detial_stay_time" class="w_input" type="text" value="${detialStayTime}" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！"/>
									 			<font color="red">(单位：秒)</font>
									 		</td>
									 	</tr>
									 </table> 
									 <table>
									 	<tr>
									 		<td>凌晨12点-6点的下载数：</td>
									 		<td class="night_download_amount">
									 			<input name="night_download_amount" class="w_input"  type="text" style="text-align:right;width:30px;" value="${NIGHT_DOWNLOAD_AMOUNT.configValue }" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
									 			<font color="red">(单位：次)</font>
									 		</td>
									 	</tr>
									 </table>
									<table>
										<tr>
											<td>同IP设备数：</td>
											<td class="same_ip_equipment">
												<input name="same_ip_equipment" class="w_input" type="text" value="${SAME_IP_EQUIPMENT.configValue }" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
												<font color="red">(单位：个)</font>
											</td>
										</tr>
									</table>
									<table>
										<tr>
											<td>同应用对应的同IP设备数超过预警值的IP数：</td>
											<td class="same_ip_equipment_ip_num">
												<input name="same_ip_equipment_ip_num" class="w_input" type="text" value="${SAME_IP_EQUIPMENT_IP_NUM.configValue }" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
												<font color="red">(单位：个)</font>
											</td>
										</tr>
									</table>
									<table>
										<tr>
											<td>同IP激活数：</td>
											<td class="same_ip_activation">
												<input name="same_ip_activation" class="w_input" type="text" value="${SAME_IP_ACTIVATION.configValue}" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
												<font color="red">(单位：次)</font>
											</td>
										</tr>
									</table>
									<table>
										<tr>
											<td>同应用对应的同IP激活数超过预警值的激活数：</td>
											<td class="same_ip_activation_ip_num">
												<input name="same_ip_activation_ip_num" class="w_input" type="text" value="${SAME_IP_ACTIVATION_IP_NUM.configValue }" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
												<font color="red">(单位：个)</font>
											</td>
										</tr>
									</table>
									<table>
										<tr>
											<td>同手机应用访问数：</td>
											<td class="same_mobile_request_app">
												<input name="same_mobile_request_app" class="w_input" type="text" value="${SAME_MOBILE_REQUEST_APP.configValue}" reg="^[0-9]*[1-9][0-9]*$" tip="请填写数字！" />
												<font color="red">(单位：次)</font>
											</td>
										</tr>
									</table>
									-->
							</fieldset>
							<!--修改end-->
							<div>
								<input name="submit" id="submit" type="submit" value="保存" />
							</div>
						</form>
						</div>
					</div>
					<span class="clear_span"></span>
		</div>
		</div>
	</div>
</body>
</html>
