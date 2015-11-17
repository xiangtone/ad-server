<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告防作弊</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.bgclor01 {
	background-color: #ACACAC !important;
}
</style>
<script type="text/javascript">
function saveOrModifyAdSend(id){
	if(id){
		var url = "manage!saveOrModifyAdSend.do";
		if(confirm("确认要执行该操作吗？")){		
			$.ajax({
			url:url,
			type:'POST',
			data:'id='+id+'&schemeId='+$("#schemeId_"+id+"").val()+'&adIpsegmentNum='+$("#adIpsegmentNum_"+id+"").val()+'&adIpNum='+$("#adIpNum_"+id+"").val()+'&adBssidNum='+$("#adBssidNum_"+id+"").val()+'&adLatlonNum='+$("#adLatlonNum_"+id+"").val(),
			dataType:'text',
		beforeSend:function(){
			//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
	},
	success:function(data){
		if(data){
			var dataObj=eval("("+data+")");//转换为json对象 
			if(dataObj.status=='1'){
				alert("操作成功！！");
			}
		}
		
	}
	});
	}
	}else{
		alert("媒体IP错误！！");
		}
}

function delAdSend(id){
	if(id){
		var url = "manage!delAdSend.do";
		if(confirm("确认要执行该操作吗？")){		
			$.ajax({
			url:url,
			type:'POST',
			data:'id='+id,
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
	}else{
		alert("媒体IP错误！！");
		}
}

function refresh(){
	$("#appDeductionUp").submit();
}
</script>
<!-- <style>
table,td{ border:1px solid #000; border-collapse:collapse}
</style> -->
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>广告防作弊设置</legend>
						<div id="search_bar">
							<form action="manage!adSendList.do" id="appDeductionUp" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告ID</td>
													<td><input type="text" name="id" value="${vo.id}" /></td>
													<td>活动名称</td>
													<td><input type="text" name="campaignName"	value="${vo.campaignName}"/></td>
													<td>平台类型：</td>
													<td>
														<select name="os" id="os">
															<option value="">全部</option>
															<option value="android" <c:if test="${vo.os == 'android'}">selected="selected" </c:if>>android</option>
															<option value="ios" <c:if test="${vo.os == 'ios'}">selected="selected" </c:if> >ios</option>
														</select>
													</td>
												</tr>
												<tr>
												    <td>广告状态：</td>
													<td>
														<select name="status" id="status">
															<option value="">全部</option>															
															<option value="1" <c:if test="${vo.status == 1}">selected="selected" </c:if> >上线</option>
															<option value="0" <c:if test="${vo.status == 0}">selected="selected" </c:if> >定时上线</option>
															<option value="-1" <c:if test="${vo.status == -1}">selected="selected" </c:if> >下线</option>
															<option value="-10" <c:if test="${vo.status == -10}">selected="selected" </c:if> >暂停</option>
															<option value="-20" <c:if test="${vo.status == -20}">selected="selected" </c:if> >定时下线</option>
															<option value="-30" <c:if test="${vo.status == -30}">selected="selected" </c:if> >超量下线</option>
															<option value="-5" <c:if test="${vo.status == -5}">selected="selected" </c:if> >活动结束</option>
														</select>
													</td>
													<td>活动状态：</td>
													<td>
														<select name="pcStatus" id="pcStatus">
															<option value="">全部</option>
															<option value="-50" <c:if test="${vo.pcStatus == -50}">selected="selected" </c:if> >活动草稿</option>
															<option value="-40" <c:if test="${vo.pcStatus == -40}">selected="selected" </c:if> >活动待审核</option>
															<option value="-30" <c:if test="${vo.pcStatus == -30}">selected="selected" </c:if> >待投放</option>
															<option value="-20" <c:if test="${vo.pcStatus == -20}">selected="selected" </c:if> >投放草稿</option>
															<option value="-10" <c:if test="${vo.pcStatus == -10}">selected="selected" </c:if> >投放待审核</option>
															<option value="1" <c:if test="${vo.pcStatus == 1}">selected="selected" </c:if> >已发布</option>
															<option value="-1" <c:if test="${vo.pcStatus == -1}">selected="selected" </c:if> >活动结束</option>															
														</select>
													</td>
													<td>方案</td>
													<td> 
													<select id="schemeId" name="schemeId">
														<option value="">请选择方案</option>
														<c:forEach items="${listP}" var="pp" varStatus="status">
														 <option value="${pp.id}" <c:if test="${vo.schemeId==pp.id}">selected="selected"</c:if>>${pp.name}</option>
														 </c:forEach>							
													</select>
													</td>
												</tr>																							
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<!-- <button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button> -->
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
				<form>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1"	class="table_bod1 font_stat"  id="tb">
						<tr class="tr_td">
							<th><div class="date"></div>广告ID</td>
							<th>活动名称</td>
							<th>平台类型</td>
							<th>广告状态</td>
							<th>活动状态</td>			
							<th>方案</td>
							<th>ip段激活数据限制</td>
							<th>ip激活数据限制</td>
							<th>bssid激活数限制</td>
							<th>同意经纬度下激活数限制</td>							
							<th>操作</td>
						</tr>
						<c:if test="${!empty List}">
							<c:forEach items="${List}" var="r" varStatus="status">
								<tr>
									<td>${r.id}</td>
									<td>${r.campaignName}</td>
									<td>${r.os}</td>															
									<td>
										<c:choose>
										        <c:when test="${r.status == 1}">上线</c:when>
												<c:when test="${r.status == 0}">定时上线</c:when>
												<c:when test="${r.status == -1}">下线</c:when>
												<c:when test="${r.status == 10}">暂停</c:when>
												<c:when test="${r.status == -20}">定时下线</c:when>
												<c:when test="${r.status == -30}">超量下线</c:when>
												<c:when test="${r.status == -5}">活动结束</c:when>
												<c:otherwise>未知</c:otherwise>
										</c:choose>								
									</td>
									<td>
										<c:choose>
									        <c:when test="${r.pcStatus == -50}">活动草稿</c:when>
											<c:when test="${r.pcStatus == -40}">活动待审核</c:when>
											<c:when test="${r.pcStatus == -30}">待投放</c:when>
											<c:when test="${r.pcStatus == -20}">投放草稿</c:when>
											<c:when test="${r.pcStatus == -10}">投放待审核</c:when>
											<c:when test="${r.pcStatus == 1}">已发布</c:when>
											<c:when test="${r.pcStatus == -1}">活动结束</c:when>
											<c:otherwise>未知</c:otherwise>
										</c:choose>																		
									</td>	
								   <td>
									<select id="schemeId_${r.id}" name="schemeId_${r.id}" style="width:152px">
										<option value="">请选择方案</option>
										<c:forEach items="${listP}" var="p" varStatus="status">
										 <option value="${p.id}" <c:if test="${r.schemeId==p.id}">selected="selected"</c:if>>${p.name}</option>
										 </c:forEach>							
									</select>
									</td>						
									<td><input type="text" id="adIpsegmentNum_${r.id}" style="size: 20xp;width: 40px;" value="${r.adIpsegmentNum}"/></td>
									<td><input type="text" id="adIpNum_${r.id}" style="size: 20xp;width: 40px;" value="${r.adIpNum}"/></td>
									<td><input type="text" id="adBssidNum_${r.id}" style="size: 20xp;width: 40px;" value="${r.adBssidNum}"/></td>
									<td><input type="text" id="adLatlonNum_${r.id}" style="size: 20xp;width: 40px;" value="${r.adLatlonNum}" maxlength="4" /></td>
									<td>
									   <input type="button" value="保存" onclick="saveOrModifyAdSend('${r.id}');"/>
									   <input type="button" value="删除" onclick="delAdSend('${r.id}');"/>
									  </td>									
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty List}">
							<td colspan="15" align="center" style="text-align: center;">暂无记录！</td>
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