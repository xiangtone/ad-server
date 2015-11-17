<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后广告管理</title>
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
/**
 * 活动投放基本信息
 *	
 **/
function add(){
	var url = "appMediaAdd.do";
	new $.dialog({
		title:'添加媒介人员信息',
		page:url,
		width:475,
		height:300,
		drag:true,
		resize:true,
		cover:true,
		maxBtn:false}).ShowDialog();
}


function deleteObj(id){
	var url = "deleteAppMedia.do";
	if(confirm("确认要执行该操作吗？")){
		$.ajax({
			url:url,
			type:'POST',
			data:'id='+id,
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
function editObj(id){
		var url = "appMediaEdit.do?id="+id;
		new $.dialog({
			title:'修改媒介人员信息',
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
						<legend>媒介管理 </legend>
						<div id="search_bar">
							<form action="appMediaList.do" id="my_form" method="post">
								<table width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr>
													<td>ID</td>
													<td><input type="text" name="id" value="${bean.id}" /></td>
													<td>姓名</td>
													<td>
														<input type="text" name="name" value="${bean.name}" />
													</td>
													<td>组</td>
													<td>
														<select name="area_type" id="adForm">
															<option value="">全部</option>
															<option value="0" <c:if test="${bean.area_type ==0}">selected="selected"</c:if> >平台</option>
															<option value="1" <c:if test="${bean.area_type ==1}"> selected="selected"</c:if> >微赢</option>
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
					<div>
						<input type="button" value="添加" onclick="add();" />
					</div>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat" >
							<tr class="tr_td">
								<th width="3%">序号</th>
								<th width="5%">ID</th>
								<th width="10%">姓名</th>
								<th width="10%">电话</th>
								<th width="10%">QQ</th>
								<th width="10%">组</th>
								<th width="10%">创建时间</th>
								<th width="8%">创建人</th>
								<th width="8%">操作</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td style="text-align: center;">
											${status.index+1}
										</td>
										<td>
											${vo.id}
										</td>
										<td>
											${vo.name}
										</td>
										<td>
											${vo.mobile}
										</td>
										<td>
											${vo.qq}
										</td>
										<td>
											<c:if test="${vo.area_type eq '1' }">
												微赢
											 </c:if>
											 <c:if test="${vo.area_type eq '0' }">
											   	 平台
											 </c:if>
										</td>
										<td>
											<fmt:formatDate value="${vo.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											${vo.create_user_name}
										</td>
										<td>
											<input type="button" value="修改" onclick="editObj('${vo.id}');" />
											<input type="button" value="删除" onclick="deleteObj('${vo.id}');"  />
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