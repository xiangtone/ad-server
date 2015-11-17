<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台权限设置 </title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/managePurview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=${version}"></script>
<script type="text/javascript">
var dg = frameElement.lhgDG;

dg.addBtn("save","保存",function(){
	if($("#tb input:checkbox[checked][@value]").length<=0){
		alert("提示：请选择要增加的权限！！");
		return;
	}
	if(confirm("确认要要增加勾选的权限？")){
		var ids=getCheckedId();
		$.ajax({
			url:'${pageContext.request.contextPath}/manage!roleAddPermission.do',
			type:'POST',
			data:'ids='+ids+'&id=${id}',
			dataType:'text',
			beforeSend:function(){
				//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
			},
			success:function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status=='1'){
						alert("操作成功！！");
						dg.parent.dgWin.refresh();
						dg.cancel();
					}
				}
			},
			error:function(data){
				alert("");
			}
		});
	}
	
});

/**
 * 全选
 **/
function selectAll(value){
	var b=value;
	$("#tb input:checkbox").attr("checked", b);
}


function changetStatus(id,status){
	$.ajax({
		url:'${pageContext.request.contextPath}/blackChangeStatus.do',
		type:'POST',
		data:'id='+id+'&status='+status+'&app_id=${bean.app_id}',
		dataType:'text',
		beforeSend:function(){
			//$(obj).after('<img src="${pageContext.request.contextPath}/images/ajax_loader.gif" />');
		},
		success:function(data){
			if(data){
				var dataObj=eval("("+data+")");//转换为json对象 
				if(dataObj.status=='1'){
					alert("操作成功！！");
					if(status==1){
						$("#"+id+"_open").attr("disabled","disabled");
						$("#"+id+"_close").removeAttr("disabled"); 
					}else if(status==0){
						$("#"+id+"_open").removeAttr("disabled");
						$("#"+id+"_close").attr("disabled","disabled");
					}
				}
			}
		},
		error:function(data){
			alert("");
		}
	});
	
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
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<form action="manage!roleSelPermission.do" method="post">
				<div class="content_right admin_right">
					<div>
						<input type="hidden" name="app_id" value="${bean.app_id}" />
					</div>
					<table>
							<tr>
								<td>权限名称</td>
								<td>
									<input name="name" type="text" value="${bean.name}" />
								</td>
								<td>权限编号</td>
								<td>
									<input name="code" id="res_url" type="text" value="${bean.code}" />
								</td>
								<td><input value="查询" type="submit" /></td>
							</tr>
					</table>
					<div class="main_table">
						<table width="100%" cellpadding="0" cellspacing="1" id="tb"	class="font_stat">
							<tr class="tr_td">
								<th width="5%"><input id="checkbox" type="checkbox" value="${vo.id}" onclick="selectAll(this.checked);" /></th>
								<th width="8%">名称</th>
								<th width="10%">操作</th>
							</tr>
							<c:if test="${!empty list}">
								<c:forEach items="${list}" var="vo" varStatus="status">
									<tr>
										<td style="text-align: center;"><input id="checkbox" name="app" type="checkbox" value="${vo.id}" /></td>
										<td align="center">${vo.campaign_name}</td>
										<td align="center">
											${vo.status}
											<input type="button" id="${vo.id}_open" value="打开"  onclick="changetStatus('${vo.id}',1);" <c:if test="${vo.status!=0}"> disabled="disabled"</c:if>  />
											<input type="button" id="${vo.id}_close" value="关闭" onclick="changetStatus('${vo.id}',0);"  <c:if test="${vo.status!=1}"> disabled="disabled"</c:if> />
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<tr>
									<td align="center" colspan="9" style="text-align: center;">暂无记录！</td>
								</tr>
							</c:if>
						</table>
					</div>
					${pageInfo.pageInfoStr}
				</div>
			</form>
		</div>
	</div>
</body>
</html>