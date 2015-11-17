<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台渠道评级设置</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function applyDevAppManager(channelId,campaign_id,media_id){
	var score = $("#"+channelId+"").val();
	if(!score||score < 0 || score > 150){
		alert("输入的字符不合法,0-150之间是合法数据");
		$("#"+channelId+"").val(0);
		return;
	}else{
		score = score / 100;
	}
	var url = "manage!updateMediaScale.do";
	if(confirm("确认要执行该操作吗？")){
			//window.location.href=url;//不是驳回直接跳转
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+channelId+'&score='+score+'&campaign_id='+campaign_id+'&media_id='+media_id,
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
function jump(url){
	var a = document.createElement("a");
	if(!a.click) {
 		 window.location = url;
 		 return;
		 }
		a.setAttribute("href", url);
		a.style.display = "none";
		document.body.appendChild(a);
		a.click();
}

$(document).ready(function (){
});

function refresh(){
	$("#my_form").submit();
}	
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>渠道评级设置</legend>
						<div id="search_bar">
							<form action="manage!channelScaleList.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>渠道ID/名称</td>
													<td><input type="text" name="channel" value="${bean.channel}" /></td>
													<td>活动ID/名称</td>
													<td><input type="text" name="campaign" value="${bean.campaign}" /></td>
												</tr>
												<tr>
													<td>平台类型</td>
													<td>
														<select name="os" id="os">
															<option value="">全部</option>
															<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
															<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search(my_form);" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>	
								</table>	
							</form>
						</div>
					</fieldset>
				<form>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="font_stat" >
						<tr class="tr_td tb_head">
							<th>活动ID</th>
							<th>活动名称</th>
							<th>渠道ID</th>
							<th>渠道名称</th>
							<th>指数</th>
							<th>修改指数</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.campaign_id}
									</td>
									<td>
										${vo.campaign_name}
									</td>
									<td>
										${vo.media_id}
									</td>
									<td>
										${vo.media_name}
									</td>
									<td>
										${vo.scale_int}%
									</td>
									<td align="center">
										<input type="text" id="${vo.id}" style="size: 20xp;width: 40px;" value="${vo.scale_int}" onkeyup="value=value.replace(/[^\d]/g,'')" />
									</td>
									<td>
										<input type="button" value="保存" onclick="applyDevAppManager('${vo.id}','${vo.campaign_id}','${vo.media_id}');" />
									</td>
									<td>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty list}">
							<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
						</c:if>
					</table>
					</div>
					${pageInfo.pageInfoStr}
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>