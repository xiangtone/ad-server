<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript">
			// 检查金额
			function checkMoney(val, minMoney) {
				if (val != null) {
					var money = $("#applyMoneySussess").attr("value");
					var drawMoney = $("#devMinDrawMoney").attr("value");
					if (val > 0 && money != '' && money > 0) {
						var reg = /^[0-9]+$|^[0-9]+[\.][0-9]{0,2}$/;
						if (!reg.test(val)) {
							alert("提款金额只支持两位小数！");
							$("#applyMoney").attr("value", parseFloat(money.substring(0, money.indexOf(".") + 3)));
						}
						if (parseFloat(money.substring(0, money.indexOf(".") + 3)) < val) {
							alert("您无法申请高于已确认金额的钱数！");
							$("#applyMoney").attr("value", parseFloat(money.substring(0, money.indexOf(".") + 3)));
						}
						if (parseFloat(drawMoney) > parseFloat(val)) {
							alert("您申请钱数要高于" + drawMoney + "");
							$("#applyMoney").attr("value", drawMoney);
						}
						if (val < parseFloat(minMoney.substring(0, minMoney.indexOf(".") + 3)) && val != null || money < parseFloat(minMoney.substring(0, minMoney.indexOf(".") + 3))) {
							$("#submit").attr("disabled", true);
						} else {
							$("#submit").attr("disabled", false);
						}
					} else {
						if (money == '' || money == 0) {
							alert("您可能还没有可提现的金额！");
						}
						if (val < 0) {
							alert("请输入不小于零合法数字！");
						}
						$("#applyMoney").attr("value", '');
					}
				}
			}
			function submitForm() {
				if (vaildateForm("myForm")) {
					$("#myForm").ajaxSubmit(function(data) {
						var dataObj = eval("(" + data + ")");//转换为json对象 
						if (dataObj.status == 1) {
							alert("提款成功！！");
							window.location.href = 'toDeveloperApplyMoneyAccount.action?id=' + dataObj.data;
						} else if (dataObj.status == -1) {
							alert("请完善财务信息");
							window.location.href = 'toUpdateFnancialInfo.action';
						} else {
							alert("提款失败！！");
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
			<div id="pad-wrapper" class="new-user">
				<div class="row header">
					<div class="col-md-12">
						<h3>提款申请</h3>
					</div>
				</div>
				<div class="row form-wrapper ">
					<!-- left column -->
					<div class="col-md-9 with-sidebar">
						<div class="container">
							<form class="new_user_form" action="developerApplyMoney.action" method="post" id="myForm">
								<input id="applyMoneySussess" value="${developerVo.applyMoney}" type="hidden" /> <input id="devMinDrawMoney" value="${developerVo.devMinDrawMoney}" type="hidden" />
								<div class="col-md-12 field-box">
									<h4>${developerVo.email}</h4>
									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的可提款收入为<font style="color: red"><strong> ${developerVo.applyMoney }元 </strong></font>，该金额满100元即可申请取款。每周提款申请时间为周三15:00前，逾期的申请将顺延到下周处理。
									</p>
									<p>更多常见问题，请参考页面右侧的“提款说明”。</p>
								</div>
								<div class="col-md-12 field-box">
									<p>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp请输入您要提款的金额：
										<c:choose>
											<c:when test="${developerVo.applyMoney>=developerVo.devApplyMinMoney}">
												<input type="text" class="event-input form-control" name="applyMoney" id="applyMoney" onblur="checkMoney(this.value,'${developerVo.devApplyMinMoney}')" reg="(^[0-9]{1,8})+([.]\d{1,2})?$" tip="请输入数字" maxlength="11" />
											</c:when>
											<c:otherwise>
												<input type="text" class="event-input form-control" name="applyMoney" id="applyMoney" disabled="disabled" value="账户余额满${developerVo.devApplyMinMoney}元可提现" style="width: 140px;" />
											</c:otherwise>
										</c:choose>
										元 <span class="alert-a"> 输入必须为整数</span>
									</p>
								</div>
								<div class="col-md-11 field-box actions">
									<c:choose>
										<c:when test="${developerVo.applyMoney>=developerVo.devApplyMinMoney}">
											<input class="btn-glow primary" id="submit_btn" type="button" onclick="submitForm();" value="提交申请" />
										</c:when>
										<c:otherwise>
											<input class="btn-glow primary" id="submit_btn" type="button" value="提交申请" disabled="disabled" alt="不能申请" title="不能申请" />
										</c:otherwise>
									</c:choose>
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-3_1 col-xs-12 form-sidebar pull-right">
					<br/><br/>
						<%=company%>广告平台提款说明：<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;1.开发者可以随时在后台上提交提款申请，平台默认开发者申请上月提款为本月的5号以后。<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;2.每月的5-20号平台对于开发者的提款进行审核及结算，20号之前完成付款。<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;3.开发者可将提款申请的发票邮寄至"<%=company%>"，发票种类为增值税专用发票。<br/>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu4_2");
		</script>
	</body>
</html>