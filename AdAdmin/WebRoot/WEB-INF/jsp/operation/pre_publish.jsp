<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

var dg;
$(document).ready(function(){
	dg = frameElement.lhgDG;
	dg.addBtn("save","保存",function(){
		form_submit();
	});
	
	$("#sel_month").change(function(){
		if($(this).val){
			var start=$($(this).find("option:selected")).attr("start");
			var end=$($(this).find("option:selected")).attr("end");
			$("#plan_start").val(start);
			$("#plan_end").val(end);
		}
	});
});


function insert(channel_id,channel_name,campaign_id,campaign_name,amount,cost){
	$("#channel_id").val(channel_id);
	$("#channel_name").val(channel_name);
	$("#campaign_id").val(campaign_id);
	$("#campaign_name").val(campaign_name);
	$("#amount").val(amount);
	$("#cost").val(cost);
}

function add(id){
	var start_time=$("#plan_start").val();
	var end_time=$("#plan_end").val();
	var os=$('input:radio[name="os"]:checked').val();
	var url = "${pageContext.request.contextPath}/finance/selectChannel.do?os="+os+"&start_date="+start_time+"&end_date="+end_time;
	var showDg = new dg.curWin.$.dialog({id:'dialogrole',title:'选择渠道',page:url,width:778,height:450,fixed:true,drag:true,resize:false,maxBtn:false,cover:true,parent:dg});
	showDg.ShowDialog();
}

function form_submit(){
	if(validate_form()){
		$("#my_form").ajaxSubmit(function(data){
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
	}
}

function validate_form(){
	if(!validate_in()){
		return false;
	}
	return true;
}

function validate_in(){
	if(!$("#sel_month").val()){
		alert("亲，请选择业绩月！！");
		return false;
	}
	return true;
}

</script>
</head>
<body>
	<div style="display: block;height: 500px;width: 900px;">
		<div style="display: block;float:left;width: 450px;height: 400px;padding-top: 40px;padding-left: 40px;">
			<form  method="post" id="my_form" action="${pageContext.request.contextPath}/manage!achievementReportPublish.do">
				<div>
					<input type="hidden" name="id" id="channel_id"  value="${id}"/>
				</div>
				<table width="100%">
					<tr>
						<td>
							<table>
								<tr>
									<td valign="middle">业绩月：</td>
									<td colspan="3" class="actv_name">
										<select name="month" id="sel_month">
											<option value="">请选择</option>
											<c:if test="${!empty selMonth}">
												<c:forEach items="${selMonth}" var="bean" varStatus="status">
												<option value="${bean.month}" selected="selected" start="${bean.month_start_date}" end="${bean.month_end_date}" >${bean.month}</option> 
											    </c:forEach>
											</c:if>
										</select>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<td valign="middle">备注：</td>
									<td colspan="3">
										<textarea rows="10" cols="50" name="note"></textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>