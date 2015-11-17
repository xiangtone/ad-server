<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>运营管理后台渠道列表</title>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		
	</head>
	<body>
		<div class="head_bj head_bj_bt">
			<div class="header rel admin_head">
				<div class="clearfix land_top">
					<div class="land_nav fl">
						数据统计&nbsp;&gt;&nbsp;渠道管理
					</div>
				</div>
			</div>
		</div>
		<div class="main">

			<div class="content clearfix">
				<jsp:include page="../../../jsp/manage/common/manage_left.jsp" />
				<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px" style="display: block;float: left;width: 100%;">
						<div style="display: block;float: left;">
							<a href="manage!channelsManage.do">渠道管理</a>&gt; 渠道列表
						</div>
						<div style="float:left; margin-left: 300px;">
							<a href="manage!editChannelNotice.do">添加渠道</a>
						</div>
					</div>
					${pageInfo }
					<table cellpadding="5" cellspacing="1" class="table_bod1 font_stat">
						<tr class="tr_td">
							<td width="80px">渠道号</td>
							<td width="120px">渠道名称</td>
							<td width="75px">分成比例</td>
							<td width="320px">链接地址</td>
							<td width="520px">查询地址</td>
							<td width="100px">黑名单</td>
						</tr>
						<c:if test="${!empty newsNoticeList}">
							<c:forEach items="${newsNoticeList}" var="news" varStatus="status">
							<tr>
								<td>
									<a href="manage!editChannelNotice.do?id=${news.id}">${news.cp_id}</a>
								</td>
								<td>
									${news.cp_name}
								</td>
								
								<td>
								${news.assign_terrace} : ${news.assign_channel}
								</td>
								
								<td>
								${news.cp_url}
								</td>
								<td>
								<a href="http://www.adwalker.com/channelLogin.do?cp_key=${news.cp_key}"><font color="blue">www.adwalker.com/channelLogin.do?cp_key=${news.cp_key}</font></a>
								</td>
								<td>
								${news.black_list}
								</td>
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty newsNoticeList}">
							暂无记录！
						</c:if>
					</table>
					${pageInfo }
				</div>
				</div>
			</div>
		</div>
	</body>
</html>
