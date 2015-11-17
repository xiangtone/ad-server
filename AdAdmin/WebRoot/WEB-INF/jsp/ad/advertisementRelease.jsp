<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告主发布广告</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>	


<script type="text/javascript">

	function point_descClick(){
		 document.getElementById("point_alert").style.display="none";
	}
	function checkbudget(flag){
		if(flag == "1"){
			document.getElementById("budgetDay").disabled=true;
		}else{
			document.getElementById("budgetDay").disabled=false;
		}
	}
	function check(){
		var canSubmit = true;
		var os;
	
		os=GetRadioValue("os");
		var type_id = document.getElementById("type_id").value;
		
		if(os=="ios"){
		  var ios_size = document.getElementById("ios_resource_size").value;
		  if(ios_size==""){				
				alert("IOS文件大小不能为空！");
				document.getElementById("ios_resource_size").focus();
				canSubmit = false;
				return false;
				
			}
		  
		  var ios_version = document.getElementById("ios_version").value;
		  if(ios_version==""){				
				alert("IOS版本不能为空！");
				document.getElementById("ios_version").focus();
				canSubmit = false;
				return false;
				
			}
		}
		
		/*
		if(os == "ios" && type_id == 0){
			alert("选择IOS的时候，广告形式里不能选择积分墙！");
			document.getElementById("type_id").focus();
			canSubmit = false;
			return false;

		}*/
		
		
		
		var adName = document.getElementById("adName").value;
		if(adName==""){
			
			alert("广告名称不能为空！");
			document.getElementById("adName").focus();
			canSubmit = false;
			return false;
			
		}
		
		var title = document.getElementById("title").value;
		if(title==""){
			
			alert("活动名称不能为空！");
			document.getElementById("title").focus();
			canSubmit = false;
			return false;			
		}
		
		/*
		var keyword = document.getElementById("keyword").value;
		if(keyword==""){
			
			alert("关键字不能为空！");
			document.getElementById("keyword").focus();
			canSubmit = false;
			return false;
			
		}*/
		
		var shortDesc = document.getElementById("shortDesc").value;
		if(shortDesc==""){			
			alert("广告语不能为空！");
			document.getElementById("shortDesc").focus();
			canSubmit = false;
			return false;
			
		}else if(shortDesc.length<10){
			alert("推广使用的文字描述，10-15个汉字，用户将在广告墙中看到推广！");
			document.getElementById("shortDesc").focus();
			canSubmit = false;
			return false;	
		}
		
		
		var minPrice = document.getElementById("minPrice").value;   
		
		if(document.getElementById("budgetNid").checked){
			  //日预算验证
			var budgetDay = document.getElementById("budgetDay").value;   
			var minDayBudget = document.getElementById("minDayBudget").value;  
			minDayBudget =  minDayBudget *1;
			if (budgetDay < minDayBudget){
				document.getElementById("budgetDay").focus();
			  	alert("您输入的日预算必须不小于"+minDayBudget+"元");
			  	canSubmit = false;
			  	return false;
			}
		}
	
		
		var finance_price = document.getElementById("finance_price").value;
		var adwallCamLowestPrice = document.getElementById("adwallCamLowestPrice").value;
		if(finance_price==""){
			alert("财务单价不能为空！");
			document.getElementById("finance_price").focus();
			canSubmit = false;
			return false;	
		}else if(finance_price<adwallCamLowestPrice){
			alert("财务单价不能小于最低单价！");
			document.getElementById("finance_price").focus();
			canSubmit = false;
			return false;	
		}
		
		
		var point_desc_status = document.getElementById("point_desc_status").value;
		var point_desc = document.getElementById("point_desc").value;
		if(point_desc_status==1&&point_desc==""){
			alert("付费条件为其他时，需输入条件！");
			document.getElementById("point_desc").focus();
			canSubmit = false;
			return false;	
		}
		
		
		  //总预算验证
		var budget = document.getElementById("budget").value;   
		var actualBalance = document.getElementById("actualBalance").value;  
		var creditBalance = document.getElementById("creditBalance").value;  
		var sum = actualBalance*1 + creditBalance*1;
	
		 if (budget < 100){
		  	alert("您输入的总预算必须不小于100元");
		  	document.getElementById("budget").focus();
		  	canSubmit = false;
		  	return false;
		  }else{
			  if(budget> sum){
			  		alert("您输入的总预算必须不大于账户余额+信用额度余额！");
			  		document.getElementById("budget").focus();
			  		canSubmit = false;
			  		return false;
			  	}
		  }

		if(document.getElementById("budgetNid").checked){
			var budgetDay = document.getElementById("budgetDay").value;   //日预算
			var budget = document.getElementById("budget").value; //总预算
			
			if(parseFloat(price) > parseFloat(budgetDay)){
				alert("推广单价不能大于日预算！");
				canSubmit = false;
			  	return false;
			}
			if(parseFloat(budgetDay) > parseFloat(budget)){
				alert("日预算不能大于总预算！");
				canSubmit = false;
			  	return false;
			}
		}
		
		
		
		
		var ad_type = document.getElementById("adType").value;
		if(ad_type==1){
			var ad_url = document.getElementById("ad_url").value; //总预算
			if(ad_url==""||ad_url.length<5){
				alert("表单注册类推广地址不能为空！");
				document.getElementById("ad_url").focus();
				canSubmit = false;
			  	return false;
			}
		}
		
		
		if(os == "ios"){
			var app_url = document.getElementById("app_url").value;
			if(app_url==""||app_url.length<5){
				alert("APP下载URL不能为空！");
				document.getElementById("app_url").focus();
				canSubmit = false;
			  	return false;
			}
		}
		
		if(type_id != 4 && type_id != 5){
			if(ad_type==2){
			alert("只有banner和插屏才能选择app推广下载！");
			document.getElementById("adType").focus();
			canSubmit = false;
		  	return false;
		  	}
		}
		
		
		
		//preview1,icon,big_banner_url
		var icon = document.getElementById("icon").value;
		if(icon!=null&&icon.length<3){
			alert("活动推广程序图标不能为空！");
			document.getElementById("icon").focus();
			canSubmit = false;
		  	return false;
		}
		
		var preview1 = document.getElementById("preview1").value;
		if(preview1!=null&&preview1.length<3){
			alert("应用截图不能为空！");
			document.getElementById("preview1").focus();
			canSubmit = false;
		  	return false;
		}
		var preview2 = document.getElementById("preview2").value;
		if(preview2!=null&&preview2.length<3){
			alert("应用截图不能为空！");
			document.getElementById("preview2").focus();
			canSubmit = false;
		  	return false;
		}
		var preview3 = document.getElementById("preview3").value;
		if(preview3!=null&&preview1.length<3){
			alert("应用截图不能为空！");
			document.getElementById("preview3").focus();
			canSubmit = false;
		  	return false;
		}
		
		/*
		var big_banner_url = document.getElementById("big_banner").value;
		if(big_banner_url!=null&&big_banner_url.length<3){
			alert("广告墙banner不能为空！");
			document.getElementById("big_banner").focus();
			canSubmit = false;
		  	return false;
		}*/
		
		
		
	
		if(type_id==4){
			var banner_url = document.getElementById("banner").value;
			if(banner_url!=null&&banner_url.length<5){
				alert("banner不能为空！");
				document.getElementById("banner").focus();
				canSubmit = false;
			  	return false;
			}
		}
		
		
		if(type_id==5){
			var plaque_width_url = document.getElementById("plaque_width").value;
			if(plaque_width_url!=null&&plaque_width_url.length<5){
				alert("插屏图片横屏不能为空！");
				document.getElementById("plaque_width").focus();
				canSubmit = false;
			  	return false;
			}
			var plaque_height_url = document.getElementById("plaque_height").value;
			if(plaque_height_url!=null&&plaque_height_url.length<5){
				alert("插屏图片竖屏不能为空！");
				document.getElementById("plaque_height").focus();
				canSubmit = false;
			  	return false;
			}
		}


		var input = document.getElementsByTagName("input");
		var rule_time_type = document.getElementById("rule_time_type").value;
		var timeFlag = 0;
		if(rule_time_type==1){
			for(var i=0;i<input.length;i++){
				if(input[i].type == "checkbox" && input[i].name == "rule_time" && input[i].checked){
					timeFlag=1;break; 
				}
			}
			if(timeFlag==0){				
				alert("投放时段不能为空！");
				canSubmit = false;
			  	return false;
			}
		}
		
		var rule_app_type = document.getElementById("rule_app_type").value;
		var timeFlag2 = 0;
		if(rule_app_type==1){
			for(var i=0;i<input.length;i++){
				if(input[i].type == "checkbox" && input[i].name == "rule_app" && input[i].checked){
					timeFlag2=1;break; 
				}
			}
			if(timeFlag2==0){				
				alert("应用类型不能为空！");
				canSubmit = false;
			  	return false;
			}
		}
		
		var ids = ",";
		var form = document.getElementById("testForm");
		for (var i=0; i<form.elements.length; i++) {
			var element = form.elements[i];
			if (element.name == "id" && element.type=='checkbox'){
				if( element.checked == true ){
					ids = ids + element.value + ",";
				}
			}
		}		
			
		var rule_area_type = document.getElementById("area_type").value;
		if(rule_area_type==1){
			if(ids.length<2){
				alert("投放地域不能为空！");
				canSubmit = false;
			  	return false;
			}
		}
		
		$("#rule_area").val(ids);   
		$("#rule_area_type").val(rule_area_type); 
		
		
		
		
		
		

		
		 
		if(canSubmit){
			$("#advertisementReleaseExecution").submit();
		}
	}
	
	
	
	function GetRadioValue(RadioName){
	    var obj;    
	    obj=document.getElementsByName(RadioName);
	    if(obj!=null){
	        var i;
	        for(i=0;i<obj.length;i++){
	            if(obj[i].checked){
	                return obj[i].value;            
	            }
	        }
	    }
	    return null;
	}

