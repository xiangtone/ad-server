<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后台广告主账号管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	
		/**
		 * 帐号激活
		 *	
		 **/
		function activationAdver(adverId){
			var flag = confirm("确认要激活吗？");
			if(flag){
				var url = "manage!activationAdver.do";
				$.ajax({
					url:url,
					type:'POST',
					data:'adverId='+adverId,
					dataType:'text',
					beforeSend:function(){
						//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
				},
				success:function(data){
					if(data){
						var dataObj=eval("("+data+")");//转换为json对象 
						if(dataObj.status=='1'){
							alert("操作成功！！");
							refresh();
						}
					}
					
				}
				});
			}
		}
		/**
		 * 查看修改信息
		 *	
		 **/
		function findUpdateAdv(advId){
				var url = "manage!editAdv.do?advId="+advId;
				new $.dialog({
					title:'修改广告主',
					page:url,
					width:775,
					height:420,
					drag:true,
					resize:true,
					cover:true,
					maxBtn:false}).ShowDialog();
		}
		/**
		 * 查看修改信息
		 *	
		 **/
		function advInfo(advId){
			var url = "manage!findAdv.do?advId="+advId;
				new $.dialog({
					title:'广告主信息',
					page:url,
					width:575,
					height:520,
					drag:true,
					resize:true,
					cover:true,
					maxBtn:false}).ShowDialog();
		}
		
		/**
		 * 发布活动
		 *	
		 **/
		function activityAdv(email,id){		
			var url = "manage!addCampaign.do?advEmail="+email+"&adv_id="+id;
			new $.dialog({
				title:'发布活动',
				page:url,
				width:625,
				height:570,
				drag:true,
				resize:true,
				cover:true,
				
				maxBtn:false}).ShowDialog();
		}

		/**
		 * 添加广告主
		 *	
		 **/
		function addAdv(){	
			var url = "manage!addAdv.do";
			new $.dialog({
				title:'添加广告主',
				page:url,
				width:455,
				height:410,
				drag:true,
				resize:true,
				cover:true,
				rang:true,
				autoPos:{left:'center',top:'center'},
				maxBtn:false}).ShowDialog();
		}
		/**
		 * tb效果
		 *	
		 **/
		function refresh(){
			$("#my_form").submit();
		}
		$(document).ready(function (){
			
		});		
	</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>广告主管理 </legend>
						<div id="search_bar">
							<form action="manage!advList.do" method="post"	id="my_form">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告主ID</td>
													<td><input type="text" name="adv_id" value="${bean.adv_id}" /></td>
													<td>广告主名称</td>
													<td><input type="text" name="adv_name" value="${bean.adv_name}" /></td>
													<td>
														联系人
													</td>
													<td>
														<input type="text" name="selectValue" value="${bean.real_name}" />
													</td>
												</tr>
												<tr>
													<td>
														状态
													</td>
													<td>
														<select name="status">
															<option value="">全部</option>
															<option value="1"  <c:if test="${bean.status ==1}">selected="selected"</c:if>>激活</option>
															<option value="0" <c:if test="${bean.status == 0}">selected="selected"</c:if>>未激活</option>
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
					<div>
						<input style="cursor: pointer;"  type="button" name="add" value="添加广告主" onclick="addAdv()" <escore:security code="BTN_ADV_ADD" type="button" /> />
					</div>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb">
							<tr>
								<th>广告主ID</th>
								<th>广告主</th>
								<th>联系人</th>
								<th>区域</th>
								<th>注册时间</th>
								<th>帐号状态</th>
								<th>账户余额</th>
								<th>信用额度</th>
								<th>信用余额</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty allUserAdvertiser}">
							<c:forEach items="${allUserAdvertiser}" var="vo" varStatus="k">
							<tr align="center">
								<td>${obj.id}</td>
								<td>
									<a href="javascript:void(0);" onclick="advInfo('${vo.id}')" style="cursor: pointer;">${vo.companyName}</a>
								</td>
								<td>${vo.realName}</td>
								<td>
									<c:if test="${vo.area_type eq '4' }">
										华南
									</c:if>
									<c:if test="${vo.area_type eq '1' }">
										华东
									 </c:if>
									 <c:if test="${vo.area_type eq '2' }">
									 	华北
									 </c:if>
									 <c:if test="${vo.area_type eq '0' }">
									   	平台
									 </c:if>
								</td>
								<td>
									<fmt:formatDate value="${vo.createTime}" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<c:if test="${vo.status == 0 }">未激活</c:if><c:if test="${vo.status == 1 }">激活</c:if>
								</td>
								<td>
									<escore:formatMoney value="${vo.actualBalance }" maxFractionDigits="2" />
								</td>
								<td>
									<escore:formatMoney value="${vo.creditQuota }" maxFractionDigits="2" />
								</td>
								<td>
									<escore:formatMoney value="${vo.creditBalance }" maxFractionDigits="2" />
								</td>
								<td>
									<input type="button" value="激活" onclick="activationAdver('${vo.id }')" <c:if test="${vo.status == 1 }">disabled</c:if> <escore:security code="BTN_ADV_ACTIVATE" type="button" /> />
									<input type="button" value="活动发布" style="cursor: pointer;" onclick="activityAdv('${vo.email}',${vo.id })" <c:if test="${vo.status == 0 }">disabled</c:if> <escore:security code="BTN_ADV_CAMPAIGN_ADD" type="button" />/>
									<input type="button" value="修改信息" onclick="findUpdateAdv(${vo.id })" <c:if test="${vo.status == 0 }">disabled</c:if> <escore:security code="BTN_ADV_MODIFIY" type="button" /> />
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty allUserAdvertiser}">
								<tr>
									<td align="center" colspan="15" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
					</div>
					<div style="display: block;height: 15px;"></div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>
</html>