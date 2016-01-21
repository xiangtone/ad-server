<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><%=company%>广告平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="/WEB-INF/jsp/include/common/header.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/compiled/index.css" type="text/css" media="screen" />
		<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			function bthScore(type_id) {
				$("#type_id").val(type_id);
				$("#reportHistorical").submit();
			}
			function bthTime(time) {
				$("#time_q").val(time);
				$("#reportHistorical").submit();
			}
			function zidiGlow() {
				$("#stat_Q").show();//显示div
				//$("#stat_Q").hide();//隐藏div
			}
			//图表
			$(function() {
				var _categories = '${static_date}';
				if (_categories) {
					_categories = eval("(" + _categories + ")");
				} else {
					_categories = [];
				}
				var _click = '${click}';
				if (_click) {
					_click = eval("(" + _click + ")");
				} else {
					_click = [];
				}
				var _activate = '${activate}';
				if (_activate) {
					_activate = eval("(" + _activate + ")");
				} else {
					_activate = [];
				}
		
				$('#container').highcharts({
					chart : {
						type : 'spline'
					},
					title : {
						text : '历史统计'
					},
					subtitle : {
						text : 'Source: adwalker.cn'
					},
					xAxis : {
						categories : _categories
					},
					yAxis : {
						title : {
							text : 'Temperature'
						},
						labels : {
							formatter : function() {
								return this.value + '°'
							}
						}
					},
					tooltip : {
						crosshairs : true,
						shared : true
					},
					plotOptions : {
						spline : {
							marker : {
								radius : 4,
								lineColor : '#666666',
								lineWidth : 1
							}
						}
					},
					series : [ {
						name : '点击数',
						marker : {
							symbol : 'square'
						},
						data : _click
		
					}, {
						name : '激活数',
						marker : {
							symbol : 'diamond'
						},
						data : _activate
					} ]
				});
			});
		</script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/include/dev/top.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/jsp/include/dev/left.jsp"></jsp:include>
		<div class="content">
			<div id="pad-wrapper" class="users-list">
				<div class="row header">
					<h3>历史统计</h3>
					<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
						<a href="toAddApplication1.action" class="btn-glow success pull-right"><span>&#43;</span>添加应用</a>
					</div>
				</div>
				<form action="reportHistorical.action" id="reportHistorical" method="post">
					<div class="table-products section">
						<div class="row filter-block">
							<input name="type_id" type="hidden" id="type_id" value="${bean.type_id}"/>
							<input name="time_q" type="hidden" id="time_q" value="${bean.time_q}"/>
							<div class="ui-dropdown">
								应用名： <select id="app_id" name="app_id">
									<option value="">全部</option>
									<c:forEach items="${appList}" var="app">
										<option value="${app.id}" <c:if test="${app.id==bean.app_id}">selected="selected" </c:if>>${app.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="ui-dropdown">
								平台：<select id="os" name="os">
									<option value="0">全部</option>
									<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android">android</option>
									<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios">ios</option>
								</select>
							</div>
							<button class="btn-flat new-product" onclick="$('#reportHistorical').submit();">查询</button>
						</div>
						<div class="col-md-12">
							<h4 class="clearfix">
								图表
								<div class="btn-group">
									<button type="button" class="glow left active" value="" onclick="bthScore('')">全部</button>
									<button type="button" class="glow middle" value="0" <c:if test="${bean.type_id==0}"> style="color:#333333;"</c:if> onclick="bthScore(0)">积分墙</button>
									<button type="button" class="glow middle" value="1" <c:if test="${bean.type_id==1}"> style="color:#333333;"</c:if> onclick="bthScore(1)">推荐墙</button>
									<button type="button" class="glow right" value="5" <c:if test="${bean.type_id==5}"> style="color:#333333;"</c:if> onclick="bthScore(5)">插屏</button>
								</div>
								<div class="btn-group pull-right">
									<button type="button" class="<c:if test='${bean.time_q==7}'>glow left active</c:if><c:if test='${bean.time_q!=7}'>glow middle</c:if>" onclick="bthTime(7)">7日</button>
									<button type="button" class="<c:if test='${bean.time_q==30}'>glow left active</c:if><c:if test='${bean.time_q!=30}'>glow middle</c:if>" onclick="bthTime(30)">30天</button>
									<%--<button type="button" class="<c:if test="${bean.time_q==null}">glow left active</c:if><c:if test="${bean.time_q!=null}">glow middle</c:if>" onclick="zidiGlow()">自定义</button>
									<div id="stat_Q" style="display:none;">
										<input name="start_date" id="start_date" type="text" class="Wdate" value="${start_date}" readonly="readonly" onclick="WdatePicker()" />
										<input name="end_date" id="end_date" type="text" class="Wdate" value="${end_date}" readonly="readonly" onclick="WdatePicker()" />
									</div> --%>
								</div>
							</h4>
						</div>
						<div class="col-md-12">
							<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
						</div>
					</div>
				</form>
				<div class="table-products section">
					<div class="col-md-12">
						<h4 class="clearfix">
							列表
							<div class="btn-group">
								<button type="button" class="glow left active" value="" onclick="bthScore('')">全部</button>
								<button type="button" class="glow middle" value="0" <c:if test="${bean.type_id==0}"> style="color:#333333;"</c:if> onclick="bthScore(0)">积分墙</button>
								<button type="button" class="glow middle" value="1" <c:if test="${bean.type_id ==1}"> style="color:#333333;"</c:if> onclick="bthScore(1)">推荐墙</button>
								<button type="button" class="glow right" value="5" <c:if test="${bean.type_id ==5}"> style="color:#333333;"</c:if> onclick="bthScore(5)">插屏</button>
							</div>
							<div class="btn-group pull-right">
								<button type="button" class="<c:if test='${bean.time_q==7}'>glow left active</c:if><c:if test='${bean.time_q!=7}'>glow middle</c:if>" onclick="bthTime(7)">7日</button>
								<button type="button" class="<c:if test='${bean.time_q==30}'>glow left active</c:if><c:if test='${bean.time_q!=30}'>glow middle</c:if>" onclick="bthTime(30)">30天</button>
								<%--<button type="button" class="glow right" onclick="bthTime(1)">自定义</button> --%>
							</div>
						</h4>
					</div>
					<div class="row">
						<div>
							<table class="table table-hover">
								<thead>
									<tr>
										<th class="col-md-3">时间</th>
										<th class="col-md-3">展示</th>
										<th class="col-md-3">点击</th>
										<th class="col-md-3">点击率</th>
										<th class="col-md-3">激活数</th>
										<th class="col-md-3">预计收入</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty list}">
										<c:set var="pv" scope="request" value="0"></c:set>
										<c:set var="click" scope="request" value="0"></c:set>
										<c:set var="activate" scope="request" value="0"></c:set>
										<c:set var="cost" scope="request" value="0.0"></c:set>
										<c:forEach items="${list}" var="aml" varStatus="status">
											<tr class="first">
												<td>${aml.static_date}</td>
												<td>${aml.pv}</td>
												<c:set var="pv" scope="request" value="${aml.pv + pv}"></c:set>
												<td>${aml.click}</td>
												<c:set var="click" scope="request" value="${aml.click + click}"></c:set>
												<td>
													<c:choose>
														<c:when test="${aml.click_rate!=null}"><fmt:formatNumber value="${aml.click_rate}" pattern="0.00"></fmt:formatNumber>%</c:when>
														<c:otherwise>0.0%</c:otherwise>
													</c:choose>
												</td>
												<td>${aml.activate}</td>
												<c:set var="activate" scope="request" value="${aml.activate + activate}"></c:set>
												<td><fmt:formatNumber value="${aml.cost}" pattern="0.00"></fmt:formatNumber></td>
												<c:set var="cost" scope="request" value="${aml.cost + cost}"></c:set>
											</tr>
										</c:forEach>
										<tr class="first">
											<td>总计</td>
											<td>${pv}</td>
											<td>${click}</td>
											<td>
												<c:choose>
													<c:when test="${click!=0}"><fmt:formatNumber value="${click / pv * 100}" pattern="0.00"></fmt:formatNumber>%</c:when>
													<c:otherwise>0.0%</c:otherwise>
												</c:choose>
											</td>
											<td>${activate}</td>
											<td><fmt:formatNumber value="${cost}" pattern="0.00"></fmt:formatNumber></td>
										</tr>
									</c:if>
									<c:if test="${empty list}">
										<tr>
											<td align="center" colspan="13" style="text-align: center;">暂无记录！</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>
						${pageInfo}
					</div>
				</div>
			</div>
			<script type="text/javascript">
				selectedMenu("menu3_2");
			</script>
		</div>
	</body>
</html>