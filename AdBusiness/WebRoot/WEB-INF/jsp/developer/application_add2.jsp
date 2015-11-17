<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>行云广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/fuelux.wizard.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ZeroClipboard.js"></script>
		<script type="text/javascript">
			var clip = null;
			function init() {
				clip = new ZeroClipboard.Client();
				clip.setHandCursor( true );
				clip.addEventListener('load', my_load);
				clip.addEventListener('mouseOver', my_mouse_over);
				clip.addEventListener('complete', my_complete);
				clip.glue('d_clip_button');
			}
			function my_load(client) {
				
			}
			function my_mouse_over(client) {
				clip.setText($("#appKey").val());
			}
			function my_complete(client, text) {
				alert("AppKey已经复制到剪切板!!!");
			}
			function downSdk(){
				var os= '${vo.os}';
				if(os=='android'){
					var ids = "${pageIds}";
					$.ajax({
						url:'${pageContext.request.contextPath}/getAndroidSDKUri.action?ids='+ids,
						type:'POST',
						data:'',
						dataType:'text',
						success:function(data){
							var url='http://res.adwalker.cn/adres/androidSDK/'+data;
							download(url);
						}
					});
				} else if(os=='ios') {
					var url='http://res.adwalker.cn/iOSSDK/iOSSDK/xingyun-iOS-sdk.zip';
					download(url);
				}
			}
			function download(url){
				var a = document.createElement("a");
				if(!a.click) {
					window.location = url;
					return;
				}
				a.setAttribute("href", url);
				a.style.display = "none";
				document.body.appendChild(a);
				a.click();
			}
		</script>
	</head>
	<body onLoad="init()">
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<!-- main container -->
		<div class="content">
			<!-- settings changer -->
			<div id="pad-wrapper" class="user-profile">
				<!-- header -->
				<div class="row header">
					<div class="col-md-12">
						<h3>添加应用</h3>
					</div>
				</div>
				<div class="row form-wrapper">
					<div class="col-md-9 with-sidebar">
						<div class="container">
							<div id="fuelux-wizard" class="wizard row">
								<ul class="wizard-steps">
									<li class="active"><span class="step">1</span><span class="title">基本信息</span></li>
									<li class="active"><span class="step">2</span><span class="title">获取ID下载SDK</span></li>
									<li><span class="step">3</span> <span class="title">上传应用</span></li>
								</ul>
							</div>
							<div class="col-md-12 field-box">
								<label>开发者ID：</label>
								<p>${dev.id}</p>
							</div>
							<div class="col-md-12 field-box">
								<label>应用ID：</label>
								<p>${vo.id}</p>
							</div>
							<div class="col-md-12 field-box">
								<label>AppKey：</label><input class="form-control" name="appKey" id="appKey" type="text" value="${vo.appKey}" onChange="clip.setText(this.value)" readonly="readonly"/>
								<button id="d_clip_button" class="btn-glow primary" type="button" title="点击复制到粘贴板">复制</button>
								<span class="alert-a">*此为应用获取广告的唯一标识</span>
							</div>
							<div class="wizard-actions">
								<a href="toAddApplication1.action?appId=${vo.id}"><button type="button" class="btn-glow primary btn-prev"><i class="icon-chevron-left"></i>上一步</button></a>
								<a href="toAddApplication3.action?appId=${vo.id}"><button type="button" class="btn-glow primary btn-next" data-last="Finish">下一步 <i class="icon-chevron-right"></i></button></a>
							</div>
							<div class="sdk-download clearfix">
								<div class="sdk-download-android pull-left">
									<h2 class="sdk-title"><c:if test="${vo.os=='android'}">Android</c:if><c:if test="${vo.os=='ios'}">iOS</c:if> SDK</h2>
									<ul class="sdk-ul">
										<li class="sdk-ul-item"><span class="dib">有积分广告业务：</span><span>&nbsp;积分墙</span></li>
										<li class="sdk-ul-item"><span class="dib">无积分广告业务：</span><span>&nbsp;推荐墙</span><span>&nbsp;插屏</span></li>
										<li class="sdk-ul-item"><span class="dib">版本号：</span><span>V2.1.0</span></li>
										<li class="sdk-ul-item"><span class="dib">SDK在线文档：</span>
											<c:if test="${vo.os=='android'}">
												<a class="switch-changelog" href="http://res.adwalker.cn/adres/developersAndroidExplain.pdf" target="_blank">查看详情</a>
											</c:if>
											<c:if test="${vo.os=='ios'}">
												<a class="switch-changelog" href="http://res.adwalker.cn/adres/developersIOSExplain.pdf" target="_blank">查看详情</a>
											</c:if>
										</li>
									</ul>
									<div class="wrap-sdk-btn">
										<a download="" class="btn-glow success" href="#">
											<div class="sdk-btn-content">
												<i class="icon-android icon-android-sdk"></i><span class="sdk-version-name" onclick="downSdk();">下载 <c:if test="${vo.os=='android'}">Android</c:if><c:if test="${vo.os=='ios'}">iOS</c:if>&nbsp;SDK</span>
											</div>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3_1 col-xs-12 address pull-right">
						<h6>
							<div class="alert alert-info hidden-tablet">
								<i class="icon-lightbulb"></i>常见问题
							</div>
						</h6>
						<ul>
							<li class="ico-li"><a href="#">怎么下载SDK？</a></li>
							<li class="ico-li"><a href="#">怎么上传审核应用？</a></li>
							<li class="ico-li"><a href="#">如何添加新应用？</a></li>
							<li class="ico-li"><a href="#">审核一般需要多长时间？</a></li>
							<li class="ico-li"><a href="#">财务信息可以不是本人吗？</a></li>
							<li class="ico-li"><a href="#">怎么查看账户财务状态？</a></li>
							<li class="ico-li"><a href="#">开发者提现须知有哪些？</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu2_1");
		</script>
	</body>
</html>