<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control"  content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>运营管理后台开发者应用审核</title>
	<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		if(vaildateForm("regist")){
			$("#regist").ajaxSubmit(function(data) {
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						alert("操作失败"+dataObj.error);
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
</script>
</head>

<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right" >
			<form name="regist" id="regist" method="post" action="manage!saveAdv.do">
				<div>
					<input type="hidden" name="salesman_area_type" value="0" />
					<input type="hidden" name="password" value="123456" />
					<input type="hidden" name="confimPassword" value="123456" />
					
				</div>
					<fieldset class="search_fieldset" style="width: 400px;">
					<legend>基本信息 </legend>
					<table width="500px;">
						<tr>
							<td width="23%" height="30" align="right">广告主账号：</td>
							<td width="77%" align="left" class="email">
								<input type="text" name="email" id="email" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入邮箱" tips="邮箱已存在！" maxlength="50" />
							</td>
						</tr>
						<!-- 
						<tr>
							<td align="right">用户密码：</td>
							<td class="password" align="left">
								<input style="width: 149px" type="password" name="password" id="password" reg="^(\w){6,15}$" tip="请输入密码 " maxlength="15" />
							</td>
						</tr>
						<tr>
							<td align="right">确认密码：</td>
							<td class="confimPassword" align="left"><input
								style="width: 149px" type="password" name="confimPassword"
								id="confimPassword" reg="^(\w){6,15}$" tip="请输入密码" tips="密码不一致！"
								maxlength="15" /> <b></b>
							</td>
						</tr>
						-->
						<tr>
							<td align="right">公司名称：</td>
							<td class="companyName" align="left"><input
								name="companyName" type="text" id="companyName"	reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入4-50个字符  "
								maxlength="50" /></td>
						</tr>
						<tr>
							<td align="right">公司电话：</td>
							<td align="left" class="fixedPhone"><input type="text"
								name="fixedPhone" id="fixedPhone"
								reg="^([0-9]{3,4})?([2-9][0-9]{6,7})+([0-9]{1,4})?$"
								tip="请输入电话号 " maxlength="20" /></td>
						</tr>
						<tr>
							<td align="right">详细地址：</td>
							<td class="companyAddress" align="left"><input
								name="companyAddress" type="text" id="companyAddress"
								reg="^[\u4e00-\u9fa5\w\W]{5,100}$" tip="请输入5-100个字符 "
								maxlength="100" /></td>
						</tr>


						<tr>
							<td align="right">联系人：</td>
							<td class="realName" align="left"><input type="text"
								name="realName" id="realName" reg="^[\u4e00-\u9fa5]+$"
								tip="请填写姓名 " maxlength="10" />
							</td>
						</tr>
						<!-- 
								<tr>
							<td align="right">所属区域：</td>
							<td><select name="salesman_area_type" style="width: 155px;">
									<option value="4">华南</option>
									<option value="1">华东</option>
									<option value="2">华北</option>
									<option value="0">平台</option>
							</select>
							</td>
						</tr>
						
						 -->
					
						<tr>
							<td align="right">手机号码：</td>
							<td align="left" class="mobilePhone"><input type="text"
								name="mobilePhone" id="mobilePhone" reg="^1[3,5,8,7]\d{9}$"
								tip="请输入手机号 " maxlength="11" /></td>
						</tr>
						<tr>
							<td align="right">QQ：</td>
							<td class="qq" align="left"><input type="text" name="qq"
								id="qq" reg="[1-9][0-9]{5,}" tip="请输入QQ号" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td align="right">邮编：</td>
							<td class="postCode" align="left">
								<input type="text" name="postCode" id="postCode" maxlength="6" />
							</td>
						</tr>
						<tr>
							<td align="right">信用额度：</td>
							<td class="creditQuota" align="left">
								<input type="text"	name="creditQuota" id="creditQuota"	reg="(^[0-9]{1,8})+([.]\d{1,2})?$" tip="请输入信用额度" maxlength="11"  value="100000.00"  />
							</td>
						</tr>
					</table>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>