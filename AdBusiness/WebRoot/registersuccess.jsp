<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>欢迎加入-<%=company%>广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
 	 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/signup.css" type="text/css" media="screen" />
 	 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	    <script type="text/javascript">
		    $.ajax({
				url : "sendMail.action",
				data : "email=${email}",
				type : "POST",
				dataType : "text",
				error : function() {
					
				},
				success: function(result) {
				}
			});
		    
		    
		    function resin(){
		    	 $.ajax({
						url : "sendMail.action",
						data : "email=${email}",
						type : "POST",
						dataType : "text",
						error : function() {
							
						},
						success: function(result) {
						}
					});
		    	
		    }
	    </script>
<body>
    <!-- begins navbar -->
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
    <!-- ends navbar -->

    <!-- coming soon -->
    <div id="comingsoon" class="comingsoon_page" style=" margin-top:0px">
        <div class="container">
            <!-- header -->
            <h2 class="section_header">
                <hr class="left visible-desktop"  style="margin-top:25px"/>
                <span>
                  	  欢迎加入
                </span>
                <hr class="right visible-desktop" style="margin-top:25px"/>
            </h2>
            <div class="countdown">
              <div class="row" style=" margin-left:0px;text-align:center;">
                  <div class="offset1 left" style="text-align:center; margin-left:auto;">
                  <p>
                 	     我们已将激活邮件发送至：${email},您需要点击邮件内的激活链接对账户进行激活完成注册！
                      </p>
                  </div>
              </div>
              <div class="bar" style="margin-top: 54px; font-size: 18px; font-weight: bold;">
                  <div class="offset1 left" style="text-align:center; margin-left:auto;">
                  <a href="${url}" class="btn btn-success btn-large">点击登录邮箱 </a>
                  </div>
              </div>
              <div class="row" style="margin-top: 54px; margin-left: 0px; text-align: center;">
                  <div class=" offset1  left" style="font-size:14px;margin-left: auto; margin-right: auto;width:360px; height:25px;">
                                  <div class="already" style="float:left">没有收到邮件? <a href="" onclick="resin();" style="color:#428bca;"> 重新发送</a></div>
                                  <div class="already" style="float: right">邮箱填写错误? <a href="${pageContext.request.contextPath}/register.jsp" style="color:#428bca;"> 修改邮箱</a></div>

                  </div>
              </div>
            </div>
            <div class="subscribe">
              <div class="row">
             </div>
            </div>
        </div>
    </div>

    <!-- starts footer -->
	<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>