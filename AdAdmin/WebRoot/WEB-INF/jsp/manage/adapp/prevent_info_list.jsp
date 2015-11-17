<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台作弊方案信息设置</title>
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

function saveOrModifyAppMedia(id){
	if(id){
		var url = "manage!saveOrModifyPreventInfo.do";
		if(confirm("确认要执行该操作吗？")){		
			$.ajax({
			url:url,
			type:'POST',
			data:'id='+id+'&area='+$("#area_"+id+"").val()+'&schemeId='+$("#schemeId_"+id+"").val()+'&config='+$("#config_"+id+"").val(),
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
						<legend>作弊方案地区设置</legend>
						<div id="search_bar">
							<form action="manage!preventInfoList.do" id="appDeductionUp" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>方案：</td>
													<td>
													<select id="schemeId" name="schemeId" style="width:152px">
														<option value="">请选择方案</option>
														<c:forEach items="${listP}" var="p" varStatus="status">
														 <option value="${p.id}" <c:if test="${vo.schemeId==p.id}">selected="selected"</c:if>>${p.name}</option>
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
							<th><div class="date"></div>
							方案区域Id</td>
							<th>方案名称</td>
							<th>区域名称</td>
							<th>区域限制</td>							
							<th>操作</td>
						</tr>
						<tr>
							<td></td>																						
							<td>
								<select id="schemeId_0" name="schemeId_0" style="width:152px">
									<option value="">请选择方案</option>
									<c:forEach items="${listP}" var="p" varStatus="status">
									 <option value="${p.id}">${p.name}</option>
									 </c:forEach>							
								</select>	
							</td>
							<td>
							   <input type="text" id="area_0" style="width: 160px;" value=""/>								
							</td>									
							<td>
							   <input type="text" id="config_0" style="width: 60px;" value=""/>
							</td>
							<td><input type="button" value="增加" onclick="saveOrModifyAppMedia('0');"/></td>
							<td></td>
						</tr>
						<c:if test="${!empty List}">
							<c:forEach items="${List}" var="r" varStatus="status">
								<tr>
									<td>${r.id}</td>																															
									<td>
										<select id="schemeId_${r.id}" name="schemeId_${r.id}" style="width:152px">
											<option value="">请选择方案</option>
											<c:forEach items="${listP}" var="p" varStatus="status">
											 <option value="${p.id}" <c:if test="${r.schemeId==p.id}">selected="selected"</c:if>>${p.name}</option>
											 </c:forEach>							
										</select>		
									</td>
									<td><input type="text" id="area_${r.id}" style="width: 160px;" value="${r.area}"/></td>									
									<td><input type="text" id="config_${r.id}" style="width: 60px;" value="${r.config}"/><font style="color: red;">0标示限制，正整数标示可配置数，-1标示不限</font></td>
									<td><input type="button" value="保存" onclick="saveOrModifyAppMedia('${r.id}');"/></td>
									<td></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty List}">
							<td colspan="5" align="center" style="text-align: center;">暂无记录！</td>
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