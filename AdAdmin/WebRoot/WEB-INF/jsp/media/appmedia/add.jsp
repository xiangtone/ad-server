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
});


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
	if(!vaildateForm("my_form")){
		return false;
	}
	return true;
}

function validate_in(){
	if(!$("#area_type").val()){
		alert("请选择区域！！");
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<div style="display: block;height: 500px;width: 900px;">
		<div style="display: block;float:left;width: 400px;height: 400px;">
			<form  method="post" id="my_form" action="${pageContext.request.contextPath}/saveAppMedia.do">
				<div>
				</div>
				<table width="100%">
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td width="23%" valign="middle">媒介姓名：</td>
									<td width="77%" class="name" colspan="3">
										<input name="name" type="text" reg="^[\u4e00-\u9fa5\w\W]{2,10}$" tip="真实名称" maxlength="50" />
									</td>
								</tr>
								<tr>
									<td valign="middle">电话：</td>
									<td colspan="3" class="mobile">
										<input type="text"  name="mobile" reg="^1[3,5,8,7]\d{9}$" tip="请输入手机号 " maxlength="11"  />
									</td>
								</tr>
								<tr>
									<td valign="middle">QQ：</td>
									<td class="qq">
										<input type="text" name="qq" reg="[1-9][0-9]{5,}" tip="请输入QQ号" maxlength="20" />
									</td>
								</tr>
								<tr>
									<td valign="cost">组：</td>
									<td class="cost">
										<select name="area_type" id="area_type" style="width: 155px;">
											<option value="">请选择</option>
											<option value="0">平台</option>
											<option value="1">微赢</option>
											
										</select>
										<font color="red">*</font>	
									</td>
								</tr>
								<tr>
									<td valign="cost">系统账号：</td>
									<td class="cost">
										<select name="sys_user_id" style="width: 155px;">
											<option value="">请选择</option>					
											<c:forEach items="${sysUserList}" var="entity">
												<option value="${entity.id}" <c:if test="${vo.salesman_id eq entity.id}"> selected="selected" </c:if>>${entity.realName}</option>
											</c:forEach>
										</select>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td><font color="red" style="font-size: 5px;">跟系统账户关联后,该媒介人员才能正常查看数据</font></td></tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>