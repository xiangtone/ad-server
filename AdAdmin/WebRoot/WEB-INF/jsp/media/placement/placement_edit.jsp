<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告主发布广告</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	function initButton(){
		dg.addBtn("save","保存",function(){
			submit_form();
		});
	}
	
	function submit_form(){
		$("#addPubAct").ajaxSubmit(function(data){
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
	}
	
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
			$('.subblock_2').show().siblings('div').hide();
			remove();
		});
	});
	
	function remove(){
		dg.removeBtn("save");
		dg.removeBtn("audit");
	}

	
	$(document).ready(function(){
		initButton();
		var page_type='${page_type}';
		   if(page_type){
			   if(page_type.indexOf(",")){
				   var arr=page_type.split(",");
				   for(var i=0;i<arr.length;i++){
					   $("#pagetype_"+arr[i]).attr("checked","checked");
					   $("#pagetype_"+arr[i]).attr("readonly","readonly");
					   $("#pagetype_"+arr[i]).click(function(){
						   //覆盖系统事件，模拟只读效果
						   return false;
					   });
				   }
			   }else{
				   $("#pagetype_"+page_type).attr("checked","checked");
				   $("#pagetype_"+page_type).attr("readonly","readonly");
				   $("#pagetype_"+page_type).click(function(){
					   return false; //覆盖系统事件，模拟只读效果
				   });
			   }
		   }
	});
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
				<div class="content_right admin_right">
				<form id="addPubAct" name="addPubAct" action="manage!modifyPlacement.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" id="adv_id" value="${placement_id}" />
					<input type="hidden" name="status" id="placement_status" value="-20" />
				 <ul class="tab_block2">    
      					<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">投放</span></a></li>
      					<li>
      						<a href="javascript:void(0)" class="sub_2">
      							<span>历史投放</span>
      						</a>
      					</li>
    				</ul>
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<iframe frameborder="0" height="720" width="940" src="manage!appPlacement.do?app_id=${app_id}"></iframe>
				</div>
				<div class="subblock_2" style="display:none;border: 1px solid #9dbaff;float: left;width: 1866px;border-top: 0;height: 612px;">
					<iframe frameborder="0" height="720" width="920" src="app/historyAd.do?app_id=${app_id}"></iframe>
    			</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>