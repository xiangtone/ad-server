<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台作弊方案设置</title>
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
		var url = "manage!saveOrModifyPrevent.do";
		var isDefault = $('input[name=isDefault_'+id+']:checked').val();
		var mess="确认要执行该操作吗？";
		var ado ="广告方案";
		if ($('input[name=adormedia_'+id+']:checked').val() ==1) {
			ado="媒体方案";
		}
		if (isDefault==1) {
			mess="确认要把【"+$("#name_"+id+"").val()+"】设置成【"+ado+"】的默认方案？";
		}
		if(confirm(mess)){		
			$.ajax({
			url:url,
			type:'POST',
			data:'id='+id+'&name='+$("#name_"+id+"").val()+'&isDefault='+$('input[name=isDefault_'+id+']:checked').val()+'&adormedia='+$('input[name=adormedia_'+id+']:checked').val(),
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
			if(dataObj.status=='-1'){
				alert("一个方案必须有1个默认方法！！");
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
						<legend>作弊方案设置</legend>
						<div id="search_bar">
							<form action="manage!preventList.do" id="appDeductionUp" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>方案名称</td>
													<td><input type="text" name="name"	value="${vo.name}"/></td>
													<td>方案是否默认：</td>
													<td>
														<select name="isDefault" id="isDefault">
															<option value="">全部</option>
															<option value="1" <c:if test="${vo.isDefault == 1}">selected="selected" </c:if>>是</option>
															<option value="0" <c:if test="${vo.isDefault == 0}">selected="selected" </c:if> >否</option>
														</select>
													</td>
													<td>方案类型：</td>
													<td>
														<select name="adormedia" id="adormedia">
															<option value="">全部</option>
															<option value="0" <c:if test="${vo.adormedia == 0}">selected="selected" </c:if>>广告方案</option>
															<option value="1" <c:if test="${vo.adormedia == 1}">selected="selected" </c:if> >媒体方案</option>
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
							方案ID</td>
							<th>方案名称</td>
							<th>是否是默认方法</td>
							<th>方案类型</td>							
							<th>操作</td>
						</tr>
						<tr>
							<td>${r.id}</td>																						
							<td><input type="text" id="name_0" style="width: 160px;" value=""/></td>
							<td>
								<input type="radio" id="isDefault_0" name="isDefault_0" value="1"/>是
								<input type="radio" id="isDefault_0" name="isDefault_0" value="0" checked="checked"/>否
							</td>									
							<td>
							    <input type="radio" id="adormedia_0" name="adormedia_0" value="0" checked="checked" />广告方案
								<input type="radio" id="adormedia_0" name="adormedia_0" value="1" />媒体方案
							</td>
							<td><input type="button" value="增加" onclick="saveOrModifyAppMedia('0');"/></td>
							<td></td>
						</tr>
						<c:if test="${!empty List}">
							<c:forEach items="${List}" var="r" varStatus="status">
								<tr>
									<td>${r.id}</td>																						
									<td><input type="text" id="name_${r.id}" style="width: 160px;" value="${r.name}"/></td>
									<td>
										<input type="radio" id="isDefault_${r.id}" name="isDefault_${r.id}" value="1" <c:if test="${r.isDefault==1}">checked</c:if> />是
										<input type="radio" id="isDefault_${r.id}" name="isDefault_${r.id}" value="0" <c:if test="${r.isDefault==0}">checked</c:if> />否
									</td>									
									<td>
									    <input type="radio" id="adormedia_${r.id}" name="adormedia_${r.id}" value="0" <c:if test="${r.adormedia==0}">checked</c:if> />广告方案
										<input type="radio" id="adormedia_${r.id}" name="adormedia_${r.id}" value="1" <c:if test="${r.adormedia==1}">checked</c:if> />媒体方案
									</td>
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