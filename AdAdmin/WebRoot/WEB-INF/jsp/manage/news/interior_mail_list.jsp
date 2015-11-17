<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台平台管理站内信列表</title>
		<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript">
			function editNews(id){
				var url = "manage!editInteriorMail.do?id="+id;
				window.location.href=url;
			}
		</script>
	</head>

	<body>
		<div class="main">
			<div class="content clearfix">
				<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px" style="display: block;float: left;width: 100%;">
						<div style="display: block;float: left;">
							<a href="manage!interiorMailList.do">新闻管理</a>&gt;站内信列表
						</div>
						<div style="float: left;margin-left: 300px;">
							<a href="manage!editInteriorMail.do">发布站内信</a>
						</div>
					</div>
					<table cellpadding="5" cellspacing="1" class="table_bod1 font_stat" width="100%">
						<tr class="tr_td">
							<td>标题</td>
							<td>日期</td>
							<td>发布的开发者id</td>
							<td>状态</td>
							<td>操作</td>
						</tr>
						<c:if test="${!empty interiorMail}">
							<c:forEach items="${interiorMail}" var="news" varStatus="status">
							<tr>
								<td>
									${news.title}
								</td>
								<td>
								<c:if test="${news.update_time!=null }"><fmt:formatDate value="${news.update_time }" pattern="yyyy-MM-dd " /></c:if>
								</td>
								<td>
									${news.dev_id_str}
								</td>
								<td>
									<c:if test="${news.status==1 }">
									草稿
									</c:if>
									<c:if test="${news.status==2 }">
									发布
									</c:if>
								</td>
								<td align="center">
									<input name="deleteNews" type="button" value="修改" onclick="editNews(${news.id })" <c:if test="${news.status!=1}"> disabled="disabled"</c:if>></input>
								</td>
							</tr>
							</c:forEach>
						</c:if>
									<tr>
										<td align="center" colspan="18" style="text-align: center;">暂无数据！</td>
									</tr>	
					</table>
					${pageInfo.pageInfoStr}
				</div>
			</div>
			</div>
		</div>
	</body>
</html>