<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>赚钱列表</title>
	<link rel="stylesheet" type="text/css"	href="../ioswall/weixinwall.css?v=20140618" />
	<link rel="stylesheet" type="text/css"	href="../ioswall/loading.css?v=201406128" />
	<script src="../ioswall/global.js?v=2014061338"></script>
	<script src="../ioswall/weixinwall.js?v=11zaaw8" charset="utf-8"></script>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		
		.dg1{
			background-color:#fff;
			width: 293px;
			height:300px;
			z-index: 10000;
			position: absolute;
			top: 110px;
			left: 0;
			border-radius:18px;
			display: none;
			opacity: 1;
		}
		
		.dg1 .c{
				display: block;
				position:absolute;
				height: 30px;
				width: 30px;
				right:0px;
				background-image: url(../ioswall/c.png);
				background-repeat: no-repeat;
				z-index: 10001;
		}
		.dg2{
			background-color:#fff;
			width: 293px;
			height:330px;
			z-index: 10000;
			position: absolute;
			top: 240px;
			left: 0;
			border-radius:18px;
			opacity: 1;
			display: none;
		}
		.dg2 .c{
				display: block;
				position:absolute;
				height: 30px;
				width: 30px;
				right:0px;
				background-image: url(../ioswall/c.png);
				background-repeat: no-repeat;
				z-index: 10001;
		}
		
		.dg2 .keyword{
			display: block;
			position: absolute;
			top: 115px;
			z-index: 10001;
			width: 100%;
		}
	</style>
	<script type="text/javascript">
		var userid='${userId}';
		var appkey="${appkey}";
		window.onload=function(){
		    requestData(pageSize,appkey,'${userId}');
		 };
		 function dg_c1() {
			document.getElementById("dg1").style.display = "none";
			document.getElementById("b").style.display = "none";
		}
		 
		 function dg_c2() {
				document.getElementById("dg2").style.display = "none";
				document.getElementById("b").style.display = "none";
			}
		 
		 var d_url="";
		 var token='${t}';
		function download_res(){
			window.location.href="download.do?userId=${userId}&appkey=${appkey}&t="+token+"&download_url="+d_url;
		}
		
		function keywordcopy(){
			window.location.href="download.do?userId=${userId}&appkey=${appkey}&t="+token+"&download_url=${p_url}";
			//window.location.href='/AdService/haha.jsp'
			
		}
	</script>
	
