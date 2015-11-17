<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
//list鼠标路过效果
$(document).ready(function (){
	// android效果录入明细
	$("#activityFindAll_Search").click(
			function() {
					$("#pageindex").val(0);
					$("#my_form").attr("action","manage!operationChannelCpdList.do").submit();
			});
});

function refresh(){
	$("#my_form").submit();
}

//保存更新渠道效果的值
function updateCpdData(id){
	var money =$("#"+id+"_money").val();
	var num=$("#"+id+"_num").val();
	if(money){
		var url = "manage!updateCpdData.do?id="+id+"&money="+money+"&num="+num;
		if(confirm("确认要执行该操作吗？")){
		$.ajax({
			type:"get",   
			url:url,   
			dataType : "json", 
			success:function(data){
			if(data){
				if(data.status==1){
					alert("保存成功！！");
					refresh();
				}else if(data.status=-1){
					alert(data.error);
				}else{
					alert("操作失败，请重试！！");
				}
			}else{
				alert("操作失败，请重试！！");
			}
			}   
		 });
	}
	//取消操作
	}else{
		alert("广告主确认数不能为空！");
	}
}


function submitCpdData(id){
	var url = "manage!confirmationCpd.do?id="+id;
	if(confirm("确认要执行该操作吗？")){
	$.ajax({
		type:"get",   
		url:url,   
		dataType : "json", 
		success:function(data){
		if(data){
			if(data.status==1){
				alert("保存成功！！");
				refresh();
			}else if(data.status=-1){
				alert(data.error);
			}else{
				alert("操作失败，请重试！！");
			}
		}else{
			alert("操作失败，请重试！！");
			}
		}   
	  });
	}
}



</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>CPD录入</legend>
						<div id="search_bar">
							<form  method="post" id="my_form" action="manage!operationChannelCpdList.do">
								<div>
									<input type="hidden" name="pageRecord" value="${pageInfo.pageSize}"/>
									<input type="hidden" name="pageIndex" id="pageindex" value="${pageInfo.currentPage}"/>
								</div>
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td >发生日期</td>
													<td>
														<input name="create_time_sart" id="create_time_sart" type="text"	value="${bean.create_time_sart}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />至
														<input name="create_time_end" id="create_time_end" type="text"	value="${bean.create_time_end}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
													</td>
													<td >活动ID</td>
													<td>
														<input name="campaign_id" type="text" value="${bean.campaign_id}" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td >活动名称</td>
													<td>
														<input name="campaign_name" type="text" value="${bean.campaign_name}" maxlength="50" />
													</td>
												</tr>
												<tr>
													<td >媒体ID</td>
													<td>
														<input name="media_id" type="text" value="${bean.media_id}" maxlength="50" onkeyup="value=value.replace(/[^\d]/g,'')"/>
													</td>
													<td >媒体名称</td>
													<td>
														<input name="media_name" type="text" value="${bean.media_name}" maxlength="50" />
													</td>
													<td>状态</td>
													<td>
														<select name="status" style="width: 157px;">
															<option value="">全部</option>
															<option <c:if test="${bean.status==0}"> selected="selected"</c:if> value="0">未提交</option>
															<option <c:if test="${bean.status==1}"> selected="selected"</c:if> value="1">已提交</option>
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
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
						<tr class="tr_td tb_head">
							<th width="3%">序号</th>
							<th width="10%">发生时间</th>
							<th width="5%">活动ID</th>
							<th>活动名称</th>
							<th width="5%">媒体ID</th>
							<th>媒体名称</th>
							<th width="8%">预计收入</th>
							<th width="8%">渠道成本</th>
							<th width="8%">渠道效果</th>
							<th width="8%">状态</th>
							<th width="5%">操作</th>
						</tr>
						<tr style="text-align: center;">
								<td>汇总</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td><font color="red">
									<escore:formatMoney	value="${sum.sum_forecast_money}" maxFractionDigits="2" />
								</font></td>
								<td>
									<font color="red">
										<escore:formatMoney value="${sum.sum_income_money}" maxFractionDigits="2" />
									</font>
								</td>
								<td>
									<font color="red">
										${sum.sum_channel_num}
									</font>
								</td>
								
								<td>-</td>
								<td>-</td>
							</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;">
										${status.index+1}
									</td>
									<td>${vo.static_date}</td>
									<td>${vo.campaign_id}</td>
									<td>${vo.campaign_name}</td>
									<td>${vo.media_id}</td>								
									<td>${vo.media_name}</td>								
									<td>
										<fmt:formatNumber  type="number" pattern="###,###.####" value="${vo.conf_money}"  minFractionDigits="2" maxFractionDigits="4" />
									</td>								
									<td>
										<c:choose>
											<c:when test="${vo.status ==1}">
												<input type="text" style="width: 60px;"  maxlength="7" value="${vo.confirm_money}" disabled="disabled"/>
											</c:when>											
											<c:otherwise>
													<input type="text" id="${vo.id}_money" style="width: 60px;"  maxlength="7" value="${vo.confirm_money}"/>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.status ==1}">
												<input type="text" style="width: 60px;" maxlength="7" value="${vo.channel_effect}" disabled="disabled"/>
											</c:when>											
											<c:otherwise>
												<input type="text" id="${vo.id}_num" style="width: 60px;"  maxlength="7" value="${vo.channel_effect}"/>
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${vo.status ==0}">
												未提交
											</c:when>				
											<c:otherwise>
								 				已提交
											</c:otherwise>
										</c:choose>
									</td>
									<td>									
										<input style="cursor: pointer;" type="button" value="保存" onclick="updateCpdData('${vo.id}');"  <c:if test="${vo.status==1}"> disabled</c:if>/>
										<input style="cursor: pointer;" type="button" value="提交" onclick="submitCpdData('${vo.id}');"  <c:if test="${!(vo.status==0&&!(vo.confirm_money eq null))}">disabled</c:if>/>
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