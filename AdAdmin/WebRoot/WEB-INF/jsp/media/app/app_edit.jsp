<%@ page language="java" import="java.util.*,cn.adwalker.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者应用审核</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/upload/uploadCommon.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/onurl.js"></script>
	<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		   dg = frameElement.lhgDG;
			dg.addBtn("save","保存",function(){
				form_submit();
			});
		   init();
	   });
	
	function form_submit(){
		if(vaildateForm("updateAppManage")){
			$("#updateAppManage").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						
					}else{
						alert("提交失败！！");
					}
				}else{
					alert("提交失败！！");
				}
			});
		};
	}
	   
	   function init(){
		   var page_type='${vo.page_type}';
		   if(page_type){
			   if(page_type.indexOf(",")){
				   var arr=page_type.split(",");
				   for(var i=0;i<arr.length;i++){
					   $("#pagetype_"+arr[i]).attr("checked","checked");
				   }
			   }else{
				   $("#pagetype_"+page_type).attr("checked","checked");
			   }
		   }
		   
		   var ad_res='${vo.ad_res}';
		   if(ad_res){
			   if(ad_res.indexOf(",")){
				   var arr=ad_res.split(",");
				   for(var i=0;i<arr.length;i++){
					   $("#ad_res_"+arr[i]).attr("checked","checked");
				   }
			   }else{
				   $("#ad_res_"+page_type).attr("checked","checked");
			   }
		   }
		   
		  
		   $("#app_scale").blur(function(){
			   var flag=false;
			   var objValue=$(this).val();
			   if(objValue){
				   objValue=$.trim(objValue);
				   if((parseInt(objValue))>=0&&(parseInt(objValue))<=251){
					   flag=true;
				   }
				}
			   if(!flag){
				   $(this).addClass("input_validation-failed");
				   $(this).siblings().css('display', '');
				   $(this).val('');
			   }else{
				   $(this).removeClass("input_validation-failed");
				   $(this).siblings().css('display', 'none');
			   }
			 });
		   
		   var os="${vo.os}";
			if(os=="ios"){
				$("#os_ios").attr("checked","checked");
			}
	   }
</script>
</head>
<body>
	<div style="display: block;height: 500px;width: 900px;background-color: white;">
		<div style="display: block;float:left;width: 600px;height: 400px;">
			<form name="updateAppManage" action="updateApp.do"	id="updateAppManage" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${vo.id}" />
				<table>
					<tr>
						<td width="100" valign="top">平台类型：</td>
						<td class="os">
							<input name="os" type="radio" value="android" checked="checked"  disabled="disabled"/>ANDROID
							<input id="os_ios" name="os" type="radio" value="ios" disabled="disabled"/>IOS
						</td>
					</tr>
					<tr>
						<td width="100" valign="top">应用名称：</td>
						<td class="name">
							<input name="name" id="name" type="text" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="应用名不能为空"	value="${vo.name }" maxlength="20" />* <font color="red">${errMsg_appName}</font>
						</td>
					</tr>
					<tr>
						<td valign="top">关键字：</td>
						<td class="keyword">
							<input name="keyword" id="keyword"	type="text" reg="^[a-zA-Z0-9\u4e00-\u9fa5\,]{0,50}$" tip="请输入不超过50位的字符 多个关键字用英文逗号分开" value="${vo.keyword}" maxlength="50" />
						</td>
					</tr>
					<tr>
						<td valign="top"></td>
						<td>多个关键字请用空格分隔</td>
					</tr>
					<tr>
						<!-- 分类start -->
						<td valign="top">应用类别：</td>
						<td>
							<select name="categoryId" id="type" reg="[^0]">
								<c:forEach items="${ecList}" var="entry">
									<option value="${entry.id}"	<c:if test="${vo.categoryId eq entry.id}"> selected="selected" </c:if>>${entry.fname}-${entry.name}</option>
								</c:forEach>
						</select>
						* 
						</td>
						<!-- 分类end -->
					</tr>
					<tr>
						<td valign="top">广告形式：</td>
						<td>
							<input id="pagetype_0" type="checkbox" name="types" value="0" />积分墙
							<input id="pagetype_1" type="checkbox" name="types" value="1" />推荐墙
							<input id="pagetype_4" type="checkbox" name="types" value="4" />BANNER
							<input id="pagetype_5" type="checkbox" name="types" value="5" />插屏
						</td>
					</tr>
					<tr>
						<td valign="top">广告来源：</td>
						<td>
							<input id="ad_res_ADWALKER"  type="checkbox" name="ad_res" value="ADWALKER"  checked="checked" />行云
							<input id="ad_res_YOUMI" type="checkbox" name="ad_res" value="YOUMI" />有米
							<input id="ad_res_CHUKONG" type="checkbox" name="ad_res" value="CHUKONG" />触控
							<input id="ad_res_KUAIYOU" type="checkbox" name="ad_res" value="KUAIYOU" />快友
						</td>
					</tr>
					<tr>
						<td valign="top">媒体评价</td>
						<td>
							<input type="text" id="app_scale" name="scale" value="${vo.scale}" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							<font color="red" style="display: none;">评价值为0~250的数字</font>
						</td>
					</tr>
					<tr>
						<td align="right">媒体负责人：</td>
						<td align="left">
							<select id="app_manager_id" name="app_manager_id" style="width: 153px;">
								<option value="">请选择</option>					
								<c:forEach items="${appMediaList}" var="entity">
									<option value="${entity.id}" <c:if test="${vo.app_manager_id eq entity.id}"> selected="selected" </c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td valign="top">是否单独投放</td>
						<td>
							<div>
					     		<input id="placement" name="placement" type="radio" value="${vo.id}" <c:if test="${vo.placement eq vo.id}"> checked="checked" </c:if> />是
					    		 <input id="placement" name="placement" type="radio" value="0" <c:if test="${vo.placement eq 0}"> checked="checked" </c:if> />否
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top">是否获取经纬度</td>
						<td>
							<div>
					     		<input id="is_coordinate" name="is_coordinate" type="radio" value="1" <c:if test="${vo.is_coordinate eq 1}"> checked="checked" </c:if> />是
					    		<input id="is_coordinate" name="is_coordinate" type="radio" value="0" <c:if test="${vo.is_coordinate eq 0}"> checked="checked" </c:if> />否
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top">详细介绍：</td>
						<td class="longDesc">
							<textarea name="longDesc" id="longDesc" reg="^(?=.*?\S)[\s\S]{0,500}$"	tip="详细描述在1至100个字符" cols="30" rows="5" maxlength="100">${vo.longDesc}</textarea>*
							<font color="red">${errMsg_appLongDesc}</font>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
