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
<title>运营管理后台方案调整修改页面</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			form_submit();
		});
		
		
		var category_str='${category_str}';
		if(category_str){
			var arr=category_str.split(",");
			for(var i=0;i<arr.length;i++){
				$("#category_"+arr[i]).attr("checked","checked");
			}
		}
		initChackBox();
	});
	
	
	function initChackBox(){
		var status="${vo.status}";
		if(status=="1"){
			$("#schedule_start").attr("checked",false);
			$("#schedule_start").attr("readonly","readonly");
			$("#schedule_start").click(function(){
				   //覆盖系统事件，模拟只读效果
				 return false;
			});
		}
		if(status=="-10"){
			$("#schedule_start").attr("checked",false);
			$("#schedule_start").attr("readonly","readonly");
			$("#schedule_start").click(function(){
				   //覆盖系统事件，模拟只读效果
				 return false;
			});
			
			$("#schedule_end").attr("checked",false);
			$("#schedule_end").attr("readonly","readonly");
			$("#schedule_end").click(function(){
				   //覆盖系统事件，模拟只读效果
				 return false;
			});
		}
	
		
	}
	
	function form_submit(){
		var charge_type = document.getElementsByName("charge_type");
		var isCheck = false;
		for(var i=0;i<charge_type.length;i++){
			if(charge_type[i].checked){
				isCheck = true;
				break;
			}
		}
		
		
		
		var dg=frameElement.lhgDG;
		if(vaildateForm("updateAdAjustment")){
			$("#updateAdAjustment").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						
					}else{
						alert("提交失败！！");
					}
				}else{
					alert("提交失败！！");
				}
			});
		};
	}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<form id="updateAdAjustment" name="updateAdAjustment" action="savePayments.do" method="post" >
				<div>
					<input type="hidden" name="balance_account_id" value="${id}" />
					<input type="hidden" id="min_date" value="" />
					<input type="hidden" id="max_date" value="<fmt:formatDate value="${today}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />" />
				</div>
				<div class="content_right admin_right">
    			<div class="subblock_1" style="display:block;border: 0px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top">是否二次回款：</td>
							<td>
								<input name="payments_type" type="radio" value="1" checked="checked"/>是  
								<input name="payments_type" type="radio" value="0" />否 
							</td>
						</tr>
						<tr>
							<td valign="top">回款日期：</td>
							<td colspan="3">
								<input type="text" name=pay_date  class="Wdate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'max_date\')}'});" value='<fmt:formatDate value="${vo.today}" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss"/>' /> 
							</td>
						</tr>
						<tr>
							<td>回款金额：</td>
							<td class="amount">
								<input type="text" name="amount" value="${vo.price}" reg="(^[0-9]{1,8})+([.]\d{1,2})?$" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" tip="请输入数字0-9，且小数点的最多为两位，总长度为10" maxlength="10"/>元
								<font color="red">*</font>	
							</td>
						</tr>
					</table>
				</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>