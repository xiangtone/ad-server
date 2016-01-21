<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
	    <script src="${pageContext.request.contextPath}/js/fuelux.wizard.js"></script>
		<script type="text/javascript">
		function downSdk(){
			var ids="";
			var os= $("input[name='os']:checked").val();
				if($(this).attr("checked")==true) {
					checked = true;
				}
			if($("input:checkbox[name=pageType]:checked").val() == undefined) {
				alert("请选择广告形式!");
				return;
			}
			if(os=='android'){
				$("input:checkbox[name=pageType]:checked").each(function(){
					if(ids){
						ids=ids+","+$(this).val();
					}else{
						ids=$(this).val();
					}
				});
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
			}else if(os=='ios'){
				var url='http://res.adwalker.cn/adres/iOSSDK/xingyun-iOS-sdk.zip';
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
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="users-list">
				<div class="row header"><h3>SDK下载</h3></div>
				<div class="table-products section">
					<div class="row filter-block">
						<div class="ui-dropdown">选择平台：
							<input type="radio" id="osAndroid" name="os" value="android" checked="checked">&nbsp;Android</input>
							<input type="radio" id="osIOS" name="os" value="ios">&nbsp;iOS</input>
						</div>
					</div>
					<div class="row filter-block">
						<div class="ui-dropdown">广告形式：
							<input type="checkbox" value="0" name="pageType">&nbsp;积分墙</input>
							<input type="checkbox" value="1" name="pageType">&nbsp;推荐墙</input>
							<input type="checkbox" value="5" name="pageType">&nbsp;插屏</input>
						</div>
					</div>
					<div><span class="alert-a">注意：<%=company%>SDK包是根据广告形式选择下载的，请根据所需广告形式下载相应SDK.</span></div>
				</div>
				<div class="col-md-12"><button type="button" class="btn-glow success btn-finish" onclick="downSdk();">下载SDK</button></div>
				<div class=" section">
	                <div class="col-md-12">
						<h4 class="clearfix">SDK版本介绍</h4>
						<div class="sdk-download-android pull-left">
							<h4>Android SDK</h4>
							<ul class="sdk-ul">
								<li class="sdk-ul-item">
									<span class="dib">版本号：</span>
									<span>V2.1.0</span>
								</li>
								<li class="sdk-ul-item">
									<span class="dib">更新时间：</span>
									<span>2014-03-31</span>
								</li>
								<li class="sdk-ul-item">
									<span class="dib">SDK在线文档：</span>
									<a class="dib" target="_blank" href="http://res.adwalker.cn/adres/developersAndroidExplain.pdf">查看详情</a>
								</li>
							</ul>
						</div>
	                    <div class="sdk-download-android pull-left">
							<h4>iOS SDK</h4>
							<ul class="sdk-ul">
								<li class="sdk-ul-item">
									<span class="dib">版本号：</span>
									<span>V2.3.0</span>
								</li>
								<li class="sdk-ul-item">
									<span class="dib">更新时间：</span>
									<span>2014-09-23</span>
								</li>
								<li class="sdk-ul-item">
									<span class="dib">SDK在线文档：</span>
									<a class="dib" target="_blank" href="http://res.adwalker.cn/adres/developersIOSExplain.pdf">查看详情</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu2_4");
		</script>
	</body>
</html>