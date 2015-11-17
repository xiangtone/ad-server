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
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/upload/uploadCommon.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/onurl.js"></script>
	<script type="text/javascript">
	   $(document).ready(function(){
		   var page_type='${page_type}';
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
		   
		   $("#app_scale").blur(function(){
			   var flag=false;
			   var objValue=$(this).val();
			   if(objValue){
				   objValue=$.trim(objValue);
				   if((parseInt(objValue))>=0&&(parseInt(objValue))<=151){
					   flag=true;
				   }
			  }
		   
		   var os="${app.os}";
			if(os=="ios"){
				$("#os_ios").attr("checked","checked");
			}
	   });
		
		function applyDevAppManager(status,appId){
			var url = "manage!auditDevApp.do?status="+status+"&appId="+appId;
			if(confirm("确认要执行该操作吗？")){
				if(status=="NOTPASS"){
					var reason = window.prompt("请填写驳回理由", "");
					if(reason){	
					reason = encodeURIComponent(reason);			
					var url =url+"&reason="+reason;
						//window.location.href=encodeURI(url);//转码
						jump(encodeURI(url));
					}else{
						return;
					}
				}else{
					//window.location.href=url;//不是驳回直接跳转
					jump(url);
				}
			}//取消操作
		}
		function jump(url){
			var a = document.createElement("a");
			if(!a.click) {
	     		 window.location = url;
	     		 return;
	   		 }
	   		a.setAttribute("href", url);
	   		a.style.display = "none";
	   		document.body.appendChild(a);
	   		a.click();
		}
		function checkbudgetN(){
			document.getElementById("dailyBudgetLin").disabled=true;
			document.getElementById("dailyBudget").value="-1";
		}
		function checkbudgetD(){
			document.getElementById("dailyBudgetLin").disabled=false;
			document.getElementById("dailyBudget").value="0";
		}
		function danjia(){		
			if(document.getElementById("preModifiedPrice").value!=""){							 	
				var   re   =   /^-?\d+\.?\d{0,2}$/; 
				if(isNaN(document.getElementById("preModifiedPrice").value)){
					document.getElementById("preModifiedPrice").value="";
					document.getElementById("name_error1").style.visibility='visible';
					document.getElementById("sub").disabled=true;
				}else if(document.getElementById("preModifiedPrice").value>999||document.getElementById("preModifiedPrice").value<0.2){
					document.getElementById("name_error1").style.visibility='visible';
					document.getElementById("sub").disabled=true;		
				}else if(re.test(document.getElementById("preModifiedPrice").value) == false){					
					document.getElementById("preModifiedPrice").value="";
					document.getElementById("name_error1").style.visibility='visible';
					document.getElementById("sub").disabled=true;		
				}else{
					document.getElementById("sub").disabled=false;
					document.getElementById("name_error1").style.visibility='hidden';
				}
			}else{
				document.getElementById("sub").disabled=false;
				document.getElementById("name_error1").style.visibility='hidden';
			}	
		}
			
		function fuzhi(){	
				var   re   =   /^-?\d+\.?\d{0,2}$/;
				if(isNaN(document.getElementById("dailyBudgetLin").value)){
					document.getElementById("dailyBudgetLin").value="";
					document.getElementById("name_error2").style.visibility='visible';
					document.getElementById("sub").disabled=true;
				}else if(document.getElementById("dailyBudgetLin").value>999999999||document.getElementById("dailyBudgetLin").value<0){
					document.getElementById("dailyBudgetLin").value="";
					document.getElementById("name_error2").style.visibility='visible';
					document.getElementById("sub").disabled=true;				
				}else if(re.test(document.getElementById("dailyBudgetLin").value) == false){
					document.getElementById("dailyBudgetLin").value="";
					document.getElementById("name_error2").style.visibility='visible';
					document.getElementById("sub").disabled=true;				
				}else{
					document.getElementById("name_error2").style.visibility='hidden';
					document.getElementById("sub").disabled=false;
					document.getElementById("dailyBudget").value = document.getElementById("dailyBudgetLin").value;
				}
				
		}
			
		function zong(){
			
			   var   re= /^-?\d+\.?\d{0,2}$/;
				if(isNaN(document.getElementById("generalBudget").value)){
					document.getElementById("generalBudget").value="";
					document.getElementById("name_error3").style.visibility='visible';
					document.getElementById("sub").disabled=true;
				}else if(document.getElementById("generalBudget").value>999999999||document.getElementById("generalBudget").value<0){
					document.getElementById("generalBudget").value="";
					document.getElementById("name_error3").style.visibility='visible';		
					document.getElementById("sub").disabled=true;
				}else if(re.test(document.getElementById("generalBudget").value) == false){
					document.getElementById("generalBudget").value="";
					document.getElementById("name_error3").style.visibility='visible';	
					document.getElementById("sub").disabled=true;
				}else{
					document.getElementById("name_error3").style.visibility='hidden';
					document.getElementById("sub").disabled=false;
				}
				
		}
</script>
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">开发者审核</div>
					<div class="back_link">
						<a href="manage!appList.do">应用管理</a>&nbsp;&nbsp;${app.name}
					</div>
					<table class="mar_bom10 margtop10">
						<tr>
							<td>开发者：</td>
							<td>${app.devEmail}&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>联系人：</td>
							<td>${app.devName }</td>
						</tr>
						<tr>
							<td>提交时间：</td>
							<td><fmt:formatDate value="${app.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>上线时间：</td>
							<td>
								<fmt:formatDate value="${app.releaseTime}" type="date" dateStyle="medium" />
								<c:if test="${app.releaseTime == null}">
										--
								</c:if>
							</td>
						</tr>
				</table>
				<form name="updateAppManage" action="manage!updateApp.do"	id="updateAppManage" method="post" enctype="multipart/form-data">
					<input type="hidden" name="appWallType" value="${appWallType}" />
					<input type="hidden" name="id" value="${app.id}" />
					<input type="hidden" name="devEmail" value="${app.devEmail}" />
					<input type="hidden" name="developerId" value="${dev.id}" />
					<input type="hidden" name="ceid" value="${app.categoryId}" />
					<input type="hidden" name="status" value="${app.status}" />
					<div class="bord_bom1px">应用详情</div>
					<div>开发者ID：${dev.id}</div>
					<div>应用ID：${app.id }</div>
					<div>应用KEY:${app.appkey }</div>
					<div class="appFile" id="haha">
						应用程序：
						<span class="uploadMsg">
							<c:if test="${fileUrl ne null && fileUrl ne ''}">
								<a href="${resource_url_default}${fileUrl}" target="_blank">${devAppVo.fileName}&nbsp;</a>
								<a href="${resource_url_default_other}${fileUrl}" target="_blank">下载地址2</a>
							</c:if>
							<c:if test="${fileUrl eq null || fileUrl eq ''}">
								${app.name }&nbsp;(未上传应用)
							</c:if>
						</span><br />
						<iframe frameborder="0" height="120" width="390" src="<%=ConfigUtil.getString("upload.file.path") %>qsl=1&fe=<%=ConfigUtil.getString("upload.file.type") %>&po=<%=ConfigUtil.getString("upload.file.rename") %>&pa=${dev.email}/${app.id}/&ic=1&fn=appUpdate"></iframe>
						<div>
							应用状态：
							<c:if test="${app.status == STATUS_NOTPASS}">
  								${app.appStatusName }&nbsp;&nbsp;驳回理由：${app.checkMsg}
  							</c:if>
							<c:if test="${app.status != STATUS_NOTPASS}">
  								${app.appStatusName }
  							</c:if>
						</div>
						<div class="bord_bom1px">活动信息</div>
						<div>
							<input name="os" type="radio" value="android" checked="checked" />ANDROID
							<input id="os_ios" name="os" type="radio" value="ios" />IOS
						</div>
						<table>
							<tr>
								<td width="100" valign="top">应用名称：</td>
								<td class="name">
									<input name="name" id="name" type="text" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="应用名不能为空"	value="${app.name }" maxlength="20" />* <font color="red">${errMsg_appName}</font>
								</td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>20个字符以内</td>
							</tr>
							<tr>
								<td valign="top">关键字：</td>
								<td class="keyword">
									<input name="keyword" id="keyword"	type="text" reg="^[a-zA-Z0-9\u4e00-\u9fa5\,]{0,50}$" tip="请输入不超过50位的字符 多个关键字用英文逗号分开" value="${app.keyword}" maxlength="50" />
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
											<option value="${entry.id}"	<c:if test="${app.categoryId eq entry.id}"> selected="selected" </c:if>>${entry.fname}-${entry.name}</option>
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
									<input id="pagetype_2" type="checkbox" name="types" value="2" />推荐墙(九宫格)
									<input id="pagetype_4" type="checkbox" name="types" value="4" />BANNER
									<input id="pagetype_5" type="checkbox" name="types" value="5" />插屏
								</td>
							</tr>
						<tr>
								<td valign="top">媒体评价</td>
								<td>
									<input type="text" id="app_scale" name="scale" value="${app.scale}" onkeyup="value=value.replace(/[^\d]/g,'')"/>
									<font color="red" style="display: none;">评价值为0~150的数字</font>
								</td>
							</tr>
							<tr>
								<td valign="top">是否单独投放</td>
								<td>
									<div>
							     <input id="placement" name="placement" type="radio" value="${app.id}" <c:if test="${app.placement eq app.id}"> checked="checked" </c:if> />是
							     <input id="placement" name="placement" type="radio" value="0" <c:if test="${app.placement eq 0}"> checked="checked" </c:if> />否
						</div>
								</td>
							</tr>
							<tr>
								<td valign="top">详细介绍：</td>
								<td class="longDesc"><textarea name="longDesc" id="longDesc" reg="^(?=.*?\S)[\s\S]{0,500}$"	tip="详细描述在1至100个字符" cols="50" rows="5" maxlength="100">${app.longDesc }</textarea>*
									<font color="red">${errMsg_appLongDesc}</font>
								</td>
							</tr>
							<tr>
								<td valign="top"></td>
								<td>
									<input name="" type="submit" class="appSubmit" value="保存" id="sub" />
								</td>
							</tr>
						</table>
				</form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>