</script>
</head>
	<body>
		<div class="main">
			<div class="content clearfix">
				<form id="advertisementReleaseExecution" name="advertisementReleaseExecution" action="manage!advertisementReleaseExecution.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="userAdvertiser" id="userAdvertiser" value="${userAdvertiser}"/>
				<input type="hidden" name="adv_id" id="adv_id" value="${advertiserId}"/>
				<input type="hidden" name="rule_area" id="rule_area" value=""/>
				<input type="hidden" name="rule_area_type" id="rule_area_type" value=""/>
				<input type="hidden" name="adwallCamLowestPrice" id="adwallCamLowestPrice" value="${configEScore.adwallCamLowestPrice}"/>
				
					<div class="content_right admin_right">
						<div class="bord_bom1px">
							发布广告
						</div>
						<div class="back_link">
							<a href="#">广告管理</a>&nbsp;>&nbsp;软件管家
						</div>
						<div class="mar_bom10 margtop10">
							广告主：${userAdvertiser.realName}&nbsp;&nbsp;&nbsp;
							<span>联系人：${userAdvertiser.realName}</span>
						</div>
						<div>
							
						</div>
						<input type="hidden" name="jumpPage" value="advertisementDetal" />
						
						<div class="bord_bom1px">
							推广设置
						</div>
						<div>
							<input name="os" type="radio" value="android" checked="checked"/>ANDROID
							<input name="os" type="radio" value="ios" />IOS							
								
						</div>
						<table>
							<input type="hidden" name="id" value="${id}" />
							<input type="hidden" id="minPrice" value="${configEScore.adwallCamLowestPrice }"/>
							<input type="hidden" id="minDayBudget" value="${configEScore.adwallCamLowestDayBudget }"/>
							<tr>
								<td valign="top" width="200">
									账户余额：
								</td>
								<td width="600">
									${userAdvertiser.actualBalance}元
									<input id="actualBalance" type="hidden" value="${userAdvertiser.actualBalance }"/>
								</td>
							</tr>
							<tr>
								<td valign="top">
									信用额度：
								</td>
								<td>
									${userAdvertiser.creditQuota}元
								</td>
								<td width="90">
									信用余额：
								</td>
								<td>
									${userAdvertiser.creditBalance }元
									<input id="creditBalance" type="hidden" value="${userAdvertiser.creditBalance }"/>
								</td>
							</tr>
							<tr>
									<td valign="top">
										广告名称：
									</td>
									<td colspan="3" class="adName">
										<input type="text" id="adName" name="adName" value="${advertisement.adName}" maxlength="20" />*
									</td>
								</tr>
								<tr>
									<td valign="top">
										广告形式：
									</td>
									<td colspan="3" >
									    									
											<select name="type_id" id="type_id"  >
											   <c:forEach items="${typeList}" var="entry">
												<option value="${entry.id}">${entry.name}</option> 
												</c:forEach>
											</select>

									</td>
								</tr>
								<tr>
									<td valign="top">
										广告类型：
									</td>
									<td colspan="3" >
									 <select name="adType" id="adType"  >
										<option value="0">app推广</option> 
										<option value="1">表单注册类推广</option> 
										<option value="2">app推广下载</option>
									</select>
									</td>
								</tr>
								<tr>
									<td valign="top">
										IOS文件大小：
									</td>
									<td colspan="3" >
									 <input name="ios_resource_size"  id="ios_resource_size"  type="text"
										value="10" reg="^$|^[0-9\.]+$"  tip="请输入数字"/>M
									</td>
								</tr>
								<tr>
									<td valign="top">
										版本号：
									</td>
									<td colspan="3" >
									 <input name="ios_version"  id="ios_version"  type="text"
										value="" />
									</td>
								</tr>
								<tr>
									<td valign="top">
										确认对象：
									</td>
									<td colspan="3" >
									<select name="confirm_obj" id="confirm_obj"  >
										<c:forEach items="${iosList}" var="entry">
											<option value="${entry.id}">${entry.ad_name}</option> 
										</c:forEach>
									</select>
									</td>
								</tr>
								
								
								
								<tr>									
									<td>
									广告结算单价：
									</td>
									<td>
									<input  type="text" name="price" id="price" value="" reg="^[0-9\.]+$"  tip="请输入数字"/>
										元
									</td>
								</tr>
								
								<tr>									
									<td>
									财务单价：
									</td>
									<td>
									<input  type="text" name="finance_price" id="finance_price" value="${advertisement.finance_price}" reg="^[0-9\.]+$"  tip="请输入数字"/>
										元&nbsp;&nbsp;&nbsp;最低${configEScore.adwallCamLowestPrice}
									</td>
								</tr>
								
								<tr>
								<td valign="top">开发者结算方式：</td>
								<td><select name="chargeType" id="chargeType"  >
										<option value="CPA">CPA</option> 
										<option value="CPC">CPC</option> 
										<option value="CPM">CPM</option> 
										<option value="CPD">CPD</option> 											
									</select>
								</td>	
								</tr>
								
								
								<tr>
								<td valign="top">广告主结算方式：</td>
								<td><select name="ad_pay_type" id="ad_pay_type"  >
										<option value="CPA">CPA</option> 
										<option value="CPC">CPC</option> 										
									</select>
								</td>	
								</tr>
															
								 <tr>
								<td valign="top">付费条件：</td>
								<td><select name="point_desc_status" id="point_desc_status"  >
										<option value="0">安装激活</option> 
										<option value="1">其他 </option> 																					
									</select>
									<input name="point_desc" type="text" id="point_desc" 
										value="" maxlength="20"/>
								</td>	
								</tr>
								
								 <tr>
							    <td align=left>排序优先级:</td>
							    <td>
								   <input name="order_num" type="text"
										value="10" reg="^[0-9999\.]+$"  tip="请输入数字"/>
								   
								</td>
								</tr>
								
								<tr>
							    <td align=left>星级:</td>
							    <td>
								   <select name="star_level" id="point_desc_status"  >
										<option value="5.0">五星</option> 
										<option value="4.5">四星半 </option> 	
										<option value="4.0">四星</option> 
										<option value="3.5">三星半 </option> 
										<option value="3.0">三星</option> 
										<option value="2.5">二星半 </option> 
										<option value="2.0">二星</option> 
										<option value="1.5">一星半 </option> 
										<option value="1.0">一星</option> 																														
									</select>
								</td>
								</tr>
								
								<tr>
							    <td align=left>优先推荐:</td>
							    <td>
								   <select name="priority" id="priority"  >
										<option value="1">优先</option> 
										<option value="0">普通</option>  																														
									</select>
								</td>
								</tr>
								
							
							
							<tr>
								<td valign="top">
									广告日预算：
								</td>
								<td colspan="3">
									<!-- 
										<input name="budgetDayType" type="radio" value="1" <c:if test="${advertisement.budgetDay eq '0.0'}">checked="checked"</c:if>
										onchange="checkbudget('1')" />
									 -->
									 <input name="budgetDayType" type="radio" value="1" checked="checked" onChange="checkbudget('1')" />
									不限制
									<!-- 
										<input name="budgetDayType" type="radio" value="2" <c:if test="${advertisement.budgetDay ne '0.0'}">checked="checked"</c:if>
										onchange="checkbudget('2')" />
									-->
									<input id="budgetNid" name="budgetDayType" type="radio" value="2" onChange="checkbudget('2')" />
									日预算
									<!-- 
										<input name="budgetDay" type="text" id="budgetDay" <c:if test="${advertisement.budgetDay eq '0.0'}">disabled="true"</c:if>
										value="${advertisement.budgetDay}" />
									 -->
									 <input name="budgetDay" type="text" id="budgetDay" disabled="true" />
									元
									<span class="fontccc">&nbsp;最低${configEScore.adwallCamLowestDayBudget}元</span>
									<span id="budgetDayMes" style="color:#DC143C"></span>
									<!--<c:if test="${advertisement.budgetDay ne '0' and '' and null}">checked="checked"</c:if>-->
								</td>
							</tr>
							
							
							<tr>
								<td valign="top">
									推广时间：
								</td>
								<td colspan="3" >
									<input name="extend_type" type="radio" value="0" checked="checked"/>不限制
									<input name="extend_type" type="radio" value="1" />限制									
									开始时间<input type="text" name="extend_start_time"
													id="extend_start_time"  class="Wdate" readonly="readonly" onClick="WdatePicker()"/>
												
									结束时间<input type="text" name="extend_end_time" 
													id="extend_end_time"  class="Wdate" readonly="readonly" onClick="WdatePicker()"/>					
								</td>
							</tr>
							<tr>
								<td valign="top">
									广告总预算：
								</td>
								<td colspan="3" class="budget">
									<input name="budget" type="text" id="budget" reg="^[0-9\.]+$" tip="请输入数字"
										value="${advertisement.budget}" />
									元*
									<span id="budgetMes" style="color:#DC143C"></span>
								</td>
							</tr>
							<tr>
								<td valign="top">
									当前状态：
								</td>
								<td colspan="3">
									待提交
								</td>
							</tr>
						</table>
						<div class="bord_bom1px">
							活动信息
						</div>
						<table>
							<tr>
								<td width="200" valign="top">
									活动名称：
								</td>
								<td class="title" width="600">
									<input name="title" type="text" id="title" value="${advertisement.title}"  maxlength="20"/>*
									<span id="titleMes" style="color:#DC143C"></span>
								</td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>
									20个字符以内
								</td>
							</tr>
							<tr>
								<td valign="top">
									关键字：
								</td>
								<td class="keyword">
									<input name="keyword" id="keyword" type="text" 
										value="${advertisement.keyword}" />
								</td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>
									多个关键字请用空格分隔
								</td>
							</tr>
							<tr>
								<td valign="top">
									广告语：
								</td>
								<td class="shortDesc">
									<input name="shortDesc" id="shortDesc" type="text"  maxlength="15"
										value="${advertisement.shortDesc}" />*
									<span id="shortDescMes" style="color:#DC143C"></span>
								</td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>
									推广使用的文字描述，10-15个汉字，用户将在广告墙中看到推广
								</td>
							</tr>
							<tr>
								<td valign="top">
									活动类别：
								</td>
								<td>
									<select name="categoryId" id="categoryId" >
										<c:forEach items="${ecList}" var="entry">
											<option value="${entry.id}">${entry.fname}-${entry.name}</option>
										</c:forEach>
									</select>								
								</td>
							</tr>
							<tr>
								<td valign="top">
									活动介绍：
								</td>
								<td class="longDesc">
									<textarea name="longDesc" cols="50" rows="5" id="longDesc" reg="^[\w\W\u4e00-\u9fa5]{2,500}$" tip="不能为空" maxlength="500">${advertisement.longDesc}</textarea>*
									<span id="longDescMes" style="color:#DC143C"></span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									500个汉字以内
								</td>
							</tr>
							<tr>
								<td valign="top">
									表单注册类推广地址：
								</td>
								<td class="ad_url">
									<input name="ad_url" type="text" id="ad_url" value=""   size="80"/>	
									<span id="ad_urlMes" style="color:#DC143C"></span>								
								</td>
							</tr>
							<tr>
								<td valign="top">
									APP下载URL：
								</td>
								<td class="ad_url">
									<input name="app_url" type="text" id="app_url" value=""   size="80"/>	
									<span id="ad_urlMes" style="color:#DC143C"></span>								
								</td>
							</tr>
							<tr>
								<td valign="top">
									活动推广程序图标：
								</td>
								<td valign="top" class="icon">
									<!-- <img id="idImg" src="<escore:loadImg/>${iconUrl}" width="135px" height="135px"/>  -->
									<input name="icon" id="icon" type="file" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" />*
								</td>
							</tr>
							<tr>
								<td valign="top">应用截图 :</td>
								<td>
									<table>
										<tr>
											<td colspan="3">只能上传以下类型：jpg|gif|bmp|png 格式，480*800</td>
										</tr>
										<tr>
											<td>
												<div><input name="preview1" id="preview1" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>*</div>
												<div><input name="preview2" id="preview2" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>*</div>
												<div><input name="preview3" id="preview3" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>*</div>
											</td>
										</tr>
