<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台统计IOS渠道统计</title>
<link href="${pageContext.request.contextPath}/css/page.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript">
	function search_SetDateClean() {
		var activeDate_start = document.getElementById("activeDate_start");
		var activeDate_end = document.getElementById("activeDate_end");
		activeDate_start.value = "";
		activeDate_end.value = "";
	}

	function reportData() {
		$("#ios_form").attr("action", "manage!reportIosChannelExcel.do")
				.submit();
	}

	function selData() {
		$("#ios_form").attr("action", "manage!reportIosChannel.do").submit();
	}
	// add by jief
	jQuery(document).ready(function() {
		 var film_searche=document.getElementById("ad_name");
		    if(film_searche){   
		     jQuery("#ad_name").autocomplete(
		        "manage!matchCampaingCofnig.do",   
		        {
		        max: 10,   
		        scroll: false,   
		        width: 245,
		        dataType:'json',
		        //formatItem: function(row, i,max) {
		       // 	var obj =eval("(" + row + ")");
		       //     return obj['adName'];
		       // },
		       // formatResult: function(row,value) {
		        //	return row+"@"+value;
		        //},
		        parse: function(data) {  
		        var rows = [];
		        for(var i=0; i<data.length; i++){  
		        rows[rows.length] = {
			        data:data[i]['adName'],      
			        value:data[i]['adId'],       
			        result:data[i]['adName']+"@"+data[i]['adId']       
		        };
		       }
		       return rows;      
		     },      
		      formatItem: function(row, i, n,value, term) {
		         return row;            
		      }
		     }).result(function(event,item) {
		    	// TODO ..
		    	return item;
		     });   
		    $("#clear").click(function() {
				$("#ad_name").unautocomplete();
			});
		 }
	});
	
</script>
</head>

