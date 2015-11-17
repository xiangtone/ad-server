<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台媒体模块</title>

<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
function refresh(){
	$("#package").submit();
}

/**
 * 修改方案
 *	
 **/
function editPlacement(id){
	var url = "manage!mediaPlacement.do?app_id=" + id;
	new $.dialog({
		title:'活动投放---修改投放',
		page:url,
		width:950,
		height:620,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}



</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
						<tr class="tr_td tb_head">
							<th width="5%">序号</th>	
							<th>广告ID</th>		
							<th>投放名称</th>
							<th>形式</th>
							<th>包号</th>
							<th>日投放量</th>
							<th>单位</th>
							<th>单价</th>
							<th>结算方式</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;">
										${status.index+1}
									</td>
									<td>${vo.id}</td>
									<td>${vo.placement_name}</td>
									<td>
										 <c:if test="${vo.type_id==0}">
										 	积分墙
										</c:if>
										<c:if test="${vo.type_id==1}">推荐墙</c:if>
										<c:if test="${vo.type_id==4}">BANNER</c:if>
										<c:if test="${vo.type_id==5}">插屏</c:if>
									</td>
									<td>
										<c:forEach items="${vo.packages}" var="entry">
											<c:if test="${vo.package_id eq entry.id}">${entry.code}</c:if>
										</c:forEach>
									</td>
									<td>
										${vo.budget_day}
									</td>
									<td>
										<c:if test="${vo.budget_type=='A'}">A</c:if>
										<c:if test="${vo.budget_type=='D'}">D</c:if>
										<c:if test="${vo.budget_type=='M'}">M</c:if>
										<c:if test="${vo.budget_type=='C'}">C</c:if>
									</td>
									<td>
										${vo.blance_price}
									</td>
									<td>
										<c:if test="${vo.blance_mode eq 'CPA'}">CPA</c:if>
										<c:if test="${vo.blance_mode eq 'CPD'}">CPD</c:if>
										<c:if test="${vo.blance_mode eq 'CPC'}">CPC</c:if>
										<c:if test="${vo.blance_mode eq 'CPM'}">CPM</c:if>
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