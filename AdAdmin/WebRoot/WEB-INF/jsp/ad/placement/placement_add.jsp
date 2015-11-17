<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运营管理后台广告主发布广告</title>
<link href="${pageContext.request.contextPath}/css/common.css?v=${version}" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/regist/validate.css?v=${version}" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/escoreCategory.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dialog/lhgdialog.min.js?v=${version}"></script>
<script type="text/javascript">
	var dg = frameElement.lhgDG;
	dg.addBtn("save","保存",function(){
		if(_vaildateForm()){
			$("#addPubAct").ajaxSubmit(function(data){
				if(data){
					var dataObj=eval("("+data+")");//转换为json对象 
					if(dataObj.status==1){
						alert("提交成功！！");
						dg.curWin.refresh();
						//dg.cancel();
					}else if(dataObj.status=-1){
						
					}else{
						alert("操作失败，请重试！！");
					}
				}else{
					alert("操作失败，请重试！！");
				}
			});
		}
		
	});
	
	function _vaildateForm(){
		if(vaildateForm("addPubAct")){
		if($("#page_type input:checkbox[checked][name='types']")&&$("#page_type input:checkbox[checked][name='types']").length>0){
			return true;
		}else{
			$("#page_type_tip").show();
			return false;
		}
			}
	}
	
	
	$(document).ready(function(){
		$("#page_type input:checkbox[name='types']").click(function (){
			if($("#page_type input:checkbox[checked][name='types']").length>0){
				$("#page_type_tip").hide();
			}else{
				$("#page_type_tip").show();
			}
		});
		init_type();
	});
	
	
	function init_type(){
		var page_type='${page_type}';
		if(page_type){
			if(page_type.indexOf(",")){
				var arr=page_type.split(",");
				for(var i=0;i<arr.length;i++){
					$("#pagetype_"+arr[i]).attr("checked","checked");
				}
			}else{
				$("#pagetype_"+page_type).attr("checked","checked");
			}
		}
	}

</script>
</head>
<body>
	<div class="main" style="background: #F6F6F6">
		<div class="content clearfix">
				<div class="content_right admin_right">
				<form id="addPubAct" name="addPubAct" action="manage!updatePlacement.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="id" id="adv_id" value="${placement_id}" />
    			<div class="subblock_1" style="display:block;border: 1px solid #9dbaff;float: left;width: 866px;border-top: 0;height: 412px;">
					<table>
						<tr>
							<td valign="middle" align="right">投放名称：</td>
							<td colspan="3" class="name">
								<!-- 取出来活动名称，如果投放名称不存在就显示活动名称 -->
								<input type="text" id="campaign_name" name="name" value="${campaign.campaign_name}"  maxlength="20" reg="^[\u4e00-\u9fa5\w\W]{2,20}$" tip="请输入2-20个字符 "/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<!-- 分类start -->
							<td valign="middle" align="right">投放类别：</td>
							<td>
								<select name="category_id" id="type" reg="[^0]">
									<c:forEach items="${ecList}" var="entry">
										<option value="${entry.id}"	<c:if test="${vo.category_id eq entry.id}">   selected="selected" </c:if>>${entry.fname}-${entry.name}</option>
									</c:forEach>
								</select>
								* 
							</td>
							<!-- 分类end -->
						</tr>
						<tr>
							<td align="right">星级:</td>
							<td>
								<select name="star_level" id="point_desc_status" >
									<option value="5.0" <c:if test="${vo.star_level eq 5.0 }"> selected="selected" </c:if>>五星</option>
									<option value="4.5"	<c:if test="${vo.star_level eq 4.5 }"> selected="selected" </c:if>>四星半</option>
									<option value="4.0"	<c:if test="${vo.star_level eq 4.0 }"> selected="selected" </c:if>>四星</option>
									<option value="3.5"	<c:if test="${vo.star_level eq 3.5 }"> selected="selected" </c:if>>三星半</option>
									<option value="3.0"	<c:if test="${vo.star_level eq 3.0 }"> selected="selected" </c:if>>三星</option>
									<option value="2.5"	<c:if test="${vo.star_level eq 2.5 }"> selected="selected" </c:if>>二星半</option>
									<option value="2.0"	<c:if test="${vo.star_level eq 2.0 }"> selected="selected" </c:if>>二星</option>
									<option value="1.5"	<c:if test="${vo.star_level eq 1.5 }"> selected="selected" </c:if>>一星半</option>
									<option value="1.0" <c:if test="${vo.star_level eq 1.0 }"> selected="selected" </c:if>>一星</option>
								</select>
							</td>
						</tr>
							<tr>
							<td valign="middle" align="right">是否优先推荐：</td>
							<td>
								<input type="radio" name="priority" value="1" checked="checked" <c:if test="${vo.priority ==1}">checked="checked"</c:if> />是
								<input type="radio" name="priority" value="0" <c:if test="${vo.priority ==0}">checked="checked"</c:if> />否
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">广告形式：</td>
							<td>
								<span id="page_type">
									<input id="pagetype_0" type="checkbox" name="types" value="0" />积分墙
									<input id="pagetype_1" type="checkbox" name="types" value="1" />推荐墙
									<input id="pagetype_4" type="checkbox" name="types" value="4" />BANNER
									<input id="pagetype_5" type="checkbox" name="types" value="5" />插屏
								</span>
								<span style="color: red;display: none;" id="page_type_tip">
									请选择投放形式
								</span>
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">广告语：</td>
							<td class="slogan">
								<input name="slogan" id="slogan" type="text" maxlength="50" value="${vo.slogan}" reg="^[\u4e00-\u9fa5\w\W]{2,50}$" tip="请输入2-50个字符 "/>*
							</td>
						</tr>
						<tr>
							<td valign="middle" align="right">关键字：</td>
							<td class="keyword">
								<input name="keyword" id="keyword" type="text" value="${vo.keyword}" />
							</td>
						</tr>
						<tr>
							<td valign="top"></td>
							<td>*活动介绍使用的文字描述，2-1000个汉字，用户将在墙的详情页中看到*</td>
						</tr>
						<tr>
							<td valign="middle" align="right">活动介绍：</td>
							<td class="campaign_desc">
								<textarea name="campaign_desc" cols="50" rows="5" id="campaign_desc" reg="^[\w\W\u4e00-\u9fa5]{2,1000}$" tip="请输入2-1000个字符 " maxlength="1000">${vo.campaign_desc}</textarea>*
								<span id="longDescMes" style="color: #DC143C"></span>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>