<body>
	<div class="main">
		<div class="content clearfix">
			<div>
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>IOS渠道统计</legend>
							<form action="manage!reportIosChannel.do" method="post"	id="ios_form">
							<table>
								<tr>
									<td>
										<table width="100%">
											<tr>
												<td>广告 名称：</td>
												<td>
												  <input type="text" id ="ad_name" name="q" size="30" value="${vo.q}"/>
													<!--select name="ad_id">
														<option value="">全部</option>
														<c:forEach items="${adList}" var="ad" varStatus="status">
														<option value="${ad.id}" <c:if test="${vo.ad_id eq ad.ad_key}">selected="selected"</c:if>>${ad.ad_name}</option>
														</c:forEach>
													</select-->
												</td>
												<td>渠道名称：</td>
												<td>
													<select name="channel">
														<option value="">全部</option>
														<c:forEach items="${channelList}" var="chan" varStatus="status">
														<option value="${chan.channel}"	<c:if test="${vo.channel eq chan.channel}">selected="selected"</c:if>>${chan.channel_name}</option>
														</c:forEach>
													</select>
												</td>
												<td>墙类型</td>
												<td>
													<select name="pageType">
														<option value="">全部</option>
														<option value="0" <c:if test="${vo.pageType == 0}">selected="selected"</c:if>>积分墙</option>
														<option value="1" <c:if test="${vo.pageType == 1}">selected="selected"</c:if>>推荐墙</option>
														<option value="4" <c:if test="${vo.pageType == 4}">selected="selected"</c:if>>BANNER</option>
														<option value="5" <c:if test="${vo.pageType == 5}">selected="selected"</c:if>>插屏</option>
														<option value="6" <c:if test="${vo.pageType == 6}">selected="selected"</c:if>>热荐</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>开始时间</td>
												<td>
													<input type="text" name="statDate_start" size="15" id="statDate_start" onfocus="WdatePicker();"	value="${fn:substring(vo.statDate_start, 0, 10)}" class="Wdate" readonly="readonly" />
												</td>
												<td>结束时间</td>
												<td>
													<input type="text" name="statDate_end" size="15" id="statDate_end" onfocus="WdatePicker();"	value="${fn:substring(vo.statDate_end, 0, 10)}"	class="Wdate" readonly="readonly" />
												</td>
												<td>状态</td>
												<td>
													<select name="searchStatus">
														<option value="-1">全部</option>
														<option value="0" <c:if test="${vo.searchStatus == 0}">selected="selected"</c:if>>待确认</option>
														<option value="1" <c:if test="${vo.searchStatus == 1}">selected="selected"</c:if>>已确认</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>mac地址：</td>
												<td>
													<input type="text" value="${vo.mac}" name="mac" />
												</td>
												<td>IDFA：</td>
												<td>
													<input type="text" value="${vo.idfa}" name="idfa" />
												</td>
												<td>OPENUDID：</td>
												<td>
													<input type="text" value="${vo.openUdid}" name="openUdid" />
												</td>
											</tr>
											<tr>
												<td>渠道发送状态：</td>
												<td>
													<select name="send">
														<option value="">全部</option>
														<option value="0" <c:if test="${vo.send == '0'}">selected="selected"</c:if>>作弊嫌疑扣量</option>
														<option value="1" <c:if test="${vo.send == '1'}">selected="selected"</c:if>>正常发送</option>
														<option value="2" <c:if test="${vo.send == '2'}">selected="selected"</c:if>>协议扣量</option>
													</select>

												</td>
												<td>时间段类型：</td>
												<td>
												    <select name="timeType">
														<option value="0">点击时间</option>
														<option value="1" <c:if test="${vo.timeType == 1}">selected="selected"</c:if>>激活时间</option>
													</select>
												</td>
												<td>
												  数据类型:
												</td>
												<td>
												    <select name="dataType">
														<option value="0">点击数据</option>
														<option value="1" <c:if test="${vo.dataType == 1}">selected="selected"</c:if>>确认数据</option>
													</select>
												</td>
											</tr>
										</table>
									</td>
									<td width="5%" valign="middle" align="center">
										<div style="width: 100px;height: 100%;">
												<input name="button" type="button" onclick="selData();" value="查询" />
												<input name="button" type="button" onclick="reportData();" value="导出" />
										</div>
									</td>
								</tr>
							</table>
						</form>
					</fieldset>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"
							class="font_stat">
							<tr class="tr_td">
								<th>接收时间</th>
								<th>广告Id</th>
								<th>广告名称</th>
								<th>渠道名称</th>
								<th>mac地址</th>
								<th>状态</th>
								<th>墙类型</th>
								<th>应用ID</th>
								<th>OPENUDID</th>
								<th>IDFA</th>
								<th>渠道返回状态</th>
							</tr>
							<c:if test="${!empty rdadsr}">
								<c:forEach items="${rdadsr}" var="report" varStatus="status">
									<tr align="center">
										<td><fmt:formatDate value="${report.create_time }"
												type="date" pattern="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td>${report.ad_id }</td>
										<td>${report.ad_name }</td>
										<td>${report.channel }</td>
										<td>${report.mac }</td>
										<td>
											<c:if test="${report.status == 0 }">待确认</c:if>
											<c:if test="${report.status == 1 }">已确认</c:if>
											<c:if test="${report.status == 2 }">已发送</c:if>
										</td>
										
										<td>
										<c:if test="${report.page_type == 0 }">积分墙</c:if> 
										<c:if test="${report.page_type == 1 }">推荐墙</c:if> 
										<c:if test="${report.page_type == 4 }">BANNER</c:if>
										<c:if test="${report.page_type == 5 }">插屏</c:if>
										<c:if test="${report.page_type == 6 }">热荐</c:if>
										</td><!-- 墙类型 -->
										
										<td>${report.application_key }</td><!-- 应用id -->
										<td>${report.openUdid }</td>
										<td>${report.idfa }</td>
										<td>
										<c:if test="${report.send == 0 }">刷量嫌疑扣量</c:if> 
										<c:if test="${report.send == 1 }">正常发送</c:if> 
										<c:if test="${report.send == 2 }">协议扣量</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty rdadsr}">
								<td colspan="11" align="center" style="text-align: center;">暂无记录！</td>
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
