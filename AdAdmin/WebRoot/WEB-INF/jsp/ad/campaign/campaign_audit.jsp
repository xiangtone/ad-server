<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${business_title}</title>
<link type="text/css" rel="stylesheet" id="cssfile"	href="<%=request.getContextPath()%>/css/landscape.css?v=${version}" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js?v=${version}" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loading/loading.js?v=${version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/regist/validate.pack.js?v=${version}"></script>
<script type="text/javascript">
	var dg;
	$(document).ready(function(){
		dg = frameElement.lhgDG;
		dg.addBtn("save","保存",function(){
			var radioes=document.getElementsByName("ispass");
			for(var i=0;i<radioes.length;i++){
				if(radioes[i].checked){
				    if(radioes[i].value!=1){
					    if(!$("#auditLog_auditNote").val()&& !$("#auditLog_auditNote").val().length>0){
					    		alert("审核不通过的活动必须填写描述！");
					   }else{
						    ab();
							};
					    }else{
					    	ab();
						}
					}
				}	 
		});
	});

	function ab(){
			if(vaildateForm("myform")){
		$("#myform").ajaxSubmit(function(data){
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
		}
</script>
</head>
<body>
	<div id="content" style="padding: 0; height: 340px;">
		<h2 class="dot" style="text-align: left;">
			<span style="float: left;color: red;">
				！注意：请认真审核活动信息
			</span> 
		</h2>
		<div class="main_right"
			style="height: 150px; overflow: hidden; overflow-y: auto;">
			<table width="950" style="border: 0;">
				<tr>
					<td>
						<table width="950" border="0">
							<caption
								style="font-size: 15px; font-weight: bold; color: #333; line-height: 30px;">${accept_code}</caption>
							<tr>
								<td width="75" bgcolor="#cfe1e2" align="right">活动名称:</td>
								<td width="220" align="left">${vo.campaign_name}</td>
								<td width="79" bgcolor="#cfe1e2" align="right">平台类型:</td>
								<td width="212" align="left">${vo.os}</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">活动类型：</td>
								<td align="left">
									<c:forEach items="${sList}" var="entry">
										<c:if test="${vo.category_id eq entry.id}">${entry.name}</c:if>
									</c:forEach>
								</td>
								<td bgcolor="#cfe1e2" align="right">计费方式：</td>
								<td align="left">${vo.charge_type}</td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">总预算:</td>
								<td align="left">${vo.budget}</td>
								<td bgcolor="#cfe1e2" align="right">接入单价:</td>
								<td align="left">${vo.price} 元</td>
							</tr>
							
							<tr>
								<td bgcolor="#cfe1e2" align="right">排期时间：</td>
								<td colspan="3" align="left"><fmt:formatDate value="${vo.plan_start}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${vo.plan_end}" type="date" dateStyle="long" pattern="yyyy-MM-dd"/></td>
							</tr>
							<tr>
								<td bgcolor="#cfe1e2" align="right">活动要求:</td>
								<td colspan="3" align="left">${vo.campaign_required}</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div class="blqy" style="height: 200px;">
			<h2 class="dot" style="text-align: left;">操作区域</h2>
			<div class="main_area" style="padding: 10px 0; float: left; height: 100px;">
				<form action="manage!auditCampaign.do" id="myform" method="post">
					<div style="display: none;">
						<input type="hidden" name="id" value="${vo.id}"/>
					</div>
					<p style="width: 800px; float: left; height: 20px; padding: 5px;">
						<label style="float: left; width: 80px; text-align: right;">是否通过：</label>
						<input type="radio" name="ispass" style="float: left;"	value="1" checked="checked" id="auditLog_isPass" />
						<span style="float: left;">是</span>
						<input type="radio" name="ispass" style="float: left; margin-left: 10px;" value="-1" id="auditLog_isPass" />否
					</p>
					<p style="width: 800px; float: left; height: 20px; padding: 5px;">
						<label
							style="height: 60px; line-height: 20px; float: left; width: 80px; text-align: right;">描述：</label>
						<textarea
							style="width: 400px; padding: 5px; float: left; height: 50px; border: 1px solid #9fcae0;"
							name="note" id="auditLog_auditNote"></textarea>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>