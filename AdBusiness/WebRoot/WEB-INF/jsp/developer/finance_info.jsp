<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<%@ taglib prefix="adwalker" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<!-- this page specific styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/personal-info.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/adwalker.category.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxupload.js"></script>
		<script>
			$(document).ready(function() {
				getPlatform('${bankInfo.bankCity}','${bankInfo.city_id}');//获取默认分类
			});
			$(document).ready(function() {
				$(".close").click(function() {
					$(this).parent().fadeTo(300, 0, function() { // Links with the class "close" will close parent
						$(this).slideUp(300);
					});
					return false;
				});
			});
			$(document).ready(function() {
				initUpload();
			});
			//文件上传控件
			function initUpload() {
				//icon
				new AjaxUpload('file_upload_opposite', {
					action : '${pageContext.request.contextPath}/uploadDevImg.action',
					name : 'card',
					data : {},
					onSubmit : function(file, extension) {
						extension = extension.toUpperCase();
						if (extension != "BMP" && extension != "PNG"
								&& extension != "GIF" && extension != "JPEG"
								&& extension != "JPG") {
							alert("只支持图片文件！");
							return false;
						}
					},
					onComplete : function(file, data) {
						if (data) {
							var dataObj = eval("(" + data + ")");//转换为json对象 
							if (dataObj.status == 1) {
								$("#card_url_opposite").val(dataObj.data);
								$("#opposite").html('<a class="txt" target="_blank"  href="<adwalker:loadImg/>/' + dataObj.data + '">查看</a>');
							} else if (dataObj.status = -1) {
								$("#error_tip").html(dataObj.error);
								$("#error_tip").css("display", "block");
							} else {
								alert("登录失败，请重试！！");
							}
						} else {
							alert("登录失败，请重试！！");
						}
					}
				});
				new AjaxUpload('file_upload', {
					action : '${pageContext.request.contextPath}/uploadDevImg.action',
					name : 'card',
					data : {},
					onSubmit : function(file, extension) {
						extension = extension.toUpperCase();
						if (extension != "BMP" && extension != "PNG"
								&& extension != "GIF" && extension != "JPEG"
								&& extension != "JPG") {
							alert("只支持图片文件！");
							return false;
						}
					},
					onComplete : function(file, data) {
						if (data) {
							var dataObj = eval("(" + data + ")");//转换为json对象 
							if (dataObj.status == 1) {
								$("#cardUrl").val(dataObj.data);
								$("#iconimg").html('<a class="txt" target="_blank"  href="<adwalker:loadImg/>/' + dataObj.data + '">查看</a>');
							} else if (dataObj.status = -1) {
								$("#error_tip").html(dataObj.error);
								$("#error_tip").css("display", "block");
							} else {
								alert("登录失败，请重试！！");
							}
						} else {
							alert("登录失败，请重试！！");
						}
					}
				});
			}
			function submitForm() {
				if (vaildateForm("myForm")) {
					$("#myForm").ajaxSubmit(function(data) {
						if (data) {
							var dataObj = eval("(" + data + ")");//转换为json对象 
							if (dataObj.status == 1) {
								alert("修改成功！！");
								window.location.href = 'toUpdateFnancialInfo.action';
							} else if (dataObj.status = -1) {
								$("#error_tip").html(dataObj.error);
								$("#error_tip").css("display", "block");
							} else {
								alert("登录失败，请重试！！");
							}
						} else {
							alert("登录失败，请重试！！");
						}
					});
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div class="settings-wrapper" id="pad-wrapper">
				<div class="row header">
					<h3>财务信息设置</h3>
				</div>
				<div class="table-products section">
					<div class="row filter-block">
						<div class="personal-info">
							<div class="alert alert-info">
								<i class="icon-lightbulb"></i>该银行账号信息即为您的收款账号信息，请务必真实、准确地填写。<br />*财务信息轻易不能变更，如需修改请联系客服，并按要求提供相应资料证明。每月15-20号为财务信息修改申请的受理时期。</strong>
							</div>
							<form class="form-horizontal" role="form" name="updateFnancialInfo" action="updateFnancialInfo.action" id="myForm" method="post">
								<input name="id" value="${bankInfo.id}" type="hidden" />
								<input name="cardUrl" id="cardUrl" value="${bankInfo.cardUrl}" type="hidden" />
								<input name="card_url_opposite" id="card_url_opposite" value="${bankInfo.card_url_opposite}" type="hidden" />
								<div class="form-group">
									<label class="col-lg-2 control-label">注册账号:</label>
									<div class="col-lg-8">
										<span>${dev.email}</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">开户人姓名:</label>
									<div class="accountHoder">
										<input class="form-control" type="text" value="${bankInfo.accountHoder}" name="accountHoder" id="accountHoder" type="text" reg="^[\u4e00-\u9fa5\w\W]{2,50}$" tip="请输入开户人姓名如：张三" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">证件类型:</label>
									<div class="col-lg-8">
										<select id="cardType" name="cardType" class="ui-select span5">
											<c:if test="${bankInfo.cardType==null || bankInfo.cardType==1||bankInfo.cardType==0}">
												<option value="1" selected="selected">身份证</option>
												<option value="2">公司号</option>
											</c:if>
											<c:if test="${bankInfo.cardType==2}">
												<option value="1">身份证</option>
												<option value="2" selected="selected">公司号</option>
											</c:if>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">证件号码:</label>
									<div class="cardCode">
										<input class="form-control" id="cardCode" name="cardCode" type="text" value="${bankInfo.cardCode}" reg="^[\w]{1,30}$" tip="证件号不得超过30位的英文、数字" maxlength="30" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">开户行地区：</label>
									<div class="city_id">
										<select class="ui-select span5" name="bankCity" id="type" reg="[^0]">
											<option value="0">省份</option>
											<c:forEach items="${provinceCitySort}" var="app">
												<option value="${app.id}" <c:if test="${bankInfo.bankCity==app.id}">selected</c:if>>${app.province_City}</option>
											</c:forEach>
										</select>
										<select class="ui-select span5" name="city_id" id="platform" reg="[^0]" tip="请选择城市">
											<option value="0">城市</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">开户行名称：</label>
									<div class="bankName">
										<input class="form-control" type="text" value="${bankInfo.bankName }" id="bankName" name="bankName" reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入4-50个字符的开户银行名称 如:中国工商银行" maxlength="50" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">开户支行名称：</label>
									<div class="bankSubbranch">
										<input class="form-control" type="text" value="${bankInfo.bankSubbranch}" id="bankSubbranch" name="bankSubbranch" reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入 如:中国工商银行海淀区人民大学支行" maxlength="50" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">银行账号:</label>
									<div class="bankAccount">
										<input class="form-control" type="text" value="${bankInfo.bankAccount}" id="bankAccount" name="bankAccount" reg="^[0-9\s]{14,24}$" tip="请输入银行卡号 如：0000 0000 0000 0000 000" maxlength="24" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">上传证件正面：</label>
									<div class="col-lg-8">
										<div style="display: inline;" id="iconimg">
											<c:if test="${bankInfo.cardUrl ne null && bankInfo.cardUrl ne ''}">
												<a class="txt" target="_blank" href="<adwalker:loadImg/>/${bankInfo.cardUrl}">查看</a>
											</c:if>
											<c:if test="${bankInfo.cardUrl eq null || bankInfo.cardUrl eq ''}">
													(未上传)
											</c:if>
										</div>
										<input type='button' class='btn' value='浏览...' id="file_upload" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">上传证件反面：</label>
									<div class="col-lg-8">
										<div style="display: inline;" id="opposite">
											<c:if test="${bankInfo.card_url_opposite ne null && bankInfo.card_url_opposite ne ''}">
												<a class="txt" target="_blank" href="<adwalker:loadImg/>/${bankInfo.card_url_opposite}">查看</a>
											</c:if>
											<c:if test="${bankInfo.card_url_opposite eq null || bankInfo.card_url_opposite eq ''}">
													(未上传)
											</c:if>
										</div>
										<input type='button' class='btn' value='浏览...' id="file_upload_opposite" />
									</div>
								</div>
								<div class="actions">
									<input type="button" class="btn-glow primary" onclick="submitForm();" value="确认保存"/>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu5_2");
		</script>
	</body>
</html>