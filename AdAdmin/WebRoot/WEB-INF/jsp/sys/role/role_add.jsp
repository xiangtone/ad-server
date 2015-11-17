<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台角色设置</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage/managePurview.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	/**
	 * 添加角色
	 * @return
	 */
	 dg.addBtn("save","保存",function(){ 
			$("#addRole").ajaxSubmit(function(data){
				if(vaildateForm("addRole")){
					if(data){
						var dataObj=eval("("+data+")");//转换为json对象 
						if(dataObj.status==1){
							alert("操作成功！！");
							dg.curWin.refresh();
						}else if(dataObj.status=-1){
							alert("操作失败，请重试！！");
							dg.curWin.refresh();
						}else{
							alert("操作失败，请重试！！");
							dg.curWin.refresh();
						}
					}else{
						alert("操作失败，请重试！！");
						dg.curWin.refresh();
					}
				}
			
			});
		});
</script>
</head>
<body>
	<div class="main admin_main" style="background: #F6F6F6">
		<div class="content clearfix">
			<div class="admin_right">
				<div class="content_right content_new">
					<div>
						<form id="addRole" action="manage!saveRole.do" method="post">
							<table>
								<tr>
									<td>角色名：</td>
									<td class="name"><input  name="name" type="text" reg="^(?=.*?\S)[\s\S]{0,20}$" tip="角色名不能为空"/>
									</td>
								</tr>
								<tr>
									<td>角色编号：</td>
									<td class="code"><input  name="code" type="text" reg="^(?=.*?\S)[\s\S]{0,40}$" tip="角色编号不能为空"/>
									</td>
								</tr>
								<tr>
									<td>角色描述：</td>
									<td>
										<textarea name="note" rows="" cols=""></textarea>
									</td>
								</tr>
							</table>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
