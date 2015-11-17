<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理新闻管理</title>
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
			var dg;
			$(document).ready(function(){
				dg = frameElement.lhgDG;
			});
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
		function ceshi(){
			var mails = $("#ceshimail").attr("value");
			$("#mailtest").attr("value",mails);
			var content = tinyMCE.get('content').getContent();
			$("#tip").attr("value",content);
			if(validateCeshi()){
	    	      //options是一个ajaxForm的配置对象。
				var options = {
	    			//data:{content:tinyMCE.get('content').getContent()},
	    			 //dataType:'script',
	    			 url:"manage!sendMailsNotice.do",
	    			 contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    			 //type:'post',
	    			 dataType:'script',
	    			 resetForm:false,
	    			 success: function(responseText, statusText){ 
	    				$('#ceshiButtonTd').html('<input id="ceshiButton" type="button" value="测试发送" onclick="ceshi()" />');
	    				$('#submitButton').html('<input id="sendmail" type="button" value="发送" onClick="sendMail();" />');
	    				if(responseText = '1'){
	    					alert('测试邮件已发送');
	    				}else{
	    					alert('测试邮件发送失败');
	    				}
	    			 } //显示操作提示
	    			 };
	    			$('#saveMailsNoticetag').ajaxForm(options);
	    			$("#saveMailsNoticetag").submit();
			}
		}
		
		function validateCeshi(){
			var isSubmit = true;
			var formObj=document.getElementById("saveMailsNoticetag");
			for(var i =0;i<formObj.length;i++){
				var input = $(formObj[i]);
				if(input.attr("reg")!=null){
					if(input.attr("disabled") != "disabled" ){
						if(input.is(":input")||input.is(":textarea")){
							if (!validate(input,false)) {
								isSubmit = false;
							}
							else if (input.context.name == "email") {
								if ($(".emailText").length >= 1) {
									isSubmit = false;
								} 
							} 
						}
						else if(input.is(":select")){
							if (!validate(input,false)) {	
							isSubmit = false;
							}
						}
					}
				}
			}
		 if (isSubmit == true) {
			 $("#submit").attr("disabled",true);
			 $(":input[type=button]").attr("disabled","disabled");
		 }
			return isSubmit;
		}
		
		function sendMail(){
			var content = tinyMCE.get('content').getContent();
			$("#tip").attr("value",content);
			var options = {
	    			 url:"manage!saveMailsNotice.do",
	    			 contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    			 //type:'post',
	    			 dataType:'json',
	    			 resetForm:false,
	    			 success: function(data, statusText){
	    				 if(data){
	    						var dataObj=data;//转换为json对象 
	    						if(dataObj.status==1){
	    							alert("发送成功！！");
	    							dg.curWin.refresh();
	    							//dg.cancel();
	    						}else if(dataObj.status=-1){
	    							
	    						}else{
	    							alert("发送失败，请重试！！");
	    						}
	    					}else{
	    						alert("发送失败，请重试！！");
	    					}
	    			 } //显示操作提示
	    		};
	    		$('#saveMailsNoticetag').ajaxForm(options);
	    		$("#saveMailsNoticetag").submit();
			
		}
		
	</script>
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<form id="saveMailsNoticetag" action="" method="post" enctype="multipart/form-data">
					<input type="hidden" id="tip" name="content"></input> <input
						type="hidden" id="mailtest" name="mailtest"></input>
					<table>
						<tr>
							<td>收件人：</td>
							<td>
								<select name="type">
									<option value="1">开发者</option>
									<option value="2">广告主</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;标题：</td>
							<td class="title">
								<input name="title" type="text" id="title" style="width: 350px" maxlength="40" />
							</td>
						</tr>
						<tr>
							<td valign="top">&nbsp;&nbsp;内容：</td>
							<td>
								<textarea name="contentText" id="content" cols="60"	rows="20"></textarea>
							</td>
						</tr>
					</table>
					<div id="submitButton">
						<input id="sendmail" type="button" value="发送" />
					</div>

				</form>
				<form>
					<table>
						<tr>
							<td>测试邮箱：</td>
							<td>
								<input id="ceshimail" type="text" istyle="width: 300px"	maxlength="40" />
							</td>
							<td id="ceshiButtonTd">
								<input id="ceshiButton" type="button" value="测试发送" onclick="ceshi();" />
							</td>

						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>