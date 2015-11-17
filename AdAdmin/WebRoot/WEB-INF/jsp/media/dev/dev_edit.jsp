<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者账号管理基本资料-应用列表</title>
<link href="css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript">
		function applyDevAppManager(status,appId){
			var url = "manage!auditDevApp.do?status="+status+"&appId="+appId;
			if(confirm("确认要执行该操作吗？")){
				if(status=="NOTPASS"){
					var reason = window.prompt("请填写驳回理由", "");
					if(reason){	
					reason = encodeURIComponent(reason);			
					var url =url+"&reason="+reason
						//window.location.href=encodeURI(url);转码
						jump(encodeURI(url));
					}else{
						return;
					}
				}else{
					//window.location.href=url;//不是驳回直接跳转
					jump(url);
				}
			}//取消操作
		}
		function jump(url){
			var a = document.createElement("a");
			if(!a.click) {
	     		 window.location = url;
	     		 return;
	   		 }
	   		a.setAttribute("href", url);
	   		a.style.display = "none";
	   		document.body.appendChild(a);
	   		a.click();
		}
		
		function chekForm(){
			document.form.submit(); 
		}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<form action="manage!updateCreditLimit.do" name="form">
						<input type="hidden" name="dev_id" value="${dev_id}" />
						<table border="0">
							<tr>
								<td>应用总个数:${appCount}个</td>
								<td>账户余额:<escore:formatMoney value="${dev.finance_income}" maxFractionDigits="2" /></td>
							</tr>
						</table>
						<table width="100%" cellpadding="0" cellspacing="1"
							class="table_bod1 font_stat">
							<tr class="tr_td">
								<td>应用ID</td>
								<td>应用名称</td>
								<td>提交时间</td>
								<td>上线时间</td>
								<td>应用状态</td>
							</tr>
							<c:if test="${!empty appList}">
								<c:forEach items="${appList}" var="app" varStatus="status">
									<tr>
										<td align="center">${app.id }</td>
										<td align="center">
											${app.name}
										</td>
										<td align="center">
											<fmt:formatDate value="${app.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td align="center">
											<fmt:formatDate	value="${app.releaseTime }" pattern="yyyy-MM-dd HH:mm:ss" />
											<c:if test="${app.releaseTime == null}">
												--
											</c:if>
										</td>
										<td align="center">${app.statusName}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty appList}">
								<tr>
									<td align="center" colspan="6" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
						<input type="hidden" name="dev_id" value="${dev_id}" />
						${pageInfo.pageInfoStr}
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>