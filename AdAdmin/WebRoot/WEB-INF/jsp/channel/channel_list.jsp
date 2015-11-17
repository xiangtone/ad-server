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
<title>运营管理后台活动查询统计</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/m-button.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
/**
 * 查看渠道查看
 *	
 **/
 /**
  * 修改渠道
  *	
  **/
 function findchannel(chaId){
 		var url = "manage!channelInfo.do?chaId=" + chaId;
 		new $.dialog({
 			title:'渠道信息查看',
 			page:url,
 			width:675,
 			height:520,
 			drag:true,
 			resize:true,
 			cover:true,
 			maxBtn:false}).ShowDialog();
 }
 
function channelSeal(id){
		var url = "manage!channelSeal.do?id="+id;
		window.location.href=url;
}
function channelActivation(id){
		var url = "manage!channelActivation.do?id="+id;
		window.location.href=url;
}

function refresh(){
	$("#my_form").submit();
}

/**
 * 全选
 **/
function selectAll(value){
	var b=value;
	$("#tb input:checkbox").attr("checked", b);
}
/**
 * 添加渠道
 *	
 **/
function addchannel(){
	var url = "manage!addchannel.do";
	new $.dialog({
		title:'添加渠道',
		page:url,
		width:475,
		height:390,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}



function delchannel(){
	if($("#tb input:checkbox[checked][@value]").length<=0){
		alert("提示：请选择要删除的渠道！！");
		return;
	}
	var ids=getCheckedId();
	$.ajax({
		url:'${pageContext.request.contextPath}/manage!delChannel.do',
		type:'POST',
		data:'ids='+ids,
		dataType:'text',
		beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("删除成功");
					refresh();
				}
			}
			
		}
	});
}

//获取所有选中的id
function getCheckedId(){
	var ids="";
	if($("#tb input:checkbox[checked][@value]").length>0){
		$("#tb input:checkbox[checked][@value]").each(function(){
			if(ids){
				ids=ids+","+$(this).val();
			}else{
				ids=$(this).val();
			}
		});
	}
	return ids;
}


/**
 * 修改渠道
 *	
 **/
function editchannel(id){
		var url = "manage!editchannel.do?id="+id;
		new $.dialog({
			title:'修改渠道',
			page:url,
			width:675,
			height:520,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}
/**
 * tb效果
 *	
 **/
$(document).ready(function (){
	
});	
</script>
</head>
<body>
	<div class="main" >
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>渠道账号管理</legend>
						<div id="search_bar">
							<form action="manage!findChannelList.do" method="post" id="my_form">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>渠道帐号</td>
													<td>
														<input name="channel_emails" id="channel_emails" type="text" value="${bean.channel_emails}" />
													</td>
													<td>渠道名称</td>
													<td>
														<input name="channel_names" id="channel_names" type="text" value="${bean.channel_names}" />
													</td>
												</tr>
												<tr>
													<td>渠道联系人</td>
													<td>
														<input name="real_names" id="real_names" type="text" value="${bean.real_names}" />
													</td>
													<td>渠道负责人</td>
													<td>
														<input name="channe_managers" id="channe_managers" type="text" value="${bean.channe_managers}" />
													</td>
												</tr>
												<tr>
													<td>合作方式：</td>
													<td>
														<input name="channe_modes"	type="radio" value="-1" checked="checked" <c:if test="${bean.channe_modes eq -1}">checked="checked"</c:if> />所有
														<input name="channe_modes" type="radio" value="0" <c:if test="${bean.channe_modes eq 0}">checked="checked"</c:if> />代理
														<input name="channe_modes" type="radio" value="1" <c:if test="${bean.channe_modes eq 1}">checked="checked"</c:if> />SDK
														<input name="channe_modes" type="radio" value="2" <c:if test="${bean.channe_modes eq 2}">checked="checked"</c:if> />API
													</td>
													<td>平台类型</td>
													<td>
														<select name="os" id="os" style="width: 155px;">
															<option value="" >全部</option>
															<option value="android" <c:if test="${bean.os == 'android'}">selected="selected"</c:if>>android</option>
															<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if>>ios</option>
														</select>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
				</fieldset>
				<div>
					<input type="button"  class="btn"  value="删除" onclick="delchannel()" />
					<input type="button" class="btn" value="新增" onclick="addchannel()" />
				</div>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
					<tr class="tr_td">
						<th><input id="checkbox" type="checkbox" value="${k.id}" onclick="selectAll(this.checked);" /></th>
						<th>渠道ID</th>
						<th>渠道名称</th>
						<th>合作方式</th>
						<th>渠道联系人</th>
						<th>用户名</th>
						<th>平台类型</th>
						<th>渠道负责人</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list}" var="k">
						<tr style="text-align: center;">
							<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${k.id}" /></td>
							<td>${k.id}</td>
							<td>
								<a href="javascript:void(0);" onclick="findchannel('${k.id}')">${k.name}</a>
							</td>
							<td>
								<c:choose>
									<c:when test="${k.channe_mode == '0'}">
									 代理
								</c:when>
									<c:when test="${k.channe_mode == '1'}">
								      SDK  
								</c:when>
									<c:otherwise>
								   API
								</c:otherwise>
								</c:choose>
							</td>
							<td>${k.real_name}</td>
							<td>${k.email}</td>
							<td>${k.os}</td>
							<td>${k.channe_manager }</td>
							<td>
								<c:if test="${k.status == 1}">
									<input type="button" value="封号" onclick="channelSeal('${k.id}')" class="btn" />
								</c:if>
								<c:if test="${k.status == -1}">
									<input type="button" value="激活" onclick="channelActivation('${k.id}')" class="btn" />
								</c:if>
								<input type="button" value="修改" onclick="editchannel('${k.id}');" class="btn" />
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>


				</table>
				</div>
				<div style="display: block;height: 15px;"></div>
				${pageInfo.pageInfoStr}
			</div>
		</div>
	</div>
</body>
</html>