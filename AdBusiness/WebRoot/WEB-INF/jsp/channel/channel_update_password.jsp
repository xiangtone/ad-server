<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script type="text/javascript">
			$(function() {
				var $buttons = $(".toggle-inputs button");
				var $form = $("form.new_user_form");
				$buttons.click(function() {
					var mode = $(this).data("input");
					$buttons.removeClass("active");
					$(this).addClass("active");
					if (mode === "inline") {
						$form.addClass("inline-input");
					} else {
						$form.removeClass("inline-input");
					}
				});
			});
			$(document).ready(function() {
				$(".close").click(function() {
					$(this).parent().fadeTo(300, 0, function() { // Links with the class "close" will close parent
						$(this).slideUp(300);
					});
					return false;
				});
		
			});
	//提交
			function submitForm() {
				if (vaildateForm("myForm")) {
					$("#myForm").ajaxSubmit(function(data) {
						if (data == "true") {
							alert("修改成功！！");
							window.location.href = 'toUpdateChannelPass.action';
						} else {
							alert("修改失败，请重试！！");

						}

					});
				}
				;

			}
			//校验输入的密码
			
			function passwordRatio() {
				var pass = document.getElementById("oldpass").value;
				var param_data = 'oldpass=' + pass;
				$.ajax({
					url : 'passwordChannelRatio.action',
					type : 'POST',
					data : param_data,
					dataType : 'text',
					beforeSend : function() {
					},
					success : function(data) {
						if (data == "false") {
							$("#sp").html("输入的密码和旧密码不一致");
						} else {
							$("#sp").html("");

						}

					}
				});
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/channel/left_channel.jsp"></jsp:include>
		<div class="content">
			<div class="settings-wrapper" id="pad-wrapper">
				<div class="row header">
					<h3>修改密码</h3>
				</div>
				<div class="table-products section">
	
					<div class="row filter-block">
						<div class="personal-info">
							<form class="form-horizontal" role="form" name="updateUserPass" action="updateChannelPass.action" method="post" id="myForm">
								<div class="form-group">
									<label class="col-lg-2 control-label">注册账号：</label>
									<div class="col-lg-8">
										<span>${userChannel.email }</span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">原始密码：</label>
									<div class="oldpass">
										<input class="form-control" name="oldpass" id="oldpass" type="password" reg="^(\w){6,15}$" maxlength="15" tip="请输入6-15位密码 " onblur="passwordRatio()" /> <span id="sp" style="color: red"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">新密码：</label>
									<div class="password">
										<input class="form-control" name="password" id="password" type="password" reg="^(\w){6,15}$" tip="请输入6-15位密码 " />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">确认输入：</label>
									<div class="confimPassword">
										<input class="form-control" type="password" id="confimPassword" name="confimPassword" reg="^(\w){6,15}$" tip="请输入6-15位密码" tips="密码不一致！" />
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
			selectedMenu("menu5_4");
		</script>
	</body>
</html>