<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>注册账户-<%=company%>广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/theme.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/signup.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/poshytip/tip-yellow/tip-yellow.css" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/poshytip/jquery.poshytip.min.js"></script>
	    <style type="text/css">
			.errorsummary{
				display:none;
				width: 270px;
				height: 20px;
				background: url(${pageContext.request.contextPath}/img/sprite.gif) no-repeat -56px 0px;
				background-color:#F26535;
				color: white;
				font-size: 12px;
				line-height: 20px;
				font-size: 12px;
				padding-left: 30px;
				line-height: 20px;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function(){
				var error='${message}';
				if(error){
					$(".errorsummary").css("display","block");
				}
			});
			$(function(){
				$('#email').poshytip({
					content: '输入的邮箱格式不正确 ！！',
					showOn: 'none',
					alignTo: 'target',
					alignX: 'inner-right',
					offsetX: -50,
					offsetY: 5
				});
				$('#password').poshytip({
					content: '请输入6至15位的字母数字！！',
					showOn: 'none',
					alignTo: 'target',
					alignX: 'inner-right',
					offsetX: -50,
					offsetY: 5
				});
				$('#confimPassword').poshytip({
					content: '请输入确认密码！！',
					showOn: 'none',
					alignTo: 'target',
					alignX: 'inner-right',
					offsetX: -50,
					offsetY: 5
				});
				$('#code').poshytip({
					content: '请输入验证码！！',
					showOn: 'none',
					alignTo: 'target',
					alignX: 'inner-right',
					offsetX: -50,
					offsetY: 5
				});
				$('#email').blur(function(){
					var reg=new RegExp($(this).attr("reg"));
					if(!$(this).val()||($.trim($(this).val()).length<=0)||(!reg.test($(this).val()))){
						$(this).poshytip('show');
					}
				}).keyup(function(){
					$(this).poshytip('hide');
				}).focus(function(){
					$(this).attr("class","form-control_2");
					$(this).poshytip('hide');
				});
				$('#password').blur(function(){
					var reg=new RegExp($(this).attr("reg"));
					if(!$(this).val()||($.trim($(this).val()).length<=0)||(!reg.test($(this).val()))){
						$(this).poshytip('show');
					}
				}).keyup(function(){
					$(this).poshytip('hide');
				}).focus(function(){
					$(this).attr("class","form-control_2");
					$(this).poshytip('hide');
				});
				$('#confimPassword').blur(function(){
					var reg=new RegExp($(this).attr("reg"));
					if(!$(this).val()||($.trim($(this).val()).length<=0)||(!reg.test($(this).val()))){
						$(this).poshytip('show');
					}else if($(this).val()!=$("#password").val()){
						$(this).poshytip('update', '您两次输入的密码不一致！');
						$(this).poshytip('show');
						$(this).val("");
					}
				}).keyup(function(){
					$(this).poshytip('hide');
				}).focus(function(){
					$(this).attr("class","form-control_2");
					$(this).poshytip('hide');
				});
				
				$('#code').blur(function(){
					if(!$(this).val()||($.trim($(this).val()).length<=0)){
						$(this).poshytip('show');
					}
				}).keyup(function(){
					$(this).poshytip('hide');
				}).focus(function(){
					$(this).attr("class","form-control_2");
					$(this).poshytip('hide');
				});
			});
			function reloadImage() {
				var src = "${pageContext.request.contextPath}/jcaptcha";
				document.getElementById("pic_code").src = src + "?t=" + (new Date()).getTime();
			}
			
			function vaildateRegForm(form){
				var isSubmit = true;
				var form=$('#'+form).find('input');
				for ( var i = 0; i < form.length; i++) {
					var input = $(form[i]);
					if (input.attr("reg") != null) {
						if (input.attr("disabled") != "disabled") {
							if (input.is(":input") || input.is(":textarea")) {
								if (!validate(input, false)) {
									isSubmit = false;
									$(form[i]).poshytip('show');
									return isSubmit;
								} 
							} else if (input.is(":select")) {
								if (!validate(input, false)) {
									isSubmit = false;
								}
							}
						}
					}
				}
				if (isSubmit == true) {
					// document.getElementById("submit").disabled = true;
					$("#submit").attr("disabled", true);
					$(":input[type=button]").attr("disabled", "disabled");
				}
				return isSubmit;
			}
			function submitForm() {
				if(!vaildateRegForm("registerForm")){
					return ;
				}
				if($('#confimPassword').val()!=$("#password").val()){
					$('#confimPassword').poshytip('update', '您两次输入的密码不一致！');
					$('#confimPassword').poshytip('show');
					$('#confimPassword').val("");
					return;
				}
				$("#registerForm").submit();
			}
			$(document).keyup(function(event) {
				if(event.keyCode ==13){
					$("#submit").trigger("click");
				}
			});
		</script>
<body>    
    <!-- begins navbar -->
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
    <!-- ends navbar -->

    <div id="box_sign">
        <div class="container" style="margin-bottom:220px">
            <div class="span12 box_wrapper">
                <div class="span12 box">
                    <div>
                        <div class="head">
                            <h4>注册一个新账户</h4>
                        </div>
                        <div class="form">
                           <form id="registerForm" name="registerForm" action="${pageContext.request.contextPath}/register.action" method="post">
							<div class="errorsummary" id="error_tip">${message}</div>
							<input class="form-control_2" type="text" name="email" placeholder="邮箱" id="email" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" tips="您输入的用户名已存在！" maxlength="30">
							<input class="form-control_2" type="password" placeholder="密码" name="password" id="password" reg="^(\w){6,15}$"  maxlength="15" tip="请输入6至15位的字母数字 ">
							<input class="form-control_2" type="password" placeholder="确认密码" name="confimPassword"	id="confimPassword" reg="^(\w){6,15}$" tip="请输入6至15位的字母数字 " tips="您两次输入的密码不一致！" maxlength="15">
                            <input class="form-control_2"  style="width:50%;" name="code" type="text" id="code" reg="^(\w){4,5}$" placeholder="验证码" tip="请输入验证码" />
                            <img src="${pageContext.request.contextPath}/jcaptcha?t=1399348101254" name="pic" id="pic_code" border="0" style=" width:122px;height:40px; margin-left:8px; margin-right:8px; margin-top:-16px">
                            <a href="#" onclick="reloadImage();" title="换一张"><div style="float:right; line-height:40px; margin-right:10px;">换一张</div></a> 
                            <input type="submit" class="btn" onclick="submitForm();" value="注 册" />
                            </form>
                        </div>
                    </div>
                </div>
                <p class="already">已有账号？ 
                    <a href="${pageContext.request.contextPath}/login.jsp">立即登录</a></p>
            </div>
        </div>
    </div>
    <!-- starts footer -->
   	<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>