<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>邀请注册</title>
		<script type="text/javascript" charset="utf-8">
			function pupopen() {
				document.getElementById("guidetwo").style.display = "block";
				document.getElementById("guideone").style.display = "none";
			}
			
			function dg_c() {
				document.getElementById("guidetwo").style.display = "none";
				document.getElementById("guideone").style.display = "block";
			}
			function pupclose() {
				window.location.href='download.do?userId=${code}&appkey=${appkey}&t=${t}&download_url=${download_url}';
			}
			document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			    // 通过下面这个API隐藏右上角按钮
			    WeixinJSBridge.call('hideOptionMenu');
			});
		</script>
		<style type="text/css" media="screen">
			*{
				margin: 0;
				padding: 0;
			}
			#guidetwo {
				display: none;
			}

			#guideCode {
				width: 100%;
				height: 44px;
				margin-top: 156px;
				text-align: center;
				color: black;
				font-size: 20px;
			}
			#guideBut {
				width: 100%;
				height: 44px;
				margin-top: 130px;
				text-align: center;
				background-repeat: no-repeat;
				-webkit-background-size: 100% 100%;
			}

			#guidethree {
				width: 100%;
				display: none;
				height: 100%;
			
			}
			
			.dg{
				background-color: red;
				width: 293px;
				height:340px;
				z-index: 10000;
				position: absolute;
				top: 40px;
				left: 0;
				border-radius:18px;
				display: block;
			}
			.dg .c{
				display: block;
				position:absolute;
				height: 30px;
				width: 30px;
				right:0px;
				background-image: url(../ioswall/c.png);
				background-repeat: no-repeat;
				z-index: 10001;
			}
		</style>
	</head>
	<body style="width: 100%;height: 100%;background-color: #333333">
		<div style="width: 100%;height: 100%;" id="guideone">
			<div style="width:100%;height: 70px;position: absolute; opacity: 0.7;cursor: pointer;" onclick="pupopen();"></div>
			<img alt="" src="../ioswall/guideone.png" width="100%" />
		</div>
		<div style="height: 100%;width: 100%;" id="guidetwo">
			<img alt="" src="../ioswall/guidetwo.png" width="100%">
			<div class="dg" id="dg">
				<div class="c" style="cursor: pointer;" onclick="dg_c();">
				</div>
				<img alt="" src="../ioswall/bg.png" width="100%">
				<div style="opacity: 0.7;position: absolute;top:0px;left: 0px;height: 100%;width: 100%;" >
					<span style="position: absolute;top: 100px;left:71px;;text-align: center;vertical-align: middle;line-height: 50px;width: 150px;height: 50px;">
						${code}
					</span>
					<span style="height: 40px;width: 120px;display:block;position: absolute;top: 250px;left: 73px;" onclick="pupclose();">
						<img src="../ioswall/guideBut.png" style="cursor: pointer;height: 100%;width: 100%;"   />
					</span>
				</div>
				
			</div>
		</div>
	</body>
	<script type="text/javascript">
		//位置=(屏幕宽度-div宽度)/2
		document.getElementById("dg").style.left=(document.body.clientWidth-293)/2+"px";
	</script>
</html>

