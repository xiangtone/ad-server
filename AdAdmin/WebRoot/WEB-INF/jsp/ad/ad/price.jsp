<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告投放单价设置</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/adEffectConfirm.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function applyDevAppManager(adID,price_update){
	var price = $("#"+adID+"").val();
	if(price){
	var url = "manage!modifyAdPrice.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'adId='+adID+'&price='+price+'&price_update='+price_update,
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
	}else{
		alert("单价不能为空！");
	}
}

$(document).ready(function(){
});

function refresh(){
	$("#my_form").submit();	
}


	
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
						<legend>广告投放单价设置</legend>
						<div id="search_bar">
							<form action="manage!adPriceSetting.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>广告ID</td>
													<td>
														<input type="text" name="id" value="${bean.id}" />
													</td>
													<td>活动ID/名称</td>
													<td>
														<input type="text" name="campaign" value="${bean.campaign}" />
													</td>
													<td>媒体类型</td>
													<td>
														<select name="ad_type" id="blanceMode">
															<option value="">全部</option>
															<option <c:if test="${bean.ad_type ==0}"> selected="selected"</c:if> value="0">应用</option>
															<option <c:if test="${bean.ad_type ==1}"> selected="selected"</c:if> value="1">渠道</option>
														</select>
													</td>
												</tr>
												<tr>
													<td>广告主ID/邮箱</td>
													<td><input type="text" name="adv" value="${bean.adv}" /></td>
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
															<option value="0" <c:if test="${bean.status ==0}">selected="selected" </c:if> >初始化</option>
															<option value="-1" <c:if test="${bean.status ==-1}">selected="selected" </c:if> >人工下线</option>
															<option value="-10" <c:if test="${bean.status ==-10}">selected="selected" </c:if> >暂停</option>
															<option value="-20" <c:if test="${bean.status ==-20}">selected="selected" </c:if>>投放结束</option>
															<option value="-30" <c:if test="${bean.status==-30}">selected="selected" </c:if>>超量下线</option>
														</select>
													</td>
												</tr>
												<tr>
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
															<option value="2" <c:if test="${bean.type_id ==2}"> selected="selected"</c:if> >九宫格</option>
															<option value="4" <c:if test="${bean.type_id ==4}"> selected="selected"</c:if> >Banner</option>
															<option value="5" <c:if test="${bean.type_id ==5}"> selected="selected"</c:if> >插屏</option>
														</select>
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
				<form>
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
					<input type="hidden" name="price_update" id="price_update" value="${vo.currentPrice}"/>
						<tr class="tr_td tb_head">
							<th><div class="date"></div>
							 广告ID</th>
							<th>
							 活动ID</th>
							<th>活动名称</th>
							<th>广告主账号</th>
							<th>平台类型</th>
							<th>应用ID</th>
							<th>应用名称</th>
							<th>广告形式</th>
							<th>结算方式</th>
							<th>当前单价</th>
							<th>当前单价生效时间</th>
							<th>修改单价</th>
							<th>操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.id}
									</td>
									<td>
										${vo.campaign_id}
									</td>
									<td>
										${vo.campaign_name}
									</td>
									<td>
										${vo.adv_email}
									</td>
									<td >
										${vo.os}
									</td>
									<td>
										${vo.media_id}
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
									<fmt:formatNumber value="${vo.currentPrice}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> 
									</td>
									<td>
										${vo.effectTime}
									</td>
									<td align="center" >
										<input type="text" id="${vo.id}" style="size: 20xp;width: 40px;" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" maxlength="3" />
									</td>
									
									<td>
										<input type="button" value="保存" onclick="applyDevAppManager('${vo.id}','${vo.currentPrice}');" />
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
					</form>
				</div>
					${pageInfo.pageInfoStr}
				</div>
			</div>
		</div>
	</div>
</body>

</html>
