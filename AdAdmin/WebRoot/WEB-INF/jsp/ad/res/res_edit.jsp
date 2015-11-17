<%@ page language="java" import="java.util.*,cn.adwalker.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<escore:springBean saveId="currentUser" springId="currentUser" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告主广告审核</title>
<link href="css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/ajaxupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	if(dg){
		dg.addBtn("save","保存",function(){
			if(validate_form()){
				$("#myform").ajaxSubmit(function(data){
					if(data){
						var dataObj=eval("("+data+")");//转换为json对象 
						if(dataObj.status==1){
							alert("操作成功！！");
							dg.curWin.refresh();
							dg.cancle();
						}else if(dataObj.status=-1){
							alert("修改失败，请重试！！");
							//dg.curWin.refresh();
						}else{
							alert("操作失败，请重试！！");
						}
					}else{
						alert("操作失败，请重试！！");
					}
				});
			}
			
		});	
	}
	
	function validate_form(){
		if(!validate_app()){
			return false;
		}
		if(has_type(0)){
			if(!validate_wall_score()){
				$('.sub_2').trigger("click");
				return false;
			}
			
		}
		if(has_type(1)){
			if(!validate_wall_list()){
				$('.sub_3').trigger("click");
				return false;
			}
			
		}
		if(has_type(4)){
			if(!validate_banner()){
				$('.sub_4').trigger("click");
				return false;
			}
		}
		if(has_type(5)){
			if(!validate_chartboost()){
				$('.sub_5').trigger("click");
				return false;
			}
		}
		return true;
	}
	
	
	function has_type(type){
		var page_str=('${page_type}');
		var arr=page_str.split(",");
		var b=false;
		for(var j=0;j<arr.length;j++){
			if(type==arr[j]){
				b=true;
				break;
			}
		}
		return b;
	}
	
	function validate_app(){
		if(!$("#iconimg_url").val()){
			alert("请选择活动推广图标！！");
			return false;
		}
		if(!$("#appimg_url01").val()||!$("#appimg_url02").val()||!$("#appimg_url03").val()){
			alert("请上传应用截图！！");
			return false;
		}
		return true;
	}
	
	function validate_wall_score(){
		
		if($("#wall_score_response_category_radio_0").attr("checked")=="checked"){
			if(!$("#wall_score_response_type").val()){
				alert("请选择下载类型！！");
				return false;
			}
		}else if($("#wall_score_response_category_radio_1").attr("checked")=="checked"){
			if(!$("#wall_score_redirect_url").val()){
				alert("请填写表单注册地址！！");
				return false;
			}
			
		}
		if(!$("#wall_score_weight").val()){
			 $('#wall_score_weight').focus();
			alert("请填写 权重！！");
			return false;
			
		}
		if(!$("#wall_score_desc").val()){
			alert("请填写积分获取简要说明！！");
			 $('#wall_score_desc').focus();
			return false;
		}
		if(!$("#wall_score_long_desc").val()){
			alert("请填写积分获取详细说明！！");
			$('#wall_score_long_desc').focus();
			return false;
		}
		return true;
	}
	
	
	function validate_wall_list(){
		if($("#wall_list_response_category_radio_0").attr("checked")=="checked"){
			if(!$("#wall_list_response_type").val()){
				alert("请选择下载类型！！");
				return false;
			}
		}else if($("#wall_list_response_category_radio_1").attr("checked")=="checked"){
			if(!$("#wall_list_redirect_url").val()){
				alert("请填写表单注册地址！！");
				$('#wall_list_redirect_url').focus();
				return false;
			}
			
		}
		if(!$("#wall_list_weight").val()){
			alert("请填写权重！！");
			$('#wall_list_weight').focus();
			return false;
		}
		return true;
	}
	
	function validate_banner(){
		if($("#banner_response_category_radio_0").attr("checked")=="checked"){
			if(!$("#banner_response_type").val()){
				alert("请选择下载类型！！");
				return false;
			}
		}else if($("#banner_response_category_radio_1").attr("checked")=="checked"){
			if(!$("#banner_redirect_url").val()){
				alert("请填写表单注册地址！！");
				$('#banner_redirect_url').focus();
				return false;
			}
			
		}
		if(!$("#banner_weight").val()){
			alert("请填写权重！！");
			$('#banner_weight').focus();
			return false;
		}
		return true;
	}
	
	function validate_chartboost(){
		if($("#chartboost_response_category_radio_0").attr("checked")=="checked"){
			if(!$("#chartboost_response_type").val()){
				alert("请选择下载类型！！");
				return false;
			}
		}else if($("#chartboost_response_category_radio_1").attr("checked")=="checked"){
			if(!$("#chartboost_redirect_url").val()){
				alert("----------------");
				alert("请填写表单注册地址！！");
				$('#chartboost_redirect_url').focus();
				return false;
			}
			
		}
		if(!$("#chartboost_weight").val()){
			alert("请填写权重！！");
			$('#chartboost_weight').focus();
			return false;
		}
		return true;
	}

	
	//页面初始化加载
	$(document).ready(function(){
		initUpload();
		response_click_init();
		init_wall_type('${page_type}');
		init_response_type();
	});
	
	function init_response_type(){
		var os='${vo.os}';
		var wall_score_response_type='${vo.wall_score_response_type}';
		if(os=='ios'&&!(wall_score_response_type)){
			$("#wall_score_response_type").val(3);
		}
		//$("#wall_score_response_type").selectReadOnly();
		$("#wall_score_response_category_div").hide();
	
		
		if(os=='ios'){
			$("#wall_list_response_type ").val(3);
		}else{
			$("#wall_list_response_type ").val(0);
		}
		$("#wall_list_response_type").selectReadOnly();
		if(os=='android'){
			$("#wall_list_response_category_div").hide();
		}
	}
	
	
	
	
	$.fn.selectReadOnly=function(){
	    var tem=$(this).children('option').index($("option:selected"));
	    $(this).change(function(){
	          $(this).children('option').eq(tem).attr("selected",true); 
	    });
	};
	
	
	function init_wall_type(page_str){
		var wall_type_2=0;
		var wall_type_3=1;
		var wall_type_4=4;
		var wall_type_5=5;
		if(page_str){
			var arr=page_str.split(",");
			for(var i=2;i<6;i++){
				var b=false;
				for(var j=0;j<arr.length;j++){
					if(eval("wall_type_"+i)==arr[j]){
						b=true;
						break;
					}
				}
				if(!b){
					$(".sub_"+i).hide();
				}
			}
		}else{
			for(var i=2;i<6;i++){
				$(".sub_"+i).hide();
			}
		}
		
	}
	

	//文件上传控件
	function initUpload(){
		//icon
		new AjaxUpload('iconimg', {
			action:'${pageContext.request.contextPath}/manage!uploadIco.do',
			name: 'icon',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
                
				},
			onComplete : function(file,response){
					$("#iconimg").attr("src","<escore:loadImg/>/"+response);
					$("#iconimg_url").val(response);
			}	
		});
		//app01
		new AjaxUpload('appimg01', {
			action:'${pageContext.request.contextPath}/manage!uploadPreviews.do',
			name: 'appimg',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
                
				},
			onComplete : function(file,response){
					alert(response);
					$("#appimg01").attr("src","<escore:loadImg/>/"+response);
					$("#appimg_url01").val(response);
			}	
		});
		
		//app02
		new AjaxUpload('appimg02', {
			action:'${pageContext.request.contextPath}/manage!uploadPreviews.do',
			name: 'appimg',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
				},
			onComplete : function(file,response){
					$("#appimg02").attr("src","<escore:loadImg/>/"+response);
					$("#appimg_url02").val(response);
			}	
		});	
		
		//app03
		new AjaxUpload('appimg03', {
			action:'${pageContext.request.contextPath}/manage!uploadPreviews.do',
			name: 'appimg',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
				},
			onComplete : function(file,response){
					$("#appimg03").attr("src","<escore:loadImg/>/"+response);
					$("#appimg_url03").val(response);
			}	
		});	
		
		
		//wall_score_banner
		new AjaxUpload('wall_score_banner', {
			action:'${pageContext.request.contextPath}/manage!uploadWallScoreBanner.do',
			name: 'wall_score_banner',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
				},
			onComplete : function(file,response){
					$("#wall_score_banner").attr("src","<escore:loadImg/>/"+response);
					$("#wall_score_banner_url").val(response);
					$("#score_bunner_del_button").attr("disabled","");
			}	
		});
		
		//wall_list_banner
		new AjaxUpload('wall_list_banner', {
			action:'${pageContext.request.contextPath}/manage!uploadWallListBanner.do',
			name: 'wall_list_banner',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
				},
			onComplete : function(file,response){
					$("#wall_list_banner").attr("src","<escore:loadImg/>/"+response);
					$("#wall_list_banner_url").val(response);
			}	
		});
		
		
		//banner01
		new AjaxUpload('banner01', {
			action:'${pageContext.request.contextPath}/manage!uploadBanner.do',
			name: 'banner',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
                
				},
			onComplete : function(file,response){
					$("#banner01").attr("src","<escore:loadImg/>/"+response);
					$("#banner01_url").val(response);
			}	
		});	
		
		//banner02
		new AjaxUpload('banner02', {
			action:'${pageContext.request.contextPath}/manage!uploadBanner.do',
			name: 'banner',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
				},
			onComplete : function(file,response){
				$("#banner02").attr("src","<escore:loadImg/>/"+response);
				$("#banner02_url").val(response);
			}	
		});
		
		
		//banner03
		new AjaxUpload('banner03', {
			action:'${pageContext.request.contextPath}/manage!uploadBanner.do',
			name: 'banner',
			data:{advEmail:'${advEmail}'},
			onSubmit: function(file, extension){
				extension=extension.toUpperCase();
				if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
					alert("只支持图片文件！");
					return false;
				}
                
				},
			onComplete : function(file,response){
				$("#banner03").attr("src","<escore:loadImg/>/"+response);
				$("#banner03_url").val(response);
			}	
		});

	//插屏
	new AjaxUpload('chartboost_x', {
		action:'${pageContext.request.contextPath}/manage!uploadChartboost_x.do',
		name: 'chartboost_x',
		data:{advEmail:'${advEmail}'},
		onSubmit: function(file, extension){
			extension=extension.toUpperCase();
			if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
				alert("只支持图片文件！");
				return false;
			}
            
			},
		onComplete : function(file,response){
				$("#chartboost_x").attr("src","<escore:loadImg/>/"+response);
				$("#chartboost_x_url").val(response);
		}	
	});
	//插屏
	new AjaxUpload('chartboost_y', {
		action:'${pageContext.request.contextPath}/manage!uploadChartboost_y.do',
		name: 'chartboost_y',
		data:{advEmail:'${advEmail}'},
		onSubmit: function(file, extension){
			extension=extension.toUpperCase();
			if(extension!="BMP"&&extension!="PNG"&&extension!="GIF"&&extension!="JPEG"&&extension!="JPG"){
				alert("只支持图片文件！");
				return false;
			}
            
			},
		onComplete : function(file,response){
				$("#chartboost_y").attr("src","<escore:loadImg/>/"+response);
				$("#chartboost_y_url").val(response);
		}	
	});
}
	
