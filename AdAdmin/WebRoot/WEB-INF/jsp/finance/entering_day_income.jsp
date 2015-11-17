<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者应用审核  </title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	 dg.addBtn("save","保存",function(){ 
		if(vaildateForm("campaign")){
			$("#campaign").ajaxSubmit(function(data){
					if(data){
						var dataObj=eval("("+data+")");//转换为json对象 
						if(dataObj.status==1){
							alert("操作成功！！");
							dg.curWin.refresh();
						}else if(dataObj.status=-1){
							alert("操作失败，请重试！！");
							dg.curWin.refresh();
						}else{
							alert("操作失败，请重试！！");
							dg.curWin.refresh();
						}
					}else{
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
			});
		}
	}); 
	 
	 dg.SetCancelBtn('关闭',function(){
		var id = document.getElementById("id").value;
		 var url = "manage!deleteCampaignIncomeDay.do?id="+id;
			window.location.href=url;
			dg.curWin.refresh();
		});
</script>	
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<form name="campaign" id="campaign" method="post" action="manage!saveCampaignIncomeDay.do">
					<div>
					<font color="red">亲！如不保存请点击右下角关闭按钮，不要点击X</font>
					</div>
					<table width="100%">
						<tr>
							<td>
								<table>
									<tr>
										<td align="right">结算单号：</td>
										<td  align="left">
											<input name="id" type="text" id="id" value="${vo.id}" readonly="readonly" />
										</td>
										<td align="right">系统：</td>
										<td  align="left">
											<input name="os" type="text" id="os" value="${vo.os}" readonly="readonly" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td align="right">广告主：</td>
										<td  align="left">
											<input name="company_name" type="text" id="company_name"	value="${vo.company_name}" readonly="readonly" disabled="disabled"/>
										</td>
										<td align="right">结算方式：</td>
										<td  align="left">
											<input name="balance_model" type="text" id="balance_model"	 value="${vo.balance_model}" readonly="readonly" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td align="right">活动名称：</td>
										<td  align="left">
											<input name="campaign_name" type="text" id="campaign_name"	value="${vo.campaign_name}" readonly="readonly" disabled="disabled"/>
										</td>
										<td align="right">结算单价：</td>
										<td  align="left">
											<input name="price" type="text" id="price"	 value="${vo.price}" readonly="readonly" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td align="right">结算周期：</td>
										<td style="color: blue;"><input type="text" id="month_stat_date" name="month_stat_date"  onclick="WdatePicker()" class="Wdate" value="${vo.month_stat_date}" disabled="disabled"/>
										至
										<input type="text" id="month_end_date" name="month_end_date"  onclick="WdatePicker()" class="Wdate" value="${vo.month_end_date}" disabled="disabled"/></td>
									</tr>
									<tr>
										<td align="right">预确认：</td>
										<td align="left">
											<input name="forecast_amount" type="text" id="forecast_amount" value="${vo.forecast_amount}"  maxlength="50" readonly="readonly" disabled="disabled"/>
										</td>
										<td align="right">预确认金额：</td>
										<td  align="left">
											<input name="forecast_money" type="text" id="forecast_money" value="${vo.forecast_money}"  maxlength="50" readonly="readonly" disabled="disabled"/>
										</td>
									</tr>
									<tr>
										<td align="right">结算金额：</td>
										<td  align="left">
											<input name="income_money" type="text" id="income_money" value="${vo.income_money}"  maxlength="50" />
										</td>
									</tr>
									<tr>
										<td align="right">备注：</td>
										<td  align="left">
											<input type="text" name="income_remark" id="income_remark" value="${vo.income_remark}"/>
										</td>
									</tr>										
								</table>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
