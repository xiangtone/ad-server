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
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	function submit_form(){
		if(vaildateForm("addPubAct")){
			if(validate_in()){
			$("#addPubAct").ajaxSubmit(function(data) {
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						$("#submit_button").attr("disabled",false);
						
					}else if(dataObj.status=-1){
						alert("操作失败，请重试！！");
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}

			});
			}
		}
	}
	
	function validate_in(obj){
		var apps=$("#appstore_status_1").is(':checked');
		if(apps){
		if(!$("#appstore_id").val()){
			alert("亲，请输入广告在AppStore上的ID！！");
			return false;
		}
		}
		return true;
	}
	
	$(document).ready(function(){
		var status='${vo.appstore_status}';//从后台取值判断是否选中
		if(status=='1'){
			appstore_tmp(true);
		}else{
			appstore_tmp(false);
		}
	});
	
	function appstore(obj){
		var apps=$(obj).is(':checked');
		appstore_tmp(apps);
	}
	
	function appstore_tmp(b){
		if(b){
			$("#appstore_status_1").attr("checked","checked");
			$("#appstore_id").attr("disabled",false);
		}else{
			$("#appstore_id").attr("disabled",true);
			$("#appstore_id").val("");
		}
	}
	
	
	
	
	
</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
				<div class="content_right admin_right">
				<form id="addPubAct" name="addPubAct" action="manage!addIosPackage.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="placement_id" id="adv_id" value="${placement_id}" />
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top">版本名称：</td>
							<td colspan="3" class="version_name">
								<input type="text" id="version_name" name="version_name" value="${vo.version_name}"  maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{1,20}$" tip="请输入2-20个字符 " maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td valign="top">版本号：</td>
							<td colspan="3" class="version_code">
								<input type="text" id="version_code" name="version_code" value="${vo.version_code}"  maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符 " maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td valign="top">Bundleid：</td>
							<td colspan="3" class="package_name">
								<input type="text" id="package_name" name="package_name" value="${vo.package_name}"  maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符 " maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td valign="top">发布时间：</td>
							<td colspan="3" class="create_time">
								<input type="text" id="create_time" name="create_time"  value="<fmt:formatDate value="${vo.create_time}" type="date" dateStyle="long" pattern="yyyy-MM-dd" />"  class="Wdate" readonly="readonly" maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$"	onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" tip="请输入版本发布时间！！" maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td valign="top">编号：</td>
							<td colspan="3" class="code">
								<input type="text" id="code" name="code"  maxlength="20" value="${vo.code}" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符 " maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td valign="top">文件名：</td>
							<td class="file_name">
								<input name="file_name" id="file_name" type="text" value="${vo.file_name}" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符 " maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td valign="top">文件大小：</td>
							<td class="file_size">
								<input name="file_size" id="file_size" type="text" value="${vo.file_size}"  reg="(^[0-9]{1,3})+([.]\d{1,2})?$" tip="请输入数字" maxlength="5"/>
								<font color="red">M</font>
							</td>
						</tr>
						<tr>
							<td align="right">是否开启免跳转：</td>
							<td align="left">
								<div id="appstore_suv">
									<input name="appstore_status" id="appstore_status_1" type="checkbox" onclick="appstore(this)" value="1"/>
									输入广告在AppStore上的ID：
									<input name="appstore_id" id="appstore_id" type="text" value="${vo.appstore_id}"  onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="15"/>
								</div>
							</td>
						</tr>
						<tr>
							<td valign="top">App下载地址：</td>
							<td class="res_url">
								<input name="res_url" type="text"  value="${vo.res_url}" size="80"  reg="[a-zA-z]+://[^s]*" tip="请输入正确的地址" maxlength="200"/>
							</td>
						</tr>
						<tr>
							<td valign="top"></td>
							<td>
								<input type="button" value="保存" onclick="submit_form();" id="submit_button" />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>