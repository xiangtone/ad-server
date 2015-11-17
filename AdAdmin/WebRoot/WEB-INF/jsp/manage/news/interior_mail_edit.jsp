<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理新闻修改</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
		<link
			href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />

		<script type="text/javascript">
			$(document).ready(
				function(){
					$("#type").val('${newsNotice.type}');
				});
		</script>
		<!-- tinyMCE -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jscripts/tiny_mce/tiny_mce.js"></script>
		<script type="text/javascript">
			tinyMCE.init({
				mode : "textareas",
				theme : "advanced",
				plugins : "autolink,lists,pagebreak,layer,advhr,advlink,preview,emotions,iespell,inlinepopups,insertdatetime,searchreplace,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist,autosave",

				// Theme options
				theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
				theme_advanced_buttons2 : "search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,cleanup,code,|,insertdate,inserttime,|,forecolor,backcolor,|,preview,fullscreen",
				theme_advanced_toolbar_location : "top",
				theme_advanced_toolbar_align : "left",
				theme_advanced_statusbar_location : "bottom",
				theme_advanced_resizing : true,

				// Example content CSS (should be your site CSS)
				content_css : "css/content.css",

				// Drop lists for link/image/media/template dialogs
				template_external_list_url : "lists/template_list.js",
				external_link_list_url : "lists/link_list.js",
				external_image_list_url : "lists/image_list.js",
				media_external_list_url : "lists/media_list.js",

				// Replace values for the template plugin
				template_replace_values : {
					username : "Some User",
					staffid : "991234"
				},
				language : "zh-cn"
			});
		</script>
		<!-- /tinyMCE -->
	</head>
<script type="text/javascript">

function publishMail(type){
	$("#mail_status").val(type);
		$("#interior_mail").ajaxSubmit(function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("操作成功！！");
					window.location.href='manage!interiorMailList.do';
				}else if(dataObj.status=-1){
					alert("操作失败请重试 ");
				}else{
					alert("操作失败，请重试！！");
				}

			}
		});
	
}

</script>
	<body>
		<div class="main">
			<div class="content clearfix">
				<div class="content_right admin_right">
					<div class="bord_bom1px">
						<a href="manage!interiorMailList.do">新闻管理</a>&gt;修改站内信
					</div>
					<form action="manage!updateInteriorMail.do" method="post" name="interior_mail" id="interior_mail">
					<input type="hidden" name="id" value="${editInterior.id }">
					<input type="hidden" name="status" id="mail_status" />
					<table>
						<tr>
							<td>
								标题：
							</td>
							<td class="title">
								<input name="title" value="${editInterior.title }" type="text" id="title" style="width: 350px"  maxlength="40"/>
							</td>
						</tr>
						<tr>
							<td valign="top">
								内容：
							</td>
							<td>
								<textarea name="content" cols="60" rows="20">${editInterior.content }</textarea>
							</td>
						</tr>
						<tr>
							<td>
								开发者id集合：
							</td>
							<td class="title">
								<input name="dev_id_str" type="text" id="dev_id_str" style="width: 350px" maxlength="40" value="${editInterior.dev_id_str }"/>
							</td>
						</tr>
					</table>
					<div>
						<input id="caogao" type="button" name="caogao" value="保存草稿" onclick="publishMail(1);"/>
						<input id="fabu" type="button" name="fabu" value="发布" onclick="publishMail(2);"/>

					</div>
					</form>
</div>
<span class="clear_span"></span>
</div>
</div>
</body>
</html>
