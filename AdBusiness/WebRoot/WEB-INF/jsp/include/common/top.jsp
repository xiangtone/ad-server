<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<div class="navbar navbar-fixed-top" style="position:fixed;">
      <div class="navbar-inner">
        <div class="container">
          	<a class="brand scroller" href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}<%=menu_head_logo%>" alt="logo" />
            </a>
		  	<div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <li><a href="${pageContext.request.contextPath}/index.jsp" class="scroller">首页</a></li>
                    <li><a href="${pageContext.request.contextPath}/developer.jsp" class="scroller">开发者服务中心</a></li>
                    <li><a href="${pageContext.request.contextPath}/help_center.jsp" class="scroller">帮助中心</a></li>
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/contactus.jsp" class="scroller">
                         	   联系我们
                        </a>
                    </li>
                   <li><a class="btn-header" href="javaScript:;" onclick="alert('账号正在审核中')">登录</a></li>
				<li><a class="btn-header" href="${pageContext.request.contextPath}/register.jsp">注册</a></li>
                </ul>
	        </div>
        </div>
      </div>
    </div>