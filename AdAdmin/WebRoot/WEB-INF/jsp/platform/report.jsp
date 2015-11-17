<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
		function doSubmit(url,msg){
		    var alertMsg = "您是否确认重做 " + document.getElementById("date").value + " " + msg + "的报表数据？";
		    if(value="income"){
		    	alertMsg = "您是否确认重做  换量前台统计数据表  的报表数据？";
		    }
		    if(value="incomeBack"){
		    	alertMsg = "您是否确认重做  换量后台统计数据表  的报表数据？";
		    }
		    if(confirm(alertMsg)){
		    	post_data(url,msg);
		    }
		}
		
		function doSubmitVisitMessage(url){
		    var alertMsg = "您是否确认重做 " +  + "的日访问数据？";
		    if(confirm(alertMsg)){
				document.forms.gary.action = url;
				document.gary.submit();
		    }
		}
		
		
		
		function post_data(_url){
			var url='${pageContext.request.contextPath}/'+_url;
			var date=document.getElementById("date").value;
			$.ajax({
				url:url,
				type:'POST',
				data:'date='+date,
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
		}
		
		function refresh(){
			window.location.href='${pageContext.request.contextPath}/report.do';
		}

	</script>

</head>

<body>

	<form action="" id="gary" name="gary" method="post">
		报表定时任务管理
		<hr />
		统计时间:<input type="text" id="date" name="date" maxlength="20"	onfocus="WdatePicker()" class="Wdate" value="${statDate}" />*必填<br />
		<br />
		<a href="javascript:void(0)" onclick="doSubmit('report/tranceDevIncome.do','开发者收入')">开发者收入</a><br />
		<a href="javascript:void(0)" onclick="doSubmit('report/apiDataByDay.do','快友接口按天数据')">快友接口按天数据</a><br />
		<a href="javascript:void(0)" onclick="doSubmit('report/apiFinance.do','快友财务数据')">快友财务数据</a><br />
		<a href="javascript:void(0)" onclick="doSubmit('report/adStatus.do','更新广告状态')">广告状态</a><br />
		<a href="javascript:void(0)" onclick="doSubmit('report/channelByDay.do','快友渠道数据')">快友渠道数据</a><br />
		<a href="javascript:void(0)" onclick="doSubmit('report/devEffect.do','开发者效果数据')">开发者效果数据</a><br />
	</form>
</body>
</html>
