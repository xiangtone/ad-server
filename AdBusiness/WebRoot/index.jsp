<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><%=company%>广告平台—</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/theme.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/animate.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/js/html5.js"></script>
	<![endif]-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/theme.js"></script> --%>

<script src="js/v.js" charset="utf-8"></script>
<script src="js/hm.js"></script>

<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?d4b3e64c9f3cc3b30ab8405384499f62";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>

<link rel="stylesheet" href="css/index.css">
<style type="text/css">
.page2 .content .pay .thumbnails font {
	font-size: 18px;
}

.span4 .img {
	float: left;
}

.thumbnails .span4 .bmbox font {
	color: #555;
	font-size: 20px;
}

@media ( min-width : 767px) {
	.bmbox {
		width: 97.9%;
		height: 38px;
		background-image: url('image/main_middle_36.png');
		background-size: 100%;
		float: left;
		margin-top: -17.8%;
		z-index: 1000;
		opacity: 0.8;
		margin-left: 1.35%;
		line-height: 38px;
	}
	.page3 .content ul {
		margin-left: -55px;
		
	}
	.page3 .content ul li {
		margin-left: 86px;
		padding-top: 67px;
		font-size: 20px;
		color: #555;
		text-align: center;
		line-height: 50px;
	}
}

@media ( min-width : 997px) {
	.bmbox {
		width: 97.9%;
		height: 38px;
		background-image: url('image/main_middle_36.png');
		background-size: 100%;
		float: left;
		margin-top: -16.6%;
		z-index: 1000;
		opacity: 0.8;
		margin-left: 0.9%;
		line-height: 38px;
	}
	.page3 .content ul {
		margin-left: -20px;
		
	}
	.page3 .content ul li {
		margin-left: 35px;
		padding-top: 67px;
		font-size: 20px;
		color: #555;
		text-align: center;
		line-height: 50px;
	}
}

@media ( min-width : 1200px) {
	.bmbox {
		width: 97.9%;
		height: 38px;
		background-image: url('image/main_middle_36.png');
		background-size: 100%;
		float: left;
		margin-top: -17.3%;
		z-index: 1000;
		opacity: 0.8;
		margin-left: 1.35%;
		line-height: 38px;
	}
	.page3 .content ul {
		margin-left: -30px;
		
	}
	.page3 .content ul li {
		margin-left: 57px;
		font-size: 20px;
		color: #555;
		text-align: center;
		line-height: 50px;
	}
	/*.page4 .content .none{
	position:absolute;
	z-index:1000;
}
.page4 .content .none1{
	position:relative;
	padding-top:215px;
}
.page4 .content .none2{
	position:relative;
}
 .page4 .content .thumbnails .pic1{
	margin-left:114px;
	margin-top:53px;
}
.page4 .content .thumbnails .pic2{
	margin-left:0.5px;
	margin-top:53px;
}
.page4 .content .thumbnails .pic3{
	margin-left:0px;
	margin-top:53px;
}
.page4 .content .thumbnails .pic4{
	margin-left:2px;
	margin-top:53px;
}
.page4 .content .thumbnails .pic5{
	margin-left:114px;
	margin-top:-53px;
}
.page4 .content .thumbnails .pic6{
	margin-left:0.5px;
	margin-top:-53px;
}
.page4 .content .thumbnails .pic7{
	margin-left:0px;
	margin-top:-53px;
}
.page4 .content .thumbnails .pic8{
	margin-left:2px;
	margin-top:-53px;
} */
}

.page4 .content ul li {
	text-align: center;
}

.page2 .content h1 font {
	font-size: 36px;
	color: #222;
}

.page2 .content p font {
	font-size: 20px;
	color: #333;
}

.page2 .content ul .none {
	width: 9%;
	margin-left: 195px;
}

.page2 .content ul .none1 {
	width: 23%;
	margin-left: 80px;
	margin-bottom: 0px;
}

.page2 .content p {
	padding-top: 26px;
}

.production {
	height: 517px;
	text-align: center;
}
</style>


