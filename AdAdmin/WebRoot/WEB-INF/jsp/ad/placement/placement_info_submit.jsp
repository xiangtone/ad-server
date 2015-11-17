<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${business_title}</title>
<link type="text/css" rel="stylesheet" id="cssfile"	href="<%=request.getContextPath()%>/css/landscape.css?v=${version}" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.SetCancelBtn("关闭",function(){
			dg.cancel();
		});
		dg.addBtn("audit","提交审核",function(){
			$("#placement_status").val(-10);
			submit_form();
			
		});
		init_wall_type('${page_type}');
	});
	
	
	function submit_form(){
		var can_submit='<c:if test="${empty errorList}">true</c:if>';
		if(can_submit=='true'){
			$("#addPubAct").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						$("#error_tip").html(dataObj.error);
						$("#error_tip").css("display","block");
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}
			});
		}else{
			alert("您提交的投放存在错误信息，请调整后再提交审核！！");
		}
	}
	function init_wall_type(page_str){
		var wall_type_3=0;
		var wall_type_4=1;
		var wall_type_5=4;
		var wall_type_6=5;
		if(page_str){
			var arr=page_str.split(",");
			for(var i=3;i<7;i++){
				var b=false;
				for(var j=0;j<arr.length;j++){
					if(eval("wall_type_"+i)==arr[j]){
						b=true;
						break;
					}
				}
				if(!b){
					$(".sub_"+i).hide();
				}
			}
		}else{
			for(var i=2;i<6;i++){
				$(".sub_"+i).hide();
			}
		}
		
	}
	
</script>
</head>
<body style="overflow: hidden;">
	<div id="content" style="padding: 0; height: 520px;">
		<div class="main_right" style="height: 320px; overflow: hidden;">
			<iframe frameborder="0" height="320px;" width="1061px;" src="manage!placementInfoTemp.do?id=${placement_id}"></iframe>
		</div>
		<div class="dot"></div>
		<div style="display: block;height: 150px;">
			<c:forEach items="${errorList}" var="vo">
				<span style="color: red;display:inherit;">${vo.msg}</span>
			</c:forEach>
			<c:if test="${empty errorList}">
			</c:if>
		</div>
		<div>
			<form id="addPubAct" name="addPubAct" action="manage!submitPlacement.do" method="post">
				<input name="id" value="${placement_id}" type="hidden" />
			</form>
		</div>
		
	</div>
</body>
</html>