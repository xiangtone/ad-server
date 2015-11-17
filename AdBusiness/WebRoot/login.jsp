<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登录账户-行云移动广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signin.css" />
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
			});
			function reloadImage() {
				var src = "${pageContext.request.contextPath}/jcaptcha";
				document.getElementById("pic_code").src = src + "?t=" + (new Date()).getTime();
			}
			function vaildateLoginForm(form){
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
				if(!vaildateLoginForm("loginForm")){
					return ;
				}
				$("#loginForm").submit();	
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

    <div id="box_login">
        <div class="container" style="margin-bottom:220px">
            <div class="span12 box_wrapper">
                <div class="span12 box">
                    <div>
                        <div class="head">
                            <h4>登录账户</h4>
                        </div>
                        <div class="form">
                            <form name="loginForm" id="loginForm" action="dologin.action" method="post">
							<div class="errorsummary" id="error_tip">${message}</div>
                                <input id="email" value="${email}" name="email" class="form-control_2" type="text" reg="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" placeholder="邮箱" tips="您输入的用户名已存在！" maxlength="30">
								<input id="password" name="password" class="form-control_2" type="password" placeholder="密码"  reg="^(\w){6,15}$"  maxlength="15" tip="请输入6至15位的字母数字 ">
                                <div class="remember">
                                    <div class="right">
                                        <a href="${pageContext.request.contextPath}/find_pass.jsp">忘记密码?</a>
                                    </div>
                                </div>
                                <input type="submit" class="btn" value="登 录" onclick="submitForm();"/>
                            </form>
                        </div>
                    </div>
                </div>
                <p class="already">没有账号? <a href="${pageContext.request.contextPath}/register.jsp"> 立即注册</a></p>
            </div>
        </div>
    </div>
    <!-- starts footer -->
	<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>