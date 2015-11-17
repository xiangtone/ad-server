<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台活动查询统计</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css">
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<style type="text/css">s
#tb input {
	cursor: pointer;
}
</style>
<script type="text/javascript">

	/**
	 * 查看活动
	 *	
	 **/
	function campaignInfo(id) {
		var url = "manage!campaignInfo.do?campaign_id=" + id;
		new $.dialog({
			title:'活动信息',
			page:url,
			width:535,
			height:500,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	
	
	function placementInfo(id) {
		var url = "manage!placementInfo.do?id=" + id;
		new $.dialog({
			title:'投放信息',
			page:url,
			width:1090,
			height:520,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	
	/**
	 * 活动审核
	 *	
	 **/
	function auditPlacement(actId) {
		var url = "manage!preAuditPlacement.do?placement_id=" + actId;
		new $.dialog({
			title:'方案审核',
			page:url,
			width:1090,
			height:550,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}

	/**
	 * 添加物料
	 *	
	 **/
	function  addMaterial(actId) {
		var url = "manage!editMaterial.do?id=" + actId;
		new $.dialog({
			title:'上传物料',
			page:url,
			width:875,
			height:620,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	

	/**
	 * 活动投放基本信息
	 *	
	 **/
	function addPlacement(id){
		var url = "manage!addPlacement.do?id=" + id;
		new $.dialog({
			title:'活动投放---基本信息',
			page:url,
			width:775,
			height:450,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	
	/**
	 * 修改方案
	 *	
	 **/
	function editPlacement(id){
		var url = "manage!editPlacement.do?id=" + id;
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
	
	/**
	 * 活动审核
	 *	
	 **/
	function submitPlacement(id){
		var url = "manage!preSubmitPlacement.do?id=" + id;
		new $.dialog({
			title:'活动投放---修改投放',
			page:url,
			width:1090,
			height:580,
			drag:true,
			resize:true,
			cover:true,
			maxBtn:false}).ShowDialog();
	}
	
	/**
	 * 刷新界面
	 *	
	 **/
	function refresh(){
		$("#my_form").submit();
	}
	
	function loadPageType(){
		var sel="${bean.type_id_str}";
		if(sel){
			if(sel.indexOf(",")>0){
				var arr=sel.split(",");
				for(var i=0;i<arr.length;i++){
					$("#type_id_"+arr[i]).attr("checked","checked");
				}
			}else{
				$("#type_id_"+sel).attr("checked","checked");
			}
		}
	}
	
	$(document).ready(function(){
		loadPageType();
	});
	
	/**
	 * 全选
	 **/
	function selectAll(value){
		var b=value;
		$("#tb input:checkbox").attr("checked", b);
	}
	


	//获取所有选中的id
	function getCheckedId(){
		var ids="";
		if($("#tb input:checkbox[checked][@value]").length>0){
			$("#tb input:checkbox[checked][@value]").each(function(){
				if(ids){
					ids=ids+","+$(this).val();
				}else{
					ids=$(this).val();
				}
			});
		}
		return ids;
	}

	
	function adOffline(){
		if($("#tb input:checkbox[checked][@value]").length<=0){
			alert("提示：请选择要下线的投放！！");
			return;
		}
		if(confirm("确认要对勾选的投放进行下线操作？")){
		var ids=getCheckedId();
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!placementOffline.do',
			type:'POST',
			data:'ids='+ids,
			dataType:'text',
			beforeSend:function(){
					//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status=='1'){
						alert("下线成功！！");
						refresh();
					}
				}
				
			}
		});
		}
	}


	
	
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="content_right admin_right">
				<fieldset class="search_fieldset">
				<legend>投放查询统计 </legend>
				<form action="manage!placementList.do" method="post" id="my_form">
					<table width="100%">
						<tr>
							<td>
								<table width="100%">
									<tr>
										<td>广告主ID/名称</td>
										<td>
											<input name="adv" type="text" value="${bean.adv}" />
										</td>
										<td>活动ID/名称</td>
										<td>
											<input name="campaign" id="id" type="text" value="${bean.campaign}" />
										</td>
									</tr>
									<tr>
										<td>投放名称</td>
										<td>
											<input name="placement_name" id="manager_name" type="text" value="${bean.placement_name}" />
										</td>
										<td>状态</td>
										<td>
											<select name="status" id="status" style="width: 155px;">
												<option value="">全部</option>
												<option value="-30"	<c:if test="${bean.status =='-30'}">selected="selected"</c:if>>待投放</option>
												<option value="-20"	<c:if test="${bean.status =='-20'}">selected="selected"</c:if>>投放草稿</option>
												<option value="-10"	<c:if test="${bean.status =='-10'}">selected="selected"</c:if>>投放待审核</option>
												<option value="1" <c:if test="${bean.status =='1'}">selected="selected"</c:if>>已发布</option>
												<option value="-1" <c:if test="${bean.status =='-1'}">selected="selected"</c:if>>下线</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>平台类型</td>
										<td>
											<select name="os">
												<option value="">全部</option>
												<option <c:if test="${bean.os =='android'}">selected="selected" </c:if> value="android" >android</option>
												<option <c:if test="${bean.os =='ios'}">selected="selected" </c:if> value="ios" >ios</option>
											</select>
										</td>
										<td>投放形式</td>
										<td>
											<input id="type_id_0" name="type_id" type="checkbox" value="0" />积分墙
											<input id="type_id_1" name="type_id" type="checkbox" value="1"/>推荐墙
											<input id="type_id_4" name="type_id" type="checkbox" value="4"/>BANNER
											<input id="type_id_5" name="type_id" type="checkbox" value="5"/>插屏
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
				</fieldset>
				<div>
					<input type="button" value="活动结束" onclick="adOffline();" />
				</div>
				<div class="main_table">
				<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
					<!--修改-->
					<tr class="tr_td">
						<th width="3%"><input id="checkbox" type="checkbox" value="${k.id}" onclick="selectAll(this.checked);" /></th>
						<th>广告主ID</th>
						<th>广告主名称</th>
						<th>活动ID</th>
						<th>活动名称</th>
						<th>投放名称</th>
						<th>投放形式</th>
						<th>接入单价</th>
						<th>投放类别</th>
						<th>平台类型</th>
						<th>星级</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list}" var="vo">
						<tr style="text-align: center;">
							<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${vo.id}" /></td>
							<td>${vo.adv_id}</td>
							<td>${vo.company_name}</td>
							<td>${vo.id}</td>
							<td>
								<a href="javascript:void(0);" onclick="campaignInfo('${vo.id}')" title="点击查看活动详细">${vo.campaign_name}</a>
							</td>
							<td>
								<a href="javascript:void(0);" onclick="placementInfo('${vo.placement_id}')" title="点击查看活动详细">${vo.placement_name}</a>
							</td>
							<td>${vo.typeStr}</td>
							<td>${vo.price}</td>
							<td>
								<c:forEach items="${ecList}" var="entry">
									<c:if test="${vo.placement_category_id eq entry.id}">${entry.fname}-${entry.name}</c:if>
								</c:forEach>
							</td>
							<td>${vo.os}</td>
							<td>
							<c:choose>
								<c:when test="${vo.star_level == 5}">
									五星
								</c:when>
								<c:when test="${vo.star_level == 4.5}">
									四星半
								</c:when>
								<c:when test="${vo.star_level == 4.0}">
									四星
								</c:when>
								<c:when test="${vo.star_level == 3.5}">
									 三星半
								</c:when>
								<c:when test="${vo.star_level == 3.0}">
									 三星
								</c:when>
								<c:when test="${vo.star_level == 2.5}">
									 二星半
								</c:when>
								<c:when test="${vo.star_level == 2.0}">
									 二星
								</c:when>
								<c:when test="${vo.star_level == 1.5}">
									 一星半
								</c:when>
								<c:when test="${vo.star_level == 1.0}">
									 一星半
								</c:when>
								<c:otherwise>
								</c:otherwise>
								</c:choose>
							</td>
							<td>
								${vo.status_name}
							</td>
							<td>
								<input type="button" value="投放" onclick="addPlacement('${vo.placement_id}','${vo.id}')" <c:if test="${vo.status!=-30}"> disabled</c:if> <escore:security code="BTN_PLACEMENT_TUFANG" type="button" /> />
								<input type="button" value="修改素材" onclick="addMaterial('${vo.placement_id}')" <c:if test="${(vo.status!=-20)&&(vo.status!=1)}"> disabled</c:if> <escore:security code="BTN_PLACEMENT_TUFANG" type="button" />/>
								<input type="button" value="修改方案" onclick="editPlacement('${vo.placement_id}')"  <escore:security code="BTN_PLACEMENT_TUFANG" type="button" /> />
								<input type="button" value="提交" onclick="submitPlacement('${vo.placement_id}')" <c:if test="${((vo.status!=-18)&&(vo.status!=-17)&&(vo.status!=-19)&&(vo.status!=-20))}"> disabled</c:if> <escore:security code="BTN_PLACEMENT_TUFANG" type="button" /> />
								<input type="button" value="审核" onclick="auditPlacement('${vo.placement_id}')" <c:if test="${vo.status!=-10}"> disabled</c:if> <escore:security code="BTN_PLACEMENT_AUDIT" type="button" /> />
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty list}">
						<tr>
							<td align="center" colspan="15" style="text-align: center;">暂无数据！</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div style="display: block;height: 10px;"></div>
				${pageInfo.pageInfoStr}
			</div>
		</div>
	</div>
</body>
</html>