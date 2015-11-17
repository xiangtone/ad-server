<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台平台管理新闻列表</title>
		<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
		<script type="text/javascript">
		function send(actId) {
			var url = "manage!editMailsNotice.do";
			new $.dialog({
				title:'群发邮件',
				page:url,
				width:735,
				height:570,
				drag:true,
				resize:true,
				cover:true,
				maxBtn:false}).ShowDialog();
		}
		
		
		</script>
	</head>
	<body>
		<div class="main">
			<div class="content clearfix">
				<div class="content_right admin_right">
					<fieldset class="search_fieldset">
				<legend>邮件管理</legend>
				<!--新增-->
				<form action="manage!mailList.do" method="post"	id="my_form">
					
				</form>
				</fieldset>
				<div style="">
					<a href="javascript:void(0);" onclick="send();">群发邮件</a>
				</div>
					
						<!--  
						<div style="display: block;float: left;">
							<a href="manage!sendMails.do">邮件管理</a>&gt;群发邮件列表
						</div>
						
						-->
					</div>
					<div class="main_table">
					<table cellpadding="5" cellspacing="1" id="tb" class="table_bod1 font_stat" width="100%">
						<tr class="tr_td">
							<th width="700px">标题</th>
							<th width="170px">日期</th>
						</tr>
						<c:if test="${!empty newsNoticeList}">
							<c:forEach items="${newsNoticeList}" var="news" varStatus="status">
							<tr>
								<td>
									<a href="manage!detailMailsNoticeManage.do?id=${news.id}">【${news.showType}】${news.title}</a>
								</td>
								<td>
									
									<fmt:formatDate value="${news.update_time }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty newsNoticeList}">
							<tr>
								<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
							</tr>
						</c:if>
					</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
	</body>
</html>