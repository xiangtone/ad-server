<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function adOnline(adID,status){
	var url = "manage!adOnline.do?adId="+adID;
	if(status == 1){
		alert("已经是上线状态");
		return;
	}
	if(confirm("确认要执行该操作吗？")){
		window.location.href=url;//不是驳回直接跳转
		jump(url);
	}//取消操作
}


function adOffline(adID,status){
	var url = "manage!adOffline.do?adId="+adID;
	if(status == 0){
		alert("已经是下线状态");
		return;
	}
	if(confirm("确认要执行该操作吗？")){
		window.location.href=url;//不是驳回直接跳转
		jump(url);
	}//取消操作
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
			height:270,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

function refresh(){
	$("#adAjustmentSetting").submit();	
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
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>广告管理 </legend>
						<form action="manage!adByPlacementList.do" id="adAjustmentSetting" method="post">
							<input type="hidden" name="placement_id" value="${bean.placement_id}" />
							<table>
								<tr>
									<td>媒体ID/名称</td>
									<td>
										<input type="text" name="media" value="${bean.media}" />
									</td>
									<td>媒体类型</td>
									<td>
										<select name="ad_type" id="blanceMode">
											<option value="">全部</option>
											<option <c:if test="${bean.ad_type ==0}"> selected="selected"</c:if> value="0">应用</option>
											<option <c:if test="${bean.ad_type ==1}"> selected="selected"</c:if> value="1">渠道</option>
										</select>
									</td>
									<td>广告形式</td>
									<td>
										<select name="type_id" id="adForm">
											<option value="">全部</option>
											<option value="0" <c:if test="${bean.type_id ==0}">selected="selected"</c:if> >积分墙</option>
											<option value="1" <c:if test="${bean.type_id ==1}"> selected="selected"</c:if> >推荐墙</option>
											<option value="2" <c:if test="${bean.type_id ==2}"> selected="selected"</c:if> >九宫格</option>
											<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
											<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
										</select>
									</td>
									<td>结算方式</td>
									<td>
										<select name="blanceMode">
											<option value="">全部</option>
											<option <c:if test="${bean.blanceMode =='CPM'}"> selected="selected"</c:if> value="CPM" >CPM</option>
											<option <c:if test="${bean.blanceMode =='CPC'}"> selected="selected"</c:if> value="CPC" >CPC</option>
											<option <c:if test="${bean.blanceMode =='CPD'}"> selected="selected"</c:if> value="CPD" >CPD</option>
											<option <c:if test="${bean.blanceMode =='CPA'}"> selected="selected"</c:if> value="CPA" >CPA</option>
										</select>
									</td>
									<td>广告状态</td>
									<td>
										<select name="status">
											<option value="">全部</option>
											<option value="1" <c:if test="${bean.status ==1}">selected="selected" </c:if>>上线</option>
											<option value="0" <c:if test="${bean.status ==0}">selected="selected" </c:if> >初始化</option>
											<option value="-1" <c:if test="${bean.status ==-1}">selected="selected" </c:if> >人工下线</option>
											<option value="-10" <c:if test="${bean.status ==-10}">selected="selected" </c:if> >暂停</option>
											<option value="-20" <c:if test="${bean.status ==-20}">selected="selected" </c:if>>投放结束</option>
											<option value="-30" <c:if test="${bean.status==-30}">selected="selected" </c:if>>超量下线</option>
										</select>
									</td>
									<td rowspan="1">
										<input name="find" type="submit" id="adPriceSearch" value="查询" />
									</td>
								</tr>
							</table>
						</form>
					</fieldset>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb" class="font_stat" >
						<tr class="tr_td">
							<th>广告ID</th>
							<th>媒体类型</th>
							<th>媒体名称</th>
							<th>广告形式</th>
							<th>结算方式</th>
							<th>广告状态</th>
							<th>上线时间</th>
							<th>下线时间</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.adId}
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.ad_type ==0}">
												应用
											</c:when>
											<c:when test="${vo.ad_type ==1}">
												渠道
											</c:when>
										</c:choose>
									</td>
									<td>
										${vo.media_name}
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
												初始化
											</c:when>
											<c:when test="${vo.status ==-1}">
												人工下线
											</c:when>
											<c:when test="${vo.status ==-10}">
												暂停
											</c:when>
											<c:when test="${vo.status ==-20}">
												投放结束
											</c:when>
											<c:when test="${vo.status ==-30}">
												超量下线
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<fmt:formatDate value="${vo.onLineTime}" pattern="yyy-MM-dd "/>
									</td>
									<td>
										<fmt:formatDate value="${vo.offLineTime}" pattern="yyy-MM-dd "/>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty list}">
							<td colspan="17" align="center" style="text-align: center;">暂无记录！</td>
						</c:if>
					</table>
					${pageInfo.pageInfoStr}
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
