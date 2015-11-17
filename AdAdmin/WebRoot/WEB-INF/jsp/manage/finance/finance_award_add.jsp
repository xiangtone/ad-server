<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理平台设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
	<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save","保存",function(){
		if(vaildateForm("updateDevListAwardDetail")){
			$("#updateDevListAwardDetail").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}else{
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
				}else{
					alert("操作失败，请重试！！");
					dg.curWin.refresh();
				}
			});
		}
	});
</script>	
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
		<style type="text/css">
			.reg_ok,.reg_no{ 
				background:url(${pageContext.request.contextPath}/images/icons-signup.png) no-repeat;
				padding-left:25px;
				display:inline-block; 
				height:23px;
				line-height:23px;
				font-size:14px;
			}
			.reg_ok{
				background-position:5px 1px;
				color:#090;
			}
			.reg_no{
				background-position:5px -50px;
				color:red;
			}
			.suggest_link {
				background-color: #FFFFFF;
				padding: 2px 6px 2px 6px;
			}
			.suggest_link_over {
				background-color: #E8F2FE;
				padding: 2px 6px 2px 6px;
			}
			
			.suggest_link_sel {
				background-color: #039CEA;
				padding: 2px 6px 2px 6px;
			}
			#search_suggest {
				position: absolute; 
				background-color: #FFFFFF; 
				width:298px;
				border:1px solid #000000;			
			}
			#search_suggest2 {
				position: absolute; 
				background-color: #FFFFFF; 
				width:298px;
				border:1px solid #000000;			
			}			
		</style>