</head>
<body>

	<div class="section">
		<!-- header -->

		<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>

		<!-- <div class="page page0 ">
			<div class="m-content">
				<div  class="content">
					               <img src="/image/new_index/index-words.png"><br>
					<div class="img" ></div>
					<a href="default_004.html">了解详情</a>
				</div>
			</div>
		</div> -->

		<!-- page1 -->
		<div class="page page1 ">
			<div class="content">
				<a href="javascript:;"><img class="immg" alt=""
					src="image/start_03.png"></a>
				<div style="clear: both;"></div>
			</div>
		</div>

		<!-- page2 -->
		<div class="page page2 "
			style="background-color: #fff; overflow: hidden;">
			<div class="content">
				<h1>
					<font>支付通道</font>
				</h1>
				<p>
					<font>满足主流支付通道和支付场景，为开发者提供一站式通道解决方案</font>
				</p>
				<div class="pay">
					<ul class="thumbnails">
						<li class="span4 none">
						<div id="unionpayt">
						<img id="unionpay" alt="unionpay"
							src="image/unionpay_2.png"><br>
						<br> <font id="unionpayf">银联支付</font>
						</div></li>
						<li class="span4 none">
						<div id="wechatt">
						<img id="wechat" alt="wechat"
							src="image/wechat_1.png"><br>
						<br> <font id="wechatf">微信支付</font>
						</div></li>
						<li class="span4 none">
						<div id="alipayt">
						<img id="alipay" alt="alipay"
							src="image/zhifubao_1.png"><br>
						<br> <font id="alipayf">支付宝支付</font>
						</div></li>
					</ul>
				</div>
				<ul class="thumbnails">
					
					<li class="span4 none1"><img class="img"
						src="image/main_17.png">
					<div class="bmbox">
							<font>游戏（Games）</font>
						</div></li>
					<li class="span4 none1"><img class="img"
						src="image/main_19.png">
					<div class="bmbox">
							<font>教育（Education）</font>
						</div></li>
					<li class="span4 none1"><img class="img"
						src="image/main_21.png">
					<div class="bmbox">
							<font>电商（E-Commerce）</font>
						</div></li>
					<li class="span4 none1"><img class="img"
						src="image/main_26.png">
					<div class="bmbox">
							<font>金融（Finance）</font>
						</div></li>
					<li class="span4 none1"><img class="img"
						src="image/main_27.png">
					<div class="bmbox">
							<font>零售（Retail）</font>
						</div></li>
					<li class="span4 none1"><img class="img"
						src="image/main_28.png">
					<div class="bmbox">
							<font>其他（Others）</font>
						</div></li>
				</ul>
				<div class="clear_float"></div>
			</div>
		</div>
		<script type="text/javascript">
			document.getElementById("unionpayt").onmouseover = function() {
				document.getElementById("unionpay").src = "image/unionpay_1.png";
				$("#unionpayf").css("color", "#006aaa");
				return true;
			}
			document.getElementById("unionpayt").onmouseout = function() {
				document.getElementById("unionpay").src = "image/unionpay_2.png";
				$("#unionpayf").css("color", "#555555");
				return true;
			}
			document.getElementById("wechatt").onmouseover = function() {
				document.getElementById("wechat").src = "image/wechat_2.png";
				$("#wechatf").css("color", "#006aaa");
				return true;
			}
			document.getElementById("wechatt").onmouseout = function() {
				document.getElementById("wechat").src = "image/wechat_1.png";
				$("#wechatf").css("color", "#555555");
				return true;
			}
			document.getElementById("alipayt").onmouseover = function() {
				document.getElementById("alipay").src = "image/zhifubao_2.png";
				$("#alipayf").css("color", "#006aaa");
				return true;
			}
			document.getElementById("alipayt").onmouseout = function() {
				document.getElementById("alipay").src = "image/zhifubao_1.png";
				$("#alipayf").css("color", "#555555");
				return true;
			}
		</script>
		<!-- page3 -->
		<div class="page page3 ">
			<div class="content">
				<div>
					<font style="font-size: 36px; color: #222;">快速接入</font>
				</div>
				<div style="font-size: 20px; color: #333; padding-top: 25px;">4个步骤一站式接入，简单易用操作从此触手可及</div>
				<ul class="thumbnails uul">
					<li class="span3"><img class="img"
						src="image/main_icon_03.png"><br>
					<br> 商户注册</li>
					<li class="span3"><img class="img"
						src="image/main_icon_05.png"><br>
					<br> 线上测试</li>
					<li class="span3"><img class="img"
						src="image/main_icon_07.png"><br>
					<br> 参数配置</li>
					<li class="span3"><img class="img"
						src="image/main_icon_09.png"><br>
					<br> 完成接入</li>
				</ul>
			</div>
		</div>


		<!-- page4 -->
		<div class="page page4 " style="background-color: #fff;">
			<div class="content">
				<div>
					<font style="font-size: 36px; color: #222;">产品优势</font>
				</div>
				<div style="font-size: 20px; color: #333; padding-top: 30px;">一次对接、一个账单、一个平台，应对任何行业和场景的商业需求</div>
				<!--<ul class="thumbnails none">
				 	
					<li class="span3 pic1"><img class="img1" src="image/main_bottom_16.png"></li>
					<li class="span3 pic2"><img class="img2" src="image/main_bottom_18.png"></li>
					<li class="span3 pic3"><img class="img3" src="image/main_bottom_20.png"></li>
					<li class="span3 pic4"><img class="img4" src="image/main_bottom_22.png"></li>
					</ul>
					<ul class="thumbnails none1"> 
					<li class="span2"><img class="img" src="image/main_bottom0_16.png"></li>
					<li class="span2"><img class="img" src="image/main_bottom0_18.png"></li>
					<li class="span2"><img class="img" src="image/main_bottom0_20.png"></li>
					<li class="span2"><img class="img" src="image/main_bottom0_22.png"></li>
					<li class="span2"><img class="img" src="image/main_bottom0_24.png"></li>
					 </ul>
					<ul class="thumbnails none2"> 
					<li class="span3 pic5"><img class="img" src="image/main_bottom_28.png"></li>
					<li class="span3 pic6"><img class="img" src="image/main_bottom_29.png"></li>
					<li class="span3 pic7"><img class="img" src="image/main_bottom_30.png"></li>
					<li class="span3 pic8"><img class="img" src="image/main_bottom_32.png"></li>
				</ul> -->
				<div class="clear_float"></div>
			</div>
			<div id="prod" class=" production">
				<img alt="" src="image/chanpin.png">
			</div>
		</div>


		<!-- page5 -->
		<div class="page page6 "
			style="background: url(image/foot_bg_02.png) no-repeat; background-color: #ebedf0;">
			<div class="content">
				<div>
					<font style="font-size: 36px; color: #222;">联系我们</font>
				</div>
				<div class="contact"
					style="font-size: 20px; color: #333; padding-top: 30px;">如有疑问或定制化需求，请联系我们</div>
				<div class="con left">
					<ul>
						<li class="company">深圳市浩天投资有限公司</li>
						<li class="email">13002459732</li>
						<li class="tel">wjgame@bjxiangtone.com</li>
						<li class="address">深圳市南山区高新园三道中科大厦21-B1</li>
					</ul>
				</div>
				<div class="con right" style="margin-top: 4%;">
					<a href="${pageContext.request.contextPath}/register.jsp"><img alt="" src="image/login.png"></a>            
					<p>将发送一封验证邮件到您的邮箱，此邮箱将作为登录用户名，欢迎点击按钮注册账号。</p>
				</div>
				<div class="clear_float"></div>
			</div>
			<!-- footer -->
			<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
		</div>
	</div>

	<script type="text/javascript">
		function active() {
			var sUserAgent = navigator.userAgent.toLowerCase();

			var bIsChrome = sUserAgent.match(/chrome/i) == "chrome";
			if (bIsChrome) {
				$(".section").addClass("hello1");
			}
			
			var bIsFirefox = sUserAgent.match(/firefox/i) == "firefox";
			if (bIsFirefox) {
				$(".section").addClass("hello");
			}
			
		}
		window.onload = active;
		active();
	</script>

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>

</body>
</html>