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
<title>运营管理后台方案调整修改页面</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tabTool.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tabTool.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			form_submit();
		});
	});
	
	function form_submit(){
		var awardLength = document.getElementById("award").value.length;
		if(awardLength > 500){
			alert("市场活动奖励的内容最大长度为500个字符");
			return;
		}
		
		var summaryLength = document.getElementById("summary").value.length;
		if(summaryLength > 1000){
			alert("市场活动总结的内容最大长度为1000个字符");
			return;
		}
		var dg=frameElement.lhgDG;
		if(vaildateForm("updateMarketActivity")){
			$("#updateMarketActivity").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
						//dg.cancel();
					}else if(dataObj.status=-1){
						
					}else{
						alert("登录失败，请重试！！");
					}
				}else{
					alert("登录失败，请重试！！");
				}
			});
		};
	}
	
	
	
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<form id="updateMarketActivity" name="updateMarketActivity" action="manage!updateMarketActivity.do" method="post" >
				<div>
					<input type="hidden" name="id" id="id" value="${vo.id}" />
				</div>
				<div class="content_right admin_right">
				
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="top">市场活动名称：</td>
							<td colspan="3" class="name">
								<input type="text" name="name" id="name" value="${vo.name}" reg="^(?=.*?\S)[\s\S]{0,35}$" tip="市场活动名称不能为空" maxlength="35"/>
								<font color="red">*${errMsg_name}</font>	
							</td>
						</tr>
						
						<tr>
							<td valign="top">市场活动预算：</td>
							<td colspan="3" class="budget">
								<input type="text" name="budget" id="budget" value="${vo.budget}" reg="^\d+$" tip="请输入数字0-9，且最大长度为20" maxlength="20"/>
								<font color="red">*${errMsg_budget}</font>	
							</td>
						</tr>
						<tr>
							<td valign="top">市场活动内容：</td>
							<td colspan="3" class="marketContent">
								<textarea name="marketContent" id="marketContent" cols ="100" rows = "5" reg="^(?=.*?\S)[\s\S]{0,255}$" tip="市场活动内容不能为空">${vo.content}</textarea>
								<font color="red">*${errMsg_marketContent}</font>	
							</td>
						</tr>
						<tr>
							<td valign="top">市场活动时间：</td>
							<td colspan="3" class="acitvity_time">
								<input type="text" name="acitvity_time" size="15"	id="acitvity_time" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="请选择市场活动时间" value="${vo.acitvity_time}" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'acitvity_time\')}'});" />	
								<font color="red">*${errMsg_acitvity_time}</font>	
							</td>
						</tr>
						
						<tr>
							<td valign="top">市场活动奖励：</td>
							<td colspan="3" class="limit_required">
								<textarea name="award" id="award" cols ="100" rows = "5"  >${vo.award}</textarea>
							</td>
						</tr>
						
						<tr>
							<td valign="top">市场活动总结：</td>
							<td colspan="3" class="limit_required">
								<textarea name="summary" id="summary" cols ="100" rows = "5" >${vo.summary}</textarea>
							</td>
						</tr>
						
					</table>
				</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>