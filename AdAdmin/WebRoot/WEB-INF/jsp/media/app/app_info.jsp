<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${business_title}</title>
<link type="text/css" rel="stylesheet" id="cssfile"	href="<%=request.getContextPath()%>/css/landscape.css?v=${version}" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
</head>
<body>
	<div id="content" style="padding: 0; height: 490px;">
		<div class="main_right">
			<table width="950" style="border: 0;">
				<tr>
					<td>
						<table width="950" border="0">
							<tr>
								<td width="75" bgcolor="#cfe1e2" align="right">开发者：</td>
								<td width="220" align="left">${app.devEmail}</td>
								<td width="79" bgcolor="#cfe1e2" align="right">联系人：</td>
								<td width="212" align="left">${app.devName}</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">应用名称：</td>
								<td align="left">
									${app.name}
								</td>
								<td bgcolor="#cfe1e2" align="right">应用状态：</td>
								<td align="left">
									${appStatus}
									<c:if test="${app.checkMsg ne null}">
  										&nbsp;&nbsp;驳回理由：${app.checkMsg}
  									</c:if>
  								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">提交时间：</td>
								<td align="left"><fmt:formatDate value="${app.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td bgcolor="#cfe1e2" align="right">上线时间：</td>
								<td align="left">
									<fmt:formatDate value="${app.releaseTime}" type="date" dateStyle="medium" />
									<c:if test="${app.releaseTime == null}">
										--
									</c:if>
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">开发者ID：</td>
								<td align="left">
									${dev.id}
								</td>
								<td bgcolor="#cfe1e2" align="right">应用平台：</td>
								<td align="left">
									${app.os}
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">应用ID：</td>
								<td align="left">${app.id}</td>
								<td bgcolor="#cfe1e2" align="right">应用KEY:</td>
								<td align="left">
									${app.appkey}
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">应用程序：</td>
								<td colspan="3" align="left">
									<c:if test="${fileUrl ne null && fileUrl ne ''}">
										<a href="${resource_url_default}${fileUrl}" target="_blank">${devAppVo.fileName}&nbsp;</a>
										<a href="${resource_url_default_other}${fileUrl}" target="_blank">下载地址2</a>
									</c:if>
									<c:if test="${fileUrl eq null || fileUrl eq ''}">
										${app.name }&nbsp;(未上传应用)
									</c:if>
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">关键字：</td>
								<td  align="left">
									${app.keyword}
								</td>
								<td bgcolor="#cfe1e2" align="right">媒体负责人：</td>
								<td align="left">
									${app.app_manager}
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">广告形式：</td>
								<td  align="left">
									${page_type}
								</td>
								<td bgcolor="#cfe1e2" align="right">虚拟货币：</td>
								<td align="left">
									${appCurrency.exchange_rate_rmb}(${appCurrency.virtual_currency_name})
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">应用类别：</td>
								<td colspan="3" align="left">
									<c:forEach items="${ecList}" var="entry">
										<c:if test="${app.categoryId eq entry.id}">${entry.fname}-${entry.name}</c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">是否单独投放</td>
								<td colspan="3" align="left">
									 <c:if test="${app.placement eq app.id}">是</c:if>
							    	<c:if test="${app.placement eq 0}">否</c:if>
								</td>
							</tr>
							
							<tr>
								<td bgcolor="#cfe1e2" align="right">详细介绍：</td>
								<td colspan="3" align="left">
									<textarea rows="5" cols="118" readonly="readonly">${app.longDesc}</textarea>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>