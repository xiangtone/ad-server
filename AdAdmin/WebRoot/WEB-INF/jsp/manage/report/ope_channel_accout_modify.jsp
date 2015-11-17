<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台渠道修改</title>
<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
		<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/style/main.css" rel="stylesheet" type="text/css" />
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
				<div class="content_right admin_right">
					<div class="bord_bom1px">
						渠道管理>修改渠道
					</div>
					
					<div>
						<font color=#ff0000 size=5>${message}</font>
					</div>
					<form action="manage!updateChannelAccoutInfo.do" method="post">
						<input type="hidden" name="id" value="${newsNoticeemail.id }">
					<table>
						<tr>
							<td>
								用户名:
							</td>							
							<td>
								<input value="${newsNoticeemail.email }"  type="text" id="email" style="width: 350px"  maxlength="31" disabled="disabled"/>
							    <input type="hidden" name="email" id="email" value="${newsNoticeemail.email }">
							</td>	
						</tr>
						<tr>
							<td>
								密码:
							</td>
							<td>
								<input name="password" value="${newsNoticeemail.password }"  type="password" id="password" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
					<tr>
							<td>
								确认密码:
							</td>
							<td>
								<input name="password1" value="${newsNoticeemail.password }"  type="password" id="password1" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
						<tr>
							<td>
								渠道名:
							</td>
							<td>
								<input name="channel_name" value="${newsNoticeemail.channel_name }"  type="text" id="channel_name" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
						<tr>
							<td>
								渠道号:
							</td>
							<td>
								<input name="channel_id" value="${newsNoticeemail.channel_id }"  type="text" id="channel_id" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
						<tr>
							<td>
								联系人:
							</td>
							<td>
								<input name="real_name" value="${newsNoticeemail.real_name }"  type="text" id="real_name" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
						<tr>
							<td>
								电话:
							</td>
							<td>
								<input name="phone" value="${newsNoticeemail.phone }"  type="text" id="phone" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
						<tr>
							<td>
								分成比例:
							</td>
							<td>
								<input name="into_Rate" value="${newsNoticeemail.into_Rate }"  type="text" id="into_Rate" style="width: 350px"  maxlength="31"/>
							</td>	
						</tr>
					</table>
					<div>
						<input type="submit" value="保存" />
					</div>
					</form>
</div>
<span class="clear_span"></span>
</div>
</div>
</body>
</html>
