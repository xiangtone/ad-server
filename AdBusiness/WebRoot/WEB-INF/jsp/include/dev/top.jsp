<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="adwalker" uri="/WEB-INF/tld/spring.tld"%>
<adwalker:springBean saveId="currentUser" springId="currentUser" />
<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		url:'${pageContext.request.contextPath}/getNoReadCount.action',
		type:'POST',
		data:'',
		dataType:'text',
		success:function(data){
			$(".count").text(data);
		}
	});
});
</script>
<style type="text/css">
<!--
.nav li{
	float: left;
}
-->
</style>
<header class="navbar navbar-inverse" role="banner" style="overflow: hidden;">
	<div class="navbar-header" style="color: green;width: 200px;overflow: hidden;float: left;" >
		<a class="navbar-brand" href="index.action"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
	</div>
	 <div style="display: block;width: 850px;float: right;">
	 	<ul class="nav navbar-nav hidden-xs">
			<li class="notification-dropdown hidden-xs hidden-sm">
				<a href="tencent://message/?uin=3048524833&Site=www.adwalker.cn&Menu=yes" target="_blank">
					<i class="icon-warning-signs1">在线客服<br/>QQ:3048524833</i>
				</a>
			</li>
			<li class="notification-dropdown hidden-xs hidden-sm">
				<a href="mailto:service@wifiwalker.com?subject=开发者反馈问题">
					<i class="icon-warning-signs1">客服邮箱<br/>service@wifiwalker.com</i>
				</a>
			</li>
			<li class="notification-dropdown hidden-xs hidden-sm">
				<a href="#" class="trigger">
					<i class="icon-warning-signs">客服电话<br/>010-84249150</i>
				</a>
			</li>
			<li class="notification-dropdown hidden-xs hidden-sm">
				<a href="${pageContext.request.contextPath}/toUpdateInfo.action">
					<i class="icon-warning-sign">欢迎，${currentUser.email}</i>
				</a>
			</li>
			<li class="notification-dropdown hidden-xs hidden-sm">
				<a href="${pageContext.request.contextPath}/toInteriorMailNotList.action">
					<i class="icon-envelope">消息</i>
					<span class="count">0</span>
				</a>
			</li>
			<li class="settings hidden-xs hidden-sm">
				<a href="${pageContext.request.contextPath}/loginOut.action">
					<i class="icon-share-alt">退出</i>
				</a>
			</li>
		</ul>
	 </div>
</header>