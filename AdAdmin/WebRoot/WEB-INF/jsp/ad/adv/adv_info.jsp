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
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">基本信息</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>财务信息</span></a></li>
    			</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td width="23%" height="30" align="right"><b>* </b>广告主账号:</td>
							<td width="77%" align="left" class="email">${findAdvVo.email}</td>
						</tr>
						<tr>
							<td align="right">公司名称：</td>
							<td class="companyName" align="left">${findAdvVo.companyName}</td>
						</tr>
						<tr>
							<td align="right"><b>* </b>公司电话:</td>
							<td align="left" class="fixedPhone">${findAdvVo.fixedPhone}</td>
						</tr>
						<tr>
							<td align="right">详细地址：</td>
							<td class="companyAddress" align="left">
								${findAdvVo.companyAddress}</td>
						</tr>


						<tr>
							<td align="right"><b>* </b>联系人:</td>
							<td class="realName" align="left">${findAdvVo.realName}</td>
						</tr>
						<tr>
							<td align="right">所属区域:</td>
							<td><c:choose>
									<c:when test="${findAdvVo.area_type == '4'}">
									华南
								</c:when>
									<c:when test="${findAdvVo.area_type == '1'}">
									华东
								</c:when>
									<c:when test="${findAdvVo.area_type == '2'}">
									华北
								</c:when>
									<c:otherwise>
									平台
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><b>* </b>手机号码:</td>
							<td align="left" class="mobilePhone">${findAdvVo.mobilePhone}</td>
						</tr>
						<tr>
							<td align="right"><b>* </b>QQ:</td>
							<td class="qq" align="left">${findAdvVo.qq}</td>
						</tr>
						<tr>
							<td align="right">邮编:</td>
							<td class="post_code" align="left">${findAdvVo.postCode}</td>
						</tr>
						<tr>
							<td align="right">信用额度:</td>
							<td class="creditQuota" align="left">${findAdvVo.creditQuota}</td>
						</tr>
					</table>
					</div>
					<div class="subblock_2" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td>银行账号:</td>
							<td>${findAdvVo.bank_account}</td>
						</tr>
						<tr>
							<td>开户行:</td>
							<td>${findAdvVo.bank_subbranch}</td>
						</tr>
						<tr>
							<td>公司注册地址</td>
							<td>${findAdvVo.company_regist_address}</td>
						</tr>
						<tr>
							<td>纳税人识别号</td>
							<td>${findAdvVo.taxpayer_number}</td>
						</tr>
						<tr>
							<td>发票要求</td>
							<td><c:choose>
									<c:when test="${findAdvVo.invoice_require == '0' }">
				增值税专用发票				
			</c:when>
									<c:when test="${findAdvVo.invoice_require == '1' }">
			增值税普通发票
			</c:when>
									<c:otherwise>
										<option value="2" selected="selected">${findAdvVo.invoice_require_others}</option>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>营业执照</td>
							<td><img id="business_license"
								src="<escore:loadImg/>${findAdvVo.business_license}"
								width="200px" height="100px" /></td>
						</tr>
						<tr>
							<td>税务登记证</td>
							<td><img id="tax_reg_cer"
								src="<escore:loadImg/>${findAdvVo.tax_reg_cer}" width="200px"
								height="100px" /></td>
						</tr>
						<tr>
							<td>开户许可证</td>
							<td><img id="account_permit"
								src="<escore:loadImg/>${findAdvVo.account_permit}" width="200px"
								height="100px" /></td>
						</tr>
						<tr>
							<td>一般纳税人资质证明</td>
							<td><img id="taxpayer_certificate"
								src="<escore:loadImg/>${findAdvVo.taxpayer_certificate}"
								width="200px" height="100px" />
							</td>
						</tr>
					</table>
					</div>
					<div class="subblock_3" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						
		
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