</table>
								</td>
							</tr>
							
							<tr>
								<td valign="top">广告墙banner :</td>
								<td>
									<div><input name="big_banner" id="big_banner" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>640*268</div>
												
								</td>
							</tr>
							
							
							<tr>
								<td valign="top">banner :</td>
								<td>
									<div><input name="banner" id="banner" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>640*100</div>												
								</td>
							</tr>
						
							
							<tr>
								<td valign="top">插屏图片横屏 :</td>
								<td>
									<div><input name="plaque_width" id="plaque_width" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>
									android:600*500，IOS280*250
									
									</div>
												
								</td>
							</tr>
							
							<tr>
								<td valign="top">插屏图片竖屏:</td>
								<td>
									<div><input name="plaque_height" id="plaque_height" reg=".*png|gif|jpg|jpeg|PNG|GIF|JPG|JPEG$|(^$)" tip="允许png,gif,jpg,jpeg文件" type="file"/>
									android:400*400，IOS250*280
									</div>
												
								</td>
							</tr>
							<tr>
								<td valign="top">
								<select name="rule_time_type" id="rule_time_type" >
									<option value="0">不限</option>
									<option value="1">限制</option>
								</select>
								投放时段:
								</td>
								<td>
								    <input type="checkbox" name="rule_time" value="00"> 00:00-01:00
								    <input type="checkbox" name="rule_time" value="01"> 01:00-02:00
								    <input type="checkbox" name="rule_time" value="02"> 02:00-03:00
								    <input type="checkbox" name="rule_time" value="03"> 03:00-04:00
								    <input type="checkbox" name="rule_time" value="04"> 04:00-05:00
								    <input type="checkbox" name="rule_time" value="05"> 05:00-06:00
								    <br>
								    <input type="checkbox" name="rule_time" value="06"> 06:00-07:00
								    <input type="checkbox" name="rule_time" value="07"> 07:00-08:00
								    								    
								    <input type="checkbox" name="rule_time" value="08"> 08:00-09:00								    
								    <input type="checkbox" name="rule_time" value="09"> 09:00-10:00
								    <input type="checkbox" name="rule_time" value="10"> 10:00-11:00
								    <input type="checkbox" name="rule_time" value="11"> 11:00-12:00
								    <br>
								    <input type="checkbox" name="rule_time" value="12"> 12:00-13:00
								    <input type="checkbox" name="rule_time" value="13"> 13:00-14:00
								    <input type="checkbox" name="rule_time" value="14"> 14:00-15:00
								    <input type="checkbox" name="rule_time" value="15"> 15:00-16:00
								   
								    <input type="checkbox" name="rule_time" value="16"> 16:00-17:00								     
								    <input type="checkbox" name="rule_time" value="17"> 17:00-18:00
								    <br>
								    <input type="checkbox" name="rule_time" value="18"> 18:00-19:00
								    <input type="checkbox" name="rule_time" value="19"> 19:00-20:00 
									<input type="checkbox" name="rule_time" value="20"> 20:00-21:00
								    <input type="checkbox" name="rule_time" value="21"> 21:00-22:00
								    <input type="checkbox" name="rule_time" value="22"> 22:00-23:00
								    <input type="checkbox" name="rule_time" value="23"> 23:00-24:00			
								</td>
							</tr>
							
							<tr>
								<td valign="top">
								<select name="rule_app_type" id="rule_app_type" >
									<option value="0">不限</option>
									<option value="1">限制</option>
								</select>
								应用类型:
								</td>
								<td>							   
								    <c:forEach items="${ecList}" var="entry" varStatus="i">
											<input type="checkbox" name="rule_app" value="${entry.id}">${entry.fname}-${entry.name}
											 <c:if test="${i.count%6==0}">
											 <br>
											 </c:if>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td valign="top">
								<select name="rule_type_type" id="rule_type_type" >
									<option value="0">不限</option>
									<option value="1">限制</option>
								</select>
								终端类型：
								</td>
								<td>							   								   
									<input name="rule_type" type="radio" value="phone" checked="checked"/>手机
									<input name="rule_type" type="radio" value="Pad" />Pad								
								</td>
							</tr>
							
						
				</form>
                <tr>
								<td valign="top">
								<select name="area_type" id="area_type" >
									<option value="0">不限</option>
									<option value="1">限制</option>
								</select>
								投放地域:
								</td>
								<td>
								<div class="dtree">
								   <table>
								      <script type="text/javascript"> 
								        d = new dTree('d','.','testForm');
								        <c:forEach items="${treeList}" var="entry">
								        d.add(${entry.areaid},${entry.areaparentid},"${entry.areaname}"); 
								        </c:forEach>						                
										document.write(d);  
									</script> 
								   </table>	
								</div>   	
								</td>
							</tr>
				<tr>
								<td valign="top"></td>
								<td>
									<input name="button" type="button" onClick="check();" value="保存" />
								</td>
							</tr>	
				</table>					
			</div>
		</div>
		</div>
	</body>
</html>