<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="adwalker" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>行云广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
<script>
$(document).ready(function(){
	
 //Close button:
		$(".close").click(
			function () {
				$(this).parent().fadeTo(300, 0, function () { // Links with the class "close" will close parent
					$(this).slideUp(300);
				});
				return false;
			}
		);
		
});
//<!--表格隔行变色-->
$(document).ready(function() {
	$(".sortable tbody tr:odd").addClass("odd");
	
	if(	$("#os").val()){
		$("#os_select li").each(function(){
			if($(this).attr("val")==$("#os").val()){
				$("#os_sel_default").html($(this).html());
			}
		});
	}
	
});

function query_form(){
	$("#search_form").submit();
}
</script>
<!--复选框-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-hcheckbox.js"></script>
<script type="text/javascript">
$(function(){
	$('.chklist').hcheckbox();
	$('.radiolist').hradio();
	$('#btnOK').click(function(){
		var checkedValues = new Array();
		$('.chklist :checkbox').each(function(){
			if($(this).is(':checked'))
			{
				checkedValues.push($(this).val());
			}
		});

		alert(checkedValues.join(','));
		alert($('.radiolist :checked').val());
	});
});
</script>

<!-- the jScrollPane script 滚动条幅 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jscrollpane.min.js"></script>
<script type="text/javascript">
	$(function() {
	$('.scroll-pane').jScrollPane();
	$('.scroll-pane-arrows').jScrollPane({
	showArrows:true,horizontalGutter:10
	});
	});
	
</script>

	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/channel/left_channel.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="users-list">
				<div class="row header">
					<h3>渠道SDK数据统计</h3>
				</div>
				<div class="table-products section">
					<div class="row filter-block">
					<form action="reportChannelSdk.action" method="post" id="search_form" enctype="multipart/form-data">
					<div style="margin-bottom: 6px;height: 30px;width: 600px;overflow: hidden;">
						<div style="display: block;float: left;width: 200px;">
							<span style="display:inline;">应用名称</span>
							<input type="text" id="app_name" onclick="this.value=''" name="app_name" value="${bean.app_name}" class="txt" style="border-color: #f7a300;" />
						</div>			
					</div>
					统计时间&nbsp;
					<input class="Wdate" type="text" onclick="WdatePicker()" name="start_date" value="${bean.start_date}" />&nbsp;至&nbsp;
					<input class="Wdate" type="text" onclick="WdatePicker()" name="end_date" value="${bean.end_date}"/>&nbsp;&nbsp;
					<button class="btn-flat new-product"  onclick="query_form();">查询</button>
					</form>
					</div>
					<div class="row">
						<table class="table table-hover">
							<thead>
								<tr>
									<th class="col-md-3">序号</th>
									<th class="col-md-3">发生日期</th>
									<th class="col-md-3">应用名称</th>
									<th class="col-md-3">效果数</th>
									<th class="col-md-3">渠道收入</th>		
								</tr>
								
							</thead>
							<tbody>
							<tr>
								<td>汇总</td>
								<td>--</td>
								<td>--</td>
								<td>${s.sum_amount}</td>
								<td>￥<adwalker:formatNumber value="${s.sum_money_sdk}" maxFractionDigits="2"/></td>
							</tr>
								<c:if test="${!empty list}">
									<c:forEach items="${list}" var="vo" varStatus="status">
										<tr>
											<td>${status.index+1}</td>
											<td>${vo.static_date}</td>
											<td>${vo.app_name}</td>
											<td>${vo.confirm_amount}</td>
											<td>￥<adwalker:formatNumber value="${vo.confirm_money}" maxFractionDigits="2"/></td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty list}">
									<tr>
										<td align="center" colspan="13" style="text-align: center;">暂无记录！</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					${pageInfo}
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu3_2");
		</script>
	</body>
</html>