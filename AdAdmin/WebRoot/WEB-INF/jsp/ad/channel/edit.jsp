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
	});
	
	
	
	
	function form_submit(){
		var charge_type = document.getElementsByName("charge_type");
		var isCheck = false;
		for(var i=0;i<charge_type.length;i++){
			if(charge_type[i].checked){
				isCheck = true;
				break;
			}
		}
		if(!isCheck){
			alert("请选择结算方式");
			return;
		}
		var packageId = document.getElementById("packageId").value;
		if(packageId == ''){
			alert("请选择包渠道号!");
			return;
		}
		if($("#dayCount").val()&&!$("#budget_type").val()){
			alert("请选择限量类型！");
			return;
			
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
			<form id="updateAdAjustment" name="updateAdAjustment" action="manage!updateAdChannelInfo.do" method="post" >
				<div>
					<input type="hidden" name="id" id="id" value="${vo.id}" />
					<input type="hidden" name="price_update" id="price_update" value="${vo.price}" />
				</div>
				<div class="content_right admin_right">
    			<div class="subblock_1" style="display:block;border: 0px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top">结算方式：</td>
							<td>
							<input name="charge_type" type="radio" value="CPA" <c:if test="${vo.charge_type eq 'CPA' }">checked="checked"</c:if>/>CPA 
							<input name="charge_type" type="radio" value="CPC" <c:if test="${vo.charge_type eq 'CPC' }">checked="checked"</c:if>/>CPC 
							<input name="charge_type" type="radio" value="CPD" <c:if test="${vo.charge_type eq 'CPD' }">checked="checked"</c:if>/>CPD
							<input name="charge_type" type="radio" value="CPM" <c:if test="${vo.charge_type eq 'CPM' }">checked="checked"</c:if>/>CPM
							<input name="charge_type" type="radio" value="CPT" <c:if test="${vo.charge_type eq 'CPT' }">checked="checked"</c:if>/>CPT</td>
						</tr>
					
						<tr>
							<td valign="top">包渠道号：</td>
							<td colspan="3" class="actv_name">
								<select name="packageId" id="packageId" >
									<c:if test="${!empty vo.list}">
										<option value="">请选择</option>
										<c:forEach items="${vo.list}" var="bean" varStatus="status">
											<c:choose>
												<c:when test="${vo.packageId == bean.packageID}">
													<option value="${bean.packageID}" selected="selected">${bean.code}</option>
												</c:when>
												<c:otherwise>
													<option value="${bean.packageID}">${bean.code}</option>
												</c:otherwise>
											</c:choose>
									    </c:forEach>
									</c:if>
								</select>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td>结算单价：</td>
							<td class="price">
								<input type="text" name="price" id="price" value="${vo.price}" reg="(^[0-9]{1,2})+([.]\d{1,2})?$" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" tip="请输入数字0-9，且小数点的最多为两位，总长度为5" maxlength="5"/>元
								<font color="red">*</font>	
							</td>
						</tr>
						
						<tr>
							<td>日投放量：</td>
							<td>
								<input type="text" name="budget_day" id="dayCount" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" value="${vo.budget_day}" maxlength="20"/>
								<font color="red">*</font>	
							</td>
						</tr>

						<tr>
							<td valign="top">日投放量单位：</td>
							<td colspan="3" class="unit">
								<select name="budget_type" id="budget_type">
									<option value="">请选择</option>
									<option <c:if test="${vo.budget_type =='A'}"> selected="selected"</c:if> value="A" >A</option>
									<option <c:if test="${vo.budget_type =='D'}"> selected="selected"</c:if> value="D" >D</option>
									<option <c:if test="${vo.budget_type =='C'}"> selected="selected"</c:if> value="C" >C</option>
									<option <c:if test="${vo.budget_type =='M'}"> selected="selected"</c:if> value="M" >M</option>
								</select>
								<font>*</font>	
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