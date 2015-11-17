<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>找回密码-行云移动广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js" charset="utf-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js"></script>
	<script type="text/javascript">
		function submit_Form(){
			var email=$("#email").val();
			if(email==null||email.length<=0){
				alert("请填写邮箱地址");
			}
				$("#reg_form").ajaxSubmit(function(data){
					if(data=="true"){
						alert("密码已经发送到您的邮箱，请查看，谢谢");
						window.location.href='login.action';
					}else{
						alert("您的邮箱不存在，请核实，谢谢！！");
					}
				});
		}
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
                            <h4>找回密码</h4>
                        </div>
                        <div class="form">
                      		  请正确输入您的邮箱帐号:
                            <form action="resetPassword.action" id="reg_form" method="post">
                                <input type="text" placeholder="邮箱" name="email" id="email"/>
                                <input type="button" class="btn" value="确认提交"  onclick="submit_Form();"/>
                            </form>
                        </div>
                    </div>
                </div>
                <p class="already">记得密码？ 
                    <a href="login.jsp">立即登录</a></p>
            </div>
        </div>
    </div>
    <!-- starts footer -->
	<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>