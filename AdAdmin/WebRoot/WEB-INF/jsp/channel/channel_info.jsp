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
<title>运营管理后台渠道信息查看</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
			<ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>财务信息</span></a></li>
    			</ul>
					<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
					<tr>
							<td align="right">渠道邮箱：</td>
							<td>
								${vo.email}
							</td>
						</tr>
						<tr>
							<td align="right">渠道名称：</td>
							<td>
								${vo.channel_name}
							</td>
						</tr>
						<tr>
							<td align="right">渠道联系人:</td>
							<td>${vo.real_name}
							</td>
						</tr>
						<tr>
							<td align="right">手机号码:</td>
							<td>${vo.phone}</td>
						</tr>
						<tr>
							<td align="right">QQ号:</td>
							<td>${vo.qq}</td>
						</tr>
						<tr>
							<td align="right">渠道负责人:</td>
							<td>${vo.channe_manager}</td>
						</tr>
						<tr>
							<td align="right">渠道级别:</td>
							<td>${vo.level}</td>
						</tr>
						<tr>
							<td align="right">合作方式：</td>
							<td>
								<c:choose>
									<c:when test="${vo.channe_mode == '0'}">
										代理											
									</c:when>
									<c:when test="${vo.channe_mode == '2'}">
										API
									</c:when>
									<c:otherwise>
										${vo.marking}
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						</table>
						</div>
						<div class="subblock_2" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td align="right">公司名称:</td>
							<td>${vo.account_hoder}</td>
						</tr>
						<tr>
							<td align="right">银行账号:</td>
							<td>${vo.bank_account}</td>
						</tr>
						<tr>
							<td align="right">开户行:</td>
							<td>${vo.bank_subbranch}</td>
						</tr>
						<tr>
							<td align="right">发票要求：</td>
							<td>
								<c:choose>
									<c:when test="${vo.invoice_require == '0'}">
										增值税专用发票											
									</c:when>
									<c:when test="${vo.invoice_require == '1'}">
										增值税普通发票
									</c:when>
									<c:otherwise>
									${vo.invoice_require_others}
										
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					</div>
			</div>
		</div>
	</div>
</body>
</html>
