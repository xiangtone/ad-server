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
<title>运营管理后台ios配置</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	/**
	 * 添加广告主
	 *	
	 **/
	function interfaceIos(placement_id){	
		var url = "manage!addCollocationIos.do?placement_id="+placement_id;
		new $.dialog({
			title:'添加或修改ios配置',
			page:url,
			width:455,
			height:200,
			drag:true,
			resize:true,
			cover:true,
			rang:true,
			autoPos:{left:'center',top:'center'},
			maxBtn:false}).ShowDialog();
	}

	function refresh(){
		$("#dactivityFindAll").submit();
	}
	/**
	 * tb效果
	 *	
	 **/
	$(document).ready(function (){
		$("#tb tr").hover(function (){
			 $(this).addClass('bgclor01');
		},function (){
			  $(this).removeClass('bgclor01');  
		});
		var trs =  $("#tb").find("tr");
		for(i = 0;i<trs.length;i++){
			var obj = trs[i];
			if(i %2 == 0){
				obj.style.backgroundColor ='#EEEEEE';
			}
				 
		}  
	});	
	
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>iOS活动接口配置列表</legend>
						<div id="search_bar">
							<!--新增-->
							<form action="manage!interfaceIosList.do" method="post"	id="dactivityFindAll">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td >活动ID</td>
													<td><input name="campaign_id" id="campaign_id" type="text" value="${bean.campaign_id }"	onkeyup="value=value.replace(/[^\d]/g,'')" />
													</td>
													<td >活动名称</td>
													<td><input name="campaign_name" id="campaign_name" type="text"
														value="${bean.campaign_name}" />
													</td>
												</tr>
											</table>
										</td>
										<td width="15%" valign="middle" align="right">
											<div style="width: 100px;height: 100%;display: inline;line-height: 20px;">
												<button onclick="search('dactivityFindAll');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">查询</button>
												<button onclick="resetAll('dactivityFindAll');" class="button button-pill button-primary" style="line-height: 21px;height: 21px;font-size: 13px;padding-left: 10px;padding-right: 10px;">重置</button>
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
						<th>广告主ID</th>
						<th>广告主名称</th>
						<th>活动ID</th>
						<th>活动名称</th>
						<th>活动类型</th>
						<th>计费方式</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list}" var="k">
						<tr style="text-align: center;">
							<input name="placement_id" type="hidden" id="placement_id" maxlength="20"  value="${k.placement_id}" />
							<td>${k.adv_id}</td>
							<td>${k.adv_email}</td>
							<td>${k.id }</td>
							<td>
								${k.campaign_name}
							</td>
							<td>
								<c:choose>
									<c:when test="${k.category_id ==1}">
										电商
									</c:when>
									<c:when test="${k.category_id ==2}">
									        游戏
									</c:when>
									<c:when test="${k.category_id ==3}">
									        品牌
									</c:when>
									<c:when test="${k.category_id == 4}">
									        系统工具
									</c:when>
									<c:when test="${k.category_id == 5}">
									       生活工具
									</c:when>
								<c:otherwise>
									  注册
								</c:otherwise>
								</c:choose>
							</td>
							<td>${k.charge_type}</td>
							<td>
								<input type="submit" value="配置" onclick="interfaceIos(${k.placement_id})"/>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
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