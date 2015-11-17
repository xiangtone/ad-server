<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告单价设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}"	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
var dg = frameElement.lhgDG;
dg.addBtn("save_0001","保存",function(){ 
		$("#editForm").ajaxSubmit(function(data){
			if(vaildateForm("editForm")){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("操作成功！！");
						dg.parent.dgWin.refresh();
					}else if(dataObj.status=-1){
						alert("操作失败，请重试！！");
						dg.parent.dgWin.refresh();
					}else{
						alert("操作失败，请重试！！");
						dg.parent.dgWin.refresh();
					}
				}else{
					alert("操作失败，请重试！！");
					dg.parent.dgWin.refresh();
				}
				dg.cancel();
			}
		});
	});
</script>
</head>
<body>
	<div class="main">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<form name="editForm" action="manage!addItem.do" id="editForm" method="post">
						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#999999" class="style_table">
							<!-- 标题部分 -->
							<tr bordercolor="#999999">
								<td colspan="1">-添加新的item-</td>
								<td><font color="red">${wrong_message.error}</font>
								</td>
								<td colspan="30"><input type="hidden" id="tableid"
									name="tableid" value="${tableid}"></input>
								</td>
							</tr>
							<!--编辑部分 -->
							<tr>
								<td>SQL名称<font color="red">(为搜索时有效)</font>:</td>
								<td><input name="sqlName" id="sqlName"
									value="${data.sqlName}"> <font color="red">${wrong_message.sqlName_error}</font>
								</td>
							</tr>
							<tr>
								<td>页面名称:</td>
								<td><input name="jspName" id="jspName"
									value="${data.jspName}"> <font color="red">${wrong_message.jspName_error}</font>
								</td>
							</tr>
							<tr>
								<td>页面样式:</td>
								<td><select id="format" name="format">
										<option value="str"
											<c:if test="${data.format=='str'}">selected</c:if>>字符串"string"</option>
										<option value="int"
											<c:if test="${data.format=='int'}">selected</c:if>>整数"10,000"</option>
										<option value="flo"
											<c:if test="${data.format=='flo'}">selected</c:if>>浮点数"10,000.00"</option>
										<option value="per"
											<c:if test="${data.format=='per'}">selected</c:if>>百分比"100.12%"</option>
										<option value="dat"
											<c:if test="${data.format=='dat'}">selected</c:if>>日期"0000-00-00"</option>
										<option value="time"
											<c:if test="${data.format=='time'}">selected</c:if>>时间"0000-00-00
											00:00:00"</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>状态:</td>
								<td><select id="status" name="status">
										<option value="1"
											<c:if test="${data.status=='1'}">selected</c:if>>启用</option>
										<option value="9"
											<c:if test="${data.status=='9'}">selected</c:if>>弃用</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>排序值:</td>
								<td><input name="sequence" id="sequence"
									value="${data.sequence}"> <font color="red">${wrong_message.sequence_error}</font>
								</td>
							</tr>
							<tr>
								<td>是否搜索:</td>
								<td><select id="isSearch" name="isSearch">
										<option value="n"
											<c:if test="${data.isSearch=='n'}">selected</c:if>>否</option>
										<option value="y"
											<c:if test="${data.isSearch=='y'}">selected</c:if>>是</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>页面显示类型<font color="red">(只有在为搜索时生效)</font>:<font
									color="red">${wrong_message.jspType_error}</font>
								</td>
								<td><select id="jspType" name="jspType">
										<option value="">请选择</option>
										<option value="i"
											<c:if test="${data.jspType=='i'}">selected</c:if>>input</option>
										<option value="s"
											<c:if test="${data.jspType=='s'}">selected</c:if>>select</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>SQL搜索类型<font color="red">(只有在为搜索时生效)</font>:</td>
								<td><select id="sqlType" name="sqlType">
										<option value="">请选择</option>
										<option value="="
											<c:if test="${data.sqlType=='='}">selected</c:if>>=</option>
										<option value="like"
											<c:if test="${data.sqlType=='like'}">selected</c:if>>like</option>
								</select>
								</td>
							</tr>
							<tr>
								<td>数据源SQL<font color="red">(页面显示为select时生效)</font>:</td>
								<td>
									<textarea id="originSql" name="originSql" cols="80" rows="5">${data.originSql}</textarea>
								</td>
							</tr>
							<tr>
								<td>是否为链接:</td>
								<td>
									<select id="isLink" name="isLink">
										<option value="n" <c:if test="${data.isLink=='n'}">selected</c:if>>否</option>
										<option value="y" <c:if test="${data.isLink=='y'}">selected</c:if>>是</option>
									</select>
									<font color="red">${wrong_message.isLink_error}</font>
								</td>
							</tr>
							<tr>
								<td>链接地址<font color="red">(为链接时生效)</font>:</td>
								<td><textarea id="link" name="link" cols="80" rows="1">${data.link}</textarea>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>