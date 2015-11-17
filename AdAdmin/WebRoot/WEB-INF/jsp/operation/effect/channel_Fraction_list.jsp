<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>

<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	//手续费
	function confirm(id) {
		var confirm = document.getElementById(id + "_confirm_fen");
		var confirm_num = document.getElementById(id + "_confirm_num");
		var overplus = document.getElementById("overplus");
		if (confirm.value == "") {
			confirm.value = 0;
		}
		if (overplus.value <= 0) {
			alert("渠道包分数为0！");
		}		
		var overplus_number = parseFloat(parseFloat(overplus.value)
				- parseFloat(confirm_num.value)+parseFloat(confirm.value));
		if (overplus_number >= 0) {
			$("#overplus").val(overplus_number);
			$("#" + id + "_confirm_fen").val(confirm_num.value);
		} else {
			alert("已经没有可分的效果数！");
			$("#" + id + "_confirm_num").val(overplus.value);
		}
	}

	
	//保存
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		num();
	});
	
	
	
function confirm(){
	var campaign_id=$("#campaign_id").val();
	var start_time=$("#start_time").val();
	var end_time=$("#end_time").val();
	var param='campaign_id='+campaign_id+'&start_time='+start_time+'&end_time='+end_time;
	$.ajax({
		url:'${pageContext.request.contextPath}/operation/getAdByCampaign.do',
		type:'POST',
		data:param,
		dataType:'text',
		beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj){
					var str="";
					var perent_id="";
					for(var i=0;i<dataObj.length;i++){
						str=str+'<tr>'
						+'<td>'+start_time+'~'+end_time+'</td>'
						+'<td>'+dataObj[i].media_id+'</td>'
						+'<td>'+dataObj[i].media_name+'</td>'
						+'<td>'+dataObj[i].type_name+'</td>'
						+'<td><input type="hidden" name="id" value="'+dataObj[i].id+'" /><input type="text" name="price" value="'+dataObj[i].price+'" /></td>'
						+'<td><input type="text" name="confirm_num"  value="" /></td>'
						+'</tr>';
						perent_id=dataObj[i].parent_id;
						$("#effect_id").val(perent_id);
					}
					$("#tb tr:last-child").after(str);
				}
				if(dataObj.status=='1'){
					alert("操作成功！！");
					refresh();
				}
			}
			
		}
	});
}
	

 function num(){
	$("#confirm_form").ajaxSubmit(function(data) {
		if (data) {
			var dataObj = eval("(" + data + ")");//转换为json对象 
			if (dataObj.status == 1) {
				alert("操作成功！！");
				dg.curWin.refresh();
			}else if(dataObj.status = -1) {
				alert("操作失败" + dataObj.error);
				dg.curWin.refresh();
			}else{
				alert("操作失败，请重试！！");
				dg.curWin.refresh();
			}
		} else {
			alert("操作失败，请重试！！");
			dg.curWin.refresh();
		}
	});
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div>
						<table width="500px;">
							<tr>
								<td align="right">活动id：</td>
								<td class="companyName" align="left">
									<input	name="companyName" type="text" id="campaign_id" reg="^[\u4e00-\u9fa5\w\W]{4,50}$" tip="请输入4-50个字符  " maxlength="50" value="${findAdvVo.companyName}" />
								</td>
							</tr>
							<tr>
								<td align="right">开始日期：</td>
								<td class="companyAddress" align="left">
									<input name="companyAddress" type="text" id="start_time" maxlength="100" value="${findAdvVo.companyAddress}" class="Wdate" readonly="readonly" onclick="WdatePicker();"  />
								</td>
							</tr>
							<tr>
								<td align="right">截止日期：</td>
								<td class="companyAddress" align="left">
									<input name="companyAddress" type="text" id="end_time"  maxlength="100" value="${findAdvVo.companyAddress}" class="Wdate" readonly="readonly" onclick="WdatePicker();"  />
								</td>
							</tr>
						</tr>
					</table>
					</div>
					<table>
						<tr>
							<td>
								<input type="button" value="添加" onclick="confirm();" />
							</td>
						</tr>
					</table>
					<form name="update" id="confirm_form" method="post"	action="operation/addConfirmNum.do">
						<input name="effect_id" value="" id="effect_id" type="hidden" />
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
							<tr class="tr_td tb_head">
								<td>效果时间</td>
								<td>媒体ID</td>
								<td>媒体名称</td>
								<td>广告形式</td>
								<td>接入单价</td>
								<td>广告主确认数</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
