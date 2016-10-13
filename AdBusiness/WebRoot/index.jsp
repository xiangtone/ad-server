<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><%=company%>广告平台—</title>
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
.font:hover{
	color:#006aaa;
	font-size:18px;
}
.font{
	color:#555555;
	font-size:18px;
}
.font1{
	color:#006aaa;
	font-size:18px;
}

.span4 .img{
	float:left;
}
.thumbnails .span4 .bmbox font{
	color:#555;
	font-size:20px;
}

@media ( min-width : 168px) {
.bmbox{
	width:97.9%;
	height:38px;
	background-image:url('image/main_middle_36.png');
	background-size:100%;
	float:left;
	margin-top:-15.6%;
	z-index:1000;
	opacity:0.8;
	margin-left:1.35%;
	line-height:38px;
}
.page3 .container ul{
	padding:0 64px;
	
}

.page3 .container ul li{
	margin-left:68px;
	padding-top:67px;
	font-size:20px;
	color:#555;
	text-align:center;
	line-height:50px;
}
/* .page4 .content .none{
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
.footer-record{
	text-align:center;
	line-height:66px;
	font-size:20px;
	color:#434343;
}
}
@media ( min-width : 520px) {
.bmbox{
	width:97.9%;
	height:38px;
	background-image:url('image/main_middle_36.png');
	background-size:100%;
	float:left;
	margin-top:-15.6%;
	z-index:1000;
	opacity:0.8;
	margin-left:1.35%;
	line-height:38px;
}
.page3 .container ul{
	padding:0 64px;	
}

.page3 .container ul li{
	margin-left:68px;
	padding-top:67px;
	font-size:20px;
	color:#555;
	text-align:center;
	line-height:50px;
}
/* .page4 .content .none{
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
.footer-record{
	text-align:center;
	line-height:66px;
	font-size:20px;
	color:#434343;
}
}
@media ( min-width : 767px) {
.bmbox{
	width:97.9%;
	height:38px;
	background-image:url('image/main_middle_36.png');
	background-size:100%;
	float:left;
	margin-top:-17.8%;
	z-index:1000;
	opacity:0.8;
	margin-left:1.35%;
	line-height:38px;
}

.page3 .container{
	width:1000px;

}

.page3 .container ul{
	padding:292px 0px;
	
}
.page3 .container ul li{
	margin-left:76px;
	padding-top:67px;
	font-size:20px;
	color:#555;
	text-align:center;
	line-height:50px;
}
/* .page4 .content .none{
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
.footer-record{
	text-align:center;
	line-height:66px;
	font-size:20px;
	color:#434343;
}
}
@media ( min-width : 997px) {
.bmbox{
	width:97.9%;
	height:38px;
	background-image:url('image/main_middle_36.png');
	background-size:100%;
	float:left;
	margin-top:-16.6%;
	z-index:1000;
	opacity:0.8;
	margin-left:0.9%;
	line-height:38px;
}
.page2 .content ul li{
	width:23%;
	margin-left:80px;
	margin-bottom:0px;

}

.page3 .container ul{
	padding:277px 0px;
	
}
.page3 .container{
	width:1050px;

}
.page3 .container ul li{
	margin-left:43px;
	padding-top:67px;
	font-size:20px;
	color:#555;
	text-align:center;
	line-height:50px;
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

.footer-record{
	text-align:center;
	line-height:69px;
	font-size:20px;
	color:#434343;
	background-color:#fff;
}
#footer{
	margin-top:60px;
}
}
@media ( min-width : 1200px) {
.bmbox{
	width:97.9%;
	height:38px;
	background-image:url('image/main_middle_36.png');
	background-size:100%;
	float:left;
	margin-top:-15.6%;
	z-index:1000;
	opacity:0.8;
	margin-left:1.35%;
	line-height:38px;
}


.page3 .container ul{
	padding:0 64px;
	
}
.page3 .container{
	width:1170px;
	padding-top:20%;

}

.page3 .container ul li{
	margin-left:68px;
	padding-top:67px;
	font-size:20px;
	color:#555;
	text-align:center;
	line-height:50px;
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
.footer-record{
	text-align:center;
	line-height:55px;
	font-size:20px;
	color:#434343;
	background-color:#fff;
}
}


.page4 .content ul li {
	text-align:center;
	
}
.page2 .content h1 font{
	font-size:36px;color:#222;
}
.page2 .content p font{
	font-size:20px;color:#333;

}
.page2 .content ul .none{
	width:9%;
	margin-left:195px;

}
.page2 .content ul .none1 {
	width:23%;
	margin-left:80px;
	margin-bottom:0px;
	padding-top:26px;
}

.page2 .content p{
	padding-top:26px;
}
</style>

</head>
<body>
	
	<div  class="section" >
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
		<div class="page page1 " >
			<div  class="content">
				<a href="javascript:;"><img  alt="" src="image/start_03.png" style="margin-top:55%;"></a>
				<div style="clear: both;"></div>
			</div>
		</div>
		
		<!-- page2 -->
		<div class="page page2 " style="background-color:#fff;">
			<div  class="content" >
				<h1 >
					<font>支付通道</font>
				</h1>
					<p><font>满足主流支付通道和支付场景，为开发者提供一站式通道解决方案</font></p>
				<div  style="padding-top:20px;">
					<ul class="thumbnails">
						<li class="span4 none"><img alt="unionpay" src="image/unionpay_1.png" ><br><br>
							<font class="font1">银联支付</font></li>
						<li class="span4 none"><img alt="wechat" src="image/wechat_1.png" onmouseover="this.src='image/wechat_2.png'" onmouseout="this.src='image/wechat_1.png'"><br><br>
							<font class="font">微信支付</font></li>
						<li class="span4 none"><img alt="alipay" src="image/zhifubao_1.png" onmouseover="this.src='image/zhifubao_2.png'" onmouseout="this.src='image/zhifubao_1.png'"><br><br>
							<font class="font">支付宝支付</font></li>
					</ul>	
				</div>
				<ul class="thumbnails">
					<li class="span4 none1"><img class="img" src="image/main_17.png"><div class="bmbox"><font>游戏（Games）</font></div></li>
					<li class="span4 none1"><img class="img" src="image/main_19.png"><div class="bmbox"><font>教育（Education）</font></div></li>
					<li class="span4 none1"><img class="img" src="image/main_21.png"><div class="bmbox"><font>电商（E-Commerce）</font></div></li>
					<li class="span4 none1"><img class="img" src="image/main_26.png"><div class="bmbox"><font>金融（Finance）</font></div></li>
					<li class="span4 none1"><img class="img" src="image/main_27.png"><div class="bmbox"><font>零售（Retail）</font></div></li>
					<li class="span4 none1"><img class="img" src="image/main_28.png"><div class="bmbox"><font>其他（Others）</font></div></li>
				</ul>
				
				<!-- <div class="column">
					<h4>订阅支付</h4>
					<img src="image/icon_subscription.png">
					<p>
						会员订阅服务<br>自动扣费<br>重构用户关系
					</p>
					<a href="default_004.html">了解更多&gt;&gt;</a>
				</div>
				<div class="column">
					<h4>手机支付</h4>
					<img src="image/icon_mobile.png">
					<p>
						iOS／Android／HTML5 <br>微信公众号
					</p>
					<a href="default_005.html">了解更多&gt;&gt;</a>
				</div>
				<div class="column">
					<h4>PC支付</h4>
					<img src="image/icon_pc.png">
					<p>
						全套PC网页支付解决方案<br>多渠道支付<br>网页和扫码
					</p>
					<a href="default_005.html#pc">了解更多&gt;&gt;</a>
				</div>
				<div class="column">
					<h4>线下扫码支付</h4>
					<img src="image/icon_qr.png">
					<p>
						主动扫码支付<br>被动扫码支付<br>支付宝和微信支付双渠道
					</p>
					<a href="default_013.html">了解更多&gt;&gt;</a>
				</div>
				<div class="column">
					<h4>企业打款</h4>
					<img src="image/icon_transfer.png">
					<p>
						支持商户向企业/个人帐户打款<br>到账周期短<br>费率低
					</p>
					<a href="default_007.html">了解更多&gt;&gt;</a>
				</div> -->
				<div class="clear_float"></div>
			</div>
		</div>
		
		<!-- page3 -->
		<div class="page page3 ">
			<div  class="content">
				<div>
					<font style="font-size:36px;color:#222;">快速接入</font>
				</div>
				<div style="font-size:20px;color:#333;padding-top:25px;">4个步骤一站式接入，简单易用操作从此触手可及</div>
				
				<!-- <div class="users">
					<span><img src="image/icon_wx.png"></span> <span><img
						src="image/icon_ali.png"></span> <span><img src="image/icon_yb.png"></span>
					<span class="nth4"><img src="image/icon_yl.png"></span> <span><img
						src="image/icon_bd.png"></span> <span><img src="image/icon_paypal.png"></span>
					<span><img src="image/icon_jd.png"></span> <span class="nth4"><img
						src="image/icon_kq.png"></span> <span><img
						src="image/icon_ccmastercard.png"></span> <span><img
						src="image/icon_visa.png"></span> 
						<span class="nth4"> <img
						="" src="image/icon_ms.png"></span>
					<div class="clear_float"></div>
				</div> -->
				
				
				
			</div>
			<div class="container">
			<ul class="thumbnails">
					<li class="span3"><img class="img" src="image/main_icon_03.png"><br><br>
					商户注册</li>
					<li class="span3"><img class="img" src="image/main_icon_05.png"><br><br>
					线上测试</li>
					<li class="span3"><img class="img" src="image/main_icon_07.png"><br><br>
					参数配置</li>
					<li class="span3"><img class="img" src="image/main_icon_09.png"><br><br>
					完成接入</li>
				</ul>
				
				
		</div>
				
			
		</div>
		
		
		<!-- page4 -->
		<div class="page page4 " style="background-color:#fff;">
			<div  class="content" >
				<div>
					<font style="font-size:36px;color:#222;">产品优势</font>
				</div>
				<div style="font-size:20px;color:#333;padding-top:30px;">一次对接、一个账单、一个平台，应对任何行业和场景的商业需求</div>
				<div style="margin-top:6%;width:100%;height:auto;">
				<img alt="" src="image/chanpin.png">
				</div>
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
						

				
				
				
				<!-- <dl class="efficiency">
					<dt>
						高效<br>
						<i></i>
					</dt>
					<dd>
						秒支付助你秒开发，半个小时快速接入支付功能，缩短2~4个人月开发周期...<br>
						<a href="default_014.html">了解更多&gt;&gt;</a>
					</dd>
				</dl>
				<dl class="stable">
					<dt>
						稳定<br>
						<i></i>
					</dt>
					<dd>
						全球10余个城市的分布式云平台，5重数据备份，自动负载均衡，支持突发的高并发流量...<br>
						<a href="default_014.html#efficiency">了解更多&gt;&gt;</a>
					</dd>
				</dl>
				<dl class="safety">
					<dt>
						安全<br>
						<i></i>
					</dt>
					<dd>
						HTTPS传输加密，REST API调用数字签名验证，ACL 权限控制，严格保护客户数据的安全和隐私...<br>
						<a href="default_014.html#safety">了解更多&gt;&gt;</a>
					</dd>
				</dl>
				<dl class="fast">
					<dt>
						快速<br>
						<i></i>
					</dt>
					<dd>
						基于CDN技术的最近服务节点，百毫秒级的付款响应时间，7*24小时随时随地的秒级应急响应保障...<br>
						<a href="default_014.html#fast">了解更多&gt;&gt;</a>
					</dd>
				</dl> -->
				<div class="clear_float"></div>
			</div>
			<!-- <div class="container" style="background:url(image/chanpin.png) no-repeat;min-height:885px;margin-top:-553px;"></div> -->
			 
		</div>
		
		<!-- page5 -->
		<div class="page page6 " style="background:url(image/foot_bg_02.png) no-repeat;">
			<div  class="content" >
				<div >
					<font style="font-size:36px;color:#222;">联系我们</font>
				</div>
				<div style="font-size:20px;color:#333;padding-top:30px;">如有疑问或定制化需求，请联系我们</div>
				
				<div class="con left" style="margin-top: 4%;">
					<ul>
						<li class="company">深圳市浩天投资有限公司 </li>
						<li class="email">13002459732</li>
						<li class="tel">wjgame@bjxiangtone.com</li>
						<li class="address">
							深圳市南山区高新园三道中科大厦21-B1
						</li>
					</ul>
				</div>
				<div class="con right" style="margin-top: 4%;">
					
					<a href="default_010.html"><img alt="" src="image/login.png"></a>
					<!--                <div class="search"><input type="text" placeholder="请输入邮箱"><button>注册</button></div>-->
					<p>将发送一封验证邮件到您的邮箱，此邮箱将作为登录用户名，欢迎点击按钮注册账号。</p>
				</div>
				<div class="clear_float"></div>
			</div>
			
			<!-- footer -->

				<jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>	
				<p class="footer-record">浩天支付&copy;2016 - 粤ICP备12058124号</p>
			</div>

		</div>
		

<script type="text/javascript">
	function active(){
		var sUserAgent = navigator.userAgent.toLowerCase();
		
		
		var bIsChrome = sUserAgent.match(/chrome/i) == "chrome";
		if(bIsChrome){
			$(".section").addClass("hello1");
		}
	
		var bIsFirefox = sUserAgent.match(/firefox/i) == "firefox";
		if(bIsFirefox){
			$(".section").addClass("hello");
		}	
	}
	window.onload=active;active();
</script>

	<!-- <div style="top: 256px;" class="tag-list">
		<ul>
			<li class="on"><i></i>
			<div></div></li>
			<li><i></i>
			<div></div></li>
			<li><i></i>
			<div></div></li>
			<li><i></i>
			<div></div></li>
			<li><i></i>
			<div></div></li>
			<li><i></i></li>
		</ul>
	</div> -->
	<!--<script src="//meiqia.com/js/mechat.js?unitid=5563e1b54eae35e57c000004" charset="UTF-8" async="async"></script>-->
	<!-- Start of KF5 supportbox script -->
	<!-- <script src="js/main.js" id="kf5-provide-supportBox"
		kf5-domain="beecloud.kf5.com"></script>
	End of KF5 supportbox script
	<script type="text/javascript">
        window.bcRegisterTime = "1473240665";
    </script> -->
 	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	


	<!-- <iframe style="display: none;" title="" src="index_3.html"></iframe>
	<link href="css/btn.css" type="text/css" rel="styleSheet">
	<div id="kf5-support-123456789"
		class="kf5-support-123456789 kf5-right kf5-style1">
		<div id="kf5-support-btn" class="kf5-support-btn"
			style="background: rgb(255, 108, 44) none repeat scroll 0% 0%;">
			<img src="image/kf5-sbox-icon1.png" alt=""><span>帮助</span>
		</div>
		<div id="kf5-support-block" class="kf5-support kf5-support-show "
			style="display: none;">
			<a id="kf5-close-btn" class="kf5-close" title="关闭"><i
				class="kf5-icon-clear"></i></a>
			<div style="display: none;" id="kf5-loading" class="kf5-loading">
			</div>
			<iframe src="index_5.html" id="kf5-widget-iframe" frameborder="0"></iframe>
		</div>
	</div> -->
	<%-- <div id="hero">
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
										<p><%=company%>积分墙是在APP内展示各种广告任务供用户完成并获得虚拟币的页面广告，包括安装使用应用，填表注册等。用户完成任务获取虚拟币的同时APP开发者也随即获得收益</p>
										<a href="${pageContext.request.contextPath}/developer.jsp" class="btn btn-success btn-large">了解更多</a>
									</div>
								</div>
							</div>
							<div class="item slide2">
								<div class="row">
									<div class="span4 animated fadeInUpBig">
										<h1>插屏广告</h1>
										<p><%=company%>插屏在弹出时机和场景上均可自由设置。采用最新HTML5技术，支持做种展示形式，还有更多丰富的广告内容</p>
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
						<p class="description" style="color: red">因系统升级，拟于2014年12月31日对本年度全部媒介费用进行清算。</p>
					</a>
					<a href="${pageContext.request.contextPath}/notice.jsp" class="btn btn-default">更多</a>
				</div>
				<div class="span4 feature"></div>
                <div class="span4 feature">
					<h3><i class="i_movil"></i>下载中心<hr class="visible-desktop" /></h3>
					<p class="description">
						<a href="<%=sdk_android_url+sdk_android_package_name%>" target="_blank"><img src="${pageContext.request.contextPath}/img/android-H.png" onMouseOver="this.src='${pageContext.request.contextPath}/img/android-p.png';" onMouseOut="this.src='${pageContext.request.contextPath}/img/android-H.png';"></a>
					</p>
					<p class="description">
						<a href="<%=sdk_ios_url%>" target="_blank"><img src="${pageContext.request.contextPath}/img/iOS-H.png" onMouseOver="this.src='${pageContext.request.contextPath}/img/iOS-p.png';" onMouseOut="this.src='${pageContext.request.contextPath}/img/iOS-H.png';"></a>
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
						 电话：<%=contact_us_phone%><br />
						 地址：<%=contact_us_add%><br />
						 邮编：<%=zip_code%><br />
</p>
<!-- </p> -->
					<!--<p class="description">
						<p class="description" style="font-size:18px">
							开发者联系方式   -->
							<!-- <span style="font-size:12px;float:right">联系电话：12345678</span> -->
							<!--<p class="description">
								<span style="padding:0 30px 18px 0">媒介盖梆：<a href="tencent://message/?uin=1977975161&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
								<span>媒介小雪：<a href="tencent://message/?uin=1014300145&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
							</p>
							<p class="description">
								<span style="padding:0 30px 18px 0">媒介小丽：<a href="tencent://message/?uin=745137433&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
								<span>媒介小拉：<a href="#"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
		                    </p>
	                    </p>
						<p class="description" style="font-size:18px">
							广告主联系方式-->
							<!-- <span style="font-size:12px;float:right">联系电话：12345678</span>   -->                
						<!--<p class="description">
							<span style="padding:0 30px 18px 0">商务海霞：<a href="tencent://message/?uin=1825998992&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
							<span>商务安冬：<a href="tencent://message/?uin=2215006476&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>
						</p>
						<p class="description">
							<span style="padding:0 30px 18px 0">商务晶晶：<a href="tencent://message/?uin=2066814324&amp;Site=www.adwaler.cn&amp;Menu=yes"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span>-->
							<!-- <span>商务小拉：<a href="#"><img src="${pageContext.request.contextPath}/img/qq.png"></a></span> -->
<!-- 						</p> -->
<!-- 					</p> -->
					<a href="${pageContext.request.contextPath}/contactus.jsp" class="btn btn-default">更多</a>
				</div>
			</div>
		</div>
	</div>
	<div id="testimonials">
		<div class="container">
			<div class="controls"><hr /></div>
		</div>
	</div> --%>
	
</body>
</html>