<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="escore" uri="/WEB-INF/tld/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>渠道账号管理页面</title>
		<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js?v=${version}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/submit.js"></script>
<script type="text/javascript">
       function resetPassManageEmail(email){
	    var url = "manage!accountresetPassManageEmail.do?email="+email;
	    if(confirm("帐号密码重置，确认重置吗？")){	
	    	jump(url);					
	}
}

	function accountClosedEmail(email){
		var url = "manage!accountClosedEmail.do?email="+email;
		if(confirm("封号后将会终止该帐号下的所有应用，确认封号吗？")){
				//window.location.href=url;
				jump(url);
		}
	}
	function accountActivationEmail(email){
		var url = "manage!accountActivationEmail.do?email="+email;
		if(confirm("确认要为该帐号手动激活吗？")){
				//window.location.href=url;
				jump(url);
		}
	}
	function jump(url){
		var a = document.createElement("a");
		if(!a.click) {
     		 window.location = url;
     		 return;
   		 }
   		a.setAttribute("href", url);
   		a.style.display = "none";
   		document.body.appendChild(a);
   		a.click();
	}
</script>
	</head>
	<body>
		<div class="head_bj head_bj_bt">
			<div class="header rel admin_head">
				<div class="clearfix land_top">
					<div class="land_nav fl">
						数据统计&nbsp;>&nbsp;渠道账号管理
					</div>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="content clearfix">
				<jsp:include page="../../../jsp/manage/common/manage_left.jsp" />
				<div class="admin_right">
				<div class="content_right content_new">
						<div class="bord_bom1px rel">
						<div class="abs" style="float: left; margin-left: 300px;">
							<a href="manage!editMailsNotice.do"></a>
						</div>
						渠道账号管理> 渠道列表
					</div>
					<form action="manage!listChannelAccountmanageselect.do" method="get">
<table>
	<tr>
		<td><input name="searchText" type="text" value="${vo.searchText }" /></td>
		<td><select name="searchType" >
							<c:choose>
								<c:when test="${vo.searchType == 'email'}">
									 <option value="email" selected="selected">用户名</option> 
										<option value="channel_name">渠道名</option>
 										<option value="channel_id" >渠道号</option>
								</c:when>
								<c:when test="${vo.searchType == 'channel_name'}">
									    <option value="email">用户名</option> 
										<option value="channel_name" selected="selected">渠道名</option>
 										<option value="channel_id" >渠道号</option>
								</c:when>
								<c:when test="${vo.searchType == 'channel_id'}">
									   <option value="email">用户名</option> 
										<option value="channel_name">渠道名</option>
 										<option value="channel_id" selected="selected">渠道号</option>
								</c:when>
								<c:otherwise>
										<option value="email">用户名</option> 
										<option value="channel_name">渠道名</option>
 										<option value="channel_id" >渠道号</option>
								</c:otherwise>
							</c:choose>
						</select>
		</td>																									
		<td><input type="submit" value="查询"/></td>
	</tr>
</table>

					<div align="right"><a href="manage!editchannelsAccoutManage.do">添加账户</a></div>	
					${pageInfo }				
					<table cellpadding="5" cellspacing="1" class="table_bod1 font_stat" width="100%">
						<tr class="tr_td">
							<td>序号</td>
							<td>用户名</td>
							<td>渠道名</td>
							<td>渠道号</td>
							<td>抽成比例</td>
							<td>创建时间</td>
							<td>账号状态</td>
							<td>联系人</td>
							<td>联系电话</td>
							<td>操作</td>
						</tr>
										<c:if test="${!empty channelAccountList}">
							<c:forEach items="${channelAccountList}" var="news" varStatus="status">
							<tr>
								<td  align="center">
								${news.id}
								</td>
								<td  align="center">
									<a href="manage!editchannelsAccoutManage.do?email=${news.email}">${news.email}</a> 									
								</td>																																						
								<td  align="center">
									${news.channel_name}
								</td>								
								<td  align="center">
									${news.channel_id}
								</td>
								<td  align="center">
									${news.into_Rate}
								</td>
								
								<td  align="center">
									${news.create_time}
								</td>
								<td  align="center">
									<c:choose>
				                         <c:when test="${news.status eq 0}">封号</c:when>
				                         <c:when test="${news.status eq 1}">正常</c:when>				                        
			                         </c:choose>
								</td>								
								<td  align="center">
									${news.real_name}
								</td>								
								<td  align="center">
									${news.phone}
								</td>								
								<td  align="center">
										<input type="button" value="密码重置" onclick="resetPassManageEmail('${news.email}')"/>			   
			                <c:if test="${news.status == 0}">
			                        	<input type="button" value="激活" onclick="accountActivationEmail('${news.email}')"/>
		                	</c:if>
			              <c:if test="${news.status == 1}">
			 	                       <input type="button" value="封号" onclick="accountClosedEmail('${news.email}')"/>
			               </c:if>
								</td>								
							</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty channelAccountList}">
							暂无记录！
						</c:if>						
					</table>
					${pageInfo }
					</form>
				</div>
			</div>
			
			</div>
		
		</div>
	</body>
</html>
