<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>行云移动广告平台—</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/animate.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/js/html5.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/theme.js"></script>
</head>
<body>
	<a href="#" class="scrolltop"><span>up</span></a>
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
	<div id="hero">
		<div class="container">
			<div class="row animated fadeInDown">
				<div class="span12">
					<div id="myCarousel" class="carousel slide">
						<div class="carousel-inner">
							<div class="active item slide1">
								<div class="row">
									<div class="span6"><img src="${pageContext.request.contextPath}/img/slide1.png" alt="slide1" /></div>
									<div class="span4">
										<h1>积分墙广告</h1>
										<p>行云积分墙是在APP内展示各种广告任务供用户完成并获得虚拟币的页面广告，包括安装使用应用，填表注册等。用户完成任务获取虚拟币的同时APP开发者也随即获得收益</p>
										<a href="${pageContext.request.contextPath}/developer.jsp" class="btn btn-success btn-large">了解更多</a>
									</div>
								</div>
							</div>
							<div class="item slide2">
								<div class="row">
									<div class="span4 animated fadeInUpBig">
										<h1>插屏广告</h1>
										<p>行云插屏在弹出时机和场景上均可自由设置。采用最新HTML5技术，支持做种展示形式，还有更多丰富的广告内容</p>
										<a href="${pageContext.request.contextPath}/developer.jsp" class="btn btn-success btn-large">了解更多</a>
									</div>
									<div class="span6 animated fadeInDownBig"><img src="${pageContext.request.contextPath}/img/slide2.png" alt="slide2" /></div>
								</div>
							</div>
							<div class="item slide3 animated fadeInUpBig">
								<a href="${pageContext.request.contextPath}/jionus.jsp"> <img src="${pageContext.request.contextPath}/img/slide3.png" alt="slide3" /></a>
							</div>
						</div>
						<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
						<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="features" style="margin-top:56px;">
		<div class="container">
			<div class="row">
				<div class="span4 feature">
					<h3 class="sidebar_header"><i class="i_cloud"></i>通知公告<hr class="visible-desktop" /></h3>
					<p class="description" style="font-size:18px">致亲爱的开发者：</p>
					<a href="${pageContext.request.contextPath}/notice.jsp">
						<p class="description" style="color: red">行云平台因系统升级，拟于2014年12月31日对本年度全部媒介费用进行清算。</p>
					</a>
					<a href="${pageContext.request.contextPath}/notice.jsp" class="btn btn-default">更多</a>
				</div>
				<div class="span4 feature"></div>
                <div class="span4 feature">
					<h3><i class="i_movil"></i>下载中心<hr class="visible-desktop" /></h3>
					<p class="description">
						<a href="http://res.adwalker.cn/adres/androidSDK/xingyun-android-sdk.zip" target="_blank"><img src="${pageContext.request.contextPath}/img/android-H.png" onMouseOver="this.src='${pageContext.request.contextPath}/img/android-p.png';" onMouseOut="this.src='${pageContext.request.contextPath}/img/android-H.png';"></a>
					</p>
					<p class="description">
						<a href="http://res.adwalker.cn/adres/iOSSDK/xingyun-iOS-sdk.zip" target="_blank"><img src="${pageContext.request.contextPath}/img/iOS-H.png" onMouseOver="this.src='${pageContext.request.contextPath}/img/iOS-p.png';" onMouseOut="this.src='${pageContext.request.contextPath}/img/iOS-H.png';"></a>
					</p>
					<a href="${pageContext.request.contextPath}/down_center.jsp" class="btn btn-default">更多</a>
				</div>
			</div>
		</div>
	</div>
	<div id="features" style="margin-top:72px">
	 	<div class="container">
			<div class="row">
				<div class="span4 feature" id="pricing">
					<div class="price_wrapper lite regular">
						<div class="header"><span>开发者</span></div>
                        <div class="section">
							<ul class="perks"><img src="${pageContext.request.contextPath}/img/developer.jpg"></ul>
							<a href="${pageContext.request.contextPath}/developer.jsp" class="btn">了解更多</a>
						</div>
					</div>
				</div>
				<div class="span4 feature">
					<h3><i class="i_partner"></i>合作伙伴<hr class="visible-desktop" /></h3>
                    <p class="description">
						<a href="#"><img class="partneriocn" src="${pageContext.request.contextPath}/img/moji.png"></a>
						<a href="#"><img class="partneriocn" src="${pageContext.request.contextPath}/img/geshengzi.jpg"></a>
						<a href="#"><img class="partneriocn" src="${pageContext.request.contextPath}/img/kengdie2.jpg"></a>
						<a href="#"><img class="partneriocn" src="${pageContext.request.contextPath}/img/wannianli.jpg"></a>
						<a href="#"><img class="partneriocn" src="${pageContext.request.contextPath}/img/wangyixinwen.jpg"></a>
					</p>
					<a href="#" class="btn btn-default">更多</a>
				</div>
				<div class="span4 feature">
					<h3><i class="i_call"></i>联系我们<hr class="visible-desktop" /></h3>
					<p class="description">
						<p class="description" style="font-size:18px">
							开发者联系方式
							<!-- <span style="font-size:12px;float:right">联系电话：12345678</span> -->
							<p class="description">
								<span style="padding:0 30px 18px 0">媒介盖梆：<a href="tencent://message/?uin=1977975161&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
								<span>媒介小雪：<a href="tencent://message/?uin=1014300145&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
							</p>
							<p class="description">
								<span style="padding:0 30px 18px 0">媒介小丽：<a href="tencent://message/?uin=745137433&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
								<span><!--媒介小拉：<a href="#"><img src="${pageContext.request.contextPath}/img/qq.png"></a>--></span>
		                    </p>
	                    </p>
						<p class="description" style="font-size:18px">
							广告主联系方式
							<!-- <span style="font-size:12px;float:right">联系电话：12345678</span>  -->                 
						<p class="description">
							<span style="padding:0 30px 18px 0">商务海霞：<a href="tencent://message/?uin=1825998992&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
							<span>商务安冬：<a href="tencent://message/?uin=2215006476&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
						</p>
						<p class="description">
							<span style="padding:0 30px 18px 0">商务晶晶：<a href="tencent://message/?uin=2066814324&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
							<!-- <span>商务小拉：<a href="#"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span> -->
						</p>
					</p>
					<a href="${pageContext.request.contextPath}/contactus.jsp" class="btn btn-default">更多</a>
				</div>
			</div>
		</div>
	</div>
	<div id="testimonials">
		<div class="container">
			<div class="controls"><hr /></div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
</body>
</html>