function del_score_bunner(){
	$("#wall_score_banner").attr("src","${pageContext.request.contextPath}/images/ico/nopic.gif");
	$("#wall_score_banner_url").val("");
	$("#score_bunner_del_button").attr("disabled","disabled");
	
}


function del_wall_list_bunner(){
	$("#wall_list_banner").attr("src","${pageContext.request.contextPath}/images/ico/nopic.gif");
	$("#wall_list_banner_url").val("");
	$("#list_bunner_del_button").attr("disabled","disabled");
	
}



	
	
function response_click_init(){
	$("#wall_response_div input:radio").click(function(){
		if($(this).val()==0){
			$("#wall_score_response_span_1").hide();
			$("#wall_score_response_span_0").show();
			
		}else if($(this).val()==1){
			$("#wall_score_response_span_0").hide();
			$("#wall_score_response_span_1").show();
		}
	});
	var wall_socre_response_category='${vo.wall_score_response_category}';
	if((!wall_socre_response_category)||wall_socre_response_category==0){
		$("#wall_score_response_category_radio_0").attr("checked","checked");
		$("#wall_score_response_span_1").hide();
		$("#wall_score_response_span_0").show();
	}else{
		$("#wall_score_response_category_radio_1").attr("checked","checked");
		$("#wall_score_response_span_0").hide();
		$("#wall_score_response_span_1").show();
	}
	
	
	//--------------------------------
	$("#wall_list_response_div input:radio").click(function(){
		if($(this).val()==0){
			$("#wall_list_response_span_1").hide();
			$("#wall_list_response_span_0").show();
			
		}else if($(this).val()==1){
			$("#wall_list_response_span_0").hide();
			$("#wall_list_response_span_1").show();
		}
	});
	var wall_list_response_category='${vo.wall_list_response_category}';
	if(!wall_list_response_category||wall_list_response_category==0){
		$("#wall_list_response_category_radio_0").attr("checked","checked");
		$("#wall_list_response_span_1").hide();
		$("#wall_list_response_span_0").show();
	}else{
		$("#wall_list_response_category_radio_1").attr("checked","checked");
		$("#wall_list_response_span_0").hide();
		$("#wall_list_response_span_1").show();
	}
	
	//-------------------------------------------
	
	
	$("#banner_response_div input:radio").click(function(){
		if($(this).val()==0){
			$("#banner_response_span_1").hide();
			$("#banner_response_span_0").show();
			
		}else if($(this).val()==1){
			$("#banner_response_span_0").hide();
			$("#banner_response_span_1").show();
		}
	});
	var banner_response_category='${vo.banner_response_category}';
	if(!banner_response_category||banner_response_category==0){
		$("#banner_response_category_radio_0").attr("checked","checked");
		$("#banner_response_span_1").hide();
		$("#banner_response_span_0").show();
	}else{
		$("#banner_response_category_radio_1").attr("checked","checked");
		$("#banner_response_span_0").hide();
		$("#banner_response_span_1").show();
	}
	
	
	
	$("#chartboost_response_div input:radio").click(function(){
		if($(this).val()==0){
			$("#chartboost_response_span_1").hide();
			$("#chartboost_response_span_0").show();
			
		}else if($(this).val()==1){
			$("#chartboost_response_span_0").hide();
			$("#chartboost_response_span_1").show();
		}
	});
	var chartboost_response_category='${vo.chartboost_response_category}';
	if(!chartboost_response_category||chartboost_response_category==0){
		$("#chartboost_response_category_radio_0").attr("checked","checked");
		$("#chartboost_response_span_1").hide();
		$("#chartboost_response_span_0").show();
	}else{
		$("#chartboost_response_category_radio_1").attr("checked","checked");
		$("#chartboost_response_span_0").hide();
		$("#chartboost_response_span_1").show();
	}
}
</script>
</head>
<body>

	<div class="main">
		<div class="content clearfix">
				<div class="content_right admin_right">
					<form action="manage!updateMaterial.do" name="form" method="post" id="myform">
						<div style="display: none;">
							<input type="hidden" name="id"  value="${placement_id}" />
							<input type="hidden" name="iconimg_url" id="iconimg_url" value="${vo.iconimg_url}"/>
							<input type="hidden" name="appimg_url" id="appimg_url01" value="${vo.appimg_url_01}" />
							<input type="hidden" name="appimg_url" id="appimg_url02" value="${vo.appimg_url_02}"/>
							<input type="hidden" name="appimg_url" id="appimg_url03" value="${vo.appimg_url_03}"/>
							<input type="hidden" name="wall_score_banner_url" id="wall_score_banner_url" value="${vo.wall_score_banner_url}"/>
							<input type="hidden" name="wall_list_banner_url" id="wall_list_banner_url" value="${vo.wall_list_banner_url}"/>
							<input type="hidden" name="banner01_url"  id="banner01_url" value="${vo.beanner_img_url_first}"/>
							<input type="hidden" name="banner02_url" id="banner02_url" value="${vo.beanner_img_url_second}" />
							<input type="hidden" name="banner03_url" id="banner03_url" value="${vo.beanner_img_url_third}"/>
							<input type="hidden" name="chartboost_x_url" id="chartboost_x_url" value="${vo.img_horizontal}" />
							<input type="hidden" name="chartboost_y_url" id="chartboost_y_url" value="${vo.img_vertical}" />
						</div>
					 <ul class="tab_block2">    
      					<li class="click"><a href="javascript:void(0)" class="sub_1 tab_block2_hover click"><span class="click">应用</span></a></li>
      					<li><a href="javascript:void(0)" class="sub_2"><span>积分墙</span></a></li>
      					<li><a href="javascript:void(0)" class="sub_3"><span>推荐墙</span></a></li>
     					<li><a href="javascript:void(0)" class="sub_4"><span>BANNER</span></a></li>
     					<li><a href="javascript:void(0)" class="sub_5"><span>插屏</span></a></li>
     					<li><a href="javascript:void(0)" class="sub_6"><span>渠道包</span></a></li>
    				</ul>
    				<!-- 应用基本素材####################################################################3 -->
    				<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
    					<table>
							<tr>
								<td valign="middle" align="right">活动推广程序图标：</td>
								<td align="left" class="icon">
									<img id="iconimg" <c:if test="${vo.iconimg_url ne null}"> src="${images_url_prefix}/${vo.iconimg_url}"</c:if><c:if test="${vo.iconimg_url eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">应用截图：</td>
								<td align="left" class="previewMap">
									<div style="width: 500px;display: block;height: 200px;">
										<img id="appimg01" <c:if test="${vo.appimg_url_01 ne null}"> src="${images_url_prefix}/${vo.appimg_url_01}"</c:if><c:if test="${vo.appimg_url_01 eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
										<img id="appimg02" <c:if test="${vo.appimg_url_02 ne null}"> src="${images_url_prefix}/${vo.appimg_url_02}"</c:if><c:if test="${vo.appimg_url_02 eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
										<img id="appimg03" <c:if test="${vo.appimg_url_03 ne null}"> src="${images_url_prefix}/${vo.appimg_url_03}"</c:if><c:if test="${vo.appimg_url_03 eq null}"> src="${pageContext.request.contextPath}/images/ico/nopic.gif"</c:if> onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" width="135px" height="135px" style="cursor: pointer;" />
									</div>
								</td>
							</tr>
						</table>
    				</div>
    				<!--积分墙 ########################################################################### -->
    				<div class="subblock_2" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
    					<!-- 简单处理下拉框积分墙响应方式默认无app推广，如果以后想选择多个使用脚本数组动态生成下拉，如选择财务选择省市 -->
    					<div style="display: none;">
    						<!--  <input type="hidden" name="wall_score_response_type" value="0" />-->
    					</div>
    					<table>
    						<tr>
								<td valign="middle" align="right">响应方式：</td>
								<td colspan="3">
									<div style="display: block;" id="wall_response_div">
										<div>
											<input id="wall_score_response_category_radio_0" name="wall_score_response_category" type="radio" value="0" /><span>下载</span>
											<span id="wall_score_response_span_0" style="display: none;">
												<select name="wall_score_response_type" id="wall_score_response_type">
													<option selected="selected" value="">请选择</option>
													<option value="0" <c:if test="${vo.wall_score_response_type eq 0 }"> selected="selected" </c:if>>app推广</option>
													<option value="2" <c:if test="${vo.wall_score_response_type eq 2 }"> selected="selected" </c:if> disabled="disabled">app推广下载</option>
													<option value="3" <c:if test="${vo.wall_score_response_type eq 3 }"> selected="selected" </c:if> >直接下载</option>
													<option value="5" <c:if test="${vo.wall_score_response_type eq 5 }"> selected="selected" </c:if> >搜索下载</option> 
												</select>
											</span>
										</div>
										<div style="position: relative;left: 160px;top: -22px;" id="wall_score_response_category_div">
											<input id="wall_score_response_category_radio_1" name="wall_score_response_category" type="radio" value="1" checked="checked" /><span>注册</span>
											<span id="wall_score_response_span_1" style="display: none;">
												<span>注册类地址</span>
												<input name="wall_score_redirect_url" id="wall_score_redirect_url" type="text" maxlength="100" style="width: 200px;" value="${vo.wall_score_redirect_url}" />*
											</span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">权重：</td>
								<td colspan="3" class="wall_score_weight">
									<input type="text" id="wall_score_weight" name="wall_score_weight" <c:if test="${vo.wall_score_weight !='0'}">value="${vo.wall_score_weight}"</c:if> maxlength="10" reg="^\+?[1-9][0-9]*$" tip="请输入非零的正整数 "/>
									<span>当前最大排序号${vo.wall_score_max_weight}</span>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">限制时间：</td>
								<td colspan="3" class="wall_score_limit_time">
									<input type="text" id="wall_score_limit_time" name="wall_score_limit_time" value="${vo.wall_score_limit_time}" maxlength="10" reg="^\+?[1-9][0-9]*$" tip="请输入非零的正整数 "/>
									<font color="red">*(秒)微信墙使用，比如试玩两分钟，此处写120</font>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">积分延时：</td>
								<td colspan="3" class="score_delay">
									<input type="text" id="score_delay" name="score_delay" value="${vo.score_delay}" maxlength="10" reg="^\+?[1-9][0-9]*$" tip="请输入非零的正整数 "/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">积分获取简要说明：</td>
								<td class="shortDesc">
									<input name="wall_score_desc" id="wall_score_desc" type="text" maxlength="15" value="${vo.wall_score_desc}" />*
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">积分获取详细说明：</td>
								<td class="longDesc">
									<textarea name="wall_score_long_desc" cols="50"	rows="5" id="wall_score_long_desc" reg="^[\w\W\u4e00-\u9fa5]{2,500}$" tip="不能为空" maxlength="500">${vo.wall_score_long_desc}</textarea>*
									<span id="longDescMes" style="color: #DC143C"></span>
								</td>
							</tr>
						
							<tr>
								<td valign="middle" align="right">微信任务说明：</td>
								<td class="wall_score_weixin_desc">
									<textarea name="wall_score_weixin_desc" cols="50" rows="5" id="wall_score_long_desc" reg="^[\w\W\u4e00-\u9fa5]{2,500}$" tip="不能为空" maxlength="500">${vo.wall_score_weixin_desc}</textarea>*
									<span  style="color: #DC143C"></span>
								</td>
							</tr>
						
							<tr>
								<td valign="middle" align="right">广告墙banner：</td>
								<td valign="top" class="icon">
									<span style="display: block;">
										<img id="wall_score_banner" src="${images_url_prefix}/${vo.wall_score_banner_url}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'"/>
										<span>640*252</span>
									</span>
									<span style="display: block;">
										<input type="button" id="score_bunner_del_button" value="删除" onclick="del_score_bunner();" style="cursor: pointer;" <c:if test="${vo.wall_score_banner_url eq null}"> disabled="disabled" </c:if> />
									</span>
								</td>
							</tr>	
						</table>
    				</div>
    				<!-- 推荐墙############################################################## -->
    				<div class="subblock_3" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
    						<!-- 简单处理下拉框推荐墙响应方式默认无app推广，如果以后想选择多个使用脚本数组动态生成下拉，如选择财务选择省市 -->
    					<div style="display: none;">
    						<input type="hidden" name="wall_list_response_type" value="0" />
    					</div>
    				
    					<table>
    						<tr>
								<td valign="top" align="right">响应方式：</td>
								<td colspan="3">
									<div style="display: block;" id="wall_list_response_div">
										<div>
											<input id="wall_list_response_category_radio_0" name="wall_list_response_category" type="radio" value="0" /><span>下载</span>
											<span id="wall_list_response_span_0" style="display: none;">
												<select name="wall_list_response_type" id="wall_list_response_type" disabled="disabled">
													<option selected="selected" value="" >请选择</option>
													<option value="0" <c:if test="${vo.wall_list_response_type eq 0 }"> selected="selected" </c:if>>app推广</option>
													<option value="2" <c:if test="${vo.wall_list_response_type eq 2 }"> selected="selected" </c:if>>app推广下载</option>
													<option value="3" <c:if test="${vo.wall_list_response_type eq 3 }"> selected="selected" </c:if>>直接下载</option> 
												</select>
											</span>
										</div>
										<div style="position: relative;left: 160px;top: -22px;" id="wall_list_response_category_div">
											<input id="wall_list_response_category_radio_1" name="wall_list_response_category" type="radio" value="1" checked="checked" /><span>注册</span>
											<span id="wall_list_response_span_1" style="display: none;">
												<span>注册类地址</span>
												<input name="wall_list_redirect_url" id="wall_list_redirect_url"  type="text" maxlength="100" style="width: 200px;" value="${vo.wall_list_redirect_url}" />*
											</span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">权重：</td>
								<td colspan="3" class="wall_list_weight">
									<input type="text" id="wall_list_weight" name="wall_list_weight" <c:if test="${vo.wall_list_weight !='0'}">value="${vo.wall_list_weight}"</c:if> maxlength="10" reg="^\+?[1-9][0-9]*$" tip="请输入非零的正整数 "/>
									<span>当前最大排序号${vo.wall_list_max_weight}</span>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">广告墙banner：</td>
								<td valign="top" class="icon">
									<span>
										<span style="display: block;">
											<img id="wall_list_banner" src="${images_url_prefix}/${vo.wall_list_banner_url}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'"/>
										</span>
										<span>640*252</span>
									</span>
									<span style="display: block;">
										<input type="button" id="list_bunner_del_button" value="删除" onclick="del_wall_list_bunner();" style="cursor: pointer;" <c:if test="${vo.wall_list_banner_url eq null}"> disabled="disabled" </c:if> />
									</span>
								</td>
							</tr>	
						</table>
    				</div>
    				
    				<!-- beanner -->
    				<div class="subblock_4" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
						<table>
							<tr>
								<td valign="top" align="right">响应方式：</td>
								<td colspan="3">
									<div style="display: block;" id="banner_response_div">
										<div>
											<input id="banner_response_category_radio_0" name="banner_response_category" type="radio" value="0" /><span>下载</span>
											<span id="banner_response_span_0" style="display: none;">
												<select name="banner_response_type" id="banner_response_type">
													<option selected="selected" value="">请选择</option>
													<option value="0" <c:if test="${vo.banner_response_type eq 0 }"> selected="selected" </c:if>>app推广</option>
													<option value="2" <c:if test="${vo.banner_response_type eq 2 }"> selected="selected" </c:if>>app推广下载</option>
													<option value="3" <c:if test="${vo.banner_response_type eq 3 }"> selected="selected" </c:if>>直接下载</option> 
												</select>
											</span>
										</div>
										<div style="position: relative;left: 160px;top: -22px;">
											<input id="banner_response_category_radio_1" name="banner_response_category" type="radio" value="1" checked="checked" /><span>注册</span>
											<span id="banner_response_span_1" style="display: none;">
												<span>注册类地址</span>
												<input name="banner_redirect_url" id="banner_redirect_url" type="text" maxlength="100" style="width: 200px;" value="${vo.banner_redirect_url}" />*
											</span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">权重：</td>
								<td colspan="3" class="banner_weight">
									<input type="text" id="banner_weight" name="banner_weight" <c:if test="${vo.banner_weight !='0'}">value="${vo.banner_weight}"</c:if> maxlength="10" reg="^\+?[1-9][0-9]*$" tip="请输入非零的正整数 "/>
									<span>当前最大排序号${vo.banner_max_weight}</span>
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">BANNER图片：</td>
								<td valign="top" class="previewMap">
									<img id="banner01" src="${images_url_prefix}/${vo.beanner_img_url_first}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									<img id="banner02" src="${images_url_prefix}/${vo.beanner_img_url_second}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									<img id="banner03" src="${images_url_prefix}/${vo.beanner_img_url_third}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									<span>640*100</span>
								</td>
							</tr>
						</table>
					</div>
					<div class="subblock_5" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
						<table>
							<tr>
								<td valign="top" align="right">响应方式：</td>
								<td colspan="3">
									<div style="display: block;" id="chartboost_response_div">
										<div>
											<input id="chartboost_response_category_radio_0" name="chartboost_response_catelog" type="radio" value="0" /><span>下载</span>
											<span id="chartboost_response_span_0" style="display: none;">
												<select name="chartboost_response_type" id="chartboost_response_type">
													<option selected="selected" value="">请选择</option>
													<option value="0" <c:if test="${vo.chartboost_response_type eq 0 }"> selected="selected" </c:if>>app推广</option>
													<option value="2" <c:if test="${vo.chartboost_response_type eq 2 }"> selected="selected" </c:if>>app推广下载</option>
													<option value="3" <c:if test="${vo.chartboost_response_type eq 3 }"> selected="selected" </c:if>>直接下载</option> 
												</select>
											</span>
										</div>
										<div style="position: relative;left: 160px;top: -22px;">
											<input id="chartboost_response_category_radio_1" name="chartboost_response_catelog" type="radio" value="1" checked="checked" /><span>注册</span>
											<span id="chartboost_response_span_1" style="display: none;">
												<span>注册类地址</span>
												<input name="chartboost_redirect_url" id="chartboost_redirect_url" type="text" maxlength="100" style="width: 200px;" value="${vo.chartboost_redirect_url}" />*
											</span>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">权重：</td>
								<td colspan="3" class="chartboost_weight">
									<input type="text" id="chartboost_weight" name="chartboost_weight" <c:if test="${vo.chartboost_weight !='0'}">value="${vo.chartboost_weight}"</c:if>  maxlength="10" reg="^\+?[1-9][0-9]*$" tip="请输入非零的正整数 "/>
									<span>当前最大排序号${vo.chartboost_max_weight}</span>
									<font color="red"></font>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">插屏图片横屏：</td>
								<td valign="top" class="icon">
									<img id="chartboost_x" src="${images_url_prefix}/${vo.img_horizontal}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'" />
									<font>560*500</font>
								</td>
							</tr>
							<tr>
								<td valign="top" align="right">插屏图片竖屏：</td>
								<td valign="top" class="icon">
									<img id="chartboost_y" src="${images_url_prefix}/${vo.img_vertical}" width="135px" height="135px" style="cursor: pointer;" onerror="this.src='${pageContext.request.contextPath}/images/ico/nopic.gif'"/>
									<font>500*560</font>
								</td>
							</tr>
						</table>
					</div>
					<div class="subblock_6" id="divEventWorkFlow" style="display:none;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 720px;">
						<iframe frameborder="0" height="720" width="740" src="manage!uploadPackage.do?placement_id=${placement_id}"></iframe> 
					</div>  				
					</form>

				</div>
		</div>
	</div>
</body>
</html>