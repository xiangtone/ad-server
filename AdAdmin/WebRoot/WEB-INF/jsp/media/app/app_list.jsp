<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台开发者应用管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/operamasks-ui/css/default/om-default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/operamasks-ui/js/operamasks-ui.min.js?v=${version}"></script>
<style type="text/css">
	.om-messageBox-content .om-messageBox-image-myDefined{
       background-image: url("smile.png");
    }
</style>
<script type="text/javascript">

function refresh(){
	$("#my_form").submit();
}


function showSuccess(){
    $.omMessageBox.alert({
         type:'success',
         title:'成功',
         content:'操作成功',
         onClose:function(v){
        	 refresh();
         }
     });
 }


/**
 * 审核活动
 *	
 **/
function audit(actId) {
	var url = "manage!appAudit.do?app_id=" + actId;
	new $.dialog({
		title:'应用审核',
		page:url,
		width:735,
		height:570,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}



function info(actId) {
	var url = "manage!appInfo.do?app_id=" + actId;
	new $.dialog({
		title:'应用信息',
		page:url,
		width:735,
		height:570,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

function edit(app_id) {
	var url="appEidt.do?appId="+app_id;
	new $.dialog({
		title:'应用修改',
		page:url,
		width:535,
		height:570,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}


function appOnLine(appId){
	var url = "appOline.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'status='+status+'&appId='+appId,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					showSuccess();
				}
			}
			
		}
		});
	}//取消操作
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


function applyDevAppManager(status,appId){
	var url = "manage!auditDevApp.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'status='+status+'&appId='+appId,
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					showSuccess();
				}
			}
			
		}
		});
	}//取消操作
}
		
		
function app_audit(app_scale,passStr,appid){
		applyDevAppManager(passStr,appid);
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px"></div>
					<fieldset class="search_fieldset">
						<legend>应用管理 </legend>
						<div>
							<form action="manage!appList.do" method="post" id="my_form">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>
														应用ID/名称
													</td>
													<td>
														<input type="text" name="app" value="${bean.app}" />
													</td>
													<td>上线时间开始:</td>
													<td>
														<input type="text" name="startTime" size="15" id="startTime" value="${bean.startTime}" class="Wdate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'});" />
													</td>
													<td>上线时间结束:</td>
													<td>
														<input type="text" name="endTime" size="15" id="endTime" value="${bean.endTime }" class="Wdate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'});" />
													</td>
												</tr>
												<tr>
													<td>
														开发者ID/邮箱
													</td>
													<td>
														<input type="text" name="dev" value="${bean.dev}" />
													</td>
													<td>平台</td>
													<td>
														<select name="os" id="os">
															<option value="" selected="selected">全部</option>
															<option value="android" <c:if test="${bean.os =='android'}">selected="selected"</c:if>>android</option>
															<option value="ios" <c:if test="${bean.os =='ios'}">selected="selected"</c:if>>ios</option>
														</select>
													</td>
													<td>状态:</td>
													<td>
														<select name="status">
															<option value="" >全部</option>
															<option value="1" <c:if test="${bean.status == 1}">selected="selected"</c:if>>草稿</option>
															<option value="2" <c:if test="${bean.status ==2}">selected="selected"</c:if>>待审核</option>
															<option value="-1" <c:if test="${bean.status == -1}">selected="selected"</c:if>>未通过</option>
															<option value="3" <c:if test="${bean.status == 3}">selected="selected"</c:if>>上线</option>
															<option value="-2" <c:if test="${bean.status ==-2}">selected="selected"</c:if>>下线</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>媒体负责人:${bean.app_manager_id}</td>
													<td>
														<select id="app_manager_id" name="app_manager_id" style="width: 153px;">
															<option value="">请选择</option>					
															<c:forEach items="${appMediaList}" var="entity">
																<option value="${entity.id}" <c:if test="${entity.id  eq bean.app_manager_id}"> selected="selected" </c:if>>${entity.name}</option>
															</c:forEach>
														</select>
													</td>
													<td>来源:</td>
													<td>
														<select name="app_res">
															<option value="" >全部</option>
															<option value="0" <c:if test="${bean.app_res == 0}">selected="selected"</c:if>>平台</option>
															<option value="1" <c:if test="${bean.app_res==1}">selected="selected"</c:if>>渠道</option>
														</select>
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('my_form');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</fieldset>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="font_stat">
						<tr class="tr_td">
							<th width="8%">应用ID</th>
							<th>应用名称</th>
							<th>评级分数</th>
							<th width="4%">平台</th>
							<th width="10%">开发者账号</th>
							<th width="6%">应用来源</th>
							<th width="6%">提交时间</th>
							<th width="8%">上线时间</th>
							<th width="4%">状态</th>
							<th>广告形式</th>
							<th>媒体负责人</th>
							<th width="8%">操作</th>
						</tr>
						<c:if test="${!empty devAppList}">
							<c:forEach items="${devAppList}" var="app" varStatus="status">
								<tr>
									<td align="center">
										${app.id}
									</td>
									<td align="center">
										<a href="javascript:void(0);" onclick="info('${app.id}')"><font color="blue">${app.name}</font></a>
									</td>
									 <td align="center">
										${app.scale}
									</td>
								    <td align="center">
										${app.os}
									</td>
									
									<td align="center">
										<a href="javascript:void(0);" class="yylba" onclick="dev_info('${app.dev_id}')">
												<font color="blue">${app.devEmail}</font>
										</a>
									</td>
									<td align="center">
										<c:if test="${app.app_res == 0}">平台</c:if>
										<c:if test="${app.app_res == 1}">渠道</c:if>
									</td>
									<td align="center">
										<fmt:formatDate value="${app.updateTime}" pattern="yyyy-MM-dd" />
									</td>
									<td align="center">
										<fmt:formatDate value="${app.releaseTime}" pattern="yyyy-MM-dd" />
										<c:if test="${app.releaseTime == null}">
  											--
  										</c:if>
  									</td>
									<td align="center">
										${app.statusName}
									</td>

									<td align="center">
										${app.pageTypeVos}
									</td>
									<td align="center">
										${app.app_manager_name}
									</td>
									<td nowrap="nowrap">
										<input type="button" value="修改" onclick="edit('${app.id}')" style="cursor: pointer;"   <escore:security code="APP_MODIFY" type="button" />/>
										<input type="button" value="审核" onclick="audit('${app.id}')" style="cursor: pointer;" <c:if test="${app.status!= 2}">disabled="disabled"</c:if> <escore:security code="APP_ADUIT" type="button" /> />
										<input type="button" value="上线" onclick="appOnLine('${app.id}')" style="cursor: pointer;" <c:if test="${app.status!=-2}">disabled="disabled"</c:if> <escore:security code="APP_MODIFY" type="button" /> />
										<input type="button" value="下线" onclick="applyDevAppManager('-2','${app.id}')" style="cursor: pointer;" <c:if test="${app.status!=3}">disabled="disabled"</c:if> <escore:security code="APP_ON_OFF_LINE" type="button" /> />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty devAppList}">
							<tr>
								<td align="center" colspan="12" style="text-align: center;">暂无记录！</td>
							</tr>
						</c:if>
					</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>
</html>