<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>联系我们-行云移动广告平台</title>
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
                <span>联系我们</span>
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
                       	 联系我们
                        <hr />
                    </h4>
                     <div class="container" style="width:100%">
            <!-- list -->
            <div class="row">
                <div class="span12" style="width:96%;">
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
                  	          媒体合作
                        </div>
                        <div class="answer" style="display:inherit;">                 
					                            媒介盖梆： <a href="tencent://message/?uin=1977975161&amp;Site=www.yijifen.com&amp;Menu=yes" target="_blank" class="send" style="color:#06C">1977975161</a> <br />
					                          媒介小雪：  <a href="tencent://message/?uin=1014300145&amp;Site=www.yijifen.com&amp;Menu=yes" target="_blank" class="send" style="color:#06C">1014300145</a> <br />
					                          媒介小丽：  <a href="tencent://message/?uin=745137433&amp;Site=www.yijifen.com&amp;Menu=yes" target="_blank" class="send" style="color:#06C">745137433</a> <br />
					                         技术支持群：234956451<br />
                        </div>
                    </div>
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
                          	广告合作
                        </div>
                        <div class="answer" style="display:inherit;">
						     商务海霞： <a href="tencent://message/?uin=1825998992&amp;Site=www.yijifen.com&amp;Menu=yes" target="_blank" class="send" style="color:#06C">1825998992</a> <br />
					               商务安冬：  <a href="tencent://message/?uin=2215006476&amp;Site=www.yijifen.com&amp;Menu=yes" target="_blank" class="send" style="color:#06C">2215006476</a> <br />
						    商务小健： <a href="tencent://message/?uin=2928476037&amp;Site=www.yijifen.com&amp;Menu=yes" target="_blank" class="send" style="color:#06C">2928476037</a> <br />
                        </div>
                    </div>
                    <div class="faq">
                        <div style="color:#2c3339;font-weight:bold;font-size: 17px;">
						公司联系方式                     
						   </div>
                        <div class="answer" style="display:inherit;">
						 电话：010-84249150<br />
						 地址：北京市朝阳区慧中北里天创世缘312号楼B2座1301<br />
						 邮编：100012<br />                           
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
        <div class="pagination">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- starts footer -->
  <jsp:include page="/WEB-INF/jsp/include/common/footer.jsp"></jsp:include>
    
</body>
</html>