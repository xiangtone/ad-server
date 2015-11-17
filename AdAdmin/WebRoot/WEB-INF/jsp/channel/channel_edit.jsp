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
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save","保存",function(){ 
		$("#updateChannel").ajaxSubmit(function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("提交成功！！");
					dg.curWin.refresh();
					//dg.cancel();
				}else if(dataObj.status=-1){
					
				}else{
					alert("操作失败，请重试！！");
				}
			}else{
				alert("操作失败，请重试！！");
			}
		
	});
	});
	function resetChannel(id){
		var url = "manage!resetChannel.do?id="+id;
		window.location.href=url;
		}
//验证
	/*function a(){
		var bank_sub=document.getElementById("bank_subbranch").value;
		alert(bank_sub);
		var reg = new RegExp("^[\u4e00-\u9fa5\w\W]{4,10}$");
		if(!reg.test(bank_sub)){
			$("#bank_sub").html("<font color=red>*请输入4-50个字符</font>");
			return;
			}
		}*/

		$(document).ready(function(){
			invoice();
			invoce_ch();
			channe_mode_sdk();
		});
			
		function invoice(){
		      var a = $("#invoice_require").val();
		      if(a!=2){
		    	  	$("#invoice_1").hide();
		          }else{
					$("#invoice_1").show();
		              }
				}
		
		function channe_mode_sdk(){
		      var a = $("#channe_mode").val();
		      if(a!=1){
		    	  	$("#channe_mode_1").hide();
		          }else{
					$("#channe_mode_1").show();
		              }
				}
		function mode_sdk_ch(){
			var a="${channelBankInfo.invoice_require_others}";
			if(a){
				$("#invoice_1").show();
				}else{
				$("#invoice_1").hide();
			 	}
			}		


		
		function invoce_ch(){
			var a="${channelBankInfo.invoice_require_others}";
			if(a){
				$("#invoice_1").show();
				}else{
				$("#invoice_1").hide();
			 	}
			}		
			
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>财务信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_3"><span>重置密码</span></a></li>
    			</ul>
				<form name="updateChannel" id="updateChannel" method="post"
					action="manage!updateChannel.do">
					<input name="id" id="id" type="hidden" value="${entiy.id}"/>
					<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td align="right">&nbsp;</td>
						</tr>
						<tr>
							<td align="right">渠道名称：</td>
							<td class="name" align="left"><input
								name="name" type="text" id="channel_name"
								reg="^[\u4e00-\u9fa5\w\W]{2,50}$" tip="请输入4-50个字符  "
								maxlength="50"  value="${entiy.name}"/></td>
						</tr>
						<tr>		
							<td align="right">渠道联系人：</td>
							<td class="real_name" align="left"><input type="text"
								name="real_name" id="real_name" reg="^[\u4e00-\u9fa5]+$"
								tip="请填写姓名 " maxlength="20" value="${entiy.real_name}"/>
							</td>
						</tr>
						<tr>
							<td align="right">手机号码：</td>
							<td align="left" class="phone"><input type="text"
								name="phone" id="phone" reg="^1[3,4,5,8]\d{9}$" tip="请输入手机号 "
								maxlength="11"  value="${entiy.phone}"/></td>
								</tr>
						<tr>
							<td align="right">QQ：</td>
							<td class="qq" align="left"><input type="text" name="qq"
								id="qq" reg="[1-9][0-9]{4,}" tip="请输入QQ号" maxlength="20"  value="${entiy.qq}"/>
							</td>
						</tr>
						<tr>
							<td align="right">渠道负责人：</td>
							<td class="channe_manager" align="left"><input
								name="channe_manager" type="text" id="channe_manager"
								reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入4-50个字符  "
								maxlength="50"  value="${entiy.channe_manager}"/></td>
								</tr>
								<tr>
							<td align="right">平台类型：</td>
							<td><select name="os" id="os" style="width: 155px;">
									<option value="android"
										<c:if test="${entiy.os =='android' }">
											    selected="selected"
											</c:if>>安卓</option>
									<option value="ios"
										<c:if test="${entiy.os =='ios' }">
											    selected="selected"
											 </c:if>>Ios</option>
							</select> 
							</td>
						</tr>
						<tr>
							<td align="right">合作方式：</td>
							<td><select name="channe_mode" id="channe_mode" style="width: 155px;" onchange="channe_mode_sdk()">
									<option value="0"
										<c:if test="${entiy.channe_mode eq 0 }">
											    selected="selected"
											</c:if>>代理</option>
									<option value="1"
										<c:if test="${entiy.channe_mode eq 1 }">
											    selected="selected"
											 </c:if>>SDK</option>
									<option value="2"
										<c:if test="${entiy.channe_mode eq 2 }">
											    selected="selected"
											 </c:if>>API</option>
							</select> 
							<span id="channe_mode_1" style="display: none;">
							<input id="marking" name="marking" value="${entiy.marking}"
								type="text" />
								</span>
							</td>
						</tr>
					</table>
					</div>
					<div class="subblock_2" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td align="right">收款人：</td>
							<td><input id="account_hoder" name="account_hoder" type="text" value="${channelBankInfo.account_hoder}" onkeyup="value=value.replace(/[\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))" maxlength="50"/>
							</td>
							</tr>
						<tr>
							<td align="right">开户行：</td>
							<td><input id="bank_subbranch" name="bank_subbranch"
								type="text" value="${channelBankInfo.bank_subbranch}" onkeyup="value=value.replace(/[\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td align="right">银行账号：</td>
							<td><input id="bank_account" name="bank_account" type="text" value="${channelBankInfo.bank_account}" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
							</tr>
						<tr>
							<td align="right">发票要求：</td>
							<td><select name="invoice_require" id="invoice_require" onchange="invoice()" style="width: 153px">
									<option value="" selected="selected">请选择</option>
									<option value="0"
										<c:if test="${channelBankInfo.invoice_require eq '0' }">
											    selected="selected"
											</c:if>>增值税专用发票</option>
									<option value="1"
										<c:if test="${channelBankInfo.invoice_require eq '1' }">
											    selected="selected"
											 </c:if>>增值税普通发票</option>
									<option value="2"
										<c:if test="${channelBankInfo.invoice_require eq '2' }">
											    selected="selected"
											 </c:if>>其他</option>
							</select> 
								<span id="invoice_1" style="display: none;">
									<input id="invoice_require_others"
										name="invoice_require_others" type="text" value="${channelBankInfo.invoice_require_others}"/>
								</span>
							</td>
						</tr>
					</table>
					</div>
					<div class="subblock_3" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
						<td>
							<input type="button" value="重置密码" onclick="resetChannel('${k.id}');" style="cursor: pointer;" />
							</td>
						</tr>
					</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
