<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css"/>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
function appBlack(id){
	var black1 = $("#"+id+"").val();
	if(black1){
	var url = "manage!updateApplicationBlack.do?applicationId="+id+"&black="+black1;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("保存成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
	//取消操作
	}else{
		alert("广告ID不能为空！");
	}
}

function selectCampaign(id) {
	var url = "appSelCampaign.do?app_id=" + id;
	new $.dialog({
		title:'活动信息',
		page:url,
		width:735,
		height:480,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}


function deleteBlack(id){
	var url = "manage!deleteApplicationBlack.do?applicationId="+id;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("删除成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
}
function refresh(){
	$("#package").submit();
}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>黑名单</legend>
						<div id="search_bar">
							<form action="manage!operationBlackList.do" method="post" id="package">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>应用ID</td>
													<td>
														<input name="app_id" id="app_id" type="text" value="${bean.app_id}" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td>应用名称</td>
													<td>
														<input name="app_name" id="app_name" type="text" value="${bean.app_name}" maxlength="50" />
													</td>
												</tr>				
												<tr>
													<td>平台类型</td>
													<td>
														<select name="os">
															<option value="">全部</option>
															<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
															<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
														</select>
													</td>
												</tr>				
											</table>
										</td>
										<td width="5%" valign="middle" align="center">
											<div style="width: 100px;height: 100%;">
												<input type="submit" style="cursor: pointer;" value="查询" />
												<input type="button" onclick="resetAll('package');" value="重置" onfocus="this.blur();" class="cx"/>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>			
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
						<tr class="tr_td tb_head">						
							<th>应用ID</th>
							<th>应用名称</th>
							<th>平台类型</th>
							<th>状态</th>
							<th>黑名单</th>
							<th>黑名单(活动ID)</th>
							<th>录入</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>${vo.id}</td>
									<td>${vo.name}</td>
									<td>${vo.os}</td>
									<td><c:choose>
											<c:when test="${vo.status == 1}">
												草稿
											</c:when>
											<c:when test="${vo.status == 2}">
								     			待审核
											</c:when>
											<c:when test="${vo.status == -1}">
								     			未通过
											</c:when>
											<c:when test="${vo.status == 3}">
								     		 	上线
											</c:when>
											<c:when test="${vo.status == -1}">
								     		 	下线
											</c:when>
											<c:otherwise>
								 				终止
											</c:otherwise>
										</c:choose></td>
									<td><escore:subStr len="50" value="${vo.black}" /></td>								
									<td>${vo.black_id}</td>								
									<td>
										<input type="text" id="${vo.id}" style="width: 120px;"  maxlength="200" />
									</td>
									<td>
										<input type="button" value="设置" onclick="selectCampaign('${vo.id}');"/>
										<input type="button" value="保存" onclick="appBlack('${vo.id}');"/>
										<input type="button" value="删除" onclick="deleteBlack('${vo.id}');"/>
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
				</div>
			</div>
		</div>
	</div>
</body>
</html>