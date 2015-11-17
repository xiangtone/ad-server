<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>点击右上角</title>
		<script type="text/javascript" charset="utf-8">
			document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
			    // 通过下面这个API显示右上角按钮
			    WeixinJSBridge.call('showOptionMenu');
			});
		</script>
		<style type="text/css" media="screen">
			*{
				margin: 0;
				padding: 0;
			}
			#guidethree {
				width: 100%;
				height: 100%;
			}
		</style>
	</head>
	<body style="width: 100%;height: 100%;background-color: #333333">
		<div id="guidethree">
			<img alt="" src="../ioswall/guidethree.png" width="100%" />
		</div>
	</body>
</html>