</head>
<body style="overflow: hidden;">
	<!-- 头 部分-->
	<div class="head_div" style="display: none;">
		<div class="headertitle">赚钱列表</div>
		<p  class="head_back" id="head_back" onclick="homeback(this)">&lt;返回</p>
	</div>
	<div style="background:#e5e5e5;color:#535353;">
		<span style="display: block;font-size: 14px;line-height: 24px;margin-top: 10px;">试玩任务说明</span>
		<span style="margin-left: 20px;margin-bottom: 10px;font-size: 12px;line-height: 18px;display: block;">
			1.请按照任务要求完成下载先锋或注册任务。<br>
			2.做任务时请保持试用先锋APP后台运行。<br>
			3.返回此界面刷新，任务消失即可获得奖金。<br>
			4.之前已安装过的任务，再次试玩没有奖励。<br>
		</span>
	</div>
	<!-- 奖励任务部分 -->
	<div class="mbody">
		<div class="wall_list" id="wall_list">
			<div class="divider" id="divider0"></div>
		</div>
		<div class="itemtitle1" id="itemtitle1" style="display: none">
			暂无可赚钱应用...</div>
	</div>
	<div class="spinner"  id="spinner" >
	</div>
	<div id="b" ontouchstart ="return false;" style="background-color: #333333;width: 100%;height: 100%;display: block;position: absolute;top: 0px;opacity: 0.7;display: none;">
	</div>
	<div id="dg1" class="dg1" ontouchstart ="return false;">
		<div class="c" style="cursor: pointer;" onclick="dg_c1();" ontouchstart="dg_c1();"></div>
		<div class="box" id="box0" style="margin-top: 12px;">
			<div class="boxLM" id="boxLM0">
				<div class="boxL" id="boxL">
					<img id="logo" class="logo" src="${vo.icon_url}">
				</div>
				<div class="boxM" id="boxM">
					<div id="ad_name" class="title">cccc</div>
					<div  class="desc" id="slogan"></div>
				</div>
			</div>
		</div>
		<div class="divider_up"></div>
		<div style="background-color: #fff;line-height: 30px; padding-left: 30px;">
			<span style="font-size: 12px;">赚取条件：</span><span style="font-size: 12px;text-align: center;vertical-align: middle;color:#EB6100;">按照以下步骤完成新手条件</span>
			<span id="score_desc" style="display: block;width: 100%;line-height: 20px;font-size: 16px;margin-top: 20px;height: 80px;">
			</span>
		</div>
		<div style="background-color:#EB6100;height: 50px;width:150px;text-align: center;margin-left:60px;margin-top: 20px;">
			<a style="text-decoration: none;" href="javascript:void(0);" onclick="download_res();" ontouchstart="download_res();">
				<span style="color: white;line-height: 50px;cursor: pointer;" id="score_unit">立即赚取1.80元</span>
			</a>
		</div>
	</div>
	<div id="dg2" class="dg2">
		<div class="box" id="box0" style="margin-top:20px;">
			<div class="boxLM" id="boxLM0">
				<div class="boxL" id="boxL">
					<img id="logo_b" class="logo" src="${vo.icon_url}">
				</div>
				<div class="boxM" id="boxM">
					<div id="ad_name_b" class="title">cccc</div>
					<div id="desc0" class="desc">下载注册，试玩两分钟获取积分</div>
				</div>
			</div>
		</div>
		<div class="divider_up"></div>
		<div style="background-color: #fff;line-height: 30px; padding-left: 30px;">
			<span style="font-size: 14px;">赚取条件：</span>
			<span style="font-size: 14px;text-align: center;vertical-align: middle;color:#EB6100;">按照以下步骤完成以下任务</span>
		</div>
		<div style="width: 100%;height: 50px;left: 50px;display: block;">
			<img alt="" src="../ioswall/l_b.png" style="width: 100%;">
		</div>
		<div style="width: 100%;height: 138px;">
				<span style="width: 290px;height: 80px;display: block;margin-left: 30px;padding-top: 20px;">
					<span style="font-size: 14px;color: #EF8133;">任务步骤新手教程：</span><br>
					<span style="font-size: 12px;line-height: 20px;" id="weixin_desc">
					<!--  
						1.在APP Store搜索关键词“XX”<br>
						2.“XX”排在第10位左右<br>
						3.首次下载，注册账户<br>
						4.试用3分钟以上<br>
						-->
					</span>
				</span>
		</div>
		<HR style="border:1 dashed #987cb9" width="100%" color=#987cb9 SIZE=1 />
		<div style="height: 50px;width:150px;text-align: center;margin-left:60px;">
			<span style="color: #EB6100;font-size: 12px;">复制关键字开始赚取奖励吧</span>
		</div>
		<!-- 最外层 加一个div 别的东西不能复制，取消按钮在过滤层上边-->
		<div style="height:400px;width: 293px;position: absolute;top: 0px;border-radius:18px;">
			<div class="c" style="cursor: pointer;" onclick="dg_c2();"></div>
			<div class="keyword">
				<span id="code_b" style="margin-left:auto;margin-right:auto;height: 50px;width: 140px;display: block;text-align: center;line-height: 50px;font-size: 10px;" oncopy="keywordcopy();">
				</span>
			</div>
		</div>
		
	</div>
</body>
	<script type="text/javascript">
		//alert(+"----"+document.body.clientHeight);
		//位置=(屏幕宽度-div宽度)/2
		document.getElementById("dg1").style.left=(document.body.clientWidth-293)/2+"px";
		document.getElementById("dg1").style.top=(window.screen.availHeight-300)/2+"px";
		
		
		document.getElementById("dg2").style.left=(document.body.clientWidth-293)/2+"px";
		document.getElementById("dg2").style.top=(window.screen.availHeight-400)/2+"px";
		document.body.style.overflow="hidden";
		
	</script>
</html>