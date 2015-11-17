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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本资料</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>财务资料</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_3"><span>应用列表</span></a></li>
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<!--个人-->
					<c:if test="${dev.type!=2}">
						<input type="hidden" name="id" value="${dev.id }" />
						<input type="hidden" name="type" value="${dev.type}" />
						<table>
							<tr>
								<td>账号邮箱：</td>
								<td>${dev.email}</td>
							</tr>
							<tr>
								<td>联系人姓名：</td>
								<td>${dev.realName}</td>
							</tr>
							<tr>
								<td>手机：</td>
								<td>${dev.mobilePhone}</td>
							</tr>
							<tr>
								<td>QQ：</td>
								<td>${dev.qq}</td>
							</tr>
							<tr>
								<td>通信地址：</td>
								<td>${dev.mailingAddress}</td>
							</tr>
							<tr>
								<td>邮编：</td>
								<td>${dev.postCode}</td>
							</tr>
						</table>
				</c:if>
				<!--个人end-->
				<!--公司-->
				<c:if test="${dev.type==2}">
						<table>
							<tr>
								<td>账号邮箱：</td>
								<td>${dev.email}</td>
							</tr>
							<tr>
								<td>公司名称：</td>
								<td>${dev.companyName}</td>
							</tr>
							<tr>
								<td>详细地址：</td>
								<td>${dev.companyAddress}</td>
							</tr>
							<tr>
								<td>通信地址：</td>
								<td>${dev.mailingAddress}</td>
							</tr>
							<tr>
								<td>公司网址：</td>
								<td>${dev.websiteUrl}</td>
							</tr>
							<tr>
								<td>邮编：</td>
								<td>${dev.postCode}</td>
							</tr>
							<tr>
								<td>联系人姓名：</td>
								<td>${dev.realName}</td>
							</tr>
							<tr>
								<td>手机：</td>
								<td>${dev.mobilePhone}</td>
							</tr>
							<tr>
								<td>QQ：</td>
								<td>${dev.qq}</td>
							</tr>
						</table>
				</c:if>
				<!-- 公司end -->
			</div>
			<div class="subblock_2" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
				<iframe frameborder="0" height="720" width="940" src="manage!detailFnancialInfo.do?dev_id=${dev_id}"></iframe>
			</div>
			<div class="subblock_3" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
				<iframe frameborder="0" height="720" width="940" src="manage!editDev.do?dev_id=${dev_id}"></iframe>
			</div>
			</div>
			<span class="clear_span"></span>
		</div>
	</div>
</body>
</html>