<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台媒体数量调控设置</title>
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

function adeductionAppManager(id){
	var rate = $("#"+id+"").val();
	if(rate){
		var url = "manage!deductionScaleAppUp.do";
		if(confirm("确认要执行该操作吗？")){
		
			$.ajax({
				url:url,
			type:'POST',
			data:'id='+id+'&rate='+$("#"+id+"").val(),
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
		alert("分配比例不能保存为空！");
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
						<legend>应用数量调控设置</legend>
						<div id="search_bar">
							<form action="manage!appDeductionUp.do" id="appDeductionUp" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>开发者ID</td>
													<td><input type="text" name="devID" value="${vo.devID}" /></td>
													<td>开发者账号</td>
													<td><input type="text" name="devMail" value="${vo.devMail}"/></td>
													<td>广告形式：</td>
													<td>
														<select name="type_id" id="adForm">
															<option value="">全部</option>
															<option value="0" <c:if test="${vo.type_id==0}">selected="selected" </c:if>>积分墙</option>
															<option value="1" <c:if test="${vo.type_id==1}">selected="selected" </c:if>>推荐墙</option>
															<option value="4" <c:if test="${vo.type_id==4}">selected="selected" </c:if>>Banner</option>
															<option value="5" <c:if test="${vo.type_id==5}">selected="selected" </c:if>>插屏</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>媒体ID</td>
													<td><input type="text" name="mediaID" value="${vo.mediaID}"/></td>
													<td>媒体名称</td>
													<td><input type="text" name="mediaName"	value="${vo.mediaName}"/></td>
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
													<td>媒体评级</td>
													<td>
														<select name="mediaRating" id="mediaRating">
															<option value="">全部</option>
															<option value="A" <c:if test="${vo.mediaRating =='A'}">selected="selected" </c:if>>A</option>
															<option value="B" <c:if test="${vo.mediaRating =='B'}">selected="selected" </c:if>>B</option>
															<option value="C" <c:if test="${vo.mediaRating =='C'}">selected="selected" </c:if>>C</option>
															<option value="D" <c:if test="${vo.mediaRating =='D'}">selected="selected" </c:if>>D</option>
															<option value="E" <c:if test="${vo.mediaRating =='E'}">selected="selected" </c:if>>E</option>
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
				<form>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1"	class="table_bod1 font_stat"  id="tb">
						<tr class="tr_td">
							<th><div class="date"></div>
							ID</td>
							<th>开发者ID</td>
							<th>开发者</td>
							<th>开发者账号</td>
							<th>媒体ID</td>
							<th>媒体名称</td>
							<th>状态</td>
							<th>广告形式</td>
							<th>平台</td>
							<th>媒体评级</td>
							<th>当前分配比例</td>
							<th>分配比例</td>
							<th>操作</td>
						</tr>
						<c:if test="${!empty List}">
							<c:forEach items="${List}" var="r" varStatus="status">
								<tr>
									<td>
										${r.id}
									</td>
									<td>
										${r.devID}
									</td>
									<td >
										${r.devName}
									</td>
									<td >
										${r.devMail}
									</td>
									<td>
										${r.appId}
									</td>
									<td >
										${r.appName}
									</td>
									<td>
										<c:choose>
										<c:when test="${r.status == 1}">
												打开 
										</c:when>
										<c:when test="${r.status == -1}">
												关闭
										</c:when>
									</c:choose>
									</td>
									<td>
										${r.adForm}
									</td>								
									<td>
										${r.os}
									</td>
									<td>
										${r.level}
									</td>
									<td>
										<fmt:formatNumber value="${r.rate}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 
									</td>
									<td>
										<input type="text" id="${r.id}" style="size: 20xp;width: 40px;" value="${r.rate}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" maxlength="4" />
									</td>
									<td>
										<input type="button" value="保存" onclick="adeductionAppManager('${r.id}');"/>
									</td>
									<td>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty List}">
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