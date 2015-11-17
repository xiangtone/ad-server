<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台</title>

<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	//保存
	var dg = frameElement.lhgDG;
	dg.addBtn("save", "保存", function() {
		num();
	});
	

 function num(){
	$("#dspInfoConfig").ajaxSubmit(function(data) {
		if (data) {
			var dataObj = eval("(" + data + ")");//转换为json对象 
			if (dataObj.status == 1) {
				alert("操作成功！！");
				dg.curWin.refresh();
			}else if(dataObj.status = -1) {
				alert("操作失败" + dataObj.error);
				dg.curWin.refresh();
			}else{
				alert("操作失败，请重试！！");
				dg.curWin.refresh();
			}
		} else {
			alert("操作失败，请重试！！");
			dg.curWin.refresh();
		}
	});
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
			
					<form name="dspInfoConfig" id="dspInfoConfig" method="post"	action="manage!updateDspInfoConfig.do">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="table_bod1 font_stat">
							<tr class="tr_td tb_head">
								<td>DSP_ID</td>
								<td>参数类型</td>
								<td>参数名</td>
							</tr>
							<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
							<tr>
							<input type="hidden" name="id" value="${vo.id}" />
								<td>${vo.dsp_id}</td>
								<td><input type="text" name="param_type"  value="${vo.param_type}" /></td>
								<td><input type="text" name="param_name"  value="${vo.param_name}" /></td>
							</tr>
							</c:forEach>
							</c:if>	
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
