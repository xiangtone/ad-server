<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>开发者常见问题-帮助中心-行云移动广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme.js"></script>
<body>
    <a href="#" class="scrolltop">
        <span>up</span>
    </a>
    <!-- begins navbar -->
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
    <!-- ends navbar -->

    <div id="blog_wrapper">
        <div class="container">
        
            <h2 class="section_header">
                <hr class="left visible-desktop"  style="margin-top:25px"/>
                <span>帮助中心</span>
                <hr class="right visible-desktop" style="margin-top:25px"/>
            </h2>
            <div class="row map">
                <div class="span12">
                    <div style="width: 100%;height: 200px;border: 3px solid #fff;border-radius: 6px;box-shadow: 0px 0px 1px 1px #CFCFCF;">
                        <img style="width: 100%; height: 100%; border: none;" src="${pageContext.request.contextPath}/img/help-center.jpg">
                    </div>
                </div>
            </div>

            <div class="row" id="faq">

                <div class="span3 sidebar offset1">


                    <h3 class="sidebar_header">
                        帮助
                    </h3>
                    <ul class="sidebar_menu">
                        <li><a href="${pageContext.request.contextPath}/help_center.jsp">平台常见问题</a></li>
                        <li><a href="${pageContext.request.contextPath}/dev_problem.jsp">开发者常见问题</a></li>
                    </ul>


                </div>
                <div class="span8">
            <!-- header -->
                    <h4 class="header" id="开发者常见问题">
                        平台常见问题
                        <hr />
                    </h4>

                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="bar-warning">
                <div class="span12" style="width:96%;">
                    <div class="faq">
                        <div class="question">
                            如何注册行云开发者服务平台账户？
                        </div>
                        <div class="answer">
                        
   （1）打开<a href="index.html"target="_blank" style="color:#06C">http://www.adwalker.cn/</a>，点击页面右上角的“注册”按钮，即可弹出注册页面；<br />
   （2）在注册页面填写相关信息,点击注册按钮即可收到账户激活邮件；<br />
   （3）登录填写的邮箱，打开邮箱中的验证链接来完成邮箱验证。<br />   
     邮箱激活后就可以使用行云开发者服务平台提供的各项服务了。<br />                            
                        </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                            忘记密码怎么办？
                        </div>
                        <div class="answer">
    （1）打开<a href="index.html"target="_blank" style="color:#06C">http://www.adwalker.cn/</a>，点击页面右上角的“登录”按钮，在登录页面中点击“忘记密码”。<br /> 
    （2）输入您注册时填写的邮箱地址，点击“找回密码”按钮。<br />  
    （3）登录您的邮箱，打开邮件中的密码重置，然后使用平台提供的重置密码登录平台，并及时进行密码修改。 <br /> 
                          </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                            激活邮件/密码找回邮件没有收到？
                        </div>
                        <div class="answer">
请先耐心等待，可能是网络延迟造成的；同时请检查是否在垃圾邮件中；如果数分钟后仍没有收到邮件，请联系平台客服。<br />
                       </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                            如何修改密码？
                        </div>
                        <div class="answer">
（1）登录平台后，点击导航边栏的“个人中心”里的“密码修改”。<br />
（2）输入新密码及确认密码。<br /> 
（3）点击“修改”，以保存修改结果。<br /> 
                       </div>
                    </div>
                    <div class="faq">
                        <div class="question">
                            被冻结的账户是否还能登录？
                        </div>
                        <div class="answer">
目前被冻结的账户无法登录行云开发者服务平台，您如果有什么问题可咨询平台客服。   <br />                        </div>
                    </div>
                </div>
            </div>
        </div>                    
                </div>

            </div>
        </div>
    </div>

    <!-- starts footer -->
 <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
    
</body>
</html>