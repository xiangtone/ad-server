<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/util/config_manager.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>新闻动态-<%=company%>广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/theme.js"></script>
<body>
    <a href="#" class="scrolltop">
        <span>up</span>
    </a>
    <!-- begins navbar -->
	<jsp:include page="/WEB-INF/jsp/include/common/top.jsp"></jsp:include>
    <!-- ends navbar -->

    <div id="blog_wrapper">
        <div class="container">
        
            <h2 class="section_header">
                <hr class="left visible-desktop"  style="margin-top:25px"/>
                <span>新闻动态</span>
                <hr class="right visible-desktop" style="margin-top:25px"/>
            </h2>
            <div class="row map">
                <div class="span12">
                    <div style="width: 100%;height: 200px;border: 3px solid #fff;border-radius: 6px;box-shadow: 0px 0px 1px 1px #CFCFCF;">
                        <img style="width: 100%; height: 100%; border: none;" src="${pageContext.request.contextPath}/img/help-center.jpg">
                    </div>
                </div>
            </div>

            <div class="row" id="faq">
                <div class="span3 sidebar offset1">


                    <h3 class="sidebar_header">
                        公告
                    </h3>

                    <ul class="sidebar_menu">
                        <li><a href="${pageContext.request.contextPath}/notice.jsp">通知公告</a></li>
                        <li><a href="${pageContext.request.contextPath}/activity.jsp">最新活动</a></li>
                        <li><a href="${pageContext.request.contextPath}/press.jsp">新闻动态</a></li>
                    </ul>


                </div>
                <div class="span8">
            <!-- header -->
                    <h4 class="header" id="新闻动态告">
                        新闻动态
                          <hr />
                    </h4>

                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="row">
                <div class="span12" style="width:96%;">

                </div>
            </div>     
                   <div class="pagination">
                        <ul>
                            <li><a href="#">Prev</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">Next</a></li>
                        </ul>
                    </div>

                </div>
              </div>

            </div>
        </div>
    </div>

    <!-- starts footer -->
    <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
    
</body>
</html>