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
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save","保存",function(){ 
		if(vaildateForm("adv_form")){
		$("#adv_form").ajaxSubmit(function(data){
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
		invoce();
		invoce_ad();
	});

			
		function invoce(){
			var a = $("#invoice_require").val();
			if(a!=2){
				$("#invoice_require_others_2").hide();
				}else{
				$("#invoice_require_others_2").show();
				}
			
			}
	function invoce_ad(){
		var a="${findAdvVo.invoice_require_others}";
		if(a){
			$("#invoice_require_others_2").show();
			}else{
			$("#invoice_require_others_2").hide();
		 	}
		}	
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>财务信息</span></a></li>
    			</ul>
				<form name="update" id="adv_form" method="post" action="manage!updateAdv.do">
					<input name="id" type="hidden" id="advId" value="${advId}" />
					<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
						<table width="500px;">
							<tr>
								<td width="23%" height="30" align="right">广告主账号：</td>
								<td width="77%" align="left" class="email">${findAdvVo.email}</td>
							</tr>
							<tr>
								<td align="right">公司名称：</td>
								<td class="companyName" align="left">
									<input	name="companyName" type="text" id="companyName" reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入4-50个字符  " maxlength="50" value="${findAdvVo.companyName}" />
								</td>
							</tr>
							<tr>
								<td align="right">公司电话：</td>
								<td align="left" class="fixedPhone">
									<input type="text" name="fixedPhone" id="fixedPhone" reg="^([0-9]{3,4})?([2-9][0-9]{6,7})+([0-9]{1,4})?$" tip="请输入电话号 " maxlength="20" value="${findAdvVo.fixedPhone}" />
								</td>
							</tr>
							<tr>
								<td align="right">详细地址：</td>
								<td class="companyAddress" align="left">
									<input name="companyAddress" type="text" id="companyAddress" reg="^[\u4e00-\u9fa5\w\W]{5,50}$" tip="请输入5-100个字符 " maxlength="100" value="${findAdvVo.companyAddress}" />
								</td>
							</tr>
							<tr>
								<td align="right">联系人：</td>
								<td class="realName" align="left">
									<input type="text"	name="realName" id="realName" reg="^[\u4e00-\u9fa5]+$" tip="请填写姓名 " maxlength="10" value="${findAdvVo.realName}" />
								</td>
							</tr>
							<tr>
							<td align="right">所属区域：</td>
							<td align="left"><select name="area_type" id="area_type" style="width: 153px">
									<option value="4"
										<c:if test="${findAdvVo.area_type eq '4' }">
											    selected="selected"
											</c:if>>华南</option>
									<option value="1"
										<c:if test="${findAdvVo.area_type eq '1' }">
											    selected="selected"
											 </c:if>>华东</option>
									<option value="2"
										<c:if test="${findAdvVo.area_type eq '2' }">
											    selected="selected"
											 </c:if>>华北</option>
									<option value="0"
										<c:if test="${findAdvVo.area_type eq '0' }">
											    selected="selected"
											 </c:if>>平台</option>
							</select>
							</td>
						</tr>
							<tr>
								<td align="right">手机号码：</td>
								<td align="left" class="mobilePhone">
									<input type="text"	name="mobilePhone" id="mobilePhone" reg="^1[3,5,8,7]\d{9}$"	tip="请输入手机号 " maxlength="11" value="${findAdvVo.mobilePhone}" />
								</td>
							</tr>
							<tr>
								<td align="right">QQ：</td>
								<td class="qq" align="left">
								<input type="text" name="qq" id="qq" reg="[1-9][0-9]{5,}" tip="请输入QQ号" maxlength="20" value="${findAdvVo.qq}" />
							</td>
						</tr>
						<tr>
							<td align="right">邮编：</td>
							<td class="postCode" align="left">
								<input type="text"	name="postCode" id="postCode"  maxlength="6" value="${findAdvVo.postCode}" />
							</td>
						</tr>
						<tr>
							<td align="right">信用额度：</td>
							<td class="creditQuota" align="left">
								<input type="text"	name="creditQuota" id="creditQuota" reg="(^[0-9]{1,8})+([.]\d{1,2})?$"	tip="请输入信用额度" maxlength="20" value="${findAdvVo.creditQuota}" />
							</td>
						</tr>
					</table>
					</div>
					<div class="subblock_2" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
						<table width="500px;">
						<tr>
							<td width="23%" height="30" align="right">银行账号：</td>
							<td width="70%" height="30" align="left"><input id="bank_account" name="bank_account" type="text"
								value="${findAdvVo.bank_account}" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
						</tr>
						<tr>
							<td align="right">开户行：</td>
							<td align="left"><input id="bank_subbranch" name="bank_subbranch"
								type="text" value="${findAdvVo.bank_subbranch}" maxlength="50" onkeyup="value=value.replace(/[\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))" />
							</td>
							<td id="bank_sub"></td>
						</tr>
						<tr>
							<td align="right">公司注册地址：</td>
							<td align="left"><input id="company_regist_address"
								name="company_regist_address" type="text"
								value="${findAdvVo.company_regist_address}" onkeyup="value=value.replace(/[\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))"  maxlength="100"/>
							</td>
						</tr>
						<tr>
							<td align="right">纳税人识别号：</td>
							<td align="left"><input id="taxpayer_number" name="taxpayer_number"
								type="text" value="${findAdvVo.taxpayer_number}" onKeyUp="value=value.replace(/[^\d|chun]/g,'')" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<td align="right">发票要求：</td>
							<td align="left"><select name="invoice_require" id="invoice_require" style="width: 153px" onchange="invoce()">
									<option value="0"
										<c:if test="${findAdvVo.invoice_require eq '0' }">
											    selected="selected"
											</c:if>>增值税专用发票</option>
									<option value="1"
										<c:if test="${findAdvVo.invoice_require eq '1' }">
											    selected="selected"
											 </c:if>>增值税普通发票</option>
									<option value="2" 
										<c:if test="${findAdvVo.invoice_require eq '2' }">
											    selected="selected"
											 </c:if>>其他</option>
							</select> 
								<span id="invoice_require_others_2" style="display: none;">
									<input id="invoice_require_others"
										name="invoice_require_others" type="text"  value="${findAdvVo.invoice_require_others}"/>
								</span>								
							</td>
						</tr>
						<!--  
						<tr>
							<td align="right">营业执照：</td>
							<td align="left">
									<input id="business_license" name="business_license"
										type="file" />
							</td>
						</tr>
						<tr>
							<td align="right">税务登记证：</td>
							<td align="left">
									<input id="tax_reg_cer" name="tax_reg_cer" type="file" />
							</td>
						</tr>
						<tr>
							<td align="right">开户许可证：</td>

							<td align="left">
									<input id="account_permit" name="account_permit" type="file" />
								
							</td>
						</tr>
						<tr>
							<td align="right">纳税人资质证明：</td>
							<td align="left">
									<input id="taxpayer_certificate" name="taxpayer_certificate"
										type="file" />
							</td>
						</tr>
						-->
					</table>
					</div>
				</form>
			</div>
		</div>
		</div>
</body>
</html>
