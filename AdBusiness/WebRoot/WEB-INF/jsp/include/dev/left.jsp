<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$(".knob").knob();
		$(".slider-sample1").slider({
			value : 100,
			min : 1,
			max : 500
		});
		$(".slider-sample2").slider({
			range : "min",
			value : 130,
			min : 1,
			max : 500
		});
		$(".slider-sample3").slider({
			range : true,
			min : 0,
			max : 500,
			values : [ 40, 170 ]
		});
		
		function showTooltip(x, y, contents) {
			$('<div id="tooltip">' + contents + '</div>').css({
				position : 'absolute',
				display : 'none',
				top : y - 30,
				left : x - 50,
				color : "#fff",
				padding : '2px 5px',
				'border-radius' : '6px',
				'background-color' : '#000',
				opacity : 0.80
			}).appendTo("body").fadeIn(200);
		}
		var previousPoint = null;
		$("#statsChart").bind("plothover", function(event, pos, item) {
			if (item) {
				if (previousPoint != item.dataIndex) {
					previousPoint = item.dataIndex;
					$("#tooltip").remove();
					var x = item.datapoint[0].toFixed(0), y = item.datapoint[1].toFixed(0);
					var month = item.series.xaxis.ticks[item.dataIndex].label;
					showTooltip(item.pageX, item.pageY, item.series.label + " of " + month + ": " + y);
				}
			} else {
				$("#tooltip").remove();
				previousPoint = null;
			}
		});
	});
	function selectedMenu(menu) {
		if(menu == "menu1_1") {
			$("#li1").attr("class", "active");
		} else {
			$("#"+menu).parent().parent().parent().attr("class", "active submenu");
			$("#"+menu).parent().parent().attr("class", "active submenu");
			$("#"+menu).attr("class", "active");
		}
	}
</script>
<div id="sidebar-nav" style="float: left;display: block;">
	<ul id="dashboard-menu">
		<li id="li1">
			<div class="pointer">
				<div class="arrow"></div>
				<div class="arrow_border"></div>
			</div>
			<a href="developerIndex.action" id="menu1_1"><i class="icon-home"></i><span>首页</span></a>
		</li>
		<li id="li2">
			<a class="dropdown-toggle" href="#"><i class="icon-group"></i><span>应用中心</span><i class="icon-chevron-down"></i></a>
			<ul id="ul2" class="submenu">
				<li><a href="toAddApplication1.action" id="menu2_1">添加应用</a></li>
				<li><a href="applicationList.action" id="menu2_2">应用列表</a></li>
				<%--<li><a href="adSeting.action" id="menu2_3">广告设置</a></li>--%>
				<li><a href="toDownloadSDK.action" id="menu2_4">SDK下载</a></li>
			</ul>
		</li>
		<li id="li3">
			<a class="dropdown-toggle" href="#"><i class="icon-edit"></i><span>数据中心</span><i class="icon-chevron-down"></i></a>
			<ul id="ul3" class="submenu">
				<li><a href="reportActualTime.action" id="menu3_1">实时统计</a></li>
				<li><a href="reportHistorical.action" id="menu3_2">历史统计</a></li>
			</ul>
		</li>
		<li id="li4">
			<a class="dropdown-toggle" href="#"><i class="icon-th-large"></i><span>财务中心</span><i class="icon-chevron-down"></i></a>
			<ul id="ul4" class="submenu">
				<li><a href="accountInfo.action" id="menu4_1">账户信息</a></li>
				<li><a href="toDeveloperApplyMoney.action" id="menu4_2">提款申请</a></li>
				<li><a href="developerAccount.action" id="menu4_3">提款记录</a></li>
			</ul>
		</li>
		<li id="li5">
			<a class="dropdown-toggle" href="#"><i class="icon-code-fork"></i><span>个人中心</span><i class="icon-chevron-down"></i></a>
			<ul id="ul5" class="submenu">
				<li><a href="toUpdateInfo.action" id="menu5_1">基本信息</a></li>
				<li><a href="toUpdateFnancialInfo.action" id="menu5_2">财务信息</a></li>
				<li><a href="toInteriorMailAllList.action" id="menu5_3">站内信</a></li>
				<li><a href="toUpdatePassword.action" id="menu5_4">修改密码</a></li>
			</ul>
		</li>
	</ul>
</div>