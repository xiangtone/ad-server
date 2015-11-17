<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib prefix="ax" uri="/WEB-INF/tld/AlanXUpload.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control"  content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>运营管理后台</title>
	<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		if(vaildateForm("dspInfo")){
			$("#dspInfo").ajaxSubmit(function(data) {
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.curWin.refresh();
					}else if(dataObj.status=-1){
						alert("操作失败"+dataObj.error);
						dg.curWin.refresh();
					}else{
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
				}else{
					alert("操作失败，请重试！！");
					dg.curWin.refresh();
				}

			});
		}
	});
</script>
</head>

<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="content_right admin_right" >
				<form name="dspInfo" id="dspInfo" method="post" action="manage!saveDspInfo.do">
					
					<table width="500px;">
						<tr>
							<td align="right">dsp名称：</td>
							<td class="dsp_name" align="left"><input
								name="dsp_name" type="text" id="dsp_name"
								reg="^[\u4e00-\u9fa5\w\W]{2,50}$" tip="请输入4-50个字符  "
								maxlength="50" /></td>
						</tr>
						<tr>
							<td align="right">dsp请求路径：</td>
							<td class="dsp_url" align="left"><input
								name="dsp_url" type="text" id="dsp_url"
								maxlength="150" /></td>
						</tr>
						<tr>
							<td align="right">备注：</td>
							<td class="dsp_desc" align="left"><input
								name="dsp_desc" type="text" id="dsp_desc"
								maxlength="150" /></td>
						</tr>
						<tr>
							<td align="right">加密因子：</td>
							<td class="dsp_key" align="left"><input
								name="dsp_key" type="text" id="dsp_key"
								maxlength="150" /></td>
						</tr>
						<tr>
							<td align="right">额外的固定参数值：</td>
							<td class="source_str" align="left"><input
								name="source_str" type="text" id="source_str"
								maxlength="150" /></td>
						</tr>
						<tr>
							<td align="right">启动的服务名称：</td>
							<td class="service" align="left"><input
								name="service" type="text" id="service"
								maxlength="150" /></td>
						</tr>
						<tr>
							<td align="right">名称：</td>
							<td class="bean" align="left"><input
								name="bean" type="text" id="bean"
								maxlength="150" /></td>
						</tr>
						
						
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>