<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title><%=company%>广告平台</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>		
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/user-profile.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/form-wizard.css" type="text/css" media="screen" />
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script type="text/javascript">
		    function selectApplication(obj){
				$("[name='categoryId']").removeAttr("checked");
		    	var appId = $(obj).val();
		    	if(appId != "") {
			    	var a=$(obj).find("option:selected").attr("oub");
					$("#os").html(a);
		    		$("#sp").html("");
			    	$.ajax({
						type : "get",   
						url : '${pageContext.request.contextPath}/getCategoryIds.action',   
						dataType : "text",
						data : {appId : appId},
						success : function(data) {
							data = "," + data;
							if(data != null && data != "") {
								$("input[name='categoryId']").each(function(){
									if(data.indexOf("," + $(this).val()) != -1) {
										$(this).attr("checked", "checked");
									}
								});
							}
						}   
					});
		    	} else {
		    		$("#os").html("");
		    	}
			}
		    function submitForm(){
		    	var categoryIds = "";
		    	$("input[name='categoryId']:checkbox:checked").each(function(){
		    		categoryIds += $(this).val() + ",";
				});
		    	if(categoryIds != "") {
		    		categoryIds = categoryIds.substring(0, categoryIds.length - 1);
		    	}
		    	$("#categoryIds").val(categoryIds);
				var b=true;
				var appId= $("#appId").val();
				if(appId == ""){
					b=false;
					$("#sp").html("请选择应用！");
				}
				if(b){
						$("#adSettingForm").ajaxSubmit(function(data){
							if(data=="true"){
								alert("修改成功！！");
								window.location.href='adSeting.action';
							}else{
								alert("修改失败，请重试！！");
								
							}
							
						});
					
				}
			}
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
	        <div id="pad-wrapper" class="users-list">
				<div class="row header"><h3>广告类型过滤设置</h3></div>
				<div class="table-products section">
					<form name="adSettingForm" id="adSettingForm" action="updateAdSeting.action" method="post">
						<input type="hidden" name="categoryIds" id="categoryIds"/>
						<div class="row filter-block">
							<div class="ui-dropdown">应用名：
								<select id="appId" name="appId" onchange="selectApplication(this)">
									<option value="">全部</option>
									<c:forEach items="${appList}" var="app">
										<option value ="${app.id}" oub="${app.os}">${app.name}</option>
									</c:forEach>
								</select>
							</div><span id="sp" style="color : red;"></span>
							<div class="ui-dropdown">平台：<span id="os"></span></div>
						</div>
						<div class="row filter-block">
							<div class="ui-dropdown">
								<h4 class="clearfix">请选择要过滤的广告分类：</h4>
								<div class="alert-a">注意：过滤一个或多个广告类别可能会大幅降低您的收入，请谨慎操作。</div>
								<table>
									<tr>
										<c:forEach items="${ecList}" var="entry" varStatus="s">
											<td><input name="categoryId" type="checkbox" value="${entry.id}">&nbsp;&nbsp;${entry.fname}-${entry.name}&nbsp;&nbsp;</td>
											<c:if test="${(s.index+1)%4==0}"></tr><tr></c:if>
										</c:forEach>
									</tr>
								</table>
								<div class="ui-dropdown"><button type="button" class="btn-glow primary" onclick="submitForm();">确认 </button></div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			selectedMenu("menu2_3");
		</script>
	</body>
</html>