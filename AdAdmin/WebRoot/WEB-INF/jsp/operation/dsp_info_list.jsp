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

<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
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
/**
 * dsp业务录入
 *	
 **/
function dspFraction(){		
	var url = "manage!addDspInfo.do";
	new $.dialog({
		title:'dsp业务录入',
		page:url,
		width:480,
		height:280,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

function edit(id){		
	var url = "manage!editDspInfo.do?id="+id;
	new $.dialog({
		title:'dsp业务修改',
		page:url,
		width:480,
		height:280,
		drag:true,
		resize:true,
		cover:true,
		
		maxBtn:false}).ShowDialog();
}

$(document).ready(function (){
	initOrder();
});

function refresh(){
	$("#dspInfo").submit();
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

function add(dsp_id){		
	var url = "manage!addDspInfoConfig.do?dsp_id="+dsp_id;
	new $.dialog({
		title:'添加参数项',
		page:url,
		width:480,
		height:180,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}

function editConfig(dsp_id){		
	var url = "manage!editDspInfoConfig.do?dsp_id="+dsp_id;
	new $.dialog({
		title:'添加参数项',
		page:url,
		width:580,
		height:420,
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
					<fieldset class="search_fieldset">
						<legend>DSP业务</legend>
						<div id="search_bar">
						<form action="manage!dspInfoList.do" method="post" id="dspInfo">
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
											</tr>
											<tr>
												<td>dspID</td>
												<td>
													<input name="dsp_id" id="dsp_id" type="text" value="${bean.dsp_id}" maxlength="50" />
												</td>
												<td>dsp名称</td>
												<td>
													<input name="dsp_name" id="dsp_name" type="text" value="${bean.dsp_name}" maxlength="50" />
												</td>
											</tr>
										</table>
									</td>
									<td width="5%" valign="middle" align="center">
										<div style="width: 100px;height: 100%;">
											<input type="submit" value="查询" id="activityFindAll" style="cursor: pointer;" />
											<input type="button" onclick="resetAll('dspInfo');" value="重置" onfocus="this.blur();" class="cx"/>
										</div>
									</td>
								</tr>
							</table>
							</form>
						</div>
					</fieldset>
					<div>
						<input type="button" value="录数" onclick="dspFraction();" />
					</div>
					<div class="main_table">
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
						<tr class="tr_td tb_head">
							<th width="3%"  order="false">序号</th>
							<th width="5%" order="false">dspID</th>
							<th width="10%" order="false">dsp名称</th>
							<th width="10%" order="false">dsp请求路径</th>
							<th width="5%" order="false">备注</th>
							<th width="5%" order="false">启动的服务名称</th>
							<th width="5%" order="false">名称</th>
							<th width="5%" order="false">时间</th>
							<th width="8%"  order="false">操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td style="text-align: center;">
										${status.index+1}
									</td>
									<td>${vo.dsp_id}</td>
									<td>${vo.dsp_name}</td>
									<td>${vo.dsp_url}</td>
									<td>${vo.dsp_desc}</td>
									<td>${vo.service}</td>
									<td>${vo.bean}</td>
									<td>${vo.create_date}</td>
									<td>
										<input type="button" value="修改" onclick="edit('${vo.dsp_id}')"/>
										<input type="button" value="添加参数项" onclick="add('${vo.dsp_id}')"/>
										<input type="button" value="修改参数项" onclick="editConfig('${vo.dsp_id}')"/>
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