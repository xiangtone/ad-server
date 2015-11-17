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
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#jifen_d1").toggle();
		$("#jifen_d2").toggle();
		$("#jifen_d3").toggle();
		$("#pageType_0").click(function() {
			$("#jifen_d1").toggle();
			$("#jifen_d2").toggle();
			$("#jifen_d3").toggle();
		});
		checkType('0');
	});
	function checkType(id){
		if(id==0){
			$("#jifen_d1").toggle();
			$("#jifen_d2").toggle();
			$("#jifen_d3").toggle();
		}
		$("#pageType_"+id).attr("checked","checked");
	}
	function form_submit(){
		if(vaildateForm("addAppManage")){
			$("#addAppManage").ajaxSubmit(function(data){
				if(data=="0") {
					alert("增加成功！！");
					window.parent.refresh();
				} else {
					alert("增加失败！！");
				}
			});
		};
	}
</script>
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<table class="mar_bom10 margtop10">
					<tr>
						<td>开发者：</td>
						<td>${dev.email }</td>
						<td>联系人：</td>
						<td>${dev.realName }</td>
					</tr>
				</table>
				<form name="addAppManage" action="manage!saveApp.do" id="addAppManage" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${app.id}" /> <input type="hidden" name="devEmail" value="${app.devEmail}" /> <input type="hidden" name="developerId" value="${dev.id}" />
					<div></div>
					<table>
						<tr>
							<td width="100" valign="top">系统类型：</td>
							<td><input name="os" type="radio" value="android" checked="checked" />ANDROID <input id="select_os" name="os" type="radio" value="ios" />IOS</td>
						</tr>
						<tr>
							<td width="100" valign="top">应用名称：</td>
							<td class="name"><input name="name" id="name" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="应用名不能为空" type="text" value="${app.name }" maxlength="20" />* <font color="red">${errMsg_appName}</font></td>
						</tr>
						<tr>
							<td valign="top"></td>
							<td>20个字符以内</td>
						</tr>
						<tr>
							<td valign="top">关键字：</td>
							<td class="keyword"><input name="keyword" id="keyword" type="text" value="${app.keyword }" reg="^[a-zA-Z0-9\u4e00-\u9fa5\,]{0,50}$" tip="请输入不超过50位的字符 多个关键字用英文逗号分开" maxlength="50" /></td>
						</tr>
						<tr>
							<td valign="top"></td>
							<td>多个关键字请用空格分隔</td>
						</tr>
						<!-- 分类start -->
						<tr>
							<td valign="top">应用类别：</td>
							<td>
								<select name="categoryId" id="type" reg="[^0]">
									<c:forEach items="${ecList}" var="entry">
										<option value="${entry.id}" <c:if test="${advertisement.categoryId eq entry.id}"> selected="selected" </c:if>>${entry.fname}-${entry.name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td valign="top">应用类别：</td>
							<td><input id="pageType_0" type="checkbox" name="types" value="0" />积分墙 <input id="pageType_1" type="checkbox" name="types" value="1" />推荐墙 <input id="pageType_4" type="checkbox" name="types" value="4" />BANNER <input id="pageType_5" type="checkbox" name="types" value="5" />插屏</td>
						</tr>
						<!-- 分类end -->
						<tr id="jifen_d1">
							<td valign="top">虚拟货币单位：</td>
							<td valign="top"><input id="virtual_currency_name" name="virtual_currency_name" type="text" class="form-control" value="${vo.virtual_currency_name}"/></td>
						</div>
						<tr id="jifen_d2">
							<td valign="top">汇率设置：</td>
							<td valign="top"><input id="exchange_rate_rmb" name="exchange_rate_rmb" type="text" class="form-control" value="${vo.exchange_rate_rmb}"/></td>
						</div>
						<tr id="jifen_d3">
							<td valign="top">积分回调URL：</td>
							<td valign="top"><input id="response_url" name="response_url" type="text" class="form-control" value="${vo.response_url}"/></td>
						</div>
						<tr>
							<td valign="top">详细介绍：</td>
							<td class="longDesc"><textarea name="longDesc" id="longDesc" reg="^(?=.*?\S)[\s\S]{0,500}$" tip="详细描述在1至500个字符" cols="50" rows="5" maxlength="100">${app.longDesc }</textarea>* <font color="red">${errMsg_appLongDesc}</font></td>
						</tr>
						<tr>
						</tr>
						<tr>
							<td valign="top"></td>
							<td><input name="button" type="button" class="addAppSubmit" onclick="form_submit()" value="保存" /></td>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>
</body>
</html>