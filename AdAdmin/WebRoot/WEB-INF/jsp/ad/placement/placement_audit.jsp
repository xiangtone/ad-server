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
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			$("#myform").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
						//dg.cancel();
					}else if(dataObj.status=-1){
						
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}
			});
			
			 
		});
	});
</script>
</head>
<body>
	<div id="content" style="padding: 0;overflow: hidden;">
		<div class="main_right"	style="height: 320px; overflow: hidden;">
			<iframe frameborder="0" height="320px;" width="1061px;" src="manage!placementInfoTemp.do?id=${placement_id}"></iframe>
		</div>
		<div class="blqy" style="height: 200px;">
			<h2 class="dot" style="text-align: left;">审核意见</h2>
			<div class="main_area" style="padding: 10px 0; float: left; height: 100px;">
				<form action="manage!auditPlacement.do" id="myform" method="post">
					<div style="display: none;">
						<input type="hidden" name="placement_id" value="${placement_id}" />
					</div>
					<p style="width: 800px; float: left; height: 20px; padding: 5px;">
						<label style="float: left; width: 80px; text-align: right;">是否通过：</label>
							<input type="radio" name="ispass" style="float: left;"	value="1" checked="checked" id="auditLog_isPass" />
						<span style="float: left;">是</span>
							<input type="radio" name="ispass" style="float: left; margin-left: 10px;" value="-1" id="auditLog_isPass" />否
					</p>
					<p style="width: 800px; float: left; height: 20px; padding: 5px;">
						<label
							style="height: 60px; line-height: 20px; float: left; width: 80px; text-align: right;">描述：</label>
						<textarea
							style="width: 400px; padding: 5px; float: left; height: 50px; border: 1px solid #9fcae0;"
							name="note" id="auditLog_auditNote"></textarea>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>