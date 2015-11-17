<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台活动查询统计</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
var dg = frameElement.lhgDG;
function initButton(){
	dg.addBtn("save","提交审核",function(){
		$("#update_status_form").ajaxSubmit(function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("提交成功！！");
					dg.curWin.refresh();
				}else if(dataObj.status=-1){
					$("#error_tip").html(dataObj.error);
					$("#error_tip").css("display","block");
				}else{
					alert("操作失败，请重试！！");
				}
			}else{
				alert("操作失败，请重试！！");
			}
		});
	});
}



dg.SetCancelBtn("关闭",function(){
	dg.curWin.refresh();
	dg.cancel();
});


$(document).ready(function(){
	var dg = frameElement.lhgDG;
	dg.reDialogSize(888,585);
	dg.SetTitle("活动投放--媒体定向");
	dg.SetPosition(250,5);
	initButton();
});


//tab框事件---特殊处理从脚本文件中拿出来
$(function() {
	$('.tab_block2 li a').click(
			function() {
				$(this).parent('li').addClass('click').siblings('li')
						.removeClass('click');
				$(this).addClass('click').parent('li').siblings('li').children(
						'a').removeClass('click');
				$(this).children('span').addClass('click').parent('a').parent(
						'li').siblings('li').children('a').children('span')
						.removeClass('click');
			});
	$('.sub_1').click(function() {
		$('.subblock_1').show().siblings('div').hide();
		initButton();
	});

	$('.sub_2').click(function() {
		remove();
		$('.subblock_2').show().siblings('div').hide();
		
	});
	$('.sub_3').click(function() {
		$('.subblock_3').show().siblings('div').hide();
	});
	$('.sub_4').click(function() {
		$('.subblock_4').show().siblings('div').hide();
	});
	$('.sub_5').click(function() {
		$('.subblock_5').show().siblings('div').hide();
	});
});


function remove(){
	dg.removeBtn("save");
}
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right">
				 <ul class="tab_block2">    
      				<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">平台投放</span></a></li>
     				<li><a href="javascript:void(0)" class="sub_2"><span>渠道投放</span></a></li>
    			</ul>
				<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<iframe frameborder="0" height="720" width="940" src="manage!advertiseConfigureTemp.do?placement_id=${placement_id}"></iframe>
				</div>
				<div class="subblock_2" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<iframe frameborder="0" height="720" width="940" src="manage!advertiseConfigureChannel.do?placement_id=${placement_id}"></iframe>
				</div>	
				<form action="manage!updateStatus.do" method="post" id="update_status_form">
					<input type="hidden"  name="id" value="${placement_id}" />
					<input type="hidden"  name="status" value="1" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>
