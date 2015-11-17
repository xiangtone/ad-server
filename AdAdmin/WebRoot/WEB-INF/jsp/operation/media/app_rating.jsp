<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台应用评级设置</title>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function applyDevAppManager(mediaId){
	var score = $("#"+mediaId+"").val();
	if(!score||score < 0 || score > 150){
		alert("输入的字符不合法,0-150之间是合法数据");
		$("#"+mediaId+"").val(0);
		return;
	}else{
		score = score / 100;
	}
	var url = "manage!modifyMediaRating.do";
	if(confirm("确认要执行该操作吗？")){
			//window.location.href=url;//不是驳回直接跳转
		$.ajax({
			url:url,
			type:'POST',
			data:'mediaId='+mediaId+'&score='+score,
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

$(document).ready(function (){
});
	
function refresh(){
	$("#mediaRatingSetting").submit();
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
						<legend>应用评级设置</legend>
						<form action="manage!appScaleSetting.do" id="mediaRatingSetting" method="post">
							<table>
								<tr>
									<td>开发者ID</td>
									<td><input type="text" name="devID" value="${bean.devID}" /></td>
									<td>开发者账号</td>
									<td><input type="text" name="devMail" value="${bean.devMail}"/></td>
								</tr>
								
								<tr>
									<td>应用ID</td>
									<td><input type="text" name="mediaID" value="${bean.mediaID}" /></td>
									<td>应用名称</td>
									<td><input type="text" name="mediaName"	value="${bean.mediaName}"/></td>
								</tr>
								<tr>	
										
										<td>平台</td>
										<td>
											<select name="os" id="os">
												<option value="">全部</option>
												<option value="android" <c:if test="${bean.os =='android'}">selected="selected"</c:if>>android</option>
												<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if> >ios</option>
											</select>
										</td>
										<td>
											<input name="find" type="submit" id="mediaRatingSearch"	value="查询" />
										</td>
								</tr>
								
							</table>
								</form>
					</fieldset>
				<div class="main_table">
				<form>
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
						<tr class="tr_td tb_head">
							<th><div class="date"></div>
							开发者ID</th>
							<th>开发者账号</th>
							<th>应用ID</th>
							<th>应用名称</th>
							<th>平台类型</th>
							<th>修改评级</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.devId}
									</td>
									<td>
										${vo.devEmail}
									</td>
									<td>
										${vo.mediaId}
									</td>
									<td >
										${vo.mediaName}
									</td>
									<td>
										${vo.os}
									</td>
									<td align="center">
										<input type="text" id="${vo.mediaId}" style="size: 20xp;width: 40px;" value="${vo.scale_int}" onkeyup="value=value.replace(/[^\d]/g,'')" />
									</td>
									
									<td>
										<input type="button" value="保存" onclick="applyDevAppManager('${vo.mediaId}');"  />
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
					</form>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>
</html>