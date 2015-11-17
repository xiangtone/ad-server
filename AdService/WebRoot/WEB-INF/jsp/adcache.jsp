<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>cache</title>
<style type="text/css">
	.errorimage{
		width: 100%;
		height: 100%
	}
</style>
</head>
<body>
	
	签到次数：${sign.sign_status},第一次签到:${sign.sign_first},第二次:${sign.sign_second}，第三次:${sign.sign_third}<br/>
	
	typeId:${typeId},广告数:${fn:length(list)}  <br/>
	<c:forEach items="${list}" var="ad">
	   adId:${ad.id}<br/>
	</c:forEach>
	
	
	    <c:choose>
       <c:when test="${!empty user}">
	        ${user.appIds},${user.uuid},${user.dateTag}<br/>
		   	 <c:forEach items="${user.signAdRels}" var="o">
		   	      ${o.ad_id} ,  ${o.create_time}<br/>
		   	 </c:forEach>
		   	 
		   	 
		   	  <c:forEach items="${user.scoreMap}" var="o">
		   	    ${o.key},${o.value.uuid},${o.value.score},${o.value.todayInteger},${o.value.downLoadTime}<br/>
		   	  </c:forEach>
       </c:when>
       <c:otherwise>
          user is null
       </c:otherwise>
    </c:choose>	
	
</body>
</html>