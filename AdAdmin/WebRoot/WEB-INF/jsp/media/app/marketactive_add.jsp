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
	src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    function check(){
    	var awardLength = document.getElementById("award").value.length;
    	var awardAttribute = document.getElementById("award").getAttribute("tip");
		if(awardLength > 500){
			alert("市场活动奖励的内容最大长度为500个字符");
			return;
		}
		
		var summaryLength = document.getElementById("summary").value.length;
		if(summaryLength > 1000){
			alert("市场活动总结的内容最大长度为1000个字符");
			return;
		}
    }
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<div class="bord_bom1px">添加市场活动</div>
				<form name="addMarketActivity" action="manage!addMarketActivity.do"
					id="addMarketActivity" method="post" >
					<table>
						<tr>
							<td valign="top">市场活动名称：</td>
							<td colspan="3" class="name">
								<input type="text" name="name" id="name"  reg="^(?=.*?\S)[\s\S]{0,35}$" tip="市场活动名称不能为空" maxlength="35"/>
								<font color="red">*${errMsg_name}</font>	
							</td>
						</tr>
						
						<tr>
							<td valign="top">市场活动预算：</td>
							<td colspan="3" class="budget">
								<input type="text" name="budget" id="budget"  reg="^\d+$" tip="请输入数字0-9，且最大长度为20" maxlength="20"/>
								<font color="red">*${errMsg_budget}</font>	
							</td>
						</tr>
						<tr>
							<td valign="top">市场活动内容：</td>
							<td colspan="3" class="marketContent">
								<textarea name="marketContent" id="marketContent" cols ="70" rows = "5" reg="^(?=.*?\S)[\s\S]{0,255}$" tip="市场活动内容不能为空,且最大长度为255个字符"></textarea>
								<font color="red">*${errMsg_marketContent}</font>
							</td>
						</tr>						
						<tr>
							<td valign="top">市场活动时间：</td>
							<td colspan="3" class="acitvity_time">
								<input type="text" name="acitvity_time" size="15"	id="acitvity_time" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="请选择市场活动时间" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'acitvity_time\')}'});" />	
								<font color="red">*${errMsg_acitvity_time}</font>	
							</td>
						</tr>
						
						<tr>
							<td valign="top">市场活动奖励：</td>
							<td colspan="3" class="award">
								<textarea name="award" id="award" cols ="70" rows = "5" ></textarea>
								<font color="red">${errMsg_award}</font>	
							</td>
						</tr>
						
						<tr>
							<td valign="top">市场活动总结：</td>
							<td colspan="3" class="summary">
								<textarea name="summary" id="summary" cols ="70" rows = "5" ></textarea>
								<font color="red">${errMsg_summary}</font>
							</td>
						</tr>
						<tr>
							<td valign="top"></td>
							<td colspan="3"><input name="" type="submit"  onclick="return check()"
								value="保存" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>