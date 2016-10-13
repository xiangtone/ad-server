<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<style>
<!--
.login{
	font-size:20px;
	border:1px solid;
	border-radius:15px;
}
-->
</style>


<div class="navbar navbar-fixed-top" style="position:fixed;">
      <div class="navbar-inner">
        <div class="container">
          	<a style="margin-left:15%" class="brand scroller" href="${pageContext.request.contextPath}/index.jsp">
                <img  src="${pageContext.request.contextPath}<%=menu_head_logo%>" alt="logo" width="85%" />
            </a>
		  	<div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <%-- <li><a href="${pageContext.request.contextPath}/index.jsp" class="scroller">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/developer.jsp" class="scroller">开发者服务中心</a></li>
                    <li><a href="${pageContext.request.contextPath}/help_center.jsp" class="scroller">帮助中心</a></li>
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/contactus.jsp" class="scroller">
                         	   联系我们
                        </a>
                    </li> --%>
                    <li style="margin-right:15px;"><a style="color:#000;font-size:20px;padding:2px 28px 2px;" href="${pageContext.request.contextPath}/register.jsp">注册</a></li>
                   <li><a style="color:#000;padding:2px 28px 2px;" class="login" href="javaScript:;" onclick="alert('账号正在审核中')"><font >登入</font></a></li>
				
                </ul>
	        </div>
        </div>
      </div>
    </div>