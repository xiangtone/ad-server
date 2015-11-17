<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/operamasks-ui/js/operamasks-ui.min.js?v=${version}"></script>
<script type="text/javascript">

function adOnline(adID){
	var url = "${pageContext.request.contextPath}/app/auditAd.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'ad_id='+adID,
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
	}//取消操作
}




/**
 * 查看修改信息
 *	
 **/
function findUpdateAdv(adId){
		var url = "manage!findAdContent.do?adId="+adId;
		new $.dialog({
			title:'广告管理',
			page:url,
			width:575,
			height:300,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

function refresh(){
	$("#my_form").submit();	
}

/**
 * tb效果
 *	
 **/
$(document).ready(function (){
});		
</script>
<!-- <style>
table,td{ border:1px solid #000; border-collapse:collapse}
</style> -->
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>广告管理 </legend>
						<div id="search_bar">
							<form action="${pageContext.request.contextPath}/app/adList.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告主ID/名称</td>
													<td><input type="text" name="adv" value="${bean.adv}" /></td>
													<td>媒体ID/名称</td>
													<td>
														<input type="text" name="media" value="${bean.media}" />
													</td>
													<td>广告形式</td>
													<td>
														<select name="type_id" id="adForm">
															<option value="">全部</option>
															<option value="0" <c:if test="${bean.type_id ==0}">selected="selected"</c:if> >积分墙</option>
															<option value="1" <c:if test="${bean.type_id ==1}"> selected="selected"</c:if> >推荐墙</option>
															<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
															<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>广告ID</td>
													<td>
														<input type="text" name="id" value="${bean.id}" />
													</td>
													<td>结算方式</td>
													<td>
														<select name="blanceMode">
															<option value="">全部</option>
															<option <c:if test="${bean.blanceMode =='CPM'}"> selected="selected"</c:if> value="CPM" >CPM</option>
															<option <c:if test="${bean.blanceMode =='CPC'}"> selected="selected"</c:if> value="CPC" >CPC</option>
															<option <c:if test="${bean.blanceMode =='CPD'}"> selected="selected"</c:if> value="CPD" >CPD</option>
															<option <c:if test="${bean.blanceMode =='CPA'}"> selected="selected"</c:if> value="CPA" >CPA</option>
															<option <c:if test="${bean.blanceMode =='CPT'}"> selected="selected"</c:if> value="CPT" >CPT</option>
														</select>
													</td>
													<td>广告状态</td>
													<td>
														<select name="status">
															<option value="">全部</option>
															<option value="1" <c:if test="${bean.status ==1}">selected="selected" </c:if>>上线</option>
															<option value="0" <c:if test="${bean.status ==0}">selected="selected" </c:if> >定时上线</option>
															<option value="-1" <c:if test="${bean.status ==-1}">selected="selected" </c:if> >下线</option>
															<option value="-10" <c:if test="${bean.status ==-10}">selected="selected" </c:if> >暂停</option>
															<option value="-20" <c:if test="${bean.status ==-20}">selected="selected" </c:if>>定时下线</option>
															<option value="-30" <c:if test="${bean.status==-30}">selected="selected" </c:if>>超量下线</option>
															<option value="-5" <c:if test="${bean.status==-5}">selected="selected" </c:if>>活动结束</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>活动ID/名称</td>
													<td>
														<input type="text" name="campaign" value="${bean.campaign}" />
													</td>
													<td>平台类型</td>
													<td>
														<select name="os" id="os">
															<option value="">全部</option>
															<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
															<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
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
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
						<tr class="tr_td">
							<th width="3%">序号</th>
							<th width="3%">&nbsp;&nbsp;</th>
							<th width="5%">AD_ID</th>
							<th width="8%">广告主ID</th>
							<th width="10%">广告主名称</th>
							<th width="8%">活动ID</th>
							<th width="10%">活动名称</th>
							<th width="10%">应用名称</th>
							<th width="5%">OS</th>
							<th width="10%">投放名称</th>
							<th width="8%">形式</th>
							<th width="10%">结算方式</th>
							<th width="10%">广告状态</th>
							<th width="10%">定时</th>
							<th width="10%">上线时间</th>
							<th width="10%">下线时间</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;">
										${status.index+1}
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.status ==1}">
												<img src="${pageContext.request.contextPath}/images/sys/green.gif" alt="" />
											</c:when>
											<c:when test="${vo.status ==-5}">
												<img src="${pageContext.request.contextPath}/images/sys/red.gif" alt="" />
											</c:when>
											<c:otherwise>
												<img src="${pageContext.request.contextPath}/images/sys/yellow.gif" alt="" />
											</c:otherwise>
										</c:choose>
										
									</td>
									<td>
										${vo.adId}
									</td>
									<td>
										${vo.advertisersId}
									</td>
									<td>
										<escore:subStr len="25" value="${vo.adv_name}" />
									</td>
									<td>
										${vo.campaign_id}
									</td>
									<td>
										<escore:subStr len="20" value="${vo.campaign_name}" />
									</td>
									<td>
										<escore:subStr len="15" value="${vo.media_name}" />
									</td>
									<td>
										${vo.os}
									</td>
									<td>
										<escore:subStr len="20" value="${vo.placement_name}" />
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.adForm =='0'}">
												积分墙
											</c:when>
											<c:when test="${vo.adForm =='1'}">
												推荐墙
											</c:when>
											<c:when test="${vo.adForm =='2'}">
												九宫格
											</c:when>
											<c:when test="${vo.adForm =='4'}">
												Banner
											</c:when>
											<c:when test="${vo.adForm =='5'}">
												插屏
											</c:when>
										</c:choose>
									</td>
									<td>
										${vo.blanceMode}
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.status ==1}">
												上线
											</c:when>
											<c:when test="${vo.status ==0}">
												定时上线
											</c:when>
											<c:when test="${vo.status ==-1}">
												下线
											</c:when>
											<c:when test="${vo.status ==-10}">
												暂停
											</c:when>
											<c:when test="${vo.status ==-20}">
												定时下线
											</c:when>
											<c:when test="${vo.status ==-30}">
												超量下线
											</c:when>
											<c:when test="${vo.status ==-5}">
												活动结束
											</c:when>
											<c:when test="${vo.status ==-35}">
												待审核
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<span title="上线：<fmt:formatDate value="${vo.schedule_start_time}" pattern="yyyy-MM-dd HH:mm:ss"/>">
											<c:choose>
												<c:when test="${vo.schedule_start}">
													<img src="${pageContext.request.contextPath}/images/sys/sq.gif" alt="<fmt:formatDate value="${vo.schedule_start_time}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
												</c:when>
												<c:otherwise>
													<img src="${pageContext.request.contextPath}/images/sys/sq_h.gif" alt="" />
												</c:otherwise>
											</c:choose>
										</span>
										<span title="下线：<fmt:formatDate value="${vo.schedule_end_time}" pattern="yyyy-MM-dd HH:mm:ss"/>">
											<c:choose>
												<c:when test="${vo.schedule_end}">
													<img src="${pageContext.request.contextPath}/images/sys/sq.gif" alt="" />
												</c:when>
												<c:otherwise>
													<img src="${pageContext.request.contextPath}/images/sys/sq_h.gif" alt="" />
												</c:otherwise>
											</c:choose>
										</span>
									</td>
									<td>
										<fmt:formatDate value="${vo.onLineTime}" pattern="yyyy-MM-dd"/>
									</td>
									<td>
										<fmt:formatDate value="${vo.offLineTime}" pattern="yyyy-MM-dd"/>
									</td>
									<td>
										<input type="button" value="修改" onclick="findUpdateAdv('${vo.adId}');" <c:if test="${vo.status==-5}">disabled</c:if> <escore:security code="BTN_AD_MODIFY" type="button" /> />
										<input type="button" value="审核" onclick="adOnline('${vo.adId}','${vo.status}');" <c:if test="${(vo.status!=-35)}">disabled</c:if> <escore:security code="BTN_AD_ONLINE" type="button" /> />
									</td>
									<td>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty list}">
							<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
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
