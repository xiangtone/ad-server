<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台渠道修改</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
		<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
</head>
	<body>
		<div class="head_bj head_bj_bt">
			<div class="header rel admin_head">
				<div class="clearfix land_top">
					<div class="land_nav fl">
						数据统计&nbsp;>&nbsp;渠道管理
					</div>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="content clearfix">
				<jsp:include page="../../../jsp/manage/common/manage_left.jsp" />
					<div class="admin_right">
					<div class="content_right content_new">
					<div class="bord_bom1px">
						<a href="manage!channelsManage.do">渠道管理</a>>修改渠道
					</div>
					
					<div>
						<font color=#ff0000 size=5>${message}</font>
					</div>
					<form action="manage!saveChannelInfo.do" method="post">
						<input type="hidden" name="id" value="${newsNotice.id }">
					<table>
						<tr>
							<td>
								渠道号:
							</td>
							<td>
								<input  value="${newsNotice.cp_id }" type="text"  style="width: 350px"  maxlength="40" disabled="disabled"/>
								<input type="hidden" name="cp_id" value="${newsNotice.cp_id }">
							</td>	
						</tr>
						<tr>
							<td>
								渠道名称：
							</td>
							<td class="title">
								<input name="cp_name" value="${newsNotice.cp_name }" type="text" id="cp_name" style="width: 350px"  maxlength="40"/>
							</td>
						</tr>
						<tr>
							<td valign="top">
								分成比例：
							</td>
							<td class="assign_terrace">
								平台：
								<input name="assign_terrace" value="${newsNotice.assign_terrace}" type="text" 
								id="assign_terrace" style="width: 120px" maxlength="4" reg="^\d+(\.\d{0,2})?$" tip="分成比例只能填写数字，保留俩位小数"/>
							</td>
						</tr>
						<tr>
							<td valign="top">
							</td>
							<td class="assign_channel">
								渠道：
								<input name="assign_channel" value="${newsNotice.assign_channel}" type="text" 
								id="assign_channel" style="width: 120px" maxlength="4" reg="^\d+(\.\d{0,2})?$" tip="分成比例只能填写数字，保留俩位小数"/>
							</td>
						</tr>
						
						<tr>
							<td valign="top">
								链接地址：
							</td>
							<td>
								<input name="cp_url" value="${newsNotice.cp_url}" type="text" id="cp_url" style="width: 350px"  maxlength="40"/>
							</td>
						</tr>
						<tr>
							<td valign="top">
								黑名单：
							</td>
							<td class="black_list">
								<input name="black_list" value="${newsNotice.black_list}" type="text" id="black_list" style="width: 350px"   />
								填写多个广告id时，以英文','隔开!
							</td>
						</tr>
						
					</table>
					<div>
						<input type="submit" value="保存" />
					</div>
					</form>
					
</div>
<span class="clear_span" style="height: 250px;"></span>
</div>
</div>

</div>
</body>
</html>
