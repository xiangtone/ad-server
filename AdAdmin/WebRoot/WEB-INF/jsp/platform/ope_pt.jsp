<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="main">
		<div class="content clearfix">
				<div class="admin_right">
					<div style="display: block;float: left;margin: 0px;">
						<div class="content_right content_new">
							<div class="bord_bom1px">平台设置</div>
								<form id="updateConfigEScore" action="manage!updateConfigEScore.do" method="post">
								<input type="hidden" name="id" value="${configEScore.id == null?0:configEScore.id }" /> <!-- id为0代表是要插入 -->
								<table>
									<tr>
										<td colspan="2">
											<font color="red">*注：以下设置一旦修改后将实时生效</font>
										</td>
									</tr>
								</table>
								<!--修改-->
								<table>
									<tr>
										<td>开发者申请提现的最小值：</td>
										<td class="devApplyMinMoney"><input name="devApplyMinMoney" class="w_input" type="text" value="${configEScore.devApplyMinMoney }" reg="^[0-9]$|^[0-9]+(.[0-9]{0,2})$" tip="请填写整数或者小数，小数最多保留两位!" />元</td>
									</tr>
									<tr>
										<td>开发者每次申请提现的最小提款值：</td>
										<td class="dev_min_draw_money "><input name="dev_min_draw_money" class="w_input" type="text" value="${configEScore.dev_min_draw_money }" reg="^[0-9]$|^[0-9]+(.[0-9]{0,2})$" tip="请填写整数或者小数，小数最多保留两位!" />元</td>
									</tr>
								</table>
							<!--修改end-->
							<div>
								<input name="submit" id="submit" type="submit" value="保存" />
							</div>
						</form>
					</div>
				</div>
			<span class="clear_span" style="height: 50px;"></span>
		</div>
	</div>
	</div>
</body>
</html>
