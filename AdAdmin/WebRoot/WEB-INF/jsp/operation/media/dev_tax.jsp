<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>运营管理后台开发者免税</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Buttons/css/buttons.css" />
<link href="${pageContext.request.contextPath}/css/page.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/checkbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">

function devTaxList(dev_id,tax_status){
	var url = "manage!modifyDevTaxSetting.do";
	if(confirm("确认要执行该操作吗？")){
			//window.location.href=url;//不是驳回直接跳转
		$.ajax({
			url:url,
			type:'POST',
			data:'dev_id='+dev_id+'&tax_status='+tax_status,
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

$(document).ready(function (){
});
	
</script>
</head>
<body>
	<div class="main admin_main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<fieldset class="search_fieldset">
						<legend>开发者免税审核 </legend>
						<form action="manage!devTaxList.do" id="my_form" method="post">
							<table width="100%">
								<tr>
									<td>
										<table width="100%">
											<tr>
												<td>开发者ID</td>
												<td><input type="text" name="dev_id" value="${bean.dev_id}" /></td>
												<td>开发者账号</td>
												<td><input type="text" name="dev_email" value="${bean.dev_email}"/></td>
												<td>联系人</td>
												<td><input type="text" name="dev_name"	value="${bean.dev_name}"/></td>
											</tr>
											<tr>	
												<td>免税状态</td>
												<td>
													<select name="tax_status" id="tax_status">
														<option value="">全部</option>
														<option value="1" <c:if test="${bean.tax_status =='1'}">selected="selected"</c:if>>正常</option>
														<option value="2" <c:if test="${bean.tax_status =='2'}">selected="selected"</c:if>>免税</option>
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
					</fieldset>
				<div class="main_table">
				<form>
					<table width="100%" cellpadding="0" cellspacing="1" id="tb"
						class="table_bod1 font_stat" >
						<tr class="tr_td tb_head">
							<th><div class="date"></div>
							开发者ID</th>
							<th>开发者账号</th>
							<th>联系人</th>
							<th>免税状态</th>
							<th width="20%">操作</th>
						</tr>
						<c:if test="${!empty list}">
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr>
									<td>
										${vo.dev_id}
									</td>
									<td>
										${vo.dev_email}
									</td>
									<td>
										${vo.dev_name}
									</td>
									<td >
										<c:if test="${vo.tax_status == 1}">正常</c:if>
										<c:if test="${vo.tax_status == 2}">免税</c:if>
									</td>
									<td>
										<c:if test="${vo.tax_status == 1}">
											<input type="button" value="免税" onclick="devTaxList('${vo.dev_id}',2)" style="cursor: pointer;"/>
										</c:if>
										<c:if test="${vo.tax_status == 2}">
											<input type="button" value="正常" onclick="devTaxList('${vo.dev_id}',1)" style="cursor: pointer;" />
										</c:if>
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