<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者账号管理基本资料-基本资料</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"	rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>投放信息</span></a></li>
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<div class="bord_bom1px">
						开发者账号管理&nbsp;&nbsp;&nbsp; <span>${dev.email }</span>
					</div>
					<div>
						<a href="manage!editDev.do?dev_id=${dev.id }">应用列表</a>&nbsp;|&nbsp;
						基本概况&nbsp;|&nbsp; <a href="manage!detailFnancialInfo.do?dev_id=${dev.id}">财务资料</a>
					</div>
					<div class="mar_bom10 margtop10">应用数量：${appCount }</div>
					<fieldset class="mar_bom10 margtop10">
						<legend>收入概况</legend>
						<table width="100%">
							<tr>
								<td width="30%">确定收入：<escore:formatNumber
									value="${dev.finance_income}" maxFractionDigits="2" />元</td>
								<td width="30%">可提款收入：<escore:formatNumber
									value="${developerVo.applyMoney }" maxFractionDigits="2" />元</td>
								<td width="30%">累计提现：<escore:formatNumber
									value="${dev.totalMoney }" maxFractionDigits="2" />元</td>
							</tr>
						</table>
					</fieldset>

					<div class="bord_bom1px">基本资料</div>
					<!--个人-->
					<c:if test="${dev.type==1}">
						<form name="updateDevManager" action="manage!updateDevManager.do"	method="post" class="personal">
						<input type="hidden" name="id" value="${dev.id }" />
						<input type="hidden" name="type" value="${dev.type}" />
						<table>
							<tr>
								<td>账号邮箱：</td>
								<td>${dev.email }</td>
							</tr>
							<tr>
								<td>联系人姓名：</td>
								<td class="realName"><input type="text" name="realName"
									id="realName" value="${dev.realName }"
									reg="^(?=.*?\S)[\s\S]{0,20}$" tip="联系人不能为空" maxlength="20" />*
									<font color="red">${errMsg_devRealName}</font></td>
							</tr>
							<tr>
								<td>手机：</td>
								<td class="mobilePhone"><input type="text"
									name="mobilePhone" id="mobilePhone" value="${dev.mobilePhone }"
									reg="^0{0,1}(13[0-9]|15[0-9]|18[0-9]|147)[0-9]{8}$"
									tip="手机号码格式不正确" maxlength="11" />* <font color="red">${errMsg_devMobilePhone}</font>
								</td>
							</tr>
							<tr>
								<td>QQ：</td>
								<td class="qq"><input type="text" name="qq" id="qq"
									value="${dev.qq}" reg="[1-9][0-9]{4,}" tip="请输入QQ号 如:10000"
									maxlength="20" />* <font color="red">${errMsg_devQQ}</font></td>
							</tr>
							<tr>
								<td>通信地址：</td>
								<td><input type="text" name="mailingAddress"
									value="${dev.mailingAddress }" maxlength="50" /></td>
							</tr>
							<tr>
								<td>邮编：</td>
								<td class="postCode"><input type="text" name="postCode"
									id="postCode" value="${dev.postCode }" reg="^[0-9]{0,6}$"
									tip="邮编只能为数字" maxlength="6" /></td>
							</tr>
						</table>
						<div>
							<input type="submit" value="保存" />
						</div>
					</form>
				</c:if>
				<!--个人end-->
				<!--公司-->
				<c:if test="${dev.type==2}">
					<form name="updateDevManager" action="manage!updateDevManager.do"
						method="post" class="company">
						<input type="hidden" name="id" value="${dev.id }"/>
						<input type="hidden" name="type" value="${dev.type}"/>
						<table>
							<tr>
								<td>账号邮箱：</td>
								<td>${dev.email }</td>
							</tr>
							<tr>
								<td>公司名称：</td>
								<td class="companyName"><input type="text"
									name="companyName" id="companyName"
									reg="^(?=.*?\S)[\s\S]{4,50}$" tip="公司名称为4-50位字符"
									value="${dev.companyName }" maxlength="50" />* <font
									color="red">${errMsg_devCompanyName}</font></td>
							</tr>
							<tr>
								<td>详细地址：</td>
								<td class="companyAddress"><input type="text"
									name="companyAddress" id="companyAddress"
									reg="^(?=.*?\S)[\s\S]{5,50}$" tip="详细地址为5-50位字符"
									value="${dev.companyAddress }" maxlength="50" />* <font
									color="red">${errMsg_devCompanyAddress}</font></td>
							</tr>
							<tr>
								<td>通信地址：</td>
								<td class="mailingAddress"><input type="text"
									name="mailingAddress" id="mailingAddress"
									reg="^(?=.*?\S)[\s\S]{5,50}$" tip="通信地址为5-50位字符"
									value="${dev.mailingAddress }" maxlength="50" />* <font
									color="red">${errMsg_devMailingAddress}</font></td>
							</tr>
							<tr>
								<td>公司网址：</td>
								<td class="websiteUrl"><input type="text" name="websiteUrl"
									id="websiteUrl" reg="^(?=.*?\S)[\s\S]{0,100}$" tip="公司网址不能为空"
									value="${dev.websiteUrl }" maxlength="100" />* <font
									color="red">${errMsg_devWebsiteUrl}</font></td>
							</tr>
							<tr>
								<td>邮编：</td>
								<td class="postCode"><input type="text" name="postCode"
									id="postCode" reg="^[0-9]{3,6}$" tip="邮编为3-6位数字"
									value="${dev.postCode }" maxlength="6" />* <font color="red">${errMsg_devPostCode}</font>
								</td>
							</tr>
							<tr>
								<td>联系人姓名：</td>
								<td class="realName"><input type="text" name="realName"
									id="realName" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="联系人不能为空"
									value="${dev.realName }" maxlength="20" />* <font color="red">${errMsg_devRealName}</font>
								</td>
							</tr>
							<tr>
								<td>手机：</td>
								<td class="mobilePhone"><input type="text"
									name="mobilePhone" id="mobilePhone"
									reg="^0{0,1}(13[0-9]|15[0-9]|18[0-9]|147)[0-9]{8}$"
									tip="手机号码格式不正确" value="${dev.mobilePhone }" maxlength="11" />*
									<font color="red">${errMsg_devMobilePhone}</font></td>
							</tr>
							<tr>
								<td>QQ：</td>
								<td class="qq"><input type="text" name="qq" id="qq"
									reg="[1-9][0-9]{4,}" tip="请输入QQ号 如:10000" value="${dev.qq }"
									maxlength="20" />* <font color="red">${errMsg_devQQ}</font></td>
							</tr>
						</table>
						<div>
							<input type="submit" value="保存" />
						</div>
					</form>
				</c:if>
				<!-- 公司end -->
			</div>
			</div>
			<span class="clear_span"></span>
		</div>
	</div>
</body>
</html>