<script type="text/javascript">
	$(function() {
		var xOffset = -20;
		var yOffset = 20;
		$("input[reg][id!='entryDevName'],textarea[reg]").blur(function() {
			validate($(this), true);
		});
		$("select[reg]").blur(function() {
			validate($(this), true);
		});
		$("searchDevListAwardDetail").submit(function() {
			var isSubmit = true;
			for ( var i = 0; i < this.length; i++) {
				var input = $(this[i]);
				if (input.attr("reg") != null) {
					if (input.attr("disabled") != "disabled") {
						if (input.is(":input") || input.is(":textarea")) {
							if (!validate(input, false)) {
								isSubmit = false;
							} else if (input.context.name == "email") {
								if ($(".emailText").length >= 1) {
									isSubmit = false;
								}
							}
						} else if (input.is(":select")) {
							if (!validate(input, false)) {
								isSubmit = false;
							}
						}
					}
				}
			}
			return isSubmit;
		});
		
		$("#updateDevListAwardDetail").submit(function() {
			var isSubmit = true;
			if(!vaildateDevName()){
				isSubmit = false;
			}
			for ( var i = 0; i < this.length; i++) {
				var input = $(this[i]);
				if (input.attr("reg") != null) {
					if (input.attr("disabled") != "disabled") {
						if (input.is(":input") || input.is(":textarea")) {
							if (!validate(input, false)) {
								isSubmit = false;
							} else if (input.context.name == "email") {
								if ($(".emailText").length >= 1) {
									isSubmit = false;
								}
							}
						} else if (input.is(":select")) {
							if (!validate(input, false)) {
								isSubmit = false;
							}
						}
					}
				}
			}
			return isSubmit;
		});
	});
	
	
	function validate(obj, ajaxFlag) {
		var reg = new RegExp(obj.attr("reg"));
		var objValue = obj.attr("value");
		var objdefValue = obj.attr("defvalue");
		var objTip = obj.attr("tip");
		var objTips = obj.attr("tips");
		// 取得提示信息
		
		var objectName = obj.context.name;
		var spanName = '.' + objectName + 'Text';
		var span = $(spanName);
		if (obj.parent().parent().css("display") == "none") {
			return true;
		}
		if(!obj.attr("voff")||(obj.attr("voff")=="true")){
			if (objValue != '' || objdefValue == undefined) {
				if (!reg.test(objValue)) {
					if (obj.is(":input") || obj.is(":textarea")) {
						obj.addClass("input_validation-failed");
						if (span.length == 1) {
							span.remove();
						}
						$('<span class="' + objectName + 'Text" style="color:red">'+objTip + '</span>').appendTo('.' + obj.context.name);
					} else {
						obj.addClass("select_validation-failed");
					}
					return false;
				} else {
					if (obj.is(":input") || obj.is(":textarea")) {
						if (objectName == "confimPassword") {
							if ($("#confimPassword").val() != $("#password").val()) {
								obj.addClass("input_validation-failed");
								if (span.length == 1) {
									span.remove();
								}
								$('<span class="' + objectName + 'Text" style="color:red">'+ objTips + '</span>').appendTo('.' + obj.context.name);
								return false;
							}
						}
						if (objectName == "email") {
							if (ajaxFlag) {
								exists(obj, span);
								var emailText = $(".emailText");
								if ($(".emailText").length >= 1) {
									return false;
								}
							}
						}
						if (span.length == 1) {
							span.remove();
						}
						obj.removeClass("input_validation-failed");
					} else {
						obj.removeClass("select_validation-failed");
					}
					return true;
				}
			} else {
				if (!reg.test(objdefValue)) {
					if (obj.is(":input") || obj.is(":textarea")) {
						obj.addClass("input_validation-failed");
						if (span.length == 1) {
							span.remove();
						}
						$('<span class="' + objectName + 'Text" style="color:red">'+ objTip + '</span>').appendTo('.' + obj.context.name);
					}
					return false;
				}
			}
		}
		return true;
	}
	
	
	function vaildateDevName(){
		var obj=$("#entryDevName");
		var reg = new RegExp(obj.attr("reg"));
		var objValue = obj.attr("value");
		var objdefValue = obj.attr("defvalue");
		var objTip = obj.attr("tip");
		var objTips = obj.attr("tips");
		var objectName = obj.context.name;
		var spanName = '.' + objectName + 'Text';
		var span = $(spanName);
		if (obj.parent().parent().css("display") == "none") {
			return true;
		}
		$(obj).parent().find('span').remove();
		if (objValue != '' || objdefValue == undefined) {
			if (!reg.test(objValue)) {
					obj.addClass("input_validation-failed");
					if (span.length == 1) {
						span.remove();
					}
					$('<span class="' + objectName + 'Text" style="color:red">'+objTip + '</span>').appendTo(obj.parent());
				return false;
				
			} else {
				if (obj.is(":input")) {
					if (objectName == "email") {
						if (ajaxFlag) {
							exists(obj, span);
							var emailText = $(".emailText");
							if ($(".emailText").length >= 1) {
								return false;
							}
						}
					}
					if (span.length == 1) {
						span.remove();
					}
					obj.removeClass("input_validation-failed");
				} else {
					obj.removeClass("select_validation-failed");
				}
				return true;
			}
		} else {
			if (!reg.test(objdefValue)) {
					obj.addClass("input_validation-failed");
					if (span.length == 1) {
						span.remove();
					}
					$('<span class="' + objectName + 'Text" style="color:red">'+ objTip + '</span>').appendTo('.' + obj.context.name);
				return false;
			}
		}
	}
		
	//判断用户输入id是否正确
	var v_flag=0;
	function checkUserId(obj){
		var reg = new RegExp($("#entryDevId").attr("reg"));
		if(obj&&obj.value&&reg.test(obj.value)){
			$.ajax({
				url:'${pageContext.request.contextPath}/manage!checkUserById.do',
				type:'POST',
				data:'id='+obj.value,
				dataType:'text',
				beforeSend:function(){
						$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
				},
				success:function(data){
					$(obj).parent().find('img').remove();
					if($(obj).parent().find('span')){
						$(obj).parent().find('span').remove();
					}
					if(data&&data=="-1"){
						$(obj).after('<span class=reg_no>网站主不存在!!</span>');
					}else{
						$(obj).after('<span class=reg_ok>&nbsp;</span>');
						$("#entryDevName").val(data.split("|")[1]);
					}
				}
			});
		}
	}
	
	//判断id和名称
	function checkUserIdAndName(obj){
		if(v_flag==0){
			var devId=$("#entryDevId").val();
			var reg = new RegExp($("#entryDevId").attr("reg"));
			var reg_dvName = new RegExp($("#entryDevName").attr("reg"));
			if(vaildateDevName()){
				if(devId&&reg.test(devId)&&obj&&obj.value&&reg_dvName.test(obj.value)){
					$.ajax({
						url:'${pageContext.request.contextPath}/manage!checkUserByIdName.do',
						type:'POST',
						data:'id='+devId+'&name='+obj.value,
						dataType:'text',
						beforeSend:function(){
							$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
						},
						success:function(data){
							$(obj).parent().find('img').remove();
							if($(obj).parent().find('span')){
								$(obj).parent().find('span').remove();
							}
							if(data&&data=="1"){
								$(obj).after('<span class=reg_ok>&nbsp;</span>');
							}else{
								$(obj).after('<span class=reg_no>网站主名称与网站主id不匹配!!</span>');
							}
						}
					});
				}
			}
		}
	}
	
	
	//判断键盘输入，定时200毫秒去后台查询
	function searchSuggest(event){
		e=event?event :(window.event ? window.event : null);
		var keyCode=e.keyCode||e.which||e.charCode;
		if((keyCode>=48&&keyCode<=105)||keyCode==8){
			window.setTimeout(searchSuggestAction, 200);
		}
	}
	
	//判断键盘输入，定时200毫秒去后台查询
	function searchSuggest2(event){
		e=event?event :(window.event ? window.event : null);
		var keyCode=e.keyCode||e.which||e.charCode;
		if((keyCode>=48&&keyCode<=105)||keyCode==8){
			window.setTimeout(searchSuggestAction2, 200);
		}
	}
	var ie6=!-[1,]&&!window.XMLHttpRequest;
	
	
	var s_idnex=1;
	function searchDevNameKeydown(event) {
		var s_len=$("#search_suggest2 div").length;
		e=event?event :(window.event ? window.event : null);
		var keyCode=e.keyCode||e.which||e.charCode;
		
		if($("#search_suggest2 div[class='suggest_link_sel']").html()==null){
			$("#search_suggest2 div:first").attr("class","suggest_link_sel");
			s_idnex=1;
		}
		if((keyCode==40)||keyCode==38){
			var toDown=(keyCode==40);
			var current=$("#search_suggest2 div[class='suggest_link_sel']");
			if(toDown){
				s_idnex=s_idnex+1;
			}else{
				s_idnex=s_idnex-1;
			}
			var next;
			if(s_idnex==s_len+1){
				next=$("#search_suggest2 div:first");
				s_idnex=1;
			}else if(s_idnex==0){
				next=$("#search_suggest2 div:last");
				s_idnex=s_len;
			}else{
				if(toDown){
					next=current.next();
				}else{
					next=current.prev();
				}
				
			}
			
			next.attr("class","suggest_link_sel");
			current.attr("class","suggest_link");
		}

	
		if(keyCode==13){
			var obj=$("#search_suggest2 div[class='suggest_link_sel']");
			s_idnex=1;
			
			setSearch2(obj.html(),obj.attr("v_id"));
			
		}
		
	}
	
	//获取提示方法
	function searchSuggestAction2(){
		var str=$("#searchDevName").val();
		if(str){
			$.ajax({
				url:'${pageContext.request.contextPath}/manage!getUsersByName.do',
				type:'POST',
				data:'name='+str,
				dataType:'text',
				beforeSend:function(){},
				success:function(data){
					var ss = document.getElementById('search_suggest2');
					ss.innerHTML = '';
					if(data){
					var arr =data.split("|");
					if(arr.value = " "){
						document.getElementById('search_suggest2').style.display="none";
					}
					if(ie6&&arr.length>2){
						//简单处理i6下下拉框不能被div遮盖
						$("#searchSetType").hide();
					}

					for(i=0; i < arr.length; i++) {
						var suggest = '<div v_id="'+arr[i].split("-")[1]+'" onmouseover="javascript:suggestOver(this);" ';
						suggest += 'onmouseout="javascript:suggestOut(this);" ';
						suggest += 'onclick="javascript:setSearch2(this.innerHTML,'+arr[i].split("-")[1]+');" ';
						suggest += 'class="suggest_link">' + arr[i].split("-")[0] + '</div>';
						ss.innerHTML += suggest;
						if(ss.innerHTML.length > 0){
							document.getElementById('search_suggest2').style.display="block";
						}
					}
					$(document).one("click", function () {//对document绑定一个影藏Div方法 
						$("#search_suggest2").hide(); 
						if(ie6&&arr.length>2){
							$("#searchSetType").show();
						}
					});
				
					$("#search_suggest2 div:first").attr("class","suggest_link_sel");
				
				}
					
			}
			});
		
		}
		
	}
	
	var e_idnex=1;
	function entryDevNameKeydown(event) {
		var e_len=$("#search_suggest div").length;
		e=event?event :(window.event ? window.event : null);
		var keyCode=e.keyCode||e.which||e.charCode;
		if($("#search_suggest div[class='suggest_link_sel']").html()==null){
			$("#search_suggest div:first").attr("class","suggest_link_sel");
			e_idnex=1;
		}
		if((keyCode==40)||keyCode==38){
		
			var toDown=(keyCode==40);
			var current=$("#search_suggest div[class='suggest_link_sel']");
			if(toDown){
				e_idnex=e_idnex+1;
			}else{
				e_idnex=e_idnex-1;
			}
			var next;
			if(e_idnex==e_len+1){
				next=$("#search_suggest div:first");
				e_idnex=1;
			}else if(e_idnex==0){
				next=$("#search_suggest div:last");
				e_idnex=e_len;
			}else{
				if(toDown){
					next=current.next();
				}else{
					next=current.prev();
				}
				
			}
			
			next.attr("class","suggest_link_sel");
			current.attr("class","suggest_link");
		}
		if(keyCode==13){
			var obj=$("#search_suggest div[class='suggest_link_sel']");
			setSearch(obj.html(),obj.attr("v_id"));
		}
		
	}
	
	//获取提示方法
	function searchSuggestAction(){
		var str=$("#entryDevName").val();
		if(str){
			$("#entryDevName").attr("voff","false");
			$.ajax({
				url:'${pageContext.request.contextPath}/manage!getUsersByName.do',
				type:'POST',
				data:'name='+str,
				dataType:'text',
				beforeSend:function(){},
				success:function(data){
					if(data){
						v_flag=1;
						var ss = document.getElementById('search_suggest');
						ss.innerHTML = '';
						var arr =data.split("|");
						if(arr.value = " "){
							document.getElementById('search_suggest').style.display="none";
						}
						for(i=0; i < arr.length; i++) {
							var suggest = '<div v_id='+arr[i].split("-")[1]+' onmouseover="javascript:suggestOver(this);" ';
							suggest += 'onmouseout="javascript:suggestOut(this);" ';
							suggest += 'onclick="javascript:setSearch(this.innerHTML,'+arr[i].split("-")[1]+');" ';
							suggest += 'class="suggest_link">' + arr[i].split("-")[0] + '</div>';
							ss.innerHTML += suggest;
							if(ss.innerHTML.length > 0){
								document.getElementById('search_suggest').style.display="block";
							}
						}
						$(document).one("click", function () {//对document绑定一个影藏Div方法 
							$("#search_suggest").hide(); 
						});
						
						$("#search_suggest div:first").attr("class","suggest_link_sel");
					}else{
						v_flag=0;
					}
				}
			});
		
		}
		
	}
	//Mouse over function
	function suggestOver(div_value) {
		div_value.className = 'suggest_link_over';
	}
	//Mouse out function
	function suggestOut(div_value) {
		div_value.className = 'suggest_link';
	}
	

	//Click function
	function setSearch(value,id) {
		document.getElementById('search_suggest').style.display="none";
		document.getElementById('entryDevName').value = value;
		document.getElementById('search_suggest').innerHTML = '';
		$("#entryDevId").val(id);
		v_flag=0;
		if($("#entryDevName").parent().find('span')){
			$("#entryDevName").parent().find('span').remove();
		}

		$("#entryDevName").after('<span class=reg_ok>&nbsp;</span>');
		$("#entryActName").focus();
	}
	
	function setSearch2(value,id) {
		document.getElementById('search_suggest2').style.display="none";
		document.getElementById('searchDevName').value = value;
		document.getElementById('search_suggest2').innerHTML = '';
		if(ie6&&arr.length>2){
			$("#searchSetType").show();
		}
	}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
				<div class="">网站主奖励明细</div>

				<form id="updateDevListAwardDetail" name="updateDevListAwardDetail" method="post" action="manage!updateDevListAwardDetail.do">
						<table>
							<tr>
								<td><font style="margin-right: 14px;" color="blue">网站主id:</font></td>
								<td class="entryDevId">
									<input id="entryDevId" name="entryDevId" type="text" maxlength="20" style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;" reg="^[0-9]+$" tip="请输入正确网站主id !"  onblur="checkUserId(this);"/>
								</td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">网站主名称:</font></td>
								<td class="entryDevName">
									<div>
										<input id="entryDevName" onkeydown="entryDevNameKeydown(event);" name="entryDevName" type="text" maxlength="30" onkeyup="searchSuggest(event);" voff="false" style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;" reg="^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$" tip="请输入正确网站主名称 !" onblur="checkUserIdAndName(this);" />
									</div>
									<div id="search_suggest" style="display:none;" onkeypress="search_suggest_keypress();">
									</div>
								</td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">活动名称:</font></td>
								<td class="entryActName"><input id="entryActName"
									name="entryActName" type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									reg="^\S{1,20}$" tip="请输入活动名称(请勿输入空格) !" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 53px;" color="blue">金额:</font></td>
								<td class="entryActMoney"><input id="entryActMoney"
									name="entryActMoney" type="text" maxlength="20"
									style="border: #999 1px solid; height: 20px; background: #fff no-repeat right;"
									reg="^\d+\.\d{2}$|^[0-9]+$|^\d+\.\d{1}$" tip="请输入正确金额(两位小数) !" /></td>
							</tr>
							<tr>
								<td><font style="margin-right: 11px;" color="blue">活动周期:</font></td>
								<td><input type="text"
									id="entryActCycleBegin" name="entryActCycleBegin"
									maxlength="20" onfocus="WdatePicker()" class="Wdate"
									reg="^.{1,10}$" tip="开始时间不能空 !" /></td>
							</tr>
							<tr>
								<td>至</td>
								<td class="entryActCycleEnd"><input type="text"
									id="entryActCycleEnd" name="entryActCycleEnd" maxlength="20"
									onfocus="WdatePicker()" class="Wdate" reg="^.{1,10}$"
									tip="结束时间不能空 !" /></td>
							</tr>
						</table>						
				</form>					
			</div>
		</div>
		</div>
	</div>
</body>
</html>