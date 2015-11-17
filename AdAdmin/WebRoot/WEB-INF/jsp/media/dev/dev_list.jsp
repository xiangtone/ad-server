<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者账号管理</title>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
function alearAchievementTime(){
	$('#logonStartTime').val('');
	$('#logonEndTime').val('');
}

function dev_info(id) {
	var url = "manage!devInfo.do?dev_id="+ id;
	new $.dialog({
		title:'开发者帐号信息',
		page:url,
		width:985,
		height:530,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

	function resetPassManage(devId){
		var url = "manage!resetPassManage.do?developerId="+devId;
		if(confirm("确认要重置该帐号的密码吗？")){
				jump(url);
		}
	}
	function accountFreeze(devId){
		var url = "manage!accountFreeze.do?developerId="+devId;
		if(confirm("确认冻结吗？")){
				jump(url);
		}
	}
	function accountReverseFreeze(devId){
		var url = "manage!accountReverseFreeze.do?developerId="+devId;
		if(confirm("确认解冻吗？")){
				jump(url);
		}
	}
	function accountClosed(devId){
		var url = "manage!accountClosed.do?developerId="+devId;
		if(confirm("确认封号吗？")){
				jump(url);
		}
	}
	function accountOpen(devId){
		var url = "manage!accountOpen.do?developerId="+devId;
		if(confirm("解封后该帐号下的所有应用将恢复为待提交状态，确认解封吗？")){
			jump(url);
		}
	}
	function accountActivation(devId){
		var url = "accountActivation.do";
		if(confirm("确认要为该帐号手动激活吗？")){
			$.ajax({
				url:url,
				type:'POST',
				data:'dev_id='+devId,
				dataType:'text',
				beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status=='1'){
						alert("操作成功！！");
						window.location.href =window.location.href;
					}
				}
			}
			});
		}
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
	
	$(document).ready(function (){
		$("#devAccountManageDown").click(
			function() {
				$("#searchManager").attr("action", "manage!devAccountListDownloadManage.do").submit();
			});
		$("#adverAccountManageSearch").click(
				function() {
					$("#searchManager").attr("action", "manage!devList.do").submit();
				});
	});
	
	function addApp(id){
		var url = "manage!addApp.do?dev_id="+id;
		new $.dialog({
			title:'添加物料',
			page:url,
			width:675,
			height:550,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	
	
</script>
</head>

<body style="overflow: scroll;">
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<fieldset class="search_fieldset">
				<legend>开发者账号管理(共${devCount}位开发者)</legend>
					<form action="manage!devList.do" id="searchManager" method="post">
						<table width="100%" cellpadding="1" cellspacing="0">
							<tr>
								<td>
									开发者ID/邮箱
								</td>
								<td>
									<input type="text" name="idOrEmail" value="${bean.idOrEmail}" />
								</td>
								<td>来源:</td>
								<td>
									<select name="resource">
										<option value="" >全部</option>
										<option value="ESCORE" <c:if test="${bean.resource=='ESCORE'}">selected="selected"</c:if>>平台</option>
										<option value="KUAIYOU" <c:if test="${bean.resource=='KUAIYOU'}">selected="selected"</c:if>>渠道</option>
									</select>
								</td>
								<td>注册时间:</td>
								<td>
									<input name="logonStartTime" id="logonStartTime" type="text" onclick="WdatePicker();" value="${bean.logonStartTime}" class="Wdate" readonly="readonly" />至
									<input name="logonEndTime" id="logonEndTime" type="text" onclick="WdatePicker();" value="${bean.logonEndTime}" class="Wdate" readonly="readonly" />
									<input type="button" value="清空" onclick="alearAchievementTime();" style="cursor: pointer;" />
								</td>
								<td>
								</td>
								
							</tr>
							<tr>
								<td>联系人</td>
								<td>
									<input type="text" name="dev_name" value="${bean.dev_name}" />
								</td>
								<td>
									状态
								</td>
								<td>
									<select name="status" id="searchAccountStatus">
										<option value="" selected="selected">全部</option>
										<option value="1" <c:if test="${bean.status == 1}">selected="selected"</c:if>>正常</option>
										<option value="0" <c:if test="${bean.status == 0}">selected="selected"</c:if>>初始化</option>
										<option value="2" <c:if test="${bean.status==2}">selected="selected"</c:if>>冻结</option>
										<option value="3" <c:if test="${bean.status==3}">selected="selected"</c:if>>封号</option>
										
									</select>
								</td>
								<td>
									<input type="button" id="adverAccountManageSearch" value="搜索"	style="cursor: pointer;" />
									<input name="download" type="button" id="devAccountManageDown" value="导出Excel" style="cursor: pointer;" />
								</td>
							</tr>
						</table>
						</form>
						</fieldset>
						<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
							<tr class="tr_td">
								<th>UID</th>
								<th>账号</th>
								<th>联系人</th>
								<th>注册时间</th>
								<th>应用个数</th>
								<th>来源</th>
								<th>状态</th>
								<th>可提款收入</th>
								<th>累计提现</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty devList}">
								<c:forEach items="${devList}" var="dev" varStatus="status">
									<tr>
										<td align="center">${dev.id}</td>
										<td align="center">
											<a href="javascript:void(0);" class="yylba" onclick="dev_info('${dev.id}')">
												<font color="blue">${dev.email}</font>
											</a>
										</td>
										<td align="center">${dev.realName }</td>
										<td align="center">
											<fmt:formatDate	value="${dev.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td align="center">${dev.appCount}</td>
										<td align="center">
											<c:if test="${dev.resource_code=='ESCORE'}">平台</c:if>
											<c:if test="${dev.resource_code=='KUAIYOU'}">渠道</c:if>
										</td>
										<td align="center">
											<c:if test="${dev.status == 0}">初始化</c:if>
											<c:if test="${dev.status == 1}">正常</c:if>
											<c:if test="${dev.status == 2}">冻结</c:if>
											<c:if test="${dev.status == 3}">封号</c:if>
											
										</td>
										<td align="center">
											<escore:formatMoney	value="${dev.finance_income}" maxFractionDigits="2" />
										</td>
										<td align="center">
											<escore:formatMoney	value="${dev.totalMoney }" maxFractionDigits="2" /></td>
										<td align="center">
											<input type="button" value="解封" onclick="accountOpen('${dev.id}')" style="cursor: pointer;" <c:if test="${dev.status != 3}">disabled="disabled"</c:if> <escore:security code="DEV_FREEZE_UP" type="button" /> />
											<input type="button" value="封号" onclick="accountClosed('${dev.id}')" style="cursor: pointer;" <c:if test="${dev.status !=1 }"> disabled="disabled" </c:if> <escore:security code="DEV_FENGHAO" type="button" /> />
											<input type="button" value="激活" onclick="accountActivation('${dev.id}')" style="cursor: pointer;"  <c:if test="${dev.status !=0}"> disabled="disabled" </c:if> <escore:security code="DEV_FREEZE_ACTIVATE" type="button" /> />
											<input type="button" value="冻结" onclick="accountFreeze('${dev.id}')" style="cursor: pointer;" <c:if test="${dev.status != 1}"> disabled="disabled" </c:if> <escore:security code="DEV_FREEZE" type="button" /> />
											<input type="button" value="解冻" onclick="accountReverseFreeze('${dev.id}')" style="cursor: pointer;" <c:if test="${dev.status != 2}"> disabled="disabled" </c:if> <escore:security code="DEV_REVERSE_FREEZE" type="button" /> />
											<input type="button" value="添加应用" onclick="addApp('${dev.id}')" style="cursor: pointer;" <c:if test="${dev.status != 1}"> disabled="disabled" </c:if>  />
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty devList}">
								<tr>
									<td align="center" colspan="10" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
						</div>
						${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
</body>
</html>