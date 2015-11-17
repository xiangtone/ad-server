<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
function adOnline(adID,status){
	var url = "${pageContext.request.contextPath}/manage!adOnline.do";
	if(status == 1){
		
		alert("已经是上线状态");
		return;
	}
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'adId='+adID,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
					refresh();
				}
			}
			
		}
		});
	}//取消操作
}


function bad_debt(adID){
	var url = "bad_debt.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+adID,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
					refresh();
				}
			}
			
		}
		});
	}//取消操作
}



/**
 * 查看修改信息
 *	
 **/
function info(id){
		var url = "balanceAccountInfo.do?id="+id;
		new $.dialog({
			title:'对账信息',
			page:url,
			width:575,
			height:500,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}




/**
 * 查看修改信息
 *	
 **/
function findUpdateAdv(adId){
		var url = "addPayments.do?id="+adId;
		new $.dialog({
			title:'回款',
			page:url,
			width:575,
			height:300,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}




function add_confirm_money(adId){
	var url = "add_confirm_money.do?id="+adId;
	new $.dialog({
		title:'回款',
		page:url,
		width:575,
		height:300,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}




function refresh(){
	$("#my_form").submit();	
}



/**
 * tb效果
 *	
 **/
$(document).ready(function (){
});		
</script>
<!-- <style>
table,td{ border:1px solid #000; border-collapse:collapse}
</style> -->
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div id="search_bar">
						<table width="100%">
							<tr>
								<td>
									<table width="100%">
										<tr>
											<td>广告主公司名称</td>
											<td>${vo.adv_name}</td>
										</tr>
										<tr>
											<td>结算周期</td>
											<td>
												<fmt:formatDate value="${vo.start_date}" pattern="yyyy-MM-dd"/>至
												<fmt:formatDate value="${vo.end_date}" pattern="yyyy-MM-dd"/>
											</td>
										</tr>
										<tr>
											<td>活动名称</td>
											<td>
												${vo.campaign_name}
											</td>
											<td>活动id</td>
											<td>
												${vo.campaign_id}
											</td>
										</tr>
										<tr>
											<td>效果数</td>
											<td>
												${vo.forecast_amount}
											</td>
											<td>总金额</td>
											<td>
												${vo.forecast_money}
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
							<tr class="tr_td">
								<th width="3%">序号</th>
								<th width="15%">发生日期</th>
								<th width="10%">效果数</th>
								<th width="10%">金额</th>
							</tr>
							<c:if test="${!empty vo.detailList}">
								<c:forEach items="${vo.detailList}" var="vo" varStatus="status">
									<tr>
										<td style="text-align: center;">
											${status.index+1}
										</td>
										<td>
											<fmt:formatDate value="${vo.static_date}" pattern="yyyy-MM-dd"/>
										</td>
										<td>
											${vo.confirm_amount}
										</td>
										<td>
											<fmt:formatNumber  type="number" pattern="###,###.##" value="${vo.confirm_money}"  minFractionDigits="2" maxFractionDigits="2" />
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>