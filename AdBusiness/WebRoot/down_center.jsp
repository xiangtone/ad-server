<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>下载中心-行云移动广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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
                <span>下载中心</span>
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
                       	 下载
                    </h3>
                    <ul class="sidebar_menu">
                        <li><a href="#">Android SDK</a></li>
                        <li><a href="#" data-section="#footer">iOS SDK</a></li>
                    </ul>
                </div>
                <div class="span8">
            <!-- header -->
                    <h4 class="header" id="">
                        SDK下载  
                        <hr />
                    </h4>
                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="row">
                <div class="span12" style="width:96%;">
                    <div class="faq"  style="width:60%; float:left">
                        <div style="color: #2c3339; font-size: 22px;">
                            Android SDK
                        </div>
                        <div class="answer" style="display:inherit;border-bottom:hidden">
							版本号：2.2.0<br />
							发版时间：2014-06-21<br />
							更新日志：<br />
							   <div class="changelog">
							          （1）增加对用户的帮助提示及反馈功能，使产品更人性化；<br />
							          （2）在积分墙内增加推荐墙，给用户更多选择。<br />
							   </div>
							在线开发文档：<a href="http://res.adwalker.cn/adres/developersAndroidExplain.pdf" style="color:#06C" target="_blank">点击查看</a><br />
                          <div style="margin-top:36px">
						    <a href="http://res.adwalker.cn/adres/androidSDK/xingyun-android-sdk.zip" target="_blank">
							<img src="${pageContext.request.contextPath}/img/d_android.png"
							onMouseOver="this.src='${pageContext.request.contextPath}/img/d_android_on.png';"
							onMouseOut="this.src='${pageContext.request.contextPath}/img/d_android.png';">
						    </a>
					      </div>
                        </div>
                    </div>
                    <div class="faq" style="width:40%; float:left">
                        <div style="color: #2c3339;  font-size: 22px;">
                            Android DEMO
                        </div>
                        <div class="answer" style="display:inherit; border-bottom:hidden">
                        <img src="img/erweima.png" style="margin-top:16px">
                        <br>用手机扫码直接下载
					<div style="margin-top:36px">
						<a href="http://res.adwalker.cn/adres/androidSDK/Walker_ClientDemo.apk"  target="_blank">
							<img src="${pageContext.request.contextPath}/img/d_demo.png"
							onMouseOver="this.src='${pageContext.request.contextPath}/img/d_demo_on.png';"
							onMouseOut="this.src='${pageContext.request.contextPath}/img/d_demo.png';">
						</a>
					    </div>
                        </div>
                    </div>
                    <div class="faq">
                        <div class="answer" style="display:inherit; margin-top:312px"></div>
                    </div>
                    <div class="faq" id="iOS下载">
                        <div style="color: #2c3339; font-size:22px;">
                            iOS SDK
                        </div>
                        <div class="answer" style="display:inherit;">
							版本号：2.3.0<br />
							发版时间：2014-09-23<br />
							更新日志：<br />
							   <div class="changelog">
							   	1.修复sdk对接回调失败bug<br />
								2.适配ios8,插屏适配iphone6,iphone6plus <br />
								3.修复xcode6的一些问题<br />
							   </div>
							在线开发文档：<a href="http://res.adwalker.cn/adres/developersIOSExplain.pdf" style="color:#06C" target="_blank">点击查看</a><br /> 
                          <div style="margin-top:36px">
                          	<a href="http://res.adwalker.cn/adres/iOSSDK/xingyun-iOS-sdk.zip" target="_blank">
							<img src="${pageContext.request.contextPath}/img/d_ios.png"
							onMouseOver="this.src='${pageContext.request.contextPath}/img/d_ios_down.png';"
							onMouseOut="this.src='${pageContext.request.contextPath}/img/d_ios.png';">
						    </a>
					      </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
        <div class="pagination">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- starts footer -->
 <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>