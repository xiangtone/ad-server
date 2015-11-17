<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后广告管理  </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
function adOnline(adID,status){
	var url = "${pageContext.request.contextPath}/manage!adOnline.do";
	if(status == 1){
		alert("已经是上线状态");
		return;
	}
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'adId='+adID,
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


function adSuspend(adID,status){
	var url = "manage!adOffline.do";
	if(status == 0){
		alert("已经是下线状态");
		return;
	}
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'adId='+adID,
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
		var url = "adClusterEdit.do?adId="+adId;
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
							<form action="adClusterList.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告ID/名称</td>
													<td>
														<input type="text" name="ad" value="${bean.ad}" />
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
															<option value="-1" <c:if test="${bean.status ==-1}">selected="selected" </c:if> >下线</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>平台类型</td>
													<td>
														<select name="os" id="os">
															<option value="">全部</option>
															<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
															<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
														</select>
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
													<td>接口状态</td>
													<td>
														<select name="res_status">
															<option value="">全部</option>
															<option value="0" <c:if test="${bean.res_status ==0}">selected="selected"</c:if> >正常</option>
															<option value="1" <c:if test="${bean.res_status ==1}"> selected="selected"</c:if> >关闭</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>来源</td>
													<td>
														<select name="res_code" >
															<option value="">全部</option>
															<option <c:if test="${bean.res_code =='KUAIYOU'}">selected="selected" </c:if> value="KUAIYOU" >快有</option>
															<option <c:if test="${bean.res_code =='YOUMI'}">selected="selected" </c:if> value="YOUMI" >有米</option>
															<option <c:if test="${bean.res_code =='CHUKONG'}">selected="selected" </c:if> value="CHUKONG" >触控</option>
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
								<th width="10%">广告名称</th>
								<th width="5%">接入单价</th>
								<th width="10%">来源</th>
								<th width="5%">OS</th>
								<th width="8%">AD_TYPE</th>
								<th width="10%">结算方式</th>
								<th width="5%">结算单价</th>
								<th width="8%">广告状态</th>
								<th width="8%">接口状态</th>
								<th width="10%">接入时间</th>
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
												<c:when test="${(vo.status==1)&&(vo.media_status==0)}">
													<img src="images/sys/green.gif" alt="" />
												</c:when>
												<c:when test="${(vo.status==-1)&&(vo.media_status==1)}">
													<img src="images/sys/red.gif" alt="" />
												</c:when>
												<c:otherwise>
													<img src="images/sys/yellow.gif" alt="" />
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											${vo.adId}
										</td>
										
										<td>
											<escore:subStr len="20" value="${vo.ad_name}" />
										</td>
										<td>
											${vo.income_price}
										</td>
										<td>
											${vo.res_code}
										</td>
										<td>
											${vo.os}
										</td>
										<td>
											<c:choose>
												<c:when test="${vo.type_id ==0}">
													积分墙
												</c:when>
												<c:when test="${vo.type_id ==1}">
													推荐墙
												</c:when>
												<c:when test="${vo.type_id==2}">
													九宫格
												</c:when>
												<c:when test="${vo.type_id==4}">
													Banner
												</c:when>
												<c:when test="${vo.type_id==5}">
													插屏
												</c:when>
											</c:choose>
										</td>
										<td>
											${vo.blance_mode}
										</td>
										<td>
											${vo.blance_price}
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
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${vo.media_status ==0}">
													正常
												</c:when>
												<c:when test="${vo.media_status ==-1}">
													关闭
												</c:when>
												<c:otherwise>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd"/>
										</td>
										<td>
											<input type="button" value="修改" onclick="findUpdateAdv('${vo.adId}');" <c:if test="${vo.status==-5}">disabled</c:if> <escore:security code="BTN_AD_MODIFY" type="button" /> />
											<input type="button" value="上线" onclick="adOnline('${vo.adId}','${vo.status}');" <c:if test="${(vo.status!=-1)&&(vo.status!=-20)}">disabled</c:if> <escore:security code="BTN_AD_ONLINE" type="button" /> />
											<input type="button" value="下线" onclick="adSuspend('${vo.adId}','${vo.status}');" <c:if test="${(vo.status!=1)&&(vo.status!=-30)}"> disabled</c:if> <escore:security code="BTN_AD_OFFLINE" type="button" /> />
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