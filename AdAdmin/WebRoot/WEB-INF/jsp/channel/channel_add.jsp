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
<title>运营管理后台开发者应用审核</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	var flag=true;
	dg.addBtn("save","保存",function(){
		if(flag){
			flag=false;
			$("#addAccountChannel").ajaxSubmit(function(data){
				if(vaildateForm("addAccountChannel")){
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
				}
			
			});
		}
		
	});


function channe_mode_sdk(){
	var a = $("#channe_mode").val();
	if(a!=1){
		$("#channe_mode_1").hide();
	}else{
		$("#channe_mode_1").show();
	}
}

	
	//校验输入的密码
	function passwordRatio() {
		var email = document.getElementById("email").value;
		var param_data = 'oldpass=' + email;
		$.ajax({
			url : 'manage!channelemail.do',
			type : 'POST',
			data : param_data,
			dataType : 'text',
			beforeSend : function() {
			},
			success : function(data) {
				if (data == "false") {
					$("#sp").html("渠道账户已存在");
				} else {
					$("#sp").html("");

				}

			}
		});
	}
</script>	
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<form name="addAccountChannel" id="addAccountChannel" method="post" action="manage!addAccountChannel.do">
					<table>
						<tr>
							<td  height="30" align="right" width="100">用户名：</td>
							<td align="left" class="email">
								<input	type="text" name="email" id="email"	reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tip="请输入邮箱"	tips="邮箱已存在！" maxlength="30" onblur="passwordRatio()" />
								<span id="sp" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<td align="right">密码：</td>
							<td class="password" align="left">
								<input	style="width: 149px" type="password" name="password" id="password" reg="^(\w){6,15}$" tip="请输入密码 " maxlength="15" />
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
						<tr>
							<td align="right">渠道名称：</td>
							<td class="channel_name" align="left"><input
								name="name" type="text" id="channel_name"
								reg="^[\u4e00-\u9fa5\w\W]{2,50}$" tip="请输入2-50个字符  "
								maxlength="50" /></td>
							</tr>
						<tr>	
							<td align="right">渠道联系人：</td>
							<td class="real_name" align="left"><input type="text"
								name="real_name" id="real_name" reg="^[\u4e00-\u9fa5\w\W]{2,10}$"
								tip="请填写姓名 " maxlength="10" />
							</td>
						</tr>
						<tr>
							<td align="right">手机号码：</td>
							<td align="left" class="phone"><input type="text"
								name="phone" id="phone" reg="^1[3,5,8,7]\d{9}$" tip="请输入手机号 "
								maxlength="11" /></td>
								</tr>
						<tr>
							<td align="right">QQ：</td>
							<td class="qq" align="left">
								<input type="text" name="qq" id="qq" reg="[1-9][0-9]{5,}" tip="请输入QQ号" maxlength="20" />
							</td>
						</tr>
						<tr>
							<td align="right">渠道负责人：</td>
							<td class="channe_manager" align="left">
								<input name="channe_manager" type="text" id="channe_manager" reg="^[\u4e00-\u9fa5\w\W]{2,10}$" tip="请输入渠道负责人  " maxlength="10" /></td>
							</tr>
						<tr>
							<td align="right">平台类型：</td>
							<td>
								<select name="os" style="width: 155px;">
									<option value="android">安卓</option>
									<option value="ios">Ios</option>
							</select>
							</td>
						</tr>
						<tr>
							<td align="right">合作方式：</td>
							<td>
								<select name="channe_mode" id="channe_mode"  style="width: 155px;" onchange="channe_mode_sdk()">
									<option value="0">代理</option>
									<option value="1">SDK</option>
									<option value="2">API</option>
							</select>
							<span id="channe_mode_1" style="display: none;">
							<input id="marking" name="marking"
								type="text" />
								</span>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
