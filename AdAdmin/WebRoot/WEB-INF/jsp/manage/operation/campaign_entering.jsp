<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
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
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save","保存",function(){ 
		$("#iosDate").ajaxSubmit(function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status==1){
					alert("录入成功！！");
					dg.curWin.refresh();
				}else if(dataObj.status=-1){
					alert(dataObj.error);
					dg.curWin.refresh();
				}else{
					alert("录入失败！！");
				}
			}else{
				alert("录入失败！！");
			}
	});
	});
</script>	
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
						<div>
						<table>
							<tr>
								<td>录入格式：</td>
								<td>
									<table width="100%" cellpadding="0" cellspacing="1"
										class="table_bod1 font_stat">
										<tr class="tr_td">
											<td>adkey</td>
											<td>mac</td>
											<td>openudid</td>
											<td>idfa</td>
										</tr>
									</table></td>
								<td><a href="demo/确认收入录入IOS.xls">范例下载</a>
								</td>
							</tr>
						</table>
					</div>
					<form action="manage!impAdEffectIosData.do" method="post" enctype="multipart/form-data" onsubmit="return checkIosNull()" id="iosDate">
						<div>
							提交excel：&nbsp;<input id="file_ios" name="file_ios" type="file" /><font
								color="red">*请使用97-03版本的excel文件！</font>
						</div>
						<div>
							<font color="red">${msg }</font>
						</div>						
					</form>
			</div>
		</div>
	</div>
</body>
</html>
