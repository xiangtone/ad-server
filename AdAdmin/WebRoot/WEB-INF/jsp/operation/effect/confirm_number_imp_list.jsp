<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="order" uri="/WEB-INF/tld/orderTag.tld"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告包确认数录入</title>

<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">
.img01{
	display: none;
}
</style>
<script type="text/javascript">
function summitn_um(id){
	var url = "summitIosForecastIncomeCost.do?id="+id;
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

function delNum(id){
	var url = "manage!delConfirmationNumber.do?id="+id;
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

function deleteEffect(id){
	var url = "operation/deleteEffect.do?id="+id;
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



$(document).ready(function (){
	initOrder();
});

function refresh(){
	$("#advConfirmationNumber").submit();
}


function initOrder(){
	 $(".tb_head th").css("cursor","pointer");
	 var code=$.cookie("table_order");
	 if(code){
		 $("#"+code).removeClass('img01');
	 }

	 $(".tb_head th").click(function(){
		 if($(this).attr("order")&&($(this).attr("order")=='false')){
			 return;
		 }
		 var obj;
		 if($(this).find(".img01").length==2){
			 obj=$(this).find("img").eq(0);
		 }else{
			 obj=$(this).find(".img01");
			 obj.removeClass('img01');
			 obj.siblings().addClass('img01');
		 }
		 changeOrder(obj.attr("id"),obj.attr("url"));
	 });    	 
}
function changeOrder(key,url){
	 $.cookie("table_order",key);
	 window.location.href=url;
}
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>IOS确认数录入</legend>
						<div id="search_bar">
						<form action="manage!adEffectIosImp.do" method="post" id="advConfirmationNumber">
							<input type="hidden" name="pageRecord"  value="${pageInfo.pageSize}"/>
							<table>
								<tr>
									<td>
										<table width="100%">
											<tr>
												<td>发生日期开始</td>
												<td>
													<input name="start_time" type="text"	value="${bean.start_time}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
												</td>
												<td>发生日期结束</td>
												<td>
													<input name="end_time" type="text"	value="${bean.end_time}" class="Wdate" readonly="readonly" onclick="WdatePicker()" />
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
												<td>活动名称</td>
												<td>
													<input name="campaign_name" id="campaign_name" type="text" value="${bean.campaign_name}" maxlength="50" />
												</td>
												<td>状态</td>
												<td>
													<select name="status">
														<option value="">全部</option>
														<option value="0" <c:if test="${bean.status ==0}">selected="selected" </c:if> >待提交</option>
														<option value="1" <c:if test="${bean.status ==1}">selected="selected" </c:if>>已提交</option>
													</select>
												</td>
											</tr>
										</table>
									</td>
									<td width="5%" valign="middle" align="center">
										<div style="width: 100px;height: 100%;">
											<input type="submit" value="查询" id="activityFindAll" style="cursor: pointer;" />
											<input type="button" onclick="resetAll('advConfirmationNumber');" value="重置" onfocus="this.blur();" class="cx"/>
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
							<th width="3%"  order="false">序号</th>
							<th width="5%" order="false">广告ID</th>
							<th width="10%" order="false">效果发生时间</th>
							<th width="10%" order="false">活动名称</th>
							<th width="5%" order="false">广告形式</th>
							<th width="5%" order="false">点击数</th>
							<th width="5%" order="false">确认激活数</th>
							<th width="5%" order="false">接入单价</th>
							<th width="5%" order="false">投放单价</th>
							<th width="5%" order="false">收益</th>
							<th width="5%" order="false">成本</th>
							<th width="5%" order="false">状态</th>
							<th width="8%" order="false">操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;">
										${status.index+1}
									</td>
									<td>${vo.ad_id}</td>
									<td>${vo.static_date}</td>
									<td>${vo.ad_name}</td>
									<td>
										<c:choose>
											<c:when test="${vo.type_id ==0}">
												积分墙
											</c:when>
											<c:when test="${vo.type_id==1}">
												推荐墙
											</c:when>
											<c:when test="${vo.type_id ==2}">
												九宫格
											</c:when>
											<c:when test="${vo.type_id ==4}">
												Banner
											</c:when>
											<c:when test="${vo.type_id ==5}">
												插屏
											</c:when>
										</c:choose>
									</td>
									<td>${vo.click_num}</td>
									<td>${vo.confirm_num}</td>
									<td>${vo.in_price}</td>
									<td>${vo.out_price}</td>
									<td>${vo.income}</td>
									<td>${vo.cost}</td>
									<td>
										<c:choose>
											<c:when test="${vo.status == 0}">
												待提交
											</c:when>
											<c:when test="${vo.status == 1}">
								     			 已提交
											</c:when>
										</c:choose>
									</td>
									<td>
								 	<input type="button" value="提交" onclick="summitn_um('${vo.id}')"  <c:if test="${vo.status==1}"> disabled</c:if> />
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