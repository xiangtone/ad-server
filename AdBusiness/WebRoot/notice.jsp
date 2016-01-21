<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>通知公告-<%=company%>广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/theme.js"></script>
<body>
	<a href="#" class="scrolltop"><span>up</span></a>
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
	<div id="blog_wrapper">
		<div class="container">
			<h2 class="section_header">
				<hr class="left visible-desktop"  style="margin-top:25px"/>
				<span>通知公告</span>
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
					<h3 class="sidebar_header">公告</h3>
					<ul class="sidebar_menu">
						<li><a href="${pageContext.request.contextPath}/notice.jsp">通知公告</a></li>
						<li><a href="${pageContext.request.contextPath}/activity.jsp">最新活动</a></li>
						<li><a href="${pageContext.request.contextPath}/press.jsp">新闻动态</a></li>
					</ul>
				</div>
				<div class="span8">
					<h4 class="header" id="通知公告">通知公告 <hr /></h4>
					<div class="container" style="width:100%">
						<div class="row">
							<div class="span12" style="width:96%;">
								<div class="faq"  style="color: red ">
									<div class="question">
										开发者年度清算通知<div style="float: right; font:;font-weight: normal; font-size: 14px; text-decoration: underline;">2014-12-09</div>
									</div>
									<div class="answer">
										致亲爱的开发者：<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=company%>平台因系统升级，拟于2014年12月31日对本年度全部媒介费用进行清算。<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本次清算无最低提款额度限制，请在2014年12月31日前完成提款申请。<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们会在2015年1月1日开始进行审核，并于1月10日左右完成付款。<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;过时将对2014年所有费用进行封账，不再进行结算与付款，希望广大开发者及时进行提款申请，<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以免造成不必要的纠纷。谢谢。<br /><br />
										如有疑问请联系：<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服QQ：<%=service_notice_qq%><br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服邮箱：<%=service_notice_email%><br /><br /><br />  
										<div style="float:right; margin-right:30px;"><%=company%>广告平台 </div><br />
										<div style="float:right; margin-right:30px;">2014-12-09</div><br />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="span12" style="width:96%;">
								<div class="faq">
									<div class="question">
										媒介结款新政策<div style="float: right; font:;font-weight: normal; font-size: 14px; text-decoration: underline;">2014-05-10</div>
									</div>
									<div class="answer">
										致亲爱的开发者：<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自2014年6月1日起，IOS及Android媒介统一实行月结算政策。<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;给您带来的不便，敬请谅解！具体结算方式：每月3号，<%=company%>将统一处理上月媒介开发者提款申请。<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请各位开发者及时提交付款申请以免延误。<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=company%>团队将真诚地为伙伴们服务，祝各位开发者在2014年事业亨通！<br /><br />
										如有疑问请联系：<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服QQ：<%=service_notice_qq%><br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服电话：<%=service_notice_phone%><br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服邮箱：<%=service_notice_email%><br /><br /><br />  
										<div style="float:right; margin-right:30px;"><%=company%>广告平台 </div><br />
										<div style="float:right; margin-right:30px;">2014-05-10</div><br />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="span12" style="width:96%;">
								<div class="faq">
									<div class="question">
										新版SDK上线(Android2.1.0、iOS2.1.0)<div style="float: right; font:;font-weight: normal; font-size: 14px; text-decoration: underline;">2014-04-29</div>
									</div>
									<div class="answer">
										亲爱的开发者小伙伴们：<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=company%>开发者服务平台新版SDK正式上线，更新版本为：<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Android2.1.0，iOS2.1.0；<br />
										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新版中回归最简界面，优化代码保证数据精准，页面优化跳转更流畅。<br /><br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎广大开发者下载更新，您在使用中有任何问题，请联系我们，<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客服QQ：<%=service_notice_qq%>。  <br /><br /><br />  
										<div style="float:right; margin-right:30px;"><%=company%>广告平台 </div><br />
										<div style="float:right; margin-right:30px;">2014-04-29</div><br />
									</div>
								</div>
							</div>
						</div>
						<div class="pagination">
							<ul>
								<li><a href="#">上一页</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">下一页</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>