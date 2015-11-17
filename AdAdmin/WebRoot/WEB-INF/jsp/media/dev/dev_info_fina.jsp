<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者账号管理-概况</title>
<link href="css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreFinanceCategory.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/onurl.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getCategoryById('${bankInfo.city_id}');//获取默认分类
		//alert(getCategoryById("getCategoryById('${bankInfo.city_id}"+${bankInfo.city_id});
	});
</script>
<script type="text/javascript">
	function format() {
		document.getElementById("bankAccount").onkeyup = function() {
			this.value = this.value.replace(/\s/g, '').replace(
					/(\d{4})(?=\d)/g, "$1 ");
		};
	};
	function testFile() {
		var value = document.getElementById("cardFile").value;
		var patrn = new RegExp(".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$\s");
		if (!patrn.test(value)) {
			document.getElementById("name_error").style.visibility = 'visible';
			document.getElementById("submit").disabled = true;
		} else {
			document.getElementById("name_error").style.visibility = 'hidden';
			document.getElementById("submit").disabled = false;
		}
	}
</script>
</head>

<body>

	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<table class="margtop10">
					<tr>
						<td>证件类型：</td>
						<td><c:if test="${bankInfo.cardType==1}">
									身份证
								</c:if> <c:if test="${bankInfo.cardType==2}">
									营业执照
								</c:if></td>
					</tr>
					<tr>
						<td>证件号：</td>
						<td>${bankInfo.cardCode}</td>
					</tr>
					<tr>
						<td>上传证件：</td>
						<td class="cardFile"><c:if test="${bankInfo.cardUrl ne null && bankInfo.cardUrl ne ''}">
								<input type="file" name="cardFile" id="cardFile" class="img" onchange="testFile()" />*(已上传)
		</c:if> <c:if test="${bankInfo.cardUrl eq null || bankInfo.cardUrl eq ''}">
								<input type="file" name="cardFile" id="cardFile" class="img" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$\s" tip="允许png,gif,jpg,jpeg文件" />*
		<span class="tip_img"></span>
			(未上传)
		</c:if></td>
					</tr>
					<tr>
						<td>开户人姓名：</td>
						<td class="userName">${bankInfo.account_hoder}</td>
					</tr>
					<tr>
						<td>开户行城市:</td>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" id="id-form">
								<tr>
									<td><select class="styledselect-st4" name="type" id="type" reg="[^0]">
											<option value="0">省份</option>${provinceCity }
											<c:forEach items="${provinceCitySort}" var="app">
												<option value="${app.id}" <c:if test="${provinceCity.parent_Id eq app.id}">selected="selected"</c:if>>${app.province_City}</option>
											</c:forEach>
									</select></td>
									<td><select class="styledselect-st5" name="platform" id="platform" reg="[^0]">
											<option value="0">城市</option>
											<c:forEach items="${provinceCitySortList}" var="app">
												<option value="${app.id}" <c:if test="${provinceCity.id eq app.id}">selected="selected"</c:if>>${app.province_City}</option>
											</c:forEach>
									</select></td>
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<td>银行名称：</td>
						<td>${bankInfo.bankName}</td>
					</tr>
					<tr>
						<td>开户行名称：</td>
						<td>${bankInfo.bankSubbranch}</td>
					</tr>
					<tr>
						<td>银行账号：</td>
						<td>${bankInfo.bankAccount}</td>
					</tr>
				</table>
				</form>
			</div>
			<span class="clear_span"></span>
		</div>
	</div>
</body>
</html>