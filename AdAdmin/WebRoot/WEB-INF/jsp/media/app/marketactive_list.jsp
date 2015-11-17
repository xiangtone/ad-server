<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台平台管理</title>
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>

<script type="text/javascript">



/**
 * 查看修改信息
 *	
 **/
function findMarketActivityContent(id){
		var url = "manage!findMarketContent.do?id="+id;
		new $.dialog({
			title:'市场活动',
			page:url,
			width:1000,
			height:500,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
}

function marketActivityAdd(){
	var url = "manage!jumpAddMarketActivity.do";
	 window.location = url;
}


function refresh(){
	$("#marketactivitySetting").submit();
}
</script>
</head>
<body>
	
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>市场活动</legend>
						<form action="manage!marketactivity.do" id="marketactivitySetting" method="get">
							<table>
								<tr>
									<td>市场活动名称</td>
									<td><input type="text" name="name" value="${bean.name}"/></td>
									<td>市场活动预算</td>
									<td><input type="text" name="budget" value="${bean.budget}" /></td>
								</tr>
								<tr>								
									<td>活动时间</td>
									<td>
									<input type="text" name="acitvity_time" size="17"	id="acitvity_time" value="${bean.acitvity_time}" class="Wdate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'acitvity_time\')}'});" />
									</td>
								</tr>
								<tr>	
									<td>
									<input type="button" value="添加"  onclick="marketActivityAdd()" />
									</td>
									<td>
										<input name="find" type="submit" id="marketActiveSearch" 
												value="查询" />	
									</td>
								</tr>
								
							</table>
								</form>
					</fieldset>
				<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"
						class="table_bod1 font_stat" >
						<tr class="tr_td tb_head">
							<th><div class="date"></div>
							 市场活动名称</th>
							<th>市场活动预算</th>
							<th>市场活动内容</th>
							<th>市场活动时间</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.name}
									</td>
									<td>
										<fmt:formatNumber value="${vo.budget}" pattern="##.##" minFractionDigits="2" />
									</td>
									<td>
										${vo.content}
									</td>																	
								    <td>
										${vo.acitvity_time}
									</td>
									<td>
										<input type="button" value="编辑" onclick="findMarketActivityContent('${vo.id}');" />
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