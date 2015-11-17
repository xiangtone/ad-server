<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			values : [ 40, 170 ],
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
<div id="sidebar-nav">
	<ul id="dashboard-menu">
		<li id="li3">
			<a class="dropdown-toggle" href="#"><i class="icon-edit"></i><span>数据中心</span><i class="icon-chevron-down"></i></a>
			<ul id="ul3" class="submenu">
			<c:choose>
			<c:when test="${userChannels.channe_mode =='0'}">
			<li><a href="reportChannelProxy.action;" url="reportChannelProxy.action" id="menu3_2">代理渠道数据统计</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="reportChannelSdk.action;" url="reportChannelSdk.action" id="menu3_2">SDK渠道数据统计</a></li>		
			</c:otherwise>
			</c:choose>
			</ul>
		</li>
		<li id="li5">
			<a class="dropdown-toggle" href="#"><i class="icon-code-fork"></i><span>个人中心</span><i class="icon-chevron-down"></i></a>
			<ul id="ul5" class="submenu">
				<li><a href="toUpdateChannelPass.action;" url="toUpdateChannelPass.action" id="menu5_4">修改密码</a></li>
			</ul>
		</li>
	</ul>
</div>