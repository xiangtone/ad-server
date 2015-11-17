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
<title>平台管理定时数据监控列表</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
	function timingData(Id){
		var url = "manage!dataMonitorDel.do?id="+Id;
		if(confirm("确认要发送吗？")){
				jump(url);
		}
	}
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div class="bord_bom1px">定时数据监控列表</div>
					<form action="manage!dataMonitorList.do" method="post" id="my_form">
						<input type="hidden" name="pageRecord" value="${pageInfo.pageSize}" />
						<!--新增end-->
						<table>
							<tr>
								<td>定时任务类型</td>							
								<td>
								<select name="task">
									<option value="">全部</option>
									<option <c:if test="${bean.task == 0}">selected="selected" </c:if> value="0" >按天统计--广告</option>
									<option <c:if test="${bean.task == 1}">selected="selected" </c:if> value="1" >按天统计--应用</option>
									<option <c:if test="${bean.task == 2}">selected="selected" </c:if> value="2" >实时统计--应用</option>
									<option <c:if test="${bean.task == 3}">selected="selected" </c:if> value="3" >实时统计--广告</option>
									<option <c:if test="${bean.task == 4}">selected="selected" </c:if> value="4" >开发者收入审核</option>
									<option <c:if test="${bean.task == 5}">selected="selected" </c:if> value="5" >渠道数据统计</option>
									
								</select>
								</td>
								<td>状态</td>
								<td>
								<select name="status">
									<option value="">全部</option>
									<option <c:if test="${bean.status == 0}">selected="selected" </c:if> value="0" >失败</option>
									<option <c:if test="${bean.status == 1}">selected="selected" </c:if> value="1" >成功</option>
								</select>
								</td>
								<td><input value="查询" type="submit" /></td>
							</tr>
						</table>
					</form>				
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1"	class="font_stat" id="tb">
						<!--修改-->
						<tr class="tr_td">
							<th>主键</th>
							<th>定时任务类型</th>
							<th>状态</th>
							<th>发送邮件</th>
							<th>创建时间</th>
							<!--<th>操作</th>-->
						</tr>
						<c:forEach items="${list}" var="vo">
						<tr>
							<td><font>${vo.id}</font></td>
							<td><font>${vo.task_name}</font></td>
							<td><font>${vo.status_name}</font></td>
							<td><font>${vo.dispatch_name}</font></td>
							<td>
								<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd" />
							</td>
							<!--<td>
								  <input type="button" value="发送" onclick="timingData('${vo.id}')" <c:if test="${vo.dispatch_status!=0}"> disabled</c:if> />
							</td>-->
						</tr>
						</c:forEach>
						<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="17" style="text-align: center;">暂无数据！</td>
						</tr>
						</c:if>
					</table>
					</div>
					<div>${pageInfo.pageInfoStr}</div>
					<span class="clear_span"></span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
