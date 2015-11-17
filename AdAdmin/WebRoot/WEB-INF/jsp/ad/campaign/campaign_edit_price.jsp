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
<title>运营管理后台广告主发布广告</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			if(vaildateForm("addPubActPrice")){
				$("#addPubActPrice").ajaxSubmit(function(data){
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
			};
		});
	});
	
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<form id="addPubActPrice" name="addPubActPrice" action="manage!updateCampaignPrice.do" method="post" enctype="multipart/form-data">
				<div>
					<input type="hidden" name="id" id="id" value="${vo.id}" />
					<input type="hidden" name="placement_id" id="placement_id" value="${vo.placement_id}" />
					<input type="hidden" name="price_update" id="price_update" value="${vo.price}"/>
					<input type="hidden" name="os" id="os" value="${vo.os}" />
				</div>
				<table>
					<tr>
					<td>活动名称</td>
					<td>
					${vo.campaign_name}
					</td>
					</tr>
					<tr>
					<td>接入单价：</td>
						<td class="price">
								<input type="text" name="price" id="price" value="${vo.price}" reg="(^[0-9]{1,6})+([.]\d{1,2})?$" tip="亲,单价请输入数字哦！！" maxlength="6"/>元
						</td>
					</tr>
				</table>
</body>

</html>