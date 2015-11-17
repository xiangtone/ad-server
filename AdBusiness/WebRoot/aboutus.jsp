<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>关于我们-行云移动广告平台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/theme.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/external-pages.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
	<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
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
                <span>关于我们</span>
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
                    	    我们
                    </h3>
                    <ul class="sidebar_menu">
                        <li><a href="${pageContext.request.contextPath}/contactus.jsp">联系我们</a></li>
                        <li><a href="${pageContext.request.contextPath}/jionus.jsp">加入我们</a></li>
                        <li><a href="${pageContext.request.contextPath}/aboutus.jsp">关于我们</a></li>
                    </ul>
                </div>
                <div class="span8">
            <!-- header -->
                    <h4 class="header" id="开发者常见问题">
                        	关于我们
                        <hr />
                    </h4>
                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="row">
                <div class="span12" style="width:96%;">
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
						公司介绍                   
						     </div>
                        <div class="answer" style="display:inherit;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;北京行者天下科技有限公司，是2014年成立的新概念移动营销机构。致力于移动互联网的品牌传播和应用分发。
						行云开发者服务平台是移动营销领域第一家将移动app媒体，wifi传播网络，智能手机用户重定向网络以及移动应用分发网络通过大数据整合的高效，
						全覆盖网络平台。具有大数据，智能化开放化等特点。<br />
						合作电话：010-51088038<br />
						 公司地址：北京市朝阳区慧中北里天创世缘312号楼B2座1301  <br />                       
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
        <div class="pagination"></div>
                </div>
            </div>
        </div>
    </div>
    <!-- starts footer -->
    <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include> 
</body>